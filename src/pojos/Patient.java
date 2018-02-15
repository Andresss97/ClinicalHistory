package pojos;

public class Patient extends Person{
	private float weight;
	private float height;
	private int housePhone;
	private String addictions;
	
	public Patient() {
		super();
		this.weight = 0.0F;
		this.height = 0.0F;
		this.housePhone = 0;
		this.addictions = null;
	}

	public float getWeight() {
		return weight;
	}

	public void setWeight(float weight) {
		this.weight = weight;
	}

	public float getHeight() {
		return height;
	}

	public void setHeight(float height) {
		this.height = height;
	}

	public int getHousePhone() {
		return housePhone;
	}

	public void setHousePhone(int housePhone) {
		this.housePhone = housePhone;
	}

	public String getAddictions() {
		return addictions;
	}

	public void setAddictions(String addictions) {
		this.addictions = addictions;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((addictions == null) ? 0 : addictions.hashCode());
		result = prime * result + Float.floatToIntBits(height);
		result = prime * result + housePhone;
		result = prime * result + Float.floatToIntBits(weight);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Patient other = (Patient) obj;
		if (addictions == null) {
			if (other.addictions != null)
				return false;
		} else if (!addictions.equals(other.addictions))
			return false;
		if (Float.floatToIntBits(height) != Float.floatToIntBits(other.height))
			return false;
		if (housePhone != other.housePhone)
			return false;
		if (Float.floatToIntBits(weight) != Float.floatToIntBits(other.weight))
			return false;
		return true;
	}
	
	
}
