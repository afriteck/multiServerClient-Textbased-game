package junitGameTest;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import gameRuleEngine.GameRuleEngine;
import player.Player;

public class ResolveAttackWithTwoPlayersOrThreeOrFourPlayers {
	
	GameRuleEngine ruleEngine;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.out.println("@Before(): TestGameRuleEngine\n\n");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		System.out.println("@After(): TestRulesEngine\n\n");
	}

	@Before
	public void setUp() throws Exception {
		System.out.println("Before(): Test RulesEngine\n\n");
		ruleEngine = new GameRuleEngine();
	}

	@After
	public void tearDown() throws Exception {
		System.out.println("@After(): TestGameRuleEngine\n\n");
		ruleEngine = null;
	}
	
	@Test
	public void resolveAttackWithTwoPlayersForOnlyOneHit() {
		String playerName1         = "Fred";
		String playerToAttack1     = "Joe";
		String attackMechanism1    = ruleEngine.attackMoves[0];
		int attackSpeed1           = 3;
		String defenseMechanism1   = ruleEngine.defenceMoves[0];
		int defenseSpeed1          = 1;
		
		String playerName2         = "Joe";
		String playerToAttack2     = "Fred";
		String attackMechanism2    = ruleEngine.attackMoves[0];
		int attackSpeed2           = 3;
		String defenseMechanism2   = ruleEngine.defenceMoves[0];
		int defenseSpeed2          = 1;
		
		Player player1 = new Player(playerName1, playerToAttack1, attackMechanism1, attackSpeed1, defenseMechanism1, defenseSpeed1);
		Player player2 = new Player(playerName2, playerToAttack2, attackMechanism2, attackSpeed2, defenseMechanism2, defenseSpeed2);
		
		String finalAttackPlayer1 = ruleEngine.finalAttackToBeUsed(attackMechanism1, 1);
		String finalAttackPlayer2 = ruleEngine.finalAttackToBeUsed(attackMechanism2, 2);
		
		/* Check for player one and two attack and defense speed are valid */ 
		boolean checkIfAttackIsValidPlayer1     = ruleEngine.checkIfAttackIsValid(finalAttackPlayer1);
		boolean checkIfAttackIsValidPlayer2     = ruleEngine.checkIfAttackIsValid(finalAttackPlayer2);
		
		boolean checkifDefenseIsValidPlayer2    = ruleEngine.checkIfDefenseIsValid(defenseMechanism2);
		boolean checkifDefenseIsValidPlayer1    = ruleEngine.checkIfDefenseIsValid(defenseMechanism1); 
		
		boolean checkIfAttackSpeedValidPlayer1  = ruleEngine.attackSpeedValid(attackSpeed1);
		boolean checkIfDefenseSpeedValidPlayer2 = ruleEngine.defenseSpeedValid(defenseSpeed2);
		
		boolean checkIfAttackSpeedValidPlayer2  = ruleEngine.attackSpeedValid(attackSpeed2);
		boolean checkIfDefenseSpeedValidPlayer1 = ruleEngine.defenseSpeedValid(defenseSpeed1);
		
		/* now call rule engine to check if it was a hit or not */
		boolean wasItAHit = ruleEngine.isItAHit(finalAttackPlayer1, defenseMechanism2, attackSpeed1, defenseSpeed2);
		boolean wasItAHit1 = ruleEngine.isItAHit(finalAttackPlayer2, defenseMechanism1, attackSpeed2, defenseSpeed1);
		
		/* check if inputs are valid for player2 then check if it was a hit */
		if(checkIfAttackIsValidPlayer1 && checkifDefenseIsValidPlayer2 && checkIfAttackSpeedValidPlayer1 && checkIfDefenseSpeedValidPlayer2) {
			if(wasItAHit) {
				if(defenseMechanism2.equals(player2.getDefenseMechanism())) {
					player2.incrementNumberOfWoundsSustained();
					System.out.println("Player 2 sustained " + player2.getNumberOfWoundsSustained() + " wound");
				}
			}
			
			else {
				player2.setNumberOfWoundsToZeroWhenNoHit();
				System.out.println(player2.getNumberOfWoundsSustained());
			}
		}
		
		/* check if inputs are valid for player1 then check if it was a hit */
		if(checkIfAttackIsValidPlayer2 && checkifDefenseIsValidPlayer1 && checkIfAttackSpeedValidPlayer2 && checkIfDefenseSpeedValidPlayer1) {
			if(wasItAHit1) {
				if(defenseMechanism1.equals(player1.getDefenseMechanism())) {
					player1.incrementNumberOfWoundsSustained();
					System.out.println("Player 1 sustained " + player2.getNumberOfWoundsSustained() + " wound");
				}
			}
			
			else {
				player1.setNumberOfWoundsToZeroWhenNoHit();
				System.out.println(player2.getNumberOfWoundsSustained());
			}
		}
		
		assertEquals(1, player2.getNumberOfWoundsSustained());
		assertEquals(1, player1.getNumberOfWoundsSustained());
		
	}

	@Test
	public void resolveAttackWithTwoPlayersForNoHit() {
		/* Assuming I am trying to create two player objects and instantiate their attributes */
		String playerName1         = "Fred";
		String playerToAttack1     = "Joe";
		String attackMechanism1    = ruleEngine.attackMoves[1];
		int attackSpeed1           = 2;
		String defenseMechanism1   = ruleEngine.defenceMoves[1];
		int defenseSpeed1          = 2;
		
		String playerName2         = "Joe";
		String playerToAttack2     = "Fred";
		String attackMechanism2    = ruleEngine.attackMoves[2];
		int attackSpeed2           = 2;
		String defenseMechanism2   = ruleEngine.defenceMoves[2];
		int defenseSpeed2          = 2;
		
		Player player1 = new Player(playerName1, playerToAttack1, attackMechanism1, attackSpeed1, defenseMechanism1, defenseSpeed1);
		Player player2 = new Player(playerName2, playerToAttack2, attackMechanism2, attackSpeed2, defenseMechanism2, defenseSpeed2);
		
		String finalAttackPlayer1 = ruleEngine.finalAttackToBeUsed(attackMechanism1, 3);
		String finalAttackPlayer2 = ruleEngine.finalAttackToBeUsed(attackMechanism2, 2);
		
		/* Check for player one and two attack and defense speed are valid */ 
		boolean checkIfAttackIsValidPlayer1     = ruleEngine.checkIfAttackIsValid(finalAttackPlayer1);
		boolean checkIfAttackIsValidPlayer2     = ruleEngine.checkIfAttackIsValid(finalAttackPlayer2);
		
		boolean checkifDefenseIsValidPlayer2    = ruleEngine.checkIfDefenseIsValid(defenseMechanism2);
		boolean checkifDefenseIsValidPlayer1    = ruleEngine.checkIfDefenseIsValid(defenseMechanism1); 
		
		boolean checkIfAttackSpeedValidPlayer1  = ruleEngine.attackSpeedValid(attackSpeed1);
		boolean checkIfDefenseSpeedValidPlayer2 = ruleEngine.defenseSpeedValid(defenseSpeed2);
		
		boolean checkIfAttackSpeedValidPlayer2  = ruleEngine.attackSpeedValid(attackSpeed2);
		boolean checkIfDefenseSpeedValidPlayer1 = ruleEngine.defenseSpeedValid(defenseSpeed1);
		
		/* now call rule engine to check if it was a hit or not */
		boolean wasItAHit = ruleEngine.isItAHit(finalAttackPlayer1, defenseMechanism2, attackSpeed1, defenseSpeed2);
		boolean wasItAHit1 = ruleEngine.isItAHit(finalAttackPlayer2, defenseMechanism1, attackSpeed2, defenseSpeed1);
		
		/* check if inputs are valid for player2 then check if it was a hit */
		if(checkIfAttackIsValidPlayer1 && checkifDefenseIsValidPlayer2 && checkIfAttackSpeedValidPlayer1 && checkIfDefenseSpeedValidPlayer2) {
			if(wasItAHit) {
				if(defenseMechanism2.equals(player2.getDefenseMechanism())) {
					player2.incrementNumberOfWoundsSustained();
					System.out.println("Player 2 sustained " + player2.getNumberOfWoundsSustained() + " wound");
				}
			}
			
			else {
				player2.setNumberOfWoundsToZeroWhenNoHit();
				System.out.println("Player 2 sustained " + player2.getNumberOfWoundsSustained() + " wound");
			}
		}
		
		/* check if inputs are valid for player1 then check if it was a hit */
		if(checkIfAttackIsValidPlayer2 && checkifDefenseIsValidPlayer1 && checkIfAttackSpeedValidPlayer2 && checkIfDefenseSpeedValidPlayer1) {
			if(wasItAHit1) {
				if(defenseMechanism1.equals(player1.getDefenseMechanism())) {
					player1.incrementNumberOfWoundsSustained();
					System.out.println("Player 1 sustained " + player2.getNumberOfWoundsSustained() + " wound");
				}
			}
			
			else {
				player1.setNumberOfWoundsToZeroWhenNoHit();
				System.out.println("Player 2 sustained " + player2.getNumberOfWoundsSustained() + " wound");
			}
		}
		
		assertEquals(0, player2.getNumberOfWoundsSustained());
		assertEquals(0, player1.getNumberOfWoundsSustained());
		
	}
	
	@Test
	public void resolveAttackWithTwoPlayersFor2Hits() {
		/* Assuming I am trying to create two player objects and instantiate their attributes */
		String playerName1         = "Fred";
		String playerToAttack1     = "Joe";
		String attackMechanism1    = ruleEngine.attackMoves[0];
		int attackSpeed1           = 1;
		String defenseMechanism1   = ruleEngine.defenceMoves[1];
		int defenseSpeed1          = 3;
		
		String playerName2         = "Joe";
		String playerToAttack2     = "Fred";
		String attackMechanism2    = ruleEngine.attackMoves[2];
		int attackSpeed2           = 1;
		String defenseMechanism2   = ruleEngine.defenceMoves[0];
		int defenseSpeed2          = 3;
		
		Player player1 = new Player(playerName1, playerToAttack1, attackMechanism1, attackSpeed1, defenseMechanism1, defenseSpeed1);
		Player player2 = new Player(playerName2, playerToAttack2, attackMechanism2, attackSpeed2, defenseMechanism2, defenseSpeed2);
		
		String finalAttackPlayer1 = ruleEngine.finalAttackToBeUsed(attackMechanism1, 1);
		String finalAttackPlayer2 = ruleEngine.finalAttackToBeUsed(attackMechanism2, 4);
		
		boolean wasItAHit = ruleEngine.isItAHit(finalAttackPlayer1, defenseMechanism2, attackSpeed1, defenseSpeed2);
		boolean wasItAHit1 = ruleEngine.checkHitSpeedAndCorrectDefenseAndAttack(finalAttackPlayer1, defenseMechanism2, attackSpeed1, defenseSpeed2);
		
		boolean wasItAHit2 = ruleEngine.isItAHit(finalAttackPlayer2, defenseMechanism1, attackSpeed2, defenseSpeed1);
		boolean wasItAHit3 = ruleEngine.checkHitSpeedAndCorrectDefenseAndAttack(finalAttackPlayer2, defenseMechanism1, attackSpeed2, defenseSpeed1);
		
		if(wasItAHit) {
			if(defenseMechanism2.equals(player2.getDefenseMechanism())) {
				player2.incrementNumberOfWoundsSustained();
			}
		}
		
		if(wasItAHit1) {
			if(defenseMechanism2.equals(player2.getDefenseMechanism())) {
				player2.incrementNumberOfWoundsSustained();
				System.out.println("player 2 got " + player2.getNumberOfWoundsSustained() + " wound");
			}
		}
		
		if(wasItAHit2) {
			if(defenseMechanism1.equals(player1.getDefenseMechanism())) {
				player1.incrementNumberOfWoundsSustained();
			}
		}
		
		if(wasItAHit3) {
			if(defenseMechanism1.equals(player1.getDefenseMechanism())) {
				player1.incrementNumberOfWoundsSustained();
				System.out.println("player 2 got " + player2.getNumberOfWoundsSustained() + " wound");
			}
		}
		
		assertEquals(2, player2.getNumberOfWoundsSustained());
		assertEquals(2, player1.getNumberOfWoundsSustained());
	}
	
	
	@Test
	public void resolveAttackWithThreePlayersForNoHit() {
		/* Assuming I am trying to create three player objects and instantiate their attributes */
		String playerName1         = "joe";
		String playerToAttack1     = "jack";
		String attackMechanism1    = ruleEngine.attackMoves[2];
		int attackSpeed1           = 3;
		String defenseMechanism1   = ruleEngine.defenceMoves[2];
		int defenseSpeed1          = 1;
		
		String playerName2         = "jack";
		String playerToAttack2     = "fred";
		String attackMechanism2    = ruleEngine.attackMoves[0];
		int attackSpeed2           = 3;
		String defenseMechanism2   = ruleEngine.defenceMoves[0];
		int defenseSpeed2          = 1;
		
		String playerName3         = "fred";
		String playerToAttack3     = "joe";
		String attackMechanism3    = ruleEngine.attackMoves[1];
		int attackSpeed3           = 3;
		String defenseMechanism3   = ruleEngine.defenceMoves[1];
		int defenseSpeed3          = 1;
		
		Player player1 = new Player(playerName1, playerToAttack1, attackMechanism1, attackSpeed1, defenseMechanism1, defenseSpeed1);
		Player player2 = new Player(playerName2, playerToAttack2, attackMechanism2, attackSpeed2, defenseMechanism2, defenseSpeed2);
		Player player3 = new Player(playerName3, playerToAttack3, attackMechanism3, attackSpeed3, defenseMechanism3, defenseSpeed3);
		
		String finalAttackPlayer1 = ruleEngine.finalAttackToBeUsed(attackMechanism1, 1);
		String finalAttackPlayer2 = ruleEngine.finalAttackToBeUsed(attackMechanism2, 1);
		String finalAttackPlayer3 = ruleEngine.finalAttackToBeUsed(attackMechanism3, 1);
		
		/* Check for player one and two attack and defense speed are valid */ 
		boolean checkIfAttackIsValidPlayer1         = ruleEngine.checkIfAttackIsValid(finalAttackPlayer1);
		boolean checkIfDefenseIsValidPlayer2one     = ruleEngine.checkIfDefenseIsValid(defenseMechanism2);
		
		boolean checkIfAttackIsValidPlayer2Two      = ruleEngine.checkIfAttackIsValid(finalAttackPlayer2);
		boolean checkIfDefenseIsValidPlayer3        = ruleEngine.checkIfDefenseIsValid(defenseMechanism3);
		
		boolean checkIfAttackIsValidPlayer3     	= ruleEngine.checkIfAttackIsValid(finalAttackPlayer3);
		boolean checkIfDefenseIsValidPlayer1       = ruleEngine.checkIfDefenseIsValid(defenseMechanism1);
		
		boolean wasItAHit = ruleEngine.isItAHit(finalAttackPlayer1, defenseMechanism2, attackSpeed1, defenseSpeed2);
		boolean wasItAHit1 = ruleEngine.isItAHit(finalAttackPlayer2, defenseMechanism3, attackSpeed2, defenseSpeed3);
		boolean wasItAHit2 = ruleEngine.isItAHit(finalAttackPlayer3, defenseMechanism1, attackSpeed3, defenseSpeed1);
		
		if(checkIfAttackIsValidPlayer1 && checkIfDefenseIsValidPlayer2one) {
			if(wasItAHit){
				if(defenseMechanism2.equals(player2.getDefenseMechanism())) {
					player2.incrementNumberOfWoundsSustained();
				}
			}
		}
		
		if(checkIfAttackIsValidPlayer2Two && checkIfDefenseIsValidPlayer3) {
			if(wasItAHit1){
				if(defenseMechanism3.equals(player3.getDefenseMechanism())) {
					player3.incrementNumberOfWoundsSustained();
				}
			}
		}
		
		if(checkIfAttackIsValidPlayer3 && checkIfDefenseIsValidPlayer1) {
			if(wasItAHit2){
				if(defenseMechanism1.equals(player1.getDefenseMechanism())) {
					player1.incrementNumberOfWoundsSustained();
				}
			}
		}
		
		System.out.println("Player2 got " + player2.getNumberOfWoundsSustained() + " wound");
		System.out.println("Player3 got " + player3.getNumberOfWoundsSustained() + " wound");
		System.out.println("Player1 got " + player1.getNumberOfWoundsSustained() + " wound");
		
		assertEquals(0, player2.getNumberOfWoundsSustained());
		assertEquals(0, player3.getNumberOfWoundsSustained());
		assertEquals(0, player1.getNumberOfWoundsSustained());
	}
	
	@Test
	public void resolveAttackWithThreePlayersForEachGettingOneHit() {
		/* Assuming I am trying to create three player objects and instantiate their attributes */
		String playerName1         = "joe";
		String playerToAttack1     = "jack";
		String attackMechanism1    = ruleEngine.attackMoves[2];
		int attackSpeed1           = 2;
		String defenseMechanism1   = ruleEngine.defenceMoves[0];
		int defenseSpeed1          = 2;
		
		String playerName2         = "jack";
		String playerToAttack2     = "fred";
		String attackMechanism2    = ruleEngine.attackMoves[1];
		int attackSpeed2           = 1;
		String defenseMechanism2   = ruleEngine.defenceMoves[2];
		int defenseSpeed2          = 3;
		
		String playerName3         = "fred";
		String playerToAttack3     = "joe";
		String attackMechanism3    = ruleEngine.attackMoves[0];
		int attackSpeed3           = 2;
		String defenseMechanism3   = ruleEngine.defenceMoves[1];
		int defenseSpeed3          = 2;
		
		Player player1 = new Player(playerName1, playerToAttack1, attackMechanism1, attackSpeed1, defenseMechanism1, defenseSpeed1);
		Player player2 = new Player(playerName2, playerToAttack2, attackMechanism2, attackSpeed2, defenseMechanism2, defenseSpeed2);
		Player player3 = new Player(playerName3, playerToAttack3, attackMechanism3, attackSpeed3, defenseMechanism3, defenseSpeed3);
		
		String finalAttackPlayer1 = ruleEngine.finalAttackToBeUsed(attackMechanism1, 4);
		String finalAttackPlayer2 = ruleEngine.finalAttackToBeUsed(attackMechanism2, 1);
		String finalAttackPlayer3 = ruleEngine.finalAttackToBeUsed(attackMechanism3, 1);
		
		/* Check for player one and two attack and defense speed are valid */ 
		boolean checkIfAttackIsValidPlayer2         = ruleEngine.checkIfAttackIsValid(finalAttackPlayer2);
		boolean checkIfDefenseIsValidPlayerone     = ruleEngine.checkIfDefenseIsValid(defenseMechanism1);
		
		boolean checkIfAttackIsValidPlayer3      = ruleEngine.checkIfAttackIsValid(finalAttackPlayer3);
		boolean checkIfDefenseIsValidPlayer1        = ruleEngine.checkIfDefenseIsValid(defenseMechanism1);
		
		boolean checkIfAttackIsValidPlayerone     	= ruleEngine.checkIfAttackIsValid(finalAttackPlayer1);
		boolean checkIfDefenseIsValidPlayerthree       = ruleEngine.checkIfDefenseIsValid(defenseMechanism3);
		
		boolean wasItAHit = ruleEngine.isItAHit(finalAttackPlayer2, defenseMechanism1, attackSpeed2, defenseSpeed1);
		boolean wasItAHit1 = ruleEngine.isItAHit(finalAttackPlayer3, defenseMechanism1, attackSpeed3, defenseSpeed1);
		boolean wasItAHit2 = ruleEngine.isItAHit(finalAttackPlayer1, defenseMechanism3, attackSpeed1, defenseSpeed3);
		
		if(checkIfAttackIsValidPlayer2 && checkIfDefenseIsValidPlayerone) {
			if(wasItAHit){
				if(defenseMechanism1.equals(player1.getDefenseMechanism())) {
					player2.incrementNumberOfWoundsSustained();
				}
			}
		}
		
		if(checkIfAttackIsValidPlayer3 && checkIfDefenseIsValidPlayer1) {
			if(wasItAHit1){
				if(defenseMechanism1.equals(player1.getDefenseMechanism())) {
					player1.incrementNumberOfWoundsSustained();
				}
			}
		}
		
		if(checkIfAttackIsValidPlayerone && checkIfDefenseIsValidPlayerthree) {
			if(wasItAHit2){
				if(defenseMechanism3.equals(player3.getDefenseMechanism())) {
					player3.incrementNumberOfWoundsSustained();
				}
			}
		}
		
		System.out.println("Player2 got " + player2.getNumberOfWoundsSustained() + " wound");
		System.out.println("Player3 got " + player3.getNumberOfWoundsSustained() + " wound");
		System.out.println("Player1 got " + player1.getNumberOfWoundsSustained() + " wound");
		
		assertEquals(1, player2.getNumberOfWoundsSustained());
		assertEquals(1, player3.getNumberOfWoundsSustained());
		assertEquals(1, player1.getNumberOfWoundsSustained());
	}
	
	@Test
	public void resolveAttackWithThreePlayersForSomeHits() {
		/* Assuming I am trying to create three player objects and instantiate their attributes */
		String playerName1         = "joe";
		String playerToAttack1     = "jack";
		String attackMechanism1    = ruleEngine.attackMoves[1];
		int attackSpeed1           = 2;
		String defenseMechanism1   = ruleEngine.defenceMoves[2];
		int defenseSpeed1          = 2;
		
		String playerName2         = "jack";
		String playerToAttack2     = "fred";
		String attackMechanism2    = ruleEngine.attackMoves[2];
		int attackSpeed2           = 3;
		String defenseMechanism2   = ruleEngine.defenceMoves[0];
		int defenseSpeed2          = 1;
		
		String playerName3         = "fred";
		String playerToAttack3     = "joe";
		String attackMechanism3    = ruleEngine.attackMoves[0];
		int attackSpeed3           = 1;
		String defenseMechanism3   = ruleEngine.defenceMoves[1];
		int defenseSpeed3          = 3;
		
		Player player1 = new Player(playerName1, playerToAttack1, attackMechanism1, attackSpeed1, defenseMechanism1, defenseSpeed1);
		Player player2 = new Player(playerName2, playerToAttack2, attackMechanism2, attackSpeed2, defenseMechanism2, defenseSpeed2);
		Player player3 = new Player(playerName3, playerToAttack3, attackMechanism3, attackSpeed3, defenseMechanism3, defenseSpeed3);
		
		String finalAttackPlayer1 = ruleEngine.finalAttackToBeUsed(attackMechanism1, 4);
		String finalAttackPlayer2 = ruleEngine.finalAttackToBeUsed(attackMechanism2, 3);
		String finalAttackPlayer3 = ruleEngine.finalAttackToBeUsed(attackMechanism3, 6);
		
		/* Check for player one and two attack and defense speed are valid */ 
		boolean checkIfAttackIsValidPlayer2         = ruleEngine.checkIfAttackIsValid(finalAttackPlayer2);
		boolean checkIfDefenseIsValidPlayerone     = ruleEngine.checkIfDefenseIsValid(defenseMechanism1);
		
		boolean checkIfAttackIsValidPlayer3      = ruleEngine.checkIfAttackIsValid(finalAttackPlayer3);
		boolean checkIfDefenseIsValidPlayer1        = ruleEngine.checkIfDefenseIsValid(defenseMechanism1);
		
		boolean checkIfAttackIsValidPlayerone     	= ruleEngine.checkIfAttackIsValid(finalAttackPlayer1);
		boolean checkIfDefenseIsValidPlayerthree       = ruleEngine.checkIfDefenseIsValid(defenseMechanism3);
		
		boolean wasItAHit = ruleEngine.isItAHit(finalAttackPlayer2, defenseMechanism1, attackSpeed2, defenseSpeed1);
		boolean wasItAHit1 = ruleEngine.isItAHit(finalAttackPlayer3, defenseMechanism1, attackSpeed3, defenseSpeed1);
		boolean wasItAHit2 = ruleEngine.isItAHit(finalAttackPlayer1, defenseMechanism3, attackSpeed1, defenseSpeed3);
		
		if(checkIfAttackIsValidPlayer2 && checkIfDefenseIsValidPlayerone) {
			if(wasItAHit){
				if(defenseMechanism1.equals(player1.getDefenseMechanism())) {
					player2.incrementNumberOfWoundsSustained();
				}
			}
		}
		
		if(checkIfAttackIsValidPlayer3 && checkIfDefenseIsValidPlayer1) {
			if(wasItAHit1){
				if(defenseMechanism1.equals(player1.getDefenseMechanism())) {
					player1.incrementNumberOfWoundsSustained();
				}
			}
		}
		
		if(checkIfAttackIsValidPlayerone && checkIfDefenseIsValidPlayerthree) {
			if(wasItAHit2){
				if(defenseMechanism3.equals(player3.getDefenseMechanism())) {
					player3.incrementNumberOfWoundsSustained();
				}
			}
		}
		
		System.out.println("Player2 got " + player2.getNumberOfWoundsSustained() + " wound");
		System.out.println("Player3 got " + player3.getNumberOfWoundsSustained() + " wound");
		System.out.println("Player1 got " + player1.getNumberOfWoundsSustained() + " wound");
		
		assertEquals(0, player2.getNumberOfWoundsSustained());
		assertEquals(1, player3.getNumberOfWoundsSustained());
		assertEquals(1, player1.getNumberOfWoundsSustained());
	}
	
	@Test
	public void resolveAttackWithThreePlayersForSTwoPlayersWithOneTargetNoHit() {
		/* Assuming I am trying to create three player objects and instantiate their attributes */
		String playerName1         = "joe";
		String playerToAttack1     = "jack";
		String attackMechanism1    = ruleEngine.attackMoves[0];
		int attackSpeed1           = 2;
		String defenseMechanism1   = ruleEngine.defenceMoves[2];
		int defenseSpeed1          = 2;
		
		String playerName2         = "jack";
		String playerToAttack2     = "fred";
		String attackMechanism2    = ruleEngine.attackMoves[1];
		int attackSpeed2           = 3;
		String defenseMechanism2   = ruleEngine.defenceMoves[0];
		int defenseSpeed2          = 1;
		
		String playerName3         = "fred";
		String playerToAttack3     = "joe";
		String attackMechanism3    = ruleEngine.attackMoves[2];
		int attackSpeed3           = 2;
		String defenseMechanism3   = ruleEngine.defenceMoves[1];
		int defenseSpeed3          = 2;
		
		Player player1 = new Player(playerName1, playerToAttack1, attackMechanism1, attackSpeed1, defenseMechanism1, defenseSpeed1);
		Player player2 = new Player(playerName2, playerToAttack2, attackMechanism2, attackSpeed2, defenseMechanism2, defenseSpeed2);
		Player player3 = new Player(playerName3, playerToAttack3, attackMechanism3, attackSpeed3, defenseMechanism3, defenseSpeed3);
		
		String finalAttackPlayer2 = ruleEngine.finalAttackToBeUsed(attackMechanism2, 1);
		String finalAttackPlayer3 = ruleEngine.finalAttackToBeUsed(attackMechanism3, 3);
		
		/* Check for player one and two attack and defense speed are valid */ 
		boolean checkIfAttackIsValidPlayer2         = ruleEngine.checkIfAttackIsValid(finalAttackPlayer2);
		boolean checkIfDefenseIsValidPlayerone     = ruleEngine.checkIfDefenseIsValid(defenseMechanism1);
		
		boolean checkIfAttackIsValidPlayer3      = ruleEngine.checkIfAttackIsValid(finalAttackPlayer3);
		boolean checkIfDefenseIsValidPlayer1        = ruleEngine.checkIfDefenseIsValid(defenseMechanism1);
		
		/* Target here is player one, player 3 and player 2 are trying to attack player 1 */
		boolean wasItAHit = ruleEngine.isItAHit(finalAttackPlayer2, defenseMechanism1, attackSpeed2, defenseSpeed1);
		boolean wasItAHit1 = ruleEngine.isItAHit(finalAttackPlayer3, defenseMechanism1, attackSpeed3, defenseSpeed1);
		
		if(checkIfAttackIsValidPlayer2 && checkIfDefenseIsValidPlayerone) {
			if(wasItAHit){
				if(defenseMechanism1.equals(player1.getDefenseMechanism())) {
					player1.incrementNumberOfWoundsSustained();
				}
			}
		}
		
		if(checkIfAttackIsValidPlayer3 && checkIfDefenseIsValidPlayer1) {
			if(wasItAHit1){
				if(defenseMechanism1.equals(player1.getDefenseMechanism())) {
					player1.incrementNumberOfWoundsSustained();
				}
			}
		}
		System.out.println("Player1 got " + player1.getNumberOfWoundsSustained() + " wound");
		
		assertEquals(0, player1.getNumberOfWoundsSustained());
	}
	
	@Test
	public void resolveAttackWithThreePlayersForSTwoPlayersWithOneTargetOneHit() {
		/* Assuming I am trying to create three player objects and instantiate their attributes */
		String playerName1         = "joe";
		String playerToAttack1     = "jack";
		String attackMechanism1    = ruleEngine.attackMoves[0];
		int attackSpeed1           = 2;
		String defenseMechanism1   = ruleEngine.defenceMoves[2];
		int defenseSpeed1          = 2;
		
		String playerName2         = "jack";
		String playerToAttack2     = "fred";
		String attackMechanism2    = ruleEngine.attackMoves[1];
		int attackSpeed2           = 3;
		String defenseMechanism2   = ruleEngine.defenceMoves[0];
		int defenseSpeed2          = 1;
		
		String playerName3         = "fred";
		String playerToAttack3     = "joe";
		String attackMechanism3    = ruleEngine.attackMoves[2];
		int attackSpeed3           = 2;
		String defenseMechanism3   = ruleEngine.defenceMoves[1];
		int defenseSpeed3          = 2;
		
		Player player1 = new Player(playerName1, playerToAttack1, attackMechanism1, attackSpeed1, defenseMechanism1, defenseSpeed1);
		Player player2 = new Player(playerName2, playerToAttack2, attackMechanism2, attackSpeed2, defenseMechanism2, defenseSpeed2);
		Player player3 = new Player(playerName3, playerToAttack3, attackMechanism3, attackSpeed3, defenseMechanism3, defenseSpeed3);
		
		String finalAttackPlayer2 = ruleEngine.finalAttackToBeUsed(attackMechanism2, 1);
		String finalAttackPlayer3 = ruleEngine.finalAttackToBeUsed(attackMechanism3, 1);
		
		/* Check for player one and two attack and defense speed are valid */ 
		boolean checkIfAttackIsValidPlayer2         = ruleEngine.checkIfAttackIsValid(finalAttackPlayer2);
		boolean checkIfDefenseIsValidPlayerone     	= ruleEngine.checkIfDefenseIsValid(defenseMechanism1);
		
		boolean checkIfAttackIsValidPlayer3      	= ruleEngine.checkIfAttackIsValid(finalAttackPlayer3);
		boolean checkIfDefenseIsValidPlayer1        = ruleEngine.checkIfDefenseIsValid(defenseMechanism1);
		
		/* Target here is player one, player 3 and player 2 are trying to attack player 1 */
		boolean wasItAHit = ruleEngine.isItAHit(finalAttackPlayer2, defenseMechanism1, attackSpeed2, defenseSpeed1);
		boolean wasItAHit1 = ruleEngine.isItAHit(finalAttackPlayer3, defenseMechanism1, attackSpeed3, defenseSpeed1);
		
		if(checkIfAttackIsValidPlayer2 && checkIfDefenseIsValidPlayerone) {
			if(wasItAHit){
				if(defenseMechanism1.equals(player1.getDefenseMechanism())) {
					player1.incrementNumberOfWoundsSustained();
				}
			}
		}
		
		if(checkIfAttackIsValidPlayer3 && checkIfDefenseIsValidPlayer1) {
			if(wasItAHit1){
				if(defenseMechanism1.equals(player1.getDefenseMechanism())) {
					player1.incrementNumberOfWoundsSustained();
				}
			}
		}
		System.out.println("Player1 got " + player1.getNumberOfWoundsSustained() + " wound");
		assertEquals(1, player1.getNumberOfWoundsSustained());
	}
	
	@Test
	public void resolveAttackWithThreePlayersForSTwoPlayersWithOneTargetTwoHit() {
		/* Assuming I am trying to create three player objects and instantiate their attributes */
		String playerName1         = "joe";
		String playerToAttack1     = "jack";
		String attackMechanism1    = ruleEngine.attackMoves[1];
		int attackSpeed1           = 1;
		String defenseMechanism1   = ruleEngine.defenceMoves[0];
		int defenseSpeed1          = 3;
		
		String playerName2         = "jack";
		String playerToAttack2     = "fred";
		String attackMechanism2    = ruleEngine.attackMoves[0];
		int attackSpeed2           = 3;
		String defenseMechanism2   = ruleEngine.defenceMoves[2];
		int defenseSpeed2          = 1;
		
		String playerName3         = "fred";
		String playerToAttack3     = "joe";
		String attackMechanism3    = ruleEngine.attackMoves[2];
		int attackSpeed3           = 1;
		String defenseMechanism3   = ruleEngine.defenceMoves[1];
		int defenseSpeed3          = 2;
		
		Player player1 = new Player(playerName1, playerToAttack1, attackMechanism1, attackSpeed1, defenseMechanism1, defenseSpeed1);
		Player player2 = new Player(playerName2, playerToAttack2, attackMechanism2, attackSpeed2, defenseMechanism2, defenseSpeed2);
		Player player3 = new Player(playerName3, playerToAttack3, attackMechanism3, attackSpeed3, defenseMechanism3, defenseSpeed3);
		
		String finalAttackPlayer2 = ruleEngine.finalAttackToBeUsed(attackMechanism2, 1);
		String finalAttackPlayer3 = ruleEngine.finalAttackToBeUsed(attackMechanism3, 1);
		
		/* Check for player one and two attack and defense speed are valid */ 
		boolean checkIfAttackIsValidPlayer2         = ruleEngine.checkIfAttackIsValid(finalAttackPlayer2);
		boolean checkIfDefenseIsValidPlayerone     	= ruleEngine.checkIfDefenseIsValid(defenseMechanism1);
		
		boolean checkIfAttackIsValidPlayer3      	= ruleEngine.checkIfAttackIsValid(finalAttackPlayer3);
		boolean checkIfDefenseIsValidPlayer1        = ruleEngine.checkIfDefenseIsValid(defenseMechanism1);
		
		/* Target here is player one, player 3 and player 2 are trying to attack player 1 */
		boolean wasItAHit = ruleEngine.isItAHit(finalAttackPlayer2, defenseMechanism1, attackSpeed2, defenseSpeed1);
		boolean wasItAHit1 = ruleEngine.isItAHit(finalAttackPlayer3, defenseMechanism1, attackSpeed3, defenseSpeed1);
		
		if(checkIfAttackIsValidPlayer2 && checkIfDefenseIsValidPlayerone) {
			if(wasItAHit){
				if(defenseMechanism1.equals(player1.getDefenseMechanism())) {
					player1.incrementNumberOfWoundsSustained();
				}
			}
		}
		
		if(checkIfAttackIsValidPlayer3 && checkIfDefenseIsValidPlayer1) {
			if(wasItAHit1){
				if(defenseMechanism1.equals(player1.getDefenseMechanism())) {
					player1.incrementNumberOfWoundsSustained();
				}
			}
		}
		System.out.println("Player1 got " + player1.getNumberOfWoundsSustained() + " wound");
		assertEquals(2, player1.getNumberOfWoundsSustained());
	}
	
	
	@Test
	public void resolveAttackWithFourPlayersWithNoHit() {
		/* Assuming I am trying to create three player objects and instantiate their attributes */
		String playerName1         = "joe";
		String playerToAttack1     = "jack";
		String attackMechanism1    = ruleEngine.attackMoves[0];
		int attackSpeed1           = 1;
		String defenseMechanism1   = ruleEngine.defenceMoves[0];
		int defenseSpeed1          = 1;
		
		String playerName2         = "jack";
		String playerToAttack2     = "fred";
		String attackMechanism2    = ruleEngine.attackMoves[0];
		int attackSpeed2           = 3;
		String defenseMechanism2   = ruleEngine.defenceMoves[2];
		int defenseSpeed2          = 1;
		
		String playerName3         = "fred";
		String playerToAttack3     = "john";
		String attackMechanism3    = ruleEngine.attackMoves[2];
		int attackSpeed3           = 2;
		String defenseMechanism3   = ruleEngine.defenceMoves[1];
		int defenseSpeed3          = 2;
		
		String playerName4         = "john";
		String playerToAttack4     = "joe";
		String attackMechanism4    = ruleEngine.attackMoves[2];
		int attackSpeed4           = 3;
		String defenseMechanism4   = ruleEngine.defenceMoves[1];
		int defenseSpeed4          = 2;
		
		Player player1 = new Player(playerName1, playerToAttack1, attackMechanism1, attackSpeed1, defenseMechanism1, defenseSpeed1);
		Player player2 = new Player(playerName2, playerToAttack2, attackMechanism2, attackSpeed2, defenseMechanism2, defenseSpeed2);
		Player player3 = new Player(playerName3, playerToAttack3, attackMechanism3, attackSpeed3, defenseMechanism3, defenseSpeed3);
		Player player4 = new Player(playerName4, playerToAttack4, attackMechanism4, attackSpeed4, defenseMechanism4, defenseSpeed4);
		
		String finalAttackPlayer1 = ruleEngine.finalAttackToBeUsed(attackMechanism1, 2);
		String finalAttackPlayer2 = ruleEngine.finalAttackToBeUsed(attackMechanism2, 1);
		String finalAttackPlayer3 = ruleEngine.finalAttackToBeUsed(attackMechanism3, 2);
		String finalAttackPlayer4 = ruleEngine.finalAttackToBeUsed(attackMechanism4, 1);
		
		/* Check for player one and two attack and defense speed are valid */ 
		boolean checkIfAttackIsValidPlayer1         = ruleEngine.checkIfAttackIsValid(finalAttackPlayer1);
		boolean checkIfDefenseIsValidPlayertwo     	= ruleEngine.checkIfDefenseIsValid(defenseMechanism2);
		
		boolean checkIfAttackIsValidPlayer2      	= ruleEngine.checkIfAttackIsValid(finalAttackPlayer2);
		boolean checkIfDefenseIsValidPlayer3        = ruleEngine.checkIfDefenseIsValid(defenseMechanism3);
		
		boolean checkIfAttackIsValidPlayer3      	= ruleEngine.checkIfAttackIsValid(finalAttackPlayer3);
		boolean checkIfDefenseIsValidPlayer4        = ruleEngine.checkIfDefenseIsValid(defenseMechanism4);
		
		boolean checkIfAttackIsValidPlayer4      	= ruleEngine.checkIfAttackIsValid(finalAttackPlayer4);
		boolean checkIfDefenseIsValidPlayer1        = ruleEngine.checkIfDefenseIsValid(defenseMechanism1);
		
		/* Target here is player one, player 3 and player 2 are trying to attack player 1 */
		boolean wasItAHit = ruleEngine.isItAHit(finalAttackPlayer1, defenseMechanism2, attackSpeed1, defenseSpeed2);
		boolean wasItAHit1 = ruleEngine.isItAHit(finalAttackPlayer2, defenseMechanism3, attackSpeed2, defenseSpeed3);
		boolean wasItAHit2 = ruleEngine.isItAHit(finalAttackPlayer3, defenseMechanism4, attackSpeed3, defenseSpeed4);
		boolean wasItAHit3 = ruleEngine.isItAHit(finalAttackPlayer4, defenseMechanism1, attackSpeed4, defenseSpeed1);
		
		if(checkIfAttackIsValidPlayer1 && checkIfDefenseIsValidPlayertwo) {
			if(wasItAHit){
				if(defenseMechanism2.equals(player2.getDefenseMechanism())) {
					player2.incrementNumberOfWoundsSustained();
				}
			}
		}
		
		if(checkIfAttackIsValidPlayer2 && checkIfDefenseIsValidPlayer3) {
			if(wasItAHit1){
				if(defenseMechanism3.equals(player3.getDefenseMechanism())) {
					player3.incrementNumberOfWoundsSustained();
				}
			}
		}
		
		if(checkIfAttackIsValidPlayer3 && checkIfDefenseIsValidPlayer4) {
			if(wasItAHit2){
				if(defenseMechanism4.equals(player4.getDefenseMechanism())) {
					player4.incrementNumberOfWoundsSustained();
				}
			}
		}
		
		if(checkIfAttackIsValidPlayer4 && checkIfDefenseIsValidPlayer1) {
			if(wasItAHit3){
				if(defenseMechanism1.equals(player1.getDefenseMechanism())) {
					player1.incrementNumberOfWoundsSustained();
				}
			}
		}
		
		System.out.println("Player1 got " + player1.getNumberOfWoundsSustained() + " wound");
		System.out.println("Player2 got " + player2.getNumberOfWoundsSustained() + " wound");
		System.out.println("Player3 got " + player3.getNumberOfWoundsSustained() + " wound");
		System.out.println("Player4 got " + player4.getNumberOfWoundsSustained() + " wound");
		
		assertEquals(0, player1.getNumberOfWoundsSustained());
		assertEquals(0, player2.getNumberOfWoundsSustained());
		assertEquals(0, player3.getNumberOfWoundsSustained());
		assertEquals(0, player4.getNumberOfWoundsSustained());
	}
	
	@Test
	public void resolveAttackWithFourPlayersWithAllHit() {
		/* Assuming I am trying to create three player objects and instantiate their attributes */
		String playerName1         = "joe";
		String playerToAttack1     = "jack";
		String attackMechanism1    = ruleEngine.attackMoves[2];
		int attackSpeed1           = 1;
		String defenseMechanism1   = ruleEngine.defenceMoves[0];
		int defenseSpeed1          = 3;
		
		String playerName2         = "jack";
		String playerToAttack2     = "fred";
		String attackMechanism2    = ruleEngine.attackMoves[0];
		int attackSpeed2           = 3;
		String defenseMechanism2   = ruleEngine.defenceMoves[2];
		int defenseSpeed2          = 1;
		
		String playerName3         = "fred";
		String playerToAttack3     = "john";
		String attackMechanism3    = ruleEngine.attackMoves[1];
		int attackSpeed3           = 2;
		String defenseMechanism3   = ruleEngine.defenceMoves[0];
		int defenseSpeed3          = 2;
		
		String playerName4         = "john";
		String playerToAttack4     = "joe";
		String attackMechanism4    = ruleEngine.attackMoves[2];
		int attackSpeed4           = 1;
		String defenseMechanism4   = ruleEngine.defenceMoves[1];
		int defenseSpeed4          = 3;
		
		Player player1 = new Player(playerName1, playerToAttack1, attackMechanism1, attackSpeed1, defenseMechanism1, defenseSpeed1);
		Player player2 = new Player(playerName2, playerToAttack2, attackMechanism2, attackSpeed2, defenseMechanism2, defenseSpeed2);
		Player player3 = new Player(playerName3, playerToAttack3, attackMechanism3, attackSpeed3, defenseMechanism3, defenseSpeed3);
		Player player4 = new Player(playerName4, playerToAttack4, attackMechanism4, attackSpeed4, defenseMechanism4, defenseSpeed4);
		
		String finalAttackPlayer1 = ruleEngine.finalAttackToBeUsed(attackMechanism1, 2);
		String finalAttackPlayer2 = ruleEngine.finalAttackToBeUsed(attackMechanism2, 1);
		String finalAttackPlayer3 = ruleEngine.finalAttackToBeUsed(attackMechanism3, 2);
		String finalAttackPlayer4 = ruleEngine.finalAttackToBeUsed(attackMechanism4, 1);
		
		/* Check for player one and two attack and defense speed are valid */ 
		boolean checkIfAttackIsValidPlayer1         = ruleEngine.checkIfAttackIsValid(finalAttackPlayer1);
		boolean checkIfDefenseIsValidPlayertwo     	= ruleEngine.checkIfDefenseIsValid(defenseMechanism2);
		
		boolean checkIfAttackIsValidPlayer2      	= ruleEngine.checkIfAttackIsValid(finalAttackPlayer2);
		boolean checkIfDefenseIsValidPlayer3        = ruleEngine.checkIfDefenseIsValid(defenseMechanism3);
		
		boolean checkIfAttackIsValidPlayer3      	= ruleEngine.checkIfAttackIsValid(finalAttackPlayer3);
		boolean checkIfDefenseIsValidPlayer4        = ruleEngine.checkIfDefenseIsValid(defenseMechanism4);
		
		boolean checkIfAttackIsValidPlayer4      	= ruleEngine.checkIfAttackIsValid(finalAttackPlayer4);
		boolean checkIfDefenseIsValidPlayer1        = ruleEngine.checkIfDefenseIsValid(defenseMechanism1);
		
		/* Target here is player one, player 3 and player 2 are trying to attack player 1 */
		boolean wasItAHit = ruleEngine.isItAHit(finalAttackPlayer1, defenseMechanism2, attackSpeed1, defenseSpeed2);
		boolean wasItAHit1 = ruleEngine.isItAHit(finalAttackPlayer2, defenseMechanism3, attackSpeed2, defenseSpeed3);
		boolean wasItAHit2 = ruleEngine.isItAHit(finalAttackPlayer3, defenseMechanism4, attackSpeed3, defenseSpeed4);
		boolean wasItAHit3 = ruleEngine.isItAHit(finalAttackPlayer4, defenseMechanism1, attackSpeed4, defenseSpeed1);
		
		if(checkIfAttackIsValidPlayer1 && checkIfDefenseIsValidPlayertwo) {
			if(wasItAHit){
				if(defenseMechanism2.equals(player2.getDefenseMechanism())) {
					player2.incrementNumberOfWoundsSustained();
				}
			}
		}
		
		if(checkIfAttackIsValidPlayer2 && checkIfDefenseIsValidPlayer3) {
			if(wasItAHit1){
				if(defenseMechanism3.equals(player3.getDefenseMechanism())) {
					player3.incrementNumberOfWoundsSustained();
				}
			}
		}
		
		if(checkIfAttackIsValidPlayer3 && checkIfDefenseIsValidPlayer4) {
			if(wasItAHit2){
				if(defenseMechanism4.equals(player4.getDefenseMechanism())) {
					player4.incrementNumberOfWoundsSustained();
				}
			}
		}
		
		if(checkIfAttackIsValidPlayer4 && checkIfDefenseIsValidPlayer1) {
			if(wasItAHit3){
				if(defenseMechanism1.equals(player1.getDefenseMechanism())) {
					player1.incrementNumberOfWoundsSustained();
				}
			}
		}
		
		System.out.println("Player1 got " + player1.getNumberOfWoundsSustained() + " wound");
		System.out.println("Player2 got " + player2.getNumberOfWoundsSustained() + " wound");
		System.out.println("Player3 got " + player3.getNumberOfWoundsSustained() + " wound");
		System.out.println("Player4 got " + player4.getNumberOfWoundsSustained() + " wound");
		
		assertEquals(1, player1.getNumberOfWoundsSustained());
		assertEquals(1, player2.getNumberOfWoundsSustained());
		assertEquals(1, player3.getNumberOfWoundsSustained());
		assertEquals(1, player4.getNumberOfWoundsSustained());
	}
	
	@Test
	public void resolveAttackWithFourPlayersWithSomeHit() {
		/* Assuming I am trying to create three player objects and instantiate their attributes */
		String playerName1         = "joe";
		String playerToAttack1     = "jack";
		String attackMechanism1    = ruleEngine.attackMoves[1];
		int attackSpeed1           = 1;
		String defenseMechanism1   = ruleEngine.defenceMoves[0];
		int defenseSpeed1          = 3;
		
		String playerName2         = "jack";
		String playerToAttack2     = "fred";
		String attackMechanism2    = ruleEngine.attackMoves[2];
		int attackSpeed2           = 3;
		String defenseMechanism2   = ruleEngine.defenceMoves[0];
		int defenseSpeed2          = 1;
		
		String playerName3         = "fred";
		String playerToAttack3     = "john";
		String attackMechanism3    = ruleEngine.attackMoves[2];
		int attackSpeed3           = 1;
		String defenseMechanism3   = ruleEngine.defenceMoves[1];
		int defenseSpeed3          = 3;
		
		String playerName4         = "john";
		String playerToAttack4     = "joe";
		String attackMechanism4    = ruleEngine.attackMoves[1];
		int attackSpeed4           = 1;
		String defenseMechanism4   = ruleEngine.defenceMoves[0];
		int defenseSpeed4          = 3;
		
		Player player1 = new Player(playerName1, playerToAttack1, attackMechanism1, attackSpeed1, defenseMechanism1, defenseSpeed1);
		Player player2 = new Player(playerName2, playerToAttack2, attackMechanism2, attackSpeed2, defenseMechanism2, defenseSpeed2);
		Player player3 = new Player(playerName3, playerToAttack3, attackMechanism3, attackSpeed3, defenseMechanism3, defenseSpeed3);
		Player player4 = new Player(playerName4, playerToAttack4, attackMechanism4, attackSpeed4, defenseMechanism4, defenseSpeed4);
		
		String finalAttackPlayer1 = ruleEngine.finalAttackToBeUsed(attackMechanism1, 2);
		String finalAttackPlayer2 = ruleEngine.finalAttackToBeUsed(attackMechanism2, 1);
		String finalAttackPlayer3 = ruleEngine.finalAttackToBeUsed(attackMechanism3, 2);
		String finalAttackPlayer4 = ruleEngine.finalAttackToBeUsed(attackMechanism4, 1);
		
		/* Check for player one and two attack and defense speed are valid */ 
		boolean checkIfAttackIsValidPlayer1         = ruleEngine.checkIfAttackIsValid(finalAttackPlayer1);
		boolean checkIfDefenseIsValidPlayertwo     	= ruleEngine.checkIfDefenseIsValid(defenseMechanism2);
		
		boolean checkIfAttackIsValidPlayer2      	= ruleEngine.checkIfAttackIsValid(finalAttackPlayer2);
		boolean checkIfDefenseIsValidPlayer3        = ruleEngine.checkIfDefenseIsValid(defenseMechanism3);
		
		boolean checkIfAttackIsValidPlayer3      	= ruleEngine.checkIfAttackIsValid(finalAttackPlayer3);
		boolean checkIfDefenseIsValidPlayer4        = ruleEngine.checkIfDefenseIsValid(defenseMechanism4);
		
		boolean checkIfAttackIsValidPlayer4      	= ruleEngine.checkIfAttackIsValid(finalAttackPlayer4);
		boolean checkIfDefenseIsValidPlayer1        = ruleEngine.checkIfDefenseIsValid(defenseMechanism1);
		
		/* Target here is player one, player 3 and player 2 are trying to attack player 1 */
		boolean wasItAHit = ruleEngine.isItAHit(finalAttackPlayer1, defenseMechanism2, attackSpeed1, defenseSpeed2);
		boolean wasItAHit1 = ruleEngine.isItAHit(finalAttackPlayer2, defenseMechanism3, attackSpeed2, defenseSpeed3);
		boolean wasItAHit2 = ruleEngine.isItAHit(finalAttackPlayer3, defenseMechanism4, attackSpeed3, defenseSpeed4);
		boolean wasItAHit3 = ruleEngine.isItAHit(finalAttackPlayer4, defenseMechanism1, attackSpeed4, defenseSpeed1);
		
		if(checkIfAttackIsValidPlayer1 && checkIfDefenseIsValidPlayertwo) {
			if(wasItAHit){
				if(defenseMechanism2.equals(player2.getDefenseMechanism())) {
					player2.incrementNumberOfWoundsSustained();
				}
			}
		}
		
		if(checkIfAttackIsValidPlayer2 && checkIfDefenseIsValidPlayer3) {
			if(wasItAHit1){
				if(defenseMechanism3.equals(player3.getDefenseMechanism())) {
					player3.incrementNumberOfWoundsSustained();
				}
			}
		}
		
		if(checkIfAttackIsValidPlayer3 && checkIfDefenseIsValidPlayer4) {
			if(wasItAHit2){
				if(defenseMechanism4.equals(player4.getDefenseMechanism())) {
					player4.incrementNumberOfWoundsSustained();
				}
			}
		}
		
		if(checkIfAttackIsValidPlayer4 && checkIfDefenseIsValidPlayer1) {
			if(wasItAHit3){
				if(defenseMechanism1.equals(player1.getDefenseMechanism())) {
					player1.incrementNumberOfWoundsSustained();
				}
			}
		}
		
		System.out.println("Player1 got " + player1.getNumberOfWoundsSustained() + " wound");
		System.out.println("Player2 got " + player2.getNumberOfWoundsSustained() + " wound");
		System.out.println("Player3 got " + player3.getNumberOfWoundsSustained() + " wound");
		System.out.println("Player4 got " + player4.getNumberOfWoundsSustained() + " wound");
		
		assertEquals(1, player1.getNumberOfWoundsSustained());
		assertEquals(0, player2.getNumberOfWoundsSustained());
		assertEquals(0, player3.getNumberOfWoundsSustained());
		assertEquals(1, player4.getNumberOfWoundsSustained());
	}
	
	@Test
	public void resolveAttackWithFourPlayersWithTwoPlayersOnOneTargetAndTheOtherTwoTargetingOneOtherPlayer() {
		/* Assuming I am trying to create three player objects and instantiate their attributes */
		String playerName1         = "joe";
		String playerToAttack1     = "fred";
		String attackMechanism1    = ruleEngine.attackMoves[1];
		int attackSpeed1           = 1;
		String defenseMechanism1   = ruleEngine.defenceMoves[0];
		int defenseSpeed1          = 3;
		
		String playerName2         = "jack";
		String playerToAttack2     = "fred";
		String attackMechanism2    = ruleEngine.attackMoves[2];
		int attackSpeed2           = 3;
		String defenseMechanism2   = ruleEngine.defenceMoves[0];
		int defenseSpeed2          = 1;
		
		String playerName3         = "fred";
		String playerToAttack3     = "joe";
		String attackMechanism3    = ruleEngine.attackMoves[2];
		int attackSpeed3           = 1;
		String defenseMechanism3   = ruleEngine.defenceMoves[1];
		int defenseSpeed3          = 3;
		
		String playerName4         = "john";
		String playerToAttack4     = "joe";
		String attackMechanism4    = ruleEngine.attackMoves[1];
		int attackSpeed4           = 1;
		String defenseMechanism4   = ruleEngine.defenceMoves[0];
		int defenseSpeed4          = 3;
		
		Player player1 = new Player(playerName1, playerToAttack1, attackMechanism1, attackSpeed1, defenseMechanism1, defenseSpeed1);
		Player player2 = new Player(playerName2, playerToAttack2, attackMechanism2, attackSpeed2, defenseMechanism2, defenseSpeed2);
		Player player3 = new Player(playerName3, playerToAttack3, attackMechanism3, attackSpeed3, defenseMechanism3, defenseSpeed3);
		Player player4 = new Player(playerName4, playerToAttack4, attackMechanism4, attackSpeed4, defenseMechanism4, defenseSpeed4);
		
		String finalAttackPlayer1 = ruleEngine.finalAttackToBeUsed(attackMechanism1, 3);
		String finalAttackPlayer2 = ruleEngine.finalAttackToBeUsed(attackMechanism2, 4);
		String finalAttackPlayer3 = ruleEngine.finalAttackToBeUsed(attackMechanism3, 2);
		String finalAttackPlayer4 = ruleEngine.finalAttackToBeUsed(attackMechanism4, 6);
		
		/* Check for player one and two attack and defense speed are valid */ 
		boolean checkIfAttackIsValidPlayer1         = ruleEngine.checkIfAttackIsValid(finalAttackPlayer1);
		boolean checkIfDefenseIsValidPlayerthree    = ruleEngine.checkIfDefenseIsValid(defenseMechanism3);
		
		boolean checkIfAttackIsValidPlayer2      	= ruleEngine.checkIfAttackIsValid(finalAttackPlayer2);
		boolean checkIfDefenseIsValidPlayer3        = ruleEngine.checkIfDefenseIsValid(defenseMechanism3);
		
		boolean checkIfAttackIsValidPlayer3      	= ruleEngine.checkIfAttackIsValid(finalAttackPlayer3);
		boolean checkIfDefenseIsValidPlayerone      = ruleEngine.checkIfDefenseIsValid(defenseMechanism1);
		
		boolean checkIfAttackIsValidPlayer4      	= ruleEngine.checkIfAttackIsValid(finalAttackPlayer4);
		boolean checkIfDefenseIsValidPlayer1        = ruleEngine.checkIfDefenseIsValid(defenseMechanism1);
		
		/* Target here is player one, player 3 and player 2 are trying to attack player 1 */
		boolean wasItAHit  = ruleEngine.isItAHit(finalAttackPlayer1, defenseMechanism3, attackSpeed1, defenseSpeed3);
		boolean wasItAHit1 = ruleEngine.isItAHit(finalAttackPlayer2, defenseMechanism3, attackSpeed2, defenseSpeed3);
		boolean wasItAHit2 = ruleEngine.isItAHit(finalAttackPlayer3, defenseMechanism1, attackSpeed3, defenseSpeed1);
		boolean wasItAHit3 = ruleEngine.isItAHit(finalAttackPlayer4, defenseMechanism1, attackSpeed4, defenseSpeed1);
		
		if(checkIfAttackIsValidPlayer1 && checkIfDefenseIsValidPlayerthree) {
			if(wasItAHit){
				if(defenseMechanism3.equals(player3.getDefenseMechanism())) {
					player3.incrementNumberOfWoundsSustained();
				}
			}
		}
		
		if(checkIfAttackIsValidPlayer2 && checkIfDefenseIsValidPlayer3) {
			if(wasItAHit1){
				if(defenseMechanism3.equals(player3.getDefenseMechanism())) {
					player3.incrementNumberOfWoundsSustained();
				}
			}
		}
		
		if(checkIfAttackIsValidPlayer3 && checkIfDefenseIsValidPlayerone) {
			if(wasItAHit2){
				if(defenseMechanism1.equals(player1.getDefenseMechanism())) {
					player1.incrementNumberOfWoundsSustained();
				}
			}
		}
		
		if(checkIfAttackIsValidPlayer4 && checkIfDefenseIsValidPlayer1) {
			if(wasItAHit3){
				if(defenseMechanism1.equals(player1.getDefenseMechanism())) {
					player1.incrementNumberOfWoundsSustained();
				}
			}
		}
		
		System.out.println("Player1 got " + player1.getNumberOfWoundsSustained() + " wound");
		System.out.println("Player2 got " + player2.getNumberOfWoundsSustained() + " wound");
		System.out.println("Player3 got " + player3.getNumberOfWoundsSustained() + " wound");
		System.out.println("Player4 got " + player4.getNumberOfWoundsSustained() + " wound");
		
		assertEquals(2, player1.getNumberOfWoundsSustained());
		assertEquals(0, player2.getNumberOfWoundsSustained());
		assertEquals(2, player3.getNumberOfWoundsSustained());
		assertEquals(0, player4.getNumberOfWoundsSustained());
	}
	
	@Test
	public void resolveAttackWithFourPlayersWithThreePlayersOnOneTargetNoHit() {
		/* Assuming I am trying to create three player objects and instantiate their attributes */
		String playerName1         = "joe";
		String playerToAttack1     = "fred";
		String attackMechanism1    = ruleEngine.attackMoves[0];
		int attackSpeed1           = 3;
		String defenseMechanism1   = ruleEngine.defenceMoves[2];
		int defenseSpeed1          = 3;
		
		String playerName2         = "jack";
		String playerToAttack2     = "fred";
		String attackMechanism2    = ruleEngine.attackMoves[2];
		int attackSpeed2           = 2;
		String defenseMechanism2   = ruleEngine.defenceMoves[0];
		int defenseSpeed2          = 2;
		
		String playerName3         = "fred";
		String playerToAttack3     = "joe";
		String attackMechanism3    = ruleEngine.attackMoves[2];
		int attackSpeed3           = 1;
		String defenseMechanism3   = ruleEngine.defenceMoves[1];
		int defenseSpeed3          = 1;
		
		String playerName4         = "john";
		String playerToAttack4     = "fred";
		String attackMechanism4    = ruleEngine.attackMoves[0];
		int attackSpeed4           = 3;
		String defenseMechanism4   = ruleEngine.defenceMoves[0];
		int defenseSpeed4          = 1;
		
		Player player1 = new Player(playerName1, playerToAttack1, attackMechanism1, attackSpeed1, defenseMechanism1, defenseSpeed1);
		Player player2 = new Player(playerName2, playerToAttack2, attackMechanism2, attackSpeed2, defenseMechanism2, defenseSpeed2);
		Player player3 = new Player(playerName3, playerToAttack3, attackMechanism3, attackSpeed3, defenseMechanism3, defenseSpeed3);
		Player player4 = new Player(playerName4, playerToAttack4, attackMechanism4, attackSpeed4, defenseMechanism4, defenseSpeed4);
		
		String finalAttackPlayer1 = ruleEngine.finalAttackToBeUsed(attackMechanism1, 1);
		String finalAttackPlayer2 = ruleEngine.finalAttackToBeUsed(attackMechanism2, 2);
		String finalAttackPlayer3 = ruleEngine.finalAttackToBeUsed(attackMechanism3, 6);
		String finalAttackPlayer4 = ruleEngine.finalAttackToBeUsed(attackMechanism4, 2);
		
		/* Check for player one and two attack and defense speed are valid */ 
		boolean checkIfAttackIsValidPlayer1         = ruleEngine.checkIfAttackIsValid(finalAttackPlayer1);
		boolean checkIfDefenseIsValidPlayerthree    = ruleEngine.checkIfDefenseIsValid(defenseMechanism3);
		
		boolean checkIfAttackIsValidPlayer2      	= ruleEngine.checkIfAttackIsValid(finalAttackPlayer2);
		boolean checkIfDefenseIsValidPlayer3        = ruleEngine.checkIfDefenseIsValid(defenseMechanism3);
		
		boolean checkIfAttackIsValidPlayer4      	= ruleEngine.checkIfAttackIsValid(finalAttackPlayer4);
		boolean checkIfDefenseIsValidPlayerThree    = ruleEngine.checkIfDefenseIsValid(defenseMechanism3);
		
		/* Target here is player one, player 3 and player 2 are trying to attack player 1 */
		boolean wasItAHit  = ruleEngine.isItAHit(finalAttackPlayer1, defenseMechanism3, attackSpeed1, defenseSpeed3);
		boolean wasItAHit1 = ruleEngine.isItAHit(finalAttackPlayer2, defenseMechanism3, attackSpeed2, defenseSpeed3);
		boolean wasItAHit2 = ruleEngine.isItAHit(finalAttackPlayer4, defenseMechanism3, attackSpeed4, defenseSpeed3);
		
		if(checkIfAttackIsValidPlayer1 && checkIfDefenseIsValidPlayerthree) {
			if(wasItAHit){
				if(defenseMechanism3.equals(player3.getDefenseMechanism())) {
					player3.incrementNumberOfWoundsSustained();
				}
			}
		}
		
		if(checkIfAttackIsValidPlayer2 && checkIfDefenseIsValidPlayer3) {
			if(wasItAHit1){
				if(defenseMechanism3.equals(player3.getDefenseMechanism())) {
					player3.incrementNumberOfWoundsSustained();
				}
			}
		}
		
		if(checkIfAttackIsValidPlayer4 && checkIfDefenseIsValidPlayerThree) {
			if(wasItAHit2){
				if(defenseMechanism3.equals(player3.getDefenseMechanism())) {
					player3.incrementNumberOfWoundsSustained();
				}
			}
		}
		
		System.out.println("Player3 got " + player3.getNumberOfWoundsSustained() + " wound");
		assertEquals(0, player3.getNumberOfWoundsSustained());
	}
	
	@Test
	public void resolveAttackWithFourPlayersWithThreePlayersOnOneTargetOneHits() {
		/* Assuming I am trying to create three player objects and instantiate their attributes */
		String playerName1         = "joe";
		String playerToAttack1     = "fred";
		String attackMechanism1    = ruleEngine.attackMoves[0];
		int attackSpeed1           = 3;
		String defenseMechanism1   = ruleEngine.defenceMoves[1];
		int defenseSpeed1          = 1;
		
		String playerName2         = "jack";
		String playerToAttack2     = "fred";
		String attackMechanism2    = ruleEngine.attackMoves[2];
		int attackSpeed2           = 3;
		String defenseMechanism2   = ruleEngine.defenceMoves[0];
		int defenseSpeed2          = 1;
		
		String playerName3         = "fred";
		String playerToAttack3     = "joe";
		String attackMechanism3    = ruleEngine.attackMoves[2];
		int attackSpeed3           = 1;
		String defenseMechanism3   = ruleEngine.defenceMoves[1];
		int defenseSpeed3          = 3;
		
		String playerName4         = "john";
		String playerToAttack4     = "fred";
		String attackMechanism4    = ruleEngine.attackMoves[1];
		int attackSpeed4           = 2;
		String defenseMechanism4   = ruleEngine.defenceMoves[1];
		int defenseSpeed4          = 2;
		
		Player player1 = new Player(playerName1, playerToAttack1, attackMechanism1, attackSpeed1, defenseMechanism1, defenseSpeed1);
		Player player2 = new Player(playerName2, playerToAttack2, attackMechanism2, attackSpeed2, defenseMechanism2, defenseSpeed2);
		Player player3 = new Player(playerName3, playerToAttack3, attackMechanism3, attackSpeed3, defenseMechanism3, defenseSpeed3);
		Player player4 = new Player(playerName4, playerToAttack4, attackMechanism4, attackSpeed4, defenseMechanism4, defenseSpeed4);
		
		String finalAttackPlayer1 = ruleEngine.finalAttackToBeUsed(attackMechanism1, 1);
		String finalAttackPlayer2 = ruleEngine.finalAttackToBeUsed(attackMechanism2, 2);
		String finalAttackPlayer3 = ruleEngine.finalAttackToBeUsed(attackMechanism3, 6);
		String finalAttackPlayer4 = ruleEngine.finalAttackToBeUsed(attackMechanism4, 2);
		
		/* Check for player one and two attack and defense speed are valid */ 
		boolean checkIfAttackIsValidPlayer1         = ruleEngine.checkIfAttackIsValid(finalAttackPlayer1);
		boolean checkIfDefenseIsValidPlayerthree    = ruleEngine.checkIfDefenseIsValid(defenseMechanism3);
		
		boolean checkIfAttackIsValidPlayer2      	= ruleEngine.checkIfAttackIsValid(finalAttackPlayer2);
		boolean checkIfDefenseIsValidPlayer3        = ruleEngine.checkIfDefenseIsValid(defenseMechanism3);
		
		boolean checkIfAttackIsValidPlayer4      	= ruleEngine.checkIfAttackIsValid(finalAttackPlayer4);
		boolean checkIfDefenseIsValidPlayerThree    = ruleEngine.checkIfDefenseIsValid(defenseMechanism3);
		
		/* Target here is player one, player 3 and player 2 are trying to attack player 1 */
		boolean wasItAHit  = ruleEngine.isItAHit(finalAttackPlayer1, defenseMechanism3, attackSpeed1, defenseSpeed3);
		boolean wasItAHit1 = ruleEngine.isItAHit(finalAttackPlayer2, defenseMechanism3, attackSpeed2, defenseSpeed3);
		boolean wasItAHit2 = ruleEngine.isItAHit(finalAttackPlayer4, defenseMechanism3, attackSpeed4, defenseSpeed3);
		
		if(checkIfAttackIsValidPlayer1 && checkIfDefenseIsValidPlayerthree) {
			if(wasItAHit){
				if(defenseMechanism3.equals(player3.getDefenseMechanism())) {
					player3.incrementNumberOfWoundsSustained();
				}
			}
		}
		
		if(checkIfAttackIsValidPlayer2 && checkIfDefenseIsValidPlayer3) {
			if(wasItAHit1){
				if(defenseMechanism3.equals(player3.getDefenseMechanism())) {
					player3.incrementNumberOfWoundsSustained();
				}
			}
		}
		
		if(checkIfAttackIsValidPlayer4 && checkIfDefenseIsValidPlayerThree) {
			if(wasItAHit2){
				if(defenseMechanism3.equals(player3.getDefenseMechanism())) {
					player3.incrementNumberOfWoundsSustained();
				}
			}
		}
		
		System.out.println("Player3 got " + player3.getNumberOfWoundsSustained() + " wound");
		assertEquals(1, player3.getNumberOfWoundsSustained());
	}
	
	@Test
	public void resolveAttackWithFourPlayersWithThreePlayersOnOneTargetTwoHits() {
		/* Assuming I am trying to create three player objects and instantiate their attributes */
		String playerName1         = "joe";
		String playerToAttack1     = "fred";
		String attackMechanism1    = ruleEngine.attackMoves[0];
		int attackSpeed1           = 1;
		String defenseMechanism1   = ruleEngine.defenceMoves[1];
		int defenseSpeed1          = 3;
		
		String playerName2         = "jack";
		String playerToAttack2     = "fred";
		String attackMechanism2    = ruleEngine.attackMoves[2];
		int attackSpeed2           = 1;
		String defenseMechanism2   = ruleEngine.defenceMoves[0];
		int defenseSpeed2          = 3;
		
		String playerName3         = "fred";
		String playerToAttack3     = "joe";
		String attackMechanism3    = ruleEngine.attackMoves[2];
		int attackSpeed3           = 1;
		String defenseMechanism3   = ruleEngine.defenceMoves[1];
		int defenseSpeed3          = 3;
		
		String playerName4         = "john";
		String playerToAttack4     = "fred";
		String attackMechanism4    = ruleEngine.attackMoves[2];
		int attackSpeed4           = 3;
		String defenseMechanism4   = ruleEngine.defenceMoves[1];
		int defenseSpeed4          = 1;
		
		Player player1 = new Player(playerName1, playerToAttack1, attackMechanism1, attackSpeed1, defenseMechanism1, defenseSpeed1);
		Player player2 = new Player(playerName2, playerToAttack2, attackMechanism2, attackSpeed2, defenseMechanism2, defenseSpeed2);
		Player player3 = new Player(playerName3, playerToAttack3, attackMechanism3, attackSpeed3, defenseMechanism3, defenseSpeed3);
		Player player4 = new Player(playerName4, playerToAttack4, attackMechanism4, attackSpeed4, defenseMechanism4, defenseSpeed4);
		
		String finalAttackPlayer1 = ruleEngine.finalAttackToBeUsed(attackMechanism1, 1);
		String finalAttackPlayer2 = ruleEngine.finalAttackToBeUsed(attackMechanism2, 2);
		String finalAttackPlayer3 = ruleEngine.finalAttackToBeUsed(attackMechanism3, 6);
		String finalAttackPlayer4 = ruleEngine.finalAttackToBeUsed(attackMechanism4, 2);
		
		/* Check for player one and two attack and defense speed are valid */ 
		boolean checkIfAttackIsValidPlayer1         = ruleEngine.checkIfAttackIsValid(finalAttackPlayer1);
		boolean checkIfDefenseIsValidPlayerthree    = ruleEngine.checkIfDefenseIsValid(defenseMechanism3);
		
		boolean checkIfAttackIsValidPlayer2      	= ruleEngine.checkIfAttackIsValid(finalAttackPlayer2);
		boolean checkIfDefenseIsValidPlayer3        = ruleEngine.checkIfDefenseIsValid(defenseMechanism3);
		
		boolean checkIfAttackIsValidPlayer4      	= ruleEngine.checkIfAttackIsValid(finalAttackPlayer4);
		boolean checkIfDefenseIsValidPlayerThree    = ruleEngine.checkIfDefenseIsValid(defenseMechanism3);
		
		/* Target here is player one, player 3 and player 2 are trying to attack player 1 */
		boolean wasItAHit  = ruleEngine.isItAHit(finalAttackPlayer1, defenseMechanism3, attackSpeed1, defenseSpeed3);
		boolean wasItAHit1 = ruleEngine.isItAHit(finalAttackPlayer2, defenseMechanism3, attackSpeed2, defenseSpeed3);
		boolean wasItAHit2 = ruleEngine.isItAHit(finalAttackPlayer4, defenseMechanism3, attackSpeed4, defenseSpeed3);
		
		if(checkIfAttackIsValidPlayer1 && checkIfDefenseIsValidPlayerthree) {
			if(wasItAHit){
				if(defenseMechanism3.equals(player3.getDefenseMechanism())) {
					player3.incrementNumberOfWoundsSustained();
				}
			}
		}
		
		if(checkIfAttackIsValidPlayer2 && checkIfDefenseIsValidPlayer3) {
			if(wasItAHit1){
				if(defenseMechanism3.equals(player3.getDefenseMechanism())) {
					player3.incrementNumberOfWoundsSustained();
				}
			}
		}
		
		if(checkIfAttackIsValidPlayer4 && checkIfDefenseIsValidPlayerThree) {
			if(wasItAHit2){
				if(defenseMechanism3.equals(player3.getDefenseMechanism())) {
					player3.incrementNumberOfWoundsSustained();
				}
			}
		}
		
		System.out.println("Player3 got " + player3.getNumberOfWoundsSustained() + " wound");
		assertEquals(2, player3.getNumberOfWoundsSustained());
	}
	
	@Test
	public void resolveAttackWithFourPlayersWithThreePlayersOnOneTargetThreeHits() {
		/* Assuming I am trying to create three player objects and instantiate their attributes */
		String playerName1         = "joe";
		String playerToAttack1     = "fred";
		String attackMechanism1    = ruleEngine.attackMoves[0];
		int attackSpeed1           = 1;
		String defenseMechanism1   = ruleEngine.defenceMoves[1];
		int defenseSpeed1          = 3;
		
		String playerName2         = "jack";
		String playerToAttack2     = "fred";
		String attackMechanism2    = ruleEngine.attackMoves[2];
		int attackSpeed2           = 1;
		String defenseMechanism2   = ruleEngine.defenceMoves[0];
		int defenseSpeed2          = 3;
		
		String playerName3         = "fred";
		String playerToAttack3     = "joe";
		String attackMechanism3    = ruleEngine.attackMoves[2];
		int attackSpeed3           = 1;
		String defenseMechanism3   = ruleEngine.defenceMoves[1];
		int defenseSpeed3          = 3;
		
		String playerName4         = "john";
		String playerToAttack4     = "fred";
		String attackMechanism4    = ruleEngine.attackMoves[1];
		int attackSpeed4           = 2;
		String defenseMechanism4   = ruleEngine.defenceMoves[1];
		int defenseSpeed4          = 2;
		
		Player player1 = new Player(playerName1, playerToAttack1, attackMechanism1, attackSpeed1, defenseMechanism1, defenseSpeed1);
		Player player2 = new Player(playerName2, playerToAttack2, attackMechanism2, attackSpeed2, defenseMechanism2, defenseSpeed2);
		Player player3 = new Player(playerName3, playerToAttack3, attackMechanism3, attackSpeed3, defenseMechanism3, defenseSpeed3);
		Player player4 = new Player(playerName4, playerToAttack4, attackMechanism4, attackSpeed4, defenseMechanism4, defenseSpeed4);
		
		String finalAttackPlayer1 = ruleEngine.finalAttackToBeUsed(attackMechanism1, 1);
		String finalAttackPlayer2 = ruleEngine.finalAttackToBeUsed(attackMechanism2, 2);
		String finalAttackPlayer3 = ruleEngine.finalAttackToBeUsed(attackMechanism3, 6);
		String finalAttackPlayer4 = ruleEngine.finalAttackToBeUsed(attackMechanism4, 2);
		
		/* Check for player one and two attack and defense speed are valid */ 
		boolean checkIfAttackIsValidPlayer1         = ruleEngine.checkIfAttackIsValid(finalAttackPlayer1);
		boolean checkIfDefenseIsValidPlayerthree    = ruleEngine.checkIfDefenseIsValid(defenseMechanism3);
		
		boolean checkIfAttackIsValidPlayer2      	= ruleEngine.checkIfAttackIsValid(finalAttackPlayer2);
		boolean checkIfDefenseIsValidPlayer3        = ruleEngine.checkIfDefenseIsValid(defenseMechanism3);
		
		boolean checkIfAttackIsValidPlayer4      	= ruleEngine.checkIfAttackIsValid(finalAttackPlayer4);
		boolean checkIfDefenseIsValidPlayerThree    = ruleEngine.checkIfDefenseIsValid(defenseMechanism3);
		
		/* Target here is player one, player 3 and player 2 are trying to attack player 1 */
		boolean wasItAHit  = ruleEngine.isItAHit(finalAttackPlayer1, defenseMechanism3, attackSpeed1, defenseSpeed3);
		boolean wasItAHit1 = ruleEngine.isItAHit(finalAttackPlayer2, defenseMechanism3, attackSpeed2, defenseSpeed3);
		boolean wasItAHit2 = ruleEngine.isItAHit(finalAttackPlayer4, defenseMechanism3, attackSpeed4, defenseSpeed3);
		
		if(checkIfAttackIsValidPlayer1 && checkIfDefenseIsValidPlayerthree) {
			if(wasItAHit){
				if(defenseMechanism3.equals(player3.getDefenseMechanism())) {
					player3.incrementNumberOfWoundsSustained();
				}
			}
		}
		
		if(checkIfAttackIsValidPlayer2 && checkIfDefenseIsValidPlayer3) {
			if(wasItAHit1){
				if(defenseMechanism3.equals(player3.getDefenseMechanism())) {
					player3.incrementNumberOfWoundsSustained();
				}
			}
		}
		
		if(checkIfAttackIsValidPlayer4 && checkIfDefenseIsValidPlayerThree) {
			if(wasItAHit2){
				if(defenseMechanism3.equals(player3.getDefenseMechanism())) {
					player3.incrementNumberOfWoundsSustained();
				}
			}
		}
		
		System.out.println("Player3 got " + player3.getNumberOfWoundsSustained() + " wound");
		assertEquals(3, player3.getNumberOfWoundsSustained());
	}
}


