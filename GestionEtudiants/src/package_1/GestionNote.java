package package_1;

import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import java.awt.Font;
import java.awt.Color;

import javax.swing.JTable;
import javax.swing.JScrollPane;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;

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

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;

public class GestionNote extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTable table;
	private JComboBox comboBox;
	private JComboBox comboBox_1;
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
					GestionNote frame = new GestionNote();
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
	public GestionNote() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 783, 446);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		cnx = ConnexionMysql.connecrDb();
		
	    comboBox = new JComboBox();
		comboBox.setBounds(50, 75, 239, 33);
		contentPane.add(comboBox);
		UpdateComboBox();
		
	    comboBox_1 = new JComboBox();
		comboBox_1.setBounds(50, 133, 239, 33);
		contentPane.add(comboBox_1);
		UpdateComboBox_1();
		
		textField = new JTextField();
		textField.setBounds(50, 195, 239, 33);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Etudiant :");
		lblNewLabel_1.setForeground(Color.RED);
		lblNewLabel_1.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD | Font.ITALIC, 18));
		lblNewLabel_1.setBounds(50, 53, 187, 20);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Matiere :");
		lblNewLabel_2.setForeground(Color.RED);
		lblNewLabel_2.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD | Font.ITALIC, 18));
		lblNewLabel_2.setBounds(50, 112, 187, 20);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Note :");
		lblNewLabel_3.setForeground(Color.RED);
		lblNewLabel_3.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD | Font.ITALIC, 18));
		lblNewLabel_3.setBounds(50, 170, 187, 20);
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
		lblNewLabel_4.setBounds(10, 0, 54, 72);
		contentPane.add(lblNewLabel_4);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(325, 233, 452, 173);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				
				int ligne = table.getSelectedRow();
				String etudiant_note = table.getModel().getValueAt(ligne,1).toString();
				String matiere_note = table.getModel().getValueAt(ligne,2).toString();
				String note = table.getModel().getValueAt(ligne,3).toString();
				comboBox.setSelectedItem(etudiant_note);
				comboBox_1.setSelectedItem(matiere_note );
				textField.setText(note);
			}
		});
		table.setForeground(Color.DARK_GRAY);
		table.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD | Font.ITALIC, 13));
		scrollPane.setRowHeaderView(table);
		
		JLabel lblNewLabel_5 = new JLabel("Table des Notes :");
		lblNewLabel_5.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD | Font.ITALIC, 20));
		lblNewLabel_5.setForeground(Color.BLACK);
		lblNewLabel_5.setBounds(324, 201, 213, 33);
		contentPane.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("");
		lblNewLabel_6.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				String sql ="select * from note ";
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
		lblNewLabel_6.setBounds(715, 195, 62, 39);
		contentPane.add(lblNewLabel_6);
		
		JButton btnNewButton = new JButton("Ajouter");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String sql ="insert into note (	etudiant , matiere , note_etudiant) value (? , ? , ? )";
				try {
					prepared = cnx.prepareStatement(sql);
					prepared.setString(1,  comboBox.getSelectedItem().toString());
					prepared.setString(2, comboBox_1.getSelectedItem().toString());
					prepared.setString(3, textField.getText().toString());
					if(comboBox.getSelectedItem().toString().equals("")||textField.getText().toString().equals("")||comboBox_1.getSelectedItem().toString().equals("")){
						JOptionPane.showMessageDialog(null, "Veuillez verifier votre information !");
					}else{
						prepared.execute();
						JOptionPane.showMessageDialog(null, "Operation terminée avec succée");
						textField.setText("");
						
					}
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
			}});
		btnNewButton.setForeground(Color.RED);
		btnNewButton.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD | Font.ITALIC, 15));
		btnNewButton.setBounds(113, 239, 100, 33);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Modifier");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int ligne = table.getSelectedRow();
				if(ligne == -1 ){
					JOptionPane.showMessageDialog(null, "Veuiller taper votre information");
					textField.setText("");
				}else{
					String id = table.getModel().getValueAt(ligne,0).toString();
					String sql =" update note set etudiant = ? , matiere = ? ,		note_etudiant	 = ? where  id_note = '"+id+"'";
					try {
						prepared = cnx.prepareStatement(sql);
						prepared.setString(1, comboBox.getSelectedItem().toString());
						prepared.setString(2, comboBox_1.getSelectedItem().toString());
						prepared.setString(3, textField.getText().toString());
						prepared.execute();
						JOptionPane.showMessageDialog(null, "Operation terminée avec succée");
						textField.setText("");
					}catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
						
					
					}
						
					
					
			
			
			}
			});
		btnNewButton_1.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD | Font.ITALIC, 15));
		btnNewButton_1.setForeground(Color.RED);
		btnNewButton_1.setBounds(113, 274, 100, 33);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Supprimer");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int ligne = table.getSelectedRow();
				if(ligne == -1 ){
					JOptionPane.showMessageDialog(null, "Veuiller taper votre information");
					textField.setText("");
				}else{
				
				String sql ="delete from note where etudiant = ? and matiere = ? and  note_etudiant = ? ";
				try {
					prepared = cnx.prepareStatement(sql);
					prepared.setString(1, comboBox.getSelectedItem().toString());
					prepared.setString(2, comboBox_1.getSelectedItem().toString());
					prepared.setString(3, textField.getText().toString());
					if(comboBox.getSelectedItem().toString().equals("")||textField.getText().toString().equals("")|| comboBox_1.getSelectedItem().toString().equals("")){
					JOptionPane.showMessageDialog(null, "Veuillez verifier votre information !");	
					}else{
					prepared.execute();
					JOptionPane.showMessageDialog(null, "Cette note est supprimée avec succée !");
					textField.setText("");
										}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			}
		});
		btnNewButton_2.setForeground(Color.RED);
		btnNewButton_2.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD | Font.ITALIC, 14));
		btnNewButton_2.setBounds(113, 310, 100, 33);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Imprimer");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Document doc = new Document();//Creation d'un document
				String sql = "select * from note";
				try {
					prepared = cnx.prepareStatement(sql);
					resultat = prepared.executeQuery();
					
					PdfWriter.getInstance(doc, new FileOutputStream("C:\\Users\\El Mehdi Tbitbi\\Desktop\\Projet java\\Note.pdf"));
					doc.open();//ouvrir document
					Image img = Image.getInstance("C:\\Users\\El Mehdi Tbitbi\\Desktop\\Projet java\\gestionEtudiant.png");
					img.scaleAbsoluteHeight(120);
					img.scaleAbsoluteWidth(200);
					img.setAlignment(Image.ALIGN_CENTER);
					doc.add(img);
					doc.add(new Paragraph(" "));
					doc.add(new Paragraph("Affichage du note des Etudiants :  "));
					doc.add(new Paragraph(" "));
					
					PdfPTable table = new PdfPTable(3);//PdfPtable est la classe pour la creation d'une table dans le pdf avec 6 coll
					table.setWidthPercentage(100);
					PdfPCell cell;//creation du col du table dans le pdf
					cell = new PdfPCell(new Phrase("Etudiant", FontFactory.getFont("Comic Sans MS",13)));//surcharger constructeur pour implementer la coll
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);//ecriture au milieu de la coll
					cell.setBackgroundColor(BaseColor.RED);
				    table.addCell(cell);
					
					cell = new PdfPCell(new Phrase("Matiere", FontFactory.getFont("Comic Sans MS",13)));
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell.setBackgroundColor(BaseColor.RED);
					table.addCell(cell);
					
					cell = new PdfPCell(new Phrase("Note", FontFactory.getFont("Comic Sans MS",13)));
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell.setBackgroundColor(BaseColor.RED);
					table.addCell(cell);
					
					
					////////////////////////////////////////////////////////////////////////////////////////
					
					while(resultat.next()){
						cell = new PdfPCell(new Phrase(resultat.getString("etudiant").toString(), FontFactory.getFont("Arial",11)));//surcharger constructeur pour implementer la coll
						cell.setHorizontalAlignment(Element.ALIGN_CENTER);//ecriture au milieu de la coll
					    table.addCell(cell);
						
						cell = new PdfPCell(new Phrase(resultat.getString("matiere").toString(), FontFactory.getFont("Arial",11)));
						cell.setHorizontalAlignment(Element.ALIGN_CENTER);
						table.addCell(cell);
						
						cell = new PdfPCell(new Phrase(resultat.getString("note_etudiant").toString(), FontFactory.getFont("Arial",11)));
						cell.setHorizontalAlignment(Element.ALIGN_CENTER);
						table.addCell(cell);
					
					}
					
					
					//////////////////////////////////////////////////////////////////////////////////////
					
					
	                
					doc.add(table);			
					doc.close();
					Desktop.getDesktop().open(new File("C:\\Users\\El Mehdi Tbitbi\\Desktop\\Projet java\\Note.pdf"));
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
		btnNewButton_3.setForeground(Color.RED);
		btnNewButton_3.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD | Font.ITALIC, 15));
		btnNewButton_3.setBounds(113, 345, 100, 33);
		contentPane.add(btnNewButton_3);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\El Mehdi Tbitbi\\Desktop\\Projet java\\teacher-5499177_1280.jpg"));
		lblNewLabel.setBounds(0, 0, 775, 406);
		contentPane.add(lblNewLabel);
	}
     public void UpdateComboBox(){
		
		String sql = "select * from etudiant ";
		try {
			prepared = cnx.prepareStatement(sql);
			resultat = prepared.executeQuery();
			while(resultat.next()){
			String prenom_etudiant = resultat.getString("prenom").toString();
			String nom_etudiant = resultat.getString("nom").toString();
			comboBox.addItem(prenom_etudiant+" "+nom_etudiant);
			
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
     public void UpdateComboBox_1(){
 		
 		String sql = "select * from matiere ";
 		try {
 			prepared = cnx.prepareStatement(sql);
 			resultat = prepared.executeQuery();
 			while(resultat.next()){
 			String matiere = resultat.getString("libelle_matiere").toString();
 		    comboBox_1.addItem(matiere);
 			
 			}
 		} catch (SQLException e) {
 			// TODO Auto-generated catch block
 			e.printStackTrace();
 		}
 		}
}
