package junitGameTest;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import gameRuleEngine.GameRuleEngine;


public class TestIfAtcualAttackDirectionMatchesDefense {

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
	public void testIfItHitWhenItsAThrustAgainstACharge() {
		String finalAttackToBeUsed = ruleEngine.attackMoves[0];
		String opponentsDefense    = ruleEngine.defenceMoves[0];
		
		int    attackSpeed         = 1;
		int    defenseSpeed        = 3;
		
		System.out.println("@Test: testIfItHitWhenItsAThrustAgainstACharge");
		assertTrue(ruleEngine.isItAHit(finalAttackToBeUsed, opponentsDefense, attackSpeed, defenseSpeed));
	}
	
	@Test
	public void testIfItHitWhenItsASwingAgainstADodge() {
		String finalAttackToBeUsed = ruleEngine.attackMoves[1];
		String opponentsDefense    = ruleEngine.defenceMoves[1];
		int    attackSpeed         = 2;
		int    defenseSpeed        = 3;
		
		System.out.println("@Test: testIfItHitWhenItsASwingAgainstADodge");
		assertTrue(ruleEngine.isItAHit(finalAttackToBeUsed, opponentsDefense, attackSpeed, defenseSpeed));
	}
	
	@Test
	public void testIfItHitWhenItsASmashAgainstADuck() {
		String finalAttackToBeUsed = ruleEngine.attackMoves[2];
		String opponentsDefense    = ruleEngine.defenceMoves[2];
		int    attackSpeed         = 1;
		int    defenseSpeed        = 2;
		
		System.out.println("@Test: testIfItHitWhenItsASmashAgainstADuck");
		assertTrue(ruleEngine.isItAHit(finalAttackToBeUsed, opponentsDefense, attackSpeed, defenseSpeed));
	}
	
	@Test
	public void testIfItHitWhenItsAThrustAgainstADuck() {
		String finalAttackToBeUsed = ruleEngine.attackMoves[0];
		String opponentsDefense    = ruleEngine.defenceMoves[2];
		int    attackSpeed         = 3;
		int    defenseSpeed        = 1;
		
		System.out.println("@Test: testIfItHitWhenItsAThrustAgainstADuck");
		assertFalse(ruleEngine.isItAHit(finalAttackToBeUsed, opponentsDefense, attackSpeed, defenseSpeed));
	}

	@Test
	public void testIfItHitWhenItsAThrustAgainstADodge() {
		String finalAttackToBeUsed = ruleEngine.attackMoves[0];
		String opponentsDefense    = ruleEngine.defenceMoves[1];
		int    attackSpeed         = 3;
		int    defenseSpeed        = 1;
		
		System.out.println("@Test: testIfItHitWhenItsAThrustAgainstADodge");
		assertFalse(ruleEngine.isItAHit(finalAttackToBeUsed, opponentsDefense, attackSpeed, defenseSpeed));
	}
	
	@Test
	public void testIfItHitWhenItsASwingAgainstACharge() {
		String finalAttackToBeUsed = ruleEngine.attackMoves[1];
		String opponentsDefense    = ruleEngine.defenceMoves[0];
		int    attackSpeed         = 3;
		int    defenseSpeed        = 2;
		
		System.out.println("@Test: testIfItHitWhenItsASwingAgainstACharge");
		assertFalse(ruleEngine.isItAHit(finalAttackToBeUsed, opponentsDefense, attackSpeed, defenseSpeed));
	}
	
	@Test
	public void testIfItHitWhenItsASwingAgainstADuck() {
		String finalAttackToBeUsed = ruleEngine.attackMoves[1];
		String opponentsDefense    = ruleEngine.defenceMoves[2];
		int    attackSpeed         = 3;
		int    defenseSpeed        = 1;
		
		System.out.println("@Test: testIfItHitWhenItsASwingAgainstADuck");
		assertFalse(ruleEngine.isItAHit(finalAttackToBeUsed, opponentsDefense, attackSpeed, defenseSpeed));
	}
	
	@Test
	public void testIfItHitWhenItsASmashAgainstACharge() {
		String finalAttackToBeUsed = ruleEngine.attackMoves[2];
		String opponentsDefense    = ruleEngine.defenceMoves[0];
		int    attackSpeed         = 2;
		int    defenseSpeed        = 2;
		
		System.out.println("@Test: testIfItHitWhenItsASmashAgainstACharge");
		assertFalse(ruleEngine.isItAHit(finalAttackToBeUsed, opponentsDefense, attackSpeed, defenseSpeed));
	}
	
	@Test
	public void testIfItHitWhenItsASmashAgainstADodge() {
		String finalAttackToBeUsed = ruleEngine.attackMoves[2];
		String opponentsDefense    = ruleEngine.defenceMoves[1];
		int    attackSpeed         = 3;
		int    defenseSpeed        = 1;
		
		System.out.println("@Test: testIfItHitWhenItsASmashAgainstADodge");
		assertFalse(ruleEngine.isItAHit(finalAttackToBeUsed, opponentsDefense, attackSpeed, defenseSpeed));
	}
}
