from tkinter import *

def calculate_conversion():
    km = float(miles_input.get()) * 1.609
    km_value = Label(text=round(km))
    km_value.grid(row=1, column=1)



window = Tk()
window.minsize(150, 100)


window.title('Mile to KM Converter')

miles_input = Entry(width=10)
miles_input.grid(row=0, column=1)

miles_label = Label(text='Miles')
miles_label.grid(row=0, column=2)

equal_label = Label(text='is equal to')
equal_label.grid(row=1, column=0)

km_label = Label(text='Km')
km_label.grid(row=1, column=2)


calculate_button = Button(text='Calculate', command=calculate_conversion)
calculate_button.grid(row=2, column=1)

window.mainloop()