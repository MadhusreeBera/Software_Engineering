from fastapi import FastAPI, HTTPException
import requests
from pydantic import BaseModel
from dotenv import load_dotenv
import os

# Load environment variables from .env file
load_dotenv()

app = FastAPI()
SERVICE_REGISTRY_URL = os.getenv("SERVICE_REGISTRY_URL")

class RequestData(BaseModel):
    service_type: str
    body: dict

@app.post('/')
def route(request_data: RequestData):
    service_name = ""
    path=""
    if(request_data.service_type == "Payment"):
        #print("Payment API set")
        service_name = os.getenv("PAYMENT_SERVICE_NAME")
        path="make_payment"
    elif(request_data.service_type == "VenueAvailability"):
        #print("Venue Availability API set")
        service_name = os.getenv("VENUE_AVAILABILITY_SERVICE_NAME")
        path="venue_availability"
    elif(request_data.service_type == "Booking"):
        #print("Booking API set")
        service_name = os.getenv("BOOKING_SERVICE_NAME")
        path="book"

    data = {
        "path": path,
        "service_name": service_name,
        "body": request_data.body
    }
    #print(f"Request directed to load balancer")
    response = requests.post(f"{os.getenv('LOAD_BALANCER_URL')}/balance_request", json=data)
    return response.json()

if __name__ == '__main__':
    import uvicorn
    import argparse
    parser = argparse.ArgumentParser()
    parser.add_argument("--port", type=int, help="Port number", required=True)
    args = parser.parse_args()
    #print(f"API gateway running on port {args.port}")
    uvicorn.run(app, host='0.0.0.0', port=args.port)
