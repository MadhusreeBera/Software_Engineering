from fastapi import FastAPI, Request, HTTPException
from typing import List
import requests
from pydantic import BaseModel
from dotenv import load_dotenv
import os

# Load environment variables from .env file
load_dotenv()


app = FastAPI()

# Counter to keep track of the current gateway
gateway_counter = 0

class RequestData(BaseModel):
    service_type: str
    body: dict

@app.get("/")
async def read_root():
    return "Reverse Proxy running"


@app.post("/book-tickets")
async def forward_to_gateway(body: RequestData):

    #calculating the gateway index in round robin manner
    global gateway_counter
    gateway_index =( gateway_counter % int(os.getenv("NO_OF_GATEWAYS")) )+1
    gateway_counter += 1

     
    # Forward the request to the appropriate gateway
    forward_url = os.getenv(f"API_GATEWAY_URL_{gateway_index}")
    #print(f"Forwarding request to API Gateway {gateway_index}" )

    data = {
        "service_type": body.service_type,
        "body": body.body
    }
    response = requests.post(forward_url, json=data)
    return response.json()


@app.post("/venues")
async def forward_to_gateway(body: RequestData):

    #calculating the gateway index in round robin manner
    global gateway_counter
    gateway_index =( gateway_counter % int(os.getenv("NO_OF_GATEWAYS")) )+1
    gateway_counter += 1

     
    # Forward the request to the appropriate gateway
    forward_url = os.getenv(f"API_GATEWAY_URL_{gateway_index}")
    #print(f"Forwarding request to API Gateway {gateway_index}" )

    data = {
        "service_type": body.service_type,
        "body": body.body
    }
    response = requests.post(forward_url, json=data)
    return response.json()


if __name__ == "__main__":
    import uvicorn
    import argparse
    parser = argparse.ArgumentParser()
    parser.add_argument("--port", type=int, help="Port number", required=True)
    args = parser.parse_args()
    #print(f"Reverse Proxy running on port {args.port}")
    uvicorn.run(app, host='0.0.0.0', port=args.port)
