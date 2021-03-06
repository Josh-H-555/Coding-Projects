// Josh Hutchinson, program 6, Temperature and Arrays

#include "pch.h"
#include <fstream>
#include <iostream>
using namespace std;

void getData(int day[], double highTemp[], double recordHigh[],
	int recordDate[], double relativeHumid[], int &length);
void loadData(int day[], double highTemp[], double recordHigh[],
	int recordDate[], double relativeHumid[], int &length);
void typeData(int day[], double highTemp[], double recordHigh[], 
	int recordDate[], double relativeHumid[], int &length);

void menu();
char selectField();
void clearScreen();
void copyArray(double array1[], double array2[], int &length);
void wait();

void displayData(int day[], double highTemp[], double recordHigh[],
	int recordDate[], double relativeHumid[], int &length);

void getDay(int &length, double recordHigh[], int recordDate[], int day[]);
int searchData(int day[], int dayinput, int &length);

void display_heat_index(int day[], double highTemp[], double relativeHumid[], 
	int &length);
double calc_heat_index(int day[], double highTemp[], double relativeHumid[], 
	int count);

void display_ave_min_max(double highTemp[], double recordHigh[],
	double relativeHumid[], int &length);
void calc_ave_min_max(double selected[], double &min, double &max, 
	double &range, double &ave, int &length);

void displayLargestDiff(int day[], double highTemp[],
	double recordHigh[], double relativeHumid[], int &length);
void calcLargestDiff(double selected[], double &largestdiff, double &diff,
	int &count, int &largeday);

void countDecades(double highTemp[], double recordHigh[],
	double relativeHumid[], int &length);
void displayDecade(int decade[], int &length);



int main()
{
	char yesno;
	int day[100], length, choice;
	double highTemp[100];
	double recordHigh[100];
	int recordDate[100];
	double relativeHumid[100];

	cout << "Welcome! We're going to determine some statistics," << endl
		<< "given specific days and various temperatures and humidities."
		<< endl;
	do
	{
		getData(day, highTemp, recordHigh, recordDate, relativeHumid, length);
		clearScreen();
		do
		{
			menu();
			cout << "Please enter a number between 1 and 7 for your choice: ";
			cin >> choice;
			if (choice == 1)
			{
				displayData(day, highTemp, recordHigh, recordDate, 
					relativeHumid, length);
				clearScreen();
			}
			else if (choice == 2)
			{
				getDay(length, recordHigh, recordDate, day);
				wait();
				clearScreen();
			}
			else if (choice == 3)
			{
				display_heat_index(day, highTemp, relativeHumid, length);
				wait();
				clearScreen();
			}
			else if (choice == 4)
			{
				display_ave_min_max(highTemp, recordHigh, 
					relativeHumid, length);
				wait();
				clearScreen();
			}
			else if (choice == 5)
			{
				displayLargestDiff(day, highTemp, recordHigh,
					relativeHumid, length);
				wait();
				clearScreen();
			}
			else if (choice == 6)
			{
				countDecades(highTemp, recordHigh, relativeHumid, length);
				wait();
				clearScreen();
			}
			else if (choice != 7)
			{
				cout << endl << "Please enter a number between 1 and 7: ";
				cin >> choice;
				cout << endl << endl;
			}
		} while (choice != 7);
		cout << endl << endl;
		cout << "Do you want to continue by loading different data?"
			<< " (y/n): ";
		cin >> yesno;
	} while (yesno == 'y');
	return 0;
}



