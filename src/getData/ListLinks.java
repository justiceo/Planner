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
        	add ( links, "quarterTermDetails", quarterList);
        	processedList.add( url );
        	goThrough( quarterList );
        }
        
        //URL is quarter page
        else if(url.contains("quarterTermDetails")){
        	add( links, "collSubj", collegeList);
        	processedList.add( url );
        	goThrough( collegeList );
        }
        
        //URL is college get all the subjects,
        else if( url.contains("collSubj")){
        	add(links, "subjectDetails", subjectList);
        	processedList.add( url );
        	goThrough( subjectList );
        }
        
        //URL is major or subjects page
        else if(url.contains("subjectDetails")) {
        	add( links, "courseDetails", courseList);
        	processedList.add( url );
        	goThrough( courseList );
        }
        
        //We have reached our base case. Time to process the page
        else if(url.contains("courseDetails")) {
        	processedList.add( url );
        	BaseCase page = new BaseCase();
        	page.traverse(doc);
        	return;
        }
        
        //place "return" in any case you do not wish to run statements below
    	//by now we have all the subjects in the subjects list.
        print("\n=================================");
    	/*print("quarter size " + quarterList.size());
    	print("coll size " + collegeList.size());
    	print("subjects size " + subjectList.size());
    	print("courses size " + courseList.size());*/
        
    }
    
    //Helper functions
    
    
    //Add links with this pattern to this list
    private void add( Elements links, String pattern, List<String> list ){
    	for(Element link: links) {
    		String url = link.attr("abs:href").toString();
	    	if( url.contains(pattern) && ! list.contains(url)) 	    		
				list.add(url);
    	}
    }
    
    private boolean isHome(String url) {
    	if(url.equals("https://duapp2.drexel.edu/webtms_du/app")) return true;
    	else return false;
    }
    
    private void goThrough(List<String> list) throws IOException {
    	for(String temp: list)
    		if(! processedList.contains(temp))
    			process(temp);
    }
    
    private void print(String str){
    	System.out.println(str);
    }
          
    
}

