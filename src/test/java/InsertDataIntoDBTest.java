import org.testng.annotations.Test;
import java.sql.*;

public class InsertDataIntoDBTest {

    // Method to insert a new student into the database
    public static void insertNewStudent(int id, String firstName, String lastName, String phone) throws SQLException {
        // Establish a connection to the database
        Connection connection = DriverManager.getConnection("jdbc:sqlserver://localhost;databaseName=students; trustServerCertificate=true",
                "testUser",
                "testUser");

        // SQL statement for inserting data
        String insertSql = "INSERT INTO students (id, firstName, lastName, phone) VALUES (?, ?, ?, ?)";

        // Prepare the SQL statement
        PreparedStatement psInsert = connection.prepareStatement(insertSql);
        // Set the values for the INSERT statement
        psInsert.setInt(1, id);
        psInsert.setString(2, firstName);
        psInsert.setString(3, lastName);
        psInsert.setString(4, phone);

        // Execute the INSERT operation
        psInsert.executeUpdate();

        // Close the database connection
        connection.close();
    }

    // Method to update the first name of a student
    public static void updateStudent(int id, String name) throws SQLException {
        // Establish a connection to the database
        Connection connection = DriverManager.getConnection("jdbc:sqlserver://localhost;databaseName=students; trustServerCertificate=true",
                "testUser",
                "testUser");

        // SQL statement for updating data
        String updateSql = "UPDATE students SET firstName = ? WHERE id = ?";
        // Prepare the SQL statement
        PreparedStatement updateStatement = connection.prepareStatement(updateSql);
        // Set the new first name and ID in the UPDATE statement
        updateStatement.setString(1, name);
        updateStatement.setInt(2, id); // Student ID

        // Execute the UPDATE operation
        updateStatement.executeUpdate();

        // Close the database connection
        connection.close();
    }

    // Method to check and print details of a student
    public static void checkStudent(int id) throws SQLException {
        // Establish a connection to the database
        Connection connection = DriverManager.getConnection("jdbc:sqlserver://localhost;databaseName=students; trustServerCertificate=true",
                "testUser",
                "testUser");

        // SQL statement for selecting a student
        String selectSql = "SELECT * FROM students WHERE id = " + id;

        // Execute the SELECT operation
        ResultSet rs = connection.createStatement().executeQuery(selectSql);

        // Process the result set
        if (rs.next()) {
            // Print student details if found
            System.out.println("Student ID: " + rs.getInt("id"));
            System.out.println("First Name: " + rs.getString("firstName"));
        } else {
            // Print message if no student is found
            System.out.println("Row not found");
        }

        // Close the database connection
        connection.close();
    }

    // Test method to run the database operations
    @Test
    public void runTest() throws SQLException {
        // Insert a new student
        insertNewStudent(1004, "Arnold","Schwarzenegger", "5115234455");
        // Check and print the newly inserted student's details
        checkStudent(1004);
        // Update the first name of the student
        updateStudent(1004, "Bondo");
        // Check and print the updated details of the student
        checkStudent(1004);
    }

}
