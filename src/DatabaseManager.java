package src;

import java.sql.*;

public class DatabaseManager {
    private static final String DB_URL = "jdbc:sqlite:resources/database.db";

    public static void initializeDatabase() {
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

        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement()) {
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
             Statement stmt = conn.createStatement()) {
            stmt.execute("INSERT OR IGNORE INTO " + table + " (" + columns + ") VALUES (" + values + ");");
            System.out.println("Data inserted into " + table + ".");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void selectData(String table) {
        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM " + table)) {
            while (rs.next()) {
                System.out.println(rs.getString(1) + " | " + rs.getString(2));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void updateData(String table, String column, String newValue, String condition) {
        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement()) {
            stmt.execute("UPDATE " + table + " SET " + column + " = '" + newValue + "' WHERE " + condition);
            System.out.println("Data updated");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void deleteData(String table, String condition) {
        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement()) {
            stmt.execute("DELETE FROM " + table + " WHERE " + condition);
            System.out.println("Data deleted");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}
