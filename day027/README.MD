# Day 27 - saga as orchestrator

Saga pattern allows for dividing one big transaction across multiple smaller ones.
It can be useful in distributed systems to perform complex processes.

Saga can contain multiple steps, like a process manager.
In case of problems at any step of the process, the Saga knows how to revert all previously made changes.
Those "revers" are called compensating transactions.

In this task, we are simulating a system that performs two things:

1. When order is placed, it makes a reservation for products in warehouse. Reservation decreases available product count.
2. when products are reserved, it invokes the payment

If payment goes without problems, then the flow is finished. 
But when payment will fail for some reason, we want to release the reservation. 

Your task is to write compensating transaction in `WarehouseEventListener`. It should be created in `ReservationsListener`