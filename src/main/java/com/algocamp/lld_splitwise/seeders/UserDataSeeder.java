package com.algocamp.lld_splitwise.seeders;

import com.algocamp.lld_splitwise.models.User;
import com.algocamp.lld_splitwise.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class UserDataSeeder implements CommandLineRunner {

    private final UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {
        if(userRepository.count() == 0) {
            System.out.println("Seeding user data ");

            List<User> users = List.of(
                    User.builder().userName("Alice").email("alice@mail.com").mobileNumber("999999999").build(),
                    User.builder().userName("Bob").email("bob@mail.com").mobileNumber("999999999").build(),
                    User.builder().userName("Charlie").email("charlie@mail.com").mobileNumber("999999999").build(),
                    User.builder().userName("David").email("david@mail.com").mobileNumber("999999999").build()

            );

            userRepository.saveAll(users);
        }
    }
}
