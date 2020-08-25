package com.jchat.client;

public class Main {
	public static void main(String[] args) throws InterruptedException {
		ChatClient client = new ChatClient();
		Thread thread = new Thread(client);
		thread.start();
		
		client.sendMessage("Hello from Client");
		
		Thread.sleep(5000);
		client.sendMessage("Hello again from the client");
		
		thread.join();
		
		client.closeConnection();
		
	}
}
