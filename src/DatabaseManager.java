package src;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseManager {
    private static final String DB_URL = "jdbc:sqlite:resources/database.db";

    // Initializes the database with tables
    public static void initializeDatabase() {

        //Creates a table through a "String"
        String createCourseTableSQL =
                "CREATE TABLE IF NOT EXISTS course (" +
                        "code INTEGER PRIMARY KEY," +
                        "name TEXT NOT NULL" +
                        ");";

        String createSemesterTableSQL =
                "CREATE TABLE IF NOT EXISTS semester (" +
                        "id INTEGER PRIMARY KEY," +
                        "name TEXT NOT NULL" +
                        ");";

        String createAYearTableSQL =
                "CREATE TABLE IF NOT EXISTS aYear (" +
                        "id INTEGER PRIMARY KEY," +
                        "name TEXT NOT NULL" +
                        ");";

        String createCourseEvalTableSQL =
                "CREATE TABLE IF NOT EXISTS courseEval (" +
                        "id INTEGER PRIMARY KEY," +
                        "courseId INTEGER," +
                        "semesterId INTEGER," +
                        "aYearId INTEGER," +
                        "FOREIGN KEY (courseId) REFERENCES course(code)," +
                        "FOREIGN KEY (semesterId) REFERENCES semester(id)," +
                        "FOREIGN KEY (aYearId) REFERENCES aYear(id)" +
                        ");";

        String createEvalQuestionsTableSQL =
                "CREATE TABLE IF NOT EXISTS evalQuestions (" +
                        "id INTEGER PRIMARY KEY," +
                        "question TEXT NOT NULL," +
                        "type BOOLEAN NOT NULL" +
                        ");";

        String createGuestEvalTableSQL =
                "CREATE TABLE IF NOT EXISTS guestEval (" +
                        "id INTEGER PRIMARY KEY," +
                        "guestId INTEGER," +
                        "courseId INTEGER," +
                        "semesterId INTEGER," +
                        "aYearId INTEGER," +
                        "FOREIGN KEY (guestId) REFERENCES guests(id)," +
                        "FOREIGN KEY (courseId) REFERENCES course(code)," +
                        "FOREIGN KEY (semesterId) REFERENCES semester(id)," +
                        "FOREIGN KEY (aYearId) REFERENCES aYear(id)" +
                        ");";

        String createEvalAnswersTableSQL =
                "CREATE TABLE IF NOT EXISTS evalAnswers (" +
                        "guestEvalId INTEGER," +
                        "courseEvalId INTEGER," +
                        "evalQuestionId INTEGER," +
                        "answer TEXT," +
                        "PRIMARY KEY (guestEvalId, courseEvalId, evalQuestionId)," +
                        "FOREIGN KEY (guestEvalId) REFERENCES guestEval(id)," +
                        "FOREIGN KEY (courseEvalId) REFERENCES courseEval(id)," +
                        "FOREIGN KEY (evalQuestionId) REFERENCES evalQuestions(id)" +
                        ");";

        String createGuestsTableSQL =
                "CREATE TABLE IF NOT EXISTS guests (" +
                        "id INTEGER PRIMARY KEY," +
                        "fname TEXT NOT NULL," +
                        "lname TEXT NOT NULL" +
                        ");";

        String createGradesTableSQL =
                "CREATE TABLE IF NOT EXISTS grades (" +
                        "id INTEGER PRIMARY KEY," +
                        "studentId INTEGER," +
                        "courseId INTEGER," +
                        "semesterId INTEGER," +
                        "aYearId INTEGER," +
                        "grade TEXT," +
                        "retake BOOLEAN," +
                        "FOREIGN KEY (courseId) REFERENCES course(code)," +
                        "FOREIGN KEY (semesterId) REFERENCES semester(id)," +
                        "FOREIGN KEY (aYearId) REFERENCES aYear(id)" +
                        ");";

        try (Connection conn = DriverManager.getConnection(DB_URL); //Connects to the SQLite database
             Statement stmt = conn.createStatement()) { //SQL statement to the connected database
            // Execute all the SQL statements to create the tables
            stmt.execute(createCourseTableSQL);
            stmt.execute(createSemesterTableSQL);
            stmt.execute(createAYearTableSQL);
            stmt.execute(createCourseEvalTableSQL);
            stmt.execute(createEvalQuestionsTableSQL);
            stmt.execute(createGuestEvalTableSQL);
            stmt.execute(createEvalAnswersTableSQL);
            stmt.execute(createGuestsTableSQL);
            stmt.execute(createGradesTableSQL);

            System.out.println("Database initialized.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void insertTestData(String table, String columns, String values) {
        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement()) { // Connects to the SQLite database and creates a Statement variable
            stmt.execute("INSERT OR IGNORE INTO " + table + " (" + columns + ") VALUES (" + values + ");"); // Executes the statement with the given values
            System.out.println("Data inserted into " + table + ".");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static List<List<String>> selectData(String table, String columns) {
        List<List<String>> results = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT " + columns + " FROM " + table)) {
            while (rs.next()) {
                List<String> row = new ArrayList<>();
                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                    row.add(rs.getString(i));
                }
                results.add(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return results;
    }

    public static void updateData(String table, String column, String newValue, String condition) {
        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement()) { // Connects to the SQLite database and creates a statement variable
            stmt.execute("UPDATE " + table + " SET " + column + " = '" + newValue + "' WHERE " + condition); // Executes the statement
            System.out.println("Data updated");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void deleteData(String table, String condition) {
        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement()) { // Connects to the SQLite database and creates a statement variable
            stmt.execute("DELETE FROM " + table + " WHERE " + condition); // Executes the statement given from the variable
            System.out.println("Data deleted");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}
