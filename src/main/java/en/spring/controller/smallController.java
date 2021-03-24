package en.spring.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashSet;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.mapping.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import en.spring.model.Product;
import en.spring.repository.ProductRepository;

import java.io.File;
@Controller
public class smallController {
	private static final String UPLOAD_DIRECTORY ="/image"; 
	private static final String HOME="user.home";
	private static final String HOME_PATH=System.getProperty(HOME);
	private static final String IMAGE_PATH = HOME_PATH + File.separator + UPLOAD_DIRECTORY;

	private static final File IMAGE_DIR = new File(IMAGE_PATH);
	private static final String IMAGE_PRODUCT_PATH = IMAGE_DIR.getAbsolutePath() + File.separator;
	@Autowired 
	private ProductRepository prorepo;
	@RequestMapping(value="/index", method = RequestMethod.GET)
	public String addproduct(Model model) {
		model.addAttribute("product", new Product());
		return "index";
	}
	@RequestMapping(value="/savefile", method = RequestMethod.POST)
	public String addproduct(@ModelAttribute("product") Product product, @RequestParam("file") MultipartFile file) {
		
		String fileName = file.getOriginalFilename();
		if(file.isEmpty()){
			  
				return "index";		
			}else{
				Createfolder();
				prorepo.save(product);
				String name = product.getName();
				 StringBuilder sb = new StringBuilder(name);
				 sb.append(".jpg");
				CreateImage(sb, file);
				return "index";
				
			}
		
	}
	

	private void Createfolder() {
		if(!IMAGE_DIR.exists()) {
			IMAGE_DIR.mkdirs();
		}
	}
	private String CreateImage(StringBuilder sb, MultipartFile file) {
		try {
		File image = new File(IMAGE_PRODUCT_PATH + sb );
		BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(image));
		stream.write(file.getBytes());
		stream.flush();
		stream.close();
		return String.format("upload thanh cong ", sb);
		}catch(Exception e) {
			return String.format("upload : ", sb, e.getMessage());
		}
	}
	 	@RequestMapping(value = "/image/{imageName}")
	    @ResponseBody
	    public byte[] getImage(@PathVariable(value = "imageName") String imageName) throws IOException {
	        Createfolder();

	        File serverFile = new File(IMAGE_PRODUCT_PATH + imageName + ".jpg");

	        return Files.readAllBytes(serverFile.toPath());
	    }
	 	
	 	
	
}	
