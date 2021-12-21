import colorgram
import turtle
import random

turtle.colormode(255)
timmy = turtle.Turtle()
screen = turtle.Screen()
screenSize = screen.screensize()
timmy_x = timmy.xcor()
timmy_y = timmy.ycor()
timmy.penup()

color_list = []

colors = colorgram.extract('painting.jpg', 10)

for i in colors:
    color_tuple = (i.rgb.r, i.rgb.g, i.rgb.b)
    color_list.append(color_tuple)
print(color_list)

for i in range(10):
    for ix in range(10):
        timmy.dot(20, random.choice(color_list))
        timmy.forward(50)
    timmy.setx(timmy_x)
    timmy.sety(timmy_y - 50)
    timmy_y -= 50


screen.exitonclick()