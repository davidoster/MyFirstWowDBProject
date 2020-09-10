/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myfirstwowdbproject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

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
        MyFirstWowDBProject dbProject = new MyFirstWowDBProject();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        ResultSetMetaData rsmd = null;
        
        try {
            connection = DriverManager.getConnection(dbProject.createJDBCConnectionString(), 
                                                     dbProject.username, dbProject.password);
            statement = connection.createStatement();
            // executeQuery - SELECT -> resultSet
            resultSet = statement.executeQuery("SELECT * FROM `cb11studentsmarks`.`students`;");
            rsmd = resultSet.getMetaData();
            int columnsNumber = rsmd.getColumnCount();
            System.out.println("Number of fields: " + columnsNumber);
            while(resultSet.next()) {
                System.out.println("Id: " + resultSet.getString(1)           + 
                                   "\tFirst Name: " + resultSet.getString(2) + 
                                   "\tLast Name: " + resultSet.getString(3));
            }
            // executeUpdate - INSERT, UPDATE, DELETE -> int count
            int insertCount = statement.executeUpdate("INSERT INTO students(fname, lname) VALUES ('Manolis', 'Kokovikos');");
            System.out.println("Rows inserted : " + insertCount);
            
            resultSet.close();
            statement.close();
            connection.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(MyFirstWowDBProject.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
    // jdbc:mysql://5.189.135.166:3011/cb11studentsmarks?zeroDateTimeBehavior=convertToNull
    private String createJDBCConnectionString() {
        String value = "";
        value = "jdbc:mysql://" + serverIP + ":" + srvPort + "/" + databaseName + "?zeroDateTimeBehavior=convertToNull";
        
        return(value);
    }
    
}
