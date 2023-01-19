#include <string>
#pragma once

class Person
{
private:
    std::string firstname;
    std::string lastname;

    int arbitrarynumber;

public:
    std::string getName();
    Person operator+(int i) const;
    Person(std::string first, std::string last, int arbitrary);
    ~Person();
};
Person operator+(int i, Person const &p);