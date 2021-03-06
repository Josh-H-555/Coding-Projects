//Josh Hutchinson, program 5

#include "pch.h"
#include <stdlib.h>
#include <time.h>
#include <stdio.h>
#include <math.h>
#include <iostream>
using namespace std;

void menu();
void basicmenu();
void calcmenu();
void warriormenu();
void doubleinput(double &x);
void intinput(int &x);
void charinput(char &x);
void randomtarget(double &x, double &y);
void artillery();
void target();
void huntwump();
void randomxy(double &x, double &y);
void wumpmovement(double &x, double &y);
void warmovement(double &x, double &y);


void ask_dist();
double compute_dist(double x1, double y1, double x2, double y2);
void ask_horizangle();
double compute_horizangle(double x1, double y1, double x2, double y2);
void ask_traveldist();
double compute_traveldist(double elevangle, double velocity);
void ask_destination();
void compute_destination(double x1, double y1, double distance,
	double horizangle, double &endx, double &endy);
void clearscreen();


int main()
{
	int choice;

	cout << "Hello! Welcome to my program!" << endl
		<< "Your first option will allow you to do some basic calculations,"
		<< endl
		<< " the second option will allow you to make a table of where"
		<< " an artillery shell will land," << endl
		<< " the third option is some target practice on a floating target,"
		<< endl
		<< " and the last option will pit you against a wumpus, with your"
		<< " only weapon being a grenade launcher!" << endl;

	srand (time(NULL));

	do
	{
		menu();
		cout << "Please choose your selection: ";
		cin >> choice;
		if (choice == 1)
			basicmenu();
		else if (choice == 2)
			artillery();
		else if (choice == 3)
			target();
		else if (choice == 4)
			huntwump();
		else if (choice != 5)
			cout << "Please enter a number from 1 to 5." << endl << endl;
	} while (choice != 5);

	cout << endl << "Thank you! Goodbye.";

	return 0;
}





void menu()
{
	cout << endl << "(1). Basic Calculations" << endl;
	cout << "(2). Artillery Table" << endl;
	cout << "(3). Target Practice" << endl;
	cout << "(4). Hunt The Wumpus" << endl;
	cout << "(5). Quit" << endl << endl;
}
void basicmenu()
{
	int choice;

	cout << "Hello! Welcome to this fancy calculator!" << endl 
		<< "We can calculate a distance between two points," << endl
		<< " calculate the horizontal angle between two points," << endl
		<< " calculate the distance an object will travel," << endl
		<< " and calculate where an object will land."<< endl;

	do
	{
		calcmenu();
		cout << "Please choose your selection: ";
		cin >> choice;
		
		if (choice == 1)
			ask_dist();
		else if (choice == 2)
			ask_horizangle();
		else if (choice == 3)
			ask_traveldist();
		else if (choice == 4)
			ask_destination();
		else if (choice != 5)
			cout << "Please enter a number for 1 to 5." << endl << endl;
	} while (choice != 5);

	cout << endl << endl << "Thank you! Goodbye." << endl << endl;
}
void calcmenu()
{
	cout << endl << "(1). Compute distance between two points"
		<< " in feet" << endl;
	cout << "(2). Compute the horizontal angle from one point to another"
		<< endl;
	cout << "(3). Compute horizontal distance travelled of an object" 
		<< " in feet" << endl;
	cout << "(4). Compute destination point of a travelling object" << endl;
	cout << "(5). Quit" << endl << endl;

}
void doubleinput(double &x)
{
	cin >> x;
}
void intinput(int &x)
{
	cin >> x;
}
void charinput(char &x)
{
	cin >> x;
}
void randomtarget(double &x, double &y)
{
	x = rand() % 5000;
	y = rand() % 5000;
}





