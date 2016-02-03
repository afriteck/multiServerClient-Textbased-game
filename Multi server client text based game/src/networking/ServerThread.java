package networking;

import java.net.*;

import org.apache.log4j.Logger;

import player.Player;

import java.io.*;

public class ServerThread extends Thread {
	
	static final Logger logger 				= Logger.getLogger("serverLogger");

	private int ID = -1;
	private Socket socket = null;
	private ServerApp server = null;
	private BufferedReader streamIn = null;
	private BufferedWriter streamOut = null;
	private ObjectOutputStream outputStream = null;
	private ObjectInputStream inStream = null;
	
	private String clientAddress = null;

	private boolean done = false;

	public ServerThread(ServerApp server, Socket socket) {
		super();
		this.server = server;
		this.socket = socket;
		this.ID = socket.getPort();
		this.clientAddress = socket.getInetAddress().getHostAddress();
	}

	public int getID() {
		return this.ID;
	}
	
	public BufferedWriter buff() {
		return streamOut;
	}
	
	public BufferedReader getInStream() {
		return streamIn;
	}

	public String getSocketAddress () {
		return clientAddress;
	}
	
	/** The server processes the messages and passes it to the client to send it */
	public void send(String input) {
		try {
			streamOut.write(input);
			streamOut.flush();
		} catch (IOException ioe) {
			System.out.println(ioe.getMessage());
			logger.error(ioe.getMessage());
			server.remove(ID);
		}
	}
	
	/** Send message object */
	public void sendObject(Object input) {
		try {
			outputStream.writeObject(input);
			outputStream.flush();
		} catch(IOException e) {
			System.out.println(e.getMessage());
			logger.error(e.getMessage());
			server.remove(ID);
		}
	}
	
	/** Receive object sent */
	public Object reciveObject(Object input) {
		
		Object obj = null;
		
		try {
			obj = (Object) inStream.readObject();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return obj;
	}

	/** server thread that listens for incoming message from the client
	 * on the assigned port 
	 * */
	public void run() {
		System.out.println("Server Thread Running" +  ID);
		logger.info("Server Thread Running" +  ID);
		while (!done) {
			try {
				/** Received a message and pass to the server to handle */
				server.handle(ID, streamIn.readLine());
			} catch (IOException ioe) {
				System.out.println(ioe.getMessage());
				server.remove(ID);
				break;
			}}
	}

	public void open() throws IOException {
		streamIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		streamOut = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
	}

	public void close() {
		try {
			if (socket != null) socket.close();
			if (streamIn != null) streamIn.close();
			
			this.done = true;
			this.socket = null;
			this.streamIn = null;
		} catch (IOException e) { }
	}

}