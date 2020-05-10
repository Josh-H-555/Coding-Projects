// Josh Hutchinson, Program 3, File Analyzer and Data Manipulation

import java.io.*;
import java.util.Scanner;
import java.util.*;

class Moons
{
    public String name;
    public double magnitude;
    public String discoverer;
    public int datefound;
};



public class Program3
{
    public static Scanner kb = new Scanner(System.in);
    private static Random generator = new Random();

    public static void main (String [] args)
    {
        Moons[] moons = new Moons[100];
        int choice, length;

        System.out.println("\nWelcome to my data file reader!");
        System.out.println("We're going to analyze, quiz, and display a "
                    + "histogram of the data.");

        length = loadData(moons);

        do
        {
            menu();
            System.out.print("Please enter your choice: ");
            choice = kb.nextInt();
            if (choice == 1)
            {
                displayAll(moons, length);
            }
            else if (choice == 2)
            {
                displayData(moons, length);
            }
            else if (choice == 3)
            {
                doQuiz(moons, length);
            }
            else if (choice == 4)
            {
                displayGraph(moons, length);
            }
            else
            {
                System.out.println("Please enter a number between 1 and 5: ");
            }
        }while (choice != 5);

        System.out.println("\nThank you! Goodbye.");
    }




    public static void menu()
    {
        System.out.println("\nWhat would you like to do with the data?");
        System.out.println("\n(1) Display all of the data\n"
                + "(2) Display all data for a given key field\n"
                + "(3) Play a quiz based on the key field\n"
                + "(4) Display a histogram of all of the data\n"
                + "(5) Quit\n");
    }




    public static int loadData(Moons[] moons) //needs inputs
    {
        int length = 0;

        String filename = "C:\\Users\\Jhutc\\Desktop\\JupiterMoons.txt";
        try
        {
            File file = new File(filename);
            Scanner infile = new Scanner (file);

            do
            {
                moons[length] = new Moons();
                moons[length].name = infile.next();
                moons[length].magnitude = infile.nextDouble();
                moons[length].discoverer = infile.next();
                moons[length].datefound = infile.nextInt();
                ++length;
            } while (!(moons[length-1].name.equals("EOF")));
            --length;
        }
        catch (IOException ioe)
        {
            System.out.println("File access error");
            length = 0;
        }
        return length;
    }




    public static void displayAll(Moons[] moons, int length) // needs inputs
    {
        int count = 1;
        System.out.println("Displaying all data...\n");
        System.out.println("Moon\t\tMagnitude\t\tDiscoverer\t\tFound\n\n");
        for (int ix = 0; ix < length; ++ix)
        {
            System.out.printf("%-10s\t\t%4.1f\t\t%-10s\t\t%d\n", moons[ix].name,
                    moons[ix].magnitude, moons[ix].discoverer,
                    moons[ix].datefound);
            ++count;
            if (count == 16)
            {
                System.out.println("Please enter any key to continue...");
                String hold = kb.next();
                count = 0;
            }
        }
    }




    public static void displayData(Moons[] moons, int length)
    {
        String choice;
        int ix;
        boolean found = false;
        System.out.print("Please enter the name of the moon you want to display"
                + " the data for: ");
        choice = kb.next();
        for (ix = 0; ix < length && !found; ++ix)
        {
            if (moons[ix].name.equals(choice))
            {
                System.out.println("Moon\tMagnitude\tDiscoverer\tFound\n\n");
                System.out.printf("%-10s\t%2.1f\t%-10s\t%d\n\n", moons[ix].name,
                        moons[ix].magnitude, moons[ix].discoverer,
                        moons[ix].datefound);
                found = true;
            }
        }
        if(!found)
        {
            System.out.println("Sorry, that record isn't in this file.\n");
        }
    }




    public static void doQuiz(Moons[] moons, int length)
    {
        int guess = 0;
        int ix, guessrange;
        int correct = 0;
        int nextQ;
        int[] repeat = new int[length];

        for (int i = 0; i < length; ++i)
        {
            repeat[i] = 0;
        }

        System.out.println("Let's test your skills!");
        System.out.println("I'll display the name of a moon, and you\n"
                + "can guess the approximate year it was discovered.");

        for (ix = 0; ix < length && guess != 1; ++ix)
        {
            do
            {
                nextQ = generator.nextInt(length);
            } while (repeat[nextQ] > 0);
            ++repeat[nextQ];

            System.out.println("Moon: " + moons[nextQ].name);
            System.out.print("Please enter the approximate year it was"
                    + " discovered, or type 1 to quit: ");
            guess = kb.nextInt();
            while (guess < 1)
            {
                System.out.print("Please enter a valid guess, or 1 to quit.");
                guess = kb.nextInt();
            }
            guessrange = moons[nextQ].datefound - guess;
            if (guessrange < 0)
            {
                guessrange = -guessrange;
            }


            if (guess == 1)
            {
                System.out.println("Thanks for playing!");
                --ix;
            }
            else if (guessrange == 0)
            {
                System.out.println("That's exactly when it was discovered!");
                ++correct;
            }
            else if (guessrange <= 20)
            {
                System.out.println("Great guess! That counts as correct!");
                System.out.println("The exact year: " + moons[nextQ].datefound);
                ++correct;
            }
            else
            {
                System.out.println("Sorry, that guess wasn't very close.");
                System.out.println("The exact year: " + moons[nextQ].datefound);
            }
        }

        System.out.println("You got " + correct + " right out of " + ix
                + " questions.");
    }




    public static void displayGraph(Moons[] moons, int length)
    {
        double min, max, range, tempwidth;
        int[] count;
        int decade, decadewidth, widthcounter, width;

        System.out.println("We'll be displaying a histogram of the"
                + " magnitude of the moons.");


        min = calcMinimum(moons, length);
        max = calcMaximum(moons, length);

        range = max - min;
        tempwidth = range / 10;
        decadewidth = (int) (tempwidth + 1);

        count = createDecades(moons, length, decadewidth, min);


        width = (int) min;

        widthcounter = width;
        for (int ix = 0; ix < 10; ++ix)
        {

            System.out.println(count[ix] + " Moons in decade " + widthcounter);
            widthcounter = widthcounter + decadewidth;
        }

        System.out.println("\n\nDecade\tMagnitude");

        for (int ix = 0; ix < 10; ++ ix)
        {
            System.out.print("\n" + width + "\t\t");
            for (int i = 0; i < count[ix]; ++i)
            {
                System.out.print("*");
            }
            width = width + decadewidth;

        }

    }




    public static double calcMinimum(Moons[] moons, int length)
    {
        double minimum = moons[0].magnitude;

        for (int ix = 1; ix < length; ++ix)
        {
            if (minimum > moons[ix].magnitude)
            {
                minimum = moons[ix].magnitude;
            }
        }
        return minimum;
    }




    public static double calcMaximum(Moons[] moons, int length)
    {
        double maximum = moons[0].magnitude;

        for (int ix = 1; ix < length; ++ix)
        {
            if (maximum < moons[ix].magnitude)
            {
                maximum = moons[ix].magnitude;
            }
        }

        return maximum;
    }

    public static int[] createDecades(Moons[] moons, int length, int width,
                                        double min)
    {
        int[] count = new int[10];
        int decade;


        for (int ix = 0; ix < 10; ++ix)
        {
            count[ix] = 0;
        }

        for (int ix = 0; ix < length; ++ix)
        {
            decade = (int) (moons[ix].magnitude - min) / width;
            ++count[decade];
        }

        return count;
    }
}
