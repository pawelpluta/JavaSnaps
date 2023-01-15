# Day 13 - stub 'n' mock

While writing tests, sometimes we need to fake some behaviour.
It is useful when some objects logic is complex or takes a lot of time to execute.
There are four types of objects, that are helpful in that: dummy, fake, stub and mock.
In this task, we will focus on two last.
Use stubs when you need to fake some behaviour or object.
Mock on the other hand allows you to verify the invocations.
Unfortunately, mockito does not differentiate it, and both things are called Mocks.

Use Mockito to test the PaycheckService. You need to stub the behaviour of one interface and verify invocation on two other to check the logic.