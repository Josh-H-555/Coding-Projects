# from flask import Flask
#
# app = Flask(__name__)
#
# def make_bold(function):
#     def wrapper():
#         return "<b>" + function() + "</b>"
#     return wrapper
#
# def make_emphasis(function):
#     def wrapper():
#         return "<em>" + function() + "</em>"
#     return wrapper
#
# def make_underlined(function):
#     def wrapper():
#         return "<u>" + function() + "</em>"
#     return wrapper
#
# @app.route('/') #python decorator
# @make_bold
# @make_emphasis
# @make_underlined
# def hello_world():
#     return 'Hello World!'
#
# @app.route('/<name>/<int:number>')
# def greet(name, number):
#     return f"Hello {name}, hello {number}!"
#
# if __name__ == "__main__":
#     app.run(debug=True)


from flask import Flask
import random

#creates the Flask object
app = Flask(__name__)

random_int = random.randint(0, 9)


#home page
@app.route('/')
def index():
    return 'Please guess a number between 0-9! Use the URL to guess a number.'

@app.route('/<int:guess>')
def guess_page(guess):
    if int(guess) < random_int:
        return 'Too low!'
    elif int(guess) > random_int:
        return 'Too high!'
    else:
        return 'Good job! You got it!'


if __name__ == '__main__':
    app.run()