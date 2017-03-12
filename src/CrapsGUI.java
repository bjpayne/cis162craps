import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
/***************************************************************
 * GUI front end to a game of Craps.
 *
 * @author Scott Grissom
 * @version February 14, 2017
 ***************************************************************/
public class CrapsGUI extends JFrame implements ActionListener{

    /** visual representation of the dice */
    GVdie d1, d2;

    /** buttons */
    JButton comeOutButton;

    /** labels for message and credits */
    JLabel message, credits;

    /** the game of craps object */
    Craps game;

    /****************************************************************
     Create all elements and display within the GUI
     ****************************************************************/
    public static void main(String arg[]){
        CrapsGUI gui = new CrapsGUI();
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gui.setTitle("A Generic Title");
        gui.pack();
        gui.setVisible(true);
    }

    /****************************************************************
     Create all elements and display within the GUI
     ****************************************************************/
    public CrapsGUI(){

        // create the game object as well as the GUI Frame
        game = new Craps();

        // set the layout to GridBag
        setLayout(new GridBagLayout());
        GridBagConstraints position = new GridBagConstraints();

        // create and place the message label
        message = new JLabel();
        message.setText(game.getMessage());
        position.gridx = 0;
        position.gridy = 0;
        add(message, position);

        // get Die #1 from game and place on frame
        d1 = game.getDie(1);
        position.gridx = 0;
        position.gridy = 1;
        add(d1, position);

        // FIX ME: get Die #2 from game and place on frame


        // instantiate and place the Come Out button
        comeOutButton = new JButton("Come Out");
        position.gridx = 0;
        position.gridy = 3;
        add(comeOutButton, position);

        // FIX ME: instantiate and place the Roll button


        // instantiate and position the Credits label
        credits = new JLabel();
        credits.setText("Credits: " + game.getCredits());
        position.gridx = 0;
        position.gridy = 2;
        add(credits, position);

        // FIX ME: register the action listeners for both buttons


    }


    /****************************************************************
     Inner class to repond to the user action

     @param e - the JComponent just selected
     ****************************************************************/
    public void actionPerformed(ActionEvent event){

        // FIX ME: test for roll button and invoke game.roll()

        // FIX ME: test for come out button and invoke game.comeOut()

        // FIX ME: enable/disable each button based on status of game

        //update credits and the message
        credits.setText("Credits: " + game.getCredits());
        message.setText(game.getMessage());
    }
}
