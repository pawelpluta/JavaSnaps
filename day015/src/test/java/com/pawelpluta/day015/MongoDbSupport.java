package com.pawelpluta.day015;

import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.utility.DockerImageName;

interface MongoDbSupport {
    MongoDBContainer MONGODB_CONTAINER = new MongoDBContainer(DockerImageName.parse("mongo:4.2.23"));

    @DynamicPropertySource
    static void setupMongoDb(DynamicPropertyRegistry propertyRegistry) {
        MONGODB_CONTAINER.start();
        propertyRegistry.add("spring.data.mongodb.uri", MONGODB_CONTAINER::getReplicaSetUrl);
    }
}
