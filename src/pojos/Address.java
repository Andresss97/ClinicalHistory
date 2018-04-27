package pojos;
import java.io.*;
import javax.persistence.*;
import javax.xml.bind.annotation.*;


@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table (name = "ADDRESS")
public class Address implements Serializable {
	
	@XmlElement
	@Column(name = "cp")
	private int postalCode;
	
	@XmlElement
	private String city;
	
	@XmlElement
	private String street;
	
	@XmlElement
	private int houseNumber;
	
	
	@XmlAttribute
	@Id
	@GeneratedValue (generator = "ADDRESS")
	@TableGenerator (name = "ADDRESS", table = "sqlite_sequence", 
	pkColumnName = "name", valueColumnName = "seq", pkColumnValue = "ADDRESS")
	private Integer ID;

	@XmlTransient
	@OneToOne (fetch = FetchType.LAZY, mappedBy = "address")
	private Person person;
	
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
				
	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
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

	@Override
	public String toString() {
		return "Address [postalCode=" + postalCode + ", city=" + city + ", street=" + street + ", houseNumber="
				+ houseNumber + ", ID=" + ID + "]";
	}
}
