/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
    Created by Andrés Daniel de Pereda Cruz
 */
package creation;

import java.io.File;
import java.sql.*;

/**
 *
 * @author andre
 */
public abstract class DBCreation {    
	
	public static void createDB() {
		Conector con = new Conector();
		if(con.conectar() == true) {
			cTAddress();
			cTAllergies();
			cTDoctor();
			cTMapping();
			cTPatient();
			cTSurgeries();
			cTTreatment();
			cTVaccine();
			cTIllnesses();
		}
		else {
			//Aqui va un JOptionPane con un error
		}
	}
    
    private static void cTAddress() {
        Conector con = new Conector();
        Statement st = null;
        String in = null;
        
        try {
            con.conectar();
            st = con.getConnect().createStatement();
            
            in = "CREATE TABLE ADDRESS "
                    + "(ID int NOT NULL UNIQUE,"
                    + "CITY varchar(50),"
                    + "STREET varchar(50),"
                    + "CP bigint,"
                    + "HOUSENUMBER bigint,"
                    + "PRIMARY KEY (ID))";
            
            st.execute(in);
            st.close();
        }
        catch(Exception ex) {
            
        }
        finally {
            con.killConnection();
        }
    }
    
    private static void cTAllergies() {
        Conector con = new Conector();
        Statement st = null;
        String in = null;
        
        try {
            con.conectar();
            st = con.getConnect().createStatement();
            
            in = "CREATE TABLE ALLERGIES"
                    + "(ID int NOT NULL UNIQUE,"
                    + "GROUP varchar(50),"
                    + "OBSERVATIONS text,"
                    + "IDPATIENT int CONSTRAINT rPatient REFERENCES PATIENT (ID),"
                    + "IDTREATMENT int CONSTRAINT rTreatment REFERENCES TREATMENT (ID),"
                    + "PRIMARY KEY(ID))";
            
            st.execute(in);
            st.close();
        }
        catch(Exception ex) {
            System.out.println(ex.getMessage());
        }
        finally {
            con.killConnection();
        }
    }
    
    private static void cTDoctor() {
        Conector con = new Conector();
        Statement st = null;
        String in = null;
        
        try {
            con.conectar();
            st = con.getConnect().createStatement();
            
            in = "CREATE TABLE DOCTOR"
                    + "(ID int NOT NULL UNIQUE,"
                    + "USERNAME varchar(50) NOT NULL,"
                    + "PASSWORD varchar(50) NOT NULL,"
                    + "EMAIL varchar(100),"
                    + "SPECIALITY varchar(35) NOT NULL,"
                    + "MOBILEPHONE bigInt,"
                    + "NAME varchar(50) NOT NULL,"
                    + "SURNAME varchar(50) NOT NULL,"
                    + "NIF varchar(25) NOT NULL,"
                    + "DOB DATE,"
                    + "IDADDRESS int CONSTRAINT rAddress REFERENCES ADDRESS (ID),"
                    + "PRIMARY KEY (ID))";
            
            st.execute(in);
            st.close();
        }
        catch(Exception ex) {
            
        }
        finally {
            con.killConnection();
        }
    }
    
    private static void cTMapping() {
        Conector con = new Conector();
        Statement st = null;
        String in = null;
        
        try {
            con.conectar();
            st = con.getConnect().createStatement();
            in = "CREATE TABLE MAPPING"
                    + "(IDDOCTOR int,"
                    + "IDPATIENT int)";
            
            st.execute(in);
            st.close();
        }
        catch(Exception ex) {
            
        }
        finally {
            con.killConnection();
        }
    }
    
    private static void cTSurgeries() {
        Conector con = new Conector();
        Statement st = null;
        String in = null;
        
        try {
            con.conectar();
            st = con.getConnect().createStatement();
            in = "CREATE TABLE SURGERIES"
                    + "(ID int NOT NULL UNIQUE,"
                    + "IDPATIENT int CONSTRAINT rPatient REFERENCES PATIENT,"
                    + "DATE date,"
                    + "TYPE varchar(50),"
                    + "IDTREATMENT int CONSTRAINT rTreatment REFERENCES TREATMENT,"
                    + "PRIMARY KEY (ID))";
            
            st.execute(in);
            st.close();
        }
        catch(Exception ex) {
            
        }
        finally {
            con.killConnection();
        }
    }
    
