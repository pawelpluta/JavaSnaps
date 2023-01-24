# Day 23 - idempotent consumer

This task is related to a problem commonly faced in distributed system.
Due to different reasons, the same message might be delivered multiple times, causing the same data to be processed twice or more.
Imagine an account balance maintaining system: if a message changing the account balance would be processed twice, then there could be a money loss.
For this reason, the receiver of the message should be idempotent.

Implement a solution that will ensure the command is processed exactly once.
You can use any technic, and modify database schema if needed.
Database schema is located under resources/sql/schemaInit.sql in tests.
