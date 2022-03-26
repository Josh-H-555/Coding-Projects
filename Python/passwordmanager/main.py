from tkinter import *
from tkinter import messagebox
import random
import pyperclip
import json

DEFAULTEMAIL = 'poop@poopmail.com'

# ---------------------------- PASSWORD GENERATOR ------------------------------- #

def generate_password():

    letters = ['a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z']
    numbers = ['0', '1', '2', '3', '4', '5', '6', '7', '8', '9']
    symbols = ['!', '#', '$', '%', '&', '(', ')', '*', '+']

    nr_letters = random.randint(6, 8)
    nr_symbols = random.randint(3, 4)
    nr_numbers = random.randint(3, 4)

    password_letters = [random.choice(letters) for _ in range(nr_letters)]
    password_symbols = [random.choice(symbols) for _ in range(nr_symbols)]
    password_numbers = [random.choice(numbers) for _ in range(nr_numbers)]

    password_list = password_letters + password_symbols + password_numbers

    random.shuffle(password_list)

    password = ''.join(password_list)

    pass_entry.insert(0, password)
    pyperclip.copy(password)


# ---------------------------- SAVE PASSWORD ------------------------------- #

def search_website():
    website = web_entry.get()
    try:
        with open('pmdata.json', 'r') as file:
            data = json.load(file)
    except FileNotFoundError:
        pass
    else:
        for key in data:
            if key == website:
                web_entry.delete(0, 'end')
                web_entry.insert(0, key)
                email_entry.delete(0, 'end')
                email_entry.insert(0, data[key]['email'])
                pass_entry.insert(0, data[key]['password'])

def save_data():

    website = web_entry.get()
    email = email_entry.get()
    password = pass_entry.get()
    new_data = {website:
                    {
                        "email": email,
                        "password": password,
                    }}

    if len(website) == 0 or len(password) == 0:
        messagebox.showerror(title='Empty Fields', message='Please enter values for all fields before saving.')


    else:
        try:
            with open(file='pmdata.json', mode='r') as file:
                data = json.load(file)

        except FileNotFoundError:
            with open(file='pmdata.json', mode='w') as file:
                file.close()
                data = new_data
        finally:
            with open(file='pmdata.json', mode='w') as file:
                data.update(new_data)
                json.dump(data, file, indent=4)

        web_entry.delete(0, 'end')
        email_entry.delete(0, 'end')
        email_entry.insert(0, DEFAULTEMAIL)
        pass_entry.delete(0, 'end')

# ---------------------------- UI SETUP ------------------------------- #

window = Tk()
window.title('Password Manager')
window.config(padx=50, pady=50)
logo_image = PhotoImage(file='logo.png')

canvas = Canvas(width=200, height=200, highlightthickness=0)

logo = canvas.create_image(100, 100, image=logo_image)
canvas.grid(row=0, column=1)

web_label = Label(text='Website:')
web_label.grid(row=1, column=0)

web_entry = Entry(width=35)
web_entry.grid(row=1, column=1, columnspan=2)
web_entry.focus()

pass_button = Button(text='Search', command=search_website)
pass_button.grid(row=1, column=2)

email_label = Label(text='Email/Username:')
email_label.grid(row=2, column=0)

email_entry = Entry(width=35)
email_entry.grid(row=2, column=1, columnspan=2)
email_entry.insert(0, DEFAULTEMAIL)

pass_label = Label(text='Password:')
pass_label.grid(row=3, column=0)

pass_entry = Entry(width=17)
pass_entry.grid(row=3, column=1)


pass_button = Button(text='Generate Password', command=generate_password)
pass_button.grid(row=3, column=2)

add_button = Button(text='Add', width=43, command=save_data)
add_button.grid(row=4, column=1, columnspan=2)


window.mainloop()