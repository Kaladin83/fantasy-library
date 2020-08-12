package com.fantasyLibrary.data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class Mappers {
	
	private static Set<String> acceptedLanguages = Set.of("en-US", "en-CA", "en-GB", "eng", "");
	
	private static Map<String, String> keyWords = populateKeyWords();//Set.of("part", "volume", "vol", "book", "bk.", "bk");
	
	private static Map<String, Integer> mapOfNumbers = populateMapOfNumbers();
	private static Map<String, Integer> mapOfRomanNumbers = populateMapOfRomanNumbers();

	private static List<String> listOfPublishers = List.of("abelard science fiction", "ace books", "advent:publishers",
			"aqueduct press", "arcadia house", "arkham house", "avalon science fiction", "badger books", "baen books",
			"baen ebooks", "webscriptions", "ballantine books", "bantam spectra", "berkley books", "bent agency",
			"Bison Books", "Brick Cave Media", "Canaveral press", "carcosa", "chandra press", "cheap street",
			"chimaera publications", "cosmos books", "daw books", "dark castle publishing", "del rey books",
			"donald m grant", "dragon moon press", "dobson", "double dragon publishing", "eidolon publications",
			"elastic Press", "elder signs press", "eos books", "fandemonium books", "fantasy press", "fedogan & bremer",
			"flame tree publishing", "gnome press", "golden gryphon press", "gorgon press", "the grandon company",
			"grant-hadley enterprises", "greenberg", "gregg press", "griffin publishing company", "hadley rille books",
			"harper prism", "harpercollins", "isfic press", "john hunt publishing", "jurassic london", "kayelle press",
			"lore lush publishing", "mark v ziesing", "meisha merlin publishing", "mojo press", "necronomicon press",
			"necropolitan nress", "nesfa press", "new arc books", "new collector's group",
			"newcastle publishing company", "new era", "nfff", "night shade books", "norilana books", "orb books",
			"orb publications", "orbit", "orbit books", "palliard press", "panther books", "parvus press", "phantasia press",
			"phoenix pick", "prime books", "prime press", "ps publishing", "pulphouse publishing", "pyr",
			"rainfall books", "st martin's press", "shasta publishers", "silver key press", "small beer press",
			"sphere", "subterranean press", "severed press", "tachyon publications", "ticonderoga publications",
			"timescape books", "tor ", "tom doherty", "twayne", "underwood-miller", "victor gollancz ltd", "wheatland press",
			"wildside press", "winston science fiction", "publishing mills", "doherty associates", "macdonald",
			"audio renaissance", "tandem library", "atom", "bca", " doherty", "little brown uk", "lcc",
			"legend", "new millennium press", "starscape", "Turtleback Books", "hale", "media books",
			"pinnacle books", "books on tape", "currency house", "forge books", "highbridge",
			"voyager", "harper collins", "legend paperbacks", "legend / arrow", "macmillan",
			"paw prints", "grim oak press");

	private static Map<String, Map<String, String>> mapOfPublishers = populateMap();
	public static List<String> getListOfPublishers() {
		return listOfPublishers;
	}
	
	
	private static Map<String, String> populateKeyWords() {
		Map<String, String> map = new HashMap<>();
		map.put("part", "Part");
		map.put("pt.", "Part");
		map.put("pt", "Part");
		map.put("volume", "Volume");
		map.put("vol", "Volume");
		map.put("vol.", "Volume");
		map.put("book", "Book");
		map.put("bk", "Book");
		map.put("bk.", "Book");
		return map;
	}


	private static Map<String, Integer> populateMapOfRomanNumbers() {
		Map<String, Integer> map = new HashMap<>();
		map.put("I", 1);
		map.put("II", 2);
		map.put("III", 3);
		map.put("IV", 4);
		map.put("V", 5);
		map.put("VI", 6);
		map.put("VII", 7);
		map.put("VIII", 8);
		map.put("IX", 9);
		map.put("X", 10);
		map.put("XI", 11);
		map.put("XII", 12);
		map.put("XIII", 13);
		map.put("XIV", 14);
		map.put("XV", 15);
		map.put("XVI", 16);
		map.put("XVII", 17);
		map.put("XVIII", 18);
		map.put("XIX", 19);
		map.put("XX", 1);
		map.put("XXI", 21);
		map.put("XXII", 22);
		map.put("XXIII", 23);
		map.put("XXIV", 24);
		map.put("XXV", 25);
		map.put("XXVI", 26);
		map.put("XXVII", 27);
		map.put("XXVIII", 28);
		map.put("XXIX", 29);
		map.put("XXX", 30);
		map.put("XXXI", 31);
		map.put("XXXII", 32);
		map.put("XXXIII", 33);
		map.put("XXXIV", 34);
		map.put("XXXV", 35);
		map.put("XXXVI", 36);
		map.put("XXXVII", 37);
		map.put("XXXVIII", 38);
		map.put("XXXIX", 39);
		map.put("XL", 40);
		map.put("XLI", 41);
		map.put("XLII", 42);
		map.put("XLIII", 43);
		map.put("XLIV", 44);
		map.put("XLV", 45);
		map.put("XLVI", 46);
		map.put("XLVII", 47);
		map.put("XLVIII", 48);
		map.put("XLIX", 49);
		map.put("L", 50);
		return map;
	}


	private static Map<String, Integer> populateMapOfNumbers() {
		Map<String, Integer> map = new HashMap<>();
		
		map.put("one", 1);
		map.put("two", 2);
		map.put("three", 3);
		map.put("four", 4);
		map.put("five", 5);
		map.put("six", 6);
		map.put("seven", 7);
		map.put("eight", 8);
		map.put("nine", 9);
		map.put("ten", 10);
		map.put("eleven", 11);
		map.put("twelve", 12);
		map.put("thirteen", 13);
		map.put("fourteen", 14);
		map.put("fifteen", 15);
		map.put("sixteen", 16);
		map.put("seventeen", 17);
		map.put("eighteen", 18);
		map.put("nineteen", 19);
		map.put("twenty", 1);
		map.put("twenty one", 21);
		map.put("twenty two", 22);
		map.put("twenty tree", 23);
		map.put("twenty four", 24);
		map.put("twenty five", 25);
		map.put("twenty six", 26);
		map.put("twenty seven", 27);
		map.put("twenty eight", 28);
		map.put("twenty nine", 29);
		map.put("thirty", 30);
		map.put("thirty one", 31);
		map.put("thirty two", 32);
		map.put("thirty tree", 33);
		map.put("thirty four", 34);
		map.put("thirty five", 35);
		map.put("thirty six", 36);
		map.put("thirty seven", 37);
		map.put("thirty eight", 38);
		map.put("thirty nine", 39);
		map.put("fourty", 40);
		map.put("fourty one", 41);
		map.put("fourty two", 42);
		map.put("fourty tree", 43);
		map.put("fourty four", 44);
		map.put("fourty five", 45);
		map.put("fourty six", 46);
		map.put("fourty seven", 47);
		map.put("fourty eight", 48);
		map.put("fourty nine", 49);
		map.put("fifty", 50);
		return map;
	}


	public static Map<String, Integer> getMapOfRomanNumbers() {
		return mapOfRomanNumbers;
	}


	public static Map<String, Map<String, String>> getMapOfPublishers() {
		return mapOfPublishers;
	}


	public static Set<String> getAcceptedLanguages() {
		return acceptedLanguages;
	}


	public static void setAcceptedLanguages(Set<String> acceptedLanguages) {
		Mappers.acceptedLanguages = acceptedLanguages;
	}

	public static Map<String, String> getKeyWords() {
		return keyWords;
	}


	public static void setKeyWords(Map<String, String> keyWords) {
		Mappers.keyWords = keyWords;
	}


	public static Map<String, Integer> getMapOfNumbers() {
		return mapOfNumbers;
	}


	public static void setMapOfNumbers(Map<String, Integer> mapOfNumbers) {
		Mappers.mapOfNumbers = mapOfNumbers;
	}


	private static Map<String, Map<String, String>> populateMap() {
		
		 Map<String, Map<String, String>> map = new HashMap<>();
			map.put("A", aMap());
			map.put("B", bMap());
			map.put("C", cMap());
			map.put("D", dMap());
			map.put("E", eMap());
			map.put("F", fMap());
			map.put("G", gMap());
			map.put("H", hMap());
			map.put("I", iMap());
			map.put("J", jMap());
			map.put("K", kMap());
			map.put("L", lMap());
			map.put("M", mMap());
			map.put("N", nMap());
			map.put("O", oMap());
			map.put("P", pMap());
			map.put("R", rMap());
			map.put("S", sMap());
			map.put("T", tMap());
			map.put("U", uMap());
			map.put("V", vMap());
			map.put("W", wMap());
	
		return map;
	}
	
	private static Map<String, String> aMap() {
		Map<String, String> map = new HashMap<>();
		
		map.put("Abelard Science Fiction", "abelard science fiction");
		map.put("Ace Books", "ace books");
		map.put("Advent:Publishers", "advent:publishers");
		map.put("Aqueduct Press", "aqueduct press");
		map.put("Arcadia house", "arcadia house");
		map.put("Arkham House", "arkham house");
		map.put("Atom", "atom");
		map.put("Audio Renaissance", "audio renaissance");	 
		map.put("Avalon Science Fiction", "avalon science fiction");
		return map;
	}
	
	private static Map<String, String> bMap() {
		Map<String, String> map = new HashMap<>();
		
		map.put("Badger Books", "badger books");
		map.put("Baen Books", "baen");
		map.put("Ballantine Books", "ballantine books");
		map.put("Bantam Spectra", "bantam spectra");
		map.put("BCA", "bca");
		map.put("Berkley Books", "berkley books");
		map.put("Bent Agency", "bent agency");
		map.put("Bison Books", "bison books");
		map.put("books on tape", "books on tape");
		map.put("Brick Cave Media", "brick cave");
		return map;
	}

	private static Map<String, String> cMap() {
		Map<String, String> map = new HashMap<>();
		
		map.put("Canaveral Press", "canaveral press");
		map.put("Carcosa", "carcosa");
		map.put("Chandra Press", "chandra press");
		map.put("Cheap Street", "cheap street");
		map.put("Chimaera Publications", "chimaera publications");
		map.put("Cosmos Books", "cosmos books");
		map.put("Currency House", "currency house");
		return map;
	}

	private static Map<String, String> dMap() {
		Map<String, String> map = new HashMap<>();
		
		map.put("Daw Books", "daw books");
		map.put("Dark Castle Publishing", "dark castle publishing");
		map.put("Del Rey Books", "del rey books");
		map.put("Donald M. Grant", "donald m grant");
		map.put("Tom Doherty Associates", "doherty");
		map.put("Dragon Moon Press", "dragon moon press");
		map.put("Dobson", "dobson");
		map.put("Double Dragon Publishing", "double dragon publishing");
		return map;
	}

	private static Map<String, String> eMap() {
		Map<String, String> map = new HashMap<>();
		
		map.put("Eidolon Publications", "eidolon publications");
		map.put("Elastic Press", "elastic Press");
		map.put("Elder Signs Press", "elder signs press");
		map.put("EOS books", "eos books");
		return map;
	}

	private static Map<String, String> fMap() {
		Map<String, String> map = new HashMap<>();
		
		map.put("Fandemonium Books", "fandemonium books");
		map.put("Fantasy Press", "fantasy press");
		map.put("Fedogan & Bremer", "fedogan & bremer");
		map.put("Flame Tree Publishing", "flame tree");
		map.put("Forge books", "forge books");
		return map;
	}

	private static Map<String, String> gMap() {
		Map<String, String> map = new HashMap<>();
		
		map.put("Gnome Press", "gnome press");
		map.put("Golden Gryphon Press", "golden gryphon");
		map.put("Gorgon Press", "gorgon press");
		map.put("Grim Oak Press", "grim oak");
		map.put("Grant-Hadley Enterprises", "grant hadley");
		map.put("Greenberg", "greenberg");
		map.put("Gregg Press", "gregg press");
		map.put("Griffin Publishing", "griffin publishing");
		return map;
	}

	private static Map<String, String> hMap() {
		Map<String, String> map = new HashMap<>();
		
		map.put("Hadley Rille Books", "hadley rille");
		map.put("Hale", "hale");
		map.put("Harper Prism", "harper prism");
		map.put("Harper Collins", "harper collins");
		map.put("Highbridge", "highbridge");
		return map;
	}

	private static Map<String, String> iMap() {
		Map<String, String> map = new HashMap<>();
		
		map.put("ISFIC Press", "isfic press");
		return map;
	}

	private static Map<String, String> jMap() {
		Map<String, String> map = new HashMap<>();
		
		map.put("John Hunt Publishing", "john hunt publishing"); 
		map.put("Jurassic London", "jurassic london");
		return map;
	}
	private static Map<String, String> kMap() {
		Map<String, String> map = new HashMap<>();
		
		map.put("Kayelle Press", "kayelle press");
		return map;
	}
	
	private static Map<String, String> lMap() {
		Map<String, String> map = new HashMap<>();
		
		map.put("LCC", "lcc");
		map.put("Legend", "legend");
		map.put("Legend Paperbacks", "legend paperbacks");
		map.put("Little Brown UK", "little brown uk");
		map.put("Lore Lush Publishing", "lore lush");
		return map;
	}
	private static Map<String, String> mMap() {
		Map<String, String> map = new HashMap<>();
		
		map.put("Macdonald", "macdonald");
		map.put("Macmillan Audio", "macmillan");
		map.put("Mark V. Ziesing", "mark v ziesing");
		map.put("Meisha Merlin publishing", "meisha merlin");
		map.put("Media books", "media books");
		map.put("Mojo Press", "mojo press");
		return map;
	}
	private static Map<String, String> nMap() {
		Map<String, String> map = new HashMap<>();
		
		map.put("Necronomicon Press", "necronomicon");
		map.put("Nesfa Press", "nesfa press");
		map.put("New Arc Books", "new arc");
		map.put("New Collector's Group", "new collector");
		map.put("Newcastle Publishing company", "newcastle publishing");
		map.put("New Era", "new era");
	    map.put("New Millennium Press", "new millennium");
		map.put("N.F.F.F", "nfff");
		map.put("Night Shade Books", "night shade");
		map.put("Norilana Books", "norilana books");
		return map;
	}
	private static Map<String, String> oMap() {
		Map<String, String> map = new HashMap<>();
		
		map.put("Orb Books", "orb ");
		map.put("Orbit Books", "orbit");
		return map;
	}
	private static Map<String, String> pMap() {
		Map<String, String> map = new HashMap<>();
		
		map.put("Palliard Press", "palliard press");
		map.put("Panther Books", "panther books");
		map.put("Paw Prints", "paw prints");
		map.put("Parvus Press", "parvus press");
		map.put("Pinnacle Books", "parvus press");	 
		map.put("Phantasia Press", "phantasia press");
		map.put("phoenix pick", "phoenix pick");
		map.put("Prime Books", "prime ");
		map.put("PS Publishing", "ps publishing");
		map.put("Publishing Mills", "publishing mills");
		map.put("Pulphouse Publishing", "pulphouse");
		map.put("Pyr", "pyr");
		return map;
	}
	private static Map<String, String> rMap() {
		Map<String, String> map = new HashMap<>();
		
		map.put("Rainfall Books", "rainfall");
		return map;
	}
	private static Map<String, String> sMap() {
		Map<String, String> map = new HashMap<>();
		
		map.put("St. Martin's Press", "st martin's press");
		map.put("Saint Martin's Press", "saint martin's press");
		map.put("Shasta Publishers", "shasta publishers");
		map.put("Silver Key Press", "silver key");
		map.put("Small Beer Press", "small beer");
		map.put("Starscape", "starscape");
		map.put("Sphere", "sphere");
		map.put("Subterranean Press", "subterranean");
		map.put("Severed press", "severed press");
		return map;
	}
	private static Map<String, String> tMap() {
		Map<String, String> map = new HashMap<>();
		
		map.put("Tandem Library", "tandem library");
		map.put("Tachyon Publications", "tachyon publications");
		map.put("The Grandon Company", "the grandon company");
		map.put("Ticonderoga Publications", "ticonderoga");
		map.put("Timescape Books", "timescape");
		map.put("Tor Books", "tor ");
		map.put("Turtleback Books", "turtleback ");
		map.put("Twayne", "twayne");
		return map;
	}
	private static Map<String, String> uMap() {
		Map<String, String> map = new HashMap<>();
		
		map.put("Underwood-Miller", "underwood");
		return map;
	}
	private static Map<String, String> vMap() {
		Map<String, String> map = new HashMap<>();
		
		map.put("Victor Gollancz Ltd.", "victor gollancz");
		map.put("Voyager", "voyager");
		return map;
	}
	private static Map<String, String> wMap() {
		Map<String, String> map = new HashMap<>();
		
		map.put("Webscriptions", "webscriptions");
		map.put("Wheatland Press", "wheatland");
		map.put("Wildside press", "wildside");
		map.put("Winston Science Fiction", "Winston ");
		return map;
	}
}