void artillery()
{
	double vertangle, velocity, distance, variation;
	int rows, i;
	char choice;


	cout << "Let's shoot some artillery!!!!" << endl;
	cout << "We'll be making a table of distances that a shot travels."
		<< endl << endl;


	cout << "Please enter the vertical angle that the object"
		<< endl << "will be shot at in degrees: ";
	doubleinput(vertangle);
	while (vertangle <= 0 || vertangle >= 90)
	{
		cout << "Please enter a number between 0 and 90 for the angle: ";
		doubleinput(vertangle);
	}


	cout << endl << "Please enter the velocity the object will travel"
		<< " in miles per hour: ";
	doubleinput(velocity);
	while (velocity <= 0)
	{
		cout << "Please enter a positive number for the velocity in mph: ";
		doubleinput(velocity);
	}


	cout << endl << "How many rows would you like in the table?: ";
	intinput(rows);

	cout << "Do you want to vary the vertical angle, or the velocity?"
		<< endl << "Please enter a for angle, and v for velocity. (a/v): ";
	do
	{
		charinput(choice);
		if (choice == 'a')
		{
			cout << endl << "How much do you want to vary "
				"the angle per row?" << endl;
			doubleinput(variation);
			printf("\nVelocity(mph)=%4.2f\n\nAngle\tDistance\n"
				"(degs)\t(feet)\n", velocity);
			for (i = 0; i < rows; ++i)
			{
				distance = compute_traveldist(vertangle, velocity);
				printf("%3.2f\t%3.2f\n", vertangle, distance);
				vertangle = vertangle + variation;
			}
		}
		else if (choice == 'v')
		{
			cout << "How much do you want to vary the velocity per row?" 
				<< endl;
			doubleinput(variation);
			printf("\nAngle(degs)=%4.2f\n\nVelocity\tDistance\n"
				"(mph)\t(feet)\n", vertangle);
			for (i = 0; i < rows; ++i)
			{
				distance = compute_traveldist(vertangle, velocity);
				printf("%3.2f\t%3.2f\n", velocity, distance);
				velocity = velocity + variation;
			}
		}
		else
			cout << "Please enter a for the angle, or v for the velocity: ";
	} while (!(choice == 'a' || choice == 'v'));
	
	cout << endl << endl << "Fun!!" << endl << endl;
}





