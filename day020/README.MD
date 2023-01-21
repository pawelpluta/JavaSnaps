# Day 20 - liquibase

Liquibase allows for keeping database schema versioned and up to date.
It can perform automatic database migration by executing a definition provided in files, or might be a step in deployment pipeline.
Those definitions, when kept in liquibase format, can be executed on different types of databases.

Today, the task is to perform a migration using liquibase.
Application is already configured, so you need only to provide definitions for two things:

* create a table
* insert a row into this table

You need to figure out the table name and row data from test.