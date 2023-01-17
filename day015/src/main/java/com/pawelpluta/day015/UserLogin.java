package com.pawelpluta.day015;

import org.springframework.data.annotation.Id;

record UserLogin(@Id String userId, String login) {
}
