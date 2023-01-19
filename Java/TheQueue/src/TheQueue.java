import java.util.Arrays;

public class TheQueue
{

    private String[] queueArray;

    private int queueSize;

    private int front, rear, amountOfItems = 0;

    TheQueue(int size)
    {
        queueSize = size;
        front = size;
        rear = size;

        queueArray = new String[size];

        Arrays.fill(queueArray, "-1");
    }

    public void insert(String input)
    {
        if (amountOfItems + 1 <= queueSize)
        {
            queueArray[rear - 1] = input;
            --rear;
            ++amountOfItems;
        }
    }

    public void remove()
    {
        if (amountOfItems > 0)
        {
            --front;
        }
    }

    public void displayQueue()
    {
        System.out.print("| ");
        for (int i = rear; i < front; ++i)
        {
            System.out.print(queueArray[i] + " | ");
        }
        System.out.println("\n\n");
    }

    public static void main(String[] args)
    {
        TheQueue myQueue = new TheQueue(10);

        myQueue.displayQueue();
        myQueue.insert("hello");
        myQueue.displayQueue();
        myQueue.insert("goodbye");
        myQueue.displayQueue();
        myQueue.insert("josh");
        myQueue.displayQueue();
        myQueue.remove();
        myQueue.displayQueue();

    }
}