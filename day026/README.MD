# Day 26 - transactional outbox

Today's task is to implement a pattern that sends out events and persist aggregate changes in single transaction.
Usually it is implemented on database with a table dedicated for events, where they are stored. 
Events are picked up from this table and sent by another thread.

The most important for this pattern to work is to persist changes and events in the same transaction.
For task clearness purposes, tests for transaction checks are skipped, but feel free to write them.