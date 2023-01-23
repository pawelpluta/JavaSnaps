# Day 22 - REST retry

Today you need to implement retry mechanism for REST client. 
The client should make another request when first one fails, but if second one also fails, it should return that information.

Retry is often implemented in clients that communicate through the network, as communication might fail due to infrastructure problems.
This task is basing on previous one, but feel free to rewrite the client completly.