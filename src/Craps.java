import java.util.stream.IntStream;

/*****************************************************************
 Craps class to manage logic of the craps game.
 @author Ben Payne
 @version 03-01-2017
 *****************************************************************/
public class Craps {
    private GVdie[] gVdies = {new GVdie(), new GVdie()};

    private int currentPoint;

    private int creditBalance;

    private String currentMessage;

    private boolean canComeOut;

    private static final int INITIAL_CREDITS = 10;

    private static final int[] COME_OUT_WIN = {7, 11};

    private static final int[] COME_OUT_LOSS = {2, 3, 12};

    private static final int ROLL_LOSS = 7;

    private static final int TURN_START_POINT = -1;

    private int diceTotal;
    /*****************************************************************
     Constructor creates the Craps game.
     *****************************************************************/
    public Craps() {
        this.currentPoint = TURN_START_POINT;

        this.creditBalance = INITIAL_CREDITS;

        this.currentMessage = "Roll come out roll";

        this.canComeOut = true;
    }

    /*****************************************************************
     Return the current credit balance.

     @return int the current credit balance.
     *****************************************************************/
    public int getCredits() {
        return this.creditBalance;
    }

    /*****************************************************************
     return the current point.

     @return int the current point
     *****************************************************************/
    public int getPoint() {
        return this.currentPoint;
    }

    /*****************************************************************
     Return the current message.

     @return String the current message
     *****************************************************************/
    public String getMessage() {
        return this.currentMessage;
    }

    /*****************************************************************
     Return a specific die.
     @param num the die to get
     @return the current die
     *****************************************************************/
    public GVdie getDie(final int num) {
        return this.gVdies[num];
    }

    /*****************************************************************
     Set the credit balance if the amount is greater than or equal to 0.

     @param amount the amount to set the credit to
     *****************************************************************/
    public void setCredits(final int amount) {
        if (amount >= 0) {
            this.creditBalance = amount;
        }
    }

    /*****************************************************************
     Roll the come out roll if possible.
     *****************************************************************/
    public void comeOut() {
        if (okToRoll() || this.getCredits() <= 0) {
            this.currentMessage = "Please roll.";

            return;
        }

        this.diceTotal = rollDice();

        // Check if the roll was in the COME_OUT_WIN array of values
        if (IntStream.of(COME_OUT_WIN).anyMatch(x -> x == this.diceTotal)) {
            this.creditBalance++;

            this.currentMessage = "You won that turn.";

            this.canComeOut = true;
        } else if (
            IntStream.of(COME_OUT_LOSS).anyMatch(x -> x == this.diceTotal)
        ) {
            this.creditBalance--;

            this.currentMessage = "You lost that turn";

            this.canComeOut = true;
        } else {
            this.currentPoint = this.diceTotal;

            this.currentMessage = "Please roll.";

            this.canComeOut = false;
        }
    }

    /*****************************************************************
     roll the dice.
     *****************************************************************/
    public void roll() {
        if (! okToRoll()) {
            this.currentMessage = "Please come out.";

            return;
        }

        this.diceTotal = this.rollDice();

        if (this.diceTotal == ROLL_LOSS) {
            this.creditBalance--;

            this.currentPoint = TURN_START_POINT;

            this.currentMessage = "You lost that turn.";

            this.canComeOut = true;
        } else if (this.diceTotal == this.currentPoint) {
            this.creditBalance++;

            this.currentPoint = TURN_START_POINT;

            this.currentMessage = "You won that turn";
        } else {
            this.currentMessage = "Please roll again";
        }
    }

    /*****************************************************************
     check if it is ok to roll or come out.
     @return if it is ok to roll.
     *****************************************************************/
    public boolean okToRoll() {
        return ! this.canComeOut;
    }

    /*****************************************************************
     roll the dice.
     @return the dice total
     *****************************************************************/
    private int rollDice() {
        int diceTotal = 0;

        for (GVdie die : this.gVdies) {
            die.roll();

            diceTotal += die.getValue();
        }

        return diceTotal;
    }

    public int getDiceTotal() {
        return diceTotal;
    }
}
