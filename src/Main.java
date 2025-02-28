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

        //READ (saves the data into an array list)
        List<List<String>> guests = DatabaseManager.selectData("guests", "id, fname, lname");
        System.out.println(guests);

        //UPDATE
        //DatabaseManager.updateData("course", "name", "Advanced Java Programming 2", "code = 390");

        //DELETE
        //DatabaseManager.deleteData("course", "code = 390");

        GUI app = new GUI();
        app.show();
    }
}