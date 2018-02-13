package pojos;

import java.sql.Date;

public abstract class Person {
	private String user;
	private String password;
	private String email;
	private String NIF;
	private int mobilePhone;
	private String name;
	private String surname;
	private Date dob;
	public enum GENDER {MALE, FEMALE};
	private GENDER gender;
	private byte photo;
	
	@SuppressWarnings("deprecation")
	public Person() {
		this.user = null;
		this.password = null;
		this.email = null;
		this.mobilePhone = 0;
		this.name = null;
		this.surname = null;
		this.dob = new Date(1,1,1);
		this.gender = null;
	}
	
	public String getUser() {
		return user;
	}
	
	public void setUser(String user) {
		this.user = user;
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

	public byte getPhoto() {
		return photo;
	}

	public void setPhoto(byte photo) {
		this.photo = photo;
	}
}
