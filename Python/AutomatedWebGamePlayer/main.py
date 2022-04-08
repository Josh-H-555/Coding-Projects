from selenium import webdriver
from selenium.webdriver.chrome.service import Service
from selenium.webdriver.common.by import By

cd_path = 'C:/Users/Ronako/Downloads/chromedriver_win32/chromedriver.exe'

service = Service(cd_path)

driver = webdriver.Chrome(service=service)

driver.get('https://www.amazon.com/Instant-Pot-Plus-60-Programmable/dp/B01NBKTPTS/ref=sr_1_5?crid=18JNXP7BCA5KS&keywords=instapot&qid=1648328332&s=home-garden&sprefix=instapo%2Cgarden%2C143&sr=1-5')

# price = driver.find_element(by=By.CSS_SELECTOR, value="span.a-price.a-text-price, span.a-offscreen")
#
# print(price.text)

driver.get("https://www.python.org")

mylink = driver.find_element(by=By.XPATH, value='//*[@id="site-map"]/div[2]/div/ul/li[3]/a')
print(mylink.)

driver.quit()