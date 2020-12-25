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

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;

import net.proteanit.sql.DbUtils;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GestionUtilisateur extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	
	Connection cnx = null;
	ResultSet resultat = null; 
	PreparedStatement prepared = null;
	private JTable table;
    public void fermer(){
    	dispose();
    }
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GestionUtilisateur frame = new GestionUtilisateur();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GestionUtilisateur() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 927, 469);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		cnx = ConnexionMysql.connecrDb();
		
		textField = new JTextField();
		textField.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent arg0) {
				
			}
		});
		textField.setBounds(297, 133, 197, 30);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(297, 191, 197, 30);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("username : ");
		lblNewLabel_1.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD | Font.ITALIC, 20));
		lblNewLabel_1.setForeground(new Color(255, 0, 0));
		lblNewLabel_1.setBounds(297, 108, 148, 19);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Password :");
		lblNewLabel_2.setForeground(new Color(255, 0, 0));
		lblNewLabel_2.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD | Font.ITALIC, 20));
		lblNewLabel_2.setBounds(297, 161, 148, 27);
		contentPane.add(lblNewLabel_2);
		
		JButton btnNewButton = new JButton("Ajouter");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String sql = "insert into utilisateur (username , password) value (? , ?)";
				try {
					prepared = cnx.prepareStatement(sql);
                    prepared.setString(1,textField.getText().toString());
                    prepared.setString(2,textField_1.getText().toString());
                    if(textField.getText().toString().equals("") || textField_1.getText().toString().equals("")){
                   	 JOptionPane.showMessageDialog(null,"Veuillez remlisser les champs SVP");
                   }else{
                    prepared.execute();//on a pas besoin du resultat car ce code ne retourne rien juste operation
                    JOptionPane.showMessageDialog(null, "User ajoutée avec succée");
                    textField.setText("");
	                textField_1.setText("");
                   }} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
			}
		});
		btnNewButton.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD | Font.ITALIC, 12));
		btnNewButton.setForeground(new Color(0, 100, 0));
		btnNewButton.setBounds(333, 239, 112, 36);
		contentPane.add(btnNewButton);
		
		JButton btnModifier = new JButton("Modifier");
		btnModifier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				 
				int ligne = table.getSelectedRow();
				if(ligne == -1){
					JOptionPane.showMessageDialog(null, "Verifier votre information !");
					textField.setText("");
	                textField_1.setText("");
				}else{
					String id = table.getModel().getValueAt(ligne, 0).toString();
					String sql = "update utilisateur set username = ? , password = ? where id_user = '"+id+"'" ;
					try {
						prepared = cnx.prepareStatement(sql);
						prepared.setString(1,textField.getText().toString());
						prepared.setString(2,textField_1.getText().toString());
						prepared.execute();
						JOptionPane.showMessageDialog(null, "User modifiée avec succée");
						textField.setText("");
		                textField_1.setText("");
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
				
	                
				
				
			}
		});
		btnModifier.setForeground(new Color(0, 100, 0));
		btnModifier.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD | Font.ITALIC, 12));
		btnModifier.setBounds(333, 272, 112, 30);
		contentPane.add(btnModifier);
		
		JButton btnSupprimer = new JButton("Supprimer");
		btnSupprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int ligne = table.getSelectedRow();
				if(ligne == -1){
					JOptionPane.showMessageDialog(null, "Verifier votre information !");
					textField.setText("");
	                textField_1.setText("");
				}else{
				String sql = "delete from utilisateur where username = ? and password = ? ";
				try {
					prepared = cnx.prepareStatement(sql);
					prepared.setString(1,textField.getText().toString());
	                prepared.setString(2,textField_1.getText().toString());
	                if(textField.getText().toString().equals("")||textField_1.getText().toString().equals("")){
	                	JOptionPane.showMessageDialog(null,"Verifier votre information et ressayer !");
	                }else{
	                prepared.execute();
	                JOptionPane.showMessageDialog(null,"User supprimée avec succée");
	                textField.setText("");
	                textField_1.setText("");
	                }
			  }catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				}
			}
		});
		btnSupprimer.setForeground(new Color(0, 100, 0));
		btnSupprimer.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD | Font.ITALIC, 11));
		btnSupprimer.setBounds(333, 303, 112, 30);
		contentPane.add(btnSupprimer);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(599, 279, 312, 150);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				int ligne = table.getSelectedRow();//selectioner le numero du ligne du table
				String username = table.getModel().getValueAt(ligne, 1).toString();//retourner valeur d'une valeur de table
				String password = table.getModel().getValueAt(ligne, 2).toString();
				textField.setText(username);
				textField_1.setText(password);
				
			}
		});
		scrollPane.setViewportView(table);
		
		JButton btnNewButton_1 = new JButton("Actualiser");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				UpdateTable();	
			}
		});
		btnNewButton_1.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD | Font.ITALIC, 12));
		btnNewButton_1.setForeground(new Color(0, 100, 0));
		btnNewButton_1.setBounds(333, 333, 112, 30);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnNewButton_2.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				MenuAdmine obj = new MenuAdmine();
				obj.setVisible(true);
				obj.setLocationRelativeTo(null);//apparaitre au milieu de l'ecran 
	             fermer();		
			}
		});
		btnNewButton_2.setIcon(new ImageIcon("C:\\Users\\El Mehdi Tbitbi\\Desktop\\Projet java\\back_12955.png"));
		btnNewButton_2.setBounds(10, 0, 62, 57);
		contentPane.add(btnNewButton_2);
		
		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				UpdateTable();
			}
		});
		lblNewLabel_3.setIcon(new ImageIcon("C:\\Users\\El Mehdi Tbitbi\\Desktop\\Projet java\\refresh_icon-icons.com_50052 (1).png"));
		lblNewLabel_3.setBounds(711, 227, 62, 48);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\El Mehdi Tbitbi\\Desktop\\Projet java\\teacher-5499177_1280.jpg"));
		lblNewLabel.setBounds(10, 0, 901, 429);
		contentPane.add(lblNewLabel);
	}
 public void UpdateTable(){
	 
	 String sql ="select * from utilisateur ";
	 try {
		prepared = cnx.prepareStatement(sql);
		resultat = prepared.executeQuery();
		table.setModel(DbUtils.resultSetToTableModel(resultat));//Dbutils est un class dans la bibliotheque(jar)que nous avons importée
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	 
	 
 }
}
