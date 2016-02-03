package gameRuleEngine;

import config.Config;

public class GameRuleEngine {
	
	public static int WAITING       	        		   = 0;
	public static int SENT_REQUEST  	        		   = 1;
	public static int GAME_START	        		       = 2;
	public static int WAITING_FOR_PLAYERS       	 	   = 3;
	public static int EXCEEDED_CAPACITY         		   = 4;
	public static int PLAYER_JOINED             		   = 5;
	public static int SENT_SELECT_MESSAGE       		   = 6;
	public static int SENT_ROLL_TO_RESOLVE      		   = 7;
	public static int RESOLVED_FINAL_ATTACK_TO_BE_USED     = 8;
	
	public int CURRENT_NUM_PLAYER               = 0;	
	public int NUMBER_OF_SELECT_MESSAGES_SENT   = 0;
	
	public String[]   defenceMoves              = {"CHARGE", "DODGE", "DUCK"};
	public String[]   attackMoves               = {"THRUST", "SWING", "SMASH"};
	
	int               state                     = WAITING;
	
	public int getState() {
		return state;
	}
	
	public GameRuleEngine() {
		//System.out.println("Calling Game Rule Engine to check Rules...");
	}
	
	public String serverSendsAMessageBack(String input) {
		String output = "";
		
		if((CURRENT_NUM_PLAYER == Config.NUMBER_OF_PLAYERS_SPEC) && (input.equalsIgnoreCase("join"))) {
			output = "GAME READY";
			state = GAME_START;
		}
		
		else {
			state = WAITING_FOR_PLAYERS;
			output = "WAITING FOR MORE PLAYERS";
		}
		
		return output;
	}
	
	public String waitingForMorePlayers(String input) {
		String output = "";
		if(state == WAITING_FOR_PLAYERS) {
			output = "WAITING FOR MORE PLAYERS";
		}
		return output;
	}
	
	public String checkSelectMessage(int noOfPlayers, String input, String attackMech, String defenseMech, int attackSpeed, int defenseSpeed) {
		String output ="";
		
		if(input.equals("select") && checkIfAttackIsValid(attackMech) && checkIfDefenseIsValid(defenseMech) && checkAttackSpeedAndDefenseSpeed(attackSpeed, defenseSpeed) && NUMBER_OF_SELECT_MESSAGES_SENT < noOfPlayers) {
			output = "WAITING FOR OTHER PLAYERS SELECT MESSAGES";
		}
		state = SENT_ROLL_TO_RESOLVE;
		
		return output;
	}
	
	public void resetNumberOfSelectMessages() {
		NUMBER_OF_SELECT_MESSAGES_SENT = 0;
	}
	
	public String checkSelectMessage2(int noOfPlayers, String input, String attackMech, String defenseMech, int attackSpeed, int defenseSpeed) {
		String output ="";
		
		if(input.equals("select") && checkIfAttackIsValid(attackMech) && checkIfDefenseIsValid(defenseMech) &&  checkAttackSpeedAndDefenseSpeed(attackSpeed, defenseSpeed) && NUMBER_OF_SELECT_MESSAGES_SENT == noOfPlayers) {
			output = "RESOLVE ROLL";
		}
		return output;
	}
	
	public String checkSelectMessage1(int noOfPlayers, String input, String attackMech, String defenseMech, int attackSpeed, int defenseSpeed) {
		String output ="";
		
		if(input.equals("select") && !checkIfAttackIsValid(attackMech) || !checkIfDefenseIsValid(defenseMech) || !checkAttackSpeedAndDefenseSpeed(attackSpeed, defenseSpeed)) {
			output = "WRONG ATTACK OR DEFENCE OR SPEED DETAILS";
		}
		return output;
	}
	
	public boolean checkAttackSpeedAndDefenseSpeed(int attackSpeed, int defenseSpeed) {
		boolean flag = false;
		
		if(attackSpeed >= 1 && attackSpeed <= 3 && defenseSpeed >= 1 && defenseSpeed <= 3) {
			flag = true;
		}
		
		else {
			flag = false;
		}
		
		return flag;
	}
	
