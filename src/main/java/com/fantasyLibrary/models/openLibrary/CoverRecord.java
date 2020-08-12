package com.fantasyLibrary.models.openLibrary;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;

public class CoverRecord {

    @JsonAnySetter
    private Map<String, BookNum> booksNum = new HashMap<>();
    
    @JsonAnyGetter
    public Map<String, BookNum> getBooksNum() {
		return booksNum;
	}
}
