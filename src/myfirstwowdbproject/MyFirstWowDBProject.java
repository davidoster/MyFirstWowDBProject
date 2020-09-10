/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myfirstwowdbproject;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author mac
 */
public class MyFirstWowDBProject {
    private String serverIP   = "5.189.135.166"; // ra1.anystream.eu
                           // "127.0.0.1" // localhost  
    private String srvPort  = "3011";
    private String databaseName = "cb11studentsmarks";
    private String username = "root";
    private String password = "AFDEmp_MySQL1";

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // connect to the database
        // run (call) a stored procedure
        // or run a simple SQL query (SELECT)
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        
        
    }
    
    // jdbc:mysql://5.189.135.166:3011/cb11studentsmarks?zeroDateTimeBehavior=convertToNull
    private String createJDBCConnectionString() {
        String value = "";
        value = "jdbc:mysql://" + serverIP + ":" + srvPort + "/" + databaseName + "?zeroDateTimeBehavior=convertToNull";
        
        return(value);
    }
    
}
