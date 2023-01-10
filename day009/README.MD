# Day 9 - Pattern Matching for switch

Today's task requires you to enable preview features in your IDE (if you are only using gradle from command line, than it is covered by build.gradle).
With Java 19, Pattern Matching for switch came in 3rd preview. 
It is still not a part of the core version, but might be interesting to test.
For reference, please visit [JEP-420](https://openjdk.org/jeps/420).

Your code should use switch statement to find the type of the object provided to the `SwitchTypeMatching` class and create an instance of `MatchResults` class.
The `MatchResults` contains creation methods that refer to the object types in tests.