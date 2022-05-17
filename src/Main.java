import java.util.Random;

public class Main {
    static final Random random = new Random();

    public static void main(String[] args) {
        int runs = 10000;
        System.out.println("Switching: " + (double) gameLoop(runs, true) / runs);
        System.out.println("Not switching: " + (double) gameLoop(runs, false) / runs);
    }

    public static int gameLoop(int runs, boolean shouldSwitch) {
        int wins = 0;

        // Game loop
        for (int i = 0; i < runs; i++) {
            // Randomize door contents
            boolean[] doorsAndContents = new boolean[3];
            int doorWithCar = random.nextInt(3);
            doorsAndContents[doorWithCar] = true;

            // Player chooses a door
            int doorChosen = random.nextInt(3);
            // System.out.println("We chose: " + doorChosen);

            // Show the goat to the player
            int knownGoat = -1;
            for (int j = 0; j < doorsAndContents.length; j++) {
                if ((doorChosen != j) && (doorsAndContents[j] == false)) {
                    knownGoat = j;
                    break; // Not really necessary though
                }
            }

            // System.out.println("Goat at: " + knownGoat);

            // Switch doors if we need to
            if (shouldSwitch) {
                for (int j = 0; j < doorsAndContents.length; j++) {
                    // Leftover door
                    if ((j != doorChosen) && (j != knownGoat)) {
                        // Switch doors
                        doorChosen = j;
                        break;
                    }
                }
            }

            if (doorsAndContents[doorChosen] == true) {
                // System.out.println("Win");
                wins++;
            } else {
                // System.out.println("Loss");
            }
        }
        return wins;
    }
}
