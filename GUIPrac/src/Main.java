/*

    Made by: CPHJL325 // Guacamoleboy
    Version 0.1.0
    Date: 31/03-2025

*/

import javax.swing.*;
import guis.LoginForm;
import guis.RegisterForm;
import guis.ForgotPassword;

// _________________________________________

public class Main {
    public static Program program; // Client class

    // Attributes

    // _________________________________________

    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {

                new LoginForm().setVisible(true);
                new RegisterForm().setVisible(false);
                new ForgotPassword().setVisible(false);

            }

        });

    } // Main end

}