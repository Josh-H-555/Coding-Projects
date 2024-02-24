from bs4 import BeautifulSoup
from spotipy.oauth2 import SpotifyOAuth
import requests, spotipy

BILLBOARD_BASE_URI = 'https://www.billboard.com/charts/hot-100/'
SPOTIFY_CLIENT_ID = 'PLACEHOLDER'
SPOTIFY_CLIENT_SECRET = 'PLACEHOLDER'


def main():

    scope = 'playlist-modify-private'

    sp = spotipy.Spotify(auth_manager=SpotifyOAuth(redirect_uri='http://example.com',
                                                   client_id=SPOTIFY_CLIENT_ID,
                                                   client_secret=SPOTIFY_CLIENT_SECRET,
                                                   scope=scope))


    date = input("Please enter a date you'd like to travel to (YYYY-MM-DD): ")

    split_date = date.split('-')
    print(split_date)

    response = requests.get(url=BILLBOARD_BASE_URI + date)

    soup = BeautifulSoup(response.text, 'html.parser')

    song_list = soup.select(selector='li h3', id='title-of-a-story', class_='c-title')

    songs = [song.getText().strip() for song in song_list]

    user = sp.current_user()

    user_id = user['id']

    song_uris = []

    for song in songs:
        try:
            search_result = sp.search(q=f'song: {song}, year: {split_date[0]}', limit=1, type='track')
            uri = search_result['tracks']['items'][0]['uri']
        except IndexError:
            print("Song not in Spotify")
        else:
            song_uris.append(uri)

    playlist = sp.user_playlist_create(user=user_id,
                            name=f'{date} Billboard 100',
                            public=False,
                            collaborative=False,
                            description='Billboard top 100 songs from the playlist year)')

    sp.playlist_add_items(playlist_id=playlist['id'], items=song_uris)

main()
