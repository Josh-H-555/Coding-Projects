from tkinter import *
import math
# ---------------------------- CONSTANTS ------------------------------- #
PINK = "#e2979c"
RED = "#e7305b"
GREEN = "#9bdeac"
YELLOW = "#f7f5dd"
FONT_NAME = "Courier"
WORK_MIN = 25
SHORT_BREAK_MIN = 5
LONG_BREAK_MIN = 20
reps = 0
mark = ''
timer = None

# ---------------------------- TIMER RESET ------------------------------- # 

# ---------------------------- TIMER MECHANISM ------------------------------- # 

# ---------------------------- COUNTDOWN MECHANISM ------------------------------- #

def reset_timer():
    global reps, mark, timer
    reps = 0
    mark = 0
    window.after_cancel(timer)
    canvas.itemconfig(timer_text, text='00:00')
    time_label.config(text='Timer')


def start_timer():
    global reps
    reps += 1

    if reps % 8 == 0:
        count_down(LONG_BREAK_MIN * 60)
        time_label.config(text='Break')
    elif reps % 2 == 0:
        count_down(SHORT_BREAK_MIN * 60)
        time_label.config(text='Break')
    else:
        count_down(WORK_MIN * 60)
        time_label.config(text='Work')

def count_down(count):
    global reps, mark, timer

    count_min = math.floor(count / 60)
    count_sec = count % 60
    if count_sec < 10:
        count_sec = f'0{count_sec}'
    canvas.itemconfig(timer_text, text=f"{count_min}:{count_sec}")
    if count > 0:
        timer = window.after(1000, count_down, count - 1)
    else:
        start_timer()
        if reps % 2 == 0:
            mark += '✔'
        check_label.config(text=mark)


# ---------------------------- UI SETUP ------------------------------- #

window = Tk()
window.title("Pomodoro")
window.config(padx=100, pady=50, bg=YELLOW)


time_label = Label(text='Timer')
time_label.config(bg=YELLOW, fg=GREEN, font=(FONT_NAME, 40, 'bold'))
time_label.grid(row=0, column=1)


canvas = Canvas(width=200, height=224, bg=YELLOW, highlightthickness=0)
tomato_photo = PhotoImage(file='tomato.png')
canvas.create_image(100, 112, image=tomato_photo)
timer_text = canvas.create_text(100, 130, text='00:00', fill='white', font=(FONT_NAME, 30, 'bold'))
canvas.grid(row=1, column=1)

check_label = Label()
check_label.config(bg=YELLOW, fg=GREEN)
check_label.grid(row=3, column=1)

start_button = Button(text='Start', command=start_timer)
start_button.grid(row=2, column=0)

reset_button = Button(text='Reset', command=reset_timer)
reset_button.grid(row=2, column=2)

window.mainloop()