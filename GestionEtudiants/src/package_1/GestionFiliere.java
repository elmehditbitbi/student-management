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
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.DefaultComboBoxModel;

import net.proteanit.sql.DbUtils;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GestionFiliere extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTable table;
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
					GestionFiliere frame = new GestionFiliere();
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
	public GestionFiliere() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 824, 461);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		cnx = ConnexionMysql.connecrDb();
		textField = new JTextField();
		textField.setBounds(50, 152, 231, 30);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Nom du Fili\u00E9re :");
		lblNewLabel_1.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD | Font.ITALIC, 19));
		lblNewLabel_1.setForeground(Color.RED);
		lblNewLabel_1.setBounds(50, 127, 192, 14);
		contentPane.add(lblNewLabel_1);
		
	    final JComboBox ComboBoxe = new JComboBox();
		ComboBoxe.setModel(new DefaultComboBoxModel(new String[] {"cycle d'ingenieur", "cycle preparatoir"}));
		ComboBoxe.setMaximumRowCount(3);
		ComboBoxe.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		ComboBoxe.setToolTipText("");
		ComboBoxe.setBounds(50, 210, 231, 30);
		contentPane.add(ComboBoxe);
		
		JLabel lblNewLabel_2 = new JLabel("Type :");
		lblNewLabel_2.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD | Font.ITALIC, 19));
		lblNewLabel_2.setForeground(Color.RED);
		lblNewLabel_2.setBounds(50, 185, 192, 23);
		contentPane.add(lblNewLabel_2);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(394, 247, 414, 175);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				int ligne = table.getSelectedRow();
				String filiere = table.getModel().getValueAt(ligne,1).toString();
				String type = table.getModel().getValueAt(ligne,2).toString();
				textField.setText(filiere);
				ComboBoxe.setSelectedItem(type);
				
			}
		});
		scrollPane.setRowHeaderView(table);
		
		JLabel lblNewLabel_3 = new JLabel("Table des filli\u00E9res :");
		lblNewLabel_3.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD | Font.ITALIC, 20));
		lblNewLabel_3.setForeground(Color.BLACK);
		lblNewLabel_3.setBounds(395, 217, 184, 30);
		contentPane.add(lblNewLabel_3);
		
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
		lblNewLabel_4.setBounds(10, 0, 57, 65);
		contentPane.add(lblNewLabel_4);
		
		JButton btnNewButton = new JButton("Ajouter");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String textFiliere = textField.getText().toString();
				String Combo =  ComboBoxe.getSelectedItem().toString();//retouurner la valeur du combobox avec getselecteditem
				String sql = "insert into filiere (nom_filiere, type) value ( ? , ? ) ";
				try {
					prepared = cnx.prepareStatement(sql);
					prepared.setString(1,textFiliere );
					prepared.setString(2, Combo);
					if(textFiliere.equals("")){
						JOptionPane.showMessageDialog(null, "Veuillez taper votre information correcte !");
					}else{
					prepared.execute();
					JOptionPane.showMessageDialog(null, "Operation terminée avec succée !");
					textField.setText("");
					ComboBoxe.setSelectedItem("selectionner");
					}
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnNewButton.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD | Font.ITALIC, 16));
		btnNewButton.setForeground(Color.BLUE);
		btnNewButton.setBounds(97, 259, 119, 30);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Modifier");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int ligne = table.getSelectedRow();
				if(ligne == -1 ){
					JOptionPane.showMessageDialog(null, "Veuiller taper votre information");
					textField.setText("");
				}else{
				String id = table.getModel().getValueAt(ligne, 0).toString();
                String sql= "update filiere set nom_filiere = ? , type = ? where id_filiere = '"+id+"'"	;		
				try {
					prepared = cnx.prepareStatement(sql);
					prepared.setString(1,textField.getText().toString());
					prepared.setString(2, ComboBoxe.getSelectedItem().toString());
					prepared.execute();
					JOptionPane.showMessageDialog(null, "Operation terminée avec succée !");
					textField.setText("");
						}
				catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
				} 
				}
		});
		btnNewButton_1.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD | Font.ITALIC, 16));
		btnNewButton_1.setForeground(Color.BLUE);
		btnNewButton_1.setBounds(97, 300, 119, 30);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Supprimer");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int ligne = table.getSelectedRow();
				if(ligne == -1 ){
					JOptionPane.showMessageDialog(null, "Veuiller taper votre information");
					textField.setText("");
				}else{
				String sql ="delete from filiere where nom_filiere = ? and type = ?";
				try {
					prepared = cnx.prepareStatement(sql);
					prepared.setString(1, textField.getText().toString());
					prepared.setString(2, ComboBoxe.getSelectedItem().toString());
					if(textField.getText().toString().equals("")||ComboBoxe.getSelectedItem().toString().equals("")){
						JOptionPane.showMessageDialog(null, "Veuillez verifier ces information");
					}else{
						prepared.execute();
						JOptionPane.showMessageDialog(null, "Operation terminée avec succée");
						textField.setText("");
						
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
		btnNewButton_2.setBounds(97, 341, 119, 30);
		contentPane.add(btnNewButton_2);
		
		JLabel lblNewLabel_5 = new JLabel("");
		lblNewLabel_5.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				update();
				
			}
		});
		lblNewLabel_5.setIcon(new ImageIcon("C:\\Users\\El Mehdi Tbitbi\\Desktop\\Projet java\\refresh_icon-icons.com_50052 (1).png"));
		lblNewLabel_5.setBounds(751, 198, 57, 49);
		contentPane.add(lblNewLabel_5);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\El Mehdi Tbitbi\\Desktop\\Projet java\\teacher-5499177_1280.jpg"));
		lblNewLabel.setBounds(0, 0, 808, 422);
		contentPane.add(lblNewLabel);
	}
void update(){
	String sql = "select * from filiere";
	try {
		prepared = cnx.prepareStatement(sql);
		resultat = prepared.executeQuery();
		table.setModel(DbUtils.resultSetToTableModel(resultat));
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
}

}
