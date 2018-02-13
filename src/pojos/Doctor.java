package pojos;

public class Doctor extends Person{
	public enum SPECIALITY {ALLERGY_IMMUNOLLOGY, GENERAL_PATHOLOGY,CARDIOLOGY,CLINICAL_NEUROPHISIOLOGY,ENDOCRINOLOGY,GENERAL_PRACTICE,
		INTERNAL_MEDICINE,NEPHROLOGY,NEUROLOGY,OPHTHALMOLOGY,ORTHOPAEDICS,PAEDIATRICS,NEONATOLOGY,PHYSICAL_MEDICINE_REHABILITATION,
		PULMONOLOGY,PSYCHIATRY,RADIOLOGY,GENERAL_SURGERY,UROLOGY};
	
	private SPECIALITY speciality;
	
	public Doctor() {
		super();
		this.speciality = null;
	}

	public SPECIALITY getSpeciality() {
		return speciality;
	}

	public void setSpeciality(SPECIALITY speciality) {
		this.speciality = speciality;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((speciality == null) ? 0 : speciality.hashCode());
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
		Doctor other = (Doctor) obj;
		if (speciality != other.speciality)
			return false;
		return true;
	}
}
