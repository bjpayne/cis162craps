/**
 Tests for Craps game.
*/
public class CrapsTest {
    public static void basicTests() {
        Craps game = new Craps();

        // does game start with 10 credits?
        final int gameCredits = 10;

        if (game.getCredits() != gameCredits) {
            System.out.println("Game should start with 10 credits");
        }

        if (game.okToRoll()) {
            System.out.println("Game should not allow a roll");
        }

        game.comeOut();

        if (! game.okToRoll()) {
            System.out.println("Game should allow a roll");
        }

        final int comeOut = -1;

        if (game.getPoint() != comeOut) {
            System.out.println("Initial game point should be -1");
        }

        // check setCredits()
        final int credits = 5;

        game.setCredits(credits);

        if (game.getCredits() != credits) {
            System.out.println("Game should now have 5 credits");
        }
    }

    public static void autoPlay() {
        Craps game = new Craps();

        int gameCount = 1;

        // Game: 169 Credits: 6
        // You rolled a 7 and lose. Come out to start a new round
        // Turn ends with credits: 5

        while (game.getCredits() > 0) {
            System.out.println(
                "Game: "
                + gameCount
                + " Credits: "
                + game.getCredits()
            );

            game.comeOut();

            System.out.println(
                "Your come out roll was an "
                + game.getDiceTotal()
                + "."
            );

            System.out.println(game.getMessage());

            while (game.okToRoll()) {
                game.roll();

                System.out.println(
                    "You rolled an "
                    + game.getDiceTotal()
                    + "."
                );

                System.out.println(game.getMessage());
            }

            System.out.println(System.lineSeparator());

            gameCount++;
        }
    }
}
