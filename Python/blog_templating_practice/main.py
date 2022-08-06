from flask import Flask, render_template
import requests

BLOG_DATA = 'https://api.npoint.io/c790b4d5cab58020d391'

app = Flask(__name__)

@app.route('/')
def home():
    response = requests.get(url=BLOG_DATA)
    response.raise_for_status()
    blogs = response.json()
    return render_template("index.html", blogs=blogs)

@app.route('/post/<blog_id>')
def post(blog_id):
    response = requests.get(url=BLOG_DATA)
    response.raise_for_status()
    blogs = response.json()

    for item in blogs:
        print(item)
    single_post = [item for item in blogs if item['id'] == int(blog_id)][0]
    print(single_post)

    return render_template('post.html', post=single_post)


if __name__ == "__main__":
    app.run(debug=True)
