package getData;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class BaseCase {	
	
	private List<String> courseDetails = new ArrayList<String>();
	private List<String> otherDetails = new ArrayList<String>();
	private String txtbkLink;
	private String courseD;
	private String college;
	private String department;
	private String enroll_rest;
	private String co_req;
	private String pre_req;
	private String repeat;
	
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
		Elements cDescs = doc.select("div.courseDesc");
		for(Element elem: cDescs) {
			courseD = elem.text();
			Element temp = elem.nextElementSibling();
			college = temp.nextElementSibling().text();
			temp = temp.nextElementSibling();
			department = temp.nextElementSibling().text();
			temp = temp.nextElementSibling();
			temp = temp.nextElementSibling();
			temp = temp.nextElementSibling();
			enroll_rest = temp.nextElementSibling().text();
			temp = temp.nextElementSibling();
			if(temp.nextElementSibling().text().equals(""))
				temp = temp.nextElementSibling();
			co_req = temp.nextElementSibling().text();
			temp = temp.nextElementSibling();
			pre_req = temp.nextElementSibling().text();
			temp = temp.nextElementSibling();
			repeat = temp.nextElementSibling().text();
			/*do {
				temp = temp.nextElementSibling();
				if (temp.text().equals("")){
					temp = temp.nextElementSibling();
				}
				else {
					otherDetails.add(temp.text());
				}
			}while()
			
			*/
		}
		
		/*
		System.out.printf("%s\t%s\t%s\t%s\t%s\t%20s\t%20s\t%20s\t%15s\t%s\t%s\t%s\t%s\t%s\t%s\n", 
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
		
		System.out.println(courseD);
		System.out.println(college + "\n" + department + "\nen " + enroll_rest + "\nco " + co_req + "\npre " + pre_req + "\nrep " + repeat);
		
		*/
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
