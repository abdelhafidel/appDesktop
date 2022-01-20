package com.client;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.project.modal.Position;
import com.project.modal.Smartphone;
import com.project.modal.User;
import com.toedter.calendar.JCalendar;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class App extends JFrame {

	private JPanel contentPane;
	private JTextField nomtextField;
	private JTextField pnomtextField;
	private JTextField emailtextField;
	private JTextField teletextField;
	private JTextField imeitextField;
	private JTable table;

	private DefaultTableModel model;
	private JTable table2;
	private DefaultTableModel model2;
	private JTextField lattextField;
	private JTextField longtextField;
	private JTable table_1;
	private DefaultTableModel model3;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					App frame = new App();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public App() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 951, 569);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(128, -31, 883, 574);
		contentPane.add(tabbedPane);

		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Gestion phone", null, panel_1, null);
		panel_1.setLayout(null);

		JLabel lblNewLabel = new JLabel("IMEI");
		lblNewLabel.setBounds(80, 47, 45, 13);
		panel_1.add(lblNewLabel);

		imeitextField = new JTextField();
		imeitextField.setBounds(147, 44, 154, 19);
		panel_1.add(imeitextField);
		imeitextField.setColumns(10);

		JComboBox comboBox = new JComboBox();
		comboBox.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				User[] users = new User[EjbConnection.getrUser().findAll().size()];
				users = EjbConnection.getrUser().findAll().toArray(users);
				comboBox.removeAllItems();
				for (User u : users)
					comboBox.addItem(u);
			}
		});

		comboBox.setBounds(147, 84, 154, 21);
		panel_1.add(comboBox);

		JLabel lblNewLabel_1 = new JLabel("User");
		lblNewLabel_1.setBounds(80, 88, 45, 13);
		panel_1.add(lblNewLabel_1);

		

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(323, 46, 409, 340);
		panel_1.add(scrollPane_1);

		table2 = new JTable();
		
		model2 = new DefaultTableModel();
		Object[] column2 = { "ID", "IMEI", "NOM", "PRENOM" };
		Object[] rows2 = new Object[4];
		model2.setColumnIdentifiers(column2);
		table2.setModel(model2);
		scrollPane_1.setViewportView(table2);
		table2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int i = table2.getSelectedRow();
				imeitextField.setText(model2.getValueAt(i, 1).toString());
			}
		});
		
		JButton btnNewButton_1 = new JButton("Ajouter");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				User userphone = (User) comboBox.getSelectedItem();
				Smartphone s = new Smartphone(imeitextField.getText());
				s.setUser(userphone);
				EjbConnection.getrSphone().create(s);
				imeitextField.setText("");
				List<Smartphone> datas2 = EjbConnection.getrSphone().findAll();

				model2.setRowCount(0);

				for (Smartphone phone : datas2) {
					rows2[0] = phone.getId();
					rows2[1] = phone.getImei();
					rows2[2] = phone.getUser().getNom();
					rows2[3] = phone.getUser().getPrenom();

					model2.addRow(rows2);
				}
				JOptionPane.showMessageDialog(null, "Smartphone added");
			}
		});
		btnNewButton_1.setBounds(147, 134, 154, 21);
		panel_1.add(btnNewButton_1);

		List<Smartphone> datas2 = EjbConnection.getrSphone().findAll();

		model2.setRowCount(0);

		for (Smartphone phone : datas2) {
			rows2[0] = phone.getId();
			rows2[1] = phone.getImei();
			rows2[2] = phone.getUser().getNom();
			rows2[3] = phone.getUser().getPrenom();

			model2.addRow(rows2);
		}

		JButton btnNewButton_2 = new JButton("Delete");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i = table2.getSelectedRow();
				Long id = (Long) table.getModel().getValueAt(i, 0);
				Smartphone u = new Smartphone();
				u.setId(id);
				EjbConnection.getrSphone().delteById(u);
				model2.removeRow(i);
				JOptionPane.showMessageDialog(null, "Smartphone deleted");

			}
		});
		btnNewButton_2.setBounds(147, 166, 154, 23);
		panel_1.add(btnNewButton_2);
		
		JButton btnmdf2 = new JButton("Modify");
		btnmdf2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i = table2.getSelectedRow();
				try {
				Long id = (Long) table2.getModel().getValueAt(i, 0);
				Smartphone s1 = new Smartphone();
				s1.setId(id);
				Smartphone s = EjbConnection.getrSphone().findById(s1);
				s.setImei(imeitextField.getText());
				s.setUser((User) comboBox.getSelectedItem());

				EjbConnection.getrSphone().update(s);
				imeitextField.setText("");
				List<Smartphone> datas2 = EjbConnection.getrSphone().findAll();

				model2.setRowCount(0);

				for (Smartphone phone : datas2) {
					rows2[0] = phone.getId();
					rows2[1] = phone.getImei();
					rows2[2] = phone.getUser().getNom();
					rows2[3] = phone.getUser().getPrenom();

					model2.addRow(rows2);
				}
				JOptionPane.showMessageDialog(null, "Smartphone a ete modifier");
				}catch(Exception ex) {
					JOptionPane.showMessageDialog(null, "selectionez une ligne");
					ex.printStackTrace();
					return;
				}
				
				
			}
		});
		btnmdf2.setBounds(147, 199, 154, 21);
		panel_1.add(btnmdf2);

		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("Gestion Postion", null, panel_2, null);
		panel_2.setLayout(null);
		
		JLabel lblNewLabel_4 = new JLabel("Latitude");
		lblNewLabel_4.setBounds(36, 37, 46, 14);
		panel_2.add(lblNewLabel_4);
		
		JLabel lblNewLabel_4_1 = new JLabel("Longitude");
		lblNewLabel_4_1.setBounds(36, 75, 67, 14);
		panel_2.add(lblNewLabel_4_1);
		
		JLabel lblNewLabel_4_1_1 = new JLabel("Date");
		lblNewLabel_4_1_1.setBounds(36, 111, 46, 14);
		panel_2.add(lblNewLabel_4_1_1);
		
		JLabel lblNewLabel_4_1_1_1 = new JLabel("Smartphone");
		lblNewLabel_4_1_1_1.setBounds(36, 299, 78, 14);
		panel_2.add(lblNewLabel_4_1_1_1);
		
		lattextField = new JTextField();
		lattextField.setBounds(113, 34, 138, 20);
		panel_2.add(lattextField);
		lattextField.setColumns(10);
		
		longtextField = new JTextField();
		longtextField.setColumns(10);
		longtextField.setBounds(113, 72, 138, 20);
		panel_2.add(longtextField);
		
		JCalendar calendar_1 = new JCalendar();
		calendar_1.setBounds(113, 111, 198, 153);
		panel_2.add(calendar_1);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Smartphone[] ss = new Smartphone[EjbConnection.getrSphone().findAll().size()];
				ss = EjbConnection.getrSphone().findAll().toArray(ss);
				comboBox_1.removeAllItems();
				for (Smartphone u : ss)
					comboBox_1.addItem(u);
			}
		});
		comboBox_1.setBounds(113, 295, 138, 22);
		panel_2.add(comboBox_1);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(342, 37, 406, 349);
		panel_2.add(scrollPane_2);
		
		table_1 = new JTable();
		table_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
			}
		});
		
		
		
		model3 = new DefaultTableModel();
		Object[] column3 = { "ID", "latitude", "longitude", "date", "smartphone"};
		Object[] rows3 = new Object[5];
		model3.setColumnIdentifiers(column3);
		table_1.setModel(model3);
		
		scrollPane_2.setViewportView(table_1);

		List<Position> datas3 = EjbConnection.getRposition().findAll();

		model3.setRowCount(0);

		for (Position p : datas3) {
			rows3[0] = p.getId();
			rows3[1] = p.getLatitude();
			rows3[2] = p.getLongitude();
			rows3[3] = p.getDate();
			rows3[4] = p.getSmartphone().getImei();
			model3.addRow(rows3);
		}

		
		JButton btnNewButton_4 = new JButton("Ajouter");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					
				comboBox_1.getSelectedItem();			
				Position p = new Position(lattextField.getText(),longtextField.getText(),calendar_1.getDate());
				p.setSmartphone((Smartphone)comboBox_1.getSelectedItem());
				EjbConnection.getRposition().create(p);
				
				List<Position> datas3 = EjbConnection.getRposition().findAll();

				model3.setRowCount(0);

				for (Position ps : datas3) {
					rows3[0] = ps.getId();
					rows3[1] = ps.getLatitude();
					rows3[2] = ps.getLongitude();
					rows3[3] = ps.getDate();
					rows3[4] = ps.getSmartphone().getImei();
					model3.addRow(rows3);
				}
				lattextField.setText("");longtextField.setText("");
				
				JOptionPane.showMessageDialog(null, "Postion added");
				
			}
		});
		btnNewButton_4.setBounds(36, 340, 92, 32);
		panel_2.add(btnNewButton_4);
		
		JButton btnNewButton_5 = new JButton("Delete");
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i = table_1.getSelectedRow();
				Long id = (Long) table.getModel().getValueAt(i, 0);
				Position u = new Position();
				u.setId(id);
				EjbConnection.getRposition().delteById(u);
				model3.removeRow(i);
				JOptionPane.showMessageDialog(null, "Postion deleted");

			}
		});
		btnNewButton_5.setBounds(138, 340, 92, 32);
		panel_2.add(btnNewButton_5);
		
		JButton btnNewButton_6 = new JButton("New button");
		btnNewButton_6.setBounds(240, 340, 85, 32);
		panel_2.add(btnNewButton_6);

		JPanel panel = new JPanel();
		tabbedPane.addTab("Gestion User", null, panel, null);
		panel.setLayout(null);

		JLabel nomtextsss = new JLabel("Nom");
		nomtextsss.setBounds(83, 36, 45, 13);
		panel.add(nomtextsss);

		JLabel pnomtextdddd = new JLabel("Prenom");
		pnomtextdddd.setBounds(83, 66, 45, 13);
		panel.add(pnomtextdddd);

		nomtextField = new JTextField();
		nomtextField.setBounds(166, 33, 154, 19);
		panel.add(nomtextField);
		nomtextField.setColumns(10);

		pnomtextField = new JTextField();
		pnomtextField.setBounds(166, 62, 154, 19);
		panel.add(pnomtextField);
		pnomtextField.setColumns(10);

		JLabel Email = new JLabel("Email");
		Email.setBounds(83, 96, 45, 13);
		panel.add(Email);

		emailtextField = new JTextField();
		emailtextField.setBounds(166, 92, 154, 19);
		panel.add(emailtextField);
		emailtextField.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel("Tele");
		lblNewLabel_3.setBounds(83, 284, 45, 13);
		panel.add(lblNewLabel_3);

		teletextField = new JTextField();
		teletextField.setBounds(166, 284, 154, 19);
		panel.add(teletextField);
		teletextField.setColumns(10);

		JCalendar calendar = new JCalendar();
		calendar.setBounds(166, 125, 198, 153);
		panel.add(calendar);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(374, 11, 364, 375);
		panel.add(scrollPane);

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				int i = table.getSelectedRow();
				nomtextField.setText(model.getValueAt(i, 1).toString());
				pnomtextField.setText(model.getValueAt(i, 2).toString());
				emailtextField.setText(model.getValueAt(i, 3).toString());
				calendar.setDate((Date) model.getValueAt(i, 4));
				teletextField.setText(model.getValueAt(i, 5).toString());

			}
		});
		table.setBackground(new Color(176, 224, 230));
		model = new DefaultTableModel();
		Object[] column = { "ID", "NAME", "LNAME", "EMAIL", "DATEdeNaiss", "TELE" };
		Object[] rows = new Object[6];
		model.setColumnIdentifiers(column);
		table.setModel(model);
		scrollPane.setViewportView(table);

		List<User> datas = EjbConnection.getrUser().findAll();

		model.setRowCount(0);

		for (User user : datas) {
			rows[0] = user.getId();
			rows[1] = user.getNom();
			rows[2] = user.getPrenom();
			rows[3] = user.getEmail();
			rows[4] = user.getDateNaiss();
			rows[5] = user.getTelephone();
			model.addRow(rows);
		}

		JButton btnNewButton = new JButton("Ajouter");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if(nomtextField.getText().equals("") || pnomtextField.getText().equals("")  || teletextField.getText().equals("")  || emailtextField.getText().equals("") ) {
					JOptionPane.showMessageDialog(null, "Veuillez remplir tous les champs");
					return;
				}
				User u = new User(nomtextField.getText(), pnomtextField.getText(), teletextField.getText(),
						emailtextField.getText(), calendar.getDate());
				EjbConnection.getrUser().create(u);
				nomtextField.setText("");
				pnomtextField.setText("");
				teletextField.setText("");
				emailtextField.setText("");

				List<User> datas = EjbConnection.getrUser().findAll();

				model.setRowCount(0);

				for (User user : datas) {
					rows[0] = user.getId();
					rows[1] = user.getNom();
					rows[2] = user.getPrenom();
					rows[3] = user.getEmail();
					rows[4] = user.getDateNaiss();
					rows[5] = user.getTelephone();
					model.addRow(rows);
				}
				JOptionPane.showMessageDialog(null, "User a ete ajouter");

			}
		});
		btnNewButton.setBounds(20, 347, 86, 23);
		panel.add(btnNewButton);

		JLabel lblNewLabel_2 = new JLabel("Date de Naiss");
		lblNewLabel_2.setBounds(82, 125, 86, 14);
		panel.add(lblNewLabel_2);

		JButton btnNewButton_3 = new JButton("Delete");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i = table.getSelectedRow();
				Long id = (Long) table.getModel().getValueAt(i, 0);
				User u = new User();
				u.setId(id);
				EjbConnection.getrUser().delteById(u);
				model.removeRow(i);

			}
		});
		btnNewButton_3.setBounds(116, 347, 86, 23);
		panel.add(btnNewButton_3);
		
		JButton mdfbtn = new JButton("Modify");
		mdfbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i = table.getSelectedRow();
				try {
				Long id = (Long) table.getModel().getValueAt(i, 0);
				User u = new User();
				u.setId(id);
				u.setNom(nomtextField.getText());
				u.setPrenom(pnomtextField.getText());
				u.setEmail(emailtextField.getText());
				u.setTelephone(teletextField.getText());
				u.setDateNaiss(calendar.getDate());
				EjbConnection.getrUser().update(u);
				nomtextField.setText("");
				pnomtextField.setText("");
				teletextField.setText("");
				emailtextField.setText("");
				
				}catch(Exception ex) {
					JOptionPane.showMessageDialog(null, "selectionez une ligne");
					return;
				}
				

				List<User> users = EjbConnection.getrUser().findAll();
				model.setRowCount(0);
				for (User s : users) {
					rows[0] = s.getId();
					rows[1] = s.getNom();
					rows[2] = s.getPrenom();
					rows[3] = s.getEmail();
					rows[4] = s.getDateNaiss();
					rows[5] = s.getTelephone();

					model.addRow(rows);
				}
				JOptionPane.showMessageDialog(null, "User a ete modifier");
			}
		});
		mdfbtn.setBounds(212, 347, 85, 22);
		panel.add(mdfbtn);
		
		JButton btnNewButton_7 = new JButton("User");
		btnNewButton_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(2);
			}
		});
		btnNewButton_7.setBounds(10, 67, 108, 21);
		contentPane.add(btnNewButton_7);
		
		JButton btnNewButton_8 = new JButton("Smartphone");
		btnNewButton_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(0);
			}
		});
		btnNewButton_8.setBounds(10, 121, 108, 21);
		contentPane.add(btnNewButton_8);
		
		JButton btnNewButton_9 = new JButton("Position");
		btnNewButton_9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(1);
			}
		});
		btnNewButton_9.setBounds(10, 182, 108, 21);
		contentPane.add(btnNewButton_9);

	}
}
