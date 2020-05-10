//Josh Hutchinson, Program 7, Binary Search Trees and Interfaces with NBA Players.

import java.util.Scanner;
import java.util.*;

interface KeyComp
{
    int keyCompareTo(KeyComp given);

    String toStringKey();
}

class Node
{
    public KeyComp data;
    public Node left;
    public Node right;
}


class KeyComparableNumber implements KeyComp
{
    private int compnumber;

    public KeyComparableNumber(int given)
    {
        compnumber = given;
    }

    public int getNumber()
    {
        return compnumber;
    }

    public int keyCompareTo(KeyComp given)
    {
        KeyComparableNumber  comparable = (KeyComparableNumber) given;
        int comp = compnumber - comparable.getNumber();

        if (comp == 0)
        {
            return 0;
        }

        else if (comp < 0)
        {
            return -1;
        }

        else
        {
            return 1;
        }
    }

    public String toStringKey()
    {
        String build = "" + compnumber;

        return build;
    }

}

class NBAPlayerKey implements KeyComp
{
    private int _jerseynum;
    private String _team;

    public NBAPlayerKey(int jersey, String team)
    {
        _jerseynum = jersey;
        _team = team;
    }

    public int getNum()
    {
        return _jerseynum;
    }

    public String getTeam()
    {
        return _team;
    }

    public int keyCompareTo(KeyComp given)
    {
        NBAPlayerKey compare = (NBAPlayerKey) given;

        int comp = _jerseynum - compare.getNum();

        if (compare.getTeam().equals(_team) && comp == 0)
        {
            return 0;
        }

        else if (comp == 0 && !(compare.getTeam().equals(_team)))
        {
            return -1;
        }


        if (comp < 0)
        {
            return -1;
        }

        else
        {
            return 1;
        }

    }

    public String toStringKey()
    {
        String build = "";

        build = build + _jerseynum;

        if (_team.length() < 3)
        {
            build = build + _team;
        }

        else
        {
            build = build + _team.substring (0, 3);
        }

        return build;
    }
}

class NBAPlayer extends NBAPlayerKey implements KeyComp
{
    private String _name;
    private double _aveScore;

    public NBAPlayer(int jersey, String team, String name, double average)
    {
        super(jersey, team);
        _name = name;
        _aveScore = average;
    }

    public String getName()
    {
        return _name;
    }

    public double getAverage()
    {
        return _aveScore;
    }

    public String toString()
    {
        String build = "";
        build = build + getNum() + " " + getTeam()  + " "
                + _name  + " " + _aveScore + "\n";

        return build;
    }

}

class Table
{
    private Node _root;

    public Table()
    {
        _root = null;
    }

    public boolean insert(KeyComp given)
    {
        if (_root != null)
        {
            return insert(_root, given);
        }
        else
        {
            Node add = new Node();
            add.data = given;
            add.left = add.right = null;
            _root = add;
            return true;
        }
    }

    private boolean insert(Node root, KeyComp given)
    {

        Node add = new Node();

        add.data = given;
        add.left = add.right = null;

        if (root != null)
        {
            int comp = given.keyCompareTo(root.data);

            if (comp == 0)
            {
                return false;
            }

            else if (comp < 0)
            {
                if (root.left == null)
                {
                    root.left = add;
                }
                else
                {
                    return insert(root.left, given);
                }
            }

            else
            {
                if (root.right == null)
                {
                    root.right = add;
                }
                else
                {
                    return insert(root.right, given);
                }
            }

        }
        return true;
    }

    public void delete(KeyComp given)
    {

        if (_root == null)
        {
            return;
        }


       _root = delete(_root, given);

    }

    private Node getMaxL(Node root)
    {
        if (root.right == null)
        {
            return root;
        }

        return getMaxL(root.right);
    }

    private Node getMaxR(Node root)
    {
        if (root.left == null)
        {
            return root;
        }

        return getMaxR(root.right);
    }

