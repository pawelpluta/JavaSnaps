package com.pawelpluta.day025;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

class InMemoryUserRepository implements UserRepository {

    private final Map<UserId, User> users = new HashMap<>();

    @Override
    public void register(User user) {
        users.putIfAbsent(user.id(), user);
    }

    @Override
    public void save(User user) {
        users.put(user.id(), user);
    }

    @Override
    public Optional<User> findById(UserId id) {
        return Optional.ofNullable(users.get(id));
    }
}
