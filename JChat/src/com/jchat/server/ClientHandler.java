package com.jchat.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.List;

import com.jchat.Message;

public class ClientHandler implements Runnable{
	
	Socket socket;
	DataInputStream dataIn;
	DataOutputStream dataOut;

	public ClientHandler(Socket socket) {
		this.socket = socket;
		try {
			this.dataIn = new DataInputStream( socket.getInputStream());
			this.dataOut = new DataOutputStream(socket.getOutputStream());
		} catch (IOException e) {
			System.out.println("Unable to create data in or out stream from socket: " + socket);
		}
		
		System.out.println("New thread for socket created!");
		ChatData.getInstance().addClient(socket);
		
	}

	@Override
	public void run() {
		ObjectInputStream inStream = null;
		System.out.println("Thread started");
		while(true) {
			
			try {
				inStream = new ObjectInputStream(dataIn);
			} catch (IOException e1) {
			
			}
			Object readObject = null;
			try {
				readObject = inStream.readObject();
				
			} catch (ClassNotFoundException e) {

			} catch (IOException e) {

			} 
			
			if (readObject != null && readObject instanceof Message) {
				Message msg = (Message) readObject;
				System.out.println(msg);
				ChatData.getInstance().addMessage(msg);
			}
			

		}
		
	}
}
