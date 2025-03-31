package constants;

// _________________________________________

import java.awt.*;

import static java.awt.Font.BOLD;

// _________________________________________

public class Constants { // All these values are "final" or constant

    // Colors
    public static final Color PRIMARY_COLOR = Color.decode("#D7D7D7");
    public static final Color GREY_COLOR = Color.decode("#808080");
    public static final Color ORANGE_COLOR = Color.decode("#FFBF00");
    public static final Color BLACK_COLOR = Color.decode("#000000");
    public static final Color WHITE_COLOR = Color.decode("#FFFFFF");
    public static final Color RED_COLOR = Color.decode("#AA4A44");
    public static final Color BLUE_COLOR = Color.decode("#6495ED");
    public static final Color PURPLE_COLOR = Color.decode("#3F00FF");

    // Fonts
    public static Font fontOne = new Font("Arial", BOLD, 40);
    public static Font fontVersion = new Font("Arial", BOLD, 20);
    public static Font fontTwo = new Font("Arial", BOLD, 25);
    public static Font fontInfo = new Font("Arial", BOLD, 15);
    public static Font fontForm = new Font("Arial", Font.PLAIN, 20);


} // Constants end
