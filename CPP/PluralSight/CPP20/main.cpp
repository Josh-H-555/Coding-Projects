// static_cast<int>(my_integer);
// declare as late as possible

// include vs import

// c++20, modules were added. alternative to header files. faster compiles, less oddities

// no standard yet

//  import std; import <string>;

// if no constructors are written for a class, c++ will write a default constructor for you.

// however, as soon as any constructor is provided for the class, the provided default will be gone.

// RAII, resource acquisition is initialization, acquire in constructor, release in destructor

// ~Person(); is destructor syntax

// struct and class can essentially be the same. however, classes default to private acces
// structs default to public access

// use pragma once to be able to include headers everywhere. makes code more readable

// result = something ? 7 : 302; true -> 7, false -> 302

#include "Header Files/Person.h"
#include <iostream>
enum class FileError
{
    notfound,
    ok
};

enum Status
{
    Pending,
    Approved
};

int main()
{

    Status s = Pending;
    s = Approved;

    FileError fe = FileError::notfound;
    fe = FileError::ok;

    Person p1("Josh", "Hutchinson", 123);
    Person p2("Someone", "Else", 321);
    std::string name = p1.getName();

    return 0;
}