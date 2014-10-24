package getData;

public class Course {
	
	private String cal_year;
	private String quarter;
	private String college;
	private String campus;
	private String department;
	private String crn;
	private String subject_code;
	private String course_number;
	private String section;
	private String credits;
	private String title;
	private String instructor;
	private String instruction_type;
	private String instruction_method;
	private String section_comments;
	private String textbooks_link;
	private String start_date;
	private String end_date;
	private String times;
	private String days;
	private String pre_requisites;
	private String co_requisites;
	private String restrictions;
	private String repeatable;
	
	//Set variables
	public void setYear ( String year ) { cal_year = year;	}	
	public void setQuarter ( String qtr ) { quarter = qtr;	}	
	public void setCollege ( String coll ){ college = coll; }	
	public void setCampus(String camp){ campus = camp; }	
	public void setDept ( String dept ) { department = dept; }
	public void setCRN ( String CRN ) { crn = CRN; }
	public void setSubjCode ( String sCode ) { subject_code = sCode; }
	public void setCourseNumber (String cNumber ) { course_number = cNumber; }
	public void setSection ( String sect ) { section = sect; }
	public void setCredits ( String creds ) { credits = creds; }
	public void setTitle ( String tit ) { title = tit; }
	public void setInstructor ( String inst ) { instructor = inst; }
	public void setInstType (String instType ) { instruction_type = instType; }
	public void setInstMethod (String instMethod ) { instruction_method = instMethod; }
	public void setSectComments ( String sComments ) { section_comments = sComments; }
	public void setTextbookLink ( String tLink ) { textbooks_link = tLink; }
	public void setStartDate ( String sDate ) { start_date = sDate; }
	public void setEndDate ( String eDate ) { end_date = eDate; }
	public void setTimes ( String timez ) { times = timez; }
	public void setDays ( String dayz ) { days = dayz ; }
	public void setPreReq ( String preReq ) { pre_requisites = preReq; }
	public void setCoReq (String coReq ) { co_requisites = coReq; }
	public void setRestrictions ( String restr ) { restrictions = restr; }
	public void setRepeatable ( String repeat ) { repeatable = repeat; }
	
	//Get variables
	public String getYear() { return cal_year; };
	
	
	
	

}
