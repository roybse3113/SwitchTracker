package net.switchtracker.springboot.model;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {

    private String baseURL;
    public Document currentDoc;
    

    /*
     * Constructor that initializes the base URL and loads the document produced
     * from that URL
     */
    public Parser() {
    }

    public Parser(String url) {
        this.baseURL = url;
        try {
            this.currentDoc = Jsoup.connect(this.baseURL).get();
            // System.out.println(this.currentDoc);
        } catch (IOException e) {
            // System.out.println("Could not get the corgis :(");
        }

    }

    /*
     * Creates article map to be a mapping of article titles to url from our current
     * doc
     */
    public LinkedHashMap<Integer,String> getArticles(String baseURL, LinkedHashMap<Switch,String> allSwitches, LinkedHashMap<String,String> articleMap, LinkedHashMap<Integer,String> links) {
        Pattern p = Pattern.compile("\\/collections\\/switches\\?page=(\\d{1})");
        Matcher m;
        LinkedHashMap<Integer,String> newLinks = new LinkedHashMap<Integer,String>();
        Elements articleElements = this.currentDoc.select("a"); // gets all elements of type article
        for (Element article : articleElements) {
            Elements aTag = article.select("a"); // links come in <a> tags typically

            // Element a = aTag.get(0);

            //System.out.println("aTag: " + aTag);
            for (Element a : aTag) {
                //System.out.println("a: " + a);

                String articleURL = a.attr("href");
                String articleTitle = a.text();
                
                m = p.matcher(articleURL);
                //Checks for multiple pages
                if (m.find()) {
                    System.out.println(m.group(1));
                    int pageNumber = Integer.parseInt(m.group(1));
//                    System.out.println(pageNumber);
                    if (!links.containsKey(pageNumber)) {
                        newLinks.put(pageNumber, articleURL);
                    }
                }
                
              //Adds the pages for each switch
                if (articleURL.contains("collections/switches/")) {
                    System.out.println(articleURL);
                    if (!allSwitches.containsValue(baseURL + articleURL)) {
                        Pattern pRegularPrice = Pattern.compile("(.*)Regular");
                        Pattern pPrice = Pattern.compile("(.*)([^A-z\\s\\d][\\\\\\^]?)\\d*([^A-z\\s\\d][\\\\\\^]?)\\d*");
                        
                        //Accounts for case when title contains a numeric price or 'Regular Price' for primekb
                        m = pRegularPrice.matcher(articleTitle);
                        if (m.find()) {
                            articleTitle = m.group(1).trim();
                        } else {
                            m = pPrice.matcher(articleTitle);
                            if (m.find()) {
                                articleTitle = m.group(1).trim();
                            }
                        }
//                        
                        
                        allSwitches.put(new Switch(articleTitle), baseURL + articleURL);
                    }
//                    articleMap.put(articleTitle, "https://novelkeys.xyz" + articleURL);
//                    allSwitches.put(articleTitle, new Switch(articleTitle));
                }
                
                // System.out.println(articleTitle);
                // System.out.println();

            }
        }
        return newLinks;

    }
    
    public boolean validStringToInteger(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    public void getStockStatus(Switch s) {
//        System.out.println("-----------------");
//        System.out.println("SWITCH: " + s.getName());
        //Keep track of the names of switches already seen (sub-switch names)
        HashMap<String,Switch> subSwitches = new HashMap<String,Switch>();
        Pattern p = Pattern.compile("(\\D*)");
        Matcher m;
        String stockCheck = "product-form__variants no-js";
        
        Elements selectElements = this.currentDoc.select("select");
        for (Element select : selectElements) {
            if (select.attr("class").equals(stockCheck)) {
                Elements optionElements = select.children();
                
                for (Element option : optionElements) {
                    String currSwitch = option.text().trim();
//                    System.out.println(currSwitch);
                    m = p.matcher(currSwitch);
                    boolean stockStatus = !currSwitch.contains("Sold out");
                    //If any count/sub-switch of the main switch is in stock, the main switch is
                    //set to be in stock since there are available variants/options for the switch
                    if (!s.isInStock()) {
                        if (stockStatus) {
                            s.setInStock(true);
                        }
                    }
                    
                    //Filter the name of the sub-switch accordingly
                    String subName = currSwitch;
                    for (int i = 0; i < subName.length(); i++) {
                        char c = subName.charAt(i);
                        if (c != ' ' && !Character.isLetter(c)) {
                            subName = subName.substring(0, i).trim();
                            break;
                        }
                    }
                    
                    
                    //Case 3 for default title is unique for Gateron Sample Pack which does not have sub-switches
                    
                    //Case 2 for if there is no string (name of the sub-switches) and only at most a count
                    
                    //Case 1 for text: "# - Sold out"
                    
                    if (subName.equals("") || !m.find() || m.group(1).contains("Default Title")) {
                        //Only change stock status if it is currently false
                        //since we want to set stock status as true if any count is in stock
                        if (!s.isInStock()) {
                            if (!currSwitch.contains("Sold out")) {
                                //If at any point/count the switch is available, set as in stock
                                //This is the case where there are no sub-switches
                                s.setInStock(true);
                            }
                        }
                        //If no sub-switch, no need to make sub-switch objects
                    } else {
                        //Need to consider sub-switch objects and if each are sold out
                        //Add a sub-switch to the main switch
                        
                        
//                        System.out.println(subName);
                        if (subSwitches.get(subName) == null) {
                            Switch sub = new Switch(subName);
                            subSwitches.put(subName,sub);
                            //Update the stock status of the sub-switch
                            
                            sub.setInStock(!currSwitch.contains("Sold out"));
//                            System.out.println("In Stock?: " + sub.getStockStatus());
//                            s.setSubSwitches(sub);
                        } else {
                            //If at any point a sub-switch for some count is in stock, set it as in stock
                            //and break the loop for the current sub-switch\
                            Switch sub = subSwitches.get(subName);
                            if (!sub.isInStock()) {
                                if (!currSwitch.contains("Sold out")) {
                                    sub.setInStock(true);
                                }
                            }
//                            System.out.println("In Stock?: " + sub.getStockStatus());
                        }
                        
                    }
                }
            }
        }
    }
    

    
}
