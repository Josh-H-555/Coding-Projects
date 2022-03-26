//Josh Hutchinson, Letter Frequencies

#include <iostream>
#include <fstream>
using namespace std;

void menu();
void initcounters(int counts[]);
int getLine(char buffer[]);
void computeFrequency(char string[], int counter[]);
char convertUpper(char given);
void FrequencyTable(int counters[]);

int main()
{
	char oneinput;
	char createdString[501];
	int counters[26];
	int length = 26;
	int choice, boolflag;

	cout << "Hello! Let's calculate some letter frequencies and read files."
		<< endl << endl;

	boolflag = getLine(createdString);
	if (boolflag == 0)
	{
		cout << "Something went wrong, please try again." << endl;
	}
	else
	{
		cout << "File Loaded." << endl << endl;
	}

	do
	{
		menu();
		cin >> choice;

		if (choice == 1)
		{
			boolflag = getLine(createdString);
			if (boolflag == 0)
			{
				cout << "Something went wrong, please try again." << endl;
			}
			else
			{
				cout << "File Loaded." << endl << endl;
			}
		}

		if (choice == 2)
		{
			initcounters(counters);

			computeFrequency(createdString, counters);
		}

		if (choice == 3)
		{
			cout << "Please enter a character: ";
			cin >> oneinput;

			oneinput = convertUpper(oneinput);

			cout << oneinput << endl << endl;
		}

		if (choice == 4)
		{
			FrequencyTable(counters);
		}
		
	} while (choice != 5);
	
}

void menu()
{
	printf("(1) Read text from a new file and create a string from it.\n"
		"(2) Compute the frequencies of letters from loaded string.\n"
		"(3) Return a lower-case version of given character.\n"
		"(4) Display the letter frequencies in a table.\n"
		"(5) Quit.\n\n");
	cout << "Please enter your choice: ";
}

void initcounters(int counter[])
{
	for (int i = 0; i < 26; ++i)
	{
		counter[i] = 0;
	}
}

int getLine(char buffer[])
{
	char fileName[129];
	fstream infile;
	int ix;
	char c;

	cout << "Enter the file name: ";
	cin >> fileName;

	infile.open(fileName, ios::in);
	if (infile.fail())
		return 0;

	ix = 0;

	c = ' ';
	while (!infile.eof() && c != '\n')
	{
		c = infile.get();
		if (c != '\n')
		{
			buffer[ix] = c;
			++ix;
		}
	}
	buffer[ix] = '\0';
	infile.close();

	return 1;
}

void computeFrequency(char string[], int counter[])
{
	char converted;
	int index;

	for (int i = 0; string[i]; ++i)
	{
		if (string[i] >= 'A' && string[i] <= 'Z')
		{
			converted = convertUpper(string[i]);
			index = converted - 'a';
			++counter[index];
		}
		else
		{
			index = string[i] - 'a';
			++counter[index];
		}
	}
}

char convertUpper(char given)
{
	given = given + ('a' - 'A');

	return given;
}

void FrequencyTable(int counters[])
{
	char stop;
	char letter = 'a';
	for (int i = 0; i < 26; ++i)
	{
		printf("%c\t%2d\n", letter, counters[i]);
		letter = letter + 1;
		if (i == 12)
		{
			cout << "Please enter any key to continue... ";
			cin >> stop;
		}
	}
}