package networking;

import java.io.Console;
import java.util.Scanner;

import config.Config;

public class StartServer {

	private static Boolean done = Boolean.FALSE;
	private static Boolean started = Boolean.FALSE;

	private static Scanner sc = new Scanner(System.in);
	private static ServerApp appServer = null;
	
	public static void main(String[] argv) {
	
		Console c = System.console();
		if (c == null) {
			System.err.println("No System Console....");
			System.err.println("Use IDE Console....");
		}
		
		do {
			String input = sc.nextLine();
			
			if (input.equalsIgnoreCase("START") && !started)
			{
				System.out.println("Starting server ...");
				appServer = new ServerApp(Config.DEFAULT_PORT);
				started = Boolean.TRUE;
			}
			
			if (input.equalsIgnoreCase("SHUTDOWN") && started)
			{
				System.out.println("Shutting server down ...");
				started = Boolean.FALSE;
				done = Boolean.TRUE;
			}			
		} while (!done);

		System.exit(1);
	}
}
