import org.testng.annotations.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DataBaseTest {
    private static final String connectionString = "jdbc:sqlserver://localhost:1433;trustServerCertificate=true";
    private static final String user = "testUser";
    private static final String password = "testUser";
    static final String query = "SELECT firstName, lastName FROM students.dbo.students";


    public static List<String[]> executeQuery() {
        List<String[]> results = new ArrayList<>();

        // Create connection
        try (Connection conn = DriverManager.getConnection(connectionString, user, password);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            // Get the number of columns needed
            int columnCount = rs.getMetaData().getColumnCount();

            // Write data into array
            while (rs.next()) {
                String[] rowData = new String[columnCount];
                for (int i = 0; i < columnCount; i++) {
                    rowData[i] = rs.getString(i + 1);
                }
                results.add(rowData);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return results;
    }

    public static boolean testDBConnection() {
        try (Connection ignored = DriverManager.getConnection(connectionString, user, password)) {
            System.out.println("Connected to SQL Server successfully.");
            return true; // Connection was successful
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false; // Connection failed
        }
    }

    @Test
    public void getDataFromDB(){
        //testDBConnection();

        List<String[]> results = executeQuery();

        for (String[] row : results) {
            System.out.println(Arrays.toString(row));
        }
    }
}