void target()
{
	const double playerx = 2500;
	const double playery = 0;
	double horizangle, vertangle, velocity, distance, randx, randy;
	double cheatang, cheatdist, hitx, hity, finaldist;
	int target, hitcount, shotcount;
	char difficulty, again, cheat;
	cout << "Let's try some target practice with a cannon." << endl;
	cout << "Your cannon will start at the coordinates (2500, 0), "
		<< "while the floating target's coordinates will be random each time."
		<< endl;
	cout << "First, choose a difficulty level:" << endl
		<< "easy: Target radius = 100 feet" << endl
		<< "Medium: Target radius = 25 feet" << endl
		<< "HARD: Target radius = 5 feet" << endl
		<< "Please choose a difficulty now, entering e for easy, "
		<< "m for medium, and h for hard (e/m/h): ";
	do
	{
		charinput(difficulty);
		if (difficulty == 'e')
			target = 100;
		else if (difficulty == 'm')
			target = 25;
		else if (difficulty == 'h')
			target = 5;
		else
			cout << "Please enter e for easy, m for medium, and h for hard: ";
	} while (!(difficulty == 'e' || difficulty == 'm' || difficulty == 'h'));


	cout << "Would you like to cheat? By choosing to cheat, you will be given"
		<< endl
		<< "the distance and horizontal angle from the cannon to the target."
		<< endl
		<< "Cheat? (y/n): ";
	charinput(cheat);
	while (cheat != 'y' && cheat != 'n')
	{
		cout << "Please enter y for yes, or n for no: ";
		charinput(cheat);
	}
	hitcount = 0;
	shotcount = 0;
	do
	{
		randomtarget(randx, randy);
		if (cheat == 'y')
		{
			cheatang = compute_horizangle(playerx, playery, randx, randy);
			cheatdist = compute_dist(playerx, playery, randx, randy);
			cout << endl << endl
				<< "The distance between you and the target is: "
				<< cheatdist << " feet." << endl
				<< "The horizontal angle between you and the target is: "
				<< cheatang << " degrees." << endl
				<< "Good luck!!" << endl << endl;
		}
		cout << endl;
		cout << "The target is at " << randx << ", " << randy << endl;
		cout << "First, enter the direction of the cannon's barrel in degrees."
			<< endl << " It must be from 0 to 180 degrees."
			<< endl << "Please enter the horizontal angle now: ";
		doubleinput(horizangle);
		while (!(horizangle > 0 && horizangle < 180))
		{
			cout << "Please enter a number from 0 to 180 for the angle: ";
			doubleinput(horizangle);
		}


		cout << endl << endl;
		cout << "Now enter the vertical angle you will set the cannon's barrel"
			<< " to, in degrees." << endl
			<< "This must be between 0 and 90, but not equal to 0 or 90."
			<< endl
			<< "Please enter the vertical angle in degrees now: ";
		doubleinput(vertangle);
		while (!(vertangle >= 0 && vertangle <= 90))
		{
			cout << "Please enter a number between 0 and 90 for the angle: ";
			doubleinput(vertangle);
		}

		cout << endl << endl;
		cout << "Now, enter the velocity, in miles per hour, that you want the"
			<< " cannonball to be shot out from the cannon." << endl;
		cout << "Please enter the velocity in miles per hour now: ";
		doubleinput(velocity);
		while (velocity <= 0)
		{
			cout << "Please enter a positive number for the velocity in mph: ";
			doubleinput(velocity);
		}
		distance = compute_traveldist(vertangle, velocity);
		compute_destination(playerx, playery, distance, horizangle,
			hitx, hity);

		finaldist = compute_dist(hitx, hity, randx, randy);

		if (finaldist <= difficulty)
		{
			cout << endl << endl << "BOOM! You hit the target!" << endl;
			++hitcount;
		}
		
		else
		{
			cout << "You missed! Your shot was off by: " << finaldist
				<< " feet.";
			cout << endl << endl << "Better luck next time!" << endl << endl;
		}

		++shotcount;

		cout << endl << endl << "Would you like to shoot another? (y/n): ";
		charinput(again);
		while (!(again == 'y' || again == 'n'))
		{
			cout << "Please enter y for yes, or n for no (y/n): ";
			charinput(again);
		}
	} while (again == 'y');
	cout << endl;
	cout << "You hit the target " << hitcount << " times!" << endl;
	cout << "You shot " << shotcount << " shots." << endl << endl;

}





