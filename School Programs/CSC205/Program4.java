//Josh Hutchinson, Program 4, Creating and using a deque for multiple abstract
//data types.


/*get the array of greek heroes to work, you got the ai for the cpu set up,
you just need to continue the game rules, make sure to check if it's a tie for win and lose as well as
if it's straight up a tie.
 */

import java.util.Scanner;
import java.util.*;

class GreekHero
{
    private String _name;
    private int _bravery;
    private int _wisdom;
    private int _strength;
    private int _ferocity;
    private int _fearFactor;

    /**
     * Constructor with parameters
     */
    public GreekHero(String name, int bravery, int wisdom,
                     int strength, int ferocity, int fearFactor)
    {
        _name = name;
        _bravery = bravery;
        _wisdom = wisdom;
        _strength = strength;
        _ferocity = ferocity;
        _fearFactor = fearFactor;
    }

    /**
     * Accessor method for name
     *
     * @return _name
     */
    public String getName()
    {
        return _name;
    }

    /**
     * Accessor method for bravery
     *
     * @return _bravery
     */
    public int getBravery()
    {
        return _bravery;
    }

    /**
     * Accessor method for wisdom
     *
     * @return _wisdom
     */
    public int getWisdom()
    {
        return _wisdom;
    }

    /**
     * Accessor method for strength
     *
     * @return _strength
     */
    public int getStrength()
    {
        return _strength;
    }

    /**
     * Accessor method for ferocity
     *
     * @return _ferocity
     */
    public int getFerocity()
    {
        return _ferocity;
    }

    /**
     * Accessor method for fear facto0r
     *
     * @return _fearFactor
     */
    public int getFearFactor()
    {
        return _fearFactor;
    }

    /**
     * toString method
     *
     * @return string representation of a GreekHero
     */
    public String toString()
    {
        return "[" + _name + "]\n" +
                "(a)Bravery:" + _bravery + ", (b)Wisdom:" + _wisdom +
                ", (c)Strength:" + _strength + ", (d)Ferocity:" + _ferocity +
                ", (e)Fear Factor:" + _fearFactor;
    }
} // end class GreekHero()

class GreekHeroArray
{
    private GreekHero[] cards;

    /**
     * Default constructor for the array of GreekHero cards
     */
    public GreekHeroArray()
    {
        cards = new GreekHero[]
                {
                        // bravery, wisdom, strength, ferocity, fear factor

                        new GreekHero("Achilles",   20,  48,  78, 14, 12),
                        new GreekHero("Medusa",      5,  30,  60, 12, 50),
                        new GreekHero("Chimera",     5,  10, 140, 20, 48),

                        new GreekHero("Polyphemus",  8,  18, 140, 15, 47),
                        new GreekHero("Cetus",       5,  14, 150, 16, 45),
                        new GreekHero("Zeus",       10, 100, 200,  8, 40),

                        new GreekHero("Minotaur",   12,  25, 138, 18, 40),
                        new GreekHero("Craeae",      6,  99,  30,  1, 40),
                        new GreekHero("Pegasus",    20,  20, 180,  9, 37),

                        new GreekHero("Siren",       3,  30, 160,  7, 36),
                        new GreekHero("Daphne",     10,  21,  47,  2, 30),
                        new GreekHero("Poseidon",   20,  90, 150, 15, 30),

                        new GreekHero("Theseus",    26,  38,  75, 10, 12),
                        new GreekHero("Odysseus",   30,  56,  65, 10, 11),
                        new GreekHero("Perseus",    26,  40,  70,  9, 10),

                        new GreekHero("Icarus",     18,  65,  50,  2,  9),
                        new GreekHero("Jason",      27,  39,  68,  8,  9),
                        new GreekHero("Oedipus",    26,  74,  63,  1,  7),

                        new GreekHero("Pandora",    10,  50,  32,  1,  5),
                        new GreekHero("Orpheus",    30,  51,  41,  4,  5),
                        new GreekHero("Sisyphus",    9,  80,  40,  3,  5),

                        new GreekHero("Ariadne",    29,  45,  40,  2,  3),
                        new GreekHero("Narcissus",   3,  50,  50,  8,  2),
                        new GreekHero("Aphrodite",   9,  49,  40,  6,  2),

                        new GreekHero("Trojan Horse", 0,  0, 185, 18, 29),
                        new GreekHero("Hermes",     18,  84, 100,  6, 29),
                        new GreekHero("Athene",     19,  85, 110,  5, 25),

                        new GreekHero("Prometheus", 22,  70,  37,  3, 24),
                        new GreekHero("Actaeon",    23,  20,  80,  3, 22),
                        new GreekHero("Heracles",   15,  55, 170, 13, 22)

                };
    }

