package src;

public class Main {
    public static void main(String[] args) {
        DatabaseManager.initializeDatabase();

        /*
        * CRUD OPERATIONS:
        * UNCOMMENT ONE-BY-ONE TO TEST EACH OPERATION!!
        */

        //DatabaseManager.insertTestData("course", "code, name", "206, 'Software Craftsmanship'");
        //DatabaseManager.selectData("evalQuestions");
        //DatabaseManager.updateData("course", "name", "Advanced Software Engineering", "code = 205");
        //DatabaseManager.deleteData("guests", "id = 3");

        GUI app = new GUI();
        app.show();
    }
}