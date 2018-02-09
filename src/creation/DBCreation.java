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
public class DBCreation {
    private String url;
    
    public DBCreation() {
        this.url = null;
    }
    
    private boolean findUrl() {
        File f = new File("C:\\DatabasesGHC\\DBproject.db");
        if(f.exists()) {
            this.url = f.getAbsolutePath();
            return true;
        }
        else {
            return false;
        }
    }
    
    public void createDB() {
        if(this.findUrl() != true) {
            this.url = "C:\\DatabasesGHC\\DBproject.db";
            String bUrl = "jdbc:sqlite:" + this.url;
            
            File newDir = new File("C:\\DatabasesGHC");
            Connection con = null;
            
            try {
                Class.forName("org.sqlite.JDBC");
                newDir.mkdir();
                con = DriverManager.getConnection(bUrl);
                this.cTAddress();
                this.cTAllergies();
                this.cTDoctor();
                this.cTMapping();
                this.cTPatient();
                this.cTSurgeries();
                this.cTTreatment();
                this.cTVaccine();
                this.cTIllnesses();
            }
            catch(ClassNotFoundException | SQLException ex) {
                
            }
            finally {
                try {
                    con.close();
                }
                catch(SQLException ex) {
                    
                }
            }
        }
    }
    
    private void cTAddress() {
        Conector con = new Conector();
        Statement st = null;
        String in = null;
        
        try {
            con.conectar();
            st = con.getConnect().createStatement();
            
            in = "CREATE TABLE ADDRESS "
                    + "(ID int NOT NULL UNIQUE PRIMARY KEY,"
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
    
    private void cTAllergies() {
        Conector con = new Conector();
        Statement st = null;
        String in = null;
        
        try {
            con.conectar();;
            st = con.getConnect().createStatement();
            
            in = "CREATE TABLE ALLERGIES"
                    + "(ID int NOT NULL UNIQUE PRIMARY KEY,"
                    + "GROUP varchar(50),"
                    + "NOTES text,"
                    + "IDPATIENT int CONSTRAINT rPatient REFERENCES PATIENT (ID) ON DELETE CASCADE ON UPDATE CASCADE,"
                    + "IDTREATMENT int CONSTRAINT rTreatment REFERENCES TREATMENT (ID) ON DELETE CASCADE ON UPDATE CASCADE,"
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
    
    private void cTDoctor() {
        Conector con = new Conector();
        Statement st = null;
        String in = null;
        
        try {
            con.conectar();
            st = con.getConnect().createStatement();
            
            in = "CREATE TABLE DOCTOR"
                    + "(ID int NOT NULL UNIQUE PRIMARY KEY,"
                    + "USER varchar(50) NOT NULL UNIQUE,"
                    + "PSW varchar(50) NOT NULL,"
                    + "EMAIL varchar(100),"
                    + "SPECIALITY varchar(35) NOT NULL,"
                    + "MOBILEPHONE bigInt,"
                    + "NAME varchar(50) NOT NULL,"
                    + "SURNAME varchar(50) NOT NULL,"
                    + "NIF varchar(25) NOT NULL,"
                    + "DOB DATE,"
                    + "IDADDRESS int CONSTRAINT rAddress REFERENCES ADDRESS (ID) ON DELETE CASCADE ON UPDATE CASCADE,"
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
    
    private void cTMapping() {
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
    
    private void cTSurgeries() {
        Conector con = new Conector();
        Statement st = null;
        String in = null;
        
        try {
            con.conectar();
            st = con.getConnect().createStatement();
            in = "CREATE TABLE SURGERIES"
                    + "(ID int NOT NULL UNIQUE PRIMARY KEY,"
                    + "IDPATIENT int CONSTRAINT rPatient REFERENCES PATIENT ON DELETE CASCADE ON UPDATE CASCADE,"
                    + "DOS date,"
                    + "Type varchar(50),"
                    + "IDTREATMENT int CONSTRAINT rTreatment REFERENCES TREATMENT ON DELETE CASCADE ON UPDATE CASCADE,"
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
    
    private void cTVaccine() {
        Conector con = new Conector();
        Statement st = null;
        String in = null;
        
        try {
            con.conectar();
            st = con.getConnect().createStatement();
            in = "CREATE TABLE VACCINE"
                    + "(ID int NOT NULL UNIQUE PRIMARY KEY,"
                    + "NAME varchar(25) NOT NULL,"
                    + "IDPATIENT int CONSTRAINT rPatient REFERENCES PATIENT (ID) ON DELETE CASCADE ON UPDATE CASCADE,"
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
    
    private void cTPatient() {
        Conector con = new Conector();
        Statement st = null;
        String in = null;
        
        try {
            con.conectar();
            st = con.getConnect().createStatement();
            in = "CREATE TABLE PATIENT"
                    + "(ID int NOT NULL UNIQUE PRIMARY KEY,"
                    + "NAME varchar(25) NOT NULL,"
                    + "SURNAME varchar(25) NOT NULL,"
                    + "NIF varchar (15) NOT NULL,"
                    + "EMAIL varchar(50),"
                    + "MOBILEPHONE bigint,"
                    + "LANDLINE bigint,"
                    + "DOB date,"
                    + "GENDER varchar (15),"
                    + "ADDICTIONS text,"
                    + "WEIGHT float,"
                    + "HEIGHT float,"
                    + "IDADDRESS int CONSTRAINT rAddress REFERENCES ADDRESS (ID) ON DELETE CASCADE ON UPDATE CASCADE,"
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
    
    private void cTTreatment() {
        Conector con = new Conector();
        Statement st = null;
        String in = null;
        
        try {
            con.conectar();
            st = con.getConnect().createStatement();
            in = "CREATE TABLE TREATMENT"
                    + "(ID int,"
                    + "START date,"
                    + "END date,"
                    + "REHAB boolean,"
                    + "MEDICATION text,"
                    + "DESCRIPTION text,"
                    + "DOB date,"
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
    
    private void cTIllnesses() {
        Conector con = new Conector();
        Statement st = null;
        String in = null;
        
        try {
            con.conectar();
            st = con.getConnect().createStatement();
            in = "CREATE TABLE ILLNESSES"
                    + "(ID int NOT NULL UNIQUE PRIMARY KEY,"
                    + "DESCRIPTION text,"
                    + "PERSONALPATHOLOGIES text,"
                    + "HEREDITARYDISEASES text,"
                    + "IDTREATMENT int CONSTRAINT rTreatment REFERENCES TREATMENT ON DELETE CASCADE ON UPDATE CASCADE,"
                    + "IDPATIENT int CONSTRAINT rPatient REFERENCES PATIENT ON DELETE CASCADE ON UPDATE CASCADE,"
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
}
