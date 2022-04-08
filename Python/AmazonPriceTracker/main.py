import requests, lxml, smtplib
from bs4 import BeautifulSoup

MY_PRICE = 100.00

gmail_user = 'PLACEHOLDER'
gmail_password = 'PLACEHOLDER'

AMAZON_INSTAPOT_URL = 'https://www.amazon.com/Instant-Pot-Plus-60-Programmable/' \
                      'dp/B01NBKTPTS/ref=sr_1_5?crid=18JNXP7BCA5KS&keywords=' \
                      'instapot&qid=1648328332&s=home-garden&sprefix=instapo%2Cgarden%2C143&sr=1-5'

headers = {
    'Accept-Language': 'en-US,en;q=0.9',
    'User-Agent': 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 '
                  '(KHTML, like Gecko) Chrome/99.0.4844.83 Safari/537.36'
}

def main():

    response = requests.get(url=AMAZON_INSTAPOT_URL, headers=headers)

    soup = BeautifulSoup(response.text, 'lxml')

    price = soup.select(selector='span.a-price span.a-offscreen')

    price_value = float(price[0].getText().split('$')[1])
    print(price_value)

    if price_value < MY_PRICE:
        with smtplib.SMTP('smtp.gmail.com', 587) as connection:
            connection.starttls()
            connection.login(user=gmail_user, password=gmail_password)
            connection.sendmail(from_addr=gmail_user,
                                to_addrs='josh.hutchinson555@gmail.com',
                                msg=f'Subject: Your item is on sale!\n\n'
                                    f'The item you have been tracking is on sale for {price_value}'
                                    f'Here\'s the link! {AMAZON_INSTAPOT_URL}')



main()
