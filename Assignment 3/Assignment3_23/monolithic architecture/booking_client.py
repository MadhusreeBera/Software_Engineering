import requests


SERVER_URL = "http://localhost:8000"


def get_venues():
    response = requests.get(f"{SERVER_URL}/venues")
    if response.status_code == 200:
        return response.json()
    else:
        print(f"Error getting venues: {response.text}")
        return None


def book_tickets(venue_name, num_tickets):
    data = {"venue_name": venue_name, "num_tickets": num_tickets, "payment_info": num_tickets * 500}
    response = requests.post(f"{SERVER_URL}/book-tickets", json=data)
    if response.status_code == 200:
        print((response.json()))
    else:
        print(f"Error booking tickets: {response.text}")


venues = get_venues()
if venues:
    for venue in venues:
        print(f"{venue['name']}: {venue['capacity'] - venue['tickets_sold']} tickets available")
    venue_name = input("Enter venue name: ")
    num_tickets = int(input("Enter number of tickets: "))
    book_tickets(venue_name, num_tickets)
