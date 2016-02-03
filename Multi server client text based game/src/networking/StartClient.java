package networking;

import java.io.IOException;
import java.util.Scanner;

import config.Config;

public class StartClient {
	static String ipAddress;
	static int    portNumber;
	
	static Scanner response = new Scanner(System.in);
	
	public static void main(String[] args) throws IOException {
		
		System.out.print("Enter player initiator IPAddress: ");
		ipAddress = response.nextLine();
		
		System.out.println();
		
		System.out.print("Enter your portNumber: ");
		portNumber = response.nextInt();
		
		System.out.println();
		
		try {
			new ClientApp(ipAddress, portNumber);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
