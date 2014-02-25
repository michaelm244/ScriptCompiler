package com.ashwin.script.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.codehaus.jettison.json.JSONException;

@Provider
public class JSONExceptionMapper implements ExceptionMapper<JSONException> {

	public Response toResponse(JSONException e) {
		return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
				.entity(e.getMessage()).type("text/plain").build();
	}
}
