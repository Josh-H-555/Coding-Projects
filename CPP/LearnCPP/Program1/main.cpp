#include <iostream>

int main()
{
    std::cout << "Please enter a number: ";

    int firstNum{};

    std::cin >> firstNum;

    std::cout << "Enter a second number: ";

    int secondNum{};

    std::cin >> secondNum;

    std::cout << firstNum << " + " << secondNum << " is " << firstNum + secondNum << '\n';

    std::cout << firstNum << " - " << secondNum << " is " << firstNum - secondNum << '\n';

    return 0;
}