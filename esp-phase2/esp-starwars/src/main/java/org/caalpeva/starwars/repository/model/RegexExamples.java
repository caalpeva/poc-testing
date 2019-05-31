package org.caalpeva.starwars.repository.model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.caalpeva.starwars.ws.api.StarWarsEndpoint;

public class RegexExamples {
    private static final Pattern p = Pattern.compile(StarWarsEndpoint.PLANETS + "\\[0-9]+");
    public static void main(String[] args) {
    	System.out.println("fsdfssdf");
        // create matcher for pattern p and given string
        Matcher m = p.matcher("https://swapi.co/api/planets/1/");

        // if an occurrence if a pattern was found in a given string...
        if (m.find()) {
            System.out.println(m.group(1)); // second matched digits
        }
    	System.out.println("fsdfssdf");
    }
}