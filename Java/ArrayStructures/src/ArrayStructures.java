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
            recursiveBinarySearch(value, i + 1, high);
        }

        else
        {
            recursiveBinarySearch(value, low, i - 1);
        }

    }

    public static void main(String[] args)
    {
        ArrayStructures newArray = new ArrayStructures();

        newArray.generateRandomArray();

        newArray.printArray();

        System.out.println(newArray.getValueAtIndex(3));

        System.out.println(newArray.doesArrayContainValue(18));

        newArray.deleteIndex(4);

        newArray.printArray();

        newArray.insertValue(50);

        newArray.printArray();

        newArray.linearSearch(17);

    }
}