    private Node delete(Node root, KeyComp given)
    {

        if (root == null)
        {
            System.out.println("Operation could not be completed.");
            return null;
        }

        int comp = given.keyCompareTo(root.data);

        if (comp == 0)
        {
            if (root.left != null && root.right != null)
            {
                Node maxL, maxR;

                maxL = getMaxL(root.left);
                maxR = getMaxR(root.right);

                int max = maxL.data.keyCompareTo(maxR.data);

                if (max <= 0)
                {
                    root = maxR;
                    maxR = null;
                    return root;
                }
                else
                {
                    root = maxL;
                    maxL = null;
                    return root;
                }




            }

            else if (root.left == null && root.right == null)
            {
                System.out.println("Item deleted.");
                root = null;
                return root;
            }

            else if (root.left == null)
            {
                System.out.println("Item deleted.");
                root = root.right;
                return root;
            }

            else
            {
                System.out.println("Item deleted.");
                root = root.left;
                return root;
            }
        }

        if (comp < 0)
        {
            root.left = delete(root.left, given);
        }

        else
        {
            root.right = delete(root.right, given);
        }
        return root;
    }


    public KeyComp Search(KeyComp given)
    {
        if (_root == null)
        {
            return null;
        }

        else
        {
            return Search(_root, given);
        }
    }

    private KeyComp Search(Node root, KeyComp given)
    {
        if (root == null)
        {
            return null;
        }

        else
        {
            int comp = given.keyCompareTo(root.data);

            if (comp == 0)
            {
                return root.data;
            }

            else if (comp < 0)
            {
                return Search(root.left, given);
            }

            else
            {
                return Search(root.right, given);
            }
        }
    }

    public int getHeight()
    {
        if (_root == null)
        {
            return 0;
        }

        else if (_root.left == null && _root.right == null)
        {
            return 1;
        }


        return getHeight(_root);
    }

    private int getHeight(Node root)
    {

        int heightL, heightR;

        if (root == null)
        {
            return 0;
        }

        heightL = getHeight(root.left);
        heightR = getHeight(root.right) ;

        return (Math.max(heightL, heightR) + 1);


    }

    public int getSize()
    {
        if (_root == null)
        {
            return 0;
        }

        else if (_root.left == null && _root.right == null)
        {
            return 1;
        }

        else
            return getSize(_root);
    }

    private int getSize(Node root)
    {
        int sizeL, sizeR;

        if(root == null)
        {
            return 0;
        }

        else
        {
            sizeL = getSize(root.left);
            sizeR = getSize(root.right);
        }

        return sizeL + sizeR + 1;
    }

    public double getAveLevel()
    {
        double levelave;

        if (_root == null)
        {
            return 0;
        }

        else
        {
            levelave = getAveLevel(_root, 1);

            if (levelave == 0)
            {
                return 0;
            }

            else
            {
                levelave = levelave / getSize();

                return levelave;
            }
        }
    }

    private double getAveLevel(Node root, int level)
    {
        double heightL, heightR;

        if (root == null)
        {
            return 0;
        }


        heightL = getAveLevel(root.left, level + 1);
        heightR = getAveLevel(root.right, level + 1);

        return level + heightL + heightR;

    }

    public String showTree()
    {
        if (_root == null)
        {
            return null;
        }

        if (_root.left == null && _root.right == null)
        {
            String build = _root.data.toStringKey();
            return build;
        }

        else
        {
            return showTree(_root, 1);
        }

    }

    private String showTree(Node root, int level)
    {
        String build = "";
        String tabs = "";
        for (int ix = 0; ix < level; ++ix)
        {
            tabs = tabs + "\t";
        }

        if (root == null)
        {
            return "";
        }

        else
        {
            build = build + showTree(root.right, level+1) + "\n";
            build = build + tabs + root.data.toStringKey();
            build = build + showTree(root.left, level+1) + "\n";
        }

        return build;
    }

    public String toString()
    {
        String build = "";

        if (_root == null)
        {
            return null;
        }

        build = toString(_root);

        return build;
    }

    private String toString(Node root)
    {
        String build = "";

        if (root == null)
        {
            return build;
        }

        else
        {
            build = build + toString(root.left);
            build = build + root.data;
            build = build + toString(root.right);
        }

        return build;
    }

}


public class Program7
{
    public static Scanner kb = new Scanner(System.in);

    public static Random generator = new Random();

