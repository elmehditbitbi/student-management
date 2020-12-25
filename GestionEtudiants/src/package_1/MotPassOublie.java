package package_1;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ImageIcon;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class MotPassOublie extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	Connection cnx = null;
	ResultSet resultat = null; 
	PreparedStatement prepared = null;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MotPassOublie frame = new MotPassOublie();
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
	public MotPassOublie() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 647, 355);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		cnx = ConnexionMysql.connecrDb();
		textField = new JTextField();
		textField.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent arg0) {
			
		String username = textField.getText().toString();
		String sql      = "select password from utilisateur where username= ? ";
	    try {
			prepared = cnx.prepareStatement(sql);
		 	prepared.setString(1, username);
		 	resultat = prepared.executeQuery();
		 	if(resultat.next()){
		 	String pass = resultat.getString("password");
		 	String pass1= pass.substring(0, 3);
		 	textField_1.setText(pass1+"*");
		}} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	   	
			}
		});
		textField.setBounds(224, 189, 164, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setEnabled(false);
		textField_1.setEditable(false);
		textField_1.setBounds(224, 220, 164, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Nom d'utilisateur :");
		lblNewLabel.setForeground(new Color(0, 0, 0));
		lblNewLabel.setFont(new Font("Yu Gothic UI Semibold", Font.ITALIC, 15));
		lblNewLabel.setBounds(92, 186, 131, 20);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Indication :");
		lblNewLabel_1.setFont(new Font("Yu Gothic UI Semibold", Font.ITALIC, 15));
		lblNewLabel_1.setForeground(new Color(0, 0, 0));
		lblNewLabel_1.setBounds(139, 220, 97, 20);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("New label");
		lblNewLabel_2.setIcon(new ImageIcon("C:\\Users\\El Mehdi Tbitbi\\Desktop\\Projet java\\Etonnement-laptop-ordinateur-bureau-employ\u00E9-pixabay.png"));
		lblNewLabel_2.setBounds(0, 0, 631, 316);
		contentPane.add(lblNewLabel_2);
	}
}
