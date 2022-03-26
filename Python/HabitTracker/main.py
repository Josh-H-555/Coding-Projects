import requests
from datetime import datetime as dt

pixela_endpoint = 'https://pixe.la/v1/users'

USERNAME = 'ronako'
TOKEN = 'greanigoehraigvne'
ID = 'firstgraph'

pixela_params = {
    'agreeTermsOfService': 'yes',
    'notMinor': 'yes'
}

# response = requests.post(url=pixela_endpoint, json=pixela_params)
# print(response.text)

today = dt.now().date()
formatted_today = today.strftime('%Y%m%d')

graph_params = {
    'date': formatted_today,
    'quantity': '25',
}

graph_endpoint = f'{pixela_endpoint}/{USERNAME}/graphs/{ID}'

headers = {
    'X-USER-TOKEN': TOKEN
}

response = requests.post(url=graph_endpoint, json=graph_params, headers=headers)
print(response.text)
