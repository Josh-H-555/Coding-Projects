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

Person Person::operator+(int i) const
{
    return Person(firstname, lastname, arbitrarynumber + i);
}

// this is a free variable
Person operator+(int i, Person const &p)
{
    return p + i;
}

Person::~Person()
{
    std::cout << "Destructing" << std::endl;
}