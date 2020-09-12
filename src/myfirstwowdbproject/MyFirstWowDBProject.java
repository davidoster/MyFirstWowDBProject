package myfirstwowdbproject;

import dbhelpers.Database;
import entities.Student;
import java.sql.CallableStatement;
import java.sql.Connection;
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

    private String serverIP = "........"; // ra1.anystream.eu
    // "127.0.0.1" // localhost  
    private String srvPort = "3011";
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
        Student s = new Student("Manolis", "Kokovikos");

        callStoredProcedureGetAllStudents("CALL getAllStudentsGKP();");

        resultSet = executeQuery("SELECT * FROM `cb11studentsmarks`.`students`;");
        boolean rsOK = printResultSet(resultSet);
        //int count = executeUpdate("INSERT INTO students(fname, lname) VALUES ('Manolis', 'Kokovikos');");
        int count = executeUpdateStudent(s);
        resultSet = executeQuery("SELECT * FROM `cb11studentsmarks`.`students`;");
        rsOK = printResultSet(resultSet);

    }

    public static void callStoredProcedureGetAllStudents(String sql) {
        MyFirstWowDBProject dbProject = new MyFirstWowDBProject();
        Database db = new Database(dbProject.serverIP, dbProject.srvPort, dbProject.databaseName, dbProject.username, dbProject.password);
        Connection conn = db.createConnection();
        try {
            CallableStatement callableStatement = conn.prepareCall(sql); // CALL getAllStudentsGKP();
            ResultSet rs = callableStatement.executeQuery();
            while (rs.next()) {
                System.out.println("Id: " + rs.getString(1)
                        + "\tFirst Name: " + rs.getString(2)
                        + "\tLast Name: " + rs.getString(3));
            }
            rs.close();
            callableStatement.close();
            conn.close();

        } catch (SQLException ex) {
            Logger.getLogger(MyFirstWowDBProject.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static ResultSet executeQuery(String sql) {
        MyFirstWowDBProject dbProject = new MyFirstWowDBProject();
        ResultSet rs = null;
        Database db = new Database(dbProject.serverIP, dbProject.srvPort, dbProject.databaseName, dbProject.username, dbProject.password);
        rs = db.connectAndExecuteQuery(sql);
        return (rs);
    }

    public static int executeUpdate(String sql) {
        MyFirstWowDBProject dbProject = new MyFirstWowDBProject();
        Database db = new Database(dbProject.serverIP, dbProject.srvPort, dbProject.databaseName, dbProject.username, dbProject.password);
        int count = db.connectAndExecuteUpdate(sql);
        return (count);
    }
    
    public static int executeUpdateStudent(Student s) {
        MyFirstWowDBProject dbProject = new MyFirstWowDBProject();
        Database db = new Database(dbProject.serverIP, dbProject.srvPort, dbProject.databaseName, dbProject.username, dbProject.password);
        int count = db.connectAndExecuteUpdate("INSERT INTO students(fname, lname) "
                                             + "VALUES ('" + s.getFirstName() + "', '" + s.getLastName() + "');");
        return (count);
    }

    public static boolean printResultSet(ResultSet rs) {
        try {
            ResultSetMetaData rsmd = null;
            rsmd = rs.getMetaData();
            int columnsNumber = rsmd.getColumnCount();
            System.out.println("\n");
            System.out.println("Number of fields: " + columnsNumber);
            while (rs.next()) {
                System.out.println("Id: " + rs.getString(1)
                        + "\tFirst Name: " + rs.getString(2)
                        + "\tLast Name: " + rs.getString(3));
            }
            System.out.println("\n");
            return (true);
        } catch (SQLException ex) {
            Logger.getLogger(MyFirstWowDBProject.class.getName()).log(Level.SEVERE, null, ex);
            return (false);
        }
    }

    // jdbc:mysql://5.189.135.166:3011/cb11studentsmarks?zeroDateTimeBehavior=convertToNull
    private String createJDBCConnectionString() {
        String value = "";
        value = "jdbc:mysql://" + serverIP + ":" + srvPort + "/" + databaseName + "?zeroDateTimeBehavior=convertToNull";

        return (value);
    }

}
