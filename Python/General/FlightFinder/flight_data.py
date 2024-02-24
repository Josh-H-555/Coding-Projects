import datetime

MY_IATA = 'PHX'
MY_CURRENCY = 'USD'

class FlightData:

    def __init__(self):
        self.formattedOutgoingData = {}
        self.formattedIncomingData = {}


    def FormatOutgoingData(self, sheets_data):
        time_now = datetime.datetime.now().strftime('%d/%m/%Y')
        time_six_months = (datetime.datetime.now() + datetime.timedelta(days=(6*30))).strftime('%d/%m/%Y')

        self.formattedOutgoingData['fly_from'] = f'airport:{MY_IATA}'

        iataString = f'{sheets_data[0]["iataCode"]}'
        for i in range(1, len(sheets_data)):
            iataString += f',{sheets_data[i]["iataCode"]}'

        self.formattedOutgoingData['fly_to'] = iataString
        self.formattedOutgoingData['date_from'] = time_now
        self.formattedOutgoingData['date_to'] = time_six_months
        self.formattedOutgoingData['curr'] = MY_CURRENCY
        self.formattedOutgoingData['one_for_city'] = 1

        return self.formattedOutgoingData

    def FormatIncomingData(self, data):
        for item in data:
            print(f"{item['cityTo']}: ${item['price']}")