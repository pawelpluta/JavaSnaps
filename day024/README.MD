# Day 24 - synchronous calls

Todays task is to optimize code that needs to perform two, time consuming operations.
`TestController` invokes REST calls to other services, that takes long time to respond (250 and 350 milliseconds).

Test requires the interface to respond in less then 500ms. 
The task is to make it happen without changing the response times of dependent REST calls.