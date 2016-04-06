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
public class DataNotFoundExceptionMapper implements ExceptionMapper<DataNotFoundException> {

    @Override
    public Response toResponse(DataNotFoundException ex) {
        ErrorMessage message = new ErrorMessage(ex.getMessage(), Status.NOT_FOUND.getStatusCode(), "http://bhuwan.com");
        return Response.status(Status.NOT_FOUND).entity(message).build();
    }

}
