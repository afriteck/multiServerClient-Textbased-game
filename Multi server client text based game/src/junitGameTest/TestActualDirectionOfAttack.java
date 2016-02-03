package junitGameTest;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import gameRuleEngine.GameRuleEngine;

public class TestActualDirectionOfAttack {

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
	public void testFinalAttackForThrustIfDieIsAOne() {
		int dieNumber = 1;
		System.out.println("@Test: testFinalAttackForSmashIfDieIsAOne");	
		String fredsAttack = ruleEngine.finalAttackToBeUsed(ruleEngine.attackMoves[0], dieNumber);
		
		assertEquals(fredsAttack, ruleEngine.attackMoves[0]);
	}

	@Test
	public void testFinalAttackForThrustIfDieIsATwo() {
		int dieNumber = 2;
		System.out.println("@Test: testFinalAttackForThrustIfDieIsATwo");	
		String JoesAttack = ruleEngine.finalAttackToBeUsed(ruleEngine.attackMoves[0], dieNumber);
		
		assertEquals(JoesAttack, ruleEngine.attackMoves[0]);
	}
	
	@Test
	public void testFinalAttackForThrustIfDieIsAThree() {
		int dieNumber = 3;
		System.out.println("@Test: testFinalAttackForThrustIfDieIsAThree");	
		String fredsAttack = ruleEngine.finalAttackToBeUsed(ruleEngine.attackMoves[0], dieNumber);
		
		assertEquals(fredsAttack, ruleEngine.attackMoves[2]);
	}
	
	@Test
	public void testFinalAttackForThrustIfDieIsAFour() {
		int dieNumber = 4;
		System.out.println("@Test: testFinalAttackForThrustIfDieIsAFour");	
		String JoesAttack = ruleEngine.finalAttackToBeUsed(ruleEngine.attackMoves[0], dieNumber);
		
		assertEquals(JoesAttack, ruleEngine.attackMoves[2]);
	}
	
	@Test
	public void testFinalAttackForThrustIfDieIsAFive() {
		int dieNumber = 5;
		System.out.println("@Test: testFinalAttackForThrustIfDieIsAFive");	
		String jacksAttack = ruleEngine.finalAttackToBeUsed(ruleEngine.attackMoves[0], dieNumber);
		
		assertEquals(jacksAttack, ruleEngine.attackMoves[1]);
	}
	
	@Test
	public void testFinalAttackForThrustIfDieIsASix() {
		int dieNumber = 6;
		System.out.println("@Test: testFinalAttackForThrustIfDieIsAFive");	
		String joesAttack = ruleEngine.finalAttackToBeUsed(ruleEngine.attackMoves[0], dieNumber);
		
		assertEquals(joesAttack, ruleEngine.attackMoves[1]);
	}
	
	@Test
	public void testFinalAttackForSwingIfDieIsAOne() {
		int dieNumber = 1;
		System.out.println("@Test: testFinalAttackForSwingIfDieIsAOne");	
		String timmysAttack = ruleEngine.finalAttackToBeUsed(ruleEngine.attackMoves[1], dieNumber);
		
		assertEquals(timmysAttack, ruleEngine.attackMoves[1]);
	}
	
	@Test
	public void testFinalAttackForSwingIfDieIsATwo() {
		int dieNumber = 2;
		System.out.println("@Test: testFinalAttackForSwingIfDieIsAOne");	
		String fredssAttack = ruleEngine.finalAttackToBeUsed(ruleEngine.attackMoves[1], dieNumber);
		
		assertEquals(fredssAttack, ruleEngine.attackMoves[1]);
	}
	
	@Test
	public void testFinalAttackForSwingIfDieIsAThree() {
		int dieNumber = 3;
		System.out.println("@Test: testFinalAttackForSwingIfDieIsAOne");	
		String jacksAttack = ruleEngine.finalAttackToBeUsed(ruleEngine.attackMoves[1], dieNumber);
		
		assertEquals(jacksAttack, ruleEngine.attackMoves[0]);
	}
	
	@Test
	public void testFinalAttackForSwingIfDieIsAFour() {
		int dieNumber = 4;
		System.out.println("@Test: testFinalAttackForSwingIfDieIsAOne");
		String fredssAttack = ruleEngine.finalAttackToBeUsed(ruleEngine.attackMoves[1], dieNumber);
		
		assertEquals(fredssAttack, ruleEngine.attackMoves[0]);
	}
	
	@Test
	public void testFinalAttackForSwingIfDieIsAFive() {
		int dieNumber = 5;
		System.out.println("@Test: testFinalAttackForSwingIfDieIsAOne");	
		String joesAttack = ruleEngine.finalAttackToBeUsed(ruleEngine.attackMoves[1], dieNumber);
		
		assertEquals(joesAttack, ruleEngine.attackMoves[2]);
	}

	@Test
	public void testFinalAttackForSwingIfDieIsASix() {
		int dieNumber = 6;
		System.out.println("@Test: testFinalAttackForSwingIfDieIsAOne");	
		String jamesAttack = ruleEngine.finalAttackToBeUsed(ruleEngine.attackMoves[1], dieNumber);
		
		assertEquals(jamesAttack, ruleEngine.attackMoves[2]);
	}
	
	@Test
	public void testFinalAttackForSmashIfDieIsAOne() {
		int dieNumber = 1;
		System.out.println("@Test: testFinalAttackForSmashIfDieIsAOne");	
		String manipAttack = ruleEngine.finalAttackToBeUsed(ruleEngine.attackMoves[2], dieNumber);
		
		assertEquals(manipAttack, ruleEngine.attackMoves[2]);
	}
	
	@Test
	public void testFinalAttackForSmashIfDieIsATwo() {
		int dieNumber = 2;
		System.out.println("@Test: testFinalAttackForSmashIfDieIsATwo");	
		String fredssAttack = ruleEngine.finalAttackToBeUsed(ruleEngine.attackMoves[2], dieNumber);
		
		assertEquals(fredssAttack, ruleEngine.attackMoves[2]);
	}
	
	@Test
	public void testFinalAttackForSmashIfDieIsAThree() {
		int dieNumber = 3;
		System.out.println("@Test: testFinalAttackForSmashIfDieIsATwo");	
		String manipAttack = ruleEngine.finalAttackToBeUsed(ruleEngine.attackMoves[2], dieNumber);
		
		assertEquals(manipAttack, ruleEngine.attackMoves[1]);
	}
	
	@Test
	public void testFinalAttackForSmashIfDieIsAFour() {
		int dieNumber = 4;
		System.out.println("@Test: testFinalAttackForSmashIfDieIsATwo");	
		String JohnsAttack = ruleEngine.finalAttackToBeUsed(ruleEngine.attackMoves[2], dieNumber);
		
		assertEquals(JohnsAttack, ruleEngine.attackMoves[1]);
	}
	
	@Test
	public void testFinalAttackForSmashIfDieIsAFive() {
		int dieNumber = 5;
		System.out.println("@Test: testFinalAttackForSmashIfDieIsATwo");	
		String sarahsAttack = ruleEngine.finalAttackToBeUsed(ruleEngine.attackMoves[2], dieNumber);
		
		assertEquals(sarahsAttack, ruleEngine.attackMoves[0]);
	}
	
	@Test
	public void testFinalAttackForSmashIfDieIsASix() {
		int dieNumber = 6;
		System.out.println("@Test: testFinalAttackForSmashIfDieIsATwo");
		String sarahsAttack = ruleEngine.finalAttackToBeUsed(ruleEngine.attackMoves[2], dieNumber);
		
		assertEquals(sarahsAttack, ruleEngine.attackMoves[0]);
	}

}
