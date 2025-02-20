package src;

public class Main {
    public static void main(String[] args) {
        DatabaseManager.initializeDatabase();
        GUI app = new GUI();
        app.show();
    }
}
