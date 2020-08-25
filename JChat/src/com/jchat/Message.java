package com.jchat;

import java.io.Serializable;

public class Message implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String msg;
	
	public Message(String msg) {
		this.msg = msg;
	}
	
	public String toString() {
		return msg;
	}

}
