MENU = {
    "espresso": {
        "ingredients": {
            "water": 50,
            "coffee": 18,
        },
        "cost": 1.5,
    },
    "latte": {
        "ingredients": {
            "water": 200,
            "milk": 150,
            "coffee": 24,
        },
        "cost": 2.5,
    },
    "cappuccino": {
        "ingredients": {
            "water": 250,
            "milk": 100,
            "coffee": 24,
        },
        "cost": 3.0,
    }
}

resources = {
    "water": 300,
    "milk": 200,
    "coffee": 100,
}

def main():

    print("Welcome to the Coffee Machine!\n")
    off = False
    #Loop to run the coffee machine.
    while not off:

        #Get selection from user. Check for misinput.
        selection = input("What would you like? (espresso/latte/cappuccino): ")
        while selection != 'espresso' and selection != 'latte' and selection != 'cappuccino'\
                and selection != 'off' and selection != 'report':
            selection = input("Please enter a valid input (espresso/latte/cappuccino): ")


        if selection != 'off':
            if selection == 'report':
                print(resources)
                continue_transaction = False
            else:

                continue_transaction = check_resources(selection)
            if continue_transaction:
                cost = MENU[selection]['cost']
                print(f"That will be ${round(MENU[selection]['cost'], 2)}. Please insert coins now.\n")
                quarters = receive_cash("Quarters")
                dimes = receive_cash("Dimes")
                nickels = receive_cash("Nickels")
                pennies = receive_cash("Pennies")

                cash = tally_coins(quarters, dimes, nickels, pennies)
                if cash < cost:
                    print("We're sorry, that's not enough money. You have been refunded.\n")
                elif cash == cost:
                    print(f"Transaction successful! Enjoy your {selection}!")
                elif cash > cost:
                    change = round((cash - cost), 2)
                    print(f"Transaction successful. Your change is ${change}. Enjoy your {selection}!")
                    resources['water'] = resources['water'] - MENU[selection]['ingredients']['water']
                    resources['coffee'] = resources['coffee'] - MENU[selection]['ingredients']['coffee']
                    if selection != 'espresso':
                        resources['milk'] = resources['milk'] - MENU[selection]['ingredients']['milk']


        else:
            off = True
            print("Thank you! Come again!")



#Checks if there is enough resources in the coffee machine to create the selected option.
def check_resources(selection):
    if MENU[selection]['ingredients']['water'] > resources['water']:
        print("Not enough water.")
        return False
    elif MENU[selection]['ingredients']['coffee'] > resources['coffee']:
        print("Not enough coffee.")
        return False
    elif selection != 'espresso':
        if MENU[selection]['ingredients']['milk'] > resources['milk']:
            print("Not enough milk.")
            return False
        else:
            return True
    else:
        return True


def tally_coins(quarters, dimes, nickels, pennies):
    total = (0.25 * quarters) + (0.1 * dimes) + (0.05 * nickels) + (0.01 * pennies)
    return round(total, 2)

def receive_cash(type):
    amount = int(input(f"{type}: "))
    while amount < 0:
        amount = int(input("Please enter a non-negative amount: "))
    return amount

main()