void huntwump()
{
	char goforward;
	double pitx, pity, wumpusx, wumpusy, warriorx, warriory,
		batterycumpx, batterycumpy;
	int cumpcharge = 3;
	int endgame = 0;
	int turns, totaldist, shotcount, choice, firstshot;
	double distfromwump, distfrompit, distfrombattery, anglefromwump;
	double wumpdistpit, horizangle, elevangle, velocity, distance, grenadehitx,
		grenadehity, wumphit;


	firstshot = 0;
	turns = 0;
	totaldist = 0;
	shotcount = 0;
	randomxy(pitx, pity);
	randomxy(wumpusx, wumpusy);
	randomxy(warriorx, warriory);
	randomxy(batterycumpx, batterycumpy);


	cout << endl << endl << endl
		<< "You wake up and find yourself in a cold and dimly lit enclosure."
		<< endl
		<< "As your eyes adjust to the darkness, you notice the room "
		<< "is a perfect square."
		<< endl
		<< "There's one door in the middle of each wall."
		<< endl
		<< "You can't see past the darkness of the doorway."
		<< endl
		<< "You look up and notice there's no ceiling, and the sky is "
		<< "glittering with thousands of stars."
		<< endl
		<< "As you pick yourself up from the ground, your foot nudges "
		<< "something that was lying next to you."
		<< endl
		<< "You look down to see a grenade launcher, a note, and a compass."
		<< endl
		<< "The note reads: "
		<< endl << endl
		<< "This grenade launcher is special, and has unlimited ammo."
		<< endl
		<< "There's a wumpus in this maze with you. It's stronger than you."
		<< endl
		<< "Don't find yourself in the same room, or you will lose the fight."
		<< endl
		<< "To help, I've provided a compass that will tell you the direction"
		<< " that the wumpus is in."
		<< endl
		<< "Be careful, you can only use it 3 times."
		<< endl
		<< "There might be an extra battery somewhere..."
		<< endl
		<< "There is also a bottomless pit in this maze, and you can't fly."
		<< endl
		<< "You'll notice there's no ceiling, try arcing a grenade over"
		<< " the walls in the direction you think the wumpus is in."
		<< endl
		<< "You'll need to hit it with the blast if you want to survive."
		<< endl
		<< "Don't fall in the pit. Don't engage the wumpus directly."
		<< endl
		<< "This is your trial. Hunt the wumpus."
		<< endl << endl
		<< "You stand up, grabbing the grenade launcher and compass."
		<< endl
		<< "You hear a faint roar, and the sound of some ground crumbling."
		<< endl
		<< "Frightened, but with a steeled resolve, you will hunt the wumpus."
		<< endl << endl
		<< "Type any character to continue... ";
	cin >> goforward;
	clearscreen();

	do
	{
		distfromwump = compute_dist(warriorx, warriory, wumpusx, wumpusy);
		distfrompit = compute_dist(warriorx, warriory, pitx, pity);
		distfrombattery = compute_dist(warriorx, warriory,
			batterycumpx, batterycumpy);
		wumpdistpit = compute_dist(wumpusx, wumpusy, pitx, pity);

		printf("I am at %2.0f, %2.0f\n\n", warriorx, warriory);


		if (distfromwump == 10)
		{
			printf("I smell something funny...\n\n");
		}
		else if (distfromwump == 0)
		{
			printf("The wumpus is in the same room as you!\n"
				"you attempt to shoot it, but it knocks the grenade"
				" launcher out of your hand.\n"
				"After a brief struggle, the wumpus overpowers you.\n"
				"You have failed.\n\n");
			endgame = 1;
		}


		if (distfrompit == 10)
		{
			printf("I feel a draft.\n\n");
		}
		if (wumpdistpit == 0 && distfrompit == 0)
		{
			printf("As you step through the pitch black doorway,"
				" you notice your foot found no hold. \n"
				"Your heart skips as inevitable dread sets in. \n"
				"A beastly scream rouses your mind, you can make out"
				" the wumpus not far away from you. \n"
				"This is your fate, forever falling with the wumpus. \n"
				"This is a draw. \n\n");
			endgame = 1;
		}
		else if (wumpdistpit == 0)
		{
			printf("You hear a beastly scream come from another room. \n"
				"It echoes and seems to grow further away at a steady"
				" pace. The wumpus fell into the pit. \n"
				"By luck, you have survived. \n"
				"You have successfully hunted the wumpus. \n\n");
			endgame = 2;
		}
		else if (distfrompit == 0)
		{
			printf("As you step through the pitch black doorway,"
				" you notice that your foot found no hold.\n"
				"Your heart skips as inevitable dread sets in.\n"
				"It's already too late, you're falling into the pit.\n"
				"You have failed.\n\n");
			endgame = 1;
		}


		if (distfrombattery == 0)
		{
			printf("While moving closer to the center of the next room,"
				" your wumpus compass starts to wobble.\n"
				"As you reach the center, the compass flings itself off of"
				" your belt, and collides with a battery pack.\n"
				"The wumpus compass now has three additional charges.\n");
			cumpcharge = cumpcharge + 3;
			batterycumpx = 2500;
			batterycumpy = 2500;
		}

		if (endgame == 0)
		{
			do
			{
				warriormenu();
				cout << "Your decision: ";
				cin >> choice;

				wumpmovement(wumpusx, wumpusy);

				if (choice == 1)
				{
					warmovement(warriorx, warriory);
					totaldist = totaldist + 10;
				}
				else if (choice == 2)
				{
					++shotcount;
					cout << "What is the horizontal angle (in degrees)"
						<< " that you want"
						<< " to shoot the grenade at?" << endl << endl;
					if (firstshot == 0)
					{
						cout << "0 is East" << endl
							<< "90 is North" << endl
							<< "180 is West" << endl
							<< "270 is South" << endl
							<< "All numbers between,"
							<< " and including 0 and 360 are valid angles";
					}
					cout << endl << "Please enter the horizontal angle: ";
					cin >> horizangle;
					while (horizangle < 0 || horizangle > 360)
					{
						cout << "Please enter a number between 0 and 360"
							<< " for the horizontal angle: ";
						cin >> horizangle;
					}
					cout << endl;
					cout << "What is the vertical angle (in degrees)"
						<< " you would like"
						<< " to shoot the grenade at?";
					if (firstshot == 0)
					{
						cout << "The vertical angle can be between 0 and 90"
							<< " degrees, but not 0 or 90.";
					}
					cout << endl << "Please enter the vertical angle: ";
					cin >> elevangle;
					while (elevangle <= 0 || elevangle >= 90)
					{
						cout << "Please enter a number between 0 and 90,"
							<< " but not 0 or 90 for the vertical angle: ";
						cin >> elevangle;
					}
					cout << "What do you want set the velocity (mph)"
						<< " of the grenade "
						<< "to be shot at?" << endl;
					if (firstshot == 0)
					{
						cout << "The velocity must be a positive number." 
							<< endl;
					}
					cout << endl << "Please enter the velocity: ";
					cin >> velocity;
					while (velocity < 0)
					{
						cout << "Please enter a positive number for"
							<< " velocity: ";
						cin >> velocity;
					}
					firstshot = 1;
					distance = compute_traveldist(elevangle, velocity);
					compute_destination(warriorx, warriory, distance,
						horizangle, grenadehitx, grenadehity);
					wumphit = compute_dist(grenadehitx, grenadehity,
						wumpusx, wumpusy);
					if (wumphit < 5)
					{
						printf("A ghastly scream of pain overpowers the "
							"sound of the grenade's explosion. \n"
							"You hit the wumpus with the grenade. \n"
							"You successfully hunted the wumpus. \n\n");
						endgame = 2;
					}
					else if (wumphit <= 15)
					{
						cout << "I hear a wumpus ROAR!" << endl;
					}
					else
					{
						cout << "I hear a wumpus laugh." << endl;
					}
				}
				else if (choice == 3)
				{
					if (cumpcharge > 0)
					{
						anglefromwump = compute_horizangle(warriorx, warriory,
							wumpusx, wumpusy);
						cout << "You open the compass, the arrow"
							<< " spins furiously"
							<< " for a few moments before halting abruptly."
							<< endl;
						cout << "The wumpus is at a horizontal angle of "
							<< anglefromwump
							<< " from your location." << endl
							<< "Your compass has also lost a charge."
							<< endl << endl;
						cumpcharge = cumpcharge - 1;
					}
					else
					{
						cout << "You open the compass, but the arrow"
							<< " drifts slowly in random directions." << endl
							<< "The compass is dead." << endl;
					}
				}
				else if (choice == 4)
				{
					cout << "You decide to take more time before making"
						<< " your next move." << endl << endl;
				}
				else if (choice == 5)
				{
					endgame = 1;
				}
				else
					cout << "Please enter a number from 1 through 5: ";
			} while (!(choice == 1 || choice == 2 || choice == 3 || choice == 4
				|| choice == 5));

			++turns;
		}
		if (endgame == 1 || endgame == 2)
		{
			cout << "You played the game for " << turns << " turns."
				<< endl
				<< "You shot " << shotcount << " grenades." << endl
				<< "You travelled " << totaldist << " feet in total."
				<< endl;
		}

	} while (endgame == 0);


}

