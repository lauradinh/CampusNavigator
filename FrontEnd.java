  
// --== CS400 File Header Information ==--
// Name: Timothy Voelker
// Email: tvoelker@wisc.edu
// Team: ED
// TA: Keren Chen
// Lecturer: Florian Heimerl

import java.io.IOException;

/**
 * Class that holds the entry point and frontend logic for the UW Campus
 * Navigator app.
 * 
 * @author Timothy Voelker
 */
public class FrontEnd {
    private static BackEnd backend;
    private static String[] locations;

    public static void main(String[] args) {
        try {
            backend = new BackEnd();
        } catch (Exception e) {
            clearConsole();
            // Show the error to the user
            Input.get("ERROR: File wasn't able to be read.\n\nPress <Enter> to exit the app gracefully.");
            return;
        }

        // Grab the location options from the backend
        locations = backend.getLocations().toArray(String[]::new);

        run();
    }

    /**
     * Starts the application and orchestrates the front end.
     */
    private static void run() {
        String res;
        boolean confirmQuit = false;

        clearConsole();
        System.out.println("Welcome to the UW Campus Navigator!\n\n");

        do {
            res = getMainMenuChoice();
            clearConsole();

            switch (res) {
                case "1":
                    oneTrip();
                    break;
                case "2":
                    multipleTrips();
                    break;
                default:
                    // Confirm that the user wants to quit
                    String confirmRes = Input.getStringFrom("Are you sure you want to quit?",
                            new String[] { "Yes", "y", "No", "n" });

                    confirmQuit = confirmRes.substring(0, 1).equalsIgnoreCase("y");
                    break;
            }

            clearConsole();
        } while (!res.substring(0, 1).equalsIgnoreCase("q") || !confirmQuit);

        clearConsole();
        System.out.println("Thank you for using the UW Campus Navigator!");
    }

    /**
     * Prompts the user to enter two locations (starting and ending) and shows them
     * the shortest path between them.
     * 
     * @param startTitle the title displayed when grabbing the start location.
     * @param endTitle   the title displayed when grabbing the destination.
     */
    private static void oneTrip(String startTitle, String endTitle) {
        clearConsole();
        String start = getLocation(startTitle, "Please select a starting location.");
        clearConsole();
        String end = getLocation(endTitle, "Please select a destination.");
        clearConsole();

        // Show the shortest path to the user
        Input.get(String.format(
                "The shortest path from %s to %s.\n\nPath:\n\t%s\n\nDistance: %d blocks.\n\n(Press <Enter> to continue)",
                start, end, backend.shortestDistance(start, end), backend.getDistanceCost(start, end)));
    }

    /**
     * Overloaded method with default values for startTitle and endTitle.
     */
    private static void oneTrip() {
        oneTrip("Starting Location.", "Destination.");
    }

    /**
     * Prompts the user to specify the number of trips they want to make, then asks
     * them to enter two locations (starting and ending) and shows them the shortest
     * path between them. This then repeats for the number of trips they responded
     * with.
     */
    private static void multipleTrips() {
        clearConsole();
        int numTrips = Input.getPositiveNumber("Enter the number of trips you want to take.");
        clearConsole();

        for (int i = 0; i < numTrips; i++) {
            // After every 5 locations, ask the user if they want to continue
            if (i > 0 && i % 5 == 0) {
                clearConsole();
                String keepGoing = Input.getStringFrom("Do you still want to continue (<Enter> to continue)?",
                        new String[] { "Yes", "y", "No", "n", "" });

                if (keepGoing.equalsIgnoreCase("No") || keepGoing.equalsIgnoreCase("n")) {
                    return;
                }
            }

            oneTrip(String.format("Starting Location (#%d/%d).", i + 1, numTrips),
                    String.format("Destination (#%d/%d)", i + 1, numTrips));
        }
    }

    /*******************/
    /* UTILITY METHODS */
    /*******************/

    /**
     * Gets a location from the user.
     * 
     * @param message prompt to display when asking for location.
     * @return location selection from the user.
     */
    static String getLocation(String title, String message) {
        System.out.printf("%s\n\n", title);

        // Print all of the locations
        for (int i = 0; i < locations.length; i++) {
            System.out.printf("[%d] %s\n", i + 1, locations[i]);
        }

        // Get the user's location decision
        int locationId = Input.getNumberBetween(String.format("\n%s", message), 1, locations.length) - 1;

        return locations[locationId];
    }

    /**
     * Prompts the user with the main menu and returns the option they picked.
     * 
     * @return the option the user picks from the main menu.
     */
    static String getMainMenuChoice() {
        return Input.getStringFrom(
                String.format("%s\n\n%s\n%s\n%s\n%s\n", "Main Menu", "Please enter one of the following commands:",
                        "[1] Get route for just one trip", "[2] Get route for multiple trips", "[Q] Quit"),
                new String[] { "1", "2", "Quit", "q" });
    }

    /**
     * Prints a series of newlines to create whitespace in the console.
     */
    private static void clearConsole() {
        System.out.println("\n".repeat(50));
    }
}