	/* Determine if it's a hit or not after attack hits */
	public boolean isItAHit(String finalAttackToBeUsed, String opponentsDefense, int playerAttackSpeed, int opponentDefenseSpeed) {
		
		boolean hitOpponent = false;
		
		if(finalAttackToBeUsed.equals(attackMoves[0]) && opponentsDefense.equals(defenceMoves[0]) ) {
			hitOpponent = true;
		}

		else if(finalAttackToBeUsed.equals(attackMoves[1]) && opponentsDefense.equals(defenceMoves[1])) {
			hitOpponent = true;
		}

		else if(finalAttackToBeUsed.equals(attackMoves[2]) && opponentsDefense.equals(defenceMoves[2])) {
			hitOpponent = true;
		}

		else if(finalAttackToBeUsed.equals(attackMoves[0]) && opponentsDefense.equals(defenceMoves[1]) || opponentsDefense.equals(defenceMoves[2])) {
			if(playerAttackSpeed < opponentDefenseSpeed) {
				hitOpponent = true;
			}
		}

		else if(finalAttackToBeUsed.equals(attackMoves[1]) && opponentsDefense.equals(defenceMoves[0]) || opponentsDefense.equals(defenceMoves[2])) {
			if(playerAttackSpeed < opponentDefenseSpeed) {
				hitOpponent = true;
			}
		}

		else if(finalAttackToBeUsed.equals(attackMoves[2]) && opponentsDefense.equals(defenceMoves[0]) || opponentsDefense.equals(defenceMoves[1])) {
			if(playerAttackSpeed < opponentDefenseSpeed) {
				hitOpponent = true;
			}
		}
		
		return hitOpponent;
	}
		
	/* This will return a false or true depending if the number is below 1 or above 3 or in between 1 and 3 */
	public boolean attackSpeedValid(int attackSpeed) {
		boolean flag = false;
		
		if(attackSpeed >= 1 && attackSpeed <= 3) {
			flag = true;
		}
		
		else {
			flag = false;
		}
		
		return flag;
	}
		
	/* This will return a false or true depending if the number is below 1 or above 3 or in between 1 and 3 */
	public boolean defenseSpeedValid(int defenseSpeed) {
		boolean flag = false;
		
		if(defenseSpeed >= 1 && defenseSpeed <= 3) {
			flag = true;
		}
		
		else {
			flag = false;
		}
		
		return flag;
	}
	
	/* Check if defense mechanism is a valid defense mechanism */
	public boolean checkIfDefenseIsValid(String input) {
		boolean flag = false;
		
		if(input.equals(defenceMoves[0]))
			flag = true;
		else if(input.equals(defenceMoves[1]))
			flag = true;
		else if(input.equals(defenceMoves[2]))
			flag = true;
		else
			flag = false;
		
		return flag;
	}	
	
	/* Check if attack mechanism is a valid attack mechanism */
	public boolean checkIfAttackIsValid(String input) {
		boolean flag = false;
		
		if(input.equals(attackMoves[0]))
			flag = true;
		else if(input.equals(attackMoves[1]))
			flag = true;
		else if(input.equals(attackMoves[2]))
			flag = true;
		else
			flag = false;
		
		return flag;
	}
	
