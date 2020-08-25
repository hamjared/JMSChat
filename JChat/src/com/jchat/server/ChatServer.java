package com.jchat.server;


import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

public class ChatServer {

	ServerSocket connectionListener;
	List<Socket> currentConnections;

	public ChatServer() {
		
		try {
			connectionListener = new ServerSocket();
			connectionListener.setReuseAddress(true);
			connectionListener.bind(new InetSocketAddress(2603));
		} catch (IOException e) {
			System.out.println("Unable to create sokcet on port 2603");
		}
	}

	public void acceptConnections() {
		System.out.println("Waiting for connections...");
		while (true) {
			Socket s = null;

			try {
				s = connectionListener.accept();
				System.out.println("Connection accepeted: " + s);
				ClientHandler handler = new ClientHandler(s);
				new Thread(handler).start();
			} catch (IOException e) {
				try {
					s.close();
				} catch (IOException e1) {

				}
			}

		}
	}

}
