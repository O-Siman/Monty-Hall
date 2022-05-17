import java.util.ArrayList;
import java.util.Random;

public class Main {
    // Change these numbers!
    static final int runs = 10000;
    static final int doorCount = 3;
    
    // Code starts below

    static final Random random = new Random();

    public static void main(String[] args) {
        System.out.println("Switching: " + (double) gameLoop(true) / runs);
        System.out.println("Not switching: " + (double) gameLoop(false) / runs);
    }

    public static int gameLoop(boolean shouldSwitch) {
        int wins = 0;

        // Game loop
        for (int i = 0; i < runs; i++) {
            // Randomize door contents
            // [false, true, false] means car is in door 2 (index 1)
            boolean[] doorsAndContents = new boolean[doorCount];
            int doorWithCar = random.nextInt(doorCount);
            doorsAndContents[doorWithCar] = true;

            // Player chooses a door
            int doorChosen = random.nextInt(doorCount);
            // System.out.println("We chose: " + doorChosen);

            // Gather up all the unchosen doors that have goats in them
            ArrayList<Integer> remainingGoatDoors = new ArrayList<>();
            for (int j = 0; j < doorsAndContents.length; j++) {
                // If we didn't choose it, and it == goat
                if ((doorChosen != j) && (doorsAndContents[j] == false)) {
                    remainingGoatDoors.add(j);
                }
            }
            
            // Choose all but two of these randomly to be revealed to the player
            ArrayList<Integer> knownGoats = new ArrayList<>();
            for (int j = 0; j < doorCount - 2; j++) {
                knownGoats.add(remainingGoatDoors.remove(random.nextInt(remainingGoatDoors.size())));
            }

            // Switch doors if we need to
            if (shouldSwitch) {
                for (int j = 0; j < doorsAndContents.length; j++) {
                    // Leftover door
                    if ((j != doorChosen) && (!knownGoats.contains(j))) {
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
