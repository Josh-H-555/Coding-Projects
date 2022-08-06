#include <iostream>    // bracket for outside sources (external libraries)
#include "Functions.h" // double quotes for functions i have made

// to my understanding, header files essentially work as a way of
// putting all of the function prototypes in one spot.

using std::cin;
using std::cout;
using std::endl;

int main()
{
    int total = add(3, 4);
    cout << total << endl;
    int total2 = add(3, 4, 5);
    cout << endl
         << total2 << endl;

    std::string str = "This is a string";
    cout << str.starts_with("This");

    return 0;
}