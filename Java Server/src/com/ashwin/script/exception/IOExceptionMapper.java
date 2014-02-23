package com.ashwin.script.exception;

import java.io.IOException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class IOExceptionMapper implements ExceptionMapper<IOException> {

	public Response toResponse(IOException e) {
		return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
				.entity(e.getMessage()).type("text/plain").build();
	}
}
