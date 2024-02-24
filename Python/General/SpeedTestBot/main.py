from selenium import webdriver
from selenium.webdriver.chrome.service import Service
from selenium.webdriver.common.by import By
import time

cd_path = 'C:/Users/Ronako/Downloads/chromedriver_win32/chromedriver.exe'

service = Service(cd_path)

driver = webdriver.Chrome(service=service)

driver.get('https://speedtest.net')

start = driver.find_element(by=By.CSS_SELECTOR, value='.start-button a')

start.click()

time.sleep(45)

download_speed = driver.find_element(by=By.CSS_SELECTOR, value='.download-speed').text
upload_speed = driver.find_element(by=By.CSS_SELECTOR, value='.upload-speed').text

print(f"{download_speed}, {upload_speed}")
