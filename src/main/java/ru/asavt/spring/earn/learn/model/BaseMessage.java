package ru.asavt.spring.earn.learn.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Data
@Getter
@Builder
@JsonDeserialize
public class BaseMessage {
    public BaseMessage() {
    }

    private String id;
    private String text;

    public BaseMessage(String id, String text) {
        this.id = id;
        this.text = text;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "{" +
                "id='" + id + '\'' +
                ", text='" + text + '\'' +
                '}';
    }
}
