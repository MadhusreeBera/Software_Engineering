from fastapi import FastAPI, HTTPException
import requests
from dotenv import load_dotenv
from pydantic import BaseModel
import os

# Load environment variables from .env file
load_dotenv()

app = FastAPI()
SERVICE_REGISTRY_URL = os.getenv("SERVICE_REGISTRY_URL")


class PaymentData(BaseModel):
    amount: int
    payee_name: str
    payee_account: str


@app.post('/make_payment')
def make_payment(payment_data: PaymentData):
    # For simplicity, assuming payment is always successful
    if payment_data.amount <= 0:
        raise HTTPException(status_code=401, detail={ "message" : "Invalid amount"})
    

    success = True
    return {"success": success}

# Register with Service Registry
def register_with_registry(port):
    service_data = {
        "name": "Payment System",
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
    #print(f"Payment System running on port {args.port}")
    uvicorn.run(app, host='0.0.0.0', port=args.port)
