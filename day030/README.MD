# Day 30 - event sourcing

On the 30th day you need to implement an aggregate for library member.
Your implementation should use event sourcing - every invoked method should create an event.
Events will be persisted in event store (in this version it is simplified to in-memory implementation).

Implement all necessary methods inside the `LibraryMember` class. If needed, change fields and constructors.
Use tests as a reference for desired behaviour. 
Think about that where business rules should be checked.
Command handler is already implemented and takes care about fetching and emitting events.
It also invoked proper method in `LibraryMember` aggregate for every command.