package com.pawelpluta.day025;

import java.util.Optional;

interface UserRepository {
    void register(User user);
    void save(User user);
    Optional<User> findById(UserId id);

}