    /**
     * Accessor method to the array of GreekHero cards
     *
     * @return array of GreekHero cards created
     */
    public GreekHero[] getCards()
    {
        return cards;
    }

} // end class GreekHeroArray()

class Deque
{
    private Object[] _list;
    private int _size;
    private int _front;
    private int _back;
    private static final int ALLOC = 50;

    public Deque()
    {
        _list = new Object[ALLOC];
        _size = 0;
        _front = 0;
        _back = 1;
    }

    public void insertFront(Object given)
    {
        if (_size == _list.length)
        {
            int count = 1;
            Object[] temp = new String[_size*2];
            temp[count] = given;
            ++count;
            for (int ix = _front+1; count <= _size+1; ++ix)
            {
                if(ix == _list.length)
                {
                    _front = 0;
                    ix = 0;
                }
                temp[count] = _list[ix];
                ++count;
            }
            _front = 0;
            _back = _size+2;
            _list = temp;
        }

        else if (_front == 0)
        {
            _list[_front] = given;
            _front = _list.length-1;
        }
        else
        {
            _list[_front] = given;
            --_front;
        }
        ++_size;
    }

    public Object deleteFront()
    {
        Object grab;
        if (isEmpty())
        {
            return null;
        }
        else if (_front == _list.length-1)
        {
            _front = 0;
        }
        else
        {
            ++_front;
        }
        grab = _list[_front];
        --_size;
        return grab;
    }

    public void insertBack(Object given)
    {
        if (_size == _list.length)
        {
            int count = 1;
            Object[] temp = new String[_size*2];

            for (int ix = _front+1; count <= _size; ++ix)
            {
                if(ix == _list.length)
                {
                    _front = 0;
                    ix = 0;
                }
                temp[count] = _list[ix];
                ++count;
            }
            temp[_size+1] = given;
            _front = 0;
            _back = _size+2;
            _list = temp;
        }
        else if (_back == _list.length)
        {
            _back = 0;
            _list[_back] = given;
            ++_back;
        }
        else
        {
            _list[_back] = given;
            ++_back;
        }
        ++_size;
    }

    public Object deleteBack()
    {
        Object grab;
        if(isEmpty())
        {
            return null;
        }
        if(_back == 0)
        {
            _back = _list.length;
            grab = _list[_back-1];
        }
        else
        {
            --_back;
            grab = _list[_back];
        }
        --_size;

        return grab;
    }

    public boolean isEmpty()
    {
        return (_size == 0);
    }

    public String toString()
    {
        int count = 0;
        String display = "\n";

        for (int ix = _front+1; count < _size; ++count)
        {
            if (ix == _list.length)
            {
                ix = 0;
            }
            display = display + _list[ix] + "\n";
            ++ix;
        }
        return display;
    }

    public String toStore()
    {
        String display = "From front to back: \n";
        for (int ix = 0; ix < _list.length; ++ix)
        {
            display = display + _list[ix] + "\n";
        }

        display = display + "\nFront: " + _front;
        display = display + "\nBack: " + _back;
        display = display + "\nSize: " + _size;
        return display;
    }
}



class Queue
{
    private Deque _list;

    public Queue()
    {
        _list = new Deque();
    }

    public void insert(Object given)
    {
        _list.insertBack(given);
    }
    public Object delete()
    {
        return _list.deleteFront();
    }
    public boolean isEmpty()
    {
        return _list.isEmpty();
    }
    public String toString()
    {
        String display = "Front front to back:";
        return display + _list.toString();
    }
}


class Stack
{
    private Deque _list;

    public Stack()
    {
        _list = new Deque();
    }

    public void push(Object given)
    {
        _list.insertFront(given);
    }
    public Object pop()
    {
        return _list.deleteFront();
    }
    public boolean isEmpty()
    {
        return _list.isEmpty();
    }
    public String toString()
    {
        String display = "From top to bottom:";
        return display + _list.toString();
    }
}

class Customers
{
    private String _name;
    private int _ID;

