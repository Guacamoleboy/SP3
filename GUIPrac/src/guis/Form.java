package guis;

// _________________________________________

import javax.swing.*;

// _________________________________________

public class Form extends JFrame{ // Superclass

    // Attributes

    // ________________________________________

    public Form(String title){

        // Display Settings
        super(title);
        setSize(1000, 700);
        setDefaultCloseOperation(EXIT_ON_CLOSE); // Exit program operation
        setLayout(null);
        setLocationRelativeTo(null);
        setResizable(false); // Prevent movement / resize

    }

} // Superclass end
