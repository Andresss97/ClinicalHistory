/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
    Created by Andrés Daniel de Pereda Cruz
 */
package creation;

import java.sql.*;

/**
 *
 * @author andre
 */
public abstract class DBCreation {    
	
	public static void createDB() {
		Conector con = new Conector();
		
		if(con.conectar() == true ) {
			cTAddress();
			cTAllergies();
			cTDoctor();
			cTMapping();
			cTPatient();
			cTSurgeries();
			cTTreatment();
			cTVaccine();
			cTIllnesses();
			cTAppointment();
			cTClinicalHistory();
			cTAdministrator();
			cTMappingLogIn();
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
                    + "(ID integer PRIMARY KEY AUTOINCREMENT NOT NULL UNIQUE,"
                    + "CITY varchar(50),"
                    + "STREET varchar(50),"
                    + "CP bigint,"
                    + "HOUSENUMBER bigint)";
            
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
                    + "(ID integer PRIMARY KEY AUTOINCREMENT NOT NULL UNIQUE,"
                    + "TYPE varchar(50),"
                    + "OBSERVATIONS text,"
                    + "IDPATIENT int CONSTRAINT rPatient REFERENCES PATIENT ON UPDATE CASCADE ON DELETE SET NULL,"
                    + "IDTREATMENT int CONSTRAINT rTreatment REFERENCES TREATMENT ON UPDATE CASCADE ON DELETE SET NULL)";
            
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
                    + "(ID integer PRIMARY KEY AUTOINCREMENT NOT NULL UNIQUE,"
                    + "USERNAME varchar(50) NOT NULL,"
                    + "PASSWORD varchar(50) NOT NULL,"
                    + "EMAIL varchar(100) NOT NULL,"
                    + "GENDER varchar(20) NOT NULL,"
                    + "SPECIALITY varchar(35) NOT NULL,"
                    + "MOBILEPHONE int,"
                    + "NAME varchar(50) NOT NULL,"
                    + "SURNAME varchar(50) NOT NULL,"
                    + "NIF varchar(25) NOT NULL,"
                    + "DOB date NOT NULL,"
                    + "PHOTO blob,"
                    + "IDADDRESS int CONSTRAINT rAddress REFERENCES ADDRESS ON UPDATE CASCADE ON DELETE SET NULL)";
            
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
                    + "(ID integer PRIMARY KEY AUTOINCREMENT NOT NULL UNIQUE,"
                    + "IDPATIENT int CONSTRAINT rPatient REFERENCES PATIENT ON UPDATE CASCADE ON DELETE SET NULL,"
                    + "DATE date,"
                    + "TYPE varchar(50),"
                    + "IDTREATMENT int CONSTRAINT rTreatment REFERENCES TREATMENT ON UPDATE CASCADE ON DELETE SET NULL)";
            
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
                    + "(ID integer PRIMARY KEY AUTOINCREMENT NOT NULL UNIQUE,"
                    + "NAME varchar(25) NOT NULL,"
                    + "DATE date,"
                    + "OBSERVATIONS text,"
                    + "IDPATIENT int CONSTRAINT rPatient REFERENCES PATIENT ON UPDATE CASCADE ON DELETE SET NULL)";
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
                    + "(ID integer PRIMARY KEY AUTOINCREMENT NOT NULL UNIQUE,"
                    + "NAME varchar(25) NOT NULL,"
                    + "SURNAME varchar(25) NOT NULL,"
                    + "NIF varchar (15) NOT NULL,"
                    + "EMAIL varchar(50) NOT NULL,"
                    + "MOBILEPHONE bigint,"
                    + "HOMEPHONE bigint,"
                    + "DOB date NOT NULL,"
                    + "GENDER varchar (15) NOT NULL,"
                    + "USERNAME varchar(50) NOT NULL,"
                    + "PASSWORD varchar (20) NOT NULL,"
                    + "WEIGHT float,"
                    + "HEIGHT float,"
                    + "IDADDRESS int CONSTRAINT rAddress REFERENCES ADDRESS ON UPDATE CASCADE ON DELETE SET NULL)";
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
                    + "(ID integer PRIMARY KEY AUTOINCREMENT NOT NULL UNIQUE,"
                    + "START date NOT NULL,"
                    + "END date NOT NULL,"
                    + "REHAB boolean,"
                    + "MEDICATION text,"
                    + "DESCRIPTION text,"
                    + "TRESULTS text,"
                    + "IDPATIENT int CONSTRAINT rPatient REFERENCES PATIENT (ID) ON UPDATE CASCADE ON DELETE SET NULL)";
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
                    + "(ID integer PRIMARY KEY AUTOINCREMENT NOT NULL UNIQUE,"
                    + "DESCRIPTION text,"
                    + "DATE date,"
                    + "TYPE varchar(50),"
                    + "IDTREATMENT int CONSTRAINT rTreatment REFERENCES TREATMENT ON UPDATE CASCADE ON DELETE SET NULL,"
                    + "IDPATIENT int CONSTRAINT rPatient REFERENCES PATIENT ON UPDATE CASCADE ON DELETE SET NULL)";
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
                    + "(ID integer PRIMARY KEY AUTOINCREMENT NOT NULL UNIQUE,"
                    + "HOUR int NOT NULL,"
                    + "DATE date NOT NULL," 
                    + "IDPATIENT int CONSTRAINT rPatient REFERENCES PATIENT ON UPDATE CASCADE ON DELETE SET NULL,"
                    + "IDDOCTOR int CONSTRAINT rDoctor REFERENCES DOCTOR ON UPDATE CASCADE ON DELETE SET NULL,"
                    + "REASON text NOT NULL)";
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
    
    private static void cTClinicalHistory() {
        Conector con = new Conector();
        Statement st = null;
        String in = null;
        
        try {
            con.conectar();
            st = con.getConnect().createStatement();
            in = "CREATE TABLE CLINICALHISTORY"
                    + "(ID integer PRIMARY KEY AUTOINCREMENT NOT NULL UNIQUE,"
                    + "ADDICTIONS text,"
                    + "OBSERVATIONS text,"
                    + "LASTMODIFICATION date,"
                    + "BLOODGROUP varchar (15),"
                    + "IDPATIENT int CONSTRAINT rPatient REFERENCES PATIENT ON UPDATE CASCADE ON DELETE SET NULL)";
            st.execute(in);
            st.close();
        }
        catch(Exception ex) {
            
        }
        finally {
            con.killConnection();
        }
    }
    
    private static void cTAdministrator() {
        Conector con = new Conector();
        Statement st = null;
        String in = null;
        
        try {
            con.conectar();
            st = con.getConnect().createStatement();
            in = "CREATE TABLE ADMINISTRATOR"
                    + "(ID integer PRIMARY KEY AUTOINCREMENT NOT NULL UNIQUE,"
                    + "USER varchar(50),"
                    + "PASSWORD varchar(50))";
            st.execute(in);
            st.close();
        }
        catch(Exception ex) {
            
        }
        finally {
            con.killConnection();
        }
    }
    
    private static void cTMappingLogIn() {
        Conector con = new Conector();
        Statement st = null;
        String in = null;
        
        try {
            con.conectar();
            st = con.getConnect().createStatement();
            in = "CREATE TABLE MAPPINGLOGIN"
                    + "(ID integer PRIMARY KEY AUTOINCREMENT NOT NULL UNIQUE,"
                    + "USERNAME varchar(50),"
                    + "PASSWORD varchar (50),"
                    + "USERTYPE int NOT NULL)";
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