void getData(int day[], double highTemp[], double recordHigh[],
	int recordDate[], double relativeHumid[], int &length)
{
	char data;
	cout << endl
		<< "Would you like to enter the data from a data file, "
		<< "or manually?"
		<< endl
		<< "Please type d for data file, or m for manually: ";
	cin >> data;
	while (data != 'd' && data != 'm')
	{
		cout << "Please enter d for data file, or m for manually: ";
		cin >> data;
	}

	if (data == 'd')
	{
		loadData(day, highTemp, recordHigh, recordDate,
			relativeHumid, length);
	}
	else
	{
		cout << "First you'll enter the day of the year, starting at the"
			<< endl
			<< "beginning of the year. For example, February 2nd "
			<< "is day 33."
			<< endl
			<< "Second, you'll enter the high temperature in Fahrenheit."
			<< endl
			<< "Third, you'll enter the record high temperature "
			<< "in Fahrenheit."
			<< endl
			<< "Fourth, you'll enter the year the record high temp was"
			<< " recorded."
			<< endl
			<< "Lastly, you'll enter the relative humidity, "
			<< "in a percentage."
			<< endl << endl
			<< "When you want to quit entering data, enter a 0 for the day"
			<< " of the year."
			<< endl << endl;

		typeData(day, highTemp, recordHigh, recordDate,
			relativeHumid, length);
	}
}
void loadData(int day[], double highTemp[], double recordHigh[],
	int recordDate[], double relativeHumid[], int &length)
{
	fstream data;
	char fname[81];
	int count;

	cout << "Enter the name of the file with the weather information."
		 << endl << endl;
	cin >> fname;
	data.open(fname, ios::in);
	count = 0;
	do
	{
		data >> day[count];
		data >> highTemp[count];
		data >> recordHigh[count];
		data >> recordDate[count];
		data >> relativeHumid[count];
		++count;
	} while (day[count-1] != 0);
	data.close();
	--count;
	length = count;
}
void typeData(int day[], double highTemp[], double recordHigh[], 
	int recordDate[], double relativeHumid[], int &length)
{
	int ix;
	ix = 0;
	do
	{
		cout << "Please enter the day: ";
		cin >> day[ix];
		if (day[ix] != 0)
		{
			cout << "Please enter the high temperature for that day: ";
			cin >> highTemp[ix];
			cout << "Please enter the record high temperature of that day: ";
			cin >> recordHigh[ix];
			cout << "Please enter the year that high temp was recorded: ";
			cin >> recordDate[ix];
			cout << "Please enter the relative humidity: ";
			cin >> relativeHumid[ix];
			++ix;
		}
	} while (day[ix] != 0);
	length = ix;
}





void displayData(int day[], double highTemp[], double recordHigh[],
	int recordDate[], double relativeHumid[], int &length)
{
	int ix, count;
	char stop;
	count = 0;
	printf("\nDay\tHigh Temp\tRecord High\tRecord Year\tRelative Humidity\n");
	for (ix = 0; ix < length; ++ix)
	{
		cout << endl << endl;
		cout << day[ix] << "\t" << highTemp[ix] << "\t\t" << recordHigh[ix] 
			<< "\t\t" << recordDate[ix]
			<< "\t\t" << relativeHumid[ix] << endl << endl;
		++count;
		if (count == 10)
		{
			cout << "Please enter any key to continue loading the data... ";
			cin >> stop;
			count = 0;
		}
	}
	cout << "All data has been displayed." << endl;
	wait();
}





void getDay(int &length, double recordHigh[], int recordDate[], int day[])
{
	int dayinput, found;
	cout << "What day do you want to see the year of the record"
		<< " high temperature for?"
		<< endl;
	cin >> dayinput;
	found = searchData(day, dayinput, length);
	if (found == -1)
	{
		cout << "Sorry, we didn't find the day you entered in"
			<< " our records." << endl << endl;
	}
	else
	{
		cout << endl << endl
			<< "The record high was " << recordHigh[found]
			<< " degrees in the year " << recordDate[found]
			<< " on day " << dayinput << endl << endl;
	}
}
int searchData(int day[], int dayinput, int &length)
{
	int ix;
	for (ix = 0; ix < length; ++ix)
	{
		if (dayinput == day[ix])
			return ix;
	}
	return -1;
}





void display_heat_index(int day[], double highTemp[], double relativeHumid[], 
	int &length)
{
	int ix;
	double heatindex;
	printf("\n\n");
	printf("Day\tHigh Temp\tRelative Humidity\tHeat Index\n");
	for (ix = 0; ix < length; ++ix)
	{
		heatindex = calc_heat_index(day, highTemp, relativeHumid, ix);
		cout << day[ix] << "\t" << highTemp[ix] << "\t\t"
			<< relativeHumid[ix] << "\t\t\t" << heatindex << "\n";
	}
}
double calc_heat_index(int day[], double highTemp[], double relativeHumid[],
	int count)
{
	double heatindex, absolute;
	heatindex = -42.379 + 2.04901523 * highTemp[count] + 10.14333127
		* relativeHumid[count] - 0.22475541 * highTemp[count]
		* relativeHumid[count] - 0.00683783 * highTemp[count]
		* highTemp[count] - 0.05481717 * relativeHumid[count]
		* relativeHumid[count] + 0.00122874 * highTemp[count]
		* highTemp[count] * relativeHumid[count] + 0.00085282
		* highTemp[count] * relativeHumid[count] * relativeHumid[count]
		- 0.00000199 * highTemp[count] * highTemp[count]
		* relativeHumid[count] * relativeHumid[count];
	if (relativeHumid[count] < 13 && highTemp[count] >= 80 &&
		highTemp[count] <= 112)
	{
		absolute = highTemp[count] - 95.0;
		if (absolute < 0)
			absolute = -absolute;
		heatindex = ((13 - relativeHumid[count]) / 4) * sqrt((
			17 - absolute) / 17);
	}
	else if (relativeHumid[count] > 85 && highTemp[count] >= 80 &&
		highTemp[count] <= 87)
	{
		heatindex = ((relativeHumid[count] - 85) / 10)
			* ((87 - highTemp[count]) / 5);
	}
	return heatindex;
}




