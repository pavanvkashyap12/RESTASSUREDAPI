package PojoClasses;

public class GetCourse {
	
	private String instructor ;
	private String url ;
	private String services ;
	private String expertise ;
	private Courses courses ; // return type of this Courses because courses is an object which has nested Json 
	private String linkedIn ;
	
	public String getInstructor() {
		return instructor;
	}
	public void setInstructor(String instructor) {
		this.instructor = instructor;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getServices() {
		return services;
	}
	public void setServices(String services) {
		this.services = services;
	}
	public String getExpertise() {
		return expertise;
	}
	public void setExpertise(String expertise) {
		this.expertise = expertise;
	}
	public Courses getCourses() {
		return courses;
	}
	public void setCourses(Courses courses) {
		this.courses = courses;
	}
	public String getLinkedIn() {
		return linkedIn;
	}
	public void setLinkedIn(String linkedIn) {
		this.linkedIn = linkedIn;
	}
	
	// when we do getCourses it will bring values from child class that is Courses.java and injects it here
	// when we do setCourses it will bring values form child class that is Courses.java and injects it at run time

}