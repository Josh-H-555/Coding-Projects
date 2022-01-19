import requests
from datetime import datetime

MY_LAT = 51.5
MY_LONG=-0.12


# response = requests.get(url='http://api.open-notify.org/iss-now.json')
# response.raise_for_status()
#
# data = response.json()
# print(data)

parameters = {
    'lat': MY_LAT,
    'lng': MY_LONG,
    'formatted': 0,
}

response = requests.get('https://api.sunrise-sunset.org/json', params=parameters)
response.raise_for_status()
data = response.json()

sunrise = data["results"]["sunrise"].split('T')[1].split(':')
sunset = data["results"]["sunset"].split('T')[1].split(':')

time_now = datetime.now()