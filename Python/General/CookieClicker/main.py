import selenium.common.exceptions
from selenium import webdriver
from selenium.webdriver.chrome.service import Service
from selenium.webdriver.common.by import By
import time

cd_path = 'C:/Users/Ronako/Downloads/chromedriver_win32/chromedriver.exe'

service = Service(cd_path)

driver = webdriver.Chrome(service=service)

driver.get('http://orteil.dashnet.org/experiments/cookie/')

cookie = driver.find_element(by=By.ID, value='cookie')

cursor = driver.find_element(by=By.ID, value='buyCursor')

grandma = driver.find_element(by=By.ID, value='buyGrandma')

factory = driver.find_element(by=By.ID, value='buyFactory')

mine = driver.find_element(by=By.ID, value='buyMine')

shipment = driver.find_element(by=By.ID, value='buyShipment')

alchemy = driver.find_element(by=By.ID, value='buyAlchemy lab')

portal = driver.find_element(by=By.ID, value='buyPortal')

time_machine = driver.find_element(by=By.ID, value='buyTime machine')

pledge = driver.find_element(by=By.ID, value='buyElder Pledge')

time_start = time.time()

time_end = 300

while True:

    cookie.click()

    if (round(time.time() - time_start) % 5) == 0:

        money = True

        try:

            cookie.click()

            if pledge.get_attribute('class') != 'grayed':
                pledge.click()
            elif time_machine.get_attribute('class') != 'grayed':
                time_machine.click()
            elif portal.get_attribute('class') != 'grayed':
                portal.click()
            elif alchemy.get_attribute('class') != 'grayed':
                alchemy.click()
            elif shipment.get_attribute('class') != 'grayed':
                shipment.click()
            if mine.get_attribute('class') != 'grayed':
                mine.click()
            if factory.get_attribute('class') != 'grayed':
                factory.click()
            if grandma.get_attribute('class') != 'grayed':
                grandma.click()
            if cursor.get_attribute('class') != 'grayed':
                cursor.click()
            else:
                money = False

        except:

            cookie.click()

            cursor = driver.find_element(by=By.ID, value='buyCursor')

            grandma = driver.find_element(by=By.ID, value='buyGrandma')

            factory = driver.find_element(by=By.ID, value='buyFactory')

            mine = driver.find_element(by=By.ID, value='buyMine')

            shipment = driver.find_element(by=By.ID, value='buyShipment')

            alchemy = driver.find_element(by=By.ID, value='buyAlchemy lab')

            portal = driver.find_element(by=By.ID, value='buyPortal')

            time_machine = driver.find_element(by=By.ID, value='buyTime machine')

            pledge = driver.find_element(by=By.ID, value='buyElder Pledge')

            time.sleep(0.15)
