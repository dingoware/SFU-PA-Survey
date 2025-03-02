package src;

import javax.swing.*;

public class display extends JFrame {
    private JTabbedPane tabbedPane1;
    private JPanel panel1;
    private JButton editButton;
    private JButton deleteButton;
    private JButton saveButton;
    private JList list1;
    private JRadioButton runGuestReportRadioButton;
    private JRadioButton runCourseReportRadioButton;

    public static void main(String[] args) {
        JFrame gui = new JFrame();
        gui.setVisible(true);
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}