	/* Rule to determine what kind of attack it should use to finally strike enemy */
	public String finalAttackToBeUsed(String currentPlayerAttackName, int dieNumber) {
		String finalAttack = "";
		
		if(currentPlayerAttackName.equals(attackMoves[0]) && dieNumber == 1) {
			finalAttack = attackMoves[0];
		}

		else if(currentPlayerAttackName.equals(attackMoves[0]) && dieNumber == 2) {
			finalAttack = attackMoves[0];
		}

		else if(currentPlayerAttackName.equals(attackMoves[0]) && dieNumber == 3) {
			finalAttack = attackMoves[2];
		}

		else if(currentPlayerAttackName.equals(attackMoves[0]) && dieNumber == 4) {
			finalAttack = attackMoves[2];
		}

		else if(currentPlayerAttackName.equals(attackMoves[0]) && dieNumber == 5) {
			finalAttack = attackMoves[1];
		}

		else if(currentPlayerAttackName.equals(attackMoves[0]) && dieNumber == 6) {
			finalAttack = attackMoves[1];
		}

		else if(currentPlayerAttackName.equals(attackMoves[1]) && dieNumber == 1) {
			finalAttack = attackMoves[1];
		}

		else if(currentPlayerAttackName.equals(attackMoves[1]) && dieNumber == 2) {
			finalAttack = attackMoves[1];
		}

		else if(currentPlayerAttackName.equals(attackMoves[1]) && dieNumber == 3) {
			finalAttack = attackMoves[0];
		}

		else if(currentPlayerAttackName.equals(attackMoves[1]) && dieNumber == 4) {
			finalAttack = attackMoves[0];
		}

		else if(currentPlayerAttackName.equals(attackMoves[1]) && dieNumber == 5) {
			finalAttack = attackMoves[2];
		}

		else if(currentPlayerAttackName.equals(attackMoves[1]) && dieNumber == 6) {
			finalAttack = attackMoves[2];
		}

		else if(currentPlayerAttackName.equals(attackMoves[2]) && dieNumber == 1) {
			finalAttack = attackMoves[2];
		}

		else if(currentPlayerAttackName.equals(attackMoves[2]) && dieNumber == 2) {
			finalAttack = attackMoves[2];
		}

		else if(currentPlayerAttackName.equals(attackMoves[2]) && dieNumber == 3) {
			finalAttack = attackMoves[1];
		}

		else if(currentPlayerAttackName.equals(attackMoves[2]) && dieNumber == 4) {
			finalAttack = attackMoves[1];
		}

		else if(currentPlayerAttackName.equals(attackMoves[2]) && dieNumber == 5) {
			finalAttack = attackMoves[0];
		}

		else if(currentPlayerAttackName.equals(attackMoves[2]) && dieNumber == 6) {
			finalAttack = attackMoves[0];
		}
		
		return finalAttack;
	}
	
	/* Determine when an attack and defense speed both hit */
	public boolean checkHitSpeedAndCorrectDefenseAndAttack(String finalAttackToBeUsed, String opponentsDefense, int playerAttackSpeed, int opponentDefenseSpeed) {
		
		boolean hitOpponent = false;
		
		if(finalAttackToBeUsed.equals(attackMoves[0]) && opponentsDefense.equals(defenceMoves[0]) && playerAttackSpeed < opponentDefenseSpeed) {
			hitOpponent = true;
		}
		
		else if(finalAttackToBeUsed.equals(attackMoves[1]) && opponentsDefense.equals(defenceMoves[1]) && playerAttackSpeed < opponentDefenseSpeed) {
			hitOpponent = true;
		}
		
		else if(finalAttackToBeUsed.equals(attackMoves[2]) && opponentsDefense.equals(defenceMoves[2]) && playerAttackSpeed < opponentDefenseSpeed) {
			hitOpponent = true;
		}
		
		return hitOpponent;
	}
	
	/* Set number of selected Messages received from the client */
	public void setNumberOfSelectMessagesReceivedFromClient(int numberOfSelectMessagesReceived) {
		this.NUMBER_OF_SELECT_MESSAGES_SENT = numberOfSelectMessagesReceived;
	}
	
	public void incrementNoOfSelectReceived() {
		NUMBER_OF_SELECT_MESSAGES_SENT ++;
	}
	
	/* Get method for my number of selected messages so far */
	public int getNumberOfSelectMessagesRecieved() {
		return NUMBER_OF_SELECT_MESSAGES_SENT;
	}
	
	/* Return the current number of players */
	public int getCurrentNumberOfPlayers() {
		return CURRENT_NUM_PLAYER;
	}
	
	/* Set current number of player */
	public void setNumberOfPlayers(int numPlayers) {
		this.CURRENT_NUM_PLAYER = numPlayers;
	}
	
	public void resetGameState() {
		this.state = WAITING;
	}
}
