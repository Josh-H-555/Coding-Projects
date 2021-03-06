// Josh Hutchinson, program 4
// I have precompiled headers on, which is why the pch.h is there
#include "pch.h"
#include <stdio.h>
#include <iostream>
using namespace std;

int main()
{
	
	double annualpcharge, threshold;
	char period, table, period_or_goal, yesno;


	cout << "Welcome to my interest calculator!" << endl << endl;

	cout << "What is the annual processing charge for this account?: $";
	cin >> annualpcharge;
	while (annualpcharge < 0)
	{
		cout << "Please enter a valid, positive number: $";
		cin >> annualpcharge;
	}


	cout << endl << "What is the threshold for the processing charge?: $";
	cin >> threshold;
	while (threshold < 0)
	{
		cout << "Please enter a valid, positive number: $";
		cin >> threshold;
	}
	do
	{
		double initialbal, interest, goal;
		double oldbal, newbal, intrate, staticintrate, periodcharge;
		int i, time;

		cout << endl << endl;
		cout << "What will the initial account balance be?: $";
		cin >> initialbal;
		while (initialbal < 0)
		{
			cout << "Please enter a valid, positive number: $";
			cin >> initialbal;
		}


		cout << endl << "What is the annual interest rate percentage "
			<< "(0-100)?: ";
		cin >> intrate;
		while (intrate < 0)
		{
			cout << "Please enter a valid, positive number: ";
			cin >> intrate;
		}
		intrate = intrate / 100;

		cout << endl << "How often will this interest be compounded?" << endl
			<< "d = daily" << endl << "m = monthly" << endl << "y = yearly"
			<< endl << "Please enter compounding period (d/m/y): ";
		cin >> period;
		while (!(period == 'd' || period == 'm' || period == 'y'))
		{
			cout << "Please enter d for daily, m for monthly, or y "
				<< "for yearly: ";
			cin >> period;
		}

		cout << endl << "Would you like a table detailing the account balance?"
			<< " (y/n): ";
		cin >> table;
		while (!(table == 'y' || table == 'n'))
		{
			cout << "Please enter y for yes, or n for no: ";
			cin >> table;
		}

		cout << endl << "Do you want to calculate a set number of periods"
			<< " or an end goal for the account?" << endl;
		cout << "p = period" << endl << "g = goal" << endl;
		cout << "Please enter for period or goal (p/g): ";
		cin >> period_or_goal;
		while (!(period_or_goal == 'p' || period_or_goal == 'g'))
		{
			cout << "Please enter p or g for whether you'd like a number"
				<< " of periods or goals (p/g): ";
			cin >> period_or_goal;
		}

		//you might notice "staticintrate" in the if statements after this,
		//that's there because i'm following the example of
		//the program 4 sheet, which displays the initial balance
		//compounding period, and ANNUAL interest rate, not periodic
		//otherwise if it displayed intrate on a daily it'd show a real small
		//annual interest rate.
		//not sure if that's exactly what's wanted, but figured
		//I would explain my reasoning.


		if (period_or_goal == 'p')
		{
			if (period == 'd')
			{
				cout << "How many days do you want to "
					<< " calculate the interest for?: ";
				cin >> time;
				while (time < 0)
				{
					cout << "Please enter a valid, positive number: ";
					cin >> time;
				}
				periodcharge = (annualpcharge / 365);
				staticintrate = intrate;
				intrate = intrate / 365;
			
			}
			else if (period == 'm')
			{
				cout << "How many months do you want to "
					<< "calculate the interest for?: ";
				cin >> time;
				while (time < 0)
				{
					cout << "Please enter a valid, positive number: ";
					cin >> time;
				}
				periodcharge = (annualpcharge / 12);
				staticintrate = intrate;
				intrate = intrate / 12;
			}
			else
			{
				cout << "How many years do you want to "
					<< "calculate the interest for?: ";
				cin >> time;
				while (time < 0)
				{
					cout << "Please enter a valid, positive number: ";
					cin >> time;
				}
				staticintrate = intrate;
				periodcharge = annualpcharge;
			}

			oldbal = initialbal;
			
			cout << endl << endl;
			printf("Initial Balance \t %7.2f \n", initialbal);
			printf("Annual Interest Rate \t %3.3f \n", staticintrate);
			if (period == 'd')
			{
				printf("Compounding Period \t Daily \n");
				printf("Time (Days) \t\t %3i \n", time);
			}
			else if (period == 'm')
			{
				printf("Compounding Period \t Monthly \n");
				printf("Time (Months) \t\t %3i \n", time);
			}
			else
			{
				printf("Compounding Period \t Yearly \n");
				printf("Time (Years) \t\t %3i \n", time);
			}
			for (i = 1; i <= time; ++i)
			{
				interest = oldbal * intrate;
				newbal = oldbal + interest;
				oldbal = newbal;
				if (newbal < threshold)
				{
					oldbal = newbal - periodcharge;
				}
				
				if (table == 'y')
				{
					printf("\t Period \t Balance \n \t %2i \t\t %7.2f \n", 
						i, oldbal);
				}
			}
			cout << endl;
			i = i - 1;
			// this i = i - 1 is here because i didn't want to have a "period"
			// zero, eg a year 0, day 0, or month 0
			//however, by setting i equal to 1 and shoving it in a for loop
			//if i were to input "3" years for example, it will say after 4
			//years the balance is _____
			//so this is my simple, bootleggish fix to that.


			if (period == 'd')
			{
				printf("After %3i days, you will have $%7.2f", i, newbal);
			}
			else if (period == 'm')
			{
				printf("After %3i months, you will have $%7.2f", i, newbal);
			}
			else
			{
				printf("After %3i years, you will have $%7.2f", i, newbal);
			}
		}

		else
		{
			if (period == 'd')
			{
				periodcharge = (annualpcharge / 365);
				staticintrate = intrate;
				intrate = intrate / 365;
			}
			else if ( period == 'm')
			{
				periodcharge = annualpcharge / 12;
				staticintrate = intrate;
				intrate = intrate / 12;
			}
			else
			{
				periodcharge = annualpcharge;
				staticintrate = intrate;
				//needed this else because staticintrate wouldn't
				//get initialized otherwise
			}
			cout << "What is the goal that you'd like to calculate for?: $";
			cin >> goal;
			while (goal < 0)
			{
				cout << "Please enter a valid, positive number: $";
				cin >> goal;
			}
			cout << endl << endl;
			printf("Initial Balance \t %7.2f \n", initialbal);
			printf("Annual Interest Rate \t %3.3f \n", staticintrate);
			if (period == 'd')
			{
				printf("Compounding Period \t Daily \n");
				printf("Goal Balance \t %7.2f \n", goal);
			}
			else if (period == 'm')
			{
				printf("Compounding Period \t Monthly \n");
				printf("Goal Balance \t %7.2f \n", goal);
			}
			else
			{
				printf("Compounding Period \t Yearly \n");
				printf("Goal Balance \t\t %7.2f \n", goal);
			}

			i = 0;
			//need this to count properly.
			oldbal = initialbal;
		
			do
			{
				i = i + 1;
				interest = oldbal * intrate;
				newbal = oldbal + interest;
				oldbal = newbal;
				if (newbal < threshold)
				{
					oldbal = newbal - periodcharge;
				}
				if (table == 'y')
				{
					printf("\t Period \t Balance \n \t %2i \t\t %7.2f \n",
						i, oldbal);
				}
			} while (oldbal < goal);
			cout << endl;
			if (period == 'd')
			{
				printf("After %3i days, you will have $%7.2f", i, newbal);
			}
			else if (period == 'm')
			{
				printf("After %3i months, you will have $%7.2f", i, newbal);
			}
			else
			{
				printf("After %3i years, you will have $%7.2f", i, newbal);
			}
			
		}
		cout << endl << endl << "Again? (y/n): ";
		cin >> yesno;
		while (!(yesno == 'y' || yesno == 'n'))
		{
			cout << "Please enter y for yes or n for no: ";
			cin >> yesno;
		}
	} while (yesno == 'y');

	cout << endl << endl << "Thank you for using my interest calculator!"
		<< " Goodbye." << endl << endl;

	return 0;
}



