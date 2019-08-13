package ru.asavt.spring.earn.learn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.asavt.spring.earn.learn.model.Message;
import ru.asavt.spring.earn.learn.model.User;

public interface UserDetailsRepo extends JpaRepository<User, String> {
}
