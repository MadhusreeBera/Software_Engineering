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
    path: str
    service_name: str
    body: dict

@app.post('/balance_request')
def balance_request(request_data: RequestData):
    #print(f"Request directed to load balancer")
    response = requests.get(f"{SERVICE_REGISTRY_URL}/find/{request_data.service_name}")
    if response.status_code != 200:
        raise HTTPException(status_code=404, detail=response.json()['detail'])
    service_url = response.json()['url'] + "/" + request_data.path

    #print(f"Request directed to {request_data.service_name} at {service_url}")
    response = requests.post(f"{service_url}", json=request_data.body)
    return response.json()

if __name__ == '__main__':
    import uvicorn
    import argparse
    parser = argparse.ArgumentParser()
    parser.add_argument("--port", type=int, help="Port number", required=True)
    args = parser.parse_args()
    #print(f"Load Balancer running on port {args.port}")
    uvicorn.run(app, host='0.0.0.0', port=args.port)
