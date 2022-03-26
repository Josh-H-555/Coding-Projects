import turtle
import pandas
import create_turtle

states = pandas.read_csv('50_states.csv')

screen = turtle.Screen()
screen.title("U.S. States Game")

image = 'blank_states_img.gif'

screen.addshape(image)

turtle.shape(image)

correct = 0

while correct < 50:
    answer_state = screen.textinput(title="Guess the state", prompt="What's another state's name?")
    answer_state = answer_state.title()
    for state in states.state:
        if answer_state == state:
            xcor = states[states['state'] == answer_state].x.item()
            ycor = states[states['state'] == answer_state].y.item()
            create_turtle.NewState(answer_state, xcor, ycor)
    correct += 1


turtle.mainloop()