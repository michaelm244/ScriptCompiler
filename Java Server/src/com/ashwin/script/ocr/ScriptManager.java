package com.ashwin.script.ocr;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

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

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

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
	@Path("ocr")
	@Produces("application/json")
	public Response recognizeScript(String base64) throws IOException, TesseractException, JSONException {
		BufferedImage image = ScriptRecognition.decodeBase64(base64);
		String script = ScriptRecognition.processImage(image);
		JSONObject result = new JSONObject();
		result.put("original", base64);
		result.put("script", script);
		
		ResponseBuilder builder = new ResponseBuilderImpl();
		builder.header("content-type", "application/json");
		builder.status(Response.Status.OK);
		builder.entity(result);
		return builder.build();
	}
	
	@POST
	@Path("script")
	@Produces("application/json")
	public Response evaluateScript(@Context HttpHeaders headers, String script) throws JSONException, IOException {
		String type = headers.getRequestHeader("type").get(0);
		JSONObject result = new JSONObject();
	
		try {
			ByteArrayOutputStream console = new ByteArrayOutputStream();
			PrintStream system = System.out;
			System.setOut(new PrintStream(console));
			
			long startTimeMillis = System.currentTimeMillis();
			Object output = ScriptRecognition.evaluateScript(type, script);
			long elapsedTimeMillis = System.currentTimeMillis() - startTimeMillis;

			result.put("result", output);
			result.put("success", true);
			result.put("console", new String(console.toByteArray(), "UTF-8"));
			result.put("time", elapsedTimeMillis);
			
			System.setOut(system);
			console.close();
		} catch(ScriptException e) {
			result.put("error", e.getCause());
			result.put("success", false);
		}
		
		ResponseBuilder builder = new ResponseBuilderImpl();
		builder.header("content-type", "application/json");
		builder.status(Response.Status.OK);
		builder.entity(result);
		return builder.build();
	}
}
