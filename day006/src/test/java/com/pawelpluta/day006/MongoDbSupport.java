package com.pawelpluta.day006;

import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.utility.DockerImageName;

abstract class MongoDbSupport {
    private static final MongoDBContainer MONGODB_CONTAINER = new MongoDBContainer(DockerImageName.parse("mongo:4.2.23"));

    @DynamicPropertySource
    static void setupMongoDb(DynamicPropertyRegistry propertyRegistry) {
        System.out.println(">>>>>>> starting container");
        MONGODB_CONTAINER.start();
        propertyRegistry.add("spring.data.mongodb.uri", MONGODB_CONTAINER::getReplicaSetUrl);
    }
}
