package guis;

// _________________________________________

import javax.swing.*;
import java.awt.*;

// _________________________________________

/*

    Allows soft edges to JButton
    Change curvature to adjust for softer edgess

*/

public class RoundedEdgesButton extends JButton {

    // Attributes
    private int curvature = 25;

    // _________________________________________

    public RoundedEdgesButton(String input){
        super(input);
        setOpaque(false);
        setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
    }

    // _________________________________________

    @Override
    protected void paintComponent(Graphics g){

        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), curvature, curvature);

        super.paintComponent(g2);
        g2.dispose(); // Performance issues if it's not there

    }

    // _________________________________________

    @Override
    protected void paintBorder(Graphics g){

        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Allows us to remove the stupid white border around buttons
        g2.setColor(new Color(0, 0, 0, 0));
        g2.drawRoundRect(0, 0, getWidth(), getHeight(), curvature, curvature);

        g2.dispose(); // Performance issues if it's not there

    }

}
