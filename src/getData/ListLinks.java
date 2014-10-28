package getData;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;

public class ListLinks {
	
	private List<String> quarterList = new ArrayList<String>();
	private List<String> collegeList = new ArrayList<String>();
	private List<String> subjectList = new ArrayList<String>();
	private List<String> courseList = new ArrayList<String>();
	private List<String> processedList = new ArrayList<String>();
	
    public void process(String url) throws IOException 
    {    	
    	Document doc = null;
        try {
        	doc = Jsoup.connect(url).userAgent("Mozilla").timeout(3000).get();
        } 
        catch(SocketTimeoutException e) {
        	//force wait and retry
        }
        
        Elements links = doc.select("a[href]");
        
        //URL is WebTms home
        if(isHome(url)){
        	for(Element link: links) {
        		String linkStr = link.attr("abs:href").toString();
        		add ( linkStr, "quarterTermDetails", quarterList);
        	}
        	
        	processedList.add(url);
        	goThrough( quarterList );
        }
        
        //URL is quarter page
        else if(url.contains("quarterTermDetails")){
        	for(Element link: links) {
        		String linkStr = link.attr("abs:href").toString();
        		add( linkStr, "collSubj", collegeList);
        	}
        	
        	processedList.add(url);
        	goThrough( collegeList );
        }
        
        //URL is college get all the subjects,
        else if( url.contains("collSubj")){  
        	for(Element link: links) {
        		String linkStr = link.attr("abs:href").toString();
        		add(linkStr, "subjectDetails", subjectList);
        	}
        	
        	processedList.add(url);
        	goThrough( subjectList );
        }
        
        //URL is major or subjects page
        else if(url.contains("subjectDetails")) {
        	for(Element link: links) {
        		String linkStr = link.attr("abs:href").toString();
        		add( linkStr, "courseDetails", courseList);
        	}        	
        	processedList.add( url );
        	goThrough( courseList );
        }
        
        else if(url.contains("courseDetails")) {
        	//We have reached our base case. Time to process the page
        	return;
        }
    	//by now we have all the subjects in the subjects list.
        print("\n=================================");
    	print("quarter size " + quarterList.size());
    	print("coll size " + collegeList.size());
    	print("subjects size " + subjectList.size());
    	print("courses size " + courseList.size());
        
    }
    
    //Helper functions
    
    //Check if list contains a URL
    private boolean isAdded(String url, List<String> list){
    	boolean isAdded = false;
    	for(int i=0; i<list.size(); i++)
    		if ( list.get(i).equals(url))
    			isAdded = true;
    	return isAdded;
    }
    
    //Add URLs with this pattern to this list
    private void add( String url, String pattern, List<String> list ){
		if( url.contains(pattern)) 
			if(! isAdded(url, list))
				list.add(url);
    }
    
    private boolean isHome(String url) {
    	if(url.equals("https://duapp2.drexel.edu/webtms_du/app")) return true;
    	else return false;
    }
    
    private void goThrough(List<String> list) throws IOException {
    	for(String temp: list)
    		if(! isAdded(temp, processedList))
    			process(temp);
    }
    
    private void print(String str){
    	System.out.println(str);
    }
          
    
}

