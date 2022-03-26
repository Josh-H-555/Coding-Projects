//Josh Hutchinson, Betting Strategy

#include <iostream>
#include <stdlib.h>
#include <time.h>
using namespace std;


double computeFinal(double bal, double betAmount, int winChance,
	double odds, int table, int betsPlaced);

void computeDiff(double bal, double betAmount, int winChance,
	double odds, int table, double& total, int& betsPlaced, double differential);

int main()
{
	double initialBal, betAmount, odds, total, differential;
	int winChance, calcOption, tablebool, betsPlaced;

	char yesno, table;

	srand(time(NULL));

	cout << "Welcome! Let's place some bets and see how lucky you are!"
		<< endl << endl;

	do
	{
		betsPlaced = 0;

		cout << "Please enter your initial balance. It must be greater than 0: ";
		cin >> initialBal;
		while (initialBal <= 0)
		{
			cout << "Please enter a valid amount for the initial balance: ";
			cin >> initialBal;
		}

		cout << endl << "Great, now enter the amount of money each bet will be: ";
		cin >> betAmount;
		while (betAmount <= 0 || betAmount > initialBal)
		{
			cout << "Please enter a valid amount, greater than 0, but less"
				<< endl << "than the initial balance: ";
			cin >> betAmount;
		}

		cout << endl << "What will be your rate of winning? (0%-100%): ";
		cin >> winChance;
		while (winChance < 0 || winChance > 100)
		{
			cout << "Please enter a valid percentage for your win chance: ";
			cin >> winChance;
		}

		cout << endl << "Lastly, what will your rate of return be? (0.1-100): ";
		cin >> odds;
		while (odds < 0.1 || odds > 100)
		{
			cout << "Please enter a valid number for rate of return: ";
			cin >> odds;
		}

		cout << "We've gathered all of the information, now please choose"
			<< endl << "between two options for the calculations." << endl;

		cout << endl << "Option 1: Calculate final balance after x number of bets.";
		cout << endl << "Option 2: Set a goal differential, and calculate how"
			<< endl << "long it takes to gain or lose an amount past the differential.";

		cout << endl << endl << "Please choose Option 1 or 2: ";
		cin >> calcOption;
		while (calcOption != 1 && calcOption != 2)
		{
			cout << "Please enter 1 or 2 for the option you would like: ";
			cin >> calcOption;
		}

		cout << endl << "Would you like to see a table of your bets? (y/n): ";
		cin >> table;
		while (table != 'y' && table != 'n')
		{
			cout << "Please enter y or n for your choice on a table: ";
			cin >> table;
		}

		//figured I'd allow user input to be y/n for more consistency and
		//to be a little clearer


		if (table == 'y')
		{
			tablebool = 1;
		}
		else
		{
			tablebool = 0;
		}
		
		if (calcOption == 1)
		{
			cout << endl << "How many bets would you like to place?: ";
			cin >> betsPlaced;
			while (betsPlaced <= 0)
			{
				cout << "Please enter a valid number of bets to place: ";
				cin >> betsPlaced;
			}


			total = computeFinal(initialBal, betAmount, winChance, odds, 
				tablebool, betsPlaced);

			if (total <= 0)
			{
				cout << endl << "You ran out of money!";
			}
			else
			{
				cout << endl << "Your total after " << betsPlaced << " bets"
					<< " is $" << total << "." << endl << endl;
			}
		}
		else
		{
			cout << endl << "What is the differential you would like to compute for?"
				<< endl << "In other words, an amount of money above or below"
				<< " your initial balance.";
			cout << endl << endl << "Please enter it now: ";
			cin >> differential;
			while (differential < 0 || differential > initialBal)
			{
				cout << "Please enter a valid amount for the differential: ";
				cin >> differential;
			}


			computeDiff(initialBal, betAmount, winChance, odds, tablebool,
				total, betsPlaced, differential);

			cout << endl << "Your total after " << betsPlaced << " bets"
				<< " is $" << total << "." << endl << endl;
		}



		cout << endl << endl << "Would you like to try again? (y/n): ";
		cin >> yesno;
		while (yesno != 'y' && yesno != 'n')
		{
			cout << "Please enter y for yes, or n for no: ";
			cin >> yesno;
		}
	} while (yesno != 'n');

	cout << endl << endl << "Thank you for using my program! Goodbye." << endl;

	return 0;
	
}



double computeFinal(double bal, double betAmount, int winChance,
	double odds, int table, int betsPlaced)
{
	double randval;
	double total = bal;
	double chance = winChance / 100.0;

	if (table)
	{
		cout << endl << endl;
		printf("Bets\t\tBalance\n");
	}

	for (int i = 0; i < betsPlaced && total > 0; ++i)
	{
		randval = rand() / (double)RAND_MAX;

		if (randval <= chance)
		{
			total = total + betAmount * odds;
		}
		else
		{
			total = total - betAmount;
		}

		if (table)
		{
			printf("%2d\t\t%4.2f\n", i + 1, total);
		}
	}

	return total;
}



void computeDiff(double bal, double betAmount, int winChance,
	double odds, int table, double &total, int &betsPlaced, double differential)
{
	total = bal;
	double randval;
	double chance = winChance / 100.0;
	double minBal = total - differential;
	double maxBal = total + differential;

	if (table)
	{
		cout << endl << endl;
		printf("Bets\t\tBalance\n");
	}

	do
	{
		randval = rand() / (double)RAND_MAX;

		if (randval <= chance)
		{
			total = total + betAmount * odds;
		}
		else
		{
			total = total - betAmount;
		}

		++betsPlaced;

		if (table)
		{
			printf("%2d\t\t%4.2f\n", betsPlaced, total);
		}

	} while (total > minBal && total < maxBal&& total > 0);
}