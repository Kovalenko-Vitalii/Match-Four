package utils

import java.lang.NumberFormatException
import java.time.*
import java.util.*

/**
 * This class provides methods for the robust handling of I/O using Scanner.
 * It creates a new Scanner object for each read from the user, thereby eliminating the Scanner bug
 * (where the buffers don't flush correctly after an int read).
 *
 * The methods also parse the numeric data entered to ensure it is correct. If it isn't correct,
 * the user is prompted to enter it again.
 *
 * @author Siobhan Drohan, Mairead Meagher
 * @since 1.0
 */

object Validator {

    /**
     * Read an int from the user.
     * If the entered data isn't actually an int the user is prompted again to enter the int.
     *
     * @param prompt  The information printed to the console for the user to read
     * @return The number read from the user and verified as an int.
     */
    @JvmStatic
    fun readNextInt(prompt: String?): Int {
        do {
            try {
                print(prompt)
                return Scanner(System.`in`).next().toInt()
            } catch (e: NumberFormatException) {
                System.err.println("\tEnter a number please.")
            }
        } while (true)
    }

    /**
     * Read a double from the user.  If the entered data isn't actually a double,
     * the user is prompted again to enter the double.
     *
     * @param prompt  The information printed to the console for the user to read
     * @return The number read from the user and verified as a double.
     */
    @JvmStatic
    fun readNextDouble(prompt: String?): Double {
        do {
            try {
                print(prompt)
                return Scanner(System.`in`).next().toDouble()
            } catch (e: NumberFormatException) {
                System.err.println("\tEnter a number please.")
            }
        } while (true)
    }

    /**
     * Read a line of text from the user.  There is no validation done on the entered data.
     *
     * @param prompt  The information printed to the console for the user to read
     * @return The String read from the user.
     */
    @JvmStatic
    fun readNextLine(prompt: String?): String {
        print(prompt)
        return Scanner(System.`in`).nextLine()
    }

    /**
     * Read a single character of text from the user.  There is no validation done on the entered data.
     *
     * @param prompt  The information printed to the console for the user to read
     * @return The char read from the user.
     */
    @JvmStatic
    fun readNextChar(prompt: String?): Char {
        print(prompt)
        return Scanner(System.`in`).next()[0]
    }

    /**
     * Reads an integer from the user input with specified limits.
     *
     * @param prompt The message displayed to the user.
     * @param min The minimum allowed value.
     * @param max The maximum allowed value.
     * @return The validated integer entered by the user.
     */
    @JvmStatic
    fun readNextIntWithLimit(prompt: String?, min: Int, max: Int): Int {
        do {
            try {
                print(prompt)
                val userInput = Scanner(System.`in`).next().toInt()
                if (userInput in min..max) {
                    return userInput
                } else {
                    System.err.println("\tEnter a number within the range $min-$max.")
                }
            } catch (e: NumberFormatException) {
                System.err.println("\tEnter a number please.")
            }
        } while (true)
    }

    /**
     * Parses user input to a LocalDate, with error handling for invalid date formats.
     *
     * @param input The message displayed to the user.
     * @return The parsed LocalDate.
     */
    @JvmStatic
    fun setLocalDate(input: String): LocalDate {
        do {
            try {
                return LocalDate.parse(readNextLine(input))
            } catch (e: Exception) {
                System.err.println("\tInvalid Input. Please try again")
            }
        } while (true)
    }
}