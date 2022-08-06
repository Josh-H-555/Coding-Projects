from flask import Flask
from flask import render_template

app = Flask(__name__)

#static files such as JS, CSS, and images will go inside the static folder.

@app.route('/')
def index():
    return render_template('index.html')

if __name__ == '__main__':
    app.run()