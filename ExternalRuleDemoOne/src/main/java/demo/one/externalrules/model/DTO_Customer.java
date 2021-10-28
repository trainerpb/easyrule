package demo.one.externalrules.model;

public class DTO_Customer {
	private String id;
	private String name;
	private double cartValue;
	private int age;
	private String gender;
	private String occupation;

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

	public double getCartValue() {
		return cartValue;
	}

	public void setCartValue(double cartValue) {
		this.cartValue = cartValue;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	public DTO_Customer(String id, String name, double cartValue, int age, String gender, String occupation) {
		super();
		this.id = id;
		this.name = name;
		this.cartValue = cartValue;
		this.age = age;
		this.gender = gender;
		this.occupation = occupation;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return String.format("%s was given discount", name);
	}

}
