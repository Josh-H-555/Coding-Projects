#include "Header Files/Person.h"
#include <iostream>

std::string Person::getName()
{
    return firstname + " " + lastname;
}

Person::Person(std::string first, std::string last, int arbitrary)
    : firstname(first), lastname(last), arbitrarynumber(arbitrary)
{
}

Person::~Person()
{
    std::cout << "Destructing" << std::endl;
}