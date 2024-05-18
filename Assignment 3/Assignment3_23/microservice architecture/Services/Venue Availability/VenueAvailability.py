from fastapi import FastAPI, HTTPException
import requests
from pydantic import BaseModel

from dotenv import load_dotenv
import os

# Load environment variables from .env file
load_dotenv()



app = FastAPI()
SERVICE_REGISTRY_URL = os.getenv("SERVICE_REGISTRY_URL")

venue_details = [
    {
        "venue_id": 1,
        "capacity": 500,
        "occupied": 0
    },
    {
        "venue_id": 2,
        "capacity": 200,
        "occupied": 0
    },
    {
        "venue_id": 3,
        "capacity": 200,
        "occupied": 0
    }
]

@app.get('/venue_availability/{venue_id}')
def get_venue_availability(venue_id: int):
    if venue_id < 1 or venue_id > len(venue_details):
        raise HTTPException(status_code=404, detail={"message": "Venue not found"})

    available_seats = venue_details[venue_id - 1]['capacity'] - venue_details[venue_id - 1]['occupied']
    venue_availability = {
        "venue_id": venue_id,
        "available_seats": available_seats,
        "available": available_seats > 0
    }    

    return venue_availability

@app.post('/venue_availability')
def get_venue_availability(venue_data: dict):
    return venue_details
class Venue_Data(BaseModel):
    venue_id: int


@app.post('/increment_venue_occupancy')
def update_venue_occupancy(venue_data: dict):
    venue_id = venue_data['venue_id']
    occupied = venue_details[venue_id - 1]['occupied'] + 1

    if occupied > venue_details[venue_id - 1]['capacity']:
        return {"message": "Venue at full capacity"}
    venue_details[venue_id - 1]['occupied'] = occupied
    return {"message": "Venue occupancy updated"}

@app.post('/decrement_venue_occupancy')
def decrement_venue_occupancy(venue_data: dict):
    venue_id = venue_data['venue_id']
    occupied = venue_details[venue_id - 1]['occupied'] - 1

    if occupied < 0:
        return {"message": "Venue occupancy cannot be negative"}
    
    venue_details[venue_id - 1]['occupied'] = occupied
    return {"message": "Venue occupancy decremented"}

# Register with Service Registry
def register_with_registry(port):
    service_data = {
        "name": "Venue Availability System",
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
    #print(f"Venue Availability System running on port {args.port}")
    #make server restart on code change
    uvicorn.run(app, host='0.0.0.0', port=args.port)