    public Customers(String name, int ID)
    {
        _name = name;
        _ID = ID;
    }

    public String getName()
    {
        return _name;
    }

    public int getID()
    {
        return _ID;
    }

    public String toString()
    {
        String display = "Name: " + _name + " ID: " + _ID;
        return display;
    }
}

public class Program4
{
    public static Scanner kb = new Scanner(System.in);
    public static Random generator = new Random();

    public static void main (String [] args)
    {
        int choice;
        do
        {

            System.out.println("Welcome! Would you like to play Top Trumps,"
                    + " or test the tools of the program?");
            System.out.println("(1). Top Trumps");
            System.out.println("(2). Test the Tools");
            System.out.println("(3). Quit");
            System.out.print("Please enter your choice: ");
            choice = kb.nextInt();
            while (choice != 1 && choice != 2 && choice != 3)
            {
                System.out.print("Please enter 1 for Top Trumps, or 2 for tools test: ");
                choice = kb.nextInt();
            }

            if (choice == 1)
            {
                Queue PlayerDeck = new Queue();
                Queue CPUDeck = new Queue();
                Queue Pile = new Queue();
                GreekHeroArray array = new GreekHeroArray();
                GreekHero[] Cards = array.getCards();
                GreekHero nextplayer, nextcpu;
                boolean tie = false;
                boolean difficulty = false;
                boolean playerturn = true;
                String stop;
                String propertychoice = null;
                char property;
                int playersize = 0;
                int cpusize = 0;
                int difficultychoice, decision;
                int playerproperty = 0;
                int cpuproperty = 0;
                double b_average = 0;
                double w_average = 0;
                double s_average = 0;
                double f_average = 0;
                double ff_average = 0;


                for (int ix = 0; ix < 100; ++ix)
                {
                    GreekHero temp1;
                    int temp2, temp3;
                    temp2 = generator.nextInt(Cards.length);
                    temp3 = generator.nextInt(Cards.length);
                    temp1 = Cards[temp2];
                    Cards[temp2] = Cards[temp3];
                    Cards[temp3] = temp1;
                }

                for (int ix = 0; ix < Cards.length; ++ix)
                {
                    Pile.insert(Cards[ix]);
                }
                for (int ix = 0; !Pile.isEmpty(); ++ix)
                {
                    PlayerDeck.insert(Pile.delete());
                    CPUDeck.insert(Pile.delete());
                    ++playersize;
                    ++cpusize;
                }

                System.out.println("What difficulty would you like? Easy, or Hard?");
                System.out.print("Please enter 1 for easy, or 2 for hard: ");
                difficultychoice = kb.nextInt();
                while (difficultychoice != 1 && difficultychoice != 2)
                {
                    System.out.print("Please enter 1 for easy, or 2 for hard: ");
                    difficultychoice = kb.nextInt();
                }
                difficulty = difficultychoice == 2;

            /*
            using averages to have the cpu determine if the card. i'm sure many of the
            cards have multiple higher than average stats, so i'm going to multiply
            the average by 10%, just to make sure it gets one of the "higher" higher
            than averages. also worth mentioning it will only choose the first card
            it finds that has a higher than average stat. there will be about a
            55% chance of the cpu doing this, otherwise it will choose a card at random.
             */

                if (difficulty)
                {

                    for (int ix = 0; ix < Cards.length - 1; ++ix)
                    {
                        b_average = b_average + Cards[ix].getBravery();
                        w_average = w_average + Cards[ix].getWisdom();
                        s_average = s_average + Cards[ix].getStrength();
                        f_average = f_average + Cards[ix].getFerocity();
                        ff_average = ff_average + Cards[ix].getFearFactor();
                    }
                    b_average = ((int) (b_average / Cards.length) * 1.1);
                    w_average = ((int) (w_average / Cards.length) * 1.1);
                    s_average = ((int) (s_average / Cards.length) * 1.1);
                    f_average = ((int) (f_average / Cards.length) * 1.1);
                    ff_average = ((int) (ff_average / Cards.length) * 1.1);
                }

                System.out.println("Play a card based on which property you think"
                        + " will be greater than your opponent's.");
                System.out.println("If your card is greater, you get both cards back."
                        + "\nIf the opponent's is great, they get both cards.");
                System.out.println("If it is a tie, the pile continues to grow until"
                        + " somebody wins.\nWinner takes all cards from the pile.");
                do
                {
                    nextplayer = (GreekHero) PlayerDeck.delete();
                    nextcpu = (GreekHero) CPUDeck.delete();

                    System.out.println("Player: " + playersize + " cards\n"
                            + "Opponent: " + cpusize + " cards\n");

                    if (playerturn)
                    {

                        System.out.println("Your next card to play:\n");
                        System.out.println(nextplayer);

                        System.out.print("Which property would you like to use for combat?"
                                + "\nBravery (a)\nWisdom (b)\nStrength (c)\nFerocity (d)"
                                + "\nFear Factor, or Terror (e)"
                                + "\nPlease choose property (a/b/c/d/e): ");
                        do
                        {
                            property = kb.next().charAt(0);

                            if (property == 'a')
                            {
                                playerproperty = nextplayer.getBravery();
                                cpuproperty = nextcpu.getBravery();
                                propertychoice = "bravery";
                            } else if (property == 'b')
                            {
                                playerproperty = nextplayer.getWisdom();
                                cpuproperty = nextcpu.getWisdom();
                                propertychoice = "wisdom";
                            } else if (property == 'c')
                            {
                                playerproperty = nextplayer.getStrength();
                                cpuproperty = nextcpu.getStrength();
                                propertychoice = "strength";
                            } else if (property == 'd')
                            {
                                playerproperty = nextplayer.getFerocity();
                                cpuproperty = nextcpu.getFerocity();
                                propertychoice = "ferocity";
                            } else if (property == 'e')
                            {
                                playerproperty = nextplayer.getFearFactor();
                                cpuproperty = nextcpu.getFearFactor();
                                propertychoice = "fear factor";
                            } else
                            {
                                System.out.print("Please enter a valid property (a/b/c/d/e): ");
                            }
                        } while (!(property == 'a' || property == 'b' || property == 'c'
                                || property == 'd' || property == 'e'));
                    } else
                    {
                        if (difficulty)
                        {
                            decision = generator.nextInt(100) + 1;
                            if (decision <= 55)
                            {
                                if (nextcpu.getBravery() >= b_average)
                                {
                                    playerproperty = nextplayer.getBravery();
                                    cpuproperty = nextcpu.getBravery();
                                    propertychoice = "bravery";
                                } else if (nextcpu.getWisdom() >= w_average)
                                {
                                    playerproperty = nextplayer.getWisdom();
                                    cpuproperty = nextcpu.getWisdom();
                                    propertychoice = "wisdom";
                                } else if (nextcpu.getStrength() >= s_average)
                                {
                                    playerproperty = nextplayer.getStrength();
                                    cpuproperty = nextcpu.getStrength();
                                    propertychoice = "strength";
                                } else if (nextcpu.getFerocity() >= f_average)
                                {
                                    playerproperty = nextplayer.getFerocity();
                                    cpuproperty = nextcpu.getFerocity();
                                    propertychoice = "ferocity";
                                } else if (nextcpu.getFearFactor() >= ff_average)
                                {
                                    playerproperty = nextplayer.getFearFactor();
                                    cpuproperty = nextcpu.getFearFactor();
                                    propertychoice = "fear factor";
                                }
                            }
                        } else
                        {
                            decision = generator.nextInt(5);
                            if (decision == 0)
                            {
                                playerproperty = nextplayer.getBravery();
                                cpuproperty = nextcpu.getBravery();
                                propertychoice = "bravery";
                            } else if (decision == 1)
                            {
                                playerproperty = nextplayer.getWisdom();
                                cpuproperty = nextcpu.getWisdom();
                                propertychoice = "wisdom";
                            } else if (decision == 2)
                            {
                                playerproperty = nextplayer.getStrength();
                                cpuproperty = nextcpu.getStrength();
                                propertychoice = "strength";
                            } else if (decision == 3)
                            {
                                playerproperty = nextplayer.getFerocity();
                                cpuproperty = nextcpu.getFerocity();
                                propertychoice = "ferocity";
                            } else
                            {
                                playerproperty = nextplayer.getFearFactor();
                                cpuproperty = nextcpu.getFearFactor();
                                propertychoice = "fear factor";
                            }
                        }
                    }

                    System.out.println("\n\nChosen property: " + propertychoice);
                    System.out.println("\nYour card: " + nextplayer.getName() +
                            "\nYour card's value: " + playerproperty);
                    System.out.println("\nOpponent's card: " + nextcpu.getName() +
                            "\nOpponent's card value: " + cpuproperty);

                    if (playerproperty > cpuproperty)
                    {
                        System.out.println("You won the round!");
                        PlayerDeck.insert(nextplayer);
                        PlayerDeck.insert(nextcpu);
                        ++playersize;

                        if (tie)
                        {
                            do
                            {
                                PlayerDeck.insert(Pile.delete());
                                ++playersize;
                            } while (!Pile.isEmpty());
                            tie = false;
                        }

                        playerturn = true;
                        --cpusize;
                        System.out.print("\nPlease enter any key to continue: ");
                        stop = kb.next();
                    }

                else if (playerproperty < cpuproperty)
                {
                    System.out.println("The opponent won the round!");
                    CPUDeck.insert(nextplayer);
                    CPUDeck.insert(nextcpu);
                    ++cpusize;

                    if (tie)
                    {
                        do
                        {
                            CPUDeck.insert(Pile.delete());
                            ++cpusize;
                        }while (!Pile.isEmpty());
                        tie = false;
                    }

                    --playersize;
                    playerturn = false;
                    System.out.print("\nPlease enter any key to continue: ");
                    stop = kb.next();
                }

                    else
                    {
                        System.out.println("It's a tie!");
                        Pile.insert(nextplayer);
                        --playersize;
                        Pile.insert(nextcpu);
                        --cpusize;
                        tie = true;

                        System.out.print("\nPlease enter any key to continue: ");
                        stop = kb.next();
                    }
                } while (playersize > 0 && cpusize > 0);

                if (playersize == 0)
                {
                    System.out.println("\nYour opponent bested you!");
                    System.out.println("\nBetter luck next time!");
                } else
                {
                    System.out.println("\nYou defeated the opponent!");
                    System.out.println("\nGreat Work!");
                }
            } else
            {
                TestTools();
            }
        } while (choice != 3);
    }

