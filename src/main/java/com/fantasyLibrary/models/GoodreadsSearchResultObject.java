package com.fantasyLibrary.models;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "work")
public class GoodreadsSearchResultObject {

	List<WorkObject> work;
}
