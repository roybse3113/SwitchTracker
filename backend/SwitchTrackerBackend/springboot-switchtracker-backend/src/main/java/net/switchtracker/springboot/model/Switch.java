package net.switchtracker.springboot.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class SwitchParser {
    private LinkedHashMap<Switch,String> allSwitches;
//    private HashMap<String,Switch> allSwitches;
    private LinkedHashMap<String,String> switchLinks;
    private List<String> allPages = new LinkedList<String>();
    
	public SwitchParser() {
		allPages.add("https://ringerkeys.com/collections/switches/switches?sort_by=best-selling");
		allPages.add("https://www.primekb.com/collections/switches");
	    allPages.add("https://novelkeys.xyz/collections/switches?page=1&sort_by=best-selling");
	    
//	    Parser p = new Parser("https://novelkeys.xyz/collections/switches/products/the-silk-series");
//      p.getStockStatus(allSwitches);
	    switchLinks = new LinkedHashMap<String,String>();
	    this.allSwitches = new LinkedHashMap<Switch,String>();
	    
	    //Get all the links for each switch page
	    parseAllSwitches();
	    updateSwitchStatus();
	    
//	    for (Map.Entry<Switch, String> entry : allSwitches.entrySet()) {
//          System.out.println(entry.getKey().getName() + ": " + entry.getKey().getStockStatus());
//          System.out.println("subSwitches: ");
//          for (Switch s : entry.getKey().getSubSwitches()) {
//              System.out.println(s.getName() + ": " + s.getStockStatus());
//          }
//          System.out.println("--------------------------");
//	    }
	}
	
	public void parseAllSwitches() {
	    Pattern p = Pattern.compile("(.*)\\/collections");
	    Matcher m;
	    List<Integer> visitedPages = new ArrayList<Integer>();
	    LinkedHashMap<Integer,String> links = new LinkedHashMap<Integer,String>();
	    for (String url: allPages) {
	        
	        //Establish the base URL
	        m = p.matcher(url);
	        String baseURL = "";
	        if (m.find()) {
	            baseURL = m.group(1);
	        }
	        
	      //initalize the map such that it is not empty
	        links.put(-1, null);
	        while (!links.isEmpty()) {
//	          System.out.println(links);
	            
	            Parser parse = new Parser(url);
	            links = parse.getArticles(baseURL, allSwitches,switchLinks,links);
	            
//	          System.out.println(visitedPages);
	            
	            for (Map.Entry<Integer, String> entry : links.entrySet()) {
//	              System.out.println("Entry : " + entry.getKey());
	                if (!visitedPages.contains(entry.getKey())) {
	                    visitedPages.add(entry.getKey());
	                    System.out.println(entry.getValue());
	                    url = baseURL + entry.getValue();
//	                    System.out.println("URL: " + url);
	                } 
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
	
	public LinkedHashMap<String,String> getSwitchLinks() { 
	    return switchLinks;
	}
	
	public LinkedHashMap<Switch,String> getAllSwitches() {
		return allSwitches;
	}

}
