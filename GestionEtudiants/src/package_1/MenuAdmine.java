package package_1;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MenuAdmine extends JFrame {
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
					MenuAdmine frame = new MenuAdmine();
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
	public MenuAdmine() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 902, 467);
		getContentPane().setLayout(null);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			GestionUtilisateur obj = new GestionUtilisateur();
			obj.setVisible(true);
			obj.setLocationRelativeTo(null);
			fermer();
				
			}
		});
		btnNewButton.setIcon(new ImageIcon("C:\\Users\\El Mehdi Tbitbi\\Desktop\\Projet java\\gestionEtulisateur.png"));
		btnNewButton.setBounds(121, 36, 129, 130);
		getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				GestionEtudiant obj = new GestionEtudiant();
				obj.setVisible(true);
				obj.setLocationRelativeTo(null);
				fermer();
			}
		});
		btnNewButton_1.setIcon(new ImageIcon("C:\\Users\\El Mehdi Tbitbi\\Desktop\\Projet java\\gestionEtudiant.png"));
		btnNewButton_1.setBounds(357, 36, 122, 130);
		getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			GestionFiliere obj = new GestionFiliere();
			obj.setVisible(true);
			obj.setLocationRelativeTo(null);
			fermer();
			}
			
		});
		btnNewButton_2.setIcon(new ImageIcon("C:\\Users\\El Mehdi Tbitbi\\Desktop\\Projet java\\gestionFiliere.png"));
		btnNewButton_2.setBounds(611, 36, 129, 130);
		getContentPane().add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			GestionAbsence obj = new GestionAbsence();
			obj.setVisible(true);
			obj.setLocationRelativeTo(null);
			fermer();
			}});
		btnNewButton_3.setIcon(new ImageIcon("C:\\Users\\El Mehdi Tbitbi\\Desktop\\Projet java\\GestionAbscence.png"));
		btnNewButton_3.setBounds(121, 228, 129, 133);
		getContentPane().add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				GestionMatiere obj = new GestionMatiere();
				obj.setVisible(true);
				obj.setLocationRelativeTo(null);
				fermer();
			}
		});
		btnNewButton_4.setIcon(new ImageIcon("C:\\Users\\El Mehdi Tbitbi\\Desktop\\Projet java\\GestionMatiere.png"));
		btnNewButton_4.setBounds(357, 228, 122, 133);
		getContentPane().add(btnNewButton_4);
		
		JButton btnNewButton_5 = new JButton("");
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				GestionNote obj = new GestionNote();
				obj.setVisible(true);
				obj.setLocationRelativeTo(null);
				fermer();
			}
		});
		btnNewButton_5.setIcon(new ImageIcon("C:\\Users\\El Mehdi Tbitbi\\Desktop\\Projet java\\GestionNote.png"));
		btnNewButton_5.setBounds(611, 228, 129, 133);
		getContentPane().add(btnNewButton_5);
		
		JLabel lblNewLabel_1 = new JLabel("Gestion des Utilisateurs");
		lblNewLabel_1.setForeground(new Color(0, 255, 255));
		lblNewLabel_1.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD | Font.ITALIC, 12));
		lblNewLabel_1.setBounds(121, 189, 129, 14);
		getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Gestion des Etudiants");
		lblNewLabel_2.setForeground(new Color(0, 255, 255));
		lblNewLabel_2.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD | Font.ITALIC, 12));
		lblNewLabel_2.setBounds(357, 189, 122, 14);
		getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Gestion des Fili\u00E8res");
		lblNewLabel_3.setForeground(new Color(0, 255, 255));
		lblNewLabel_3.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD | Font.ITALIC, 12));
		lblNewLabel_3.setBounds(611, 189, 129, 14);
		getContentPane().add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Gestion des Abscence");
		lblNewLabel_4.setForeground(new Color(0, 255, 255));
		lblNewLabel_4.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD | Font.ITALIC, 12));
		lblNewLabel_4.setBounds(121, 389, 129, 14);
		getContentPane().add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Gestion des Mati\u00E8res");
		lblNewLabel_5.setForeground(new Color(0, 255, 255));
		lblNewLabel_5.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD | Font.ITALIC, 12));
		lblNewLabel_5.setBounds(357, 389, 122, 14);
		getContentPane().add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("Gestion des Notes");
		lblNewLabel_6.setForeground(new Color(0, 255, 255));
		lblNewLabel_6.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD | Font.ITALIC, 12));
		lblNewLabel_6.setBounds(611, 389, 129, 14);
		getContentPane().add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("");
		lblNewLabel_7.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				Authentification window = new Authentification();
				window.frame.setVisible(true);
				window.frame.setLocationRelativeTo(null); 
				fermer();
			}
		});
		lblNewLabel_7.setIcon(new ImageIcon("C:\\Users\\El Mehdi Tbitbi\\Desktop\\Projet java\\backarrow_80933.png"));
		lblNewLabel_7.setBounds(0, 0, 81, 67);
		getContentPane().add(lblNewLabel_7);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setForeground(new Color(0, 255, 255));
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\El Mehdi Tbitbi\\Desktop\\Projet java\\fond d'ecran.jpg"));
		lblNewLabel.setBounds(0, 0, 886, 428);
		getContentPane().add(lblNewLabel);
		
	}
}
