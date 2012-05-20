package fourlinux.justjava;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.Session;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class BlogRegister {
	@PersistenceContext(unitName="justjava-persistenceunit")
	private EntityManager entityManager;
	
	@Resource(name="BlogConfirmationQueue")
	private Queue blogConfirmationQueue;
	
	@Resource
	private ConnectionFactory connectionFactory;
	
	public boolean register(Long userId, BlogInfo blogInfo) throws BlogException, UserException {
		User user = entityManager.find(User.class, userId);
		if(user == null) {
			throw new UserException("User not found.");
		}
		
		persisteBlogInfo(blogInfo, user);
		sendBlogCreateConfirmation(blogInfo);
		return true;
	}

	private void persisteBlogInfo(BlogInfo blogInfo, User user) {
		// Adicionando blog ao usuário
		user.addBlog(blogInfo);
		blogInfo.setUser(user);
		entityManager.merge(user);
	}

	private void sendBlogCreateConfirmation(BlogInfo blogInfo)
			throws BlogException {
		try {
			Connection conn = connectionFactory.createConnection();
			Session session = conn.createSession(false, Session.AUTO_ACKNOWLEDGE);
			MessageProducer sender = session.createProducer(blogConfirmationQueue);
			ObjectMessage msg = session.createObjectMessage();
			msg.setObject(blogInfo);
			sender.send(msg);
		} catch(JMSException e) {
			throw new BlogException("Cannot create blog create confirmation request.", e);
		}
	}
	
}