void display_ave_min_max(double highTemp[], double recordHigh[],
	double relativeHumid[], int &length)
{
	char choice;
	double min, max, range, average;
	cout << endl << endl;

	choice = selectField();

	if (choice == 't')
	{
		calc_ave_min_max(highTemp, min, max, range, average, length);
	}
	else if (choice == 'r')
	{
		calc_ave_min_max(recordHigh, min, max, range, average, length);
	}
	else if (choice == 'h')
	{
		calc_ave_min_max(relativeHumid, min, max, range, average, length);
	}
	cout << endl << endl;
	cout << "Average: " << average;
	cout << endl << endl;
	cout << "Minimum: " << min;
	cout << endl << endl;
	cout << "Maximum: " << max;
	cout << endl << endl;
	cout << "Range: " << range;
	cout << endl << endl;
}
void calc_ave_min_max(double selected[], double &min, double &max,
	double &range, double &ave, int &length)
{
	int ix;
	ave = 0;
	max = selected[0];
	min = selected[0];
	for (ix = 0; ix < length; ++ix)
	{
		ave = ave + selected[ix];
		if (max < selected[ix])
			max = selected[ix];
		if (min > selected[ix])
			min = selected[ix];
	}
	range = max - min;
}


void displayLargestDiff(int day[], double highTemp[], 
	double recordHigh[], double relativeHumid[], int &length)
{
	int largeday, ix;
	char choice;
	double largestdiff, difference, selected[100];

	cout << endl << endl;

	choice = selectField();

	/*
	
	This is mostly to demonstrate my understanding of passing
	the correct arrays into the subprogram, I figured I'd type it
	up in a comment instead of replace the code I have with the
	copyArray subprogram already.
	
	if (choice == 't')
	{
		for (ix = 0; ix < length - 1; ++ix)
		{
			cout << day[ix] << "-" << day[ix + 1] << "\t";
		}
		cout << endl;
		for (ix = 0; ix < length - 1; ++ix)
		{
			calcLargestDiff(highTemp, largestdiff, difference,
				ix, largeday);
			cout << difference << "\t";
		}
		cout << endl << endl;
		cout << "The largest difference is: " << largestdiff << endl;
		cout << "Which occurred between days " << day[largeday] << " and "
			<< day[largeday + 1] << endl << endl;
	}
	else if (choice == 'r')
	{
		for (ix = 0; ix < length - 1; ++ix)
		{
			cout << day[ix] << "-" << day[ix + 1] << "\t";
		}
		cout << endl;
		for (ix = 0; ix < length - 1; ++ix)
		{
			calcLargestDiff(recordHigh, largestdiff, difference,
				ix, largeday);
			cout << difference << "\t";
		}
		cout << endl << endl;
		cout << "The largest difference is: " << largestdiff << endl;
		cout << "Which occurred between days " << day[largeday] << " and "
			<< day[largeday + 1] << endl << endl;
	}
	else if (choice == 'h')
	{
		for (ix = 0; ix < length - 1; ++ix)
		{
			cout << day[ix] << "-" << day[ix + 1] << "\t";
		}
		cout << endl;
		for (ix = 0; ix < length - 1; ++ix)
		{
			calcLargestDiff(relativeHumid, largestdiff, difference,
				ix, largeday);
			cout << difference << "\t";
		}
		cout << endl << endl;
		cout << "The largest difference is: " << largestdiff << endl;
		cout << "Which occurred between days " << day[largeday] << " and "
			<< day[largeday + 1] << endl << endl;
	}*/

	if (choice == 't')
	{
		copyArray(selected, highTemp, length);
	}
	else if (choice == 'r')
	{
		copyArray(selected, recordHigh, length);
	}
	else if (choice == 'h')
	{
		copyArray(selected, relativeHumid, length);
	}
	for (ix = 0; ix < length - 1; ++ix)
	{
		cout << day[ix] << "-" << day[ix + 1] << "\t";
	}
	cout << endl;
	for (ix = 0; ix < length - 1; ++ix)
	{
		calcLargestDiff(selected, largestdiff, difference,
			ix, largeday);
		cout << difference << "\t";
	}
	cout << endl << endl;
	cout << "The largest difference is: " << largestdiff << endl;
	cout << "Which occurred between days " << day[largeday] << " and "
		<< day[largeday + 1] << endl << endl;
}
void calcLargestDiff(double selected[], double &largestdiff, double &diff,
	int &count, int &largeday)
{
	diff = selected[count] - selected[count + 1];
	if (diff < 0)
		diff = -diff;
	if (count == 0)
		largestdiff = diff;
	if (largestdiff < diff)
	{
		largestdiff = diff;
		largeday = count;
	}
}

