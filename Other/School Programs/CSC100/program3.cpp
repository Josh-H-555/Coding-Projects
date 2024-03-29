// Josh Hutchinson, Guido's Just Pizza

#include <iostream>
using namespace std;

int main()
{
	const float tax = 0.07;
	const float cheesecrust = 0.03f;
	const float saucencheese = 0.039f;
	const float toppingprice = 0.028f;
	const float doughprice = 0.022f;
	const double pi = 3.14159;
	double diameter, length, width, area, pizzasize, multipizza;
	double  basecost, onepizza, totalpizza, volume;
	float dough;
	int topamount, numpizzas;
	char yesno, doughtype, roundorrec;


	// introduce, find out if rectangular or round pizza

	// all dimensions measured in inches, square inches, and cubic inches

	cout << "Welcome to Guido's Just Pizza!" << endl << endl;
	cout << "What kind of pizza would you like, round or rectangular? "
		<< endl << "o = round, a = rectangular (o/a): ";
	cin >> roundorrec;

	// calculate area depending on type of pizza

	if (roundorrec == 'o')
	{
		cout << endl << "How large would you like your round pizza?"
			<< endl << "Please enter the diameter in inches: ";
		cin >> diameter;
		area = pi * pow((diameter / 2), 2);
	}
	else
	{
		cout << endl << "How large would you like your rectangular pizza?"
			<< endl << "Please enter the length in inches: ";
		cin >> length;
		cout << "Please enter the width in inches: ";
		cin >> width;
		area = length * width;
	}


	cout << endl << endl
		<< "Okay, your pizza will have an area of: " << area
		<< " square inches." << endl;

	//determine toppings, then calculate depending if they want any/how many

	cout << endl << endl << "Would you like toppings? (y/n): ";
	cin >> yesno;


	if (yesno == 'y')
	{
		cout << "How many toppings would you like? ";
		cin >> topamount;
		cout << endl << endl << "Okay, you want " << topamount << " toppings."
			<< endl;
	}
	else
		topamount = 0;

	

	//determine dough type, then set thickness depending on input

	cout << endl << endl << "What kind of dough would you like? "
		<< "We have classic hand-tossed, thin & crispy, pan, and texas toast."
		<< endl << "Please specify which dough you would like." << endl
		<< "classic hand-tossed = h" << endl
		<< "thin & crispy = c" << endl
		<< "pan = p" << endl
		<< "texas toast = t" << endl
		<< "(h/c/p/t): ";
	cin >> doughtype;

	

	if (doughtype == 'h')
	{
		dough = 1.0;
		cout << endl << endl << "Okay, you want our hand-tossed pizza.";
	}
	else if (doughtype == 'c')
	{
		dough = 0.5;
		cout << endl << endl << "Okay, you want our thin & crispy pizza.";
	}
	else if (doughtype == 'p')
	{
		dough = 1.8;
		cout << endl << endl << "Okay, you want our pan pizza.";
	}
	else
	{
		dough = 2.5;
		cout << endl << endl << "Okay, you want our texas toast pizza.";
	}

	//calculate volume and base cost of the pizza
	
	volume = area * dough;

	basecost = area * (saucencheese + (topamount * toppingprice))
		+ (doughprice * volume);


	cout << endl << endl << "The volume of your pizza will be " << volume
		<< " cubic inches.";
	

	//ask for cheesy crust, unless they have thin n crispy

	if (doughtype != 'c')
	{
		cout << endl << endl
			<< "Would you like cheesy crust? (y/n): ";
		cin >> yesno;
		if (yesno == 'y')
		{
			if (roundorrec == 'o')
				pizzasize = 2 * pi * (diameter / 2);
			else
				pizzasize = 2 * (length + width);

			onepizza = basecost + (pizzasize * cheesecrust);
		}
		else
			onepizza = basecost;
	}
	else
		onepizza = basecost;


	cout << endl << endl << "The cost of one pizza before tax is $"
		<< onepizza << endl;

	//ask if they want multiple pizzas

	cout << endl << endl
		<< "Would you like more than one of these? (y/n): ";
	cin >> yesno;

	if (yesno == 'y')
	{
		cout << "How many more would you like? ";
		cin >> numpizzas;

		multipizza = onepizza * numpizzas;
	}
	else
		multipizza = onepizza;

	// calculate tax

	totalpizza = multipizza * (1 + tax);

	cout << endl << "After tax, your total is now: $" << totalpizza
		<< endl;


	// determine if they want delivery

	cout << endl << "Would you like your order to be delivered? (y/n): ";
	cin >> yesno;
	cout << endl << endl << endl;

	if (yesno == 'y')
	{
		if (totalpizza < 10)
		{
			cout << "A $2.50 delivery fee will be added to your order.";
			totalpizza = totalpizza + 2.50;
		}
		else if (totalpizza < 20)
		{
			cout << "A $2.00 delivery fee will be added to your order.";
			totalpizza = totalpizza + 2.00;
		}
		else if (totalpizza < 30)
		{
			cout << "A $1.00 delivery fee will be added to your oder.";
			totalpizza = totalpizza + 1.00;
		}
		else
		{
			cout << "Because your order is over $30 "
				<< "you have free delivery!";
		}
	}


	// display all necessary calculations


	cout << endl << endl << "Okay! Let's review your order!" << endl << endl;
	cout << "The area of your pizza is: " << area << " square inches." << endl;
	cout << "The volume of your pizza is: " << volume << " cubic inches."
		<< endl;
	cout << "The base cost of your order is: $" << basecost << "." << endl;
	cout << "The cost of one pizza is: $" << onepizza << "." << endl;
	cout << "And finally, your total cost including tax and delivery charge "
		<< "(if applicable) is: $" << totalpizza << endl << endl;
	cout << "Thank you for choosing Guido's Just Pizza! Have a wonderful day!"
		<< endl << endl;

	return 0;
}