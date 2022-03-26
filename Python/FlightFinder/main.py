from data_manager import DataManager
from flight_data import FlightData
from flight_search import FlightSearch
from notification_manager import NotificationManager

dm = DataManager()
fd = FlightData()
fs = FlightSearch()
nm = NotificationManager()

def main():

    row_list = []

    sheets_data = GetSheetsInfo(row_list)

    GetIATACodes(row_list, sheets_data)

    UpdateSheetsInfo(sheets_data)

    flights = FormatAndSearchForFlights(sheets_data)

    flight_data = flights['data']

    FormatFlightPrices(flight_data)




def GetSheetsInfo(row_list):
    dm.GetRows()
    sheets_data = dm.rowList.json()['prices']
    for row in sheets_data:
        row_list.append(row['city'])
    return sheets_data


def GetIATACodes(row_list, sheets_data):
    i = 0
    for city in row_list:
        query = {
            'location_types': 'city',
            'term': city
        }
        location = fs.GetIATA(city=query)
        sheets_data[i]['iataCode'] = location['locations'][0]['code']
        i += 1

def UpdateSheetsInfo(sheets_data):
    for i in range(len(sheets_data)):
        sheets_put_data = {
            'price': sheets_data[i]
        }
        id = i + 2
        id = str(id)
        dm.UpdateSheet(sheets_put_data, id)
        i +=1

def FormatAndSearchForFlights(sheets_data):
    outgoing_data = fd.FormatOutgoingData(sheets_data)
    incoming_data = fs.SearchFlights(outgoing_data)

    return incoming_data

def FormatFlightPrices(prices):
    fd.FormatIncomingData(prices)

main()