from selenium import webdriver
from selenium.webdriver.chrome.service import Service
from selenium.webdriver.common.by import By
from bs4 import BeautifulSoup
import requests, time

CD_PATH = 'C:/Users/Ronako/Downloads/chromedriver_win32/chromedriver.exe'
GOOGLE_FORM = 'https://forms.gle/6c6PLnRjZbEkqoYp7'
ZILLOW_LINK = 'https://tinyurl.com/mr22y89e'

SERVICE = Service(CD_PATH)


def scrape_site():

    req_headers = {
        'accept': 'text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8',
        'accept-encoding': 'gzip, deflate, br',
        'accept-language': 'en-US,en;q=0.8',
        'upgrade-insecure-requests': '1',
        'user-agent': 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/61.0.3163.100 Safari/537.36'
    }


    response = requests.get(ZILLOW_LINK, headers=req_headers)
    response.raise_for_status()
    soup = BeautifulSoup(response.text, 'html.parser')

    listings = soup.select('ul.photo-cards article.list-card')

    links = []
    prices = []
    addresses = []

    for listing in listings:

        link = listing.select_one('a.list-card-link', href=True)
        price = listing.select_one('div.list-card-price')
        address = listing.select_one('address.list-card-addr')
        try:
            href = link.get('href')
        except AttributeError:
            pass
        else:
            if 'zillow' not in href:
                href = 'https://www.zillow.com' + href
            links.append(href)
        try:
            price_text = price.text
        except AttributeError:
            pass
        else:
            prices.append(price_text)
        try:
            address_text = address.text
        except AttributeError:
            pass
        else:
            addresses.append(address_text)

    add_to_google_sheet(links, prices, addresses)


def add_to_google_sheet(links, prices, addresses):
    driver = webdriver.Chrome(service=SERVICE)

    driver.get(GOOGLE_FORM)

    for i in range(len(links)):
        address = driver.find_element(by=By.XPATH, value='//*[@id="mG61Hd"]/div[2]/div/div[2]/div[1]/div/div/'
                                                         'div[2]/div/div[1]/div/div[1]/input')

        address.click()
        address.send_keys(addresses[i])

        price = driver.find_element(by=By.XPATH, value='//*[@id="mG61Hd"]/div[2]/div/div[2]/div[2]/div/'
                                                       'div/div[2]/div/div[1]/div/div[1]/input')

        price.click()
        price.send_keys(prices[i])

        link = driver.find_element(by=By.XPATH, value='//*[@id="mG61Hd"]/div[2]/div/div[2]/div[3]'
                                                      '/div/div/div[2]/div/div[1]/div/div[1]/input')
        link.click()
        link.send_keys(links[i])

        submit = driver.find_element(by=By.XPATH, value='//*[@id="mG61Hd"]/div[2]/div/div[3]/'
                                                        'div[1]/div[1]/div/span/span')

        submit.click()
        time.sleep(1)

        another_response = driver.find_element(by=By.XPATH, value='/html/body/div[1]/div[2]/'
                                                                  'div[1]/div/div[4]/a')

        another_response.click()
        time.sleep(1)

    driver.quit()


def main():
    scrape_site()



main()
