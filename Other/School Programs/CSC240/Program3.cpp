//Josh Hutchinson, ASCII Math

#include <iostream>
using namespace std;

int main()
{
	int base, total;
	char yesno, sequence;

	cout << "Welcome! We are going to convert numbers from different"
		<< endl << "bases to decimal!" << endl << endl;

	do
	{

		cout << "Please enter a number from 2 to 10 as your base, otherwise"
			<< endl << "you may type 16 to convert from a hexadecimal base: ";
		cin >> base;
		while (!((base == 16) || (base >= 2 && base <= 10)))
		{
			cout << "Please enter a valid number from 2 to 10, or 16: ";
			cin >> base;
		}

		cout << endl << "Now please enter the sequence of numbers you would"
			<< endl << "like to convert. When you are finished, enter !";
		cout << endl << endl << "Please enter your sequence here: ";
		cin >> sequence;

		total = 0;
		
		if (base != 16)
		{
			do
			{
				total = (total * base) + (sequence - '0');
				cin >> sequence;

			} while (sequence != '!');
		}

		else
		{

			do
			{
				if (sequence <= '9')
				{
					total = (total * base) + (sequence - '0');
				}
				else
				{
					total = (total * base) + ((sequence - 'A') + 10);
				}
				cin >> sequence;
			} while (sequence != '!');
		}

		cout << "Your total number in decimal form is: " << total << "."
			<< endl << "Thank you!" << endl << endl;

		cout << "Would you like to do another conversion? (y/n): ";
		cin >> yesno;
		while (yesno != 'y' && yesno != 'n')
		{
			cout << "Please enter y for yes or n for no: ";
			cin >> yesno;
		}
	} while (yesno == 'y');


	cout << "Thank you for using my program! Goodbye." << endl;
	return 0;
}