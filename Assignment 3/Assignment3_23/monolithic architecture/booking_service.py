from fastapi import FastAPI
from pydantic import BaseModel


app = FastAPI()


class Venue(BaseModel):
    name: str
    capacity: int
    tickets_sold: int


class BookingRequest(BaseModel):
    venue_name: str
    num_tickets: int
    payment_info: float


venues = {
    "Concert Hall": Venue(name="Concert Hall", capacity=5000, tickets_sold=0),
    "Theater": Venue(name="Theater", capacity=5000, tickets_sold=0),
}


@app.get("/venues")
def get_venues():
    return sorted(list(venues.values()), key=lambda x: x.capacity - x.tickets_sold, reverse=True)


def payment(payment_info):
    # Simulate payment processing (replace with actual payment gateway integration)
    # This part would typically involve verifying payment information with the payment gateway
    # and handling success/failure scenarios.
    print(f"Payment Amount: {payment_info}")
    return True


@app.post("/book-tickets")
def book_tickets(request: BookingRequest):
    if request.venue_name not in venues:
        return {"message": f"Venue {request.venue_name} is not registered."}, 404

    venue = venues[request.venue_name]
    if venue.tickets_sold + request.num_tickets <= venue.capacity:
        if payment(request.payment_info):
            venue.tickets_sold += request.num_tickets
            return {"message": f"Successfully booked {request.num_tickets} tickets for {venue.name}."}
        else:
            return {"message": "Payment failed. Booking not confirmed."}, 400
    else:
        return {"message": f"Sorry, there are not enough tickets available for {venue.name}."}, 400


if __name__ == "__main__":
    import uvicorn

    uvicorn.run("booking_service:app", host="0.0.0.0", port=8000)
