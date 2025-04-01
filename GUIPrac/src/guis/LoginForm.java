package guis;

// _________________________________________

import constants.Constants;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

// _________________________________________

public class LoginForm extends Form { // Subclass

    // Attributes
    private JPanel blurrPanel;

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

        exitByESC(orangeRect);
        createBlurPanel();

    }

    // _________________________________________

    public void exitByESC(JPanel panel){

        // Exit prompt setup

        InputMap im = panel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap am = panel.getActionMap();

        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "exit");
        am.put("exit", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exitPrompt();
            }
        });

    }

    // _________________________________________

    public void exitPrompt(){

        blurrPanel.setVisible(true);

        JDialog dialog = new JDialog(this, "Confirm Exit", true);
        dialog.setSize(500, 300);
        dialog.setLocationRelativeTo(this);
        dialog.setUndecorated(true);
        dialog.setBackground(new Color(0, 0, 0, 0));

        JPanel panel = new JPanel(){
          @Override
          protected void paintComponent(Graphics g) {

            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            g2.setColor(Constants.GREY_COLOR);
            g2.fillRoundRect(0,0, getWidth(), getHeight(), 30, 30);

            // Basically makes a new panel and draws it at a slight shift
            g2.setColor(Constants.WHITE_COLOR);
            g2.setStroke(new BasicStroke(3)); // Border
            g2.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, 30, 30);

          }
        };

        panel.setLayout(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panel.setOpaque(false);

        JLabel msg = new JLabel("Trying to escape?..");
        msg.setForeground(Constants.WHITE_COLOR);
        msg.setFont(Constants.fontTwo);

        JButton yes = new RoundedEdgesButton("Yes");
        JButton back = new RoundedEdgesButton("Back");

        buttonVisuals(yes, Constants.ORANGE_COLOR);
        buttonVisuals(back, Constants.ORANGE_COLOR);

        yes.addActionListener(e -> System.exit(0));
        back.addActionListener(e -> {
            dialog.dispose();
            blurrPanel.setVisible(false);
        }); // Optimize

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridy = 0;
        panel.add(msg, gbc);

        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(yes, gbc);

        gbc.gridy = 2;
        panel.add(back, gbc);

        dialog.add(panel);
        dialog.setVisible(true);

    }

    public void buttonVisuals(JButton button, Color color){

        button.setFont(Constants.fontVersion);
        button.setFocusPainted(false);
        button.setOpaque(true);
        button.setContentAreaFilled(false);
        button.setBackground(color);
        button.setForeground(Constants.WHITE_COLOR);
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

    }

    public void createBlurPanel(){

        blurrPanel = new JPanel(){
            @Override
            protected void paintComponent(Graphics g){
                super.paintComponents(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(new Color(0, 0, 0, 175));
                g2.fillRect(0, 0, getWidth(), getHeight());

            }
        };

        blurrPanel.setOpaque(false);
        blurrPanel.setVisible(false);
        blurrPanel.setLayout(new GridBagLayout());
        setGlassPane(blurrPanel);

    }

}
