import turtle

class NewState(turtle.Turtle):
    
    def __init__(self, given_shape, xcor, ycor):
        super().__init__()
        self.penup()
        self.color("black")
        self.speed("fastest")
        self.hideturtle()
        self.goto(xcor, ycor)
        self.write(given_shape, False, "center", ("Arial", 10, "bold"))
        
    