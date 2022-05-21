package com.vaccine_management_system;

/**
 * Utilities class.
 *
 * @author Dean Lonergan (20092570)
 */
public class Utilities {

    /**
     * Method to test if a String input contains a valid PPS number.
     * @param pps input String.
     * @return true if the input matches the appropriate PPS number pattern, false otherwise.
     */
    public static boolean validPPS(String pps) {
        return ((pps.matches("[0-9]{7}[A-z]{2}")) && (!pps.isBlank()));
    }

    /**
     * Method to test if a String input contains a valid Eircode.
     * @param eircode input String.
     * @return true if the input matches the appropriate Eircode number pattern, false otherwise.
     */
    public static boolean validEircode(String eircode) {
        return ((eircode.matches("(?:^[AC-FHKNPRTV-Y][0-9]{2}|D6W)[ -]?[0-9AC-FHKNPRTV-Y]{4}$")) && (!eircode.isBlank()));
    }

    /**
     * Method to test if a String input contains a valid PPS booth ID.
     * @param pps input String.
     * @return true if the input matches the appropriate booth ID pattern, false otherwise.
     */
    public static boolean validID(String pps) {
        return ((pps.matches("[A-z][0-9]{2}")) && (!pps.isBlank()));
    }

    /**
     * Method to test if a String input contains fewer than 10 characters.
     * @param string input String.
     * @return true if the length of the string is less than 10, false otherwise.
     */
    public static boolean max100Chars(String string) {
        return string.length() <= 100;
    }

    /**
     * Method to test if a String input contains fewer than 50 characters.
     * @param string input String.
     * @return true if the length of the string is less than 50, false otherwise.
     */
    public static boolean max50Chars(String string) {
        return string.length() <= 50;
    }

    /**
     * Method to test if a String input contains fewer than 30 characters.
     * @param string input String.
     * @return true if the length of the string is less than 30, false otherwise.
     */
    public static boolean max30Chars(String string) {
        return string.length() <= 30;
    }

    /**
     * Method to test if a String input contains a valid floor.
     * @param string input String.
     * @return true if the string is a valid building floor, false otherwise.
     */
    public static boolean validFloor(String string) {
        return (string.equals("G") || string.equals("1") || string.equals("2") || string.equals("3") || string.equals("4") || string.equals("5")
                || string.equals("6") || string.equals("7") || string.equals("8") || string.equals("9") || string.equals("10"));
    }
}
