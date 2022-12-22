I prefer use combined approach in testing

My testing strategy
1. The business logic in every microservice I will cover with Unit tests
2. Transmission between "http-client and microservice" and "microservice and database" I will test with Integration tests
3. The logic of interaction between microservice and third party api (like rabbitMQ or other microservice) I will test with Contract testing
4. The work flow of microservice I will test with Component testing.
Most of the cases will check the positive flow (when we expect the positive result) 
5. The work flow of entire application I will test with end-2-end testing. 
Here I will test only positive flows of the application. To make the correct cases 
I need to make an exploration how user is using the application

My testing strategy has created according to testing triangle, where the first bullet in the list 
will have the most covered test cases and descending from top to bottom, the fifth bullet in the list
will be the least number of cases.
