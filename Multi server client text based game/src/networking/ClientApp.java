package networking;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import org.apache.log4j.Logger;

import config.Config;
import player.Player;

public class ClientApp implements Runnable{

	private int ID = 0;
	private Socket socket            = null;
	private Thread thread            = null;
	private ClientThread   client    = null;
	private BufferedReader console   = null;
	private BufferedReader streamIn  = null;
	private BufferedWriter streamOut = null;

	private Scanner response;
	
	private String  playerName;
	private String  playerAttackMechanism;
	private int     playerAttackSpeed;
	private String  playerDefenseMechanism;
	private int     playerDefenseSpeed;
	private String  playerToAttack;
	private int    	promptUserToEnterADieNumber;
	Player          player;
	private String 	sendJoinMessageAcross;
	
	static final Logger logger 				= Logger.getLogger("clientLogger");

	public ClientApp (String serverName, int serverPort) {  
		
		response   = new Scanner(System.in);

		System.out.print("Enter your name: ");
		playerName = response.nextLine();
		System.out.println();

		System.out.println(playerName + ": Establishing connection. Please wait ...");
		logger.info(playerName + ": Establishing connection. Please wait ...");
		player = new Player(playerName);

		try {  
			this.socket = new Socket(serverName, serverPort);
			this.ID = socket.getLocalPort();
			System.out.println();
			logger.info("=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=");
			System.out.println("=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=");
			logger.info("WELCOME TO GAME OF THE SURVIVAL OF THE FITTEST");
			System.out.println("WELCOME TO GAME OF THE SURVIVAL OF THE FITTEST");
			logger.info("=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=\n");
			System.out.println("=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=\n");
			System.out.println(playerName + ": Connected to server: " + socket.getInetAddress());
			logger.info(playerName + ": Connected to server: " + socket.getInetAddress());
			System.out.println(playerName + ": Connected to portid: " + socket.getLocalPort());
			logger.info(playerName + ": Connected to portid: " + socket.getLocalPort());
			this.start();
			sendJoinMessageAcross = sendJoinMessage(playerName, socket.getInetAddress().getHostAddress());
			send(sendJoinMessageAcross);
			logger.info("Client sent to server: " + sendJoinMessageAcross);
		} catch(UnknownHostException uhe) {  
			System.err.println(ID + ": Unknown Host");
			logger.error(ID + ": Unknown Host");
			System.out.println(uhe.getMessage());
			logger.info(uhe.getMessage());
		} catch(IOException ioe) {  
			logger.error(ID + ": Unexpected exception");
			System.out.println(ID + ": Unexpected exception");
			System.out.println(ioe.getMessage());
			logger.info(ioe.getMessage());
		}
	}

	public int getID () {
		return this.ID;
	}

	public void start() throws IOException {  
		try {
			console	= new BufferedReader(new InputStreamReader(System.in));
			streamIn	= new BufferedReader(new InputStreamReader(socket.getInputStream()));
			streamOut = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

			if (thread == null) {  
				client = new ClientThread(this, socket);
				thread = new Thread(this);                   
				thread.start();
			}
		} catch (IOException ioe) {
			System.out.println(ioe.getMessage());  
			throw ioe;
		}
	}

	/** The server processes the messages and passes it to the client to send it */
	public void send(String input) {
		
		try {
			streamOut.write(input + "\n");
			streamOut.flush();
		} catch (IOException ioe) {
			System.out.println(ioe.getMessage());
		}
	}

	public void run() { 
		System.out.println("\n" + ID + ": Client Started...\n");
		logger.info("\n" + ID + ": Client Started...\n");
	}

