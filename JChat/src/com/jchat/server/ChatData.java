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
		if(chatData == null) {
			chatData = new ChatData();
		}
		
		return chatData;
	}
	
	public void addMessage(Message msg) {
		chat.add(msg);
	}
	
	public void broadCastChat() throws IOException {
		
		for (Socket con: connectedClients) {
			ObjectOutputStream outStream = new ObjectOutputStream(con.getOutputStream());
			outStream.writeObject(chat);
		}
	}

}
