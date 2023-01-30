# Day 29 - CQRS - creating read model

This task require from you preparation of dedicated data structure that will handle queries.
In this system, customer is attached to bank.
Adding new customer to bank might be heavy operation, due to all business logic that might be executed.
Data structure in the database might consist of multiple tables (however, in this task it is maximally simplified).

Currently, command that registers customer into a bank is generating an event that is emitted. 
It keeps the information about customer and its bank.
Your task is to create read model for a operation of querying customer data and information where is its bank.
To do that, you need to:

* create a data structure in DB for queries (optimize it for reads)
* implement the `CustomerRegisteredInBankListener` that will handle events generate by successful processing of `RegisterCustomerCommand`
* implement the `CustomerQuery` that will return the data on request