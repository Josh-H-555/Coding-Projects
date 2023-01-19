// good ol header guard
#pragma once

// This heap will be implemented as a Minimum Heap.

class Heap
{
private:
    // member variables
    int heapSize;
    int heapArray[];

    // private helper functions used when adding or removing
    void moveUp(int array[], int i);
    void moveDown(int array[], int i);

public:
    // constructor and deconstructor
    Heap(int size);
    ~Heap();

    // adds to bottom, bubbles up
    bool add(int value);

    // removes from top, sets last element to top, falls down
    int remove();

    // sort starts from bottom
    void sort();

    // return a sorted copy of the heap
    int *getSorted();
};