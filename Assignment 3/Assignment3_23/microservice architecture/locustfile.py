from locust import FastHttpUser, task

class HelloWorldUser(FastHttpUser):
#     @task
#     def post_booking_venue1(self):
#         data = {
#                     "service_type": "Booking",
#                     "body": {
#                       "amount": 100,
#                       "venue_id": 1,
#                       "payee_name": "Madhusree",
#                       "payee_account": "xxxx"
#                     }
#                 }
#         self.client.post("/book-tickets", json=data)
#     def post_booking_venue2(self):
#         data = {
#                     "service_type": "Booking",
#                     "body": {
#                       "amount": 100,
#                       "venue_id": 2,
#                       "payee_name": "Madhusree",
#                       "payee_account": "xxxx"
#                     }
#                 }
#         self.client.post("/book-tickets", json=data)
#     def post_booking_venue3(self):
#         data = {
#                     "service_type": "Booking",
#                     "body": {
#                       "amount": 100,
#                       "venue_id": 3,
#                       "payee_name": "Madhusree",
#                       "payee_account": "xxxx"
#                     }
#                 }
#         self.client.post("/book-tickets", json=data)

    @task
    def post_venue_availability(self):
        data = {
                    
                "service_type": "VenueAvailability",
                "body": {}

              }
        self.client.post("/venues", json=data)