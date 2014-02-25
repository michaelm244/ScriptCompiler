package com.ashwin.script.spell;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SpellChecker {

//	http://norvig.com/spell-correct.html
	public static void main(String[] args) throws IOException {
		Dictionary dict = Dictionary.load(new File("assets/train.txt"));
		Scanner input = new Scanner(System.in);
		String next = null;
		
		while(!(next = input.next()).equalsIgnoreCase("quit")) {
			if(dict.isWord(next))
				System.out.println("Spelling of " + next + " is correct.");
			else
				System.out.println("Suggested spellings: " + known(getEdits(next), dict));
		}
		
		input.close();
	}
	
	// Returns an list of the known word edits, ordered by how common they
	// are in the dictionary.
	private static List<String> known(List<String> words, Dictionary dict) {
		List<String> known = new ArrayList<String>();
		for(String word : words) {
			if(dict.isWord(word)) {
				int usage = dict.getUsage(word);
				int pos	  = 0;
				
				while(pos < known.size() && dict.getUsage(known.get(pos)) > usage) pos++;
				known.add(pos, word);
			}
		}
		return known;
	}
	
//	Four different kinds of errors for word of length (n):
//		(1) Deletion: Remove letters			(n)
//		(2) Transposes: Swap adjacent letters	(n - 1)
//		(3) Alteration: Change letters around 	(26n)
//		(4) Insertion: Add a letter				26(n + 1)
//	Size of the resulting list: 54 * n + 25;
	private static List<String> getEdits(String word) {
		int len  = word.length();
		
		List<String> edits = new ArrayList<String>();
		for(int i = 0; i < len + 1; i++) {
//			Deletes
			if(i < len)	edits.add(word.substring(0, i) + word.substring(i + 1));
			
//			Transposes
			if(i < len - 1) 	
				edits.add(word.substring(0, i) + 
						  String.valueOf(word.charAt(i + 1)) +
						  String.valueOf(word.charAt(i)) +
						  word.substring(i + 2));
					  
//			Alterations and Insertions
			for(char letter = 'a'; letter <= 'z'; letter++) {
				if(i < len + 0)	edits.add(word.substring(0, i) + letter + word.substring(i + 1));
				if(i < len + 1) edits.add(word.substring(0, i) + letter + word.substring(i));
			}
		}
		
		return edits;
	}
}
