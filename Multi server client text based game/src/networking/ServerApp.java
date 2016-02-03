package networking;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;
import java.util.*;
import org.apache.log4j.Logger;


import config.Config;
import gameRuleEngine.GameRuleEngine;
import player.Player;

public class ServerApp implements Runnable {
	
	int                                       clientCount = 0;
	private Thread                            thread = null;
	private ServerSocket                      server = null;
	private HashMap<Integer, ServerThread>    clients;
	
	private Scanner                           response = new Scanner(System.in);
	private int                               numPlayers;
	private int                               numRounds;
	
	private GameRuleEngine                    rules;
	
	private String                            playerName;
	private String                            playerToAttack;
	private String                            playerAttackMechanism;
	private int                               playerAttackSpeed;
	private String                            playerDefenseMechansim;
	private int                               playerDefenseSpeed;
	private int                               numberOfPlayersThatJustJoinedTheGame = 0;
	private Player                            player;
	
	private String                            sendListOfPlayersCurrentlyInTheGame = "";
	private String                            sendListOfWoundsGottenSoFar = "";
	private String							  nameFound = "";
	
	private int 							  noOfRollsSoFar;
	private int    							  numberOfWoundsSustainedSofar = 0;
	public  boolean							  didAClientConnect = false;
	
	public int 								  numberRoundsss = 1;
	
	static final Logger logger 				= Logger.getLogger("serverLogger");
	
	/* ArrayList to hold player object */
	private static List<Player> playerCurrentlyInGame = new ArrayList<Player>(Config.NUMBER_OF_PLAYERS_SPEC);
	
	/* ArrayList of players that just joined the game */
	private static List<String> playerJustJoined = new ArrayList<String>(Config.NUMBER_OF_PLAYERS_SPEC);
	
	/* ArrayList to hold message for number of injuries gotten by players */
	private static HashSet<String> outCome = new HashSet<String>(Config.NUMBER_OF_PLAYERS_SPEC); 
	
	public ServerApp(int port) {
		try {
			
			rules = new GameRuleEngine();
			
			logger.info("Binding to port " + port + ", please wait  ...");
			System.out.println("Binding to port " + port + ", please wait  ...");
			System.out.println("Binding to port " + port);
			logger.info("Binding to port " + port);
			/**
			 * I use a HashMap to keep track of the client connections and their
			 * related thread
			 */
			clients = new HashMap<Integer, ServerThread>();

			/** Establish the servers main port that it will listen on */
			server = new ServerSocket(port);
			/**
			 * Allows a ServerSocket to bind to the same address without raising
			 * an "already bind exception"
			 */
			server.setReuseAddress(true);
			start();
		} catch (IOException ioe) {
			System.out.println(ioe.getMessage());
			logger.error(ioe.getMessage());
		}
	}

	/** Now we start the servers main thread */
	public void start() {
		if (thread == null) {
			/* Now server started prompt user to enter number of players and number of games to play */
			System.out.println();
			System.out.println("=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=");
			System.out.println("WELCOME TO GAME OF THE SURVIVAL OF THE FITTEST");
			System.out.println("=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=\n");
			System.out.print("Enter number of players: ");
			numPlayers = response.nextInt();
			
			while(numPlayers > Config.MAX_NUM_PLAYERS_ALLOWED || numPlayers < Config.MIN_NUM_OF_PLAYER_ALLOWED) {
				if(numPlayers > Config.MAX_NUM_PLAYERS_ALLOWED || numPlayers < Config.MIN_NUM_OF_PLAYER_ALLOWED) {
					System.out.println();
					System.out.println("Enter a number between 2 and 4 for number of players.");
					System.out.println();
					System.out.print("Enter number of players: ");
					numPlayers = response.nextInt();
				}
				
				else {
					break;
				}
			}
			
			System.out.print("Enter number of rounds to play: ");
			numRounds = response.nextInt();
			
			Config.NUMBER_OF_PLAYERS_SPEC = numPlayers;
			Config.NUMBER_OF_ROUNDS_TO_BE_PLAYED = numRounds;
			
			System.out.println();
			
			thread = new Thread(this);
			thread.start();
			System.out.println(String.format("Server started: %s %d", server,thread.getId()));
			logger.info(String.format("Server started: %s %d", server,thread.getId()));
		}
	}

