package ru.asavt.spring.earn.learn.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import ru.asavt.spring.earn.learn.model.BaseMessage;
import ru.asavt.spring.earn.learn.services.MessageService;

import java.util.List;

@RestController
@RequestMapping("message")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @GetMapping
    public List<BaseMessage> list(){
        return messageService.getListOfMessages();
    }

    @GetMapping("{id}")
    public BaseMessage getMessage(@PathVariable String id){
        return messageService.getMessageById(id);
    }


    @PostMapping
    public BaseMessage save(@RequestBody BaseMessage message){
        BaseMessage message1 = messageService.putMessage(message);
        return message1;
    }

    @PutMapping("{id}")
    public BaseMessage update(@PathVariable String id, @RequestBody BaseMessage message){
        if (StringUtils.isEmpty(message.getId())){
            message.setId(id);
        }

        return messageService.updateMessage(message);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable String id){
        messageService.delete(  id);
    }

}
