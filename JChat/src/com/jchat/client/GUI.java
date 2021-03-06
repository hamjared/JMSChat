package com.jchat.client;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.jchat.Message;

import javax.swing.JTextPane;
import javax.swing.SwingWorker;
import javax.swing.JTextField;
import javax.swing.JButton;

public class GUI extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	JTextPane textPane;
	List<Message> chat;
	ChatClient chatClient;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
	

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI frame = new GUI();

					frame.setVisible(true);
					frame.addWindowListener(new WindowAdapter() {
						public void windowClosing(WindowEvent we) {
							frame.chatClient.closeConnection();
							System.exit(0);
						}
					});
//					thread.join();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		
	}

	/**
	 * Create the frame.
	 */
	public GUI() {

		chat = new ArrayList<>();
		chatClient = new ChatClient();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textPane = new JTextPane();
		textPane.setBounds(12, 12, 412, 185);
		contentPane.add(textPane);
		
		textField = new JTextField();
		textField.setBounds(12, 227, 241, 19);
		contentPane.add(textField);
		textField.setColumns(10);
		
		
		JButton btnNewButton = new JButton("Send");
		btnNewButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				sendMessage();

				
			}
			
		});
		btnNewButton.setBounds(278, 224, 117, 25);
		contentPane.add(btnNewButton);
		updatePane();
	}

	private void updateTextField() {
		
		
	}
	
	private void sendMessage() {
		chatClient.sendMessage(textField.getText());
	}
	
	private void updatePane() {
		SwingWorker worker = new SwingWorker() {

			@Override
			protected Object doInBackground() throws Exception {
				// TODO Auto-generated method stub
				while(true) {
					Message msg = chatClient.receiveIncomingMessage();
					if(msg!= null);
					chat.add(msg);
					textPane.setText(createTextPaneText());
					Thread.sleep(150);
				}
			}
			
		};
		
		worker.execute();
	}

	protected String createTextPaneText() {
		String s = "";
		for (Message m : chat) {
			s += m.toString() + "\n";
		}
		
		return s;
	}
	

}
