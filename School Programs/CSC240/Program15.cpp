//Josh Hutchinson, Program #15, The Sieve of Eratosthenes

#include <iostream>
using namespace std;

int* initializeNewArray(int size);
int returnFirstZero(int* myArray, int size);
void crossMultiples(int* myArray, int size, int factor);
void circleRemainingZeroes(int* myArray, int size);
int* createPrimeArray(int* myArray, int& size);

int main()
{
	int* workingArray, *primeArray;
	int lowBound, highBound, size, newFactor;
	char yesno;

	cout << endl << "Welcome! We're going to find all prime numbers within" <<
		"a given range!" << endl << endl;

	do
	{
		do
		{
			cout << "Please enter the high boundary of the range: ";
			cin >> highBound;
			while (highBound <= 0)
			{
				cout << endl << "Please enter a positive number for the boundary: ";
				cin >> highBound;
			}

			cout << endl << "Please enter the lower boundary of the range: ";
			cin >> lowBound;

			if (lowBound > highBound || lowBound <= 0)
			{
				cout << "Please make sure the lower boundary is less than" <<
					endl << "or equal to the high boundary," << endl
					<< "and greater than zero: ";
			}
		} while (lowBound > highBound || lowBound <= 0);

		size = highBound + 1;

		workingArray = initializeNewArray(size);

		do
		{
			newFactor = returnFirstZero(workingArray, size);
			if (newFactor != 5)
			{
				crossMultiples(workingArray, size, newFactor);
			}
		} while (newFactor != 5);

		circleRemainingZeroes(workingArray, size);

		primeArray = createPrimeArray(workingArray, size);

		cout << endl << endl;

		cout << "Printing prime numbers within your range..." << endl;

		for (int* ix = primeArray; ix < (primeArray + size); ++ix)
		{
			if (*ix >= lowBound)
				cout << *ix << endl;
		}

		cout << endl << endl;
		cout << "Would you like to go again? (y/n): ";
		cin >> yesno;
		while (yesno != 'y' && yesno != 'n')
		{
			cout << "Please enter y for yes or n for no: ";
			cin >> yesno;
		}

	} while (yesno != 'n');

	cout << endl << "Thank you! goodbye." << endl;

	return 0;
}

int* initializeNewArray(int size)
{
	++size;
	int* newArray = new int[size];
	for(int* iterable = newArray; iterable < (newArray + size); ++iterable)
	{
		if ((iterable - newArray) <= 1)
		{
			*iterable = -1;
		}
		else
		{
			*iterable = 0;
		}
	}
	return newArray;
}

int returnFirstZero(int* myArray, int size)
{
	for (int* ix = myArray; ix < (myArray + size); ++ix)
	{
		if (*ix == 0)
		{
			return (ix - myArray);
		}
	}
	return 4;
}

void crossMultiples(int* myArray, int size, int factor)
{
	int* ix = (myArray + factor);
	*ix = 1;
	for (ix = ++ix; ix < (myArray + size); ++ix)
	{
		if ((ix - myArray) % factor == 0)
		{
			*ix = -1;
		}
	}
}

void circleRemainingZeroes(int* myArray, int size)
{
	for (int* ix = myArray; ix < (myArray + size); ++ix)
	{
		if (*ix == 0)
		{
			*ix += 1;
		}
	}
}

int* createPrimeArray(int* myArray, int& size)
{
	int* newPrimeArray;
	int newSize = 0;
	int kx;

	for (int* ix = myArray; ix < (myArray + size); ++ix)
	{
		if (*ix == 1)
		{
			++newSize;
		}
	}


	newPrimeArray = new int[newSize];
	kx = 0;
	for (int* ix = myArray; ix < (myArray + size); ++ix)
	{
		if (*ix == 1)
		{
			newPrimeArray[kx] = (ix - myArray);
			++kx;
		}
	}

	size = newSize;

	return newPrimeArray;

}