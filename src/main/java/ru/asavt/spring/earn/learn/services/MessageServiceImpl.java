package ru.asavt.spring.earn.learn.services;

import org.springframework.stereotype.Service;
import ru.asavt.spring.earn.learn.exceptions.NotFoundException;
import ru.asavt.spring.earn.learn.model.BaseMessage;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {

    private List<BaseMessage> messages = new ArrayList<>();

    @PostConstruct
    private void init() {
        messages.add(new BaseMessage("1", "232"));
    }


    @Override
    public BaseMessage getMessageById(String id) throws NotFoundException {
        return messages.stream().filter(m -> m.getId().equals(id)).findFirst().orElseThrow(NotFoundException::new);
    }

    @Override
    public BaseMessage putMessage(BaseMessage message) {
        int id = messages.size() + 1;
        message.setId(String.valueOf(id));
        messages.add(message);
        return message;

    }

    @Override
    public BaseMessage updateMessage(BaseMessage message) {

        BaseMessage baseMessage = getMessageById(message.getId());
        baseMessage = message;
        return baseMessage;
    }

    @Override
    public void delete(String id) {
        messages.remove(getMessageById(id));
    }

    @Override
    public List<BaseMessage> getListOfMessages() {
        return messages;
    }
}
