package pojos;

public class Doctor extends Person {

	private Integer primarykey;

	public enum SPECIALITY {
		ALLERGY_IMMUNOLLOGY, GENERAL_PATHOLOGY, CARDIOLOGY, CLINICAL_NEUROPHISIOLOGY,
		ENDOCRINOLOGY, GENERAL_PRACTICE, INTERNAL_MEDICINE, NEPHROLOGY, NEUROLOGY, OPHTHALMOLOGY, 
		ORTHOPAEDICS, PAEDIATRICS, NEONATOLOGY, PHYSICAL_MEDICINE_REHABILITATION, PULMONOLOGY, 
		PSYCHIATRY, RADIOLOGY, GENERAL_SURGERY, UROLOGY
	};

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
}
