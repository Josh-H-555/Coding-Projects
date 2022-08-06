from selenium import webdriver
from selenium.webdriver.chrome.service import Service
from selenium.webdriver.common.by import By
from selenium.webdriver.common.keys import Keys
import time

CD_PATH = 'C:/Users/Ronako/Downloads/chromedriver_win32/chromedriver.exe'

service = Service(CD_PATH)

USER = 'Placeholder'
PASS = 'Placeholder'

SIMILAR_ACCOUNT = 'Placeholder'


class InstaFollower:

    def __init__(self):
        self.driver = webdriver.Chrome(service=service)

    def login(self, username, password):
        input_user = self.driver.find_element(by=By.NAME, value='username')
        input_user.send_keys(username)

        input_password = self.driver.find_element(by=By.NAME, value='password')
        input_password.send_keys(password)

        login = self.driver.find_element(by=By.XPATH, value='//*[@id="loginForm"]/div/div[3]/button')
        login.click()

    def find_followers(self, account):
        search_account = self.driver.find_element(by=By.XPATH,
                                                  value='//*[@id="react-root"]/section/nav/div[2]/div/div/div[2]/input')
        search_account.send_keys(account)
        search_account.send_keys(Keys.ENTER)

    def follow(self):
        find_followers = self.driver.find_element(by=By.XPATH,
                                                  value='//*[@id="react-root"]/section'
                                                        '/main/div/header/section/ul/li[2]/a')
        find_followers.click()

        for i in range(0, 10):
            scroll_followers = self.driver.find_element(by=By.XPATH,
                                                        value='/html/body/div[6]/div/div/div/div[2]')
            self.driver.execute_script("arguments[0].scrollTop = arguments[0].scrollHeight", scroll_followers)
            time.sleep(0.25)

        follower_list = self.driver.find_elements(by=By.XPATH, value='/html/body/div[6]/div/div/div/div[2]/ul/div/li')
        for item in follower_list:
            try:
                item.find_element(by=By.CSS_SELECTOR, value='button').click()
            except:
                self.driver.find_element(by=By.XPATH,
                                         value='/html/body/div[7]/div/div/div/div[3]/button[2]').click()
            finally:
                time.sleep(1)

def main():
    instagram = InstaFollower()

    instagram.login(USER, PASS)

    instagram.find_followers(SIMILAR_ACCOUNT)

    instagram.follow()


main()