void countDecades(double highTemp[], double recordHigh[],
	double relativeHumid[], int &length)
{
	int ix, decade[11], decadeint, index;
	double selected[100];
	char choice;
	for (ix = 0; ix < 11; ++ix)
	{
		decade[ix] = 0;
	}
	choice = selectField();

	if (choice == 't')
		copyArray(selected, highTemp, length);
	else if (choice == 'r')
		copyArray(selected, recordHigh, length);
	else if (choice == 'h')
		copyArray(selected, relativeHumid, length);
	for (ix = 0; ix < length; ++ix)
	{
		index = selected[ix] / 10.0;
		decadeint = index * 10;
		if (decadeint < 10)
			++decade[0];
		else if (decadeint >= 100)
			++decade[10];
		else
		{
			++decade[index];
		}
	}
	
	displayDecade(decade, length);
}
void displayDecade(int decade[], int &length)
{
	int ix, i;
	char tableorbar;
	cout << endl << endl;
	cout << "Would you like a table or bar chart to display the values? "
		<< "(t/b): ";
	cin >> tableorbar;
	while (tableorbar != 't' && tableorbar != 'b')
	{
		cout << endl << "Please enter t or b for a table or bar graph: ";
		cin >> tableorbar;
	}
	if (tableorbar == 't')
	{
		printf("\nDecade\t:\tCount\n");
		for (ix = 0; ix < 10; ++ix)
		{
			cout << ix * 10 << "\t\t" << decade[ix] << endl;
		}
		cout << "100+" << "\t\t" << decade[10] << endl;
	}
	else if (tableorbar == 'b')
	{
		printf("\nDecade\t:\tCount\n");
		for (ix = 0; ix < 10; ++ix)
		{
			cout << ix * 10 << "\t\t";
			for (i = 0; i < decade[ix]; ++i)
			{
				cout << "*";
			}
			cout << endl;
		}
		cout << "100+" << "\t\t";
		for (ix = 0; ix < decade[10]; ++ix)
			cout << "*";
		cout << endl << endl;
	}
}


void menu()
{
	cout << "(1) Display the data in the file."
		<< endl << endl
		<< "(2) Display the record high temperature and the year it was"
		<< endl
		<< " recorded for a given day."
		<< endl << endl
		<< "(3) Display the heat index for each day."
		<< endl << endl
		<< "(4) Display the average, the maximum, the minimum,"
		<< endl
		<< " and the range of one field."
		<< endl << endl
		<< "(5) Display the largest difference between two"
		<< endl
		<< " consecutive values in one field."
		<< endl << endl
		<< "(6) Display a chart summarizing the distribution"
		<< endl
		<< " of the values in one field."
		<< endl << endl
		<< "(7) Stop analyzing this collection of data."
		<< endl << endl;
}
char selectField()
{
	char choice;
	cout << "Which field would you like us to do the calculations for?"
		<< endl
		<< "Type t for high temperature,"
		<< endl
		<< "Type r for record high temperature,"
		<< endl
		<< "Type h for relative humidity."
		<< endl;
	cin >> choice;
	while (choice != 't' && choice != 'r' && choice != 'h')
	{
		cout << "Please enter t for temperature,"
			<< " r for record temp, or h for humidity: ";
		cin >> choice;
	}
	return choice;
}
void clearScreen()
{
	int ix;
	for (ix = 0; ix < 20; ++ix)
		cout << endl << endl << endl << endl;
}
void copyArray(double array1[], double array2[], int &length)
{
	int ix;
	for (ix = 0; ix < length; ++ix)
	{
		array1[ix] = array2[ix];
	}
}
void wait()
{
	char stop;
	cout << "Please enter any key to continue... ";
	cin >> stop;
}