package en.spring.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Ordercart")
public class Ordercart {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private long Id;
	private String Account;
	private String Name;
	private int Phone;
	private String Address;
	private String Province;
	public Ordercart() {}
	public Ordercart(String Account, String Name, int Phone, String Address, String Province) {
		this.Account=Account;
		this.Name=Name;
		this.Phone = Phone;
		this.Address = Address;
		this.Province = Province;
	}
	public String getAccount() {
		return Account;
	}
	public void setAccount(String  Account) {
		this.Account=Account;
	}
	
	public int getPhone() {
		return Phone;
	}
	public void setPhone(int phone) {
		Phone = phone;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getAddress() {
		return Address;
	}
	public void setAddress(String address) {
		Address = address;
	}
	public String getProvince() {
		return Province;
	}
	public void setProvince(String province) {
		Province = province;
	}
}
