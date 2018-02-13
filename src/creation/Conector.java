/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package creation;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author andre
 */
public class Conector {
    private Connection connect;
    private String url;
    
    public Connection getConnect() {
        return connect;
    }

    public void setConnect(Connection connect) {
        this.connect = connect;
    }
    
    public String getUrl() {
    	return url;
    }
    
    public boolean conectar() {
        try {
            Class.forName("org.sqlite.JDBC");
            this.url = "jdbc:sqlite:" + ".//Database//DBproject.db";
            connect = DriverManager.getConnection(this.url);
            return true;
        }
        catch(Exception ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }
    
    public void killConnection() {
        try {
            connect.close();
        }
        catch(SQLException ex) {
            Logger.getLogger(Conector.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
