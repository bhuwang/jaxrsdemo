/**
 * 
 */
package com.bhuwan.jaxrsdemo.messanger.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.bhuwan.jaxrsdemo.messanger.DatabaseClass;
import com.bhuwan.jaxrsdemo.messanger.model.Comment;
import com.bhuwan.jaxrsdemo.messanger.model.Message;

/**
 * @author bhuwan
 *
 */
public class CommentService {

    private Map<Long, Message> messages = DatabaseClass.getMessages();

    public CommentService() {
            messages.get(1L).getComments().put(1L, new Comment(1, "comment 1", "Gautam Bhuwan"));
            messages.get(2L).getComments().put(1L, new Comment(2, "comment 2", "Gautam Prisha"));
        }   

    public List<Comment> getAllComments(long messageId) {
        System.out.println("messageId: " + messageId);
        if (messages.get(messageId) != null) {
            Map<Long, Comment> comments = messages.get(messageId).getComments();
            return new ArrayList<Comment>(comments.values());
        }
        return new ArrayList<Comment>();
    }

    public Comment getComment(long messageId, long commentId) {
        Map<Long, Comment> comments = messages.get(messageId).getComments();
        return comments.get(commentId);
    }

    public Comment addComment(long messageId, Comment comment) {
        System.out.println("messageId:" + messageId);
        Map<Long, Comment> comments = messages.get(messageId).getComments();
        comment.setId(comments.size() + 1);
        comments.put(comment.getId(), comment);
        System.out.println("before size: " + comments.size());
        messages.get(messageId).setComments(comments);
        System.out.println("after size: " + comments.size());
        return comment;
    }

    public Comment updateComment(long messageId, Comment comment) {
        Map<Long, Comment> comments = messages.get(messageId).getComments();
        if (comment.getId() <= 0) {
            return null;
        }
        comments.put(comment.getId(), comment);
        messages.get(messageId).setComments(comments);
        return comment;
    }

    public void removeComment(long messageId, long commentId) {
        Map<Long, Comment> comments = messages.get(messageId).getComments();
        comments.remove(commentId);
        messages.get(messageId).setComments(comments);
    }
}
