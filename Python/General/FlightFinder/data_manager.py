import requests

API_KEY = 'Basic cG9vcDpidXR0'

API_BASEURI = 'https://api.sheety.co/3202dfd28def85409ba52dacd1d73904'

API_GET_ENDPOINT = '/flightDealFinder/prices'

API_PUT_ENDPOINT = '/flightDealFinder/prices/'

class DataManager:


    def __init__(self):
        self.headers = {
            'Authorization': API_KEY
        }
        self.uri = API_BASEURI
        self.get = API_GET_ENDPOINT
        self.put = API_PUT_ENDPOINT

    def GetRows(self):
        self.rowList = requests.get(url=self.uri+self.get, headers=self.headers)

    def UpdateSheet(self, sheet, id):
        response = requests.put(url=self.uri+self.put+id, headers=self.headers, json=sheet)
        response.raise_for_status()