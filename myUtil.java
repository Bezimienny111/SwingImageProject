package test1;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.Scanner;

import javax.swing.JMenuItem;

public class myUtil {


	
	
	
	public static Date dateFromScann() {
		System.out.println("Wprowadz datê w formacie \"dd/MM/yyyy\" ");
		Scanner scan = new Scanner(System.in);
		String dateScann = scan.nextLine();
		Date setDate = new Date();
		try {
			 setDate = new SimpleDateFormat("dd/MM/yyyy").parse(dateScann);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			System.out.println("Nie poprawny format, wprowadz jeszcze raz");
			dateFromScann();
		}
		return setDate ;
	}
	
	public static String ImageToString(MyImage img) {
		
		return " date : " + img.getDate() + "Name: " + img.getAuthor().getName() + "Surname: " + img.getAuthor().getSurname()+ "Lokacja" + img.getLocation() + "patch:" + img.getFilePatch()  ;
	}
	
	

}
