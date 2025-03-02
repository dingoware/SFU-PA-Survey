package src;

import javax.swing.*;

public class display extends JFrame {
    //create GUI components
    private JTabbedPane tabbedPane1;
    private JPanel panel1;
    private JButton editButton;
    private JButton deleteButton;
    private JButton saveButton;
    private JList list1;
    private JRadioButton runGuestReportRadioButton;
    private JRadioButton runCourseReportRadioButton;
    private JPanel dbPanel;
    private JPanel csurvPanel;
    private JPanel gsurvPanel;
    private JPanel gradePanel;
    private JPanel reportPanel;

    public display() {
        setTitle("PA Survey Database");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //add panels to tabbedPane
        tabbedPane1.addTab("Database", dbPanel);
        tabbedPane1.add("Course Surveys", csurvPanel);
        tabbedPane1.add("Guest Surveys", gsurvPanel);
        tabbedPane1.add("Grade Inputs", gradePanel);
        tabbedPane1.add("Generate Report", reportPanel);

        //add panel to display
        add(tabbedPane1);
    }

    public static void main(String[] args) {
        display gui = new display();
        gui.setVisible(true);
    }
}