    public static void main (String [] args)
    {

        int choice;
        System.out.println("Welcome! We're going to make tables with Binary Search Trees!");
        do
        {
            System.out.println("What would you like to do?");
            System.out.println("(1). Create a table of NBA players.");
            System.out.println("(2). Create multiple randomly generated tables and see their performance.");
            System.out.println("(3). Quit");

            System.out.print("Please make your selection: ");
            choice = kb.nextInt();

            if (choice == 1)
            {
                NBATables();
            }

            else if (choice == 2)
            {
                double i = 0;
                Table testTable = new Table();

                System.out.printf("Size\tMax\t\tAverage\n");

                for (int tablesize = 4; i < (Math.pow(2, 15)); ++tablesize)
                {
                    double maxheight = 0;
                    double sumlevels = 0;

                    i = (Math.pow(2, tablesize));
                    for (int ix = 0; ix < 10; ++ix)
                    {
                        testTable = new Table();
                        do
                        {
                            KeyComparableNumber randinsert = new KeyComparableNumber(generator.nextInt());
                            testTable.insert(randinsert);
                        }while (testTable.getSize() < i);
                        if (testTable.getHeight() > maxheight)
                        {
                            maxheight = testTable.getHeight();
                        }
                        sumlevels += testTable.getAveLevel();


                    }


                    System.out.printf("%5d\t%3.2f\t%4.2f\n", testTable.getSize(),
                            maxheight, (sumlevels/10));

                }

            }

            else if (choice == 3)
            {
                System.out.println("Thank you! Goodbye.");
            }

            else
            {
                System.out.print("Please choose a number between 1 and 3: ");
                choice = kb.nextInt();
            }
        }
        while(choice != 3);

    }

    public static void NBATables()
    {
        int choice;
        int jersey;
        String team, name;
        double average;
        boolean flag;

        Table nbaplayers = new Table();

        System.out.println("Welcome! We're going to create a Table of " +
                "NBA Players!\nWe'll be able to manipulate them as we go along.");

        do
        {
            menu();

            System.out.print("\nPlease make your selection: ");
            choice = kb.nextInt();

            if (choice == 1)
            {
                nbaplayers = new Table();
            }

            else if (choice == 2)
            {
                System.out.print("Enter new player's Jersey Number: ");
                jersey = kb.nextInt();
                System.out.print("Enter new player's team: ");
                team = kb.next();
                System.out.print("Enter new player's name: ");
                name = kb.next();
                System.out.print("Enter new player's score average: ");
                average = kb.nextDouble();

                NBAPlayer add = new NBAPlayer(jersey, team, name, average);

                flag = nbaplayers.insert(add);

                if (flag)
                {
                    System.out.println("Insertion successful.");
                }
                else
                {
                    System.out.println("Insertion could not be completed.");
                }
            }

            else if (choice == 3)
            {
                NBAPlayerKey deleteitem;

                System.out.print("Enter desired player jersey: ");
                jersey = kb.nextInt();
                System.out.print("Enter desired player's team: ");
                team = kb.next();

                deleteitem = new NBAPlayerKey(jersey, team);

                nbaplayers.delete(deleteitem);
            }

            else if (choice == 4)
            {
                KeyComp info;
                NBAPlayerKey search;

                System.out.print("Enter desired player jersey: ");
                jersey = kb.nextInt();
                System.out.print("Enter desired player's team: ");
                team = kb.next();

                search = new NBAPlayerKey(jersey, team);

                info = nbaplayers.Search(search);

                if (info == null)
                {
                    System.out.println("Player not found.");
                }

                else
                {
                    System.out.println(info);
                }
            }

            else if (choice == 5)
            {
                System.out.println(nbaplayers.getHeight());
            }

            else if (choice == 6)
            {
                System.out.println(nbaplayers.getSize());
            }

            else if (choice == 7)
            {
                System.out.println("Average Level: " + nbaplayers.getAveLevel());
            }

            else if (choice == 8)
            {
                System.out.println(nbaplayers.showTree());
            }

            else if (choice == 9)
            {
                System.out.println(nbaplayers);
            }

            else if (choice == 10)
            {
                System.out.println("Thank you for using my program! Goodbye.");
            }

            else
            {
                System.out.println("Please enter a valid selection from 1 to 10");
            }


        }while (choice != 10);
    }

    public static void menu()
    {

        System.out.println("\nPlease select what you would like to do with " +
                "the Table of Players.");
        System.out.println("(1). Create a new Table.");
        System.out.println("(2). Insert a new player into the Table.");
        System.out.println("(3). Delete a player from the Table.");
        System.out.println("(4). Search for a player, given their key.");
        System.out.println("(5). See the height of the Table tree.");
        System.out.println("(6). See the size, or amount of Nodes in the Table's tree.");
        System.out.println("(7). See the average level of the nodes in the Table tree.");
        System.out.println("(8). Display the contents of the Table in a tree-like fashion.");
        System.out.println("(9). Display the contents of the Table in order by keys.");
        System.out.println("(10). Quit.");

    }
}
