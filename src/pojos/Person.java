package pojos;

import java.sql.Date;

import javafx.scene.image.Image;

public abstract class Person {
	protected String username;
	protected String password;
	protected String email;
	protected String NIF;
	protected int mobilePhone;
	protected String name;
	protected String surname;
	protected Date dob;
	public enum GENDER {MALE, FEMALE};
	protected GENDER gender;
	protected byte[] photo;
	protected Address address;
	protected Integer ID;
	
	@SuppressWarnings("deprecation")
	public Person() {
		this.username = null;
		this.password = null;
		this.email = null;
		this.mobilePhone = 0;
		this.name = null;
		this.surname = null;
		this.dob = new Date(1,1,1);
		this.gender = null;
		this.address = new Address();
		this.setID(0);
		this.photo = new byte[0];
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername (String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public int getMobilePhone() {
		return mobilePhone;
	}
	
	public void setMobilePhone(int mobilePhone) {
		this.mobilePhone = mobilePhone;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getSurname() {
		return surname;
	}
	
	public void setSurname(String surname) {
		this.surname = surname;
	}
	
	public Date getDob() {
		return dob;
	}
	
	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getNIF() {
		return NIF;
	}

	public void setNIF(String NIF) {
		this.NIF = NIF;
	}

	public GENDER getGender() {
		return gender;
	}

	public void setGender(GENDER gender) {
		this.gender = gender;
	}

	public byte[] getPhoto() {
		return photo;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public  int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}
}
