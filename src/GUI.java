package src;

import javax.swing.*;
import java.awt.*;

public class GUI {

    public void show() {
        // Main frame setup
        JFrame frame = new JFrame("SFU PA Survey Report");
        frame.setSize(800, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null); // Center the window

        // Create a panel for the navigation bar
        JPanel navPanel = new JPanel();
        navPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        navPanel.setBackground(new Color(0x3A3A3A)); // Dark background color for buttons

        // Create navigation buttons with simple style
        JButton courseSurveysButton = new JButton("Course Surveys");
        JButton guestSurveysButton = new JButton("Guest Surveys");
        JButton gradeInputsButton = new JButton("Grade Inputs");
        JButton databaseButton = new JButton("Database");
        JButton generateReportButton = new JButton("Generate Report");

        // Add buttons to the navigation panel
        navPanel.add(courseSurveysButton);
        navPanel.add(guestSurveysButton);
        navPanel.add(gradeInputsButton);
        navPanel.add(databaseButton);
        navPanel.add(generateReportButton);

        // Create a container for the pages (we will just show a simple panel here)
        JPanel cardPanel = new JPanel();
        cardPanel.add(new JLabel("Content Area"));

        // Set the layout of the frame and add components
        frame.setLayout(new BorderLayout());
        frame.add(navPanel, BorderLayout.NORTH);
        frame.add(cardPanel, BorderLayout.CENTER);

        // Display the frame
        frame.setVisible(true);
    }

}
