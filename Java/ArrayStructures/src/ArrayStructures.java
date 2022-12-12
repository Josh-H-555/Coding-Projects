public class ArrayStructures
{

    private int[] theArray = new int[50];

    private int arraySize = 10;

    public void generateRandomArray()
    {
        for (int i = 0; i < arraySize; ++i)
        {
            theArray[i] = (int)(Math.random()*10) + 10;
        }

        int myStaticValue = (int)(Math.random() * 10);
        theArray[myStaticValue] = 14;

    }

    public void printArray()
    {
        System.out.println("---------");
        for (int i = 0; i < arraySize; ++i)
        {
            System.out.print("| " + i + " | ");
            System.out.println(theArray[i] + " |");
            System.out.println("---------");
        }
    }

    public int getValueAtIndex(int index)
    {
        if (index < arraySize)
        {
            return theArray[index];
        }

        return 0;
    }

    public boolean doesArrayContainValue(int searchValue)
    {
        boolean valueInArray = false;

        for (int i = 0; i < arraySize; ++i)
        {
            if (theArray[i] == searchValue)
            {
                valueInArray = true;
                return valueInArray;
            }
        }

        return valueInArray;
    }

    public void deleteIndex(int index)
    {
        if (index < arraySize)
        {
            for (int i = index; i < arraySize - 1; ++i)
            {
                theArray[i] = theArray[i + 1];
            }

            --arraySize;
        }
    }

    public void insertValue(int value)
    {
        if (arraySize < 50)
        {
            theArray[arraySize] = value;

            ++arraySize;
        }
    }

    public String linearSearch(int value)
    {
        String values = "Values: ";

        for (int i = 0; i < arraySize; ++i)
        {
            if (theArray[i] == value)
            {
                values = values + i + " ";
            }
        }

        return values;
    }

    public void selectSort()
    {
        for (int i = 0; i < arraySize - 1; ++i)
        {
            for (int ix = i + 1; ix < arraySize; ++ix)
            {
                if (theArray[i] > theArray[ix])
                {
                    int temp = theArray[i];
                    theArray[i] = theArray[ix];
                    theArray[ix] = temp;
                }
            }
        }
    }

    public void bubbleSort()
    {
        for (int i = arraySize - 1; i > 1; --i)
        {
            boolean swap = false;
            for (int ix = 0; ix < i; ++i)
            {
                if (theArray[ix] > theArray[ix+1])
                {
                    swap = true;
                    int temp = theArray[ix];
                    theArray[ix] = theArray[ix+1];
                    theArray[ix+1] = temp;
                }
            }
            if (!swap)
            {
                return;
            }
        }
    }

    public void insertionSort()
    {
        for (int i = 1; i < arraySize; ++i)
        {
            int ix = i;

            int toInsert = theArray[i];
            while ((ix > 0) && (theArray[ix-1] > toInsert))
            {
                theArray[ix] = theArray[ix-1];
                --ix;
            }

            theArray[ix] = toInsert;
        }
    }


    public int iterativeBinarySearch(int value)
    {
        int low = 0;
        int high = arraySize;

        for (int i = (low+high)/2; low < high; i = (low+high)/2)
        {
            if (theArray[i] == value)
            {
                return i;
            }

            else if (theArray[i] < value)
            {
                low = i + 1;
            }

            else
            {
                high = i - 1;
            }

        }

        return -1;

    }

    public int recursiveBinarySearch(int value, int low, int high)
    {
        if (low > high)
        {
            return -1;
        }

        int i = (low + high) / 2;

        if (theArray[i] == value)
        {
            return i;
        }

        else if (theArray[i] < value)
        {
            return recursiveBinarySearch(value, i + 1, high);
        }

        else
        {
            return recursiveBinarySearch(value, low, i - 1);
        }

    }

    public static void main(String[] args)
    {
        ArrayStructures newArray = new ArrayStructures();

        newArray.generateRandomArray();

        newArray.printArray();

        System.out.println(newArray.getValueAtIndex(3));

        System.out.println(newArray.doesArrayContainValue(18));

        //newArray.deleteIndex(4);

        newArray.printArray();

        newArray.insertValue(16);

        newArray.printArray();

        System.out.println(newArray.linearSearch(14));

        newArray.selectSort();

        newArray.printArray();

        System.out.println(newArray.linearSearch(14));

        System.out.println(newArray.iterativeBinarySearch(14));

        System.out.println(newArray.recursiveBinarySearch(14, 0, newArray.arraySize));

        newArray.generateRandomArray();

        newArray.bubbleSort();

        newArray.printArray();

        newArray.generateRandomArray();

        newArray.insertionSort();

    }
}