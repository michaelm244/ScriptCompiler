package com.ashwin.script.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import net.sourceforge.tess4j.TesseractException;

@Provider
public class TesseractExceptionMapper implements ExceptionMapper<TesseractException> {

	public Response toResponse(TesseractException e) {
		return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
				.entity(e.getMessage()).type("text/plain").build();
	}
}
