// --== CS400 File Header Information ==--
// Name: Timothy Voelker
// Email: tvoelker@wisc.edu
// Team: ED
// TA: Keren Chen
// Lecturer: Florian Heimerl

import java.util.Scanner;
import java.util.Arrays;
import java.lang.NumberFormatException;

/**
 * Utility class for getting user input from the command line.
 * 
 * @author Timothy Voelker
 */
public class Input {
    // Scanner is used to grab input from the user
    private static final Scanner s = new Scanner(System.in);

    // Console colors
    private static final String RED = "\u001B[31m";
    private static final String RESET_COLOR = "\u001B[0m";

    /************/
    /* Decimals */
    /************/

    /**
     * Gets an decimal number from the user and prompts them to retry if they don't
     * enter a valid one.
     * 
     * @param prompt message that is printed to the user prior to getting input.
     * @return decimal number from the user via the command line.
     */
    public static double getDecimal(String prompt) {
        double number = 0;
        boolean getting = true;
        do {
            try {
                number = Double.valueOf(get(prompt));
                // If a valid integer is chosen, break out of the loop
                getting = false;
            } catch (NumberFormatException e) {
                // Prompt the user to enter a valid integer while one isn't entered
                showError("Please enter a valid decimal number.");
            }
        } while (getting);

        return number;
    }

    /**
     * Gets a decimal number, from an array of possible doubles, from the user and
     * prompts them to retry if they don't enter a valid integer from
     * {@code possibleNumbers}.
     * 
     * @param prompt          message that is printed to the user prior to getting
     *                        input.
     * @param possibleNumbers array of possible inputs for the user.
     * @return decimal number from user in array of {@code possibleNumbers}.
     */
    public static double getDecimalFrom(String prompt, double[] possibleNumbers) {
        double number;
        boolean getting = true;
        do {
            // Get integer from the user
            number = getDecimal(String.format("\n%s\nChoices: %s", prompt + Arrays.toString(possibleNumbers)));
            for (double p : possibleNumbers) {
                if (number == p) {
                    // If the double is in the array of possibleNumbers, break out of the loop
                    getting = false;
                }
            }
            // If the user didn't enter a number from possibleNumbers, prompt them to do so
            if (getting) {
                showError("Enter a number from the provided list.");
            }
        } while (getting);

        return number;
    }

    /**
     * Gets a decmial number, bounded by min and max, from the user and prompts them
     * to enter decimal number between the bounds if they don't.
     * 
     * @param prompt message that is printed to the user prior to getting input.
     * @param min    the minimum bound.
     * @param max    the maximum bound.
     * @return decimal number from user between {@code min} and {@code max}.
     */
    public static double getDecimalBetween(String prompt, double min, double max) {
        double number;

        do {
            // Get double from the user
            number = getDecimal(String.format("\n%s\n(%f-%f)", prompt, min, max));
            // If the number isn't between max and min, prompt the user to enter one between
            // them
            if (number < min || number > max) {
                showError(String.format("Please enter a number between %f and %f.", min, max));
            }
        } while (number < min || number > max);

        return number;
    }

    /***********/
    /* Numbers */
    /***********/

    /**
     * Gets an integer from the user and prompts them to retry if they don't enter a
     * valid one.
     * 
     * @param prompt message that is printed to the user prior to getting input.
     * @return integer number from the user via the command line.
     */
    public static int getNumber(String prompt) {
        int number = 0;
        boolean getting = true;
        do {
            try {
                number = Integer.valueOf(get(prompt));
                // If a valid integer is chosen, break out of the loop
                getting = false;
            } catch (NumberFormatException e) {
                // Prompt the user to enter a valid integer while one isn't entered
                showError("Please enter a valid whole number.");
            }
        } while (getting);

        return number;
    }

    /**
     * Gets an integer, from an array of possible integers, from the user and
     * prompts them to retry if they don't enter a valid integer from
     * {@code possibleNumbers}.
     * 
     * @param prompt          message that is printed to the user prior to getting
     *                        input.
     * @param possibleNumbers array of possible inputs for the user.
     * @return integer number from user in array of {@code possibleNumbers}.
     */
    public static int getNumberFrom(String prompt, int[] possibleNumbers) {
        int number;
        boolean getting = true;
        do {
            // Get integer from the user
            number = getNumber(String.format("\n%s\nChoices: %s", prompt, Arrays.toString(possibleNumbers)));
            for (int p : possibleNumbers) {
                if (number == p) {
                    // If the integer is in the array of possibleNumbers, break out of the loop
                    getting = false;
                }
            }
            // If the user didn't enter a number from possibleNumbers, prompt them to do so
            if (getting) {
                showError("Enter a number from the provided list.");
            }
        } while (getting);

        return number;
    }

    /**
     * Gets an integer, bounded by min and max, from the user and prompts them to
     * enter an integer between the bounds if they don't.
     * 
     * @param prompt message that is printed to the user prior to getting input.
     * @param min    the minimum bound.
     * @param max    the maximum bound.
     * @return integer number from user between {@code min} and {@code max}.
     */
    public static int getNumberBetween(String prompt, int min, int max) {
        int number;

        do {
            // Get integer from the user
            number = getNumber(String.format("\n%s\n(%d - %d)", prompt, min, max));
            // If the number isn't between max and min, prompt the user to enter one between
            // them
            if (number < min || number > max) {
                showError(String.format("Please enter a whole number between %d and %d.", min, max));
            }
        } while (number < min || number > max);

        return number;
    }

    /**
     * Gets a positive integer from the user and promts them to enter one if they
     * don't.
     * 
     * @param prompt message that is printed to the user prior to getting input.
     * @return positive integer number from the user.
     */
    public static int getPositiveNumber(String prompt) {
        int number;

        do {
            // Get integer from the user
            number = getNumber(String.format("\n%s\n(positive whole number)", prompt));

            if (number < 0) {
                showError("Please enter a positive whole number.");
            }
        } while (number < 0);

        return number;
    }

    /***********/
    /* Strings */
    /***********/

    /**
     * Gets a string, from array of possible strings, from the user and prompts them
     * to enter a valid string from {@code possibleStrings} if they don't.
     * 
     * @param prompt          message that is printed to the user prior to getting
     *                        input.
     * @param possibleStrings array of possible inputs for the user.
     * @return string from user in array of {@code possibleStrings}.
     */
    public static String getStringFrom(String prompt, String[] possibleStrings) {
        String s;
        boolean getting = true;
        do {
            // Get string from the user
            s = get(String.format("\n%s\nChoices: %s", prompt, Arrays.toString(possibleStrings)));
            for (var p : possibleStrings) {
                if (s.equalsIgnoreCase(p)) {
                    // If the string is in the array of possibleStrings, break out of the loop
                    getting = false;
                }
            }
            // If the user didn't enter a string from possibleStrings, prompt them to do so
            if (getting) {
                showError("Enter a choice from the provided list.");
            }
        } while (getting);

        return s;
    }

    /**
     * Gets a string from the user.
     * 
     * @param prompt message that is printed to the user prior to getting input.
     * @return string from user.
     */
    public static String get(String prompt) {
        System.out.print(prompt + "\n> ");
        return s.nextLine();
    }

    /*******************/
    /* UTILITY METHODS */
    /*******************/

    private static void showError(String errorText) {
        System.out.printf("%s\n\n%s%s\n", RED, errorText, RESET_COLOR);
    }
}