package com.pawelpluta.day015;

import org.springframework.data.mongodb.repository.MongoRepository;

interface UserLoginRepository extends MongoRepository<UserLogin, String> {
}
