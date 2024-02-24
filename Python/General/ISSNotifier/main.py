import requests
import smtplib
from datetime import datetime

MY_LAT = 51.507351 # Your latitude
MY_LONG = -0.127758 # Your longitude
MY_EMAIL = 'testEmail'
MY_PASS = 'testPass'

def check_iss():
    response = requests.get(url="http://api.open-notify.org/iss-now.json")
    response.raise_for_status()
    data = response.json()

    iss_latitude = float(data["iss_position"]["latitude"])
    iss_longitude = float(data["iss_position"]["longitude"])

    #Your position is within +5 or -5 degrees of the ISS position.


    parameters = {
        "lat": MY_LAT,
        "lng": MY_LONG,
        "formatted": 0,
    }

    response = requests.get("https://api.sunrise-sunset.org/json", params=parameters)
    response.raise_for_status()
    data = response.json()
    sunrise = int(data["results"]["sunrise"].split("T")[1].split(":")[0])
    sunset = int(data["results"]["sunset"].split("T")[1].split(":")[0])

    time_now = datetime.now()

    if (abs(iss_latitude - MY_LAT) <= 5 and abs(iss_longitude - MY_LONG) <= 5 and (time_now.hour < sunrise or time_now.hour > sunset)):
        return true




if check_iss():
    connection = smtplib.SMTP('smtp.gmail.com')
    connection.starttls()
    connection.login(MY_EMAIL, MY_PASS)
    connection.sendmail(from_addr=MY_EMAIL,
                        to_addrs=MY_EMAIL,
                        MSG='Subject:Look Up\n\nThe ISS is above you!')