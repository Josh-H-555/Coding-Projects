// preprocessor directive
//load file and objects
#include <cstdlib>
#include <iostream>
#include <string>
#include <vector>
#include <sstream>
#include <limits>

using std::cout;
using std::cin;
using std::endl;

int randomNumber = 0;

const double PI = 3.14159;

int main(int argc, char** argv)
{
    bool married = true;
    char myGrade = 'A';
    unsigned short int age = 24;
    short int weight = 118;
    int days = 7;
    long int bigNumber = 1100000;
    float pi = 3.14;
    double bigFloat = 3.14159;
    long double bigDouble = 3.14159265359;
    auto autoGenerated = true;

    cout << "Min bool " << std::numeric_limits<bool>::min() << endl;
    cout << "Max bool " << std::numeric_limits<bool>::max() << endl << endl;

    cout << "Min unsigned short int " << std::numeric_limits<unsigned short int>::min() << endl;
    cout << "Max unsigned short int " << std::numeric_limits<unsigned short int>::max() << endl
         << endl;

    cout << "Min short int " << std::numeric_limits<short int>::min() << endl;
    cout << "Max short int " << std::numeric_limits<short int>::max() << endl << endl;

    cout << "Min unsigned long int " << std::numeric_limits<unsigned long int>::min() << endl;
    cout << "Max unsigned long int " << std::numeric_limits<unsigned long int>::max() << endl
         << endl;
    
    cout << "Min long int " << std::numeric_limits<long int>::min() << endl;
    cout << "Max long int " << std::numeric_limits<long int>::max() << endl << endl;

    printf("%c %d %5d %.3f %s\n", 'a', 10, 5, 3.1234, "Hi");

    std::string Question ("Enter number of miles: ");
    std::string miles;
    cout << Question;
    getline(cin, miles);
    float milesNum = std::stoi(miles);
    float km = milesNum * 1.60934f;

    printf("%f miles is %2.3f km", milesNum, km);

    return 0;
}