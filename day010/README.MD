# Day 10 - Application Healthcheck

Applications ofter inform other about their current state.
Mechanism that provides that is called Healthcheck, and might be a simple information (like `OK`) or contain detailed information about submodules and connections.
In this task, you need to expose 3 endpoints - one for general healthcheck, and two for kubernetes: readiness and liveness.

Tests in this task assume, that you will use actuator for it.