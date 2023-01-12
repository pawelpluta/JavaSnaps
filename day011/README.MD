# Day 11 - Metrics

One of the ways to know what is a state of application and its condition are metrics.
Metrics are values that gives you an overview of application internals.
Usually, there is a tool that allows you to create a metric. One of popular choices is micrometer.
Metrics needs to be aggregated in another tool that aggregate them. In this task, we are going to use prometheus.

Your task is to produce gauge metric and expose it with actuator. 
Refer to test to know the metric name and endpoint name for exposing metrics. 