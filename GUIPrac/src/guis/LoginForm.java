package guis;

// _________________________________________

import constants.Constants;
import javax.swing.*;
import java.awt.*;
import static java.awt.Font.*;

// _________________________________________

public class LoginForm extends Form { // Subclass

    // Attributes

    Font fontOne = new Font("Arial", BOLD, 40);
    Font fontVersion = new Font("Arial", BOLD, 10);
    Font fontTwo = new Font("Arial", BOLD, 25);
    Font fontInfo = new Font("Arial", BOLD, 15);
    Font fontForm = new Font("Arial", Font.PLAIN, 20);

    // _________________________________________

    public LoginForm(){

        super("Login");
        getContentPane().setBackground(Constants.PRIMARY_COLOR);
        components();

    } // Constructor

    // _________________________________________

    public void components(){

        JPanel orangeRect = new JPanel(){

            @Override
            protected void paintComponent(Graphics g){
                super.paintComponent(g);

                // Orange RECT
                g.setColor(Constants.ORANGE_COLOR);
                g.fillRect(0,0, getWidth(), getHeight()); // Colors panel

            }

        };

        orangeRect.setBounds(0, 0, getWidth()/3, getHeight()); // Sets location and bounds
        orangeRect.setLayout(null);
        // Sets border but ONLY on the right side
        orangeRect.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 2, Constants.GREY_COLOR));

        // JLabels
        JLabel loginLabel = new JLabel("Log in");
        loginLabel.setBounds(getWidth()/2, 100, 300, 100);
        loginLabel.setForeground(Constants.GREY_COLOR); // Font color
        loginLabel.setFont(fontOne);
        loginLabel.setHorizontalAlignment(SwingConstants.CENTER); // Center text

        JLabel versionControl = new JLabel("Version 0.1.0");
        versionControl.setBounds(850, 600, 100, 50);
        versionControl.setForeground(Constants.BLACK_COLOR);
        versionControl.setFont(fontVersion);
        versionControl.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel loginTitle = new JLabel("<html><center>Welcome to Drakeflix<br>______________</center></html>");
        loginTitle.setBounds(15, 100, 300, 300);
        loginTitle.setForeground(Constants.GREY_COLOR);
        loginTitle.setFont(fontTwo);
        loginTitle.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel loginInfo = new JLabel("<html><center>This program was made by<br>Guacamoleboy<br>GUI Prac 1.0<br>SP3</center></html>");
        loginInfo.setBounds(15, 200, 300, 300);
        loginInfo.setForeground(Constants.GREY_COLOR);
        loginInfo.setFont(fontInfo);
        loginInfo.setHorizontalAlignment(SwingConstants.CENTER);

        // JTextFields
        JTextField usernameField = new RoundedEdges(30);
        usernameField.setBounds(500, 200, 300, 50);
        usernameField.setBackground(Constants.GREY_COLOR);
        usernameField.setForeground(Constants.WHITE_COLOR);
        usernameField.setFont(fontForm);

        JTextField passwordField = new RoundedEdges(30);
        passwordField.setBounds(500, 280, 300, 50);
        passwordField.setBackground(Constants.GREY_COLOR);
        passwordField.setForeground(Constants.WHITE_COLOR);
        passwordField.setFont(fontForm);

        // Add components
        add(loginLabel);
        add(loginTitle);
        add(loginInfo);
        add(versionControl);

        add(usernameField);
        add(passwordField);

        add(orangeRect);

    }

}
