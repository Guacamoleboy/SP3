package guis;

// _________________________________________

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;

// _________________________________________

public class Form extends JFrame{ // Superclass

    // Attributes

    // Can't load directories outside packages or SRC unless you do it like this..
    ImageIcon icon16 = new ImageIcon(new File("assets/logo/icon16.png").getAbsolutePath());
    ImageIcon icon32 = new ImageIcon(new File("assets/logo/icon32.png").getAbsolutePath());
    ImageIcon icon64 = new ImageIcon(new File("assets/logo/icon64.png").getAbsolutePath());

    // ________________________________________

    public Form(String title){

        // Display Settings
        super("Version 0.1.0");

        // Logo & Icons

        ArrayList <Image> logo = new ArrayList<>();

        logo.add(icon16.getImage());
        logo.add(icon32.getImage());
        logo.add(icon64.getImage());

        setIconImages(logo);

        setSize(1000, 700);
        setDefaultCloseOperation(EXIT_ON_CLOSE); // Exit program operation
        setLayout(null);
        setLocationRelativeTo(null);
        setResizable(false); // Prevent movement / resize

    }

} // Superclass end
