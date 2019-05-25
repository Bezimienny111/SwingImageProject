package test1;

import java.awt.Frame;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.TreeSet;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;
import javax.xml.crypto.Data;

public class MyTableModel extends AbstractTableModel {

	private ArrayList<MyImage> images;
	private String[] columns = new String[6];

	public MyTableModel(ArrayList<MyImage> aImageList) {
		super();
		images = aImageList;
		columns = new String[] { "Location", "Author", "FilePatch", "Date", "Tags", " datex" };
	}

	public void loadTable(String path) {

		try {
			
		
			
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path));
			this.images = (ArrayList<MyImage>) ois.readObject();
			ois.close();
			fireTableDataChanged();
		} catch (FileNotFoundException e1) {
			JFrame errorFrame = new JFrame();
			JOptionPane.showMessageDialog(errorFrame,
				    "File not found, try again",
				    "Not found",
				    JOptionPane.ERROR_MESSAGE);
			
			
			/*JFrame errorFrame = new JFrame();
			JTextField errorField = new JTextField(30);
			errorField.setText("Error with read, try again");
			errorField.setEditable(false);
			errorField.setVisible(true);
			errorFrame.add(errorField);
			errorFrame.pack();
			errorFrame.setVisible(true);
			errorFrame.setLocation(300, 300);;*/
		} catch (IOException e1) {
			JFrame errorFrame = new JFrame();
			JOptionPane.showMessageDialog(errorFrame,
				    "Wrong file, try again",
				    "Wrong file",
				    JOptionPane.ERROR_MESSAGE);
			
			/*
			JFrame errorFrame = new JFrame();
			JTextField errorField = new JTextField(30);
			errorField.setText("Error with read, try again");
			errorField.setEditable(false);
			errorField.setVisible(true);
			errorFrame.add(errorField);
			errorFrame.pack();
			errorFrame.setVisible(true);
			errorFrame.setLocation(300, 300);;*/
		} catch (ClassNotFoundException e1) {
			
			JFrame errorFrame = new JFrame();
			JOptionPane.showMessageDialog(errorFrame,
				    "Loading class error, try again.",
				    "Class Error",
				    JOptionPane.ERROR_MESSAGE);
			
			
			/*
			JFrame errorFrame = new JFrame();
			JTextField errorField = new JTextField(30);
			errorField.setText("Error with read, try again");
			errorField.setEditable(false);
			errorField.setVisible(true);
			errorFrame.add(errorField);
			errorFrame.pack();
			errorFrame.setVisible(true);
			errorFrame.setLocation(300, 300);; */
		}

	}

	public void saveTable(String path) {

		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path));
			oos.writeObject(this.images);
			oos.close();
		} catch (FileNotFoundException e1) {
			JFrame errorFrame = new JFrame();
			JTextField errorField = new JTextField(30);
			errorField.setText("Error with save, try again");
			errorField.setEditable(false);
			errorField.setVisible(true);
			errorFrame.add(errorField);
			errorFrame.pack();
			errorFrame.setVisible(true);
			errorFrame.setLocation(300, 300);;
		} catch (IOException e1) {
	
			
			
			
			/*JFrame errorFrame = new JFrame();
			JTextField errorField = new JTextField(30);
			errorField.setText("Error with save, try again");
			errorField.setEditable(false);
			errorField.setVisible(true);
			errorFrame.add(errorField);
			errorFrame.pack();
			errorFrame.setVisible(true);
			errorFrame.setLocation(300, 300);;*/
		}

	}

	public void addImage(MyImage mi) {
		this.images.add(mi);
		fireTableDataChanged();
	}

	
	//*******************************************
	//*******Sortery****************************
	//*******************************************
	
	public void nameSort() {
		Collections.sort(this.images, new Comparator<MyImage>() {
			public int compare(MyImage i1, MyImage i2) {
				return i1.getAuthor().getName().compareTo(i2.getAuthor().getName());
			}
		});
		fireTableDataChanged();
	}

	public void nameSortR() {

		Collections.sort(this.images, Collections.reverseOrder(new Comparator<MyImage>() {
			public int compare(MyImage i1, MyImage i2) {
				return i1.getAuthor().getName().compareTo(i2.getAuthor().getName());
			}
		}));
		fireTableDataChanged();
	}

	public void surnameSort() {
		Collections.sort(this.images, new Comparator<MyImage>() {
			public int compare(MyImage i1, MyImage i2) {
				return i1.getAuthor().getSurname().compareTo(i2.getAuthor().getSurname());
			}
		});
		fireTableDataChanged();
	}

	public void surnameSortR() {

		Collections.sort(this.images, Collections.reverseOrder(new Comparator<MyImage>() {
			public int compare(MyImage i1, MyImage i2) {
				return i1.getAuthor().getSurname().compareTo(i2.getAuthor().getSurname());
			}
		}));
		fireTableDataChanged();
	}

	public void locationSort() {

		Collections.sort(this.images, new Comparator<MyImage>() {
			public int compare(MyImage i1, MyImage i2) {
				return i1.getLocation().compareTo(i2.getLocation());
			}
		});
		fireTableDataChanged();
	}

	public void locationSortR() {

		Collections.sort(this.images, Collections.reverseOrder(new Comparator<MyImage>() {
			public int compare(MyImage i1, MyImage i2) {
				return i1.getLocation().compareTo(i2.getLocation());
			}
		}));
		fireTableDataChanged();
	}

	public void dateSort() {

		Collections.sort(this.images, new Comparator<MyImage>() {
			public int compare(MyImage i1, MyImage i2) {
				return i1.getDate().compareTo(i2.getDate());
			}
		});
		fireTableDataChanged();
	}

	public void dateSortR() {

		Collections.sort(this.images, Collections.reverseOrder(new Comparator<MyImage>() {
			public int compare(MyImage i1, MyImage i2) {
				return i1.getDate().compareTo(i2.getDate());
			}
		}));
		fireTableDataChanged();
	}

	//*********************************************
	//*********************************************
	//*********************************************
	//*********************************************
	
	public void deleteObj(JTable table) {
		int viewRow = table.getSelectedRow();
		int modelRow = table.convertRowIndexToModel(viewRow);
		this.images.remove(modelRow);
		fireTableDataChanged();

	}

	public int getColumnCount() {
		return columns.length;
	}

	public int getRowCount() {
		return images.size();
	}

	public String getColumnName(int index) {
		return columns[index];

	}

	public void editLocation(int row, String string) {
		images.get(row).setLocation(string);
		fireTableDataChanged();
	}

	public void editName(int row, String string) {
		images.get(row).getAuthor().setName(string);
		fireTableDataChanged();
	}

	public void editSurname(int row, String string) {
		images.get(row).getAuthor().setSurname(string);
		fireTableDataChanged();
	}

	public void editDate(int row, String string) {
		SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
		f.setLenient(false);
		try {
			images.get(row).setDate(f.parse(string));
			fireTableDataChanged();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			// bledny format daty
		}
	}

	public void editfilePatch(int row, String string) {

		BufferedImage imgTest = null;
		boolean existImg = false;
		try {
			imgTest = ImageIO.read(new File(string));
			existImg = true;

		} catch (IOException e) {
			existImg = false;
		}
		if (string != null && string.equals("") == false && existImg == true && imgTest != null)
			images.get(row).setFilePatch(string);
		fireTableDataChanged();

	}

	public void resetTags(int row) {
		images.get(row).getTags().clear();
		fireTableDataChanged();
	}
	
	public void addTagToList(int row, String tag) {
		images.get(row).addTag(tag);
		fireTableDataChanged();
	}
	
	
	/*
	 * public void addTagToList(int row, String string) { ArrayList<String> tmp =
	 * new ArrayList<String>(); tmp = images.get(row).getTags().forEach(string);
	 * tmp.add(string); images.get(row).setTags(tmp);
	 * 
	 * }
	 * 
	 * public void clearTags(int row) {
	 * images.get(row).getTags().removeAll(images.get(row).getTags()); }
	 */

	public void myRemoveRow(int row) {
		images.remove(row);
		fireTableRowsDeleted(row, row);
	}

	public Object getValueAt(int row, int col) {
		MyImage image = images.get(row);
		switch (col) {
		case 0:
			return image.getLocation();
		case 1:
			return image.getAuthor().getName() + " " + image.getAuthor().getSurname();
		case 2:
			return image.getFilePatch();
		case 3:
			return new SimpleDateFormat("dd/MM/yyyy").format(image.getDate());
		// return image.getDate().toString();
		case 4:
			return image.getTags();
		case 5:
			return image.getDate();
		default:
			return null;
		}

	}

}