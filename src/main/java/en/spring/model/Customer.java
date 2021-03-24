package en.spring.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import javax.persistence.*;


import javax.validation.constraints.*;

import org.hibernate.validator.constraints.Length;


@Entity
@Table(name="customer")
public class Customer{
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
   @Length(min=3, max=30,message = "Username must be between 3 and 20 characters long")
   @NotEmpty(message = "not blank")
   @Column(name="account")
    private String account;
    
   	@NotEmpty(message = "not blank")
    @Email(message = "Please enter a valid e-mail address")
   	@Column(name="email")
    private String email;
    
   
   	@NotEmpty
   	@Column(name="password")
    private String password;
    
    
    public Customer() {}

   
    public String getAccount() {
    	return account;
    }
    public void setAccount(String account) {
    	this.account=account;
    }
    public String getEmail() {
    	return email;
    }
    public void setEmail(String email) {
    	this.email=email;
    }
    
	@Override 
	public String toString() {
		return "Customer[id="+id +"name=" + account +"email=" +email + "password" + password +"]";
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	@ManyToMany(cascade = CascadeType.MERGE)
	@JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles;
	public Set<Role> getRoles() {
    	return roles;
    }
    public void setRoles(Set<Role> roles) {
    	this.roles=roles;
    }
}

