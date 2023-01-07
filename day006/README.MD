# Day 6 - testcontainers

Integration tests are a great way to check your application.
Years back, when database was needed for tests, some inmemory implementation were used by developers, such as embed mongo.
It had a drawback that not all database features were available.

To overcome that, we can use real database hosted on Docker.
Testcontainers is a technology, that allows to create a docker container from code and use it in tests.

For this task, you need to configure MongoDB testcontainer and use it in tests.