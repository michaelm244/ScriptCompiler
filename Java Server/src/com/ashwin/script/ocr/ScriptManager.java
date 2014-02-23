package com.ashwin.script.ocr;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.script.ScriptException;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import net.sourceforge.tess4j.TesseractException;

import com.sun.jersey.core.spi.factory.ResponseBuilderImpl;

@Path("/manager")
public class ScriptManager {

	@GET
	@Path("test")
	@Produces("text/plain")
	public String getTest() {
		return "Test Successful!";
	}
	
	@POST
	@Path("script")
	@Produces("text/plain")
	public Response recognizeScript(@Context HttpHeaders header, String base64) throws IOException, ScriptException, TesseractException {
		String type = header.getRequestHeader("type").get(0);
		
		BufferedImage image = ScriptRecognition.decodeBase64(base64);
		String script = ScriptRecognition.processImage(image);
		Object obj = ScriptRecognition.evaluateScript(type, script);

		ResponseBuilder builder = new ResponseBuilderImpl();
		builder.header("content-type", "application/json");
		builder.status(Response.Status.OK);
		builder.entity(obj);
		return builder.build();
	}
	
}
