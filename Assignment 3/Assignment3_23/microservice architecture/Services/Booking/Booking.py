from fastapi import FastAPI, HTTPException
import requests
import os
from dotenv import load_dotenv
import os
from pydantic import BaseModel
import datetime

# Load environment variables from .env file
load_dotenv()


app = FastAPI()
SERVICE_REGISTRY_URL = os.getenv("SERVICE_REGISTRY_URL")
VENUE_AVAILABILITY_SERVICE_NAME = os.getenv("VENUE_AVAILABILITY_SERVICE_NAME")
PAYMENT_SERVICE_NAME = os.getenv("PAYMENT_SERVICE_NAME")

booking_id = 0
class BookingData(BaseModel):
    amount: int
    venue_id: int
    payee_name: str
    payee_account: str

@app.post('/book')
def book(booking_data: BookingData):
    # Fetch venue availability
    #print("Fetching venue availability")
    response = requests.get(f"{SERVICE_REGISTRY_URL}/find/{VENUE_AVAILABILITY_SERVICE_NAME}")
    if response.status_code != 200:
        raise HTTPException(status_code=500, detail={ "message": "Venue Availability Service not found"})
    venue_availability_url = response.json()['url']
    
    response = requests.get(f"{venue_availability_url}/venue_availability/{booking_data.venue_id}")
    if(response.status_code != 200):
        raise HTTPException(status_code=404, detail={ "message": "Venue not found"})
    
    venue_availability = response.json()
    #print(venue_availability)
    
    if not venue_availability['available']:
        return {"message": "Venue not available"}
    
    #print("Venue available")

    # Make payment
    response = requests.get(f"{SERVICE_REGISTRY_URL}/find/{PAYMENT_SERVICE_NAME}")
    if response.status_code != 200:
        raise HTTPException(status_code=500, detail={ "message": "Payment System Service not found"})
    payment_system_url = response.json()['url']
    
    payment_data = {"amount": booking_data.amount, "payee_name": booking_data.payee_name, "payee_account": booking_data.payee_account}
    payment_response = requests.post(f"{payment_system_url}/make_payment", json=payment_data)
    
   
    if payment_response.status_code != 200:
        raise HTTPException(status_code=payment_response.status_code, detail=payment_response.json()['detail'])

    # Update venue occupancy
    venue_data = {"venue_id": booking_data.venue_id}
    response = requests.post(f"{venue_availability_url}/increment_venue_occupancy", json=venue_data)
    if response.status_code != 200:
        raise HTTPException(status_code=500, detail=response.json()['detail'])
    global booking_id 
    booking_id = booking_id + 1

    return {"message": "Booking confirmed", "booking_id": booking_id, "booking_time": datetime.datetime.now().strftime("%Y-%m-%d %H:%M:%S"), "venue_id": booking_data.venue_id, "amount": booking_data.amount, "payee_name": booking_data.payee_name}

# Register with Service Registry
def register_with_registry(port):
    service_data = {
        "name": "Booking System",
        "url": f"http://localhost:{port}"
    }
    requests.post(f"{SERVICE_REGISTRY_URL}/register", json=service_data)

if __name__ == '__main__':
    import uvicorn
    import argparse
    parser = argparse.ArgumentParser()
    parser.add_argument("--port", type=int, help="Port number", required=True)
    args = parser.parse_args()
    register_with_registry(args.port)
    #print(f"Booking System running on port {args.port}")
    uvicorn.run(app, host='0.0.0.0', port=args.port)
