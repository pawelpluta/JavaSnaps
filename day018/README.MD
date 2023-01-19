# Day 18 - micronaut REST error handling

Yesterday we covered very basic use case for REST API in micronaut.
Today we need to handle the response codes, depending on the application behaviour.

The task is to implement `BookController` logic in a way that it will return proper HTTP statuses (200, 404 and 418).
Take a notice on tests - interesting fact is that HttpClient always throws an exception when response code id >= 400, so it requires special handling.
