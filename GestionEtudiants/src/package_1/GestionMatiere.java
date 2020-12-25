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
import javax.swing.JComboBox;

import java.awt.Font;
import java.awt.Color;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;

import net.proteanit.sql.DbUtils;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GestionMatiere extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
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
					GestionMatiere frame = new GestionMatiere();
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
	public GestionMatiere() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 813, 448);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		cnx = ConnexionMysql.connecrDb();
		
		textField = new JTextField();
		textField.setBounds(10, 134, 222, 33);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(10, 193, 222, 33);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
	    comboBox = new JComboBox();
		comboBox.setBounds(10, 255, 222, 33);
		contentPane.add(comboBox);
		UpdateComboBox();
		
		JLabel lblNewLabel_1 = new JLabel("Matiere :");
		lblNewLabel_1.setForeground(Color.RED);
		lblNewLabel_1.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD | Font.ITALIC, 19));
		lblNewLabel_1.setBounds(10, 115, 166, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Coefficient :");
		lblNewLabel_2.setForeground(Color.RED);
		lblNewLabel_2.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD | Font.ITALIC, 19));
		lblNewLabel_2.setBounds(10, 178, 166, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Professeur :");
		lblNewLabel_3.setForeground(Color.RED);
		lblNewLabel_3.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD | Font.ITALIC, 18));
		lblNewLabel_3.setBounds(10, 237, 166, 19);
		contentPane.add(lblNewLabel_3);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(320, 255, 452, 154);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				int ligne = table.getSelectedRow();
				String libelle_matiere = table.getModel().getValueAt(ligne,1).toString();
				String coefficient_matiere = table.getModel().getValueAt(ligne,2).toString();
				String professeur_matiere = table.getModel().getValueAt(ligne,3).toString();
				textField.setText(libelle_matiere);
				textField_1.setText(coefficient_matiere);
				comboBox.setSelectedItem(professeur_matiere);
				
				
			}
		});
		table.setForeground(Color.BLUE);
		table.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD | Font.ITALIC, 13));
		scrollPane.setRowHeaderView(table);
		
		JLabel lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				MenuAdmine obj = new MenuAdmine();
				obj.setVisible(true);
				obj.setLocationRelativeTo(null);
				fermer();
			}
		});
		lblNewLabel_4.setIcon(new ImageIcon("C:\\Users\\El Mehdi Tbitbi\\Desktop\\Projet java\\backarrow_80933.png"));
		lblNewLabel_4.setBounds(10, 0, 56, 65);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Table des Mati\u00E8res :");
		lblNewLabel_5.setForeground(Color.BLACK);
		lblNewLabel_5.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD | Font.ITALIC, 20));
		lblNewLabel_5.setBounds(318, 229, 289, 27);
		contentPane.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("");
		lblNewLabel_6.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				String sql ="select * from matiere ";
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
		lblNewLabel_6.setBounds(709, 209, 63, 42);
		contentPane.add(lblNewLabel_6);
		
		JButton btnNewButton = new JButton("Ajouter");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String sql ="insert into matiere (libelle_matiere , coefficient_matiere , professeur_matiere) value (? , ? , ? )";
				try {
					prepared = cnx.prepareStatement(sql);
					prepared.setString(1,  textField.getText().toString());
					prepared.setString(2, textField_1.getText().toString());
					prepared.setString(3, comboBox.getSelectedItem().toString());
					if(comboBox.getSelectedItem().toString().equals("")||textField.getText().toString().equals("")||textField_1.getText().toString().equals("")){
						JOptionPane.showMessageDialog(null, "Veuillez verifier votre information !");
					}else{
						prepared.execute();
						JOptionPane.showMessageDialog(null, "Operation terminée avec succée");
						textField.setText("");
						textField_1.setText("");
					}
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnNewButton.setForeground(Color.DARK_GRAY);
		btnNewButton.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD | Font.ITALIC, 16));
		btnNewButton.setBounds(67, 299, 109, 27);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Modifier");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int ligne = table.getSelectedRow();
				if(ligne == -1 ){
					JOptionPane.showMessageDialog(null, "Veuiller taper votre information");
					textField.setText("");
					textField_1.setText("");
				}else{
					String id = table.getModel().getValueAt(ligne,0).toString();
					String sql =" update matiere set libelle_matiere = ? , coefficient_matiere = ? ,	professeur_matiere	 = ? where  id_matiere = '"+id+"'";
					try {
						prepared = cnx.prepareStatement(sql);
						prepared.setString(1, textField.getText().toString());
						prepared.setString(2, textField_1.getText().toString());
						prepared.setString(3, comboBox.getSelectedItem().toString());
						prepared.execute();
						JOptionPane.showMessageDialog(null, "Operation terminée avec succée");
					}catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
						
					
					}
			
				
				
			}
		});
		btnNewButton_1.setForeground(Color.DARK_GRAY);
		btnNewButton_1.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD | Font.ITALIC, 16));
		btnNewButton_1.setBounds(67, 330, 109, 28);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Supprimer");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int ligne = table.getSelectedRow();
				if(ligne == -1 ){
					JOptionPane.showMessageDialog(null, "Veuiller taper votre information");
					textField.setText("");
					textField_1.setText("");
				}else {
				String sql ="delete from matiere where libelle_matiere = ? and coefficient_matiere = ? and 	professeur_matiere = ? ";
				try {
					prepared = cnx.prepareStatement(sql);
					prepared.setString(1, textField.getText().toString());
					prepared.setString(2, textField_1.getText().toString());
					prepared.setString(3, comboBox.getSelectedItem().toString());
					if(comboBox.getSelectedItem().toString().equals("")||textField.getText().toString().equals("")|| textField_1.getText().toString().equals("")){
					JOptionPane.showMessageDialog(null, "Veuillez verifier votre information !");	
					}else{
						prepared.execute();
					JOptionPane.showMessageDialog(null, "Cette matiere est supprimée avec succée !");
					textField.setText("");
					textField_1.setText("");					
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				}
			}
		});
		btnNewButton_2.setForeground(Color.DARK_GRAY);
		btnNewButton_2.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD | Font.ITALIC, 15));
		btnNewButton_2.setBounds(67, 363, 109, 33);
		contentPane.add(btnNewButton_2);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\El Mehdi Tbitbi\\Desktop\\Projet java\\teacher-5499177_1280.jpg"));
		lblNewLabel.setBounds(-3, 0, 800, 409);
		contentPane.add(lblNewLabel);
	}
public void UpdateComboBox(){
		
		String sql = "select * from utilisateur ";
		try {
			prepared = cnx.prepareStatement(sql);
			resultat = prepared.executeQuery();
			while(resultat.next()){
			String nom_professeur = resultat.getString("username").toString();
			comboBox.addItem(nom_professeur);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}




}
