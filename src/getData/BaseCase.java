package getData;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class BaseCase {
	
	Course course = new Course();
	private List<String> courseDetails = new ArrayList<String>();
	String txtbkLink;
	
	public void traverse(Document doc) {
		Elements theader = doc.select(".tableHeader");
		
		//get course details
		for(Element elem: theader){
			String next = elem.nextElementSibling().text();
			next = trim(next, 20);
			courseDetails.add(next);
		}
		
		//get textbook link
		Elements links = doc.select("a[href]");
		for(Element elem: links) {
			String temp = elem.attr("abs:href").toString();
			if(temp.contains("bncollege.com")){
				txtbkLink = temp;
			}
		}
		
		//get course description
		
		
		System.out.printf("%s\t%s\t%s\t%s\t%s\t%20s\t%20s\t%20s\t%15s\t%s\t%s\t%s\t%s\t%s\t%s", 
				courseDetails.get(0), 	//CRN
				courseDetails.get(1), 	//subject code
				courseDetails.get(2),	//course number
				courseDetails.get(3),	//section
				courseDetails.get(4),	//credit
				courseDetails.get(5),	//title
				courseDetails.get(6),	//campus
				courseDetails.get(7),	//instructor
				courseDetails.get(8),	//instruction type
				courseDetails.get(9),	//instruction method
				courseDetails.get(10),	//Max Enroll
				courseDetails.get(11),	//Enroll
				courseDetails.get(12),	//Section comments
				courseDetails.get(13),	//Textbooks
				courseDetails.get(14)	//dates
				);	
		
		
	}
	
	public void print(String str){
		System.out.println(str);
	}
	
	private static String trim(String s, int width) {
        if (s.length() > width)
            return s.substring(0, width-6) + "...";
        else
            return s;
    }


}
