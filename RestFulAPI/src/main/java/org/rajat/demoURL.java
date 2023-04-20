package org.rajat;

import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;



@Path("/")
public class demoURL {
	
	@Context
	private UriInfo uriInfo;
	
	@GET
	@Produces({MediaType.APPLICATION_JSON , MediaType.TEXT_PLAIN})
	public String sayhello(@HeaderParam("demo")  String demo)
	{
		if(demo==null)
		{
			throw new NotFoundException();
		}
		else
		{
			return "Hello JAVA Developer header value of demo is :"+demo+" and base URI is :"+uriInfo.getAbsolutePath();
		}
	}
}
