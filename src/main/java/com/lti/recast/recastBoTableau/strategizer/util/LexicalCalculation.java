package com.lti.recast.recastBoTableau.strategizer.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Properties;
import java.util.Scanner;
import java.util.Set;

public class LexicalCalculation {

	public String lexicalAnalysis(String input,String Query) throws IOException {
		// TODO Auto-generated method stub
		
		File file = new File(getClass().getClassLoader().getResource("").getPath().substring(1).replaceAll("%20", " ")+"/lexical/ReplaceFunction.properties");
		FileReader reader = new FileReader(file);

		Properties p = new Properties();
		p.load(reader);
		
		HashMap<String, String> stringReplacementMap = new HashMap<>();
		
		FileInputStream fis = new FileInputStream(getClass().getClassLoader().getResource("").getPath().substring(1).replaceAll("%20", " ")+"/lexical/StringReplacement.txt");
		Scanner sc = new Scanner(fis); // file to be scanned
		// returns true if there is another line to read
		while (sc.hasNextLine()) {
			String contents = sc.nextLine(); // returns the line that was skipped
			String[] stringarray = contents.split("~");
			stringReplacementMap.put(stringarray[0], stringarray[1]);
		}
		sc.close();
		
		
	    Lexer lexer = new Lexer(input);
		
        
        System.out.println("Lexical Analysis");
		System.out.println("-----------------");
		LinkedList<String> indentifierList = new LinkedList<>();
		LinkedList<String> TK_OPEN_List = new LinkedList<>();
		StringBuilder sb = null;
		StringBuilder orig = null;
		int lastStringPosition = 0;
		int count = 0;
		int ifCounter = 0;
		int elseIfCounter = 0;
		int openCounter = 0;
		boolean finalClosureDue = false;
		boolean isIFThen = false;
		String lastToken = "";
		while (!lexer.isExausthed()) {
			count++;
			// System.out.printf("%-18s : %s \n",lexer.currentLexema() ,
			// lexer.currentToken());

			if ((lexer.currentLexema()).equalsIgnoreCase("=")) {
				if (ifCounter == 0) {// not if else then case
					if (sb != null) {
						String data = endOfLineCleanUp(finalClosureDue, elseIfCounter, sb.toString());
						finalClosureDue = false;
						elseIfCounter = 0;
						lastStringPosition = 0;
						count = 0;
						openCounter = 0;
						isIFThen = false;
						lastToken = "";
						indentifierList.clear();
						//System.err.println(orig);
						//boToBIMap.put(orig.toString(), data);
					}					
					sb = new StringBuilder();
					sb.append(lexer.currentLexema());
					
					orig = new StringBuilder();					
				} else {
					// if else case
					sb.append(lexer.currentLexema());
				}
			} else if (lexer.currentToken().name().equalsIgnoreCase("TK_SEMI")) {
				sb.append(p.getProperty(lexer.currentToken().name()));
			} else if (lexer.currentToken().name().equalsIgnoreCase("TK_PLUS")) {
				if (lastStringPosition + 1 == count) {
					sb.append(" ");
					sb.append(p.getProperty(lexer.currentToken().name()));
					sb.append(" ");
					lastStringPosition = count;
				} else {
					sb.append(lexer.currentLexema());
				}
			} else if (lexer.currentToken().name().equalsIgnoreCase("NUMBER")
					|| lexer.currentToken().name().equalsIgnoreCase("INTEGER")) {
				if (lastStringPosition + 1 == count) {
					lastStringPosition = count;
					int lastPlusPosition = sb.lastIndexOf("+");
					if (lastPlusPosition > 0 && sb.length() - lastPlusPosition <= 3) {
						sb.replace(lastPlusPosition, lastPlusPosition + 1, " & ");
					}
				}
				sb.append(lexer.currentLexema());
			} else if (lexer.currentToken().name().equalsIgnoreCase("EMPTY_STRING")) {
				sb.append(lexer.currentLexema());
			}else if (lexer.currentToken().name().equalsIgnoreCase("STRING")) {
				lastStringPosition = count;
				int lastPlusPosition = sb.lastIndexOf("+");
				if (lastPlusPosition > 0 && sb.length() - lastPlusPosition <= 3) {
					sb.replace(lastPlusPosition, lastPlusPosition + 1, " & ");
				}
				// sb.append(lexer.currentLexema());
				sb.append(stringReplacementMap.containsKey(lexer.currentLexema())
						? stringReplacementMap.get(lexer.currentLexema())
						: lexer.currentLexema());
			} else if (lexer.currentToken().name().equalsIgnoreCase("IDENTIFIER")) {
				indentifierList.addLast(lexer.currentLexema());
				if (lastToken.equalsIgnoreCase("SQUR_OPEN_BRACKET")) {
					sb.replace(sb.lastIndexOf("["), sb.length(), "(" + Query + "[");
					openCounter++;
				}
				if (lastToken.equalsIgnoreCase("IDENTIFIER") || lastToken.equalsIgnoreCase("STRING")) {
					sb.append(p.containsKey(lexer.currentLexema()) ? " " + p.getProperty(lexer.currentLexema())
							: " " + lexer.currentLexema());
				} else {
					sb.append(p.containsKey(lexer.currentLexema()) ? p.getProperty(lexer.currentLexema())
							: lexer.currentLexema());
				}
			} else if (lexer.currentToken().name().equalsIgnoreCase("TK_OPEN")) {
				sb.append(lexer.currentLexema());
				openCounter++;
				TK_OPEN_List.addLast(lastToken);

			} else if (lexer.currentToken().name().equalsIgnoreCase("TK_CLOSE")) {
				openCounter--;
				String functionName = TK_OPEN_List.getLast();
				TK_OPEN_List.removeLast();
				if (indentifierList.size() >= 1) {
					sb.append(p.containsKey(lexer.currentToken().name() + "__" + indentifierList.getLast())
							? p.getProperty(lexer.currentToken().name() + "__" + indentifierList.getLast())
							: lexer.currentLexema());
					indentifierList.removeLast();
				} else {
					sb.append(lexer.currentLexema());
				}
				if (functionName.equalsIgnoreCase("TK_NOFILTER")) {
					sb.replace(sb.lastIndexOf(")"), sb.length(), ", ALL(" + Query + "))");
				}
			} else if (lexer.currentToken().name().equalsIgnoreCase("TK_KEY_IF")) {
				ifCounter++;
				sb.append(lexer.currentLexema().trim());
				sb.append("_{" + ifCounter + "}");
				isIFThen = false;
			} else if (lexer.currentToken().name().equalsIgnoreCase("TK_KEY_THEN")) {
				sb.append(",TK_KEY_THEN");
				sb.append("_{" + ifCounter + "}");
				isIFThen = true;
				ifCounter--;
				if (ifCounter == 0 && !finalClosureDue)
					finalClosureDue = true;
			} else if (lexer.currentToken().name().equalsIgnoreCase("TK_KEY_ELSE")) {
				sb.append(",TK_KEY_ELSE");
				if (!isIFThen) {
					sb.append("_{" + ifCounter + "}");
					ifCounter--;
					if (ifCounter == 0 && !finalClosureDue)
						finalClosureDue = true; /// At END last ELSE of if loop
					isIFThen = false;
				} else {
					sb.append("_{" + (ifCounter + 1) + "}");
				}
			} else if (lexer.currentToken().name().equalsIgnoreCase("TK_KEY_ELSE_IF")) {
				sb.append(",");
				if (isIFThen) {
					ifCounter++;
				}				
				sb.append("If(");
				
				elseIfCounter++;
				finalClosureDue = true;
			} else if (lexer.currentToken().name().equalsIgnoreCase("TK_NOFILTER")) {
				sb.append(" CALCULATE");
			} else if (lexer.currentToken().name().equalsIgnoreCase("SQUR_OPEN_BRACKET")) {
				if (ifCounter > 0) {
					int lastIfPosition = sb.lastIndexOf("If");
					int lastOpenBracketPosition = sb.lastIndexOf("(");
					if (lastOpenBracketPosition > 0 && sb.length() - lastIfPosition <= 4) {
						// sb.append("(");
						sb.append(lexer.currentLexema().trim());
					} else if (sb.length() - lastIfPosition <= 3) {
						sb.append("(");
						openCounter++;
						sb.append(lexer.currentLexema().trim());
					} else {
						sb.append(lexer.currentLexema().trim());
					}
				} else {
					sb.append(lexer.currentLexema());
				}
			} else if (lexer.currentToken().name().equalsIgnoreCase("SQUR_CLOSE_BRACKET")) {
				sb.append(lexer.currentLexema());
				if (lastToken.equalsIgnoreCase("IDENTIFIER")) {
					sb.append(")");
					openCounter--;
				}
			} else if (lexer.currentToken().name().equalsIgnoreCase("TK_IS_NULL")) {
				sb.append(" ISBLANK");
			} else if (lexer.currentToken().name().equalsIgnoreCase("TK_AND")) {
				sb.append(" && ");
			} else if (lexer.currentToken().name().equalsIgnoreCase("TK_OR")) {
				sb.append(" || ");
			} else {
				sb.append(lexer.currentLexema());
			}
			// keep the last token
			lastToken = lexer.currentToken().name();
			orig.append(lexer.currentLexema());
			lexer.moveAhead();
		}

		// At End of all line, before exit
		if (lexer.isSuccessful()) {
			String data =endOfLineCleanUp(finalClosureDue, elseIfCounter, sb.toString());
			finalClosureDue = false;
			elseIfCounter = 0;
			lastStringPosition = 0;
			count = 0;
			ifCounter = 0;
			openCounter = 0;
			isIFThen = false;
			lastToken = "";
			//System.err.println(orig);
			//boToBIMap.put(orig.toString(), data);
			System.out.println(data);
			
			return data;
			//System.out.println(boToBIMap);
		} else {
			System.out.println(lexer.errorMessage());
		}
		System.out.println("");
		System.out.println("-------------------------------------------------------");
		//dilimitedData(boToBIMap);
		return "error";
	}

