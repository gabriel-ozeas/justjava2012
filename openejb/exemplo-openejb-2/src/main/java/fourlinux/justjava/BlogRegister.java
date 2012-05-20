package fourlinux.justjava;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

@Stateless
@LocalBean
public class BlogRegister {
	public boolean register(User user, BlogInfo blogInfo) throws BlogException, UserException {
		return true;
	}
}
