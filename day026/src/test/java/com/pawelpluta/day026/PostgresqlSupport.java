package com.pawelpluta.day026;

import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.utility.DockerImageName;

interface PostgresqlSupport {
    PostgreSQLContainer POSTGRESQL_CONTAINER = new PostgreSQLContainer(DockerImageName.parse("postgres:15.1"));

    @DynamicPropertySource
    static void setupMongoDb(DynamicPropertyRegistry propertyRegistry) {
        POSTGRESQL_CONTAINER.withInitScript("sql/databaseInit.sql").start();
        propertyRegistry.add("spring.datasource.url", POSTGRESQL_CONTAINER::getJdbcUrl);
        propertyRegistry.add("spring.datasource.username", POSTGRESQL_CONTAINER::getUsername);
        propertyRegistry.add("spring.datasource.password", POSTGRESQL_CONTAINER::getPassword);
    }
}
