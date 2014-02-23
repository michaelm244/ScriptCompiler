package com.ashwin.script.exception;

import javax.script.ScriptException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class ScriptExceptionMapper implements ExceptionMapper<ScriptException> {

	public Response toResponse(ScriptException e) {
		return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
				.entity(e.getMessage()).type("text/plain").build();
	}
}
