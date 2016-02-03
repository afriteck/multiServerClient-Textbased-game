package junitGameTest;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import gameRuleEngine.GameRuleEngine;

public class TestAttackmechAndSpeedsHitsFails {
	
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
	public void testThrustAndChargeAndAttackSpeedLessThanOpponentDefenseSpeed() {
		String finalAttackToBeUsed = ruleEngine.attackMoves[0];
		String opponentsDefense    = ruleEngine.defenceMoves[1];
		
		int    attackSpeed         = 3;
		int    defenseSpeed        = 1;
		
		boolean checkAttackSpeedValid  = ruleEngine.attackSpeedValid(attackSpeed);
		boolean checkDefenseSpeedValid = ruleEngine.defenseSpeedValid(defenseSpeed); 
		
		if(checkAttackSpeedValid && checkDefenseSpeedValid) {
			System.out.println("@Test: testSwingAndDodgeAndAttackSpeedLessThanOpponentDefenseSpeed");
			assertFalse(ruleEngine.checkHitSpeedAndCorrectDefenseAndAttack(finalAttackToBeUsed, opponentsDefense, attackSpeed, defenseSpeed));
		}
	}
	
	@Test
	public void testSwingAndDodgeAndAttackSpeedLessThanOpponentDefenseSpeed() {
		String finalAttackToBeUsed = ruleEngine.attackMoves[1];
		String opponentsDefense    = ruleEngine.defenceMoves[0];
		
		int    attackSpeed         = 2;
		int    defenseSpeed        = 2;
		
		boolean checkAttackSpeedValid  = ruleEngine.attackSpeedValid(attackSpeed);
		boolean checkDefenseSpeedValid = ruleEngine.defenseSpeedValid(defenseSpeed); 
		
		if(checkAttackSpeedValid && checkDefenseSpeedValid) {
			System.out.println("@Test: testSwingAndDodgeAndAttackSpeedLessThanOpponentDefenseSpeed");
			assertFalse(ruleEngine.checkHitSpeedAndCorrectDefenseAndAttack(finalAttackToBeUsed, opponentsDefense, attackSpeed, defenseSpeed));
		}
	}
	
	@Test
	public void testSmashAndDuckAndAttackSpeedLessThanOpponentDefenseSpeed() {
		String finalAttackToBeUsed = ruleEngine.attackMoves[2];
		String opponentsDefense    = ruleEngine.defenceMoves[1];
		
		int    attackSpeed         = 3;
		int    defenseSpeed        = 1;
		
		boolean checkAttackSpeedValid  = ruleEngine.attackSpeedValid(attackSpeed);
		boolean checkDefenseSpeedValid = ruleEngine.defenseSpeedValid(defenseSpeed); 
		
		if(checkAttackSpeedValid && checkDefenseSpeedValid) {
			System.out.println("@Test: testSwingAndDodgeAndAttackSpeedLessThanOpponentDefenseSpeed");
			assertFalse(ruleEngine.checkHitSpeedAndCorrectDefenseAndAttack(finalAttackToBeUsed, opponentsDefense, attackSpeed, defenseSpeed));
		}
	}

}
