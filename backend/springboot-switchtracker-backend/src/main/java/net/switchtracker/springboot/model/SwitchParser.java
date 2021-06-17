package net.switchtracker.springboot.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class SwitchParser {
    private HashMap<Switch,String> allSwitches;
//    private HashMap<String,Switch> allSwitches;
    private HashMap<String,String> switchLinks;
    
	public SwitchParser() {
//	    Parser p = new Parser("https://novelkeys.xyz/collections/switches/products/the-silk-series");
//      p.getStockStatus(allSwitches);
	    switchLinks = new HashMap<String,String>();
	    this.allSwitches = new HashMap<Switch,String>();
	    
	    //Get all the links for each switch page
	    parseAllSwitches();
	    updateSwitchStatus();
	    
	    for (Map.Entry<Switch, String> entry : allSwitches.entrySet()) {
          System.out.println(entry.getKey().getName() + ": " + entry.getKey().isInStock());
//          System.out.println("subSwitches: ");
//          for (Switch s : entry.getKey().getSubSwitches()) {
//              System.out.println(s.getName() + ": " + s.isInStock());
//          }
          System.out.println("--------------------------");
	    }
	}
	
	public HashMap<Switch,String> getAllSwitches() {
		return allSwitches;
	}
	
	public void parseAllSwitches() {
	    List<Integer> visitedPages = new ArrayList<Integer>();
	    HashMap<Integer,String> links = new HashMap<Integer,String>();
	    String url = "https://novelkeys.xyz/collections/switches";
	    
	    //initalize the map such that it is not empty
	    links.put(-1, null);
	    while (!links.isEmpty()) {
//	        System.out.println(links);
	        
	        Parser parse = new Parser(url);
	        links = parse.getArticles(allSwitches,switchLinks,links);
	        
//	        System.out.println(visitedPages);
	        
	        for (Map.Entry<Integer, String> entry : links.entrySet()) {
//	            System.out.println("Entry : " + entry.getKey());
                if (!visitedPages.contains(entry.getKey())) {
                    visitedPages.add(entry.getKey());
                    url = "https://novelkeys.xyz" + entry.getValue();
//                    System.out.println("URL: " + url);
                } 
            }
	    }
//	    System.out.println(allSwitches.size());
//	    for (Map.Entry<Switch, String> entry : allSwitches.entrySet()) {
//	        System.out.println(entry.getKey().getName() + ": " + entry.getValue());
//	    }
//	    System.out.println(allSwitches);
	}
	
	public void updateSwitchStatus() {
	    for (Map.Entry<Switch, String> entry : allSwitches.entrySet()) {
	        Parser parse = new Parser(entry.getValue());
	        parse.getStockStatus(entry.getKey());
	    }
	}
	
	public HashMap<String,String> getSwitchLinks() { 
	    return switchLinks;
	}

}