package package_1;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JPasswordField;




import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Authentification extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JFrame frame;
	private JTextField usernameField;
	private JPasswordField passwordField;
	Connection cnx = null;
	ResultSet resultat = null; 
	PreparedStatement prepared = null;
	void fermer(){
		frame.dispose();//on a utilisée application window et pas JFRAME
	}
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Authentification window = new Authentification();
					window.frame.setVisible(true);
					window.frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Authentification() {
		initialize();
	}
	/**
	 * Initialize the contents of the frame.
	 */
	
    private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setBounds(100, 100, 928, 513);
		frame.getContentPane().setLayout(null);
		cnx = ConnexionMysql.connecrDb();
		
		usernameField = new JTextField();
		usernameField.setBounds(388, 204, 197, 30);
		frame.getContentPane().add(usernameField);
		usernameField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Nom d'utilisateur:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblNewLabel_1.setForeground(Color.BLACK);
		lblNewLabel_1.setBounds(388, 179, 145, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Mot de passe:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblNewLabel_2.setForeground(Color.BLACK);
		lblNewLabel_2.setBounds(388, 244, 145, 14);
		frame.getContentPane().add(lblNewLabel_2);
		
		JButton btnNewButton = new JButton("Se connecter");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			
				
			    String sql = "select username , password from utilisateur ";
			
			try {
				prepared = cnx.prepareStatement(sql);
				resultat = prepared.executeQuery();
		        int i = 0;
		        if(usernameField.getText().toString().equalsIgnoreCase("") || passwordField.getText().toString().equalsIgnoreCase("")){
		        	JOptionPane.showMessageDialog(null, "Connexion echoueé !");	
		        }else{
		        while(resultat.next()){
				        if(usernameField.getText().toString().equalsIgnoreCase(resultat.getString("username")) && passwordField.getText().toString().equalsIgnoreCase(resultat.getString("password"))){
		            	   JOptionPane.showMessageDialog(null, "Connexion reussite !");				
					       i=1;
		            	   MenuAdmine obj = new MenuAdmine();
					       obj.setVisible(true);
					       obj.setLocationRelativeTo(null);
					       fermer();
					        }
		              
		              }
			if(i==0){
				JOptionPane.showMessageDialog(null, "Connexion echoueé !");	
			}
			
		        }}
			   
				
			catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 10));
		btnNewButton.setBounds(430, 333, 105, 23);
		frame.getContentPane().add(btnNewButton);
		
		JLabel lblNewLabel_3 = new JLabel("New label");
		lblNewLabel_3.setIcon(new ImageIcon("C:\\Users\\El Mehdi Tbitbi\\Desktop\\Projet java\\Abdelmalek_Essaadi_University.png"));
		lblNewLabel_3.setBounds(0, -14, 178, 218);
		frame.getContentPane().add(lblNewLabel_3);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(388, 269, 197, 30);
		frame.getContentPane().add(passwordField);
		
		JLabel lblNewLabel_4 = new JLabel("Mot de passe oubli\u00E9 ");
		lblNewLabel_4.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
	        MotPassOublie obj = new MotPassOublie();
            obj.setVisible(true);	        
            obj.setLocationRelativeTo(null);
          
			}
		});
		lblNewLabel_4.setFont(new Font("Tahoma", Font.ITALIC, 12));
		lblNewLabel_4.setForeground(new Color(0, 255, 255));
		lblNewLabel_4.setBounds(430, 308, 125, 14);
		frame.getContentPane().add(lblNewLabel_4);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setForeground(new Color(255, 0, 0));
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\El Mehdi Tbitbi\\Desktop\\Projet java\\fond d'ecran.jpg"));
		lblNewLabel.setBounds(0, 0, 912, 474);
		frame.getContentPane().add(lblNewLabel);
	}
}
