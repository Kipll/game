package network;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import game.ClientWindow;

public class Client {

	private Sender sender;
	private Listener listener;
	private Socket server;

	private ClientWindow window;

	/**
	 * 
	 */
	public Client() {
		this.sender = null;
		this.listener = null;
		this.server = null;
		this.window = null;
	}

	/**
	 * main method for the class, the client will try to connect to a specified
	 * server socket, and open input and output streams to it
	 * 
	 * @param port
	 *            The port number you wish to connect to
	 * @param name
	 *            The name of the server on the network
	 * @return returns true if a connection is successfully established. False
	 *         otherwise.
	 */
	public boolean connect(String name, int port) {
		try {

			this.server = new Socket(name, port);
			sender = new Sender(new DataOutputStream(server.getOutputStream()));
			listener = new Listener(new DataInputStream(server.getInputStream()), null, null);// needs
																								// to
																								// be
																								// changed.
			sender.start();
			listener.start();
			window = new ClientWindow(sender);
			return true;
		} catch (IOException e) {
			System.out.println("couldnt connect to specified host, connection refused.");
			e.printStackTrace();
			return false;
		}
	}

	public void addToQueue(Integer input) {

		sender.addToQueue(input);

	}

	public static void main(String[] args) {
		Client c = new Client();
		c.connect(args[0], Integer.parseInt(args[1]));
	}

}