	public void handle (String msg) throws IOException {
		if (msg.equalsIgnoreCase("quit!")) {  
			System.out.println(ID + "Good bye. Press RETURN to exit ...");
			logger.info(ID + "Good bye. Press RETURN to exit ...");
			stop();
		}

		else if(msg.startsWith("GAME")) {
			logger.info("Message from the server: " + msg);
			promptForPlayerAttributes();
			String sendSelectMessage = sendSelectMessageToServer(playerName, playerToAttack, playerAttackMechanism, playerAttackSpeed, playerDefenseMechanism, playerDefenseSpeed);
			send(sendSelectMessage);
			logger.info("client sends this info to the server: " + sendSelectMessage);
		}
		
		else if(msg.equals("WAITING FOR MORE PLAYERS")) {
			logger.info("Message from the server: " + msg);
			System.out.println();
			System.out.println("=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=");
			System.out.println("Waiting for sufficient amount of players to join the game session");
			System.out.println("=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=\n");
		}
		
		else if(msg.equals("WAITING FOR OTHER PLAYERS SELECT MESSAGES")) {
			logger.info("Message from the server: " + msg);
			System.out.println();
			System.out.println("=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*");
			System.out.println("Waiting to receive all other players select messages to proceed");
			System.out.println("=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*\n");
		}
		
		else if(msg.equals("RESOLVE ROLL")) {
			logger.info("Message from the server: " + msg);
			System.out.println("=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=");
			System.out.println("You can now roll as server is done receiving all select messages from other players");
			System.out.println("=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=\n");
			
			response.nextLine();
			int responseFromUser         = promptUserToRoll();
			//int    generateARandomDieNumber = randomDieNumber();
			//send(responseFromUser + " " + playerName + " " + generateARandomDieNumber);
			send("roll " + playerName + " " + responseFromUser);
			logger.info("Client sends this message to the server: " + "roll " + playerName + " " + responseFromUser);
		}
		
		else if(msg.equals("re enter credentials")) {
			logger.info("Message from server: " + msg);
			System.out.println();
			System.out.println("============-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-================");
			System.out.println("the sum of your attack and defense must be greater than or equal to 4 and not less.. :)");
			System.out.println("============-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-================\n");
			
			response.nextLine();
			promptForPlayerAttributes();
			String sendSelectMessage = sendSelectMessageToServer(playerName, playerToAttack, playerAttackMechanism, playerAttackSpeed, playerDefenseMechanism, playerDefenseSpeed);
			send(sendSelectMessage);
			logger.info("Client sends this info back to the server: " + sendSelectMessage);
		}
		
		else if(msg.equals("WRONG ATTACK OR DEFENCE OR SPEED DETAILS")) {
			logger.info("Message from server: " + msg);
			System.out.println();
			System.out.println("============-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=================");
			System.out.println("You must have entered an invalid attack or defense mechanism or invalid attack speed or defense speed.. Please enter a correct one:)");
			System.out.println("============-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=--=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=--=================\n");
			
			response.nextLine();
			promptForPlayerAttributes();
			String sendSelectMessage = sendSelectMessageToServer(playerName, playerToAttack, playerAttackMechanism, playerAttackSpeed, playerDefenseMechanism, playerDefenseSpeed);
			send(sendSelectMessage);
			logger.info("Client sends this info back to the server: " + sendSelectMessage);
		}
		
		else if(msg.equals("invalid attack name")) {
			logger.info("Message from server: " + msg);
			System.out.println("\n");
			System.out.println("=^=^=^=^=^=^=^=^=^=^=^=^=^=^=^=^=^=^=^=^=^=^=^=^=^=^=^=^=^=^=^=^=^=^=^=^=^=^=^=^=^=^=^=^=^=^=^=^=^=^=^=^=^=^=^=^=^=^=^=^=^=^=^=^=^=^=^");
			System.out.println("You have entered an invalid player to attack which is not in the game session. Look through the array of players to attack above you.");
			System.out.println("=^=^=^=^=^=^=^=^=^=^=^=^=^=^=^=^=^=^=^=^=^=^=^=^=^=^=^=^=^=^=^=^=^=^=^=^=^=^=^=^=^=^=^=^=^=^=^=^=^=^=^=^=^=^=^=^=^=^=^=^=^=^=^=^=^=^=^");
			System.out.println();
			
			response.nextLine();
			promptForPlayerAttributes();
			String sendSelectMessage = sendSelectMessageToServer(playerName, playerToAttack, playerAttackMechanism, playerAttackSpeed, playerDefenseMechanism, playerDefenseSpeed);
			send(sendSelectMessage);
			logger.info("Client sent to this to the server: " + sendSelectMessage);
		}
		
		else if(msg.startsWith("List")) {
			logger.info("Message from server: " + msg);
			System.out.println("\n");
			System.out.println(msg);
		}
		
		else if(msg.startsWith("OUTCOME")) {
			logger.info("Message from server: " + msg);
			System.out.println();
			System.out.println("=-=-=--=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=--=--=-=-=-=-=-=-=--=--=-=-=");
			System.out.println("Server is done receiving and processing all rolls, here are the outcomes: ");
			System.out.println("=-=-=--=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=--=--=-=-=-=-=-=-=--=--=-=-=");
			String finalOutCome = msg.substring(8, msg.length());
			System.out.println("For this round the total casualty was: " + finalOutCome);
			logger.info("For this round the total casualty was: " + finalOutCome);
		}
		
		else if(msg.equals("Wait to receive rolls and process it from other players")) {
			logger.info("Message from server: " + msg);
			System.out.println("\n");
			System.out.println("=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+");
			System.out.println("Please wait while we are trying to receive all rolls and process them..");
			System.out.println("=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+");
			System.out.println("\n");
		}
		
		else if(msg.startsWith("There")) {
			logger.info("Message from server: " + msg);
			String[] splitIt = msg.split(" ");
			String roundNumber = splitIt[7];
			System.out.println("\n");
			
			System.out.println("+-+-+-+-+-+-+-+-+-+-+-+-+-+");
			System.out.println("Starting next round: " + roundNumber);
			System.out.println("+-+-+-+-+-+-+-+-+-+-+-+-+-+");
		
			response.nextLine();
			promptForPlayerAttributes();
			String sendSelectMessage = sendSelectMessageToServer(playerName, playerToAttack, playerAttackMechanism, playerAttackSpeed, playerDefenseMechanism, playerDefenseSpeed);
			send(sendSelectMessage);	
			logger.info("Client sent this message to the server server: " + msg);
		}
		
		else if(msg.equals("Rounds is done")) {
			logger.info("Message from server: " + msg);
			System.out.println("\n");
			System.out.println("=<>=<>=<>=<>=<>=<>=<>=<>=<>=<>=<>=<>=<>=<>=<>=<>=<>=<>=<>=<>=<>=<>=<>=<>=<>=<>=<>=<>=<>=");
			System.out.println("Round is FINALLY done! Hope you enjoyed the game. Thanks for trying the game out too :)");
			System.out.println("=<>=<>=<>=<>=<>=<>=<>=<>=<>=<>=<>=<>=<>=<>=<>=<>=<>=<>=<>=<>=<>=<>=<>=<>=<>=<>=<>=<>=<>=");
			
			send("game done");
			logger.info("Message sent to the server: " + "game done");
		}

		else {
			System.out.println(msg);
			logger.info(msg);
		}
	}