	/** The main server thread starts and is listening for clients to connect */
	public void run() {
		while (thread != null) {
			try {
				logger.info("Waiting for other players to join the game ...");
				System.out.println("===============================================");
				System.out.println("Waiting for other players to join the game ...");
				System.out.println("===============================================\n");
				addThread(server.accept());
			} catch (IOException e) {				
				System.out.println(e.getMessage());
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}}
	}

	/** 
	 * Client connection is accepted and now we need to handle it and register it 
	 * and with the server | HashTable 
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 **/
	private void addThread(Socket socket) throws IOException, ClassNotFoundException {
		System.out.println("Client accepted" + socket);
		logger.info("Client accepted" + socket);
		ServerThread serverThread = null;
		if (clientCount < Config.NUMBER_OF_PLAYERS_SPEC) {
			try {
				/** Create a separate server thread for each client */
				serverThread = new ServerThread(this, socket);
				/** Open and start the thread */
				serverThread.open();
				serverThread.start();
				clients.put(serverThread.getID(), serverThread);
				this.clientCount++;
				rules.setNumberOfPlayers(clientCount);
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
		} else {
			System.out.println("Client Tried to connect" + socket );
			logger.info("Client Tried to connect" + socket );
			System.out.println("Client refused: maximum number of player reached, this game session can only allow: " + Config.NUMBER_OF_PLAYERS_SPEC + " players.");
			logger.info("Client refused: maximum number of player reached, this game session can only allow: " + Config.NUMBER_OF_PLAYERS_SPEC + " players.");
		}
	}

	public synchronized void handle(int ID, String input) {
		
		if (input.equals("game done")) 
		{
			logger.info(input);
			System.out.println("Removing Client:" + ID);
			if (clients.containsKey(ID)) {
				clients.get(ID).send("quit!" + "\n");
				remove(ID);
			}
		}
		
		else if (input.equals("shutdown!")) { 
			logger.info(input);	
			shutdown(); 
		}
		
		else if (input.startsWith("join")) {
			
			logger.info(input);
			/* Echo to all clients */
			sendMessageToAllClients("Player " + ID +" broadcasted: " + input);
			sendToOneClient("Server sent: startGame " + Config.NUMBER_OF_PLAYERS_SPEC + " Players " + numRounds + " rounds", ID);
			
			logger.info("Player " + ID +" broadcasted: " + input);
			logger.info("Server sent: startGame " + Config.NUMBER_OF_PLAYERS_SPEC + " Players " + numRounds + " rounds");
			
			numberOfPlayersThatJustJoinedTheGame ++;
			System.out.println(numberOfPlayersThatJustJoinedTheGame + " players just joined");
			String[] splitIt = input.split(" ");
			
			playerJustJoined.add(splitIt[1]);
			
			if(numberOfPlayersThatJustJoinedTheGame == Config.NUMBER_OF_PLAYERS_SPEC) {
				for(int i = 0; i < playerJustJoined.size(); i++) {
					sendListOfPlayersCurrentlyInTheGame += playerJustJoined.get(i) + " ";
				}
				sendMessageToAllClients("List of players In the Game you can attack: [ " + sendListOfPlayersCurrentlyInTheGame + " ]");
				logger.info("List of players In the Game you can attack: [ " + sendListOfPlayersCurrentlyInTheGame + " ]");
			}			
			sendMessageToAllClients(rules.serverSendsAMessageBack(splitIt[0]));
			logger.info(rules.serverSendsAMessageBack(splitIt[0]));
		}
		
		else if (input.startsWith("select")) {
			
			logger.info(input);
			
			/* Echo to all clients */
			sendMessageToAllClients("Player " + ID +" broadcasted: " + input);
			
			String select;
			boolean                           isItAValidAttackMove;
			boolean                           isItAValidDefenseMove;
			boolean                           isItAValidAttackSpeedAndDefendSpeed;
			boolean							  isItAValidAttackSpeed;
			boolean							  isItAValidDefenseSpeed;
			
			
			String[] splitIt = input.split(" ");
			
			select                 = splitIt[0];
			playerName             = splitIt[1];
			playerToAttack         = splitIt[2];
			playerAttackMechanism  = splitIt[3].toUpperCase();
			playerAttackSpeed      = Integer.parseInt(splitIt[4]);
			playerDefenseMechansim = splitIt[5].toUpperCase();
			playerDefenseSpeed     = Integer.parseInt(splitIt[6]);
			
			isItAValidAttackMove                = rules.checkIfAttackIsValid(playerAttackMechanism);
			isItAValidDefenseMove               = rules.checkIfDefenseIsValid(playerDefenseMechansim);
			isItAValidAttackSpeedAndDefendSpeed = rules.checkAttackSpeedAndDefenseSpeed(playerAttackSpeed, playerDefenseSpeed);
			isItAValidAttackSpeed               = rules.attackSpeedValid(playerAttackSpeed);
			isItAValidDefenseSpeed              = rules.defenseSpeedValid(playerDefenseSpeed);
			
			
			/* Loop through the list of players and make sure that player to attack is in that array */ 
			for(int i = 0; i < playerJustJoined.size(); i++) {
				if(playerToAttack.equals(playerJustJoined.get(i))) {
					nameFound = playerJustJoined.get(i);
					break;
				}
			}
			
			/* If player name to attack exists then proceed to game logic */
			if(playerToAttack.equals(nameFound)) {
				System.out.println("I am here");
				if(isItAValidAttackMove == true && isItAValidDefenseMove == true && isItAValidAttackSpeedAndDefendSpeed == true) {
					
					rules.incrementNoOfSelectReceived();
					System.out.println(rules.getNumberOfSelectMessagesRecieved());
					System.out.println("I passed this test");
					
					String message = rules.checkSelectMessage(Config.NUMBER_OF_PLAYERS_SPEC, select, playerAttackMechanism, playerDefenseMechansim, playerAttackSpeed, playerDefenseSpeed);
					sendToOneClient(message, ID);
					
					System.out.println("sent select message");
					
					/* Create a new Player and store their attributes supplied to the server in an array */
					player = new Player(playerName, playerToAttack, playerAttackMechanism, playerAttackSpeed, playerDefenseMechansim, playerDefenseSpeed);
					playerCurrentlyInGame.add(player);
					
					logger.info(message);
				}
				
				/* Check if the state of the rule engine is a rollToResolve and send a message to the client */
				if(rules.getState() == rules.SENT_ROLL_TO_RESOLVE) {
					String message = rules.checkSelectMessage2(Config.NUMBER_OF_PLAYERS_SPEC, select, playerAttackMechanism, playerDefenseMechansim, playerAttackSpeed, playerDefenseSpeed);
					sendMessageToAllClients(message);
					
					logger.info(message);
				}
				
				else if(isItAValidAttackMove == false || isItAValidDefenseMove == false || isItAValidAttackSpeed == false || isItAValidDefenseSpeed == false) {
					String message = rules.checkSelectMessage1(Config.NUMBER_OF_PLAYERS_SPEC, select, playerAttackMechanism, playerDefenseMechansim, playerAttackSpeed, playerDefenseSpeed);
					sendToOneClient(message, ID);
					
					logger.info(message);
				}
			}
			
			/* The player name to attack is then invalid send a message across */
			else {
				sendToOneClient("invalid attack name", ID);
				logger.info("invalid attack name");
			}
				
		}
		
		else if(input.equals("Your attack speed plus defense speed must be greater than 4 try again!")) {
			logger.info(input);
			sendToOneClient("re enter credentials", ID);
		}
		
		else if(input.startsWith("roll")) {
			/* Echo to all clients */
			sendMessageToAllClients("Player " + ID +" broadcasted: " + input);
			logger.info("Player " + ID +" broadcasted: " + input);
			
			noOfRollsSoFar++;
			
			String[] splitIt = input.split(" ");
			
			String rollAttrib = splitIt[0];
			String playerName = splitIt[1];
			int    dieNumber  = Integer.parseInt(splitIt[2]);
					
			String currentPlayersAttackMechanism = "";
			String playerToAttackNow = "";
			String playerToAttackDefenseMechanism="";
			String playerFinalAttackToBeUsed;
			int    currentPlayerAttackSpeed = 0;
			int    opponentDefenseSpeed = 0;
			 
			boolean didThePlayerHitItsOpponentNotInAllDirections = false;
			boolean didThePlayerHitItsOpponentInAllDirections = false;
			
			
			/* Loop through the array of players currently in game session and get player to attack and the player attack 
			 * mechanism as well a the player attack speed too.
			 */
			for(int i = 0; i < playerCurrentlyInGame.size(); i++) {
				if(playerName.equals(playerCurrentlyInGame.get(i).getPlayerName())) {
					playerToAttackNow                    = playerCurrentlyInGame.get(i).getPlayerToAttack();
					currentPlayersAttackMechanism        = playerCurrentlyInGame.get(i).getAttackMechanism(); 
					currentPlayerAttackSpeed             = playerCurrentlyInGame.get(i).getAttackSpeed();
				}
			}
			
			
			/* Loop through the array of players currently in game session and get opponents defense mechanism 
			 * as well as their defense speed too.
			 */
			for(int j = 0; j < playerCurrentlyInGame.size(); j++) {
				if(playerToAttackNow.equals(playerCurrentlyInGame.get(j).getPlayerName())) {
					playerToAttackDefenseMechanism = playerCurrentlyInGame.get(j).getDefenseMechanism();
					opponentDefenseSpeed           = playerCurrentlyInGame.get(j).getDefenseSpeed();
				}
			}
			
			
			/* Now I go process my attack */
			playerFinalAttackToBeUsed = rules.finalAttackToBeUsed(currentPlayersAttackMechanism, dieNumber);
			
			
			/* Now I can strike a player and inflict a wound on that player */
			didThePlayerHitItsOpponentNotInAllDirections = rules.isItAHit(playerFinalAttackToBeUsed, playerToAttackDefenseMechanism, currentPlayerAttackSpeed, opponentDefenseSpeed);
			didThePlayerHitItsOpponentInAllDirections    = rules.checkHitSpeedAndCorrectDefenseAndAttack(playerFinalAttackToBeUsed, playerToAttackDefenseMechanism, currentPlayerAttackSpeed, opponentDefenseSpeed);
			
			System.out.println("Hit in all directions: " + didThePlayerHitItsOpponentInAllDirections);
			
			/* Now check if it a hit in all directions */
			if(didThePlayerHitItsOpponentInAllDirections) {
				for(int k = 0; k < playerCurrentlyInGame.size(); k++) {
					if(playerToAttackNow.equals(playerCurrentlyInGame.get(k).getPlayerName())) {
						playerCurrentlyInGame.get(k).incrementNumberOfWoundsSustained();
						numberOfWoundsSustainedSofar = playerCurrentlyInGame.get(k).getNumberOfWoundsSustained();
					}
				}
			}
			
			if(didThePlayerHitItsOpponentNotInAllDirections) {
				for(int k = 0; k < playerCurrentlyInGame.size(); k++) {
					if(playerToAttackNow.equals(playerCurrentlyInGame.get(k).getPlayerName())) {
						playerCurrentlyInGame.get(k).incrementNumberOfWoundsSustained();
						numberOfWoundsSustainedSofar = playerCurrentlyInGame.get(k).getNumberOfWoundsSustained();
					}
				}
			}
			
			if(noOfRollsSoFar == Config.NUMBER_OF_PLAYERS_SPEC) {
				for(int i = 0; i < playerCurrentlyInGame.size(); i++) {
					outCome.add(playerCurrentlyInGame.get(i).getPlayerName() + " got " + playerCurrentlyInGame.get(i).getNumberOfWoundsSustained() + " hit");
				}
				sendListOfWoundsGottenSoFar += outCome.toString() + "\n";
				sendMessageToAllClients("OUTCOME " + sendListOfWoundsGottenSoFar);
				
				/* now decrease number of rounds */
				numRounds--;
				Config.NUMBER_OF_ROUNDS_TO_BE_PLAYED = numRounds;
				
				logger.info("OUTCOME " + sendListOfWoundsGottenSoFar);
				
				/* Check if the rounds are done or not */
				if(numRounds > 0) {
					numberRoundsss++;
					String numberRoundssss = String.valueOf(numberRoundsss);
					
					String numRoundss = Integer.toString(numRounds);
					sendMessageToAllClients("There is still more rounds to play " + numberRoundsss);
					
					logger.info("There is still more rounds to play " + numberRoundsss);
					
					/* Reset the game now its done */
					resetGame();
				}
				else {
					/* Round is now done */
					sendMessageToAllClients("Rounds is done");
					
					logger.info("Round is done");
				}
			}
			else {	
				/* Send a message to client to wait to for server to receiver and process all rolls */
				sendToOneClient("Wait to receive rolls and process it from other players", ID);
				
				logger.info("Wait to receive rolls and process it from other players");
			}
		}
		
		else {
			System.out.println(input);
			logger.info(input);
		}
	}
	
	/* Send message to all players */
	public void sendMessageToAllClients(String input) {
		for (ServerThread to : clients.values()) {
			to.send(String.format("%s\n", input));
		}
	}
	
	/* Send message to only one player */
	public void sendToOneClient(String input, int ID) {
		ServerThread from = clients.get(ID);
		from.send(String.format("%s\n", input));
	}
	
	/* Reset the game when the rounds are done */
	public void resetGame() {
		outCome.clear();
		rules.resetNumberOfSelectMessages();
		numberOfPlayersThatJustJoinedTheGame = 0;
		playerName = "";
		playerToAttack = "";
		playerAttackMechanism = "";
		playerAttackSpeed = 0;
		playerDefenseMechansim = "";
		playerDefenseSpeed = 0;
		sendListOfPlayersCurrentlyInTheGame = "";
		sendListOfWoundsGottenSoFar = "";
		nameFound = "";
		noOfRollsSoFar = 0;
		numberOfWoundsSustainedSofar = 0;
		rules.resetGameState();
		
		for(int i = 0; i < playerCurrentlyInGame.size(); i++) {
			playerCurrentlyInGame.get(i).setNumberOfWoundsToZeroWhenNoHit();
		}
	}

	/** Try and shutdown the client cleanly */
	public synchronized void remove(int ID) {
		if (clients.containsKey(ID)) {
			ServerThread toTerminate = clients.get(ID);
			clients.remove(ID);
			clientCount--;

			toTerminate.close();
			toTerminate = null;
		}
	}

	/** Shutdown the server cleanly */
	public void shutdown() {
		Set<Integer> keys = clients.keySet();

		if (thread != null) {
			thread = null;
		}

		try {
			for (Integer key : keys) {
				clients.get(key).close();
				clients.put(key, null);
			}
			clients.clear();
			server.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		System.out.println("Server Shutdown cleanly" + server );
	}
	
}