package com.jchat.server;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import com.jchat.Message;

public class ChatData {

	static ChatData chatData;
	List<Socket> connectedClients;
	List<Message> chat;

	private ChatData() {
		connectedClients = new ArrayList<>();
		chat = new ArrayList<>();
	}

	public static ChatData getInstance() {
		if (chatData == null) {
			chatData = new ChatData();
		}

		return chatData;
	}

	public void addMessage(Message msg) {
		System.out.println("Added message to chat");
		chat.add(msg);
		System.out.println("Broadcasting message...");
		this.broadcastNewMessage(msg);
	}

	public void broadcastNewMessage(Message msg) {

		System.out.println("Connected Clients = " + connectedClients);
		for (Socket con : connectedClients) {

			try {
				if (con.isClosed()) {
					connectedClients.remove(con);
					continue;
				}
				ObjectOutputStream outStream = new ObjectOutputStream(con.getOutputStream());
				outStream.writeObject(msg);
				System.out.println("Message returned to client");
			} catch (IOException e) {
				System.out.println("Message failed to broadcast :");
				
			}
		}

	}

	public void addClient(Socket socket) {
		this.connectedClients.add(socket);

	}

}
