package getData;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class BaseCase {	
	
	private List<String> courseDetails = new ArrayList<String>();
	private List<String> otherDetails = new ArrayList<String>();
	private List<String> restrictions = new ArrayList<String>();
	private List<String> fullList = new ArrayList<String>();
	private List<List<String>> mightyList = new ArrayList<List<String>>();
	private String txtbkLink;
	private String courseD;
	private String college;
	private String department;
	private String co_req;
	private String pre_req;
	private String repeat;
	
	public void traverse(Document doc) {
		Elements theader = doc.select(".tableHeader");
		
		//get course details
		for(Element elem: theader){
			courseDetails.add(elem.nextElementSibling().text());
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
			Elements restElem = elem.siblingElements();
			for(Element current: restElem){
				if(!current.text().equals("")){
					otherDetails.add(current.text());
				}
			}
		}
		
		String[] moreInfo = extract(otherDetails);
		System.out.printf("\n%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s", 
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
				courseDetails.get(14),	//dates
				moreInfo[0],			//College
				moreInfo[1],			//Department
				moreInfo[2],			//Pre-requisites
				moreInfo[3],			//Co-requisites
				moreInfo[4],			//repeat
				moreInfo[5],			//Restrictions list
				courseD,				//Course Description
				txtbkLink				//Textbook link
				);	
		
		fullList = Arrays.asList(
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
				courseDetails.get(14),	//dates
				moreInfo[0],			//College
				moreInfo[1],			//Department
				moreInfo[2],			//Pre-requisites
				moreInfo[3],			//Co-requisites
				moreInfo[4],			//repeat
				moreInfo[5],			//Restrictions list
				courseD,				//Course Description
				txtbkLink				//Textbook link
				);	
		
		mightyList.add(fullList);		
		
	}
	
	public String[] extract(List<String> list){
		Iterator<String> item = list.iterator();
		while(item.hasNext()){
			String current = item.next();
			if(current.startsWith("Credits")){
				continue;
			}
			else if(current.startsWith("College")){
				college = current.substring(9);
			}
			else if(current.startsWith("Department")){
				department = current.substring(11);
			}
			else if(current.startsWith("Restrictions") ){
				continue;
			}
			else if(current.startsWith("Co-Requisites")){
				co_req = current.substring(14);
			}
			else if(current.startsWith("Pre-Requisites")){
				pre_req = current.substring(15);
			}
			else if(current.startsWith("Repeat")){
				repeat = current.substring(14);
			}
			else {//collect the exceptions and restrictions
				restrictions.add(current);
			}
			
		}
		String[] result = {college, department, pre_req, co_req, repeat, restrictions.toString()};		
		return result;
	}

    public List<List<String>> getMightyList() {
        return mightyList;
    }
	

}
