package org.caalpeva.starwars.ws.dto;

import java.io.Serializable;
import java.util.List;

public class PageDTO<T> implements Serializable {
	
	private static final long serialVersionUID = 2745309082630597947L;
	
	public int count;
    public String next;
    public String previous;
    public List<T> results;

    public boolean hasMore() {
        return (next != null && !next.isEmpty());
    }
}