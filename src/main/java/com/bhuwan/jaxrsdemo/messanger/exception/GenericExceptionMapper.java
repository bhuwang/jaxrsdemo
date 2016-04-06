/**
 * 
 */
package com.bhuwan.jaxrsdemo.messanger.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.bhuwan.jaxrsdemo.messanger.model.ErrorMessage;

/**
 * @author bhuwan
 *
 */
@Provider
public class GenericExceptionMapper implements ExceptionMapper<Throwable> {

    @Override
    public Response toResponse(Throwable ex) {
        ErrorMessage message = new ErrorMessage(ex.getMessage(), Status.INTERNAL_SERVER_ERROR.getStatusCode(), "http://bhuwan.com");
        return Response.status(Status.INTERNAL_SERVER_ERROR).entity(message).build();
    }

}
