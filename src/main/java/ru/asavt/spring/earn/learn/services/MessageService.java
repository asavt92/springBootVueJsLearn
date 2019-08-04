package ru.asavt.spring.earn.learn.services;

import ru.asavt.spring.earn.learn.exceptions.NotFoundException;
import ru.asavt.spring.earn.learn.model.BaseMessage;

import java.util.List;

public interface MessageService {

    BaseMessage getMessageById(String id) throws NotFoundException;
    BaseMessage putMessage(BaseMessage message);
    BaseMessage updateMessage(BaseMessage message);
    void delete(String id);
    List<BaseMessage> getListOfMessages();


}
