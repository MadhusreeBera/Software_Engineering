from fastapi import FastAPI, HTTPException
import argparse

app = FastAPI()
registered_services = {}

@app.post('/register')
def register_service(service_data: dict):
    #print(f"Registering {service_data['name']} at {service_data['url']}")
    #print(registered_services)
    registered_services[service_data['name']] = service_data['url']
    return {"message": "Service registered successfully"}

@app.get('/find/{service_name}')
def find_service(service_name: str):
    if service_name in registered_services:
        return {"url": registered_services[service_name]}
    else:
        raise HTTPException(status_code=404, detail="Service not found")

if __name__ == '__main__':
    import uvicorn
    parser = argparse.ArgumentParser()
    parser.add_argument("--port", type=int, help="Port number", required=True)
    args = parser.parse_args()
    #print(f"Service Registry running on port {args.port}")
    uvicorn.run(app, host='0.0.0.0', port=args.port)
