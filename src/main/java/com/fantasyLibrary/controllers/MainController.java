package com.fantasyLibrary.controllers;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fantasyLibrary.Constants;

@RestController
public class MainController {
	@Autowired
	private RestTemplate restTemplate;

	@GetMapping(value = "/books")
	public String getGoodreadsData() {
		String url = Constants.BASE_URL + "Jordan" + Constants.FORMAT + Constants.GOODREADS_KEY;

		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_XML));
		headers.setContentType(MediaType.APPLICATION_XML);
		HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);

		ResponseEntity<String> response = new ResponseEntity<String>(HttpStatus.OK);
		// response.getHeaders().setContentType(MediaType.APPLICATION_XML);

		response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
		String responseBody = (String) response.getBody();
		// GoodreadsSearchResult result = restTemplate.getForObject(url, GoodreadsSearchResult.class);

		/*try {
			url = "http://www.goodreads.com/search?q=Robert+Jordan&format=xml&key=7Y67oFVbuCdvsso1dyVdQ";
			URL obj = new URL(url);
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();
			con.setRequestMethod("GET");

			int responseCode = con.getResponseCode();

			if (responseCode == HttpURLConnection.HTTP_OK) { // success
				BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
				String inputLine;
				StringBuffer res = new StringBuffer();

				while ((inputLine = in.readLine()) != null) {
					res.append(inputLine);
				}
				in.close();
			} else {
				System.out.println("POST request not worked");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}*/
		return "";
	}
}
