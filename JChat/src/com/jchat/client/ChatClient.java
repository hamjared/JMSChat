package com.jchat.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import com.jchat.Message;
import com.jchat.server.ChatData;

public class ChatClient implements Runnable{
	
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
	
	public Message receiveIncomingMessage() throws IOException {
		ObjectInputStream inStream = new ObjectInputStream(serverConnection.getInputStream());
		Object readObject = null;
		try {
			readObject = inStream.readObject();
			
		} catch (ClassNotFoundException e) {

		} catch (IOException e) {

		} 
		
		if (readObject != null && readObject instanceof Message) {
			Message msg = (Message) readObject;
			return msg;
			
		}
		
		return null;
			
		
	}

	@Override
	public void run() {
		while (true) {
			try {
				this.receiveIncomingMessage();
			} catch (IOException e) {
				
			}
		}
		
		
	}

	public void closeConnection() {
		try {
			this.serverConnection.close();
		} catch (IOException e) {
			System.out.println("Error closing server connection");
		}
		
	}

}
