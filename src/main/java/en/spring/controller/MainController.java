package en.spring.controller;




import java.util.Arrays;
import javax.servlet.http.HttpSession;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import en.spring.model.Cartmodel;
import en.spring.model.Customer;
import en.spring.model.Ordercart;
import en.spring.model.Product;
import en.spring.model.Userinfo;
import en.spring.service.CustomerService;

@Controller
public class MainController {
	 @Autowired
	private CustomerService customerservice;
	
	/*dang ki
	*
	*/ 
	@RequestMapping(value="/redic", method=RequestMethod.GET)
	public String showNewProductPage(Model model) {
	    model.addAttribute("customer1", new Customer());
	    return "Signup";
	}
	/// lưu tài khoản
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public String add( @ModelAttribute("customer1") @Valid Customer customer, BindingResult bindingResult) {
		
		Customer customerexist = customerservice.find(customer.getAccount());
		if(bindingResult.hasErrors()) {
			return "Signup";
			
		}else {
			customerservice.SaveCustomer(customer);
			return "redirect:/home";
		}
	}
	// trang sản phẩm
	@RequestMapping("/product")
	public String product(Model model, HttpSession session) {
		
		
		List<Product> listproduct = customerservice.findProduct();
		model.addAttribute("listproduct", listproduct);
		
		return "product.html";
 	}
	
	
	// trỏ về hom
	@RequestMapping(value= "/home", method = RequestMethod.GET)
	public String home() {

		return "home";
	}
	// trang login
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	//logout
	@GetMapping("/logout")
	public String logout(HttpServletRequest rq, HttpServletResponse res) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if(auth!= null) {
			new SecurityContextLogoutHandler().logout(rq, res, auth);
		}
		return "redirect:/home";
	}
	@GetMapping("/user1")
	public String user(Model model) {
		
		return "User";
	}
	
	@GetMapping("/403")
	public String error() {
		return "403";
	}
	@GetMapping("/menu")
	public String menu() {
		return "menu";
	}
	@GetMapping("/cart")
	public String cart(Model model, HttpSession session) {
		
		return "cart";
	}
	/// nhap thong tin username mua hàng
	@GetMapping("/User")
	public String checkout(Model model) {
			model.addAttribute("userinfo", new Userinfo());
			List<String> listProvince = Arrays.asList("An Giang","Bà Rịa - Vũng Tàu","Bắc Giang","Bắc Kạn","Cần Thơ","Đà Nẵng","Hà Nội","Hải Phòng","Nghệ An");
			model.addAttribute("listProvince" , listProvince);
			return "Userinfo";
		}
	//lưu userinfo
	@RequestMapping(value="/info", method= RequestMethod.POST)
	public String nhapuser( Userinfo userinfo, Model model) {
		
		customerservice.saveinfo(userinfo);
		model.addAttribute("userinfo", userinfo);
		
		return "Checkout";
		
	}
	// checkout
	@RequestMapping(value= "cart/sendcart", method =RequestMethod.POST,headers ="content-type=application/json")
	public @ResponseBody void sendcart(@RequestBody List<Cartmodel> c) {
		Ordercart ordercart = new Ordercart();
		customerservice.createorder(ordercart);
		
		for(Cartmodel c1 : c) {
			c1.setOrdercart(ordercart);
			customerservice.savecart(c1);
		}
		
	}
	// send check out
	@GetMapping("/sendcheckout")
	public String finallycheckout(Model model){
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String account;
		if (principal instanceof UserDetails) {
		     account = ((UserDetails) principal).getUsername();
		    
		} else {
		    account = principal.toString();
		}
		model.addAttribute("kig", account);
		return "successcart";
	}
	
	
}
	
