//Josh Hutchinson, Program 6, Linked List practice and manipulation

import java.util.Scanner;

class Link
{
    public Object data;
    public Link next;
}

class List
{
    private Link _head;
    private int _size;

    public List()
    {
        _head = null;
        _size = 0;
    }

    public void insert(Object given, int pos)
    {
        if (pos < 1 || pos > _size + 1)
        {
            System.out.println("Invalid position. Cannot insert.");
            return;
        }

        Link add = new Link();
        add.data = given;

        if (_head == null && pos == _size + 1)
        {
            add.next = null;
            _head = add;
        }

        else if (pos == 1)
        {
            add.next = _head;
            _head = add;
        }

        else if (pos == _size + 1)
        {
            Link curr = _head;

            while(curr.next != null)
            {
                curr = curr.next;
            }

            curr.next = add;
            add.next = null;
        }

        else
        {
            Link curr = _head;
            for (int ix = 1; ix < pos - 1; ++ix)
            {
                curr = curr.next;
            }

            add.next = curr.next;
            curr.next = add;
        }
        ++_size;
    }

    public void deleteRange(int min, int max)
    {
        if (max > _size)
        {
            System.out.println("Range larger than size of data." +
                    "\nDeleting all items.");

            _head = null;
            _size = 0;
        }

        else
        {
            Link curr = _head;
            Link grab;

            if (min == 1)
            {
                for(int ix = 1; ix <= max; ++ix)
                {
                    _head = _head.next;
                    --_size;
                }
            }
            for(int ix = 1; ix < min - 1; ++ix)
            {
                curr = curr.next;
            }
            grab = curr;
            for(int ix = min - 1; ix < max; ++ix)
            {
                curr = curr.next;
            }

            grab.next = curr.next;

            if (min != max)
                _size = (max - min) + 1;
            else
                --_size;
        }

        System.out.println("Deletion Successful.");
    }

    public void deleteItem(Object given)
    {

        if (_head == null)
        {
            System.out.println("List is empty.");
            return;
        }

        while (_head != null && _head.data.equals(given))
        {
            _head = _head.next;
            --_size;
        }

        if (_head != null)
        {
            Link prev = _head;
            Link curr = _head.next;
            while (curr != null)
            {
                while (curr != null && curr.data.equals(given))
                {
                    prev.next = curr.next;
                    --_size;
                    curr = curr.next;
                }

                if (curr != null)
                {
                    prev = curr;
                    curr = curr.next;
                }
            }
        }

        System.out.println("Deletion Successful.");
    }

    public Object retrieveItem(int pos)
    {
        if (_head == null)
        {
            System.out.println("List is empty.");
            return null;
        }

        if (pos < 1 || pos > _size)
        {
            System.out.println("Invalid position.");
            return null;
        }

        else
        {
            Link curr = _head;

            for (int ix = 1; ix < pos; ++ix)
            {
                curr = curr.next;
            }

            return curr.data;
        }
    }

    public List findItems(Object given)
    {
        if (_head == null)
        {
            System.out.println("List is empty.");
            return null;
        }

        List positions = new List();

        Link curr = _head;

        int count = 1;

        for (int ix = 1; curr != null; ++ix)
        {
            if (curr.data.equals(given))
            {
                positions.insert(ix, count);
                ++count;
            }
            curr = curr.next;
        }

        return positions;
    }


    public int getSize()
    {
        return _size;
    }

    public String toString()
    {
        Link traverse = _head;
        String info = "";

        while (traverse != null)
        {
            info = info + traverse.data + "\n";
            traverse = traverse.next;
        }


        return info;
    }
}

class InventoryItem
{
    private String _name;
    private int _quantity;
    private double _cost;

    public InventoryItem()
    {
        _name = "";
        _quantity = 0;
        _cost = 0;
    }

    public InventoryItem(String name, int quant, double cost)
    {
        _name = name;
        _quantity = quant;
        _cost = cost;
    }

    public String getName()
    {
        return _name;
    }

    public int getQuantity()
    {
        return _quantity;
    }

    public double getCost()
    {
        return _cost;
    }

    public boolean equals(Object other)
    {
        if (other instanceof InventoryItem)
        {
            InventoryItem compare = (InventoryItem) other;
            if (_name.equals(compare.getName()))
            {
                return true;
            }
        }
        return false;
    }

    public String toString()
    {
        String info = "";
        info = info + _name + "\n" + _quantity + "\n" + _cost + "\n";

        return info;
    }
}

public class Program6
{
    public static Scanner kb = new Scanner(System.in);