	private static void dilimitedData(LinkedHashMap<String, String> boToBIMap) {
		Set<String> keySet =boToBIMap.keySet();
		for(String key: keySet) {
			System.out.println(key +" ~ "+boToBIMap.get(key));
		}
	}
	/*
	 * End of line set the closing bracket, replace IF, Then, Else token etc.
	 */
	private static String endOfLineCleanUp(boolean finalClosureDue, int elseIfCounter, String str) {
		StringBuilder sb = new StringBuilder();
		sb.append(str);
		if (finalClosureDue) {
			// check the end close bracket ")" there or not
			for (int i = 0; i < elseIfCounter; i++) {
				sb.append(")");
			}
		}
		finalClosureDue = false;
		elseIfCounter = 0;
		String data = sb.toString();
		// System.out.println(data);
		String key = (data.lastIndexOf("TK_KEY_ELSE_{") > data.lastIndexOf("TK_KEY_THEN_{") ? "TK_KEY_ELSE_{" : "TK_KEY_THEN_{");

		if (key.equalsIgnoreCase("TK_KEY_THEN_{") && data.lastIndexOf("TK_KEY_THEN_{") > 0) {
			int lastThen = data.lastIndexOf("TK_KEY_THEN_{");
			String post_fix = getPostFix(data, lastThen, key);
			data = replaceLast(data, "TK_KEY_THEN" + post_fix, "TK_END_THEN" + post_fix);
			data = iFThenElseEndBracketCalculation(data, "TK_END_THEN_{");
		}
		int total = countMatches(data, key);
		// System.out.println("total "+total);
		for (int i = 0; i < total; i++) {
			data = iFThenElseEndBracketCalculation(data, key);
		}
		 if(data.lastIndexOf("TK_KEY_THEN_{") > 0) { 
			 total = countMatches(data, "TK_KEY_THEN_{");
			 data = iFThenElseEndBracketCalculation(data, "TK_KEY_THEN_{");
		}
		 
		System.err.println(data);
		return data;
	}

