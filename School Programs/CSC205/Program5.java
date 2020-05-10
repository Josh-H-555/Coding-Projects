//Josh Hutchinson, Program 5, creating and evaluating baseball teams
import java.util.Scanner;
import java.util.*;



class Team
{
    private ArrayList<Player> playerlist = new ArrayList<Player>();
    private Manager _manager;
    private int _batters;
    private int _pitchers;

    public Team(Manager given)
    {
        _manager = given;
        _batters = 0;
        _pitchers = 0;
    }

    public Manager getManager()
    {
        return _manager;
    }

    public int getBatters()
    {
        return _batters;
    }

    public int getPitchers()
    {
        return _pitchers;
    }

    public int getPlayers()
    {
        return _batters + _pitchers;
    }

    public void replaceManager(Manager given)
    {
        _manager = given;
    }

    public Player findPlayer(String given)
    {
        Player found;

/*

        for (int i = 0; i < playerlist.size(); ++i)
        {
            if (given.equals(playerlist.get(i).getName()))
            {
                found = playerlist.get(i);
                return found;
            }
        }
  */

        for (Player player : playerlist)
        {
            if(given.equals(player.getName()))
            {
                found = player;
                return found;
            }
        }

        System.out.println("Player " + given + " wasn't found.");
        return null;
    }

    public void addPlayer(Player newplayer)
    {
        if (newplayer instanceof Pitcher)
        {
            playerlist.add(newplayer);
            System.out.println(newplayer.getName() + " has been added to the team.");
            ++_pitchers;
        }
        else if (newplayer instanceof Batter)
        {
            playerlist.add(newplayer);
            System.out.println(newplayer.getName() + " has been added to the team.");
            ++_batters;
        }
    }

    public void deletePlayer(String given)
    {
        boolean found = false;

        for (int i = 0; i < playerlist.size() && !found; ++i)
        {
            if (given.equals(playerlist.get(i).getName()))
            {
                playerlist.remove(i);
                System.out.println("Player deleted.");
                found = true;
            }
        }

        if (!found)
        {
            System.out.println("Player not found. Unable to delete.");
        }
    }

    public double teamBatAve()
    {
        int totalhits = 0;
        int totalbats = 0;


        /*
        for (int i = 0; i < playerlist.size(); ++i)
        {
            totalhits = totalhits + playerlist.get(i).getHits();
            totalbats = totalbats + playerlist.get(i).getBats();
        }

        */

        for (Player player : playerlist)
        {
            totalhits = totalhits + player.getHits();
            totalbats = totalbats + player.getBats();
        }

        if (totalbats != 0)
            return ((double) totalhits/totalbats);
        else
            return 0;
    }

    public int teamHomeRuns()
    {
        int total = 0;

        /*
        for (int i = 0; i < playerlist.size(); ++i)
        {
            total = total + playerlist.get(i).getHomeRuns();
        }
        */

        for (Player player : playerlist)
        {
            total = total + player.getHomeRuns();
        }

        return total;
    }

    public double teamERA()
    {
        int totalearned = 0;
        int totalinns = 0;
/*
        for (int i = 0; i < playerlist.size(); ++i)
        {
            totalearned = totalearned + playerlist.get(i).getEarnedRuns();
            totalinns = totalinns + playerlist.get(i).getInnings();
        }
*/

        for (Player player : playerlist)
        {
            totalearned = totalearned + player.getEarnedRuns();
            totalinns = totalinns + player.getInnings();
        }


        if (totalinns != 0)
            return ((double) totalearned / totalinns);
        else
            return 0;
    }

    public int teamStrikeouts()
    {
        int total = 0;

        /*
        for (int i = 0; i < playerlist.size(); ++i)
        {
            total = total + playerlist.get(i).getStrikeouts();
        }
        */

        for (Player player : playerlist)
        {
            total = total + player.getStrikeouts();
        }

        return total;
    }

    public double teamSalary()
    {
        double totalsalary = _manager.getSalary();

        /*
        for (int i = 0; i < playerlist.size(); ++i)
        {
            totalsalary = totalsalary + playerlist.get(i).getSalary();
        }
        */

        for (Player player : playerlist)
        {
            totalsalary = totalsalary + player.getSalary();
        }

        return totalsalary;
    }

