# Day 21 - wiremock

One of the software parts that should be tested are interfaces.
This task will focus on outgoing REST interface - so a situation when our application is a client.

For this task, you must provide wiremock mapping so the tests will pass.
Wiremock can keep its mapping as a files under /mappings directory in resources. 
Application is already configured.
One test will fail due to misconfiguration of mapping. Second one is missing mapping at all. 