    public static void TestTools()
    {
        Object insertion, deletion;
        int choice;
        Deque Customers = new Deque();
        Queue strings = new Queue();
        Stack integers = new Stack();
        System.out.println("Hello! Welcome to my tool tester!");
        System.out.println("We have created a deque of customers,"
                + "\nA queue of strings,\nAnd a stack of integers.\n");


        do
        {
            choice = mainmenu();

            if (choice == 1)
            {
                do
                {
                    String name;
                    int ID;
                    Customers customer;
                    Object deleted;

                    choice = Customermenu();

                    if (choice == 1)
                    {
                        System.out.println("What is the customer's name?");
                        name = kb.next();
                        System.out.println("What is the customer's ID?");
                        ID = kb.nextInt();

                        customer = new Customers(name, ID);
                        Customers.insertFront(customer);

                        System.out.println("Customer inserted.");
                    }

                    else if (choice == 2)
                    {
                        int display;
                        deleted = Customers.deleteFront();
                        if (deleted instanceof Customers)
                        {
                            display = ((Customers) deleted).getID();
                            System.out.println("Deleted Customer of ID " + display);
                        } else
                        {
                            System.out.println("Was not a customer...");
                        }

                    }

                    else if (choice == 3)
                    {
                        System.out.println("What is the customer's name?");
                        name = kb.next();
                        System.out.println("What is the customer's ID?");
                        ID = kb.nextInt();

                        customer = new Customers(name, ID);
                        Customers.insertBack(customer);

                        System.out.println("Customer inserted.");

                    }

                    else if (choice == 4)
                    {
                        int display;
                        deleted = Customers.deleteBack();
                        if (deleted instanceof Customers)
                        {
                            display = ((Customers) deleted).getID();
                            System.out.println("Deleted Customer of ID " + display);
                        }
                        else
                        {
                            System.out.println("Was not a customer...");
                        }

                    }

                    else if (choice == 5)
                    {
                        System.out.println(Customers.isEmpty());

                    }

                    else if (choice == 6)
                    {
                        System.out.println(Customers);
                    }

                    else if (choice == 7)
                    {
                        System.out.println(Customers.toStore());
                    }

                    else if (choice == 8)
                    {
                        System.out.println("Going back.");
                    }

                    else if (choice == 9)
                    {
                        Customers = new Deque();
                        System.out.println("New deque created.");
                    }
                    else
                    {
                        System.out.print("Please enter a number between 1 and 9:");
                        choice = kb.nextInt();
                    }
                }
                while (choice != 8);
            }

            else if (choice == 2)
            {
                do
                {

                    choice = Queuemenu();

                    if (choice == 1)
                    {
                        System.out.println("\nWhat would you like to insert?");
                        insertion = kb.next();
                        strings.insert(insertion);
                        System.out.println("Object inserted.");
                    }

                    else if (choice == 2)
                    {
                        deletion = strings.delete();
                        System.out.println("Deleted: " + deletion);
                    }

                    else if (choice == 3)
                    {
                        System.out.println(strings.isEmpty());
                    }

                    else if (choice == 4)
                    {
                        System.out.println(strings);
                    }

                    else if (choice == 5)
                    {
                        System.out.println("Going back.");
                    }

                    else if (choice == 6)
                    {
                        strings = new Queue();
                        System.out.println("New queue created.");
                    }

                    else
                    {
                        System.out.println("Please enter a number from 1 to 6.");
                    }
                }
                while (choice != 5);
            }
            else if (choice == 3)
            {
                do
                {

                    choice = Stackmenu();

                    if (choice == 1)
                    {
                        System.out.println("\nWhat would you like to insert?");
                        insertion = kb.nextInt();
                        integers.push(insertion);
                        System.out.println("Object inserted.");
                    }

                    else if (choice == 2)
                    {
                        deletion = integers.pop();
                        System.out.println("Deleted: " + deletion);
                    }

                    else if (choice == 3)
                    {
                        System.out.println(integers.isEmpty());
                    }

                    else if (choice == 4)
                    {
                        System.out.println(integers);
                    }

                    else if (choice == 5)
                    {
                        System.out.println("Going back.");
                    }

                    else if (choice == 6)
                    {
                        integers = new Stack();
                        System.out.println("New stack created.");
                    }
                    else
                    {
                        System.out.println("Please enter a number from 1 to 6.");
                    }
                }
                while (choice != 5);
            }
            else if (choice == 4)
            {
                System.out.println("Thank you for using my program! Goodbye.");
            }
            else
            {
                System.out.print("Please enter a number between 1 and 4:");
                choice = kb.nextInt();
            }

        }while (choice != 4);


    }

