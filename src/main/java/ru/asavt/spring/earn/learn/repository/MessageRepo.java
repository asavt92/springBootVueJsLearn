package ru.asavt.spring.earn.learn.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import ru.asavt.spring.earn.learn.model.Message;

public interface MessageRepo extends JpaRepository<Message, Long> {
}
