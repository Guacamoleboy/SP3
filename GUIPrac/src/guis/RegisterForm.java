package guis;

// _________________________________________

import constants.Constants;
import javax.swing.*;
import java.awt.*;

// _________________________________________

public class RegisterForm extends Form { // Subclass

    // Attributes

    // _________________________________________

    public RegisterForm(){

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
        orangeRect.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 2, Constants.GREY_COLOR));

        // Label
        JLabel loginLabel = new JLabel("Create Account");
        loginLabel.setBounds(getWidth()/2, 20, 300, 100);
        loginLabel.setForeground(Constants.GREY_COLOR); // Font color
        loginLabel.setFont(Constants.fontOne);
        loginLabel.setHorizontalAlignment(SwingConstants.CENTER); // Center text

        JLabel versionControl = new JLabel("Version 0.1.0");
        versionControl.setBounds(800, 600, 150, 50);
        versionControl.setForeground(Constants.GREY_COLOR);
        versionControl.setFont(Constants.fontVersion);
        versionControl.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel loginTitle = new JLabel("<html><center>Make an account<br>______________</center></html>");
        loginTitle.setBounds(15, 100, 300, 300);
        loginTitle.setForeground(Constants.GREY_COLOR);
        loginTitle.setFont(Constants.fontTwo);
        loginTitle.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel loginInfo = new JLabel("<html><center>Please provide data in order<br>to create an account<br><br>Thank you</center></html>");
        loginInfo.setBounds(15, 200, 300, 300);
        loginInfo.setForeground(Constants.GREY_COLOR);
        loginInfo.setFont(Constants.fontInfo);
        loginInfo.setHorizontalAlignment(SwingConstants.CENTER);

        // Text Fields
        JTextField usernameField = new RoundedEdgesTextField(30);
        usernameField.setBounds(500, 120, 300, 50);
        usernameField.setBackground(Constants.GREY_COLOR);
        usernameField.setForeground(Constants.WHITE_COLOR);
        usernameField.setFont(Constants.fontForm);

        JTextField passwordField = new RoundedEdgesTextField(30);
        passwordField.setBounds(500, 200, 300, 50);
        passwordField.setBackground(Constants.GREY_COLOR);
        passwordField.setForeground(Constants.WHITE_COLOR);
        passwordField.setFont(Constants.fontForm);

        JTextField passwordConfirmField = new RoundedEdgesTextField(30);
        passwordConfirmField.setBounds(500, 280, 300, 50);
        passwordConfirmField.setBackground(Constants.GREY_COLOR);
        passwordConfirmField.setForeground(Constants.WHITE_COLOR);
        passwordConfirmField.setFont(Constants.fontForm);

        JTextField ageField = new RoundedEdgesTextField(30);
        ageField.setBounds(500, 360, 300, 50);
        ageField.setBackground(Constants.GREY_COLOR);
        ageField.setForeground(Constants.WHITE_COLOR);
        ageField.setFont(Constants.fontForm);

        /*
        JToggleButton maleButton = new JToggleButton("I'm Male");
        JToggleButton femaleButton = new JToggleButton("I'm Female");
        */

        JButton registerButton = new RoundedEdgesButton("Register");
        registerButton.setBounds(575, 520, 150, 50);
        registerButton.setBackground(Constants.GREY_COLOR);
        registerButton.setForeground(Constants.WHITE_COLOR);
        registerButton.setFont(Constants.fontForm);

        // Add components

        add(loginLabel);
        add(loginTitle);
        add(loginInfo);
        add(versionControl);

        add(usernameField);
        add(passwordField);
        add(passwordConfirmField);
        add(ageField);

        add(registerButton);

        add(orangeRect);

    }

}
