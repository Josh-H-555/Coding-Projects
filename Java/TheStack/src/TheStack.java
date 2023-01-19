import java.util.Arrays;

public class TheStack
{
    private String[] stackArray;
    private int stackSize;

    private int topOfStack = -1;

    TheStack(int size)
    {
        stackSize = size;
        stackArray = new String[size];

        Arrays.fill(stackArray, "-1");
    }

    public void push(String input)
    {
        if (topOfStack + 1 < stackSize)
        {
            ++topOfStack;
            stackArray[topOfStack] = input;
        }
        else
        {
            System.out.println("Unable to add to full stack");
        }
        displayStack();
    }

    public String pop()
    {
        if (topOfStack >= 0)
        {
            String returnItem = stackArray[topOfStack];
            --topOfStack;
            return returnItem;
        }
        else
        {
            return "Nothing to return.";
        }

    }

    public String peek()
    {
        if (topOfStack >= 0)
        {
            return stackArray[topOfStack];
        }
        else
        {
            return "Empty stack.";
        }
    }

    public void displayStack()
    {
        for (int i = 0; i < stackSize; ++i)
        {
            System.out.println("-----");
            System.out.println(stackArray[i]);
        }
        System.out.println("-----");
        System.out.println("-----");
    }

    public static void main(String[] args)
    {
        TheStack myStack = new TheStack(10);

        myStack.push("hello");

        System.out.println(myStack.pop());
    }

}