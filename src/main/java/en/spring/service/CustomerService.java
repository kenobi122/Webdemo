package en.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import en.spring.model.Cartmodel;
import en.spring.model.Customer;
import en.spring.model.Ordercart;
import en.spring.model.Product;
import en.spring.model.Role;
import en.spring.model.Userinfo;
import en.spring.repository.CartmodelRepository;
import en.spring.repository.CustomerRepository;
import en.spring.repository.OrderCartRepository;
import en.spring.repository.ProductRepository;
import en.spring.repository.RoleRepository;
import en.spring.repository.UserinfoRepository;
import en.spring.service.*;

@Service
public class CustomerService implements UserDetailsService {

	@Autowired 
	private CustomerRepository repo;
	@Autowired
	private RoleRepository reporole;
	@Autowired
	private PasswordEncoder passencoder;
	@Autowired
	private ProductRepository prorepo;
	@Autowired
	private UserinfoRepository userinforepo;
	@Autowired
	private CartmodelRepository cartmodelrepo;
	@Autowired
	private OrderCartRepository ordercartrepo;
	
	@Override
	@Transactional
	public UserDetails loadUserByUsername(String account) throws UsernameNotFoundException{
		Customer customer = repo.findByAccount(account);
		if(customer==null) {
			throw new UsernameNotFoundException("Customer not found");
			
		}
		Set<GrantedAuthority> grantedauthority = new HashSet<>();
		Set<Role> roles = customer.getRoles();
		for(Role role : roles) {
			grantedauthority.add(new SimpleGrantedAuthority(role.getName()));
		}
		return new org.springframework.security.core.userdetails.User(customer.getAccount(), customer.getPassword() , grantedauthority);
		
	}
	public void SaveCustomer(Customer customer) {
		final String encryptpassword = passencoder.encode(customer.getPassword());
		
		customer.setPassword(encryptpassword);
		HashSet<Role> role = new HashSet<>();
		role.add(reporole.findByName("ROLE_CUSTOMER"));
		customer.setRoles(role);
		
		
		repo.saveAndFlush(customer);
	}
	
	public Customer find(String name) {
		
		return repo.findByAccount(name);
		
	}
	public List<Product> findProduct(){
		return prorepo.findAll();
	}
	/// nhap userinfo
	public void saveinfo(Userinfo userinfo) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String account;
		if (principal instanceof UserDetails) {
		     account = ((UserDetails) principal).getUsername();
		    
		} else {
		    account = principal.toString();
		}
		Userinfo info = userinforepo.findByAccount(account);
		if(info!= null) {
			info.setName(userinfo.getName());
			info.setPhone(userinfo.getPhone());
			info.setAddress(userinfo.getAddress());
			info.setProvince(userinfo.getProvince());
			userinforepo.save(info);
		}else {
			
			userinfo.setAccount(account);
			userinforepo.save(userinfo);
		}
		
	}
	//create ỏder
	public void createorder(Ordercart ordercart) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String account;
		if (principal instanceof UserDetails) {
		     account = ((UserDetails) principal).getUsername();
		    
		} else {
		    account = principal.toString();
		}
		Userinfo info = userinforepo.findByAccount(account);
		ordercart.setAccount(account);
		ordercart.setAddress(info.getAddress());
		ordercart.setName(info.getName());
		ordercart.setPhone(info.getPhone());
		ordercart.setProvince(info.getProvince());
		ordercartrepo.save(ordercart);
	}
	//save cart
	public void savecart(Cartmodel cartmodel) {
		
		cartmodelrepo.save(cartmodel);
	}
	
	
	
}

