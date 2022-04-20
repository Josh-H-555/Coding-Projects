from selenium import webdriver
from selenium.webdriver.chrome.service import Service
from selenium.webdriver.common.by import By
from selenium.webdriver.common.keys import Keys

cd_path = 'C:/Users/Ronako/Downloads/chromedriver_win32/chromedriver.exe'

service = Service(cd_path)

driver = webdriver.Chrome(service=service)

driver.get("https://secure-retreat-92358.herokuapp.com/")

# driver.get('https://www.amazon.com/Instant-Pot-Plus-60-Programmable/dp/B01NBKTPTS/ref=sr_1_5?crid=18JNXP7BCA5KS&keywords=instapot&qid=1648328332&s=home-garden&sprefix=instapo%2Cgarden%2C143&sr=1-5')

# price = driver.find_element(by=By.CSS_SELECTOR, value="span.a-price.a-text-price, span.a-offscreen")
#
# print(price.text)

# event_times = driver.find_elements(by=By.CSS_SELECTOR, value=".event-widget time")
#
#
# event_names = driver.find_elements(by=By.CSS_SELECTOR, value=".event-widget li a")
#
# events = {}
#
# for n in range(len(event_times)):
#     events[n] = {
#         "time": event_times[n].text,
#         "name": event_names[n].text
#     }
#
# print(events)

#articles_number = driver.find_element(by=By.CSS_SELECTOR, value="#articlecount a")
#articles_number.click()

# all_portals = driver.find_element(by=By.LINK_TEXT, value='Content portals')
# all_portals.click()

# search = driver.find_element(by=By.NAME, value="search")
# search.send_keys("Python")
# search.send_keys(Keys.ENTER)

first_name = driver.find_element(by=By.NAME, value='fName')
first_name.send_keys("Josh")

last_name = driver.find_element(by=By.NAME, value='lName')
last_name.send_keys("Hutchinson")

email = driver.find_element(by=By.NAME, value='email')
email.send_keys("test@test.com")

sign_up = driver.find_element(by=By.CSS_SELECTOR, value='.btn')
sign_up.click()

#driver.quit()