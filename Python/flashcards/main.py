from tkinter import *
import random, pandas, time

#global variables
BACKGROUND_COLOR = "#B1DDC6"
FR = 'French'
EN = 'English'
cards = None
card = None
first_loop = True

#function to run if the user hits the green button to signify they knew the word
#will remove the card from the list, and then add it into an updated cards file
def known_word():
    global cards, card
    cards.remove(card)
    df = pandas.DataFrame.from_records(cards)
    df.to_csv('data/words_to_learn.csv', index=False, encoding='utf-8')

    generate_card()

#simply changes the card, keeping the list the same
def unknown_word():
    generate_card()

#generates a card to view
#first checks if an updated_words file is available to use, otherwise uses default french_words
def generate_card():
    global cards, card, first_loop

    #cancels show_answer in case the button has been hit before the answer has been shown
    window.after_cancel(show_answer)

    if first_loop:
        try:
            cards = pandas.read_csv('data/words_to_learn.csv', encoding='utf-8').to_dict(orient='records')
        except (FileNotFoundError, pandas.errors.EmptyDataError):
            cards = pandas.read_csv('data/french_words.csv', encoding='utf-8').to_dict(orient='records')
        finally:
            first_loop = False


    #chooses a new card, then displays it onto the tkinter canvas
    card = random.choice(cards)

    canvas.itemconfig(canvas_image, image=front_image)
    canvas.itemconfig(title_text, text=FR, fill='black')
    canvas.itemconfig(question_text, text=card[FR], fill='black')

    run_timer()

#after the timer as completed, shows the answer on the tkinter canvas
def show_answer():
    canvas.itemconfig(title_text, text=EN, fill='white')
    canvas.itemconfig(question_text, text=card[EN], fill='white')
    canvas.itemconfig(canvas_image, image=back_image)

#runs the timer to display the answer
def run_timer():
    window.after(3000, show_answer)

#creates window, loads card images
window = Tk()
window.title('Flash Cards')
window.config(padx=50, pady=50, bg=BACKGROUND_COLOR)
front_image = PhotoImage(file='images/card_front.png')
back_image = PhotoImage(file='images/card_back.png')
right_image = PhotoImage(file='images/right.png')
wrong_image = PhotoImage(file='images/wrong.png')

#creates canvas
canvas = Canvas(width=850, height=700, highlightthickness=0, bg=BACKGROUND_COLOR)

#loads image onto canvas, and text onto canvas, displays on grid
canvas_image = canvas.create_image(425, 300, image=front_image)
title_text = canvas.create_text(425, 175, text='', fill='black', font=('Arial', 25, 'italic'))
question_text = canvas.create_text(425, 300, text='', fill='black', font=('Arial', 30, 'bold'))
canvas.grid(row=0, column=0, columnspan=2)

#adds buttons to the grid
wrong_button = Button(image=wrong_image, highlightthickness=0, borderwidth=0, command=unknown_word)
wrong_button.grid(row=1, column=0)

correct_button = Button(image=right_image, highlightthickness=0, borderwidth=0, command=known_word)
correct_button.grid(row=1, column=1)

#generates first card
generate_card()

window.mainloop()