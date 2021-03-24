package en.spring.config;

public class Productcart {
	
	private int soluong;
	private Productinfo productinfo;
	
	
	public int getSoluong() {
		return soluong;
	}
	public void setSoluong(int soluong) {
		this.soluong = soluong;
	}
	
	public Productinfo getProductinfo() {
		return productinfo;
	}
	public void setProductinfo(Productinfo productinfo) {
		this.productinfo = productinfo;
	}
	public int getAmount() {
		return productinfo.getPrice()*soluong;
	}
	
}
