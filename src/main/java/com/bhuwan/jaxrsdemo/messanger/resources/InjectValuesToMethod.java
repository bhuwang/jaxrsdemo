package com.bhuwan.jaxrsdemo.messanger.resources;

import javax.ws.rs.CookieParam;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.MatrixParam;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.UriInfo;

/**
 * @author bhuwan
 *
 */
@Path("/inject")
public class InjectValuesToMethod {

    @GET
    public String getParamsValues(@MatrixParam("matrix") String matrix, @HeaderParam("header") String header,
            @CookieParam("cookie") String cookie) {
        return "Matrix Param: " + matrix + " Header Param: " + header + " Cookie Param: " + cookie;
    }

    @GET
    @Path("/context")
    public String getParamsUsingContext(@Context UriInfo uriInfo, @Context HttpHeaders headers) {
        return "Query Params: " + uriInfo.getQueryParameters().toString() + " Path Params: " + uriInfo.getPathParameters().toString()
                + " Headers : " + headers.getRequestHeaders().toString();
    }
}