    public static void main (String [] args)
    {
        String insert, name, deletion;
        int position, quantity, deletemin, deletemax;
        double cost;
        int choice = 0;
        int listchoice = 0;
        List stringlist = new List();
        List invlist1 = new List();
        List invlist2 = new List();

        System.out.println("Welcome to my program! We're going to make" +
                " and manipulate some lists!\n");

        do
        {
            choice = 0;
            listchoice = menu();

            while (!(listchoice == 1 || listchoice == 2 || listchoice == 3 || listchoice == 4))
            {
                System.out.println("Please enter a valid choice from 1 to 4.");
                listchoice = menu();
            }

            while (listchoice != 4 && choice != 9)
            {
                choice = choicemenu();

                if (choice == 1)
                {
                    if (listchoice == 1)
                    {
                        stringlist = new List();
                    }
                    else if (listchoice == 2)
                    {
                        invlist1 = new List();
                    }
                    else
                    {
                        invlist2 = new List();
                    }
                    System.out.println("New list created.");
                }

                else if (choice == 2)
                {
                    if (listchoice == 1)
                    {
                        System.out.print("Please enter what you'd like to insert: ");
                        insert = kb.next();
                        System.out.print("Please enter the position you would " +
                                "like to insert it: ");
                        position = kb.nextInt();

                        stringlist.insert(insert, position);
                    }

                    else
                    {
                        System.out.print("Name of item: ");
                        name = kb.next();
                        System.out.print("Quantity of the item: ");
                        quantity = kb.nextInt();
                        while (quantity < 1)
                        {
                            System.out.println("Please enter a valid quantity: ");
                            quantity = kb.nextInt();
                        }
                        System.out.print("Cost of the item: ");
                        cost = kb.nextDouble();
                        while (cost < 0)
                        {
                            System.out.println("Please enter a valid cost: ");
                            cost = kb.nextDouble();
                        }

                        System.out.println("Please enter where you'd like to insert it: ");
                        position = kb.nextInt();

                        InventoryItem add = new InventoryItem(name, quantity, cost);

                        if (listchoice == 2)
                        {
                            invlist1.insert(add, position);
                        }
                        else
                        {
                            invlist2.insert(add, position);
                        }
                    }
                    System.out.println("Insertion successful.");
                    // This println isn't in the List class strictly because
                    // option 6 will print insertion successful each time it
                    // matches, and then will display the positions of the items.
                    // was real annoying. simple fix, small inconsistency.
                }

                else if (choice == 3)
                {
                    System.out.print("Please enter the starting value to delete: ");
                    deletemin = kb.nextInt();
                    while (deletemin < 1)
                    {
                        System.out.print("Please enter a valid minimum: ");
                        deletemin = kb.nextInt();
                    }

                    System.out.print("Please enter the ending value to delete: ");
                    deletemax = kb.nextInt();

                    if (listchoice == 1)
                    {
                        stringlist.deleteRange(deletemin, deletemax);
                    }

                    else if (listchoice == 2)
                    {
                        invlist1.deleteRange(deletemin, deletemax);
                    }

                    else
                    {
                        invlist2.deleteRange(deletemin, deletemax);
                    }
                }

                else if (choice == 4)
                {
                    System.out.print("Please enter the item you'd like to delete: " );
                    deletion = kb.next();

                    if (listchoice == 1)
                    {
                        stringlist.deleteItem(deletion);
                    }

                    else if (listchoice == 2)
                    {
                        InventoryItem delete = new InventoryItem(deletion, 0, 0);
                        invlist1.deleteItem(delete);
                    }

                    else
                    {
                        InventoryItem delete = new InventoryItem(deletion, 0, 0);
                        invlist2.deleteItem(delete);
                    }
                }

                else if (choice == 5)
                {
                    System.out.print("Please enter the position of the item" +
                            " you would like to retrieve: ");
                    position = kb.nextInt();

                    if (listchoice == 1)
                    {
                        System.out.println(stringlist.retrieveItem(position));
                    }

                    else if (listchoice == 2)
                    {
                        System.out.println(invlist1.retrieveItem(position));
                    }

                    else
                    {
                        System.out.println(invlist2.retrieveItem(position));
                    }
                }

                else if (choice == 6)
                {
                    System.out.println("Please enter the item you would like to find: ");
                    name = kb.next();

                    if (listchoice == 1)
                    {
                        System.out.println(stringlist.findItems(name));
                    }

                    else
                    {
                        InventoryItem find = new InventoryItem(name, 0, 0);

                        if (listchoice == 2)
                        {
                            System.out.println(invlist1.findItems(find));
                        }

                        else
                        {
                            System.out.println(invlist2.findItems(find));
                        }
                    }
                }

                else if (choice == 7)
                {
                    if (listchoice == 1)
                    {
                        System.out.println(stringlist.getSize());
                    }
                    else if (listchoice == 2)
                    {
                        System.out.println(invlist1.getSize());
                    }
                    else
                    {
                        System.out.println(invlist2.getSize());
                    }
                }

                else if (choice == 8)
                {
                    if (listchoice == 1)
                    {
                        System.out.println(stringlist);
                    }

                    else if (listchoice == 2)
                    {
                        System.out.println(invlist1);
                    }

                    else
                    {
                        System.out.println(invlist2);
                    }
                }

                else if (choice == 9)
                {
                    System.out.println("Going back.");
                }

                else
                {
                    System.out.println("Please choose an option from 1 to 9.");
                }

            }

        }while (listchoice != 4);

        System.out.println("Thank you for using my program! Goodbye.");
    }

    public static int menu()
    {
        int choice;

        System.out.println("Which list would you like to manipulate?");
        System.out.println("(1). List of Strings");
        System.out.println("(2). Inventory List #1");
        System.out.println("(3). Inventory List #2");
        System.out.println("(4). Quit");

        System.out.print("Please choose an item: ");
        choice = kb.nextInt();

        return choice;
    }

    public static int choicemenu()
    {
        int choice;
        System.out.println("\nWhat would you like to do with this list?");
        System.out.println("(1). Create an empty list.");
        System.out.println("(2). Insert an item at a given position.");
        System.out.println("(3). Delete items in a range, from a start to end position.");
        System.out.println("(4). Delete all occurrences of an item.");
        System.out.println("(5). Return the item at a given position.");
        System.out.println("(6). Return a list of positions where a given item is.");
        System.out.println("(7). Return the number of items in a list.");
        System.out.println("(8). Show the contents of the list.");
        System.out.println("(9). Go back.");

        System.out.print("\nPlease choose an item: ");
        choice = kb.nextInt();

        return choice;
    }
}