    public double teamValue()
    {
        double totalvalue = 0;

        /*
        for (int i = 0; i < playerlist.size(); ++i)
        {
            totalvalue = totalvalue + playerlist.get(i).getValue();
        }
        */

        for (Player player : playerlist)
        {
            totalvalue = totalvalue + player.getValue();
        }

        if (getPlayers() != 0)
        {
            return (totalvalue / getPlayers());
        }
        else
            return 0;
    }

    public String toString()
    {
        String info = _manager.toString();

        /*
        for (int i = 0; i < playerlist.size(); ++i)
        {
            info = info + playerlist.get(i).toString();
        }
        */

        for (Player player : playerlist)
        {
            info = info + player.toString();
        }

        info = info + "\nPitchers: " + _pitchers + "\nBatters: " + _batters + "\n";

        return info;
    }
}

abstract class Employee
{
    private String _name;
    private double _salary;
    private int _yrsplayed;


    public Employee(String name, double salary, int yrsplayed)
    {
        _name = name;
        _salary = salary;
        _yrsplayed = yrsplayed;
    }
    public String toString()
    {
        String info = "\n\nName: " + _name + "\nSalary: " + _salary +
                "\nYears in Baseball: " + _yrsplayed + "\n";
        return info;
    }
    public String getName()
    {
        return _name;
    }
    public double getSalary()
    {
        return _salary;
    }
    public int getYears()
    {
        return _yrsplayed;
    }
}

class Manager extends Employee
{
    private int _careerwins;
    private int _careergames;


    public Manager(String name, double salary, int years, int games, int wins)
    {
        super(name, salary, years);
        _careergames = games;
        _careerwins = wins;
    }

    public int getGames()
    {
        return _careergames;
    }

    public int getWins()
    {
        return _careerwins;
    }

    public double compWinPercent()
    {
        double percentage;
        if (_careergames != 0)
            percentage = (double) _careerwins/_careergames;
        else
            percentage = 0;

        return percentage;
    }

    public void yearlyUpdate(int wins, int games)
    {
        _careerwins += wins;
        _careergames += games;
    }

    public String toString()
    {
        return super.toString() + "\nCareer Games: " + _careergames +
                "\nCareer Wins: " + _careerwins + "\n";
    }
}

abstract class Player extends Employee
{
    private int _numgames;


    public Player(String name, double salary, int years, int games)
    {
        super(name, salary, years);
        _numgames = games;
    }

    public int getNumgames()
    {
        return _numgames;
    }

    public int getHits()
    {
        return 0;
    }
    public int getBats()
    {
        return 0;
    }
    public int getInnings()
    {
        return 0;
    }
    public int getHomeRuns()
    {
        return 0;
    }
    public int getEarnedRuns()
    {
        return 0;
    }
    public int getStrikeouts()
    {
        return 0;
    }

    public void yearlyUpdate(int games)
    {
        _numgames += games;
    }

    abstract double getValue();

    public String toString()
    {
        return super.toString() + "\nNumber of Games played: " + _numgames + "\n";
    }
}

class Pitcher extends Player
{
    private int _innings;
    private int _earnedruns;
    private int _strikeouts;


    public Pitcher(String name, double salary, int years, int innings, int earned, int strikeouts, int games)
    {
        super(name, salary, years, games);
        _innings = innings;
        _earnedruns = earned;
        _strikeouts = strikeouts;
    }

    public int getInnings()
    {
        return _innings;
    }

    public int getEarnedRuns()
    {
        return _earnedruns;
    }

    public int getStrikeouts()
    {
        return _strikeouts;
    }

    public void yearlyUpdate(int games, int innings, int earned, int strikeouts)
    {
        super.yearlyUpdate(games);
        _innings += innings;
        _earnedruns += earned;
        _strikeouts += strikeouts;
    }

    public double compERA()
    {
        double era;
        if (_innings != 0)
            era = (9*(_earnedruns/_innings));
        else
            era = 0;

        return era;
    }

    public double getValue()
    {
        double value;

        value = (-(compERA()-2))/((3+((_strikeouts-200)))/200.0);
        return value;
    }

    public String toString()
    {
        return super.toString() + "\nInnings: " + _innings + "\nEarned Runs: "
                + _earnedruns + "\nStrikeouts: " + _strikeouts + "\n";
    }
}

class Batter extends Player
{
    private int _atbats;
    private int _hits;
    private int _homeruns;


    public Batter(String name, double salary, int years, int bats, int hits, int hruns, int games)
    {
        super(name, salary, years, games);
        _atbats = bats;
        _hits = hits;
        _homeruns = hruns;
    }