void randomxy(double &x, double &y)
{
	x = rand() % 10;
	y = rand() % 10;
	x = x * 10 + 5;
	y = y * 10 + 5;
}

void wumpmovement(double &x, double &y)
{
	int decision;
	decision = rand() % 4 + 1;
	if (decision == 1)
		y = y + 10;
	else if (decision == 2)
		y = y - 10;
	else if (decision == 3)
		x = x + 10;
	else if (decision == 4)
		x = x - 10;

	if (x < 0)
		x = x + 10;
	else if (x > 100)
		x = x - 10;
	else if (y < 0)
		y = y + 10;
	else if (y > 100)
		y = y - 10;
}

void warmovement(double &x, double &y)
{
	char direction;
	cout << "Move North, South, East, or West? (n/s/e/w): ";
	cin >> direction;
	while (!(direction == 'n' || direction == 's' || direction == 'e'
		|| direction == 'w'))
	{
		cout << "You must enter n for North, s for South, e for East,"
			<< " or w for West: ";
		cin >> direction;
	}

	cout << endl << endl;

	if (direction == 'n')
		y = y + 10;
	else if (direction == 's')
		y = y - 10;
	else if (direction == 'e')
		x = x + 10;
	else
		x = x - 10;

	if (x < 0)
	{
		x = x + 10;
		cout << "You hit your head on the western wall of the maze as you"
			<< " try to walk through it. your position is unchanged."
			<< endl << endl;
	}
	else if (x > 100)
	{
		x = x - 10;
		cout << "You hit your head on the eastern wall of the maze as you"
			<< " try to walk through it. your position is unchanged."
			<< endl << endl;
	}
	else if (y < 0)
	{
		y = y + 10;
		cout << "You hit your head on the southern wall of the maze as you"
			<< " try to walk through it. your position is unchanged."
			<< endl << endl;
	}
	else if (y > 100)
	{
		y = y - 10;
		cout << "You hit your head on the northern wall of the maze as you"
			<< " try to walk through it. your position is unchanged."
			<< endl << endl;
	}
}

