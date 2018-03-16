package pojos;

public class Address {
	private int postalCode;
	private String city;
	private String street;
	private int houseNumber;
	private Integer ID;
	private Patient patient;
	private Doctor doctor;
	
	public Address() {
		this.postalCode = 0;
		this.city = null;
		this.street = null;
		this.houseNumber = 0;
	}
	
	public int getID() {
		return this.ID;
	}
	
	public void setID(int id) {
		this.ID=id;
	}
	
	public Patient getPatient() {
		return this.patient;
	}
	
	public void setPatient(Patient patient) {
		this.patient = patient;
	}
	
	public Doctor getDoctor() {
		return this.doctor;
	}
	
	public void setDoctor(Doctor doctor) {
		this.doctor=doctor;
	}
	
	public int getPostalCode() {
		return postalCode;
	}
	
	public void setPostalCode(int postalCode) {
		this.postalCode = postalCode;
	}
	
	public String getCity() {
		return city;
	}
	
	public void setCity(String city) {
		this.city = city;
	}
	
	public String getStreet() {
		return street;
	}
	
	public void setStreet(String street) {
		this.street = street;
	}
	
	public int getHouseNumber() {
		return houseNumber;
	}
	
	public void setHouseNumber(int houseNumber) {
		this.houseNumber = houseNumber;
	}
}