    public int getBats()
    {
        return _atbats;
    }

    public int getHits()
    {
        return _hits;
    }

    public int getHomeRuns()
    {
        return _homeruns;
    }

    public void yearlyUpdate(int games, int bats, int hits, int hruns)
    {
        super.yearlyUpdate(games);
        _atbats += bats;
        _hits += hits;
        _homeruns += hruns;
    }

    public double compBatAve()
    {
        double ave;
        if(_atbats != 0)
            ave = (double) _hits/_atbats;
        else
            ave = 0;

        return ave;
    }


    public double getValue()
    {
        double value;

        value = (compBatAve() - 0.3) / ((0.3 + (_homeruns -40)) / 40.0);
        return value;
    }

    public String toString()
    {
        return super.toString() + "\nBats: " + _atbats + "\nHits: " + _hits
                + "\nHomeruns: " + _homeruns + "\n";
    }
}





public class Program5
{
    public static Scanner kb = new Scanner(System.in);
    public static void main (String [] args)
    {
        char playertype;
        int choice, teamchoice, years, bats, hits, hruns, innings, strikeouts, earnedruns,
         games, wins;
        double salary;
        String name;

        System.out.println("Welcome! We're going to create a baseball team,"
                + " and look at some of their statistics!\n");

        System.out.println("We're going to create a pre-defined team (Team 2)," +
                " along with an empty team (Team 1).");

        Manager manager = new Manager("Jack", 135000.0, 4, 12, 10);
        Team team1 = new Team(manager);
        Team team2 = new Team(manager);

        Pitcher pitcher = new Pitcher( "Tim", 90000.0, 1, 7,
                5, 2, 3);
        team2.addPlayer(pitcher);

        pitcher = new Pitcher("George", 120000.0, 3, 15,
                12, 1 , 9);
        team2.addPlayer(pitcher);

        Batter batter = new Batter( "Alvin", 140000.0, 5, 27,
                12, 1, 13);
        team2.addPlayer(batter);

        batter = new Batter( "Isaac", 150000.0, 7, 45,
                21, 4, 20);
        team2.addPlayer(batter);

        do
        {
            mainmenu();

            System.out.print("\nPlease make your selection: ");
            teamchoice = kb.nextInt();

            if (teamchoice == 1 || teamchoice == 2)
            {
                Team chosenteam;

                if (teamchoice == 1)
                    chosenteam = team1;
                else
                    chosenteam = team2;


                do
                {

                    teamMenu();

                    System.out.print("\nPlease make your selection: ");
                    choice = kb.nextInt();

                    if (choice == 1)
                    {
                        System.out.print("Is the player a pitcher or batter? (p/b): ");
                        playertype = kb.next().charAt(0);
                        while (playertype != 'p' && playertype != 'b')
                        {
                            System.out.print("Please enter p or b for the player: ");
                            playertype = kb.next().charAt(0);
                        }

                        System.out.print("Player Name: ");
                        name = kb.next();
                        System.out.print("Player Salary: ");
                        salary = kb.nextDouble();
                        System.out.print("Number of years in baseball: ");
                        years = kb.nextInt();
                        System.out.print("Number of games: ");
                        games = kb.nextInt();

                        if (playertype == 'p')
                        {
                            System.out.print("Player Innings: ");
                            innings = kb.nextInt();
                            System.out.print("Player Earned Runs: ");
                            earnedruns = kb.nextInt();
                            System.out.print("Player Strikeouts: ");
                            strikeouts = kb.nextInt();

                            Pitcher newplayer = new Pitcher(name, salary, years,
                                    innings, earnedruns, strikeouts, games);
                            chosenteam.addPlayer(newplayer);


                            System.out.println("\nPlayer added successfully.");
                        }

                        else
                        {
                            System.out.print("Player Bats: ");
                            bats = kb.nextInt();
                            System.out.print("Player Hits: ");
                            hits = kb.nextInt();
                            System.out.print("Player Home Runs: ");
                            hruns = kb.nextInt();

                            Batter newplayer = new Batter(name, salary, years,
                                    bats, hits, hruns, games);
                            chosenteam.addPlayer(newplayer);


                            System.out.println("\nPlayer added successfully.");
                        }
                    }

                    else if (choice == 2)
                    {
                        System.out.print("What is the name of the player to delete?: ");
                        name = kb.next();

                        chosenteam.deletePlayer(name);

                    }

                    else if (choice == 3)
                    {

                        System.out.println("Pitchers: " + chosenteam.getPitchers());

                    }

                    else if (choice == 4)
                    {

                        System.out.println("Batters: " + chosenteam.getBatters());

                    }

                    else if (choice == 5)
                    {
                        Player player;
                        System.out.print("What is the player's name?: ");
                        name = kb.next();


                        player = chosenteam.findPlayer(name);
                        if (player != null)
                        {
                            System.out.println(player);
                        }

                    }

                    else if (choice == 6)
                    {
                        System.out.print("Replacement Manager's name: ");
                        name = kb.next();
                        System.out.print("Replacement Manager's salary: ");
                        salary = kb.nextDouble();
                        System.out.print("Number of years in baseball: ");
                        years = kb.nextInt();
                        System.out.print("Replacement Manager's career games: ");
                        games = kb.nextInt();
                        System.out.print("Replacement Manager's career wins: ");
                        wins = kb.nextInt();

                        Manager replacement = new Manager(name, salary, years, games, wins);

                        chosenteam.replaceManager(replacement);

                    }

                    else if (choice == 7)
                    {

                        System.out.println("Team Batting Average: " +
                                chosenteam.teamBatAve());

                    }

                    else if (choice == 8)
                    {

                        System.out.println("Team Home Runs: " +
                                chosenteam.teamHomeRuns());

                    }

                    else if (choice == 9)
                    {
                        System.out.println("Team ERA: " + chosenteam.teamERA());

                    }

                    else if (choice == 10)
                    {
                        System.out.println("Team Strikeouts: " +
                                chosenteam.teamStrikeouts());

                    }

                    else if (choice == 11)
                    {

                        System.out.println("Team Salary: " + chosenteam.teamSalary());

                    }

                    else if (choice == 12)
                    {
                        System.out.println("Team Value: " + chosenteam.teamValue());

                    }

                    else if (choice == 13)
                    {
                        System.out.println(chosenteam);

                    }

                    else if (choice == 14)
                    {
                        System.out.println(chosenteam.getManager());
                    }

                    else if (choice == 15)
                    {
                        System.out.println(chosenteam.getPlayers());
                    }

                    else if (choice == 16)
                    {
                        Manager same = chosenteam.getManager();
                        chosenteam = new Team(same);

                        System.out.println("New team created with same Manager.");
                    }

                    else if (choice == 17)
                    {
                        System.out.print("New Manager name: ");
                        name = kb.next();
                        System.out.print("New Manager Salary: ");
                        salary = kb.nextDouble();
                        System.out.print("New Manager Years in Baseball: ");
                        years = kb.nextInt();
                        System.out.print("New Manager Number of Games: ");
                        games = kb.nextInt();
                        System.out.print("New Manager Number of Wins");
                        wins = kb.nextInt();

                        Manager replace = new Manager(name, salary, years, games, wins);

                        chosenteam = new Team(replace);

                    }

                    else if (choice != 18)
                    {
                        System.out.println("Please enter a valid choice.");
                    }

                } while (choice != 18);
            }
            else if (teamchoice != 3)
            {
                System.out.println("Please enter a valid choice.");
            }

        } while (teamchoice != 3);

    }

    public static void mainmenu()
    {

        System.out.println("\nWhat would you like to do?\n");

        System.out.println("(1). Manipulate Team 1.");
        System.out.println("(2). Manipulate Team 2.");
        System.out.println("(3). Quit.");

    }

    public static void teamMenu()
    {
        System.out.println("\nWhat would you like to do with this team?\n");

        System.out.println("(1). Add a player.");
        System.out.println("(2). Delete a player.");
        System.out.println("(3). See the number of pitchers.");
        System.out.println("(4). See the number of batters.");
        System.out.println("(5). Find a player by name.");
        System.out.println("(6). Replace the manager.");
        System.out.println("(7). Compute the team's batting average.");
        System.out.println("(8). Compute the team's home runs.");
        System.out.println("(9). Compute the team's ERA.");
        System.out.println("(10). Compute the team's strikeouts.");
        System.out.println("(11). Compute the team's salary.");
        System.out.println("(12). Compute the team's value.");
        System.out.println("(13). See the contents of the team.");
        System.out.println("(14). See the manager.");
        System.out.println("(15). See the number of players on the team.");
        System.out.println("(16). Create a new team with the same manager.");
        System.out.println("(17). Create a new team with a new manager.");
        System.out.println("(18). Quit.");

    }
}
