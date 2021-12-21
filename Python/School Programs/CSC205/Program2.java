//Josh Hutchinson, Bank Account Growth

import java.util.Scanner;

public class Program2
{
    public static Scanner kb = new Scanner(System.in);

    public static void main (String [] args)
    {
        String periodtype;
        char yesno, period;
        boolean table;
        int compoundoptions, time;
        double annualpcharge, threshold, annualintrate, initbal, newbal,
                periodintrate, periodpcharge, goal;


        System.out.println("\nWelcome to my compounding interest program!\n");


        do
        {
            System.out.print("\nWhat would you like the annual processing"
                    +  " charge to be?\nThis amount will be deducted"
                    +  " at the end of each period,\nbut only if"
                    +  " the balance is less than the threshold.\n"
                    +  "Please enter the processing charge: ");
            annualpcharge = kb.nextDouble();
            while (annualpcharge < 0)
            {
                System.out.print("Please enter a positive amount"
                        + " for the annual processing charge: ");
                annualpcharge = kb.nextDouble();
            }


            System.out.print("\nPlease enter the threshold for"
                    +  " the processing charge on the account: ");
            threshold = kb.nextDouble();
            while (threshold < 0)
            {
                System.out.print("Please enter a positive amount"
                        + " for the threshold: ");
                threshold = kb.nextDouble();
            }


            do
            {
                System.out.print("\nPlease enter the initial"
                      +  " balance on the account: ");
                initbal = kb.nextDouble();
                while (initbal < 0)
                {
                    System.out.print("Please enter a positive amount"
                            + " for the initial balance: ");
                    initbal = kb.nextDouble();
                }


                System.out.print("\nGreat! Please enter the annual interest"
                      +  " rate on the account as a percent: ");
                annualintrate = kb.nextDouble();
                while (annualintrate < 0)
                {
                    System.out.print("Please enter a positive amount"
                            + " for the annual interest rate: ");
                    annualintrate = kb.nextDouble();
                }
                annualintrate = annualintrate / 100;



                System.out.print("\nWhat are the periods at which"
                      +  " interest will be compounded?\n"
                      +  "Daily = d\n"
                      +  "Monthly = m\n"
                      +  "Yearly = y\n\n"
                      +  "Please enter the preferred period (d/m/y): ");
                period = kb.next().charAt(0);
                while(period != 'd' && period != 'm' && period != 'y')
                {
                    System.out.print("Please enter d, m, or y for the"
                            + " compounding period: ");
                    period = kb.next().charAt(0);
                }



                if (period == 'd')
                {
                    periodpcharge = (annualpcharge / 365);
                    periodintrate = (annualintrate / 365);
                    periodtype = "Days";
                }
                else if (period == 'm')
                {
                    periodpcharge = (annualpcharge / 12);
                    periodintrate = (annualintrate / 12);
                    periodtype = "Months";
                }
                else
                {
                    periodpcharge = annualpcharge;
                    periodintrate = annualintrate;
                    periodtype = "Years";
                }



                System.out.print("\n\nGreat! Now we have two options"
                        + " for how we'll calculate interest on this account."
                        + "\n\nOption 1: You can specify a length of time"
                        + " for the investment, and we'll show the balance\n"
                        + "after that given time.\n\n"
                        + "Option 2: You can specify a goal amount for the"
                        + " account,\nand we'll tell you how long it takes"
                        + " to get there.\n\n"
                        + "Please enter 1 for option 1, or 2 for option 2: ");
                compoundoptions = kb.nextInt();
                while (compoundoptions != 1 && compoundoptions != 2)
                {
                    System.out.print("Please enter 1 or 2 for the options: ");
                    compoundoptions = kb.nextInt();
                }

                System.out.print("\nFinally, would you like to have a table"
                        + " that describes your account\n"
                        + "balance at the end of each period?"
                        + "\nPlease enter y for yes or n for no (y/n): ");
                yesno = kb.next().charAt(0);
                while (yesno != 'y' && yesno != 'n')
                {
                    System.out.print("Please enter y for yes or n for no: ");
                    yesno = kb.next().charAt(0);
                }



                table = (yesno == 'y');


                if (compoundoptions == 1)
                {
                    System.out.print("\nPlease enter the investment time in "
                                        + periodtype.toLowerCase() + ": ");
                    time = kb.nextInt();
                    while (time <= 0)
                    {
                        System.out.print("Please enter a positive number for"
                                        + " the investment time: ");
                        time = kb.nextInt();
                    }
                    if (table)
                    {
                        System.out.println("\n" + periodtype + "\tBalance\n");
                    }
                    newbal = computeLength(initbal, periodintrate,
                            periodpcharge, threshold, time, table);
                    System.out.printf("\n\nEnd Balance: %6.2f after %2d %s.",
                            newbal, time, periodtype.toLowerCase());
                }
                else
                {
                    System.out.print("\nPlease enter the amount of the goal"
                    + " you'd like to compute for: ");
                    goal = kb.nextDouble();
                    while (goal <= 0)
                    {
                        System.out.print("Please enter a positive number for"
                                        + " the investment time: ");
                        goal = kb.nextDouble();
                    }
                    if (table)
                    {
                        System.out.println("\n" + periodtype + "\tBalance\n");
                    }
                    time = computeGoal(initbal, periodintrate, periodpcharge,
                            threshold, goal, table);
                    System.out.printf("\n\nIt took %2d %s to reach "
                            + "or surpass your goal of"
                                    + " %6.2f",
                                    time, periodtype.toLowerCase(), goal);
                }

                System.out.print("\nWould you like to try with a different"
                                + " account balance? (y/n): ");
                yesno = kb.next().charAt(0);
                while (yesno != 'y' && yesno != 'n')
                {
                    System.out.print("Please enter y for yes or n for no: ");
                    yesno = kb.next().charAt(0);
                }

            } while (yesno == 'y');


            System.out.print("\n\nWould you like to try with a different"
             + " processing charge and threshold? (y/n): ");
            yesno = kb.next().charAt(0);
            while (yesno != 'y' && yesno != 'n')
            {
                System.out.print("Please enter y for yes or n for no: ");
                yesno = kb.next().charAt(0);
            }


        } while (yesno == 'y');

        System.out.println("\nThank you for using my program! Goodbye.\n");

    }






    public static double computeLength(double initbal, double periodintrate,
                                double periodpcharge, double threshold,
                                int time, boolean table)
    {
        double newbal, interest;
        newbal = initbal;


        for(int i = 0; i < time; ++i)
        {
            interest = newbal * periodintrate;
            newbal = newbal + interest;
            if (newbal < threshold)
            {
                newbal = newbal - periodpcharge;
            }
            if (table)
            {
                System.out.printf("%2d\t\t%6.2f\n", (i+1), newbal);
            }
        }


        return newbal;
    }


    public static int computeGoal(double initbal, double periodintrate,
                                  double periodpcharge, double threshold,
                                  double goal, boolean table)
    {
        double newbal, interest;
        int i;


        newbal = initbal;


        for(i = 1; newbal <= goal; ++i)
        {
            interest = newbal * periodintrate;
            newbal = newbal + interest;
            if (newbal < threshold)
            {
                newbal = newbal - periodpcharge;
            }
            if (table)
            {
                System.out.printf("%2d\t\t%6.2f\n", i, newbal);
            }
        }


        if (table == false)
        {
            System.out.printf("End Balance: %4.2f\n", newbal);
        }


        --i;
        return i;
    }

}