void warriormenu()
{
	cout << "What do you want to do?" << endl << endl;
	cout << "(1) Move to an adjacent room" << endl
		<< "(2) Fire a grenade" << endl
		<< "(3) Use the Wumpus Compass" << endl
		<< "(4) Do nothing" << endl
		<< "(5) Quit game" << endl << endl;
}












void ask_dist()
{
	double distance, x1, y1, x2, y2;
	cout << "Please enter the first x-coordinate in feet: ";
	doubleinput(x1);
	cout << "Please enter the first y-coordinate in feet: ";
	doubleinput(y1);
	cout << "Please enter the second x-coordinate in feet: ";
	doubleinput(x2);
	cout << "Please enter the second y-coordinate in feet: ";
	doubleinput(y2);
	distance = compute_dist(x1, y1, x2, y2);
	cout << "The distance in feet is: " << distance << endl << endl << endl;
}
double compute_dist(double x1, double y1, double x2, double y2)
{
	double distance;
	distance = sqrt((pow((x2 - x1), 2)) + pow((y2 - y1), 2));
	return distance;
}




void ask_horizangle()
{
	double x1, y1, x2, y2, degangle;
	cout << "Please enter the first x-coordinate in feet: ";
	doubleinput(x1);
	cout << "Please enter the first y-coordinate in feet: ";
	doubleinput(y1);
	cout << endl << "Please enter the second x-coordinate in feet: ";
	doubleinput(x2);
	cout << "Please enter the second y-coordinate in feet: ";
	doubleinput(y2);
	degangle = compute_horizangle(x1, y1, x2, y2);
	cout << "The horizontal angle between the points is: " << degangle
		<< " degrees." << endl << endl << endl;
}
double compute_horizangle(double x1, double y1, double x2, double y2)
{
	double distx, disty, radangle, degangle;
	const double pi = 3.14159;
	distx = x2 - x1;
	disty = y2 - y1;
	if (distx > 0)
		radangle = atan(disty / distx);
	else if (distx < 0)
		radangle = atan(disty / distx) + pi;
	else if (distx == 0 && disty >= 0)
		radangle = pi / 2.0;
	else if (distx == 0 && disty < 0)
		radangle = (pi * -1) / 2.0;
	degangle = radangle * 180.0 / pi;

	return degangle;
}





