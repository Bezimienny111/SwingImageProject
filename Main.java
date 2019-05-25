package test1;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import java.util.Iterator;
import java.util.TreeSet;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.SwingUtilities;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

public class Main {

	public static void main(String[] args) {

		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGUI();
			}
		});
	}

	public static void createAndShowGUI() {

		MyImage test1 = new MyImage();
		MyImage test2 = new MyImage();
		MyImage test8 = new MyImage();
		MyImage test7 = new MyImage();
		MyImage test3 = new MyImage();
		MyImage test4 = new MyImage();
		MyImage test5 = new MyImage();
		MyImage test6 = new MyImage();

		test1.getAuthor().setName("Robert");
		;
		test2.getAuthor().setName("Michal");
		test3.getAuthor().setName("Tomek");
		test4.getAuthor().setName("Grzegorz");
		test5.getAuthor().setName("Ala");
		test6.getAuthor().setName("Ola");
		test7.getAuthor().setName("Iza");
		test8.getAuthor().setName("Darka");
		test3.getAuthor().setSurname("Dziwak");
		test4.getAuthor().setSurname("Kowal");
		test5.getAuthor().setSurname("Kota");
		test6.getAuthor().setSurname("Balcera");
		test7.getAuthor().setSurname("Balcera");
		test8.getAuthor().setSurname("Kowalska");
		test8.setLocation("Moria");
		test7.setLocation("Khorinis");
		test2.setFilePatch("C:\\Users\\Bezi\\Desktop\\test.jpg");
		test3.setFilePatch("C:\\Users\\Bezi\\Desktop\\test.jpg");
		test2.addTag("DOM");
		test2.addTag("JEZIORO");
		test3.addTag("WYCIECZKA");
		ArrayList<MyImage> MyList = new ArrayList<MyImage>();
		MyList.add(test1);
		MyList.add(test2);
		MyList.add(test3);
		MyList.add(test4);
		MyList.add(test5);
		MyList.add(test6);
		MyList.add(test7);
		MyList.add(test8);

		MyTableModel model = new MyTableModel(MyList); // stworzenie modelu i pobranie listy
		// AbstractTableModel model = new MyTableModel(MyList);
		JTable table = new JTable(model); // stworzenie tabeli
		table.addMouseListener(new RightCliclTableMouseListener(table));
		table.setFillsViewportHeight(true);
		table.removeColumn(table.getColumnModel().getColumn(5));
		table.setAutoResizeMode(5);

		JFrame frame = new JFrame("test"); // stworzenie ramki
		JPanel myTablePanel = new JPanel(); // stworzenie panelu

		// ***************************************************************************
		// ****************************** Tablica **********************************
		// ***************************************************************************

		myTablePanel.setLayout(new BorderLayout());
		myTablePanel.add(table.getTableHeader(), BorderLayout.PAGE_START);
		myTablePanel.add(table, BorderLayout.CENTER);

		final JTextField mainTextField = new JTextField(50);
		mainTextField.setEditable(false);
		myTablePanel.add(mainTextField, BorderLayout.NORTH);

		myTablePanel.setVisible(true);

		// **************** popMenu *************************

		JPopupMenu popMenu = new JPopupMenu();
		JMenuItem edit = new JMenuItem("Edit");

		edit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				int editRow = table.getSelectedRow();
				int editRowM = table.convertRowIndexToModel(editRow);

				JFrame editImgFrame = new JFrame();

				JPanel textPanel = new JPanel();
				textPanel.setVisible(true);
				JPanel buttonEditingPanel = new JPanel();
				buttonEditingPanel.setLayout(new FlowLayout());
				buttonEditingPanel.setVisible(true);
				JTextField editTextField = new JTextField(30);
				textPanel.add(editTextField);

				// *********************************************************************
				// *********zmiana imiernia**************
				JButton changeName = new JButton("Change Name");
				changeName.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent event) {
						if (editTextField.getText() != null 
								//&& editTextField.getText().equals("") == false
								) {
							((MyTableModel) table.getModel()).editName(editRowM, editTextField.getText());
							table.repaint();
						}

					}
				});
				// *********************************************************************
				// ***zmiana nazwiska
				JButton changeSurname = new JButton("Change Surname");
				changeSurname.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent event) {
						if (editTextField.getText() != null
								//&& editTextField.getText().equals("") == false
								) {
							((MyTableModel) table.getModel()).editSurname(editRowM, editTextField.getText());
							table.repaint();
						}

					}
				});
				// *********************************************************************
				// ***zmiana lokalizacji
				JButton changeLocation = new JButton("Change Location");
				changeLocation.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent event) {
						if (editTextField.getText() != null //&& 
								//editTextField.getText().equals("") == false
								)
						{
							((MyTableModel) table.getModel()).editLocation(editRowM, editTextField.getText());
							table.repaint();
						}

					}
				});
				// *********************************************************************
				// ***zmiana daty
				JButton changeDate = new JButton("Change Date");
				changeDate.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent event) {
						if (editTextField.getText() != null 
								//&& editTextField.getText().equals("") == false
								) {
							((MyTableModel) table.getModel()).editDate(editRowM, editTextField.getText());
							table.repaint();
						}

					}
				});

				// *********************************************************************
				// ***zmiana sciezki
				JButton changePath = new JButton("Change Path");
				JFileChooser changePathChooser = new JFileChooser();
				changePath.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent event) {
						int returnVal = changePathChooser.showSaveDialog(null);
	
							((MyTableModel) table.getModel()).editfilePatch(editRowM, changePathChooser.getSelectedFile().getAbsolutePath());
							table.repaint();
						

					}
				});

				
				 //*********************************************************************
				JButton addTag = new JButton("Add tag");
				 addTag.addActionListener(new ActionListener() { public void
				 actionPerformed(ActionEvent event) { if (editTextField.getText() != null &&
				 editTextField.getText().equals("") == false ) {
					 model.addTagToList(editRowM, editTextField.getText());
				 }
				 
				 } });
				 
				  //*********************************************************************
				JButton resetTags = new JButton("Reset tags ");
			  resetTags.addActionListener(new ActionListener() { public void
				 actionPerformed(ActionEvent event) {
					model.resetTags(editRowM);
			 
			} });
			//	 
				// */

				buttonEditingPanel.add(changeName);
				buttonEditingPanel.add(changeSurname);
				buttonEditingPanel.add(changeLocation);
				buttonEditingPanel.add(changeDate);
				buttonEditingPanel.add(changePath);
				buttonEditingPanel.add(addTag);
				buttonEditingPanel.add(resetTags);

				editImgFrame.setLayout(new GridLayout(0, 1));
				editImgFrame.add(textPanel);
				editImgFrame.add(buttonEditingPanel);
				editImgFrame.pack();
				editImgFrame.setVisible(true);

			}

		});

		// *********************************************************************
		// ***************** Otwieranie ****************************************
		// *********************************************************************
		JMenuItem openImg = new JMenuItem("Open img");
		openImg.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent event) {

				int openRow = table.getSelectedRow();

				BufferedImage imgTest = null;
				boolean existImg = false;
				try {
					imgTest = ImageIO.read(new File(((String) table.getValueAt(openRow, 2))));
					existImg = true;
				} catch (IOException e) {
					existImg = false;
				} catch (java.lang.ArrayIndexOutOfBoundsException e) {
					// mozna kliknac poza tabele
				}

				if (existImg == true) {

					JFrame openImgFrame = new JFrame();
					ImageIcon imgOpen = new ImageIcon((String) table.getValueAt(openRow, 2));
					JLabel imgLabel = new JLabel(imgOpen);
					openImgFrame.add(imgLabel);
					openImgFrame.pack();
					openImgFrame.setVisible(true);

				}
			}

		});
		// *********************************************************************
		// *********************************************************************

		// *********************************************************************
		// ***************** usuwanie ****************************************
		// *********************************************************************
		JMenuItem delRow = new JMenuItem("Delete");
		delRow.addActionListener(new ActionListener() {

			// @Override
			public void actionPerformed(ActionEvent event) {
				model.deleteObj(table);

				// MyListLoaded.remove(table.getSelectedRow());

				// int delRow = table.getSelectedRow();
				// int delRowM = table.convertRowIndexToModel(delRow);
				// try {
				// ((MyTableModel) table.getModel()).myRemoveRow(delRowM);
				// }catch (java.lang.ArrayIndexOutOfBoundsException e) {
				// }
				//
			}

		});
		delRow.addActionListener(event -> table.repaint());
		// *********************************************************************
		// *********************************************************************

		popMenu.add(edit);
		popMenu.add(delRow);
		popMenu.add(openImg);
		table.setComponentPopupMenu(popMenu);

		// ***************************************************************************
		// ****************************** Przyciski **********************************
		// ***************************************************************************

		JButton nameSort = new JButton("Min Name");
		nameSort.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				model.nameSort();
				mainTextField.setText("Min name:" + table.getValueAt(0, 0) + " " + table.getValueAt(0, 1) + " "
						+ table.getValueAt(0, 2) + " " + table.getValueAt(0, 3) + " " + table.getValueAt(0, 4));

			}
		});

		JButton nameSortR = new JButton("Max Name");
		nameSortR.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				model.nameSortR();
				mainTextField.setText("Max name:" + table.getValueAt(0, 0) + " " + table.getValueAt(0, 1) + " "
						+ table.getValueAt(0, 2) + " " + table.getValueAt(0, 3) + " " + table.getValueAt(0, 4));
			}
		});

		JButton surnameSort = new JButton("Min Surname");
		surnameSort.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				model.surnameSort();
				mainTextField.setText("Min Surname:" + table.getValueAt(0, 0) + " " + table.getValueAt(0, 1) + " "
						+ table.getValueAt(0, 2) + " " + table.getValueAt(0, 3) + " " + table.getValueAt(0, 4));

			}
		});

		JButton surnameSortR = new JButton("Max Surname");
		surnameSortR.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				model.surnameSortR();
				mainTextField.setText("Max Surname:" + table.getValueAt(0, 0) + " " + table.getValueAt(0, 1) + " "
						+ table.getValueAt(0, 2) + " " + table.getValueAt(0, 3) + " " + table.getValueAt(0, 4));
			}
		});

		// ***************************************************************************
		JButton lokSort = new JButton("Min Location");
		lokSort.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				model.locationSort();
				mainTextField.setText("Min Location:" + table.getValueAt(0, 0) + " " + table.getValueAt(0, 1) + " "
						+ table.getValueAt(0, 2) + " " + table.getValueAt(0, 3) + " " + table.getValueAt(0, 4));
			}

		});

		JButton lokSortR = new JButton("Max Location");
		lokSortR.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				model.locationSortR();
				mainTextField.setText("Max Location:" + table.getValueAt(0, 0) + " " + table.getValueAt(0, 1) + " "
						+ table.getValueAt(0, 2) + " " + table.getValueAt(0, 3) + " " + table.getValueAt(0, 4));

			}
		});

		// ***************************************************************************
		JButton datSort = new JButton("Min Date");
		datSort.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				model.dateSort();
				mainTextField.setText("Min Location:" + table.getValueAt(0, 0) + " " + table.getValueAt(0, 1) + " "
						+ table.getValueAt(0, 2) + " " + table.getValueAt(0, 3) + " " + table.getValueAt(0, 4));
			}

		});

		JButton datSortR = new JButton("Max Date");
		datSortR.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				model.dateSortR();
				mainTextField.setText("Min Location:" + table.getValueAt(0, 0) + " " + table.getValueAt(0, 1) + " "
						+ table.getValueAt(0, 2) + " " + table.getValueAt(0, 3) + " " + table.getValueAt(0, 4));
			}

		});

		final JTextField dateToCompareField = new JTextField(10);
		TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(table.getModel()) {
			public boolean isSortable(int column) {
				if (column == 2)
					return false;
				if (column == 4)
					return false;
				return true;
			}

		};
		table.setRowSorter(sorter);

		// *******************************************************************
		// ***************** Wyœwietlanie daty po /przed *********************
		// *******************************************************************
		JButton dateAfterButton = new JButton("Find date after - use format dd/MM/yyyy");
		dateAfterButton.addActionListener(new ActionListener() {
			private boolean filter = true;

			@Override
			public void actionPerformed(ActionEvent arg0) {
				int col = 5;
				if (filter) {
					Date tmp = new Date();
					Date test = new Date();
					test = tmp;
					SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
					f.setLenient(false);
					try {
						tmp = f.parse(dateToCompareField.getText());
					} catch (ParseException e) {
						JFrame errorFrame = new JFrame();
						JOptionPane.showMessageDialog(errorFrame,
							    "Wrong format of date, try again",
							    "Fomat error",
						    JOptionPane.ERROR_MESSAGE);
						

					}
					
					if (tmp != test) 
					sorter.setRowFilter(RowFilter.dateFilter(RowFilter.ComparisonType.AFTER, tmp, col));
					else 
					filter = !filter;
				} else
					sorter.setRowFilter(null);
				filter = !filter;
			}
		});

		JButton dateBeforeButton = new JButton("Find date before - use format dd/MM/yyyy");
		dateBeforeButton.addActionListener(new ActionListener() {
			private boolean filter = true;

			@Override
			public void actionPerformed(ActionEvent arg0) {
				int col = 5;
				if (filter) {
					Date tmp = new Date();
					Date test = new Date();
					test = tmp;
					SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
					f.setLenient(false);
					try {
						tmp = f.parse(dateToCompareField.getText());
					} catch (ParseException e) {
						JFrame errorFrame = new JFrame();
						JOptionPane.showMessageDialog(errorFrame,
							    "Wrong format of date, try again",
							    "Fomat error",
						    JOptionPane.ERROR_MESSAGE);
						

					}
					if (tmp != test) 
					sorter.setRowFilter(RowFilter.dateFilter(RowFilter.ComparisonType.BEFORE, tmp, col));
					else 
					filter = !filter;
				} else
					sorter.setRowFilter(null);
				filter = !filter;
			}
		});

		JPanel dateToComparePanel = new JPanel();

		dateToComparePanel.setLayout(new BorderLayout());
		dateToComparePanel.add(dateAfterButton, BorderLayout.NORTH);
		dateToComparePanel.add(dateBeforeButton, BorderLayout.SOUTH);
		dateToComparePanel.add(dateToCompareField, BorderLayout.CENTER);

		dateToComparePanel.setVisible(true);

		// *******************************************************************
		// ***************** szukanie parametru *********************
		// *******************************************************************

		final JTextField findField = new JTextField(20);

		JButton autorFindButton = new JButton("Find Autor");
		autorFindButton.addActionListener(new ActionListener() {
			private boolean filter = true;

			@Override
			public void actionPerformed(ActionEvent arg0) {
				int col = 1;
				if (filter) {
					sorter.setRowFilter(RowFilter.regexFilter(findField.getText(), col));

				} else
					sorter.setRowFilter(null);
				filter = !filter;
			}
		});

		JButton locationFindButton = new JButton("Find Location");
		locationFindButton.addActionListener(new ActionListener() {
			private boolean filter = true;

			@Override
			public void actionPerformed(ActionEvent arg0) {
				int col = 0;
				if (filter) {
					sorter.setRowFilter(RowFilter.regexFilter(findField.getText(), col));

				} else
					sorter.setRowFilter(null);
				filter = !filter;
			}
		});

		JButton dateFindButton = new JButton("Find date - use format dd/MM/yyyy");
		dateFindButton.addActionListener(new ActionListener() {
			private boolean filter = true;

			@Override
			public void actionPerformed(ActionEvent arg0) {
				int col = 5;
				if (filter) {
					Date tmp = new Date();
					Date test = new Date();
					test = tmp;
					SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
					try {
						tmp = f.parse(dateToCompareField.getText());
					} catch (ParseException e) {
						// TODO Auto-generated catch block

					}
					if (tmp != test)
					sorter.setRowFilter(RowFilter.dateFilter(RowFilter.ComparisonType.EQUAL, tmp, col));
					else
						filter = !filter;
				} else
					sorter.setRowFilter(null);
				filter = !filter;
			}
		});

		JButton pathFindButton = new JButton("Find path - use format x:\\\\x\\\\x\\\\x.xxx");
		pathFindButton.addActionListener(new ActionListener() {
			private boolean filter = true;

			@Override
			public void actionPerformed(ActionEvent arg0) {
				int col = 2;
				if (filter) {
					try {
						sorter.setRowFilter(RowFilter.regexFilter(findField.getText(), col));
					} catch (java.util.regex.PatternSyntaxException e) {
						// mo¿e wyrzucaæ ten blad przy pojedynczym "\"
					}
				} else
					sorter.setRowFilter(null);
				filter = !filter;
			}
		});

		JButton pathTagButton = new JButton("Find tags");
		pathTagButton.addActionListener(new ActionListener() {
			private boolean filter = true;

			@Override
			public void actionPerformed(ActionEvent arg0) {
				int col = 4;
				if (filter) {
					sorter.setRowFilter(RowFilter.regexFilter(findField.getText(), col));

				} else
					sorter.setRowFilter(null);
				filter = !filter;
			}
		});

		JPanel findPanel = new JPanel();
		JButton resetButtonViev = new JButton("Reset view of table");
		resetButtonViev.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {		
				sorter.setRowFilter(null);
			}
		}
		);
		
		
		findPanel.add(findField);
		findPanel.add(autorFindButton);
		findPanel.add(locationFindButton);
		findPanel.add(dateFindButton);
		findPanel.add(pathFindButton);
		findPanel.add(pathTagButton);
		findPanel.add(resetButtonViev);

		findPanel.setVisible(true);

		// *****************************************************************
		// **********************wyswietlenie wszyskich*********************
		// *****************************************************************
		JButton resetButton = new JButton("Reset list");
		resetButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				sorter.setRowFilter(null);

			}
		});

		// ********************************************
		// ****************generowanie nowego**********
		// *******************************************

		// ***************************************************************************
		// ***************************************************************************
		// ***************************************************************************

		JButton newWindowButton = new JButton("New Image");
		newWindowButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame frameNewImage = new JFrame();
				frameNewImage.setLayout(new GridLayout(8, 2));

				// *************************************************
				// ******** Pola tekstowe okna nowego obrazu********
				// *************************************************

				JTextField outputField = new JTextField(30);
				final JTextField locationField = new JTextField();
				final JTextField nameField = new JTextField();
				final JTextField surnameField = new JTextField();
				final JTextField dateField = new JTextField();
				final JTextField patchField = new JTextField();
				final JTextField tagField = new JTextField();

				MyImage tmp = new MyImage();
				MyImage testing = new MyImage();
				frameNewImage.add(outputField);

				// **********************************//
				// ** Przycisk do lokacji **
				// *********************************//
				JPanel locationPanel = new JPanel();
				JButton locationSetter = new JButton("Save Location");
				JButton noLocation = new JButton("Location unknown");
				noLocation.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent event) {
						tmp.setLocation("Unknown");
						outputField.setText("Location set to unknown");
					}
				});

				locationSetter.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent event) {
						if (locationField.getText() != null
								//&& locationField.getText().equals("") == false
								) {
							tmp.setLocation(locationField.getText());

							outputField.setText("Location set: " + locationField.getText());
						} else
							outputField.setText("No Location, set again");

					}
				});

				locationPanel.setLayout(new GridLayout(1, 3));
				locationPanel.add(locationField);
				locationPanel.add(locationSetter);
				locationPanel.add(noLocation);
				locationPanel.setVisible(true);

				// **********************************//
				// ** Przycisk imie autora **
				// *********************************//

				JPanel namePanel = new JPanel();
				JButton nameSetter = new JButton("Save Author Name");
				JButton noName = new JButton("Name unknown");
				noName.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent event) {
						tmp.getAuthor().setName("Unknown");
						outputField.setText("Name set to unknown");
					}
				});

				nameSetter.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent event) {
						if (nameField.getText() != null
								//&& nameField.getText().equals("") == false
								) {
							tmp.getAuthor().setName(nameField.getText());
							outputField.setText("Name setted: " + nameField.getText());

						} else
							outputField.setText("No Name, set again");

					}

				});
				namePanel.setLayout(new GridLayout(1, 3));
				namePanel.add(nameField);
				namePanel.add(nameSetter);
				namePanel.add(noName);
				namePanel.setVisible(true);

				// **********************************//
				// ** Przycisk nazwisko autora **
				// *********************************//
				JPanel surPanel = new JPanel();
				JButton surnameSetter = new JButton("Save Author Surname");
				JButton noSurname = new JButton("Surname unknown");
				noSurname.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent event) {
						tmp.getAuthor().setSurname("Unknown");
						outputField.setText("Surname set to unknown");
					}
				});

				surnameSetter.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent event) {
						if (surnameField.getText() != null 
								//&& surnameField.getText().equals("") == false
								) {
							tmp.getAuthor().setSurname(surnameField.getText());

							outputField.setText("Surname setted: " + surnameField.getText());
						} else
							outputField.setText("No Surname, set again");

					}

				});
				surPanel.setLayout(new GridLayout(1, 3));
				surPanel.add(surnameField);
				surPanel.add(surnameSetter);
				surPanel.add(noSurname);
				surPanel.setVisible(true);

				// **********************************//
				// ** Przycisk data **
				// *********************************//

				JPanel datePanel = new JPanel();
				JButton dateSetter = new JButton("Save date - Format dd/MM/yyyy ");
				JButton noDate = new JButton("Unknown date - set date to 00/00/0000 ");

				noDate.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent event) {
						SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
						try {
							tmp.setDate(f.parse("00/00/0000"));
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						outputField.setText("Unknown date");
					}
				});

				dateSetter.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent event) {
						if (dateField.getText() != null
								//&& dateField.getText().equals("") == false
								) {
							try {
								SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
								f.setLenient(false);
								tmp.setDate(f.parse(dateField.getText()));

								// Date d = new SimpleDateFormat("dd/MM/yyyy").parse(dateField.getText());
								outputField.setText(
										"Date set: " + new SimpleDateFormat("dd/MM/yyyy").format(tmp.getDate()));
							} catch (ParseException e) {
								// TODO Auto-generated catch block
								outputField.setText("Wrong date format. Try again");
							}

						} else
							outputField.setText("Set Date again");
					}
				});
				datePanel.setLayout(new GridLayout(1, 3));
				datePanel.add(dateField);
				datePanel.add(dateSetter);
				datePanel.add(noDate);
				datePanel.setVisible(true);

				JPanel patchPanel = new JPanel();
				JButton patchSetter = new JButton("Set patch to Image - Format x:\\x\\x\\x.xxx");

				JFileChooser newpath = new JFileChooser();
				
				patchSetter.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent event) {
						int returnVal = newpath.showOpenDialog(null);
						BufferedImage imgTest = null;
						boolean existImg = false;
						try {
							imgTest = ImageIO.read(new File(newpath.getSelectedFile().getAbsolutePath()));
							existImg = true;
						} catch (IOException e) {
							existImg = false;
						}
						if ( existImg == true && imgTest != null) {
							tmp.setFilePatch(newpath.getSelectedFile().getAbsolutePath());

							outputField.setText("Patch setted: " + newpath.getSelectedFile().getAbsolutePath());
						} else
							outputField.setText("No patch, set again");

					}

				});
				patchPanel.setLayout(new GridLayout(1, 3));
				patchPanel.add(patchField);
				patchPanel.add(patchSetter);
				patchPanel.setVisible(true);

				JPanel tagPanel = new JPanel();
				JButton tagSetter = new JButton("Add tag");

				tagSetter.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent event) {
						if (tagField.getText() != null && tagField.getText().equals("") == false)
							tmp.addTag(tagField.getText());

					}
				});

				tagPanel.setLayout(new GridLayout(1, 1));
				tagPanel.add(tagField);
				tagPanel.add(tagSetter);
				tagPanel.setVisible(true);

				// **********************************//
				// ** Przycisk dodawania **
				// *********************************//
				JPanel addingButtonPanel = new JPanel();
				JButton addingButton = new JButton("Add Image");

				addingButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent event) {

						if ((tmp.getAuthor() == testing.getAuthor()) || (tmp.getDate() == testing.getDate())
								|| (tmp.getLocation() == testing.getLocation())
								|| tmp.getFilePatch() == testing.getFilePatch()) {
							outputField.setText("Finisz adding data to your Image");
						} else {
							model.addImage(tmp);
							table.repaint();
							frameNewImage.dispose();
						}

					}

				});

				addingButtonPanel.setLayout(new GridLayout(8, 4));
				addingButtonPanel.setPreferredSize(new Dimension(800, 600));
				addingButtonPanel.add(addingButton);
				addingButtonPanel.setVisible(true);

				frameNewImage.add(locationPanel);
				frameNewImage.add(namePanel);
				frameNewImage.add(surPanel);
				frameNewImage.add(datePanel);
				frameNewImage.add(patchPanel);
				frameNewImage.add(tagPanel);
				frameNewImage.add(addingButtonPanel);

				frameNewImage.pack();
				frameNewImage.setVisible(true);
				// nowy.add(nameSort);
			}
		});

		// ***********************************
		// **********zapis odczyt do pliku*******
		// ***************************************

		JPanel slPanel = new JPanel();
		final JTextField pathSaveLoadField = new JTextField(30);

		JButton saveButton = new JButton("SaveList");
		JFileChooser save = new JFileChooser();
		
		saveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				int returnVal = save.showSaveDialog(null);
				model.saveTable(save.getSelectedFile().getAbsolutePath());

			}
		});

		JButton loadButton = new JButton("LoadList");
		
		JFileChooser load = new JFileChooser();
		
		loadButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {

				int returnVal = load.showOpenDialog(null);
				model.loadTable(load.getSelectedFile().getAbsolutePath());

				// TableModel model = new MyTableModel(MyList); // stworzenie modelu i pobranie
				// listy
				// JTable table = new JTable(model); // stworzenie tabeli

				// LoadMethod(MyList ,"C:\\Users\\Bezi\\Desktop\\save.xxx");

				// ArrayList<MyImage> MyListLoaded = new ArrayList<MyImage>();
				// model = new
				// MyTableModel(LoadMethodStart("C:\\Users\\Bezi\\Desktop\\save.xxx"));

				// AbstractTableModel model = (AbstractTableModel) table.getModel()
				// new AbstractTableModel(new
				// MyTableModel(LoadMethodStart("C:\\Users\\Bezi\\Desktop\\save.xxx"))));
				// model.fireTableDataChanged();
				// modelL.fireTableDataChanged();

				// table.setModel();
				// table.setFillsViewportHeight(true);
				// table.removeColumn(table.getColumnModel().getColumn(5));
				// table.setAutoResizeMode(5);

				// frame.repaint();

				// model = new MyTableModel(MyListLoaded);
				// model.fireTableDataChanged();

				//

			}
		}

		);
		// loadButton.addActionListener(event -> table.res);
		// loadButton.addActionListener(event -> table.repaint());
		// JButton repaintButton = new JButton("reste");
		// repaintButton.addActionListener(event -> myTablePanel.repaint());

		slPanel.add(saveButton);
		slPanel.add(loadButton);
		slPanel.add(pathSaveLoadField);
		// slPanel.add(repaintButton);
		slPanel.setLayout(new GridLayout(3, 1));
		slPanel.setVisible(true);

		// **********************************//
		// ** Panel Przyciskow **
		// **********************************//

		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(1, 10));
		// buttonPanel.setLayout(new FlowLayout());
		buttonPanel.add(nameSort);
		buttonPanel.add(nameSortR);
		buttonPanel.add(surnameSort);
		buttonPanel.add(surnameSortR);
		buttonPanel.add(lokSort);
		buttonPanel.add(lokSortR);
		buttonPanel.add(datSort);
		buttonPanel.add(datSortR);
		buttonPanel.add(newWindowButton);
		// buttonPanel.add(resetButton);
		// buttonPanel.add(dateToComparePanel);
		// buttonPanel.add(findPanel);
		// buttonPanel.add(slPanel);
		buttonPanel.setVisible(true);

		JPanel mainPanel = new JPanel();
		frame.getContentPane().setLayout(new BorderLayout());
		mainPanel.setLayout(new BorderLayout());
		frame.add(mainPanel, BorderLayout.CENTER);
		slPanel.setPreferredSize(new Dimension(100,786));
		mainPanel.add(myTablePanel, BorderLayout.CENTER);
		mainPanel.add(dateToComparePanel, BorderLayout.WEST);
		mainPanel.add(slPanel, BorderLayout.EAST);
		mainPanel.add(buttonPanel, BorderLayout.PAGE_END);
		mainPanel.add(findPanel, BorderLayout.PAGE_START);

		mainPanel.setPreferredSize(new Dimension(1440, 786));

		JScrollPane scrollPaneTable = new JScrollPane(table);
		scrollPaneTable.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPaneTable.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		myTablePanel.add(scrollPaneTable);

		frame.pack();
		frame.setVisible(true);

	}}

