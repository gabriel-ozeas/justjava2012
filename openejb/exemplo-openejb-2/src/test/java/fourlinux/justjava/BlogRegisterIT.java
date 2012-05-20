package fourlinux.justjava;

import javax.ejb.embeddable.EJBContainer;
import javax.naming.Context;

import org.junit.Test;

public class BlogRegisterIT {
	
	@Test
	public void creatingContainerWithAPI() throws Exception {
		EJBContainer container = EJBContainer.createEJBContainer();
		Context context = container.getContext();
		BlogRegister blogRegister = (BlogRegister) context.lookup("java:global/exemplo-openejb-2/BlogRegister");
		container.close();
	}
}
