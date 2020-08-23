package com.fantasyLibrary.models.response;

public class Genre implements Comparable<Genre>{
	
	public Genre(String name, int count) {
		super();
		this.name = name;
		this.count = count;
	}

	private String name;
	
	private int count;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	@Override
	public int compareTo(Genre o) {
		return this.getCount() > o.getCount()? 1: -1; 
	}
}
