import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
/***************************************************************
 * GUI front end to a game of Craps.
 *
 * @author Scott Grissom
 * @version February 14, 2017
 ***************************************************************/
public class CrapsGUI extends JFrame implements ActionListener {

    /** visual representation of the dice */
    GVdie d1, d2;

    /** buttons */
    JButton comeOutButton, rollButton;

    /** labels for message and credits */
    JLabel message, credits, point;

    /** the game of craps object */
    Craps game;

    /****************************************************************
     Create all elements and display within the GUI
     @param args
     ****************************************************************/
    public static void main(final String[] args) {
        CrapsGUI gui = new CrapsGUI();
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gui.setTitle("Ben Payne Craps Game");
        gui.setBackground(Color.getColor("#E3E3E3"));
        gui.pack();
        gui.setVisible(true);
    }

    /****************************************************************
     Create all elements and display within the GUI
     ****************************************************************/
    public CrapsGUI(){

        // create the game object as well as the GUI Frame
        game = new Craps();

        Color diceBackground = Color.decode("#FFA225");
        Color diceForeground = Color.decode("#214AB2");

        d1 = game.getDie(1);
        d1.setBackground(diceBackground);
        d1.setForeground(diceForeground);
        d1.setBorder(javax.swing.BorderFactory.createEmptyBorder());

        d2 = game.getDie(2);
        d2.setBackground(Color.BLACK);
        d2.setForeground(Color.RED);
        d2.setBorder(javax.swing.BorderFactory.createEmptyBorder());

        // set the layout to GridBag
        setLayout(new GridBagLayout());
        GridBagConstraints position = new GridBagConstraints();

        final int padding = 15;

        position.insets = new Insets(padding, padding, padding, padding);

        // create and place the message label
        message = new JLabel();
        message.setText(game.getMessage());
        position.gridx = 0;
        position.gridy = 0;
        position.gridwidth = 2;
        add(message, position);

        // get Die #1 from game and place on frame
        position.gridx = 0;
        position.gridy = 1;
        position.gridwidth = 1;
        add(d1, position);

        // get Die #2 from game and place on frame
        d2.setBackground(diceBackground);
        d2.setForeground(diceForeground);
        position.gridx = 1;
        position.gridy = 1;
        position.gridwidth = 1;
        add(d2, position);

        // instantiate and place the Come Out button
        comeOutButton = new JButton("Come Out");
        position.gridx = 0;
        position.gridy = 3;
        position.gridwidth = 1;
        add(comeOutButton, position);

        // instantiate and place the Roll button
        rollButton = new JButton("Roll");
        position.gridx = 1;
        position.gridy = 3;
        position.gridwidth = 1;
        add(rollButton, position);

        // instantiate and position the Credits label
        credits = new JLabel();
        credits.setText("Credits: " + game.getCredits());
        position.gridx = 0;
        position.gridy = 2;
        position.gridwidth = 1;
        add(credits, position);

        // create and place the current point
        point = new JLabel();
        int gamePoint = (game.getPoint() > 0) ? game.getPoint() : 0;
        point.setText("Point: " + gamePoint);
        position.gridx = 1;
        position.gridy = 2;
        position.gridwidth = 1;
        add(point, position);

        // register the action listeners for both buttons
        rollButton.addActionListener(this);
        comeOutButton.addActionListener(this);

        // disable roll button at first
        rollButton.setEnabled(false);
    }

    /****************************************************************
     Inner class to respond to the user action.

     @param event - the JComponent just selected
     ****************************************************************/
    public void actionPerformed(final ActionEvent event) {
        // test for roll button and invoke game.roll()
        if (event.getSource() == comeOutButton) {
            game.comeOut();
        }

        // test for come out button and invoke game.comeOut()
        if (event.getSource() == rollButton) {
            game.roll();
        }

        // enable/disable each button based on status of game
        if (game.okToRoll()) {
            rollButton.setEnabled(true);
            comeOutButton.setEnabled(false);
        } else {
            rollButton.setEnabled(false);
            comeOutButton.setEnabled(true);
        }

        // update credits and the message
        credits.setText("Credits: " + game.getCredits());
        message.setText(game.getMessage());

        // update the current point
        int gamePoint = (game.getPoint() > 0) ? game.getPoint() : 0;
        point.setText("Point: " + gamePoint);
    }
}
