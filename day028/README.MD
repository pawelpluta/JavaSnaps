# Day 28 - circuit breaker

Synchronous communication between services may case problems when the service we are calling is unavailable.
To protect our service from long timeouts, and give the called service a change to restore from problems, we can use circuit breaker.

Circuit breaker is a pattern, that stops the communication between services when it discovers the communication problem.
Depending on configuration, it might count failed requests and decide, when to give some time to the other service to restore.

Your task is to configure circuit breaker so it will:

* stop sending requests after first failed request
* wait 5 seconds before it will allow for another call
* provide fallback implementation (and return mocked/predefined data) in case of open current