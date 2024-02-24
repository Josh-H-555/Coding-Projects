from bs4 import BeautifulSoup
import requests

def GetMoviesList():
    response = requests.get(
        'https://web.archive.org/web/20200518073855/https://www.empireonline.com/movies/features/best-movies-2/')

    soup = BeautifulSoup(response.text, 'html.parser')

    movies_list = soup.find_all(name='h3', class_='title')

    movies = [movie.getText() for movie in movies_list]

    return movies

def GenerateMovieFile(movies):
    reversed_movies = movies[::-1]
    with open('movie_list.txt', mode='w') as file:
        for movie in reversed_movies:
            file.write(movie + '\n')

def main():
    movies = GetMoviesList()
    GenerateMovieFile(movies)


main()

# response = requests.get('https://news.ycombinator.com/news')
#
# yc_webpage = response.text
#
# soup = BeautifulSoup(yc_webpage, 'html.parser')
#
# articles = soup.find_all(name="a", class_="titlelink")
# upvotes = [int(score.getText().split()[0]) for score in soup.find_all(name='span', class_='score')]
# article_texts = []
# article_links = []
# for tag in articles:
#     article_texts.append(tag.getText())
#     article_links.append(tag.get('href'))
#
# index = upvotes.index(max(upvotes))
#
# print(article_texts[index] + '\n' + article_links[index] + '\n' + str(upvotes[index]))


# with open("website.html", encoding='utf-8') as file:
#     contents = file.read()
#
# soup = BeautifulSoup(contents, 'html.parser')
# print(soup.title)
# print(soup.title.string)
#
# # print(soup.prettify())
#
# # print(soup.p)
#
# anchor_tags = soup.find_all(name='a')
#
# for tag in anchor_tags:
#     print(tag.getText())
#     print(tag.get('href'))
#
# heading = soup.find(name='h1', id='name')
# print(heading)
#
# section_heading = soup.find(name='h3', class_='heading')
#
# print(section_heading.getText())
#
# company_url = soup.select_one(selector='p a')
# print(company_url)
#
# name = soup.select_one('#name')

