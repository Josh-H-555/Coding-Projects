#include <iostream>    // bracket for outside sources (external libraries)
#include "Functions.h" // double quotes for functions i have made
#include <vector>

// to my understanding, header files essentially work as a way of
// putting all of the function prototypes in one spot.

// algorithm library has things like sort and count. sort(begin(array), end(array))
// begin and end (from what I can tell) give POINTERS the start and end of the array.

// generally class member variables are private, and functions are usually public
// services are "helper functions" and are usually private

// what does pragma once do in header?
// prevents a file from being included multiple times
// not standard
//  -Wall -Wextra -Wpendantic -Werror for more warnings from the compiler

// MAKE FILES
// make is a separate tool that calls compiler
// controlled by makefile
// makefile
//  all:
//       clang++ -std=c++11 -Wall -Wextra -Wpedantic -Werror Account.cpp Transaction.cpp -o Simple

// i = static_cast<int>(d);

// void display(Transaction const& t);
// takes by const reference, and doesn't allow changing the parameter passed
// but faster, because by reference means it won't copy the data.
// ~Account() is a destructor. ~ signifies the destructor

string Transaction::Report() const // can mark member functions as const to signify
                                   // it won't modify member variables
{
    // ...
}

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