void ask_traveldist()
{
	double elevangle, velocity, traveldist;
	cout << "Please enter the elevation angle, or vertical angle in degrees: ";
	doubleinput(elevangle);
	while (elevangle <= 0 || elevangle >= 90)
	{
		cout << "Please enter a number between 0 and 90 for the elevation"
			<< " angle: ";
		doubleinput(elevangle);
	}
	cout << endl << "Please enter the object's velocity in miles per hour: ";
	doubleinput(velocity);
	while (velocity <= 0)
	{
		cout << "Please enter a positive number for the velocity in mph: ";
		doubleinput(velocity);
	}
	traveldist = compute_traveldist(elevangle, velocity);
	cout << "The object would travel " << traveldist << " feet."
		<< endl << endl << endl;
}
double compute_traveldist(double elevangle, double velocity)
{
	const double pi = 3.14159;
	const double gravity = 32.172;
	const float feetpmile = 5280.0;
	const float secperhr = 3600.0;
	double radangle, velocityfps, distance;

	radangle = elevangle * pi / 180.0;
	velocityfps = velocity * feetpmile / secperhr;
	distance = pow(velocityfps, 2) * sin(2 * radangle) / gravity;

	return distance;
}





void ask_destination()
{
	double endx, endy, x1, y1, horizangle, distance;
	cout << "Please enter the x-coordinate of the starting point in feet: ";
	doubleinput(x1);
	cout << "Please enter the y-coordinate of the starting point in feet: ";
	doubleinput(y1);
	cout << "Please enter the distance the object will travel in feet: ";
	doubleinput(distance);
	while (distance <= 0)
	{
		cout << "Please enter a positive number for the distance "
			<< "that the object will travel in feet: ";
		doubleinput(distance);
	}
	cout << endl << "Please enter the horizontal angle that the object"
		<< "will be shot in degrees. " << endl
		<< "0 being East, or right, " << endl 
		<< "90 being North, or up, " << endl
		<< "180 being West, or left, " << endl
		<< "and 270 being South, or down." << endl
		<< "All numbers between 0 and 360 are valid for different angles."
		<< endl
		<< "Please enter the horizontal angle now: ";
	doubleinput(horizangle);
	while (horizangle < 0 || horizangle > 360)
	{
		cout << "Please enter a number beginning from 0 to 360 for the "
			<< "horizontal angle: ";
		doubleinput(horizangle);
	}
	compute_destination(x1, y1, distance, horizangle, endx, endy);
	cout << "The destination point is: " << endx << ", " << endy 
		<< endl << endl << endl;
}
void compute_destination(double x1, double y1, double distance,
	double horizangle, double &endx, double &endy)
{
	double radangle, distx, disty;
	const double pi = 3.14159;

	radangle = horizangle * pi / 180.0;
	distx = distance * cos(radangle);
	disty = distance * sin(radangle);
	endx = x1 + distx;
	endy = y1 + disty;
}

void clearscreen()
{
	int i;
	for (i = 0; i < 20; ++i)
	{
		cout << endl << endl;
	}
}