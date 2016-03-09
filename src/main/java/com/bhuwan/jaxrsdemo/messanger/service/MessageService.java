/**
 * 
 */
package com.bhuwan.jaxrsdemo.messanger.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import com.bhuwan.jaxrsdemo.messanger.DatabaseClass;
import com.bhuwan.jaxrsdemo.messanger.exception.DataNotFoundException;
import com.bhuwan.jaxrsdemo.messanger.model.Message;

/**
 * @author bhuwan
 *
 */
public class MessageService {

    private Map<Long, Message> messages = DatabaseClass.getMessages();

    public MessageService() {
        messages.put(1L, new Message(1, "Custom Message 1", "Bhuwan"));
        messages.put(2L, new Message(2, "Custom Message 2", "Prisha"));
    }

    public List<Message> getAllMessages() {
        return new ArrayList<Message>(messages.values());
    }

    public Message getMessage(long id) {
        Message message = messages.get(id);
        if (message == null) {
            throw new DataNotFoundException("Message with id: " + id + " not found.");
        }
        return message;
    }

    public Message addMessage(Message message) {
        message.setId(messages.size() + 1);
        messages.put(message.getId(), message);
        return message;
    }

    public Message updateMessage(Message message) {
        if (message.getId() <= 0) {
            return null;
        }
        messages.put(message.getId(), message);
        return message;
    }

    public Message removeMessage(long id) {
        return messages.remove(id);
    }

    public List<Message> getAllMessages(int year) {
        List<Message> list = new ArrayList<>();
        Calendar cal = Calendar.getInstance();
        for (Message msg : messages.values()) {
            cal.setTime(msg.getCreated());
            if (cal.get(Calendar.YEAR) == year) {
                list.add(msg);
            }
        }
        return list;
    }

    public List<Message> getAllMessages(int start, int size) {
        List<Message> list = new ArrayList<>(messages.values());
        if ((start + size) > list.size()) {
            return new ArrayList<>();
        }
        return list.subList(start, (start + size));
    }
}
