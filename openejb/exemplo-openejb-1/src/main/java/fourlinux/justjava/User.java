package fourlinux.justjava;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class User implements Serializable {
	@Id @GeneratedValue
	private Long id;
	private String name;
	private String email;
	@OneToMany(mappedBy = "user")
	private List<BlogInfo> blogs = new ArrayList<BlogInfo>();
	
	public void addBlog(BlogInfo blog)  {
		blogs.add(blog);
	}
	
	public BlogInfo removeBlog(BlogInfo blog) {
		if (blogs.remove(blog)) 
			return blog;
		else 
			return null;
	}
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public List<BlogInfo> getBlogs() {
		return blogs;
	}
	public void setBlogs(List<BlogInfo> blogs) {
		this.blogs = blogs;
	}
}
