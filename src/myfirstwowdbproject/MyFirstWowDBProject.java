/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myfirstwowdbproject;

import dbhelpers.Database;
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
    private String serverIP   = "........"; // ra1.anystream.eu
                           // "127.0.0.1" // localhost  
    private String srvPort  = "3011";
    private String databaseName = "cb11studentsmarks";
    private String username = "root";
    private String password = "........";

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
        
        resultSet = executeQuery("SELECT * FROM `cb11studentsmarks`.`students`;");
        boolean rsOK = printResultSet(resultSet);
        int count = executeUpdate("INSERT INTO students(fname, lname) VALUES ('Manolis', 'Kokovikos');");
        resultSet = executeQuery("SELECT * FROM `cb11studentsmarks`.`students`;");
        rsOK = printResultSet(resultSet);
        
//        try {
//            connection = DriverManager.getConnection(dbProject.createJDBCConnectionString(), 
//                                                     dbProject.username, dbProject.password);
//            statement = connection.createStatement();
//            // executeQuery - SELECT -> resultSet
//            resultSet = statement.executeQuery("SELECT * FROM `cb11studentsmarks`.`students`;");
//            rsmd = resultSet.getMetaData();
//            int columnsNumber = rsmd.getColumnCount();
//            System.out.println("Number of fields: " + columnsNumber);
//            while(resultSet.next()) {
//                System.out.println("Id: " + resultSet.getString(1)           + 
//                                   "\tFirst Name: " + resultSet.getString(2) + 
//                                   "\tLast Name: " + resultSet.getString(3));
//            }
//            // executeUpdate - INSERT, UPDATE, DELETE -> int count
//            int insertCount = statement.executeUpdate("INSERT INTO students(fname, lname) VALUES ('Manolis', 'Kokovikos');");
//            System.out.println("Rows inserted : " + insertCount);
//            
//            resultSet.close();
//            statement.close();
//            connection.close();
//            
//        } catch (SQLException ex) {
//            Logger.getLogger(MyFirstWowDBProject.class.getName()).log(Level.SEVERE, null, ex);
//        }
        
        
    }
    
    public static ResultSet executeQuery(String sql) {
        MyFirstWowDBProject dbProject = new MyFirstWowDBProject();
        ResultSet rs = null;
        Database db = new Database(dbProject.serverIP, dbProject.srvPort, dbProject.databaseName, dbProject.username, dbProject.password);
        rs = db.connectAndExecuteQuery(sql);
        return(rs);
    }
    
    public static int executeUpdate(String sql) {
        MyFirstWowDBProject dbProject = new MyFirstWowDBProject();
        ResultSet rs = null;
        Database db = new Database(dbProject.serverIP, dbProject.srvPort, dbProject.databaseName, dbProject.username, dbProject.password);
        int count = db.connectAndExecuteUpdate(sql);
        return(count);
    }
    
    public static boolean printResultSet(ResultSet rs) {
        try {
            ResultSetMetaData rsmd = null;
            rsmd = rs.getMetaData();
            int columnsNumber = rsmd.getColumnCount();
            System.out.println("\n");
            System.out.println("Number of fields: " + columnsNumber);
            while(rs.next()) {
                System.out.println("Id: "           + rs.getString(1)           +
                        "\tFirst Name: " + rs.getString(2) +
                        "\tLast Name: "  + rs.getString(3));
            }
            System.out.println("\n");
            return(true);
        } catch (SQLException ex) {
            Logger.getLogger(MyFirstWowDBProject.class.getName()).log(Level.SEVERE, null, ex);
            return(false);
        }
    }
    
    // jdbc:mysql://5.189.135.166:3011/cb11studentsmarks?zeroDateTimeBehavior=convertToNull
    private String createJDBCConnectionString() {
        String value = "";
        value = "jdbc:mysql://" + serverIP + ":" + srvPort + "/" + databaseName + "?zeroDateTimeBehavior=convertToNull";
        
        return(value);
    }
    
}
