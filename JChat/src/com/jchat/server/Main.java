package com.jchat.server;

public class Main {
	public static void main(String[] args) {
		ChatServer server = new ChatServer();
		server.acceptConnections();
	}
}
