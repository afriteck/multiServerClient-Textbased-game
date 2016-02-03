package networkingTest;

import static org.junit.Assert.*;

import java.io.IOException;
import java.net.UnknownHostException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import config.Config;
import gameRuleEngine.GameRuleEngine;
import networking.ClientApp;
import networking.ServerApp;

public class ClientTest {

	GameRuleEngine ruleEngine;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.out.println("@Before(): TestClientConnectionToServer\n\n");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		System.out.println("@After(): TestClientConnectionToServer\n\n");
	}

	@Before
	public void setUp() throws Exception {
		
	}

	@After
	public void tearDown() throws Exception {
		
	}
	
	@Test
	public void testIfItCanMakeConnectionToServer() throws UnknownHostException, IOException {
		
		ServerApp serverApp = new ServerApp(Config.DEFAULT_PORT);
		ClientApp client = new ClientApp(Config.DEFAULT_HOST, Config.DEFAULT_PORT);
		client.stop();
	}

}
