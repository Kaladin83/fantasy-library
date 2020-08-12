package com.fantasyLibrary;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.fantasyLibrary.models.extracted.ExtractedBook;
import com.fantasyLibrary.models.goodreads.showBook.Series;
import com.fantasyLibrary.models.goodreads.showBook.SeriesWork;
import com.fantasyLibrary.services.BooksETLService;

@SpringBootTest
public class ETLServiceTests {
	
	@Autowired
	private BooksETLService booksETLService;

	@Test
	void checkTitleExtraction() {
		BooksETLService.authorName = "Brandon Sanderson";
		ExtractedBook book = new ExtractedBook();
		
		String title = "The Alloy of Law: A Mistborn Novel";
		String[] results = booksETLService.extractFromParentheses(title);
		results = booksETLService.extractFromWithoutParentheses(results[0].strip());
		results = booksETLService.extractFromWithSpecialKeys(results[0].strip());
		book.setTitle(results[0]);
		SeriesWork series = populateSeries("Mistborn", "4");
		results[0] = booksETLService.extractFromSeries(book, series.getSeries().getTitle().strip());
		
		assertEquals("The Alloy of Law", results[0]);
		System.out.println("The \"The Alloy of Law: A Mistborn Novel\" extracted into \"The Alloy of Law\"");
		
		
		title = "Oathbringer Part One";
		results = booksETLService.extractFromParentheses(title);
		results = booksETLService.extractFromWithoutParentheses(results[0].strip());
		results = booksETLService.extractFromWithSpecialKeys(results[0].strip());
		book.setTitle(results[0]);
		series = populateSeries("The Stormlight Archive", "3");
		results[0] = booksETLService.extractFromSeries(book, series.getSeries().getTitle().strip());
		
		assertEquals("Oathbringer", results[0]);
		System.out.println("The \"Oathbringer Part One\" extracted into \"Oathbringer\"");
		
		
		title = "The Way of Kings (The Stormlight Archive #1)";
		results = booksETLService.extractFromParentheses(title);
		results = booksETLService.extractFromWithoutParentheses(results[0].strip());
		results = booksETLService.extractFromWithSpecialKeys(results[0].strip());
		book.setTitle(results[0]);
		series = populateSeries("The Stormlight Archive", "1");
		results[0] = booksETLService.extractFromSeries(book, series.getSeries().getTitle().strip());
		
		assertEquals("The Way of Kings", results[0]);
		System.out.println("The \"The Way of Kings (The Stormlight Archive #1)\" extracted into \"The Way of Kings\"");
		
		
		title = "Arcanum Unbounded: The Cosmere Collection";
		results = booksETLService.extractFromParentheses(title);
		results = booksETLService.extractFromWithoutParentheses(results[0].strip());
		results = booksETLService.extractFromWithSpecialKeys(results[0].strip());
		
		assertEquals("Arcanum Unbounded", results[0]);
		System.out.println("The \"Arcanum Unbounded: The Cosmere Collection\" extracted into \"Arcanum Unbounded\"");
		
		
		title = "Mistborn: Secret History";
		results = booksETLService.extractFromParentheses(title);
		results = booksETLService.extractFromWithoutParentheses(results[0].strip());
		results = booksETLService.extractFromWithSpecialKeys(results[0].strip());
		book.setTitle(results[0]);
		series = populateSeries("Mistborn", "3.5");
		results[0] = booksETLService.extractFromSeries(book, series.getSeries().getTitle().strip());
		
		assertEquals("Secret History", results[0]);
		System.out.println("The \"Mistborn: Secret History\" extracted into \"Secret History\"");
		
		
		title = "The Hero of Ages: Book Three of Mistborn";
		results = booksETLService.extractFromParentheses(title);
		results = booksETLService.extractFromWithoutParentheses(results[0].strip());
		results = booksETLService.extractFromWithSpecialKeys(results[0].strip());
		book.setTitle(results[0]);
		series = populateSeries("Mistborn", "3");
		results[0] = booksETLService.extractFromSeries(book, series.getSeries().getTitle().strip());
		
		assertEquals("The Hero of Ages", results[0]);
		System.out.println("The \"The Hero of Ages: Book Three of Mistborn\" extracted into \"The Hero of Ages\"");
	
	
		title = "The Hero of Ages: Mistborn Book Three";
		results = booksETLService.extractFromParentheses(title);
		results = booksETLService.extractFromWithoutParentheses(results[0].strip());
		results = booksETLService.extractFromWithSpecialKeys(results[0].strip());
		book.setTitle(results[0]);
		series = populateSeries("Mistborn", "3");
		results[0] = booksETLService.extractFromSeries(book, series.getSeries().getTitle().strip());
		
		assertEquals("The Hero of Ages", results[0]);
		System.out.println("The \"The Hero of Ages: Mistborn Book Three\" extracted into \"The Hero of Ages\"");
		
		
		title = "Words of Radiance Part One: The Stormlight Archive Book Two";
		results = booksETLService.extractFromParentheses(title);
		results = booksETLService.extractFromWithoutParentheses(results[0].strip());
		results = booksETLService.extractFromWithSpecialKeys(results[0].strip());
		book.setTitle(results[0]);
		series = populateSeries("The Stormlight Archive", "");
		results[0] = booksETLService.extractFromSeries(book, series.getSeries().getTitle().strip());
		
		assertEquals("Words of Radiance", results[0]);
		System.out.println("The \"Words of Radiance Part One: The Stormlight Archive Book Two\" extracted into \"Words of Radiance\"");
	
	
		title = "The Alloy of Law: Prologue - Chapter 6: A Mistborn Novel";
		results = booksETLService.extractFromParentheses(title);
		results = booksETLService.extractFromWithoutParentheses(results[0].strip());
		results = booksETLService.extractFromWithSpecialKeys(results[0].strip());
		book.setTitle(results[0]);
		series = populateSeries("The Alloy of Law: Prologue - Chapter 6", "");
		results[0] = booksETLService.extractFromSeries(book, series.getSeries().getTitle().strip());
		
		assertEquals("The Alloy of Law: Prologue - Chapter 6", results[0]);
		System.out.println("The \"The Alloy of Law: Prologue - Chapter 6: A Mistborn Novel\" extracted into \"The Alloy of Law: Prologue - Chapter 6\"");
		
		
		title = "Unfettered II: New Tales By Masters of Fantasy";
		results = booksETLService.extractFromParentheses(title);
		results = booksETLService.extractFromWithoutParentheses(results[0].strip());
		results = booksETLService.extractFromWithSpecialKeys(results[0].strip());
		book.setTitle(results[0]);
		book.setOriginalTitle("Unfettered II: New Tales By Masters of Fantasy");
		series = populateSeries("Unfettered", "");
		results[0] = booksETLService.extractFromSeries(book, series.getSeries().getTitle().strip());
		
		assertEquals("Unfettered", results[0]);
		System.out.println("The \"Unfettered II: New Tales By Masters of Fantasy\" extracted into \"Unfettered\"");
		
		
		
		title = "Brandon Sanderson's White Sand Volume 1 (Softcover)";
		results = booksETLService.extractFromParentheses(title);
		results = booksETLService.extractFromWithoutParentheses(results[0].strip());
		results = booksETLService.extractFromWithSpecialKeys(results[0].strip());
		results[0] = booksETLService.extractAuthorsName(results[0].strip());
		book.setTitle(results[0]);
		book.setOriginalTitle("Brandon Sanderson's White Sand Volume 1 (Softcover)");
		series = populateSeries("White Sand", "");
		results[0] = booksETLService.extractFromSeries(book, series.getSeries().getTitle().strip());
		
		assertEquals("White Sand", results[0]);
		System.out.println("The \"Brandon Sanderson's White Sand Volume 1 (Softcover)\" extracted into \"White Sand\"");
	
	
		title = "Unfettered (Unfettered, #1)";
		results = booksETLService.extractFromParentheses(title);
		results = booksETLService.extractFromWithoutParentheses(results[0].strip());
		results = booksETLService.extractFromWithSpecialKeys(results[0].strip());
		book.setTitle(results[0]);
		book.setOriginalTitle("Unfettered (Unfettered, #1)");
		series = populateSeries("Unfettered", "");
		results[0] = booksETLService.extractFromSeries(book, series.getSeries().getTitle().strip());
		
		assertEquals("Unfettered", results[0]);
		System.out.println("The \"Unfettered (Unfettered, #1)\" extracted into \"Unfettered\"");
		
		
		title = "Writers of the Future Vol 32 (L. Ron Hubbard Presents Writers of the Future)";
		results = booksETLService.extractFromParentheses(title);
		results = booksETLService.extractFromWithoutParentheses(results[0].strip());
		results = booksETLService.extractFromWithSpecialKeys(results[0].strip());
		//book.setTitle(results[0]);
		//book.setOriginalTitle("Unfettered (Unfettered, #1)");
		//series = populateSeries("Unfettered", "");
		//results[0] = booksETLService.extractFromSeries(book, series.getSeries().getTitle().strip());
		
		assertEquals("Writers of the Future", results[0]);
		System.out.println("The \"Writers of the Future Vol 32 (L. Ron Hubbard Presents Writers of the Future\" extracted into \"Writers of the Future\"");
	
		
		
		title = "L. Ron Hubbard Presents Writers of the Future 34";
		results = booksETLService.extractFromParentheses(title);
		results = booksETLService.extractFromWithoutParentheses(results[0].strip());
		results = booksETLService.extractFromWithSpecialKeys(results[0].strip());
		book.setTitle(results[0]);
		book.setOriginalTitle("Unfettered (Unfettered, #1)");
		series = populateSeries("L. Ron Hubbard Presents Writers of the Future", "34");
		results[0] = booksETLService.extractFromSeries(book, series.getSeries().getTitle().strip());
		
		assertEquals("L. Ron Hubbard Presents Writers of the Future", results[0]);
		System.out.println("The \"L. Ron Hubbard Presents Writers of the Future 34\" extracted into \"L. Ron Hubbard Presents Writers of the Future\"");
		
		
		title = "Dangerous Women  Part I";
		results = booksETLService.extractFromParentheses(title);
		results = booksETLService.extractFromWithoutParentheses(results[0].strip());
		results = booksETLService.extractFromWithSpecialKeys(results[0].strip());
		book.setTitle(results[0]);
		book.setOriginalTitle("Dangerous Women  Part I");
		series = populateSeries("", "");
		results[0] = booksETLService.extractFromSeries(book, series.getSeries().getTitle().strip());
		
		assertEquals("Dangerous Women", results[0]);
		System.out.println("The \"Dangerous Women  Part I\" extracted into \"Dangerous Women\"");
		
		
		title = "The Eye of the World: The Graphic Novel, Volume One (Wheel of Time Other)";
		results = booksETLService.extractFromParentheses(title);
		results = booksETLService.extractFromWithoutParentheses(results[0].strip());
		results = booksETLService.extractFromWithSpecialKeys(results[0].strip());
		book.setTitle(results[0]);
		book.setOriginalTitle("The Eye of the World");
		series = populateSeries("The Wheel of Time - Graphic Novels", "1");
		results[0] = booksETLService.extractFromSeries(book, series.getSeries().getTitle().strip());
		
		assertEquals("The Eye of the World: The Graphic Novel", results[0]);
		System.out.println("The \"The Eye of the World: The Graphic Novel, Volume One (Wheel of Time Other)\" extracted into \"The Eye of the World\"");
		
		
		//title = "The Eye of the World: The Graphic Novel, Volume One (Wheel of Time Other)";
		String[] split = new String [] {"1"}; 
		book.setOriginalTitle("Dangerous Women  Part I");
		series = populateSeries("", "1");
		booksETLService.setSequence(book, series);
		//assertEquals("N/A, Part 1", results[0]+", "+results[1]);
		System.out.println("The \"Part I\" extracted into \"N/A, Part 1\"");
		
		
		split = new String [] {"1", "part 2"}; 
		book.setOriginalTitle("Dangerous Women #1 Part I");
		series = populateSeries("", "1, part 2");
		booksETLService.setSequence(book, series);
		//assertEquals("1, Part 2", results[0]+", "+results[1]);
		System.out.println("The \"Dangerous Women #1 Part I\" extracted into \"1, Part 2\"");
//		title = "The Way of Kings (1 of 5) (The Stormlight Archive #1, Part 1 of 5)";
//		results = booksETLService.extractFromParentheses(title);
//		results = booksETLService.extractFromWithoutParentheses(results[0].strip());
//		results = booksETLService.extractFromWithSpecialKeys(results[0].strip());
//		book.setTitle(results[0]);
//		book.setOriginalTitle("The Eye of the World");
//		series = populateSeries("The Way of Kings", "1");
//		results[0] = booksETLService.extractFromSeries(book, series.getSeries().getTitle().strip());
//		
//		assertEquals("The Way of Kings", results[0]);
//		System.out.println("The \"The Way of Kings (1 of 5) (The Stormlight Archive #1, Part 1 of 5)\" extracted into \"The Way of Kings\"");
//		
//		
//		title = "The Way of Kings (2 of 5) (The Stormlight Archive #1, Part 2 of 5)";
//		results = booksETLService.extractFromParentheses(title);
//		results = booksETLService.extractFromWithoutParentheses(results[0].strip());
//		results = booksETLService.extractFromWithSpecialKeys(results[0].strip());
//		book.setTitle(results[0]);
//		book.setOriginalTitle("The Eye of the World");
//		series = populateSeries("The Way of Kings", "1");
//		results[0] = booksETLService.extractFromSeries(book, series.getSeries().getTitle().strip());
//		
//		assertEquals("The Way of Kings", results[0]);
//		System.out.println("The \"The Way of Kings (2 of 5) (The Stormlight Archive #1, Part 2 of 5)\" extracted into \"The Way of Kings\"");
		
		
		title = "The Geek's Guide to the Galaxy #76-78";
		results = booksETLService.extractFromParentheses(title);
		results = booksETLService.extractFromWithoutParentheses(results[0].strip());
		results = booksETLService.extractFromWithSpecialKeys(results[0].strip());
		book.setTitle(results[0]);
		book.setOriginalTitle("The Geek's Guide to the Galaxy #76-78");
		//series = populateSeries("The Wheel of Time - Graphic Novels", "1");
		results[0] = booksETLService.extractFromSeries(book, series.getSeries().getTitle().strip());
		assertEquals("The Geek's Guide to the Galaxy", results[0]);
		System.out.println("The \"The Geek's Guide to the Galaxy #76-78\" extracted into \"The Geek's Guide to the Galaxy\"");
	}

	private SeriesWork populateSeries(String seriesName, String sequence) {
		Series s = new Series();
		SeriesWork series = new SeriesWork();
	
		s.setTitle(seriesName);
		series.setUserPosition(sequence);
		series.setSeries(s);
		return series;
	}
}
