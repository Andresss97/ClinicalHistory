
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
    Created by Andrés Daniel de Pereda Cruz
 */
package clinicalhistory.db;

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
                    + "(ID int NOT NULL,"
                    + "CITY varchar(50),"
                    + "STREET varchar(50),"
                    + "CP bigint,"
                    + "HOUSENUMBER bigint,"
                    + "IDDOCTOR int,"
                    + "IDPATIENT int,"
                    + "PRIMARY KEY (ID),"
                    + "FOREIGN KEY (IDPATIENT),"
                    + "FOREIGN KEY (IDDOCTOR))";
            
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
                    + "(ID int NOT NULL,"
                    + "GROUP varchar(50),"
                    + "NOTES text,"
                    + "IDPATIENT int,"
                    + "PRIMARY KEY(ID),"
                    + "FOREIGN KEY(IDPATIENT))";
            
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
                    + "(ID int,"
                    + "USER varchar(50),"
                    + "PSW varchar(50),"
                    + "EMAIL varchar(100),"
                    + "SPECIALITY varchar(35),"
                    + "MOBILEPHONE bigInt,"
                    + "NAME varchar(50),"
                    + "SURNAME varchar(50),"
                    + "NIF varchar(25),"
                    + "DOB DATE,"
                    + "IDADDRESS int,"
                    + "PRIMARY KEY (ID),"
                    + "FOREIGN KEY (IDADDRESS))";
            
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
                    + "(ID int,"
                    + "IDPATIENT int,"
                    + "DOS date,"
                    + "Type varchar(50),"
                    + "IDTREATMENT int,"
                    + "PRIMARY KEY (ID),"
                    + "FOREIGN KEY(IDPATIENT),"
                    + "FOREIGN KEY(IDTREATMENT))";
            
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
                    + "(ID int,"
                    + "NAME varchar(25),"
                    + "IDPATIENT int,"
                    + "PRIMARY KEY(ID),"
                    + "FOREIGN KEY(IDPATIENT))";
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
                    + "(ID int,"
                    + "NAME varchar(25),"
                    + "SURNAME varchar(25),"
                    + "NIF varchar (15),"
                    + "EMAIL varchar(50),"
                    + "MOBILEPHONE bigint,"
                    + "LANDLINE bigint,"
                    + "DOB date,"
                    + "GENDER varchar (15),"
                    + "ADDICTIONS text,"
                    + "WEIGHT float,"
                    + "HEIGHT float,"
                    + "IDADDRESS int,"
                    + "PRIMARY KEY (ID),"
                    + "FOREIGN KEY(IDADDRESS)";
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
                    + "IDADDRESS int,"
                    + "PRIMARY KEY (ID),"
                    + "FOREIGN KEY(IDADDRESS)";
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
                    + "(ID int,"
                    + "DESCRIPTION text,"
                    + "PERSONALPATHOLOGIES text,"
                    + "HEREDITARYDISEASES text,"
                    + "IDTREATMENT date,"
                    + "IDPATIENT int,"
                    + "PRIMARY KEY (ID),"
                    + "FOREIGN KEY(IDTREATMENT)"
                    + "FOREIGN KEY(IDPATIENT)";
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
