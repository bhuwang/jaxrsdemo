/**
 * 
 */
package com.bhuwan.jaxrsdemo.resources;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import com.bhuwan.jaxrsdemo.model.Message;
import com.bhuwan.jaxrsdemo.resources.custombeans.MessageFilterBean;
import com.bhuwan.jaxrsdemo.service.MessageService;

/**
 * @author bhuwan
 *
 */
@Path("/messages")
@Consumes(value={MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
@Produces(value={MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
public class MessageResource {

    MessageService messageService = new MessageService();

    @GET
    public List<Message> getMessages(@BeanParam MessageFilterBean messageFilterBean, @Context UriInfo uriInfo) {
        if (messageFilterBean.getYear() > 0) {
            return messageService.getAllMessages(messageFilterBean.getYear());
        }
        if (messageFilterBean.getStart() >= 0 && messageFilterBean.getSize() > 0) {
            return messageService.getAllMessages(messageFilterBean.getStart(), messageFilterBean.getSize());
        }
        List<Message> list = addLinks(messageService.getAllMessages(), uriInfo);
        return list;
    }

    @GET
    @Path("/{messageId}")
    public Message getMessage(@PathParam("messageId") Long messageId, @Context UriInfo uriInfo) {
        Message message = messageService.getMessage(messageId);
        List<Message> messages = new ArrayList<Message>();
        messages.add(message);
        return addLinks(messages, uriInfo).get(0);
    }

    @POST
    public Response addMessage(Message message, @Context UriInfo uriInfo) {
        Message newMessage = messageService.addMessage(message);
        List<Message> messages = new ArrayList<Message>();
        messages.add(newMessage);
        return Response.status(Status.CREATED).entity(addLinks(messages, uriInfo).get(0)).build();
    }

    @PUT
    @Path("/{messageId}")
    public Message updateMessage(@PathParam("messageId") Long messageId, Message message) {
        message.setId(messageId);
        return messageService.updateMessage(message);
    }

    @DELETE
    @Path("/{messageId}")
    public void deleteMessage(@PathParam("messageId") Long messageId) {
        messageService.removeMessage(messageId);
    }

    @Path("/{messageId}/comments")
    public CommentResource getCommentResource() {
        return new CommentResource();
    }
    
    private String getUriForComment(Message message, UriInfo uriInfo) {
        return uriInfo.getBaseUriBuilder().path(MessageResource.class).path(MessageResource.class, "getCommentResource").path(CommentResource.class)
                .resolveTemplate("messageId", message.getId()).build().toString();
    }

    private String getUriForProfile(Message message, UriInfo uriInfo) {
        return uriInfo.getBaseUriBuilder().path(ProfileResource.class).path(message.getAuthor()).build().toString();
    }

    private String getUriForSelf(Long messageId, UriInfo uriInfo) {
        return uriInfo.getBaseUriBuilder().path(MessageResource.class).path(messageId.toString()).build().toString();
    }
    
    private List<Message> addLinks(List<Message> messages, UriInfo uriInfo){
        List<Message> messageList = new ArrayList<>();
        for(Message msg: messages){
            msg.addLink(getUriForSelf(msg.getId(), uriInfo), "self");
            msg.addLink(getUriForProfile(msg, uriInfo), "profile");
            msg.addLink(getUriForComment(msg, uriInfo), "comment");
            messageList.add(msg);
        }
        return messageList;
    }
}
