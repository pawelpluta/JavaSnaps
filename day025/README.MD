# Day 25 - randomized test data

Sometimes during development you might face non-deterministic test results.
On some workstations tests will fail, while on an another will work just fine.
One of the causes might be improper test setup, using the same data for multiple test.
It might cause tests to interfere with each other and depend on the test order.

One of the solutions for that is to use randomized test data. 
Generating unique data for each test can prevent data interference.

In this task, assume that code i working properly, but tests are generating the bug. 
Try to fix it by generating random test data.