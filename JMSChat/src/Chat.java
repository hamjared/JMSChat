
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import javax.jms.ConnectionFactory;
import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.JMSProducer;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.TextMessage;
import javax.jms.Topic;

public class Chat implements Runnable {

	private List<String> chat;
	private static ConnectionFactory connectionFactory = null;
	private static Topic defaultTopic = null;


	public Chat() {
		chat = new ArrayList<>();
		connectionFactory = CommonSettings.getConnectionFactory();
		defaultTopic = CommonSettings.getDefautTopic();
	}

	public void sendMessage(String msg) {
		System.out.println("Sending Message");
		JMSContext jmsContext = connectionFactory.createContext();
		JMSProducer producer = jmsContext.createProducer();
		producer.send(defaultTopic, msg);
		System.out.println("Message sent");

	}

	public void receiveMessage(String msg) {

		if (msg == null) {
			System.out.println("String is null");
			return;
		}
		chat.add(msg);
		System.out.println(msg);
	}


	@Override
	public void run() {
		JMSContext jmsContext = connectionFactory.createContext();
		jmsContext.setClientID("exampleApp4" + Math.random());
		JMSConsumer consumer = jmsContext.createDurableConsumer(defaultTopic, "Test");
		Message msg;

		while (true) {

			msg =  consumer.receive(500);
			try {
				if (msg != null) {
					receiveMessage(msg.getBody(String.class));

				}
			} catch (JMSException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}
	
	
	public String toString() {
		String string = "";
		for (String line: chat) {
			string += line + "\n";
		}
		
		return string;
	}

	public static void main(String[] args) throws InterruptedException {
		Chat chat = new Chat();
		chat.sendMessage("Hello");
		Thread thread = new Thread(chat);
		thread.start();
		chat.sendMessage("Hello agin");
		chat.sendMessage("Hello again again");
		thread.join();
		
		

	}

}
