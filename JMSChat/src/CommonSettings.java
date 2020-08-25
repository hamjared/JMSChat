import javax.jms.ConnectionFactory;
import javax.jms.Queue;
import javax.jms.Topic;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class CommonSettings {
	private static ConnectionFactory CONNECTION_FACTORY = null;
	private static Queue PTP_QUEUE = null;
	private static Topic PUB_SUB_TOPIC = null;
	private static Queue DEFAULT_REPLY_QUEUE = null;
	private static Topic DEFAULT_MESSAGE_TOPIC = null;

	static {
		try {
			System.setProperty("org.omg.CORBA.ORBInitialHost", "13.93.220.113");
			System.setProperty("org.omg.CORBA.ORBInitialPort", "3700");
//			System.setProperty("java.naming.provider.url", "jndi://10.0.0.85:7676");
			InitialContext initialContext = new InitialContext();
			CONNECTION_FACTORY = (ConnectionFactory) initialContext.lookup("jms/RemoteConnectionFactory");
			PTP_QUEUE = (Queue) initialContext.lookup("jms/PTPQueue");
			DEFAULT_REPLY_QUEUE = (Queue) initialContext.lookup("jms/ReplyQueue");
			PUB_SUB_TOPIC = (Topic) initialContext.lookup("jms/PubSubTopic");
			DEFAULT_MESSAGE_TOPIC = (Topic) initialContext.lookup("jms/MessageQueue");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	public static ConnectionFactory getConnectionFactory() {
		return CONNECTION_FACTORY;
	}

	public static Queue getDefaultQueue() {
		return PTP_QUEUE;
	}

	public static Queue getDefaultReplyQueue() {
		return DEFAULT_REPLY_QUEUE;
	}

	public static Topic getDefautTopic() {
		return PUB_SUB_TOPIC;
	}

	public static Topic getMessageTopic() {
		return DEFAULT_MESSAGE_TOPIC;
	}
}