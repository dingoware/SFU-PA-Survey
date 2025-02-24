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

    public static void insertTestData() {
        String insertCourseSQL = "INSERT OR IGNORE INTO course (code, name) VALUES (201, 'Data Structures'), (202, 'Database Systems');";
        String insertSemesterSQL = "INSERT OR IGNORE INTO semester (id, name) VALUES (2, 'Spring 2025');";
        String insertAYearSQL = "INSERT OR IGNORE INTO aYear (id, name) VALUES (2, '2025-2026');";
        String insertGuestSQL = "INSERT OR IGNORE INTO guests (id, fname, lname) VALUES (2, 'Alice', 'Smith'), (3, 'Bob', 'Johnson');";
        String insertCourseEvalSQL = "INSERT OR IGNORE INTO courseEval (id, courseId, semesterId, aYearId) VALUES (2, 201, 2, 2), (3, 202, 2, 2);";
        String insertEvalQuestionSQL = "INSERT OR IGNORE INTO evalQuestions (id, question, type) VALUES (2, 'Was the course material well-organized?', 1), (3, 'Would you recommend this course?', 1);";
        String insertGuestEvalSQL = "INSERT OR IGNORE INTO guestEval (id, guestId, courseId, semesterId, aYearId) VALUES (2, 2, 201, 2, 2), (3, 3, 202, 2, 2);";
        String insertEvalAnswerSQL = "INSERT OR IGNORE INTO evalAnswers (guestEvalId, courseEvalId, evalQuestionId, answer) VALUES (2, 2, 2, 'Yes'), (3, 3, 3, 'No');";
        String insertGradeSQL = "INSERT OR IGNORE INTO grades (id, studentId, courseId, semesterId, aYearId, grade, retake) VALUES (2, 2, 201, 2, 2, 'B+', 0), (3, 3, 202, 2, 2, 'A-', 1);";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement()) {
            stmt.execute(insertCourseSQL);
            stmt.execute(insertSemesterSQL);
            stmt.execute(insertAYearSQL);
            stmt.execute(insertGuestSQL);
            stmt.execute(insertCourseEvalSQL);
            stmt.execute(insertEvalQuestionSQL);
            stmt.execute(insertGuestEvalSQL);
            stmt.execute(insertEvalAnswerSQL);
            stmt.execute(insertGradeSQL);

            System.out.println("New test data inserted successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