	/* Checks if a string is empty ("") or null. */
	public static boolean isEmpty(String s) {
		return s == null || s.length() == 0;
	}

	/* Counts how many times the substring appears in the larger string. */
	public static int countMatches(String text, String str) {
		if (isEmpty(text) || isEmpty(str)) {
			return 0;
		}

		int index = 0, count = 0;
		while (true) {
			index = text.indexOf(str, index);
			if (index != -1) {
				count++;
				index += str.length();
			} else {
				break;
			}
		}

		return count;
	}

	/**
	 * Here the If End closing bracket placed after end of word.
	 * 
	 * @param str
	 * @param key
	 * @return
	 */
	private static String iFThenElseEndBracketCalculation(String str, String key) {
		int lastElse = str.lastIndexOf(key);
		String post_fix = getPostFix(str, lastElse, key);
		// System.out.println("post_fix "+post_fix);
		int endBraces = getEndBresesPosition(str, lastElse, key);
		int wordEndPosition = getNextWord(str, endBraces);

		StringBuffer stringBuffer = new StringBuffer(str);
		if (key.equalsIgnoreCase("TK_KEY_ELSE_{")) {
			// insert() method where position of character to be
			str = stringBuffer.insert(wordEndPosition, ")").toString();
			str = replaceLast(str, "TK_KEY_ELSE" + post_fix, "");
		}
		if (key.equalsIgnoreCase("TK_END_THEN_{")) {
			str = stringBuffer.insert(wordEndPosition, ")").toString();
			str = replaceLast(str, "TK_END_THEN" + post_fix, "");
		}
		str = replaceLast(str, "TK_KEY_THEN" + post_fix, "");
		str = replaceLast(str, "If" + post_fix, "If(");
		// System.err.println(str);
		return str;
	}

