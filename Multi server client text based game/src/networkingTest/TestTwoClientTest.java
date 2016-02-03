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

public class TestTwoClientTest {

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
	public void testMultipleClientAndSendMEssagesToAllClientsConnected() throws UnknownHostException, IOException {
		String ipAdd = "localhost";
		int    port      = 3003;
		
		ServerApp server = new ServerApp(port);
		ClientApp client = new ClientApp(ipAdd, port);
		client.send("join fred");
	}
	

}
