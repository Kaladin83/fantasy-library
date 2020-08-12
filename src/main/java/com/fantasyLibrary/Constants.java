package com.fantasyLibrary;

public class Constants {
	public static String BOXED_SET = "Boxed Set"; 
	public enum Remove{
		START, END , BOTH;
	}
	
	public enum Url{
		BASE_GOODREADS_SEARCH("https://www.goodreads.com/search/?search[field]=author&q="), 
		BASE_GOODREADS_AUTHOR("https://www.goodreads.com/author/list.xml?id="),
		BASE_GOODREADS_SHOW_AUTHOR("https://www.goodreads.com/author/show/"),
		BASE_GOODREADS_SHOW_BOOK("https://www.goodreads.com/book/show/"),
		BASE_OPEN_LIBRARY_SEARCH("https://openlibrary.org/search.json?title="),
		BASE_OPEN_LIBRARY_AUTHOR_SEARCH("https://openlibrary.org/search.json?author="),
		BASE_OPEN_LIBRARY_ISBN_SEARCH("https://openlibrary.org/api/volumes/brief/json/isbn:"),
		GR_AUTHOR_PAGE_PREFIX("https://www.goodreads.com/author/show/"),
		GR_BOOK_PAGE_PREFIX("https://www.goodreads.com/book/show/");
		
		private String url;
		
		private Url(String url) {
			this.url = url;
		}
		
		public String value() {
			return url;
		}
	}
	
	public enum Ext{
		GOODREADS_KEY("&key=7Y67oFVbuCdvsso1dyVdQ"), 
		GOODREADS_SECRET("9ApVEVYGynbbws52AFCbURdYNhMwPgjETXjhE460"),
		FORMAT("&format=xml"),
		AUTHOR("&author="),
		OFFSET("&offset="),
		LIMIT("&limit="),
		PER_PAGE("&per_page=");
		
		private String extentioin;
		
		private Ext(String ext) {
			this.extentioin = ext;
		}
		
		public String value() {
			return extentioin;
		}
	}
	
	public enum Cover{
		GR_PREFIX("https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/"),
		GR_SUFIX("._SX98_.jpg"),
		GR_SUFIX_CHANGE("._SX250_.jpg"),
		OL_PREFIX("https://covers.openlibrary.org/b/id/"),
		OL_SUFIX("-L.jpg"),
		GR_AUTHOR_PREFIX("https://images.gr-assets.com/authors/");
		
		private String val;
		
		private Cover(String val) {
			this.val = val;
		}
		
		public String value() {
			return val;
		}
	}
}
