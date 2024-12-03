///////////////////////// TOP OF FILE COMMENT BLOCK ////////////////////////////
//
// Title:           descriptive title of the program making use of this file
// Course:          course number, term, and year
//
// Author:          your name
// Email:           your @wisc.edu email address
// Lecturer's Name: name of your lecturer 
//
///////////////////////////////// CITATIONS ////////////////////////////////////
//
// Source or Recipient; Description
// Examples (REMOVE in your code - unless Jane Doe helped you and you helped John Doe accordingly):
// Jane Doe; helped me with for loop in reverse method 
// https://docs.oracle.com/javase/tutorial/java/nutsandbolts/for.html; 
//         counting for loop
// John Doe; I helped with switch statement in main method.
// ChatGPT; Helped with debugging an off-by-one error with a loop.
//
/////////////////////////////// 80 COLUMNS WIDE ////////////////////////////////


import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.util.Scanner;
import java.util.ArrayList;

/**
 * This is the test bench that contains testing methods for the H12CustomApp class.
 * The createTestDataFile and readTestDataFile are private testing methods intended to 
 * be used within the test cases.
 *
 * All the test cases within the testH12CustomApp method should be changed to test the 
 * methods in your H12CustomApp class.
 *
 * @author Jim Williams
 * @author //TODO add your name here when you contribute.
 */
public class TestH12CustomApp {

    /**
     * This method runs the selected tests.
     *
     * @param args unused
     */
    public static void main(String[] args) {
        testH12CustomApp();
    }

    /**
     * This is a testing method to create a file with the specified name and fileContents
     * to be used by other testing methods. On a FileNotFoundException a stack trace is printed and
     * then returns.
     *
     * @param testDataFilename The filename of the testing file to create.
     * @param fileContents The data to put into the file.
     */
    private static void createTestDataFile(String testDataFilename, String fileContents) {
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(testDataFilename);
            writer.print(fileContents);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (writer != null) writer.close();
        }
    }

    /**
     * This is a testing method to read and return the entire contents of the specified file to
     * be used soley by other testing methods.
     * On a FileNotFoundException a stack trace is printed and then "" returned.
     *
     * @param dataFilename The name of the file to read.
     * @return The contents of the file or "" on error.
     */
    private static String readTestDataFile(String dataFilename) {
        File file = new File(dataFilename);
        Scanner input = null;
        String contents = "";
        try {
            input = new Scanner( file);
            while (input.hasNextLine()) {
                contents += input.nextLine() + "\n"; //assuming all lines end with newline
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (input != null) input.close();
        }
        return contents;
    }

    /**
     * Tests that the H12CustomApp read input and write output methods handle
     * the cases described in their method header comments.
     *
     * @return true for passing all testcases, false otherwise
     */
    public static boolean testH12CustomApp() {
        boolean error = false;

        {//test case 1: ensuring that data can be read from the file, and that the method
            // returns the appropriate String ArrayList after parsing the file.
            File testFile = new File("data/testInventory.txt");

            createTestDataFile("data/testInventory.txt",
                    "item: pencil, quantity: 8\n" +
                    "item: rock, quantity: 14\n" +
                    "item: stick, quantity: 3");

            ArrayList<String> expectedArrayList = new ArrayList<>();
            expectedArrayList.add("pencil");
            expectedArrayList.add("rock");
            expectedArrayList.add("stick");

            ArrayList<String> actualArrayList = new ArrayList<>();
            try {
                actualArrayList = H12CustomApp.makeInventoryItemArray(testFile);
                if (!actualArrayList.equals(expectedArrayList)) {
                    error = true;
                    System.out.println("Error in test case 1: expected: " + expectedArrayList + " actual: " + actualArrayList);
                }
            } catch (FileNotFoundException exception) {
                error = true;
                System.out.println("Error in test case 1: FileNotFoundException caught");
            }
        }

        {//test case 2: ensuring that data can be read from the file, and that the method
            // returns the appropriate Integer ArrayList after parsing the file.
            File testFile = new File("data/testInventory.txt");

            createTestDataFile("data/testInventory.txt",
                    "item: pencil, quantity: 8\n" +
                            "item: rock, quantity: 14\n" +
                            "item: stick, quantity: 3");

            ArrayList<Integer> expectedArrayList = new ArrayList<>();
            expectedArrayList.add(8);
            expectedArrayList.add(14);
            expectedArrayList.add(3);

            ArrayList<Integer> actualArrayList = new ArrayList<>();
            try {
                actualArrayList = H12CustomApp.makeInventoryQuantityArray(testFile);
                if (!(actualArrayList.equals(expectedArrayList))) {
                    error = true;
                    System.out.println("Error in test case 2: expected: " + expectedArrayList + " actual: " + actualArrayList);
                }
            } catch (FileNotFoundException exception) {
                error = true;
                System.out.println("Error in test case 2: FileNotFoundException caught");
            }
        }

        if (error) {
            System.out.println("\nTestH12CustomApp failed");
            return false;
        } else {
            System.out.println("\nTestH12CustomApp passed");
            System.out.println("There may be output from the methods being tested.");
            return true;
        }
    }
}
