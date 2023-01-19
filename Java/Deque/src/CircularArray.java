public class CircularArray
{
    private int size = 5;
    private int length;
    private int loopIndex;
    private String[] array;

    public CircularArray()
    {
        this.length = 0;
        this.array = new String[this.size];
        this.loopIndex = 0;
    }

    public int getLength()
    {
        return length;
    }

    public void insert(int index, String data)
    {
        if (this.length + 1 == this.size)
        {
            if (this.loopIndex != 0)
            {
                index = loopIndex - 1;
                --loopIndex;
            }
            else
            {
                growAndCopy();
            }
        }
        if (this.array[index] == null)
        {
            ++this.length;
        }
        this.array[index] = data;
    }

    public void delete(int index)
    {
        if (this.length == 0)
        {
            return;
        }
        if (index == this.loopIndex)
        {
            ++loopIndex;
        }
        this.array[index] = null;
        --this.length;

    }

    private void growAndCopy()
    {
        this.size *= 2;
        String[] tempArray = new String[this.size];
        for (int i = loopIndex, ix = 0; i < length; ++i, ++ix)
        {
            tempArray[ix] = this.array[i];
        }
        this.array = tempArray;
        loopIndex = 0;
    }

    public boolean isEmpty()
    {
        return this.length == 0;
    }

    public String toString()
    {
        String printArray = "";
        if (length == 0)
        {
            return printArray;
        }
        for (int i = loopIndex; i < length; ++i)
        {
            printArray += this.array[i];
            printArray += " ";
        }

        return printArray;
    }
}
