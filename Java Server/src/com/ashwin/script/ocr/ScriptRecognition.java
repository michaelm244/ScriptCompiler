package com.ashwin.script.ocr;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.PrintStream;

import javax.imageio.ImageIO;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

import org.apache.commons.codec.binary.Base64;

public class ScriptRecognition {

	public static final String SCRIPT_PY = "py";
	public static final String SCRIPT_JS = "js";
	
	// Decodes a Base64 string into a BufferedImage.
	public static BufferedImage decodeBase64(String base64) throws IOException {
		byte[] bytes = Base64.decodeBase64(base64);
		ByteArrayInputStream bin = new ByteArrayInputStream(bytes);
		return ImageIO.read(bin);
	}
	
	// Conduct OCR on a BufferedImage.
	public static String processImage(BufferedImage image) throws TesseractException {
		BufferedImage transform = new BufferedImage(image.getWidth(),
													image.getHeight(),
													BufferedImage.TYPE_BYTE_BINARY);
		
		Graphics2D graphics = transform.createGraphics();
		graphics.drawImage(image, 0, 0, null);
		graphics.dispose();
		
		Tesseract tess = Tesseract.getInstance();
		return tess.doOCR(transform);
	}
	
	public static Object evaluateScript(String scriptName, String script) throws ScriptException {
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByExtension(scriptName);
        return engine.eval(script);
	}
}
