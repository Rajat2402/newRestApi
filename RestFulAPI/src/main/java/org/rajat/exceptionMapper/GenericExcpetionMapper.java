package org.rajat.exceptionMapper;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class GenericExcpetionMapper implements ExceptionMapper<Throwable> {

	@Override
	public Response toResponse(Throwable exception) {
		
		ErrorPayload error=new ErrorPayload(500 , "Internal Server Error");
		Response response = Response.status(Status.INTERNAL_SERVER_ERROR).entity(error).build();
		return response;
	}


}
