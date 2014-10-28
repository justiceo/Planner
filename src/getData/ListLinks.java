package getData;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
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
        
        Document doc = Jsoup.connect(url).get();
        Elements links = doc.select("a[href]");
        
        //URL is WebTms home
        if(isHome(url)){
        	for(Element link: links) {
        		String linkStr = link.attr("abs:href").toString();
        		
        		if( linkStr.contains("quarterTermDetails")){
        			if(! isAdded(linkStr, quarterList)){
        				quarterList.add(linkStr);
        			}
        		}
        	}
        	
        	processedList.add(url);
        	
        	//Now that we have a list of all the Quarters...
        	for(String temp: quarterList) {
        		if(! isAdded(temp, processedList)) {
        			process(temp);
        		}
        	}
        }
        
        //URL is quarter page
        else if(url.contains("quarterTermDetails")){
        	for(Element link: links) {
        		String linkStr = link.attr("abs:href").toString();
        		
        		if( linkStr.contains("collSubj")){
        			if(! isAdded(linkStr, collegeList)){
        				collegeList.add(linkStr);
        			}
        		}        			
        	}
        	
        	processedList.add(url);
        	
        	//Now that we have a list of all the colleges...
        	for(String temp: collegeList){
        		if(! isAdded(temp, processedList)){
        			process(temp);
        		}
        	}
        }
        
        //URL is college get all the subjects,
        else if( url.contains("collSubj")){        	
        	//extract all the colleges and subject 
        	for(Element link: links) {
        		String linkStr = link.attr("abs:href").toString();
        		
        		if(linkStr.contains("subjectDetails")) {
        			if(! isAdded(linkStr, subjectList)){
        				subjectList.add(linkStr);
        			}
        		}
        	}
        	
        	processedList.add(url);
        	
        	//Now that we have a list of all the subjects...
        	for(String temp: subjectList) {
        		if(! isAdded(temp, processedList)){
        			process(temp);
        		}
        	}
        }
        
        //URL is major or subjects page
        else if(url.contains("subjectDetails")) {
        	for(Element link: links) {
        		String linkStr = link.attr("abs:href").toString();
        		
        		//if it is a course page
        		if( linkStr.contains("courseDetails")) {
        			if(! isAdded(linkStr, courseList)){
        				courseList.add(linkStr);
        			}
        		}
        	}        	
        	processedList.add(url);
        	
        	//Now we have a list of all the courses
        	for(String temp: courseList) {
        		if(! isAdded(temp, processedList)){
        			process(temp);
        		}
        	}
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
    
    private boolean isAdded(String str, List<String> list){
    	boolean isAdded = false;
    	for(int i=0; i<list.size(); i++) {
    		if ( list.get(i).equals(str)) {
    			isAdded = true;
    		}
    	}
    	return isAdded;
    }
    
    private boolean isHome(String url) {
    	if(url.equals("https://duapp2.drexel.edu/webtms_du/app")) {
    		return true;
    	}
    	else return false;
    }
    
    private void print(String str){
    	System.out.println(str);
    }
          
    
}