	/**
	 * Extract the post fix of IF, ELse, then as _{counter} like _{1}, _{3}, _{99}
	 * Note : Token key "TK_END_THEN_{" and "TK_KEY_ELSE_{" both are 13 char. So if
	 * you add different token below number need to change. Now
	 * 
	 * @param str
	 * @param lastElse
	 * @return
	 */
	private static String getPostFix(String str, int lastElse, String key) {
		char c = str.charAt(lastElse + key.length());// 13 like "TK_END_THEN_{" and "TK_KEY_ELSE_{"
		char d = str.charAt(lastElse + key.length() + 1);// 14
		char e = str.charAt(lastElse + key.length() + 2);// 15

		if ((!Character.isDigit(d)) && (Character.toString(d).equalsIgnoreCase("}"))) {
			return (str.substring(lastElse + key.length() - 2, lastElse + key.length() + 2)); /// capture "_{0}" like
																								/// single digit
		} else if ((!Character.isDigit(e)) && (Character.toString(e).equalsIgnoreCase("}"))) {
			return (str.substring(lastElse + key.length() - 2, key.length() + 3)); /// capture "_{99}" like double digit
		}
		return null;
	}

	/**
	 * Return the position of Last Curly Braces "}" for KEY like "TK_END_THEN_{" and
	 * "TK_KEY_ELSE_{"
	 * 
	 * @param str
	 * @param lastElse
	 * @param key
	 * @return
	 */
	private static int getEndBresesPosition(String str, int lastElse, String key) {
		char d = str.charAt(lastElse + key.length() + 1);
		char e = str.charAt(lastElse + key.length() + 2);
		if ((!Character.isDigit(d)) && (Character.toString(d).equalsIgnoreCase("}"))) {
			return lastElse + key.length() + 1;
		}
		return lastElse + key.length() + 2;
	}

	/**
	 * Extract the END Word or numeric afterIF Else token. IF(A>5,1999, "DES") .
	 * Here 1999 or "DES" or (ADDD) or [FFFF] are extracted
	 * 
	 * @param str
	 * @param endBraces
	 * @return
	 */
	private static int getNextWord(String str, int endBraces) {
		int endPosition = 0;
		char d = str.charAt(endBraces + 1);
		if (Character.isWhitespace(d)) {
			getNextWord(str, endBraces + 1);
		} else {
			char a = str.charAt(endBraces + 1);
			if (endBraces + 2 < str.length()) {
				if (Character.isDigit(a)) {
					for (int i = endBraces + 2; i < str.length(); i++) {
						char x = str.charAt(i);
						if (Character.isWhitespace(x)) {
							endPosition = i;
							break;
						}
					}
					endPosition = str.length();
				} else if (a == '"') {
					for (int i = endBraces + 2; i < str.length(); i++) {
						char x = str.charAt(i);
						if (x == '"') {
							endPosition = i + 1;
							break;
						}
					}
				} else if (a == '(') {
					int count = 0;
					for (int i = endBraces + 2; i < str.length(); i++) {
						char x = str.charAt(i);
						if (x == '(') {
							count++;
						}
						if (x == ')') {
							if (count == 0) {
								endPosition = i + 1;
								break;
							} else {
								count--;
							}
						}
					}
				} else if (a == '[') {
					int count = 0;
					for (int i = endBraces + 2; i < str.length(); i++) {
						char x = str.charAt(i);
						if (x == '[') {
							count++;
						}
						if (x == ']') {
							if (count == 0) {
								endPosition = i + 1;
								break;
							} else {
								count--;
							}
						}
					}
				} else if (a == '{') {
					int count = 0;
					for (int i = endBraces + 2; i < str.length(); i++) {
						char x = str.charAt(i);
						if (x == '{') {
							count++;
						}
						if (x == '}') {
							if (count == 0) {
								endPosition = i + 1;
								break;
							} else {
								count--;
							}
						}
					}
				}

			} else {
				endPosition = str.length();
			}
		}
		// System.out.println(str.substring(endBraces + 1, endPosition));
		return endPosition;
	}

	/**
	 * Replace last match from a line.
	 * 
	 * @param string
	 * @param toReplace
	 * @param replacement
	 * @return
	 */
	public static String replaceLast(String string, String toReplace, String replacement) {
		int pos = string.lastIndexOf(toReplace);
		if (pos > -1) {
			return string.substring(0, pos) + replacement + string.substring(pos + toReplace.length());
		} else {
			return string;
		}
	}
}
