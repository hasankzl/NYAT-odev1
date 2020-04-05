/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package odev1.domain;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import odev1.Odev1;

/**
 *
 * @author Hasan
 */
public class SingletonConnect {

    private Connection connection;
    private String url = "jdbc:postgresql://localhost:5432/odev1";
    private String password = "root";
    private String user = "postgres";
    private static SingletonConnect instange = null;

    private SingletonConnect() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Odev1.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException ex) {
            Logger.getLogger(Odev1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static SingletonConnect getInstance() {
        if (getInstange() == null) {
            setInstange(new SingletonConnect());
        }

        return getInstange();
    }

    /**
     * @return the connection
     */
    public Connection getConnection() {
        return connection;
    }

    /**
     * @param connection the connection to set
     */
    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    /**
     * @return the url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url the url to set
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the user
     */
    public String getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(String user) {
        this.user = user;
    }

    /**
     * @return the instange
     */
    public static SingletonConnect getInstange() {
        return instange;
    }

    /**
     * @param instange the instange to set
     */
    public static void setInstange(SingletonConnect ainstange) {
        instange = ainstange;
    }
}