	/* Prompt my user for attributes like attack and defense */
	public void promptForPlayerAttributes() {

		System.out.println();
		System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
		System.out.println("You can now enter your respective attack and defense");
		System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-\n");

		System.out.print("Enter your attack Mechanism: ");
		playerAttackMechanism = response.nextLine();
		
		System.out.print("Enter opponent name to attack: ");
		playerToAttack = response.nextLine(); 
		while(playerToAttack.equals(playerName)) {
			if(playerToAttack.equals(playerName)) {
				System.out.println("You cannot attack yourself select another player :)\n");
				System.out.print("Enter opponent name to attack: ");
				playerToAttack = response.nextLine(); 
			}
			
			else {
				break;
			}
		}

		System.out.print("Enter your attack speed: ");
		playerAttackSpeed = response.nextInt();
		
		System.out.print("Enter your defense Mechanism: ");
		playerDefenseMechanism = response.next();

		System.out.print("Enter your defense Speed: ");
		playerDefenseSpeed = response.nextInt();

	}
	
	/* Prompt user to enter a roll message */
	public int promptUserToRoll() {
		System.out.println();
		/* System.out.print("Roll to attack your opponent [e.g: type roll and hit the enter key]: ");
		promptUserToEnterRoll = response.nextLine(); */
		
		System.out.print("Enter a die number to roll: [e.g Range of 1- 6]: ");
		promptUserToEnterADieNumber = response.nextInt();
		
		while(promptUserToEnterADieNumber > 6 || promptUserToEnterADieNumber < 1) {
			if(promptUserToEnterADieNumber > 6 || promptUserToEnterADieNumber < 1) {
				System.out.print("Enter a die number to roll: [e.g Range of 1- 6]: ");
				promptUserToEnterADieNumber = response.nextInt();
			}
			
			else {
				break;
			}
		}
		
		return promptUserToEnterADieNumber;
	}
	

	/* Send my select message to the server */
	public String sendSelectMessageToServer(String playerName, String playerToAttack, String attackMechanism, int attackSpeed, String defenseMechanism, int defenseSpeed) {
		String output = "";

		if((attackSpeed + defenseSpeed) >= Config.ATTACK_AND_DEFENCE_MAX) {
			output = "select " + playerName + " " + playerToAttack + " " + attackMechanism + " " + attackSpeed + " " + defenseMechanism + " " + defenseSpeed;				
		}
		
		else {
			output = "Your attack speed plus defense speed must be greater than 4 try again!";
		}

		return output;
	}
	
	
	/* generate a random number for each die */
	public int randomDieNumber() {
		int randomNumber = 1 + (int)(Math.random() * ((6 - 1) + 1));
		return randomNumber;
	}
	
	/* Join message sent to server */
	public String sendJoinMessage(String playerName, String ipAddress) {
		String joinMessage = "join " + playerName + " " + ipAddress;
		
		return joinMessage;
	}

	public void stop() {  
		try { 
			if (thread != null) thread = null;
			if (console != null) console.close();
			if (streamIn != null) streamIn.close();
			if (streamOut != null) streamOut.close();

			if (socket != null) socket.close();

			this.socket = null;
			this.console = null;
			this.streamIn = null;
			this.streamOut = null;    	  
		} catch(IOException ioe) {  
			System.out.println(ioe.getMessage());  
		}
		client.close();  
	}
}