// standard input/output library
#include <iostream>

// standard library. at time of addition, using to generate a random number
#include <stdlib.h>
#include <string.h>

// automatically uses the std namespace, allowing to call methods without the 'std::'
// using namespace std;

// main is usually int, but can be void in microsoft's c++ versions.
int main()
{
    //---------------------PRINTING STUFF

    // std::cout << "*" << std::endl;
    // std::cout << "* *" << std::endl;
    // std::cout << "* * *\n";
    // std::cout << "* * * *\n";
    // // endl and \n effectively do the same thing, but
    // // endl is slower, because it also does something to clear the
    // // input buffer. don't fully understand it. but it's whatevs.

    //---------------------MODERN INITIALIZATION

    // int age {52};

    //---------------------CIN STUFF

    // we can read more than one number at a time

    // int a, b;

    // std::cout << "Enter two numbers\n";

    // std::cin >> a >> b;

    // std::cout << a << b << std::endl;

    //------------------------DIFFERENCE BETWEEN A++ AND ++A

    // ++a -> a = a + 1 ......... a++ -> b = a; a = a + 1

    // int a = 10, x = 10;
    // int b, c;

    // b = ++a;
    // c = x++;

    // std::cout << "a: " << a << "\nb: " << b << "\nc: " << c << "\nx: " << x << std::endl;

    //------------------------- OPERATOR PRECEDENCE

    // ++, --, -, ! before *, /, %, before +, -, before =, +=, -=, *=, /=

    //------------------------- NEW IF STATEMENT STUFF

    // Can initialize variables inside the if to reduce the scope
    // In this case, x will only be visible inside of the if statement

    // if (int x = 0; x < 10)
    //      std::cout << "This gets printed";

    //----------------------CONTINUE

    // Tells compiler to jump back to beginning of loop.
    // Pretty cool for handling errors

    // while (true)
    // {
    //     cin >> x >> y;

    //     if (y == 0)
    //     {
    //         cout << "Y is zero, please try two different numbers";
    //         continue;
    //     }
    //     cout << x / y << "\n";
    // }

    //--------------------------ARRAY INITIALIZATION
    // int array1[100] = {5};
    // int array2[100] {5}; this is the more standard c++ initialization method
    // int array3[] {1, 2, 3}; this automatically sizes the array

    using namespace std;

    struct Employee
    {
        string name;
        int age;
        int salary;
        char gender;
        int active;
    };

    const int SIZE{50};
    int length{0};
    Employee employees[SIZE];

    char yesno = 'y';
    while (yesno == 'y')
    {
        int choice{};

        cout << "Enter your choice:\n"
             << "1) Add new employee\n"
             << "2) Print all employees\n"
             << "3) Delete by age\n"
             << "4) Update salary by name\n"
             << "5) Quit\n";

        while (choice != 1 && choice != 2 && choice != 3 && choice != 4 && choice != 5)
        {
            cin >> choice;
        }
        if (choice == 1)
        {
            int age{0}, salary{0};
            string name;
            char gender;
            cout << "Enter their name: ";
            cin.ignore();
            getline(cin, name);
            cout << "Enter their age: ";
            cin >> age;
            cout << "Enter their salary: ";
            cin >> salary;
            cout << "Enter their gender: ";
            cin >> gender;

            employees[length].age = age;
            employees[length].name = name;
            employees[length].salary = salary;
            employees[length].gender = gender;
            employees[length].active = 1;

            ++length;
        }
        else if (choice == 2)
        {
            for (int i = 0; i < length; ++i)
            {
                if (employees[i].active)
                {
                    cout << employees[i].name << '\n'
                         << employees[i].salary << '\n'
                         << employees[i].age << '\n'
                         << employees[i].gender << "\n\n"
                         << "------------"
                         << "\n\n";
                }
            }
        }
        else if (choice == 3)
        {
            int start_age{}, end_age{};
            cout << "Enter the starting age: ";
            cin >> start_age;
            cout << "Enter the ending age: ";
            cin >> end_age;

            for (int i = 0; i < length; ++i)
            {
                if (employees[i].age > start_age && employees[i].age < end_age)
                {
                    employees[i].active = 0;
                }
            }
        }
        else if (choice == 4)
        {
            string given;
            cout << "Please enter the employee's name: ";
            cin.ignore();
            getline(cin, given);

            for (int i = 0; i < length; ++i)
            {
                if (employees[i].name == given)
                {
                    int salary{};
                    cout << "Enter the new salary: ";
                    cin >> salary;
                    employees[i].salary = salary;
                    break;
                }
            }
        }
        else if (choice == 5)
        {
            yesno = 'n';
        }
    }

    return 0;
}