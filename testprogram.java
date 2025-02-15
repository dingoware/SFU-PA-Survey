/*
Use this as an example for how the rest of our software should work
Feel free to mess around with the code and try different things to get a hang of it
You can follow the same tutorial I used here: https://www.sqlitetutorial.net/sqlite-java/
-Camm
*/

//required imports for SQLite
import java.sql.DriverManager;
import java.sql.SQLException;

public class testprogram {
    public static void main(String[] args) {
        //connect to database
        String url = "jdbc:sqlite:test.db";

        //using arrays to import data into database, updating values is done the same way
        var ids = new int[] {101, 102, 103, 104, 105};
        var grades = new String[] {"A", "A", "B", "A", "D"};
        var retakes = new boolean[] {false, true, false, false, false};

        //create SQL table (only for first time use, can create directly as well)
        /*var sql = "CREATE TABLE IF NOT EXISTS grades ("
                + "     id INTEGER PRIMARY KEY,"
                + "     grade TEXT NOT NULL,"
                + "     retake BOOLEAN"
                + ");";*/

        //sql command to insert values into sql table
        String sql = "INSERT INTO grades(id, grade, retake) VALUES(?, ?, ?)";

        //try to connect to database, returns error if not possible
        try (var conn = DriverManager.getConnection(url)) {
            var pstmt = conn.prepareStatement(sql);

            //iterates through arrays and assigns them to corresponding table values
            for (int i = 0; i < ids.length; i++) {
                pstmt.setInt(1, ids[i]);
                pstmt.setString(2, grades[i]);
                pstmt.setBoolean(3, retakes[i]);

                pstmt.executeUpdate();
            }
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}