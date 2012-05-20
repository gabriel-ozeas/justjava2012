package fourlinux.justjava;

import static org.junit.Assert.assertEquals;

import java.util.Properties;

import javax.annotation.Resource;
import javax.ejb.embeddable.EJBContainer;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.MessageConsumer;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.Session;
import javax.naming.Context;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;

import org.junit.Before;
import org.junit.Test;

public class BlogRegisterIT {
	@PersistenceContext(unitName = "justjava-persistenceunit")
	private EntityManager entityManager;

	@Resource(name = "BlogConfirmationQueue")
	private Queue queue;

	@Resource
	private ConnectionFactory connectionFactory;
	@Resource
	private UserTransaction userTransaction;
	
	private EJBContainer container;
	private Context context;

	private User user;

	@Before
	public void setUp() throws Exception {
		Properties props = new Properties();
		// Configurando datasource para persistência
		props.put("justJavaDatasource", "new://Resource?type=DataSource");
		props.put("justJavaDatasource.JdbcDriver", "org.hsqldb.jdbcDriver");
		props.put("justJavaDatasource.JdbcUrl", "jdbc:hsqldb:mem:justjavadb");

		props.put("BlogConfirmationQueue",
				"new://Resource?type=javax.jms.Queue");
		
		container = EJBContainer.createEJBContainer(props);
		context = container.getContext();
		context.bind("inject", this);

		user = new User();
		user.setName("Gabriel Ozeas");
		user.setEmail("gabriel.ozeas1@gmail.com");

		userTransaction.begin();
		entityManager.persist(user);
		userTransaction.commit();
	}

	@Test
	public void creatingContainerWithAPI() throws Exception {
		BlogInfo blog = new BlogInfo();
		blog.setName("Just Java Abouts");
		blog.setDescription("See what happens in Just Java");

		BlogRegister blogRegister = (BlogRegister) context
				.lookup("java:global/exemplo-openejb-1/BlogRegister");
		blogRegister.register(user.getId(), blog);

		Connection connection = connectionFactory.createConnection();
		connection.start();
		
		Session session = connection.createSession(false,
				Session.AUTO_ACKNOWLEDGE);
				
		MessageConsumer client = session.createConsumer(queue);
		ObjectMessage msg = (ObjectMessage) client.receive();
		
		BlogInfo blogPersisted = (BlogInfo) msg.getObject();
		assertEquals("Just Java Abouts", blogPersisted.getName());
		
		connection.close();
	}

	public void tearDown() throws Exception {
		container.close();
	}
}
