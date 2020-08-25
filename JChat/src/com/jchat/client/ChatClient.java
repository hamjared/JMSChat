package com.jchat.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import com.jchat.Message;

public class ChatClient {
	
	Socket serverConnection;
	
	public ChatClient() {
		try {
			serverConnection = new Socket("127.0.0.1", 2603);
			System.out.println("Connected to server: " + serverConnection);
		} catch (IOException e) {
			System.out.println("Unable to connect to server");
		}
		
		
	}

	public void sendMessage(String string) {
		Message msg = new Message(string);
		try {
			ObjectOutputStream outStream = new ObjectOutputStream(serverConnection.getOutputStream());
			outStream.writeObject(msg);
		} catch (IOException e) {
			System.out.println("Could not send message");
			e.printStackTrace();
		}
		
	}

}
