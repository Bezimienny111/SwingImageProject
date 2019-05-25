package test1;


import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;


public class MyImage implements Comparator<MyImage>, Serializable{

	

	
	
	
	private String filePatch = " c://";
	private String location = "Home";
	private Author author= new Author();
	private Date date = new Date();
	private ArrayList<String> tags = new ArrayList<String>();
	
	

	public ArrayList<String> getTags() {
		return tags;
	}
	
	public void setTags(ArrayList<String> tags) {
		this.tags = tags;
	}

	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}

	
	
	public String getFilePatch() {
		return filePatch;
	}

	public void setFilePatch(String filePatch) {
		this.filePatch = filePatch;
	}


	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public MyImage(){
	};
	
	
	public String toString() {
		return " date : " + this.date + "Name: " + this.author + "Surname: " + this.location + "patch:" + this.filePatch  ;
	}

	public void addTag(String tag) {
		tags.add(tag);
	}
	

	@Override
	public int compare(MyImage o1, MyImage o2) {
		// TODO Auto-generated method stub
		return 0;
	}

	
	
	
	}

	

