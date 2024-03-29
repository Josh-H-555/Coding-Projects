from flask import Flask, render_template, request, redirect, url_for

app = Flask(__name__)

all_books = []


@app.route('/')
def home():
    return render_template('index.html')


@app.route("/add", methods=['GET', 'POST'])
def add():
    if request.method == 'POST':
        temp_dict = {
                        "Book": request.form['book'],
                        "Author": request.form['author'],
                        "Rating": request.form['rating']
                    }
        all_books.append(temp_dict)
        return render_template('index.html', books=all_books)
    else:
        return render_template('add.html', books=all_books)


if __name__ == "__main__":
    app.run(debug=True)