    private static void cTVaccine() {
        Conector con = new Conector();
        Statement st = null;
        String in = null;
        
        try {
            con.conectar();
            st = con.getConnect().createStatement();
            in = "CREATE TABLE VACCINE"
                    + "(ID int NOT NULL UNIQUE,"
                    + "NAME varchar(25) NOT NULL,"
                    + "DATE date,"
                    + "OBSERVATIONS text,"
                    + "IDPATIENT int CONSTRAINT rPatient REFERENCES PATIENT (ID),"
                    + "PRIMARY KEY(ID))";
            st.execute(in);
            st.close();
        }
        catch(Exception ex) {
            
        }
        finally {
            con.killConnection();
        }
    }
    
    private static void cTPatient() {
        Conector con = new Conector();
        Statement st = null;
        String in = null;
        
        try {
            con.conectar();
            st = con.getConnect().createStatement();
            in = "CREATE TABLE PATIENT"
                    + "(ID int NOT NULL UNIQUE,"
                    + "NAME varchar(25) NOT NULL,"
                    + "SURNAME varchar(25) NOT NULL,"
                    + "NIF varchar (15) NOT NULL,"
                    + "EMAIL varchar(50),"
                    + "MOBILEPHONE bigint,"
                    + "LANDLINE bigint,"
                    + "DOB date,"
                    + "GENDER varchar (15),"
                    + "USERNAME text,"
                    + "PASSWORD varchar (20),"
                    + "WEIGHT float,"
                    + "HEIGHT float,"
                    + "IDADDRESS int CONSTRAINT rAddress REFERENCES ADDRESS (ID),"
                    + "PRIMARY KEY (ID))";
            st.execute(in);
            st.close();
        }
        catch(Exception ex) {
            
        }
        finally {
            con.killConnection();
        }
    }
    
    private static void cTTreatment() {
        Conector con = new Conector();
        Statement st = null;
        String in = null;
        
        try {
            con.conectar();
            st = con.getConnect().createStatement();
            in = "CREATE TABLE TREATMENT"
                    + "(ID int NOT NULL UNIQUE,"
                    + "START date,"
                    + "END date,"
                    + "REHAB boolean,"
                    + "MEDICATION text,"
                    + "DESCRIPTION text,"
                    + "OBSERVATIONS text,"
                    + "IDPATIENT int CONSTRAINT rPatient REFERENCES PATIENT (ID),"
                    + "PRIMARY KEY (ID))";
            st.execute(in);
            st.close();
        }
        catch(Exception ex) {
            
        }
        finally {
            con.killConnection();
        }
    }
    
    private static void cTIllnesses() {
        Conector con = new Conector();
        Statement st = null;
        String in = null;
        
        try {
            con.conectar();
            st = con.getConnect().createStatement();
            in = "CREATE TABLE ILLNESSES"
                    + "(ID int NOT NULL UNIQUE,"
                    + "DESCRIPTION text,"
                    + "PERSONALPATHOLOGIES text,"
                    + "HEREDITARYDISEASES text,"
                    + "OBSERVATIONS text,"
                    + "DATE date,"
                    + "IDTREATMENT int CONSTRAINT rTreatment REFERENCES TREATMENT,"
                    + "IDPATIENT int CONSTRAINT rPatient REFERENCES PATIENT,"
                    + "PRIMARY KEY (ID))";
            st.execute(in);
            st.close();
        }
        catch(Exception ex) {
            
        }
        finally {
            con.killConnection();
        }
    }
    private static void cTAppointment() {
        Conector con = new Conector();
        Statement st = null;
        String in = null;
        
        try {
            con.conectar();
            st = con.getConnect().createStatement();
            in = "CREATE TABLE APPOINTMENT"
                    + "(ID int NOT NULL UNIQUE,"
                    + "HOUR  NOT NULL,"
                    + "DATE date NOT NULL," 
                    + "IDPATIENT int CONSTRAINT rPatient REFERENCES PATIENT (ID),"
                    + "PRIMARY KEY(ID))";
            st.execute(in);
            st.close();
        }
        catch(Exception ex) {
            
        }
        finally {
            con.killConnection();
        }
    }
    private static void cTClinicalHistory() {
        Conector con = new Conector();
        Statement st = null;
        String in = null;
        
        try {
            con.conectar();
            st = con.getConnect().createStatement();
            in = "CREATE TABLE CLINICALHISTORY"
                    + "(ID int NOT NULL UNIQUE,"
                    + "ADDICTIONS text,"
                    + "OBSERVATIONS text,"
                    + "LASTMODIFICATION date,"
                    + "BLOODGROUP varchar (4),"
                    + "MEDICALINSURANCE int,"
                    + "IDPATIENT int CONSTRAINT rPatient REFERENCES PATIENT (ID),"
                    + "PRIMARY KEY(ID))";
            st.execute(in);
            st.close();
        }
        catch(Exception ex) {
            
        }
        finally {
            con.killConnection();
        }
    }
    
}
