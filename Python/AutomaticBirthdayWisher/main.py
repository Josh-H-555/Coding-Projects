import smtplib, pandas, random
import datetime as dt

email = 'TestEmail'
password = 'TestPassword'

today = (dt.datetime.now().month, dt.datetime.now().day)

data = pandas.read_csv('birthdays.csv')

birthday_dict = {(data_row.month, data_row.day): data_row for (index, data_row) in data.iterrows()}

if today in birthday_dict:
    person = birthday_dict[today]
    random_letter_number = random.choice(1, 3)
    with open(f'letter_templates/letter_{random_letter_number}.txt', 'r') as file:
        letter = file.read()
        personalized_letter = letter.replace('[NAME]', person["name"])

    with smtplib.SMTP('smtp.gmail.com') as connection:
        connection.starttls()
        connection.login(email, password)
        connection.sendmail(from_addr=email,
                            to_addrs=person['email'],
                            msg=f'Subject: Happy Birthday!\n\n{personalized_letter}')