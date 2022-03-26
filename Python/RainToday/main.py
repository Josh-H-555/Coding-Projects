import requests
from twilio.rest import Client

api_key="2598d02385eff447c56ec9663e8e5de3"

base_uri = "https://api.openweathermap.org/data/2.5/onecall"

account_sid = 'placeholder'
auth_token = '0123456789'

latitude = 33.415
longitude = -111.831

parameters = {
    "lat": latitude,
    "lon": longitude,
    "appid": api_key,
    "exclude": "minutely,daily,current",

}

response = requests.get(base_uri, params=parameters)
response.raise_for_status()

data = response.json()
print(data)

will_rain = False

weather_list = data['hourly'][:12]
for hour in weather_list:
    if hour['weather'][0]['id'] < 700:
        will_rain = True

if will_rain:
    twilio_client = Client(account_sid, auth_token)
    message = twilio_client.messages \
        .create(
        body='Bring an umbrella!',
        from_='4444444444',
        to='3333333333'
        )