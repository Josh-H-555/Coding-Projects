import requests
from twilio.rest import Client
from datetime import datetime as dt

STOCK = "TSLA"
NEWS_QUERY = '+Tesla OR +TSLA'
STOCK_BASE_URL = 'https://www.alphavantage.co/query'

ARROW_UP = '🔺'
ARROW_DOWN = '🔻'

#alphavantage information

def GetStockPrice():

        stock_api_key = '5S7AUEIKIKZ1TL6I'

        #Parameters to get the daily stock prices for given stock
        stock_params = {
                'function': 'TIME_SERIES_DAILY',
                'symbol': STOCK,
                'apikey': stock_api_key,
        }

        #Gets today's date and yesterday's date.
        today = dt.today().date()
        yesterday = today.replace(day=today.day - 1)

        #Get request to stock API
        stock_response = requests.get(url=STOCK_BASE_URL, params=stock_params)
        stock_response.raise_for_status()

        #Grabs the response in a json format, and immediately grabs the all data under
        #the Time Series (Daily) key, as this is where all stock price info is held.
        stock_data = stock_response.json()["Time Series (Daily)"]

        #Checks yesterday's price, and tries to check today's. If the market hasn't closed,
        #then it will catch the error, and instead check between yesterday and the day before.
        yesterday_price = float(stock_data[str(yesterday)]["4. close"])

        try:
                today_price = float(stock_data[str(today)]["4. close"])
                price_change = round(((today_price - yesterday_price) / today_price) * 100)
        except KeyError:
                print("Today's closing data not yet available. Checking Yesterday and day before.")
                two_days_ago = today.replace(day=today.day - 2)
                two_days_ago_price = float(stock_data[str(two_days_ago)]["4. close"])
                price_change = round(((yesterday_price - two_days_ago_price) /
                                      yesterday_price) * 100)

        return price_change

#news api information

def GetNews():

        news_api_key = 'a5855afcd8d64c5389ac0d6f122c466c'

        #Parameters for news given some filters.
        news_params = {
                'apiKey': news_api_key,
                'q': NEWS_QUERY,
                'searchIn': 'title,description',
        }

        #Get request to news API
        news_response = requests.get(url='https://newsapi.org/v2/everything/', params=news_params)
        news_response.raise_for_status()

        news_data = news_response.json()

        #Grabs the first article, which index 0 of the list-value to the articles key.
        article = news_data["articles"][0]

        return article

#twilio information

#Twilio requires an account that I'm too lazy to create. The function would work, given
#a correct account_sid, auth_token, and phone numbers.
def SendSMS(price_change, article):

        account_sid = 'Placeholder'
        auth_token = 'Placeholder'
        client = Client(account_sid, auth_token)

        if price_change < 0:
                stock_arrow = ARROW_DOWN
        else:
                stock_arrow = ARROW_UP

        message = client.messages.create(
                body=f'{STOCK}: {stock_arrow}{abs(price_change)}% \nHeadline: {article["title"]}\n'
                     f'Brief: {article["description"]}',
                from_='1111111111',
                to='2222222222'
        )

#Prints whether the stock's price increased or decreased, and gives a news article
#to perhaps provide some context as to why it shifted.
def PrintStock(price_change, article):

        if price_change < 0:
                stock_arrow = ARROW_DOWN
        else:
                stock_arrow = ARROW_UP

        print(f'{STOCK}: {stock_arrow}{abs(price_change)}% \nHeadline: {article["title"]}\n'
                     f'Brief: {article["description"]}')

#main
def main():
        price_change = GetStockPrice()
        article = GetNews()
        PrintStock(price_change, article)

main()