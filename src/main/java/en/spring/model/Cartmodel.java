package en.spring.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
@Entity
public class Cartmodel {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@Column(name = "cartid")
	private int Cartid;
	@Column(name = "cartname")
	private String Cartname;
	@Column(name = "cartprice")
	private int Cartprice;
	@Column(name = "quantity")
	private int Quantity;
	public Cartmodel() {}
	public Cartmodel(int Cartid, String Cartname, int Cartprice, int Quantity) {
		this.Cartid = Cartid;
		this.Cartname = Cartname;
		this.Cartprice = Cartprice;
		this.Quantity = Quantity;
	}
	@ManyToOne
	@JoinColumn(name="ordercartid")
	private Ordercart ordercart;
	public int getCartid() {
		return Cartid;
	}
	public void setCartid(int cartid) {
		Cartid = cartid;
	}
	public String getCartname() {
		return Cartname;
	}
	public void setCartname(String cartname) {
		Cartname = cartname;
	}
	public int getCartprice() {
		return Cartprice;
	}
	public void setCartprice(int cartprice) {
		Cartprice = cartprice;
	}
	public int getQuantity() {
		return Quantity;
	}
	public void setQuantity(int quantity) {
		Quantity = quantity;
	}
	public Ordercart getOrdercart() {
		return ordercart;
	}
	public void setOrdercart(Ordercart ordercart) {
		this.ordercart=ordercart;
	}
}
