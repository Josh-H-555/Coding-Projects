import requests

API_KEY = '0bEsS8o6tD8tg9ijb77HNe8kkInzZvI-'
API_BASEURI = 'https://tequila-api.kiwi.com'
API_LOCATION_ENDPOINT = '/locations/query'
API_SEARCH_ENDPOINT = '/v2/search'


class FlightSearch:

    def __init__(self):
        self.headers = {
            'apikey': API_KEY
        }
        self.codes = []
    def GetIATA(self, city):
        response = requests.get(url=API_BASEURI+API_LOCATION_ENDPOINT,
                                params=city, headers=self.headers)
        response.raise_for_status()

        data = response.json()
        return data

    def SearchFlights(self, data):
        response = requests.get(url=API_BASEURI+API_SEARCH_ENDPOINT, params=data, headers=self.headers)
        response.raise_for_status()

        data = response.json()

        return data