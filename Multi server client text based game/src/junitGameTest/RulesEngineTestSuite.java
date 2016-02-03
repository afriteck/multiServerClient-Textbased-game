package junitGameTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	TestWhenAttackMechAndAttackSpeedHit.class,
	TestIfAtcualAttackDirectionMatchesDefense.class,
	TestAttackmechAndSpeedsHitsFails.class,
	TestActualDirectionOfAttack.class,
	ResolveAttackWithTwoPlayersOrThreeOrFourPlayers.class,
	ConcoludingAnAttackHitBecauseOfTime.class
})

public class RulesEngineTestSuite {   
} 
