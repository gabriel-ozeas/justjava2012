package fourlinux.justjava;


public class BlogException extends Exception {

	public BlogException() {
	}

	public BlogException(String msg) {
		super(msg);
	}

	public BlogException(String msg, Exception e) {
		super(msg, e);
	}

}
