#include <iostream>
#include <cstdlib>
#include <string>
#include <vector>
#include <sstream>
#include <limits>

using namespace std;

int main()
{
    string calculation;
    cout << "Please enter your calculation separated by spaces: ";
    getline(cin, calculation);

    vector<string> divideCalculation;

    stringstream ss(calculation);
    string placeholderValue;
    char delimiter = ' ';

    while(getline(ss, placeholderValue, delimiter))
    {
        divideCalculation.push_back(placeholderValue);
    }

    cout << stof(divideCalculation[0]) << divideCalculation[1] 
        << stof(divideCalculation[2]) 
        << "=";

    if (divideCalculation[1] == "*")
    {
        cout << stof(divideCalculation[0]) * stof(divideCalculation[2]);
    }
    else if (divideCalculation[1] == "+")
    {
        cout << stof(divideCalculation[0]) + stof(divideCalculation[2]);
    }
    else if (divideCalculation[1] == "-")
    {
        cout << stof(divideCalculation[0]) - stof(divideCalculation[2]);
    }
    else if (divideCalculation[1] == "/")
    {
        cout << stof(divideCalculation[0]) / stof(divideCalculation[2]);
    }
    else
    {
        cout << "Please enter *, +, -, or /" << endl;
    }

    return 0;
}