    public static int mainmenu()
    {
        int choice;

        System.out.println("\nWhich object would you like to manipulate?");
        System.out.println("(1). Customer Deque");
        System.out.println("(2). String Queue");
        System.out.println("(3). Integer Stack");
        System.out.println("(4). Quit");
        System.out.print("Please enter your choice: ");

        choice = kb.nextInt();

        return choice;
    }

    public static int Customermenu()
    {
        int choice;

        System.out.println("\nWhat would you like to do with this object?");
        System.out.println("(1). Insert a customer on the front of a deque");
        System.out.println("(2). Delete a customer from the front of a deque");
        System.out.println("(3). Insert a customer on the back of a deque");
        System.out.println("(4). Delete a customer from the back of a deque");
        System.out.println("(5). Check if the line is empty");
        System.out.println("(6). See the line of customers");
        System.out.println("(7). See the storage contents of the deque");
        System.out.println("(8). Go back");
        System.out.println("(9). Create an empty deque");
        System.out.print("Please enter your choice: ");

        choice = kb.nextInt();

        return choice;
    }

    public static int Queuemenu()
    {
        int choice;

        System.out.println("\nWhat would you like to do with this object?");
        System.out.println("(1). Insert an object");
        System.out.println("(2). Delete an object");
        System.out.println("(3). Check if the queue is empty");
        System.out.println("(4). See the contents of the queue");
        System.out.println("(5). Go back");
        System.out.println("(6). Create an empty queue");
        System.out.print("Please enter your choice: ");

        choice = kb.nextInt();

        return choice;
    }

    public static int Stackmenu()
    {
        int choice;

        System.out.println("\nWhat would you like to do with this object?");
        System.out.println("(1). Insert an object");
        System.out.println("(2). Delete an object");
        System.out.println("(3). Check if the stack is empty");
        System.out.println("(4). See the contents of the stack");
        System.out.println("(5). Go back");
        System.out.println("(6). Create an empty stack");
        System.out.print("Please enter your choice: ");

        choice = kb.nextInt();

        return choice;
    }
}
