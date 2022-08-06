from flask import Flask
from flask import render_template
import random, datetime, requests

app = Flask(__name__)

@app.route('/')
def index():
    return render_template('index.html')

@app.route('/guess/<name>')
def guess(name):
    parameters = {"name": name}
    response = requests.get('http://api.agify.io', params=parameters)
    information = response.json()
    user_name = information['name']
    user_age = information['age']
    return render_template('index.html', name=user_name, age=user_age)

@app.route('/blog/<num>')
def blog(num):
    blog_url = 'https://api.npoint.io/c790b4d5cab58020d391'
    response = requests.get(blog_url)
    all_posts = response.json()
    return render_template('blog.html', posts=all_posts)

if __name__ == '__main__':
    app.run(debug=True)