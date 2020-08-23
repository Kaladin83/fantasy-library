package com.fantasyLibrary;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.web.client.RestTemplate;

import com.fantasyLibrary.Constants.Remove;

public class Utils {

	public static String concatenateStrings(String[] names, int endIndex, String delimiter) {
		return IntStream.range(0, endIndex)
				.mapToObj(i -> names[i])
				.collect(Collectors.joining(delimiter));
	}
	
	public static <T> T getForObject(Class<T> type, String url, RestTemplate restTemplate){
		T response = null;
		try {
			response = restTemplate.getForObject(url, type);
		}
		catch(Exception e) {
			printTime("End");
			System.out.println("URL -> "+ url);
			System.out.println(e);
		}
		return response;
	}

	public static void printTime(String txt) {
		Date d = new Date();
		SimpleDateFormat s = new SimpleDateFormat("HH:mm:ss");
		String time = s.format(d.getTime());
		System.out.println(txt+": "+time);
	}

	public static boolean isEmpty(String str) {
		return str == null || str.length() == 0;
	}

	public static String stripTitle(String title) {
		int index = title.indexOf("(");
		return index > 0? title.substring(0, index).strip(): title;
	}
	
	public static boolean containsIgnoreCase(String src, String what) {
	    final int length = what.length();
	    if (length == 0)
	        return true;

	    final char firstLo = Character.toLowerCase(what.charAt(0));
	    final char firstUp = Character.toUpperCase(what.charAt(0));

	    for (int i = src.length() - length; i >= 0; i--) {
	        // Quick check before calling the more expensive regionMatches() method:
	        final char ch = src.charAt(i);
	        if (ch != firstLo && ch != firstUp)
	            continue;

	        if (src.regionMatches(true, i, what, 0, length))
	            return true;
	    }

	    return false;
	}

	public static String removeSpecialChars(String str, Remove whereToRemove) {
		if (isEmpty(str)) {
			return str;
		}
		str = str.strip();
		int endInx = str.length() - 1;
		String start = !Character.isLetterOrDigit(str.charAt(0))? str.substring(1): str;
		String end = !Character.isLetterOrDigit(str.charAt(endInx))? str.substring(0, endInx): str;
		String both = end.equals("")? "": !Character.isLetterOrDigit(end.charAt(0))? end.substring(1): end;
		
		switch (whereToRemove) {
			case START: return start;
			case END: return end;
			default: return both;
		}
	}

	public static String capitalize(String str) {
		if (str != null && str.length() > 0) {
			return Character.toUpperCase(str.charAt(0)) + str.substring(1);		
		}
		return str;
	}

	public static String[] distructAuthorName(String name) {
		String[] names = name.split(" ");
		String firstName = Utils.concatenateStrings(names, names.length - 1, " ");
		String lastName = names[names.length - 1];
		return new String[] {firstName, lastName};
	}
}
