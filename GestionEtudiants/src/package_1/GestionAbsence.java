package package_1;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import java.awt.Font;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JComboBox;

import net.proteanit.sql.DbUtils;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;

public class GestionAbsence extends JFrame {

	private JPanel contentPane;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTable table;
	private JComboBox comboBox;
	Connection cnx = null;
	ResultSet resultat = null; 
	PreparedStatement prepared = null;
	void fermer(){
    	dispose();
    }
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GestionAbsence frame = new GestionAbsence();
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
	public GestionAbsence() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 812, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		cnx = ConnexionMysql.connecrDb();
		
		
		textField_1 = new JTextField();
		textField_1.setBounds(10, 175, 245, 30);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(10, 239, 245, 34);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Nom Etudiant :");
		lblNewLabel_1.setForeground(Color.RED);
		lblNewLabel_1.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD | Font.ITALIC, 20));
		lblNewLabel_1.setBounds(10, 88, 207, 22);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Date Abscence :");
		lblNewLabel_2.setForeground(Color.RED);
		lblNewLabel_2.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD | Font.ITALIC, 20));
		lblNewLabel_2.setBounds(10, 148, 207, 23);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Raison :");
		lblNewLabel_3.setForeground(Color.RED);
		lblNewLabel_3.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD | Font.ITALIC, 20));
		lblNewLabel_3.setBounds(10, 216, 207, 22);
		contentPane.add(lblNewLabel_3);
		
		JButton btnNewButton = new JButton("Ajouter");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String sql ="insert into absence (nom_etudiant , date_absence , raison) value (? , ? , ? )";
				try {
					prepared = cnx.prepareStatement(sql);
					prepared.setString(1, comboBox.getSelectedItem().toString());
					prepared.setString(2, textField_1.getText().toString());
					prepared.setString(3, textField_2.getText().toString());
					if(comboBox.getSelectedItem().toString().equals("")||textField_1.getText().toString().equals("")||textField_2.getText().toString().equals("")){
						JOptionPane.showMessageDialog(null, "Veuillez verifier votre information !");
					}else{
						prepared.execute();
						JOptionPane.showMessageDialog(null, "Operation terminée avec succée");
						textField_1.setText("");
						textField_2.setText("");
					}
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnNewButton.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD | Font.ITALIC, 16));
		btnNewButton.setForeground(Color.BLUE);
		btnNewButton.setBounds(67, 284, 122, 30);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Modifier");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int ligne = table.getSelectedRow();
				if(ligne == -1 ){
					JOptionPane.showMessageDialog(null, "Veuiller taper votre information");
					textField_1.setText("");
					textField_2.setText("");
					comboBox.setSelectedItem("Selectionner");
				}else{
					String id = table.getModel().getValueAt(ligne,0).toString();
					String sql =" update absence set nom_etudiant = ? , date_absence = ? ,raison = ? where  nom_etudiant = '"+id+"'";
					try {
						prepared = cnx.prepareStatement(sql);
						prepared.setString(1, comboBox.getSelectedItem().toString());
						prepared.setString(2, textField_1.getText().toString());
						prepared.setString(3, textField_2.getText().toString());
						prepared.execute();
						JOptionPane.showMessageDialog(null, "Operation terminée avec succée");
					}catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
						
					
					}
			
				
				
				
				
			}
		});
		btnNewButton_1.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD | Font.ITALIC, 16));
		btnNewButton_1.setForeground(Color.BLUE);
		btnNewButton_1.setBounds(67, 321, 122, 30);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Supprimer");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int ligne = table.getSelectedRow();
				if(ligne == -1 ){
					JOptionPane.showMessageDialog(null, "Veuiller taper votre information");
					textField_1.setText("");
					textField_2.setText("");
					comboBox.setSelectedItem("Selectionner");
				}else {
				String sql ="delete from absence where nom_etudiant = ? and date_absence = ? and raison = ? ";
				try {
					prepared = cnx.prepareStatement(sql);
					prepared.setString(1, comboBox.getSelectedItem().toString());
					prepared.setString(2, textField_1.getText().toString());
					prepared.setString(3, textField_2.getText().toString());
					if(comboBox.getSelectedItem().toString().equals("")||textField_1.getText().toString().equals("")|| textField_2.getText().toString().equals("")){
					JOptionPane.showMessageDialog(null, "Veuillez verifier votre information !");	
					}else{
						prepared.execute();
					JOptionPane.showMessageDialog(null, "Cette absence est supprimée avec succée !");
					textField_1.setText("");
					textField_2.setText("");
					comboBox.setSelectedItem("Selectionner");
										
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				}
			}
		});
		btnNewButton_2.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD | Font.ITALIC, 16));
		btnNewButton_2.setForeground(Color.BLUE);
		btnNewButton_2.setBounds(67, 362, 117, 30);
		contentPane.add(btnNewButton_2);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(474, 176, 322, 235);
		contentPane.add(scrollPane);
		
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				int ligne = table.getSelectedRow();
				String nom = table.getModel().getValueAt(ligne,0).toString();
				String date = table.getModel().getValueAt(ligne,1).toString();
				String raison = table.getModel().getValueAt(ligne,2).toString();
				comboBox.setSelectedItem(nom);
				textField_1.setText(date);
				textField_2.setText(raison);
				
				
			}
		});
		scrollPane.setRowHeaderView(table);
		
		JLabel lblNewLabel_4 = new JLabel("Table des absences :");
		lblNewLabel_4.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD | Font.ITALIC, 20));
		lblNewLabel_4.setForeground(Color.RED);
		lblNewLabel_4.setBounds(474, 144, 193, 30);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("");
		lblNewLabel_5.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				MenuAdmine obj = new MenuAdmine();
				obj.setVisible(true);
				obj.setLocationRelativeTo(null);
				fermer();
			}
		});
		lblNewLabel_5.setIcon(new ImageIcon("C:\\Users\\El Mehdi Tbitbi\\Desktop\\Projet java\\backarrow_80933.png"));
		lblNewLabel_5.setBounds(10, 0, 63, 65);
		contentPane.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("");
		lblNewLabel_6.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				
				String sql ="select * from absence ";
				 try {
					prepared = cnx.prepareStatement(sql);
					resultat = prepared.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(resultat));//Dbutils est un class dans la bibliotheque(jar)que nous avons importée
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		lblNewLabel_6.setIcon(new ImageIcon("C:\\Users\\El Mehdi Tbitbi\\Desktop\\Projet java\\refresh_icon-icons.com_50052 (1).png"));
		lblNewLabel_6.setBounds(750, 129, 46, 47);
		contentPane.add(lblNewLabel_6);
		
		comboBox = new JComboBox();
		comboBox.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD | Font.ITALIC, 14));
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Selectionner"}));
		comboBox.setBounds(10, 110, 245, 30);
		contentPane.add(comboBox);
		UpdateComboBox();
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\El Mehdi Tbitbi\\Desktop\\Projet java\\teacher-5499177_1280.jpg"));
		lblNewLabel.setBounds(0, 0, 796, 411);
		contentPane.add(lblNewLabel);
	}

	public void UpdateComboBox(){
		
		String sql = "select * from etudiant ";
		try {
			prepared = cnx.prepareStatement(sql);
			resultat = prepared.executeQuery();
			while(resultat.next()){
			String nom = resultat.getString("prenom").toString();
			String prenom = resultat.getString("nom").toString();
			comboBox.addItem(nom+" "+prenom);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}

}
