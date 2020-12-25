package package_1;

import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.net.MalformedURLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JRadioButton;
import javax.swing.JComboBox;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import net.proteanit.sql.DbUtils;

public class GestionEtudiant extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	Connection cnx = null;
	ResultSet resultat = null; 
	PreparedStatement prepared = null;
	private final JScrollPane scrollPane = new JScrollPane();
	private JTable table;
	JComboBox comboBox ;//en mode globale pour que combobox sera connu par le compilateur
	
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
					GestionEtudiant frame = new GestionEtudiant();
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
	public GestionEtudiant() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 773, 444);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		cnx = ConnexionMysql.connecrDb();
		
		textField = new JTextField();
		textField.setBounds(24, 46, 151, 30);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(24, 94, 151, 30);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(24, 139, 151, 30);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setBounds(24, 187, 151, 30);
		contentPane.add(textField_3);
		textField_3.setColumns(10);
		
		textField_4 = new JTextField();
		textField_4.setBounds(24, 241, 151, 30);
		contentPane.add(textField_4);
		textField_4.setColumns(10);
		
		textField_5 = new JTextField();
		textField_5.setBounds(24, 287, 151, 30);
		contentPane.add(textField_5);
		textField_5.setColumns(10);
		
		JButton btnNewButton = new JButton("Ajouter");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String sql = "insert into etudiant ( prenom , nom , cin , tel , datenaissance , adresse , filiere) value (?,?,?,?,?,?,?) ";
				try {
					prepared = cnx.prepareStatement(sql);
					prepared.setString(1, textField.getText().toString());
					prepared.setString(2, textField_1.getText().toString());
					prepared.setString(3, textField_2.getText().toString());
					prepared.setString(4, textField_3.getText().toString());
					prepared.setString(5, textField_4.getText().toString());
					prepared.setString(6, textField_5.getText().toString());
					prepared.setString(7, comboBox.getSelectedItem().toString());
					if(textField.getText().toString().equals("")||textField_1.getText().toString().equals("")|| textField_2.getText().toString().equals("")||textField_3.getText().toString().equals("")||textField_4.getText().toString().equals("")||textField_5.getText().toString().equals("")||comboBox.getSelectedItem().toString().equals("")){
					JOptionPane.showMessageDialog(null, "Veuillez remplisser votre champs SVP");	
					}else{
					prepared.execute();
					JOptionPane.showMessageDialog(null, "Operation terminée avec succée");
					textField.setText("");
					textField_1.setText("");
					textField_2.setText("");
					textField_3.setText("");
					textField_4.setText("");
					textField_5.setText("");
					
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}		
			}
		});
		btnNewButton.setForeground(Color.RED);
		btnNewButton.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD | Font.ITALIC, 12));
		btnNewButton.setBounds(197, 145, 89, 30);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Modifier");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int ligne = table.getSelectedRow();
				if(ligne == -1 ){
					JOptionPane.showMessageDialog(null, "Veuiller taper votre information correcte !");
					textField.setText("");
					textField_1.setText("");
					textField_2.setText("");
					textField_3.setText("");
					textField_4.setText("");
					textField_5.setText("");
				}else{
					String id = table.getModel().getValueAt(ligne,0).toString();
					String sql =" update etudiant set prenom = ? , nom = ? , cin = ? , tel = ? , datenaissance = ? , adresse = ? , filiere = ? where  	id_etudiant = '"+id+"'";
					try {
						prepared = cnx.prepareStatement(sql);
						prepared.setString(1, textField.getText().toString());
						prepared.setString(2, textField_1.getText().toString());
						prepared.setString(3, textField_2.getText().toString());
						prepared.setString(4, textField_3.getText().toString());
						prepared.setString(5, textField_4.getText().toString());
						prepared.setString(6, textField_5.getText().toString());
						prepared.setString(7, comboBox.getSelectedItem().toString());
					    prepared.execute();
						JOptionPane.showMessageDialog(null, "Operation terminée avec succée");
					}catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
						
					
					}
			
				
			}
		});
		btnNewButton_1.setForeground(Color.RED);
		btnNewButton_1.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD | Font.ITALIC, 12));
		btnNewButton_1.setBounds(197, 186, 89, 30);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Supprimer");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int ligne = table.getSelectedRow();
				if(ligne == -1 ){
					JOptionPane.showMessageDialog(null, "Veuiller taper votre information");
					textField.setText("");
					textField_1.setText("");
					textField_2.setText("");
					textField_3.setText("");
					textField_4.setText("");
					textField_5.setText("");
				}else{
				String sql ="delete from etudiant where prenom = ? and nom = ? and cin = ? and tel = ? and datenaissance = ? and adresse = ?  and filiere = ?";
				try {
					prepared = cnx.prepareStatement(sql);
					prepared.setString(1, textField.getText().toString());
					prepared.setString(2, textField_1.getText().toString());
					prepared.setString(3, textField_2.getText().toString());
					prepared.setString(4, textField_3.getText().toString());
					prepared.setString(5, textField_4.getText().toString());
					prepared.setString(6, textField_5.getText().toString());
					prepared.setString(7, comboBox.getSelectedItem().toString());
					if(textField.getText().toString().equals("")||textField_1.getText().toString().equals("")|| textField_2.getText().toString().equals("")||textField_3.getText().toString().equals("")||textField_4.getText().toString().equals("")||textField_5.getText().toString().equals("")||comboBox.getSelectedItem().toString().equals("")){
					JOptionPane.showMessageDialog(null, "Veuillez verifier votre information !");	
					}else{
						prepared.execute();
					JOptionPane.showMessageDialog(null, "Ce etudiant est supprimée avec succée !");
					textField.setText("");
					textField_1.setText("");
					textField_2.setText("");
					textField_3.setText("");
					textField_4.setText("");
					textField_5.setText("");
					
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				}
			}
		});
		btnNewButton_2.setForeground(Color.RED);
		btnNewButton_2.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD | Font.ITALIC, 11));
		btnNewButton_2.setBounds(197, 227, 89, 30);
		contentPane.add(btnNewButton_2);
		
		JLabel lblNewLabel_1 = new JLabel("Prenom :");
		lblNewLabel_1.setForeground(Color.GREEN);
		lblNewLabel_1.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD | Font.ITALIC, 13));
		lblNewLabel_1.setBounds(24, 26, 127, 20);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel(" Nom :");
		lblNewLabel_2.setForeground(Color.GREEN);
		lblNewLabel_2.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD | Font.ITALIC, 13));
		lblNewLabel_2.setBounds(24, 80, 56, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Cin :");
		lblNewLabel_3.setForeground(Color.GREEN);
		lblNewLabel_3.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD | Font.ITALIC, 13));
		lblNewLabel_3.setBounds(24, 126, 127, 14);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Telephone :");
		lblNewLabel_4.setForeground(Color.GREEN);
		lblNewLabel_4.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD | Font.ITALIC, 13));
		lblNewLabel_4.setBounds(24, 172, 127, 14);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Date de naissance :");
		lblNewLabel_5.setForeground(Color.GREEN);
		lblNewLabel_5.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD | Font.ITALIC, 13));
		lblNewLabel_5.setBounds(24, 225, 127, 14);
		contentPane.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("Adresse :");
		lblNewLabel_6.setForeground(Color.GREEN);
		lblNewLabel_6.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD | Font.ITALIC, 13));
		lblNewLabel_6.setBounds(24, 273, 127, 14);
		contentPane.add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("Filiere :");
		lblNewLabel_7.setForeground(Color.GREEN);
		lblNewLabel_7.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD | Font.ITALIC, 13));
		lblNewLabel_7.setBounds(24, 322, 127, 14);
		contentPane.add(lblNewLabel_7);
		
		JButton btnImprimer = new JButton("Imprimer");
		btnImprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Document doc = new Document();//Creation d'un document
				String sql = "select * from etudiant";
				try {
					prepared = cnx.prepareStatement(sql);
					resultat = prepared.executeQuery();
					
					PdfWriter.getInstance(doc, new FileOutputStream("C:\\Users\\El Mehdi Tbitbi\\Desktop\\Projet java\\Etudiant.pdf"));
					doc.open();//ouvrir document
					Image img = Image.getInstance("C:\\Users\\El Mehdi Tbitbi\\Desktop\\Projet java\\gestionEtudiant.png");
					img.scaleAbsoluteHeight(120);
					img.scaleAbsoluteWidth(200);
					img.setAlignment(Image.ALIGN_CENTER);
					doc.add(img);
					doc.add(new Paragraph(" "));
					doc.add(new Paragraph("Liste des Etudiants de l'ecole :  "));
					doc.add(new Paragraph(" "));
					
					PdfPTable table = new PdfPTable(7);//PdfPtable est la classe pour la creation d'une table dans le pdf avec 6 coll
					table.setWidthPercentage(100);
					PdfPCell cell;//creation du col du table dans le pdf
					cell = new PdfPCell(new Phrase("Prenom", FontFactory.getFont("Comic Sans MS",13)));//surcharger constructeur pour implementer la coll
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);//ecriture au milieu de la coll
					cell.setBackgroundColor(BaseColor.RED);
				    table.addCell(cell);
					
					cell = new PdfPCell(new Phrase("Nom", FontFactory.getFont("Comic Sans MS",13)));
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell.setBackgroundColor(BaseColor.RED);
					table.addCell(cell);
					
					cell = new PdfPCell(new Phrase("Cin", FontFactory.getFont("Comic Sans MS",13)));
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell.setBackgroundColor(BaseColor.RED);
					table.addCell(cell);
					
					cell = new PdfPCell(new Phrase("Telephone", FontFactory.getFont("Comic Sans MS",13)));
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell.setBackgroundColor(BaseColor.RED);
					table.addCell(cell);
					
					cell = new PdfPCell(new Phrase("Date de naissance", FontFactory.getFont("Comic Sans MS",13)));
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell.setBackgroundColor(BaseColor.RED);
					table.addCell(cell);
					
					cell = new PdfPCell(new Phrase("Adresse", FontFactory.getFont("Comic Sans MS",13)));
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell.setBackgroundColor(BaseColor.RED);
					table.addCell(cell);
					
					cell = new PdfPCell(new Phrase("filiere", FontFactory.getFont("Comic Sans MS",13)));//surcharger constructeur pour implementer la coll
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);//ecriture au milieu de la coll
					cell.setBackgroundColor(BaseColor.RED);
				    table.addCell(cell);
					
					////////////////////////////////////////////////////////////////////////////////////////
					
					while(resultat.next()){
						cell = new PdfPCell(new Phrase(resultat.getString("nom").toString(), FontFactory.getFont("Arial",11)));//surcharger constructeur pour implementer la coll
						cell.setHorizontalAlignment(Element.ALIGN_CENTER);//ecriture au milieu de la coll
					    table.addCell(cell);
						
						cell = new PdfPCell(new Phrase(resultat.getString("prenom").toString(), FontFactory.getFont("Arial",11)));
						cell.setHorizontalAlignment(Element.ALIGN_CENTER);
						table.addCell(cell);
						
						cell = new PdfPCell(new Phrase(resultat.getString("cin").toString(), FontFactory.getFont("Arial",11)));
						cell.setHorizontalAlignment(Element.ALIGN_CENTER);
						table.addCell(cell);
						
						cell = new PdfPCell(new Phrase(resultat.getString("tel").toString(), FontFactory.getFont("Arial",11)));
						cell.setHorizontalAlignment(Element.ALIGN_CENTER);
						table.addCell(cell);
						
						cell = new PdfPCell(new Phrase(resultat.getString("datenaissance").toString(), FontFactory.getFont("Arial",11)));
						cell.setHorizontalAlignment(Element.ALIGN_CENTER);
						table.addCell(cell);
						
						cell = new PdfPCell(new Phrase(resultat.getString("adresse").toString(), FontFactory.getFont("Arial",11)));
						cell.setHorizontalAlignment(Element.ALIGN_CENTER);
						table.addCell(cell);
					    
						cell = new PdfPCell(new Phrase(resultat.getString("filiere").toString(), FontFactory.getFont("Arial",11)));//surcharger constructeur pour implementer la coll
						cell.setHorizontalAlignment(Element.ALIGN_CENTER);//ecriture au milieu de la coll
					    table.addCell(cell);
						
					
					}
					
					
					//////////////////////////////////////////////////////////////////////////////////////
					
					
	                
					doc.add(table);			
					doc.close();
					Desktop.getDesktop().open(new File("C:\\Users\\El Mehdi Tbitbi\\Desktop\\Projet java\\Etudiant.pdf"));
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (DocumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		});
		btnImprimer.setForeground(Color.RED);
		btnImprimer.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD | Font.ITALIC, 12));
		btnImprimer.setBounds(197, 268, 89, 23);
		contentPane.add(btnImprimer);
		
		JLabel lblNewLabel_8 = new JLabel("");
		lblNewLabel_8.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				MenuAdmine obj = new MenuAdmine();
				obj.setVisible(true);
				obj.setLocationRelativeTo(null);
				fermer();
			}
		});
		lblNewLabel_8.setIcon(new ImageIcon("C:\\Users\\El Mehdi Tbitbi\\Desktop\\Projet java\\backarrow_80933.png"));
		lblNewLabel_8.setBounds(691, 0, 66, 46);
		contentPane.add(lblNewLabel_8);
		scrollPane.setBounds(296, 228, 461, 177);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				   int ligne = table.getSelectedRow();
				String prenom = table.getModel().getValueAt(ligne,1).toString();
				String nom = table.getModel().getValueAt(ligne,2).toString();
				String cin = table.getModel().getValueAt(ligne,3).toString();
				String tel = table.getModel().getValueAt(ligne,4).toString();
				String datenaissance = table.getModel().getValueAt(ligne,5).toString();
				String adresse = table.getModel().getValueAt(ligne,6).toString();
				String filiere = table.getModel().getValueAt(ligne,7).toString();
				
				textField.setText(prenom);
				textField_1.setText(nom);
				textField_2.setText(cin);
				textField_3.setText(tel);
				textField_4.setText(datenaissance);
				textField_5.setText(adresse);
				comboBox.setSelectedItem(filiere);
				
			}
		});
		scrollPane.setRowHeaderView(table);
		
		JButton btnActualiser = new JButton("");
		btnActualiser.setIcon(new ImageIcon("C:\\Users\\El Mehdi Tbitbi\\Desktop\\Projet java\\refresh_icon-icons.com_50052 (1).png"));
		btnActualiser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				UpdateTable();
			}
		});
		btnActualiser.setForeground(Color.RED);
		btnActualiser.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD | Font.ITALIC, 13));
		btnActualiser.setBounds(499, 187, 56, 41);
		contentPane.add(btnActualiser);
		
		comboBox = new JComboBox();
		comboBox.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
			
			}
		});
		comboBox.setBounds(24, 338, 151, 30);
		contentPane.add(comboBox);
		
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD | Font.ITALIC, 13));
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\El Mehdi Tbitbi\\Desktop\\Projet java\\teacher-5499177_1280.jpg"));
		lblNewLabel.setBounds(0, 0, 757, 405);
		contentPane.add(lblNewLabel);
		 UpdateComboBox();
	}
	public void UpdateTable(){
		 
		 String sql ="select * from etudiant ";
		 try {
			prepared = cnx.prepareStatement(sql);
			resultat = prepared.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(resultat));//Dbutils est un class dans la bibliotheque(jar)que nous avons importée
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	
	}
public void UpdateComboBox(){
	
	String sql = "select * from filiere";
	try {
		prepared = cnx.prepareStatement(sql);
		resultat = prepared.executeQuery();
		while(resultat.next()){
			String nom = resultat.getString("nom_filiere").toString();
			comboBox.addItem(nom);
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	
	
	
	
	
	
	
}

}
