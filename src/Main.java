package src;

import java.util.List;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        DatabaseManager.initializeDatabase();

        /*
        * CRUD OPERATIONS:
        * UNCOMMENT ONE-BY-ONE TO TEST EACH OPERATION!!
        */

        //CREATE
        //DatabaseManager.insertTestData("course", "code, name", "390, 'Advanced Java Programming'");

        //READ
        //List<List<String>> courses = DatabaseManager.selectData("course", "code, name");
        //System.out.println(courses);

        //UPDATE
        //DatabaseManager.updateData("course", "name", "Advanced Java Programming 2", "code = 390");

        //DELETE
        //DatabaseManager.deleteData("course", "code = 390");

        GUI app = new GUI();
        app.show();
    }
}