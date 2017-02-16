package network;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.*;
import java.util.Scanner;

import game.KeyboardInput;
import game.MouseInput;

public class Server extends Thread {

	private Sender sender;
	private Listener listener;

	public Server() {
		this.sender = null;
		this.listener = null;
	}

	public Listener listen(int port, KeyboardInput k, MouseInput m) {
		try {
			ServerSocket serverSocket = new ServerSocket(port);

			String name = getLocalName();

			System.out.println("To connect to this server use this name : " + name);
			System.out.println("Waiting for connection...");

			Socket clientSocket = serverSocket.accept();
			System.out.println("connection to " + clientSocket.getLocalAddress());
			this.sender = new Sender(new DataOutputStream(clientSocket.getOutputStream()));
			this.listener = new Listener(new DataInputStream(clientSocket.getInputStream()), k, m);
			sender.start();
			listener.start();
			return listener;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	public void addToQueue(Integer input) {

		sender.addToQueue(input);

	}

	private String getLocalName() {
		InetAddress addr = null;
		try {
			addr = InetAddress.getLocalHost();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		return addr.getHostName();
	}
}