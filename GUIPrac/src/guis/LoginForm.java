package guis;

// _________________________________________

import constants.Constants;
import javax.swing.*;
import java.awt.*;

// _________________________________________

public class LoginForm extends Form { // Subclass

    // Attributes

    // _________________________________________

    public LoginForm(){

        super("Version 0.1.0");
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
        JLabel loginLabel = new JLabel("Log In");
        loginLabel.setBounds(getWidth()/2, 150, 300, 100);
        loginLabel.setForeground(Constants.GREY_COLOR); // Font color
        loginLabel.setFont(Constants.fontOne);
        loginLabel.setHorizontalAlignment(SwingConstants.CENTER); // Center text

        JLabel versionControl = new JLabel("Version 0.1.0");
        versionControl.setBounds(800, 600, 150, 50);
        versionControl.setForeground(Constants.GREY_COLOR);
        versionControl.setFont(Constants.fontVersion);
        versionControl.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel loginTitle = new JLabel("<html><center>PLACEHOLDER<br>______________</center></html>");
        loginTitle.setBounds(15, 100, 300, 300);
        loginTitle.setForeground(Constants.GREY_COLOR);
        loginTitle.setFont(Constants.fontTwo);
        loginTitle.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel loginInfo = new JLabel("<html><center>TEXT PLACEHOLDER</center></html>");
        loginInfo.setBounds(15, 200, 300, 300);
        loginInfo.setForeground(Constants.GREY_COLOR);
        loginInfo.setFont(Constants.fontInfo);
        loginInfo.setHorizontalAlignment(SwingConstants.CENTER);

        // JTextFields
        JTextField usernameField = new RoundedEdgesTextField(30);
        usernameField.setBounds(500, 250, 300, 50);
        usernameField.setBackground(Constants.GREY_COLOR);
        usernameField.setForeground(Constants.WHITE_COLOR);
        usernameField.setFont(Constants.fontForm);

        JTextField passwordField = new RoundedEdgesTextField(30);
        passwordField.setBounds(500, 330, 300, 50);
        passwordField.setBackground(Constants.GREY_COLOR);
        passwordField.setForeground(Constants.WHITE_COLOR);
        passwordField.setFont(Constants.fontForm);

        // Buttons
        JButton registerButton = new RoundedEdgesButton("Log In");
        registerButton.setBounds(500, 410, 145, 50);
        registerButton.setBackground(Constants.GREY_COLOR);
        registerButton.setForeground(Constants.WHITE_COLOR);
        registerButton.setFont(Constants.fontForm);

        JButton loginButton = new RoundedEdgesButton("Register");
        loginButton.setBounds(655, 410, 145, 50);
        loginButton.setBackground(Constants.GREY_COLOR);
        loginButton.setForeground(Constants.WHITE_COLOR);
        loginButton.setFont(Constants.fontForm);

        // Add components

        add(loginLabel);
        add(loginTitle);
        add(loginInfo);
        add(versionControl);

        add(usernameField);
        add(passwordField);

        add(registerButton);
        add(loginButton);

        add(orangeRect);

    }

}
