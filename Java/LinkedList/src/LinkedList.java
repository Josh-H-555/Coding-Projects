public class LinkedList
{


    public static void main(String[] args)
    {

    }
}

class Link
{
    public Object data;
    public Link next;
    public Link previous; // for a doubly linked list
}

class List
{
    private Link head;
    private Link foot; // for double ended linked list
    private int size;

    public List()
    {
        this.head = null;
        this.size = 0;
        this.foot = null; // for double ended linked list
    }

    public boolean isEmpty()
    {
        return (this.head == null);
    }

    public void insert(Object item, int position)
    {
        if (position < 0)
        {
            System.out.println("Invalid position.");
            return;
        }

        Link newData = new Link();
        newData.data = item;

        if (this.head == null)
        {
            System.out.println("Empty list. Inserting as first item.");
            this.head = newData;
            this.foot = newData; // for double ended liked list
        }

        else if (position == 1)
        {
            newData.next = this.head;
            this.head = newData;
        }

        else if (position >= size + 1)
        {
            Link link = this.head;

            while (link.next != null)
            {
                link = link.next;
            }
            link.next = newData;
            newData.previous = link; // for doubly linked list
            this.foot = newData; // for double ended linked list

        }

        else
        {
            Link link = this.head;

            for (int i = 0; i < position - 1; ++i)
            {
                link = link.next;
            }

            newData.next = link.next;
            link.next = newData;
            newData.previous = link; // for doubly linked list
        }

        ++this.size;

    }

    public Link findItem(Object given)
    {
        Link link = this.head;

        if (!isEmpty())
        {
            while (!link.data.equals(given))
            {
                if (link.next == null)
                {
                    return null;
                }
                link = link.next;
            }
        }
        return link;
    }

    public Link removeItem(Object given)
    {
        Link current = this.head;
        Link previous = this.head;

        while (!current.data.equals(given))
        {
            if (current.next == null)
            {
                return null;
            }
            previous = current;
            current = current.next;
        }

        if (current == this.head)
        {
            this.head = this.head.next;
        }

        else if (current == this.foot)
        {
            this.foot = previous; // for double ended lined list
        }

        else
        {
            previous.next = current.next;
        }

        return current;
    }

    public Link removeFirst()
    {
        Link reference = head;

        if(!isEmpty())
        {
            head = head.next;
        }
        else
        {
            System.out.println("Empty");
        }

        return reference;
    }

    public void display()
    {
        Link link = head;

        while (link != null)
        {

        }
    }

}