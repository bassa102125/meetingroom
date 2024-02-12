package bean;

public class UserBean  {
//フィールド---------------------
	private static final long serialVersionUID = 1l;
	private String address;
	private String id;
	private String name;
	private String password;
//コンストラクタ-----------------
	public UserBean() {}
	public UserBean(String address, String id, String name, String password) {
		this.address = address;
		this.id = id;
		this.name = name;
		this.password = password;
	}
//メソッド-----------------------
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String toString() {
		return name;
	}

}
