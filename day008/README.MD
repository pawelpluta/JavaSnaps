# Day 8 - Request validation

Perform request body validation. You can use javax.validation library (for Spring Boot 3 use jakarta instead).
Rules for validation are:
* `integer` field must not be null and must be non-negative
* `decimal` field must not be null and must be negative
* `text` field must not be null and must have length between 8 and 16 characters
* `data` have a more complex rules:
  * when `magicLogicFlag` is set to `true`, then `magicLogicField` must have a non-empty value
  * when `magicLogicFlag` is set to `false`, then `magicLogicField` can be empty or null

Rules are covered with test cases.