package com.fantasyLibrary.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fantasyLibrary.services.AuthorsETLService;
import com.fantasyLibrary.services.BooksETLService;

@RestController
public class ETLControler {
	
	@Autowired
	private BooksETLService booksETLService;
	
	@Autowired
	private AuthorsETLService authorsETLService;

	
	@GetMapping(value = "/data/ETLAuthor")
	public void ETLAuthor() {
		authorsETLService.extractAuthors();
	}
	
	@GetMapping(value = "/data/ETLBooks")
	public void ETLBooks() {
		booksETLService.extractBooks();
	}
	
//	private void sendGET(String url) throws IOException {
//	URL obj = new URL(url);
//	HttpURLConnection con = (HttpURLConnection) obj.openConnection();
//	con.setRequestMethod("GET");
//	//con.setRequestProperty("User-Agent", USER_AGENT);
//	int responseCode = con.getResponseCode();
//	System.out.println("GET Response Code :: " + responseCode);
//	if (responseCode == HttpURLConnection.HTTP_OK) { // success
//		BufferedReader in = new BufferedReader(new InputStreamReader(
//				con.getInputStream()));
//		String inputLine;
//		StringBuffer response = new StringBuffer();
//
//		while ((inputLine = in.readLine()) != null) {
//			response.append(inputLine);
//			System.out.println(inputLine);
//		}
//		in.close();
//
//		// print result
//		System.out.println(response.toString());
//	} else {
//		System.out.println("GET request not worked");
//	}
//
//}
}
