package bean;

public class Customers {
	private String customerId;
	private String lastName;
	private String firstName;
	private String email;
	private String accout;
	private String password;

	public Customers() {
	}

	public Customers(String customerId, String lastName, String firstName, String email, String accout,
			String password) {
		this.customerId = customerId;
		this.lastName = lastName;
		this.firstName = firstName;
		this.email = email;
		this.accout = accout;
		this.password = password;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAccout() {
		return accout;
	}

	public void setAccout(String accout) {
		this.accout = accout;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
