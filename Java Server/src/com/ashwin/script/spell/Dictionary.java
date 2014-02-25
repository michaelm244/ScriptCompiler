package com.ashwin.script.spell;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Dictionary {

	private Map<String, Integer> _words;
	
	public Dictionary(Map<String, Integer> words) {
		_words = words;
	}
	
//	Returns whether or not something is a known word.
	public boolean isWord(String word) {
		return _words.get(word) != null;
	}
	
//	Returns the number of times a word has been repeated in
//	the training data (tells you how common it is).
	public int getUsage(String word) {
		return _words.get(word);
	}
	
//	Create a Dictionary object from a File.
	public static Dictionary load(File file) throws IOException {
		FileInputStream fis = new FileInputStream(file);
		byte[] buffer = new byte[fis.available()];
		fis.read(buffer);
		
		String[] words = new String(buffer).toLowerCase().split("[^a-z]+");
		Map<String, Integer> usage = new HashMap<String, Integer>();
		
		for(String word : words)
			if(usage.get(word) == null)
				usage.put(word, 1);
			else
				usage.put(word, usage.get(word) + 1);
		
		return new Dictionary(usage);
	}
}
