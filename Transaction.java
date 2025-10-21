package main;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

@SuppressWarnings("serial")
public class Transaction extends JFrame {

	private JPanel contentPane;
	private JTextField txtID;
	private JTextField txtTrans;
	private JComboBox<String> txtItem1;
	private JComboBox<String> txtItem2;
	private JComboBox<String> txtItem3;
	private JComboBox<String> txtItem4;
	private JComboBox<String> txtItem5;
	private JComboBox<String> txtItem6;
	private JComboBox<String> txtItem7;
	private JComboBox<String> txtItem8;
	private JComboBox<String> txtItem9;
	
	
	private JButton btnEnregistrer;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Transaction frame = new Transaction();
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
	public Transaction() {
		
		
		setBackground(Color.BLACK);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1280, 800);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblIdDeLa = new JLabel("ID de la Transaction : ");
		lblIdDeLa.setForeground(Color.WHITE);
		lblIdDeLa.setBounds(65, 94, 156, 16);
		contentPane.add(lblIdDeLa);
		
		JLabel lblDesignationDeTransaction = new JLabel("Designation de transaction : ");
		lblDesignationDeTransaction.setForeground(Color.WHITE);
		lblDesignationDeTransaction.setBounds(65, 135, 182, 16);
		contentPane.add(lblDesignationDeTransaction);
		
		JLabel lblItemN = new JLabel("Item N°01 (T-Shirt) :");
		lblItemN.setForeground(Color.WHITE);
		lblItemN.setBounds(65, 167, 156, 16);
		contentPane.add(lblItemN);
		
		JLabel lblItemN_1 = new JLabel("Item N°02 (Maillot de Foot) :");
		lblItemN_1.setForeground(Color.WHITE);
		lblItemN_1.setBounds(65, 202, 182, 16);
		contentPane.add(lblItemN_1);
		
		JLabel lblItemN_2 = new JLabel("Item N°03 (SweaShirt) :");
		lblItemN_2.setForeground(Color.WHITE);
		lblItemN_2.setBounds(65, 234, 163, 16);
		contentPane.add(lblItemN_2);
		
		JLabel lblItemN_3 = new JLabel("Item N°04 (Survetement) :");
		lblItemN_3.setForeground(Color.WHITE);
		lblItemN_3.setBounds(64, 272, 182, 16);
		contentPane.add(lblItemN_3);
		
		JLabel lblItemN_4 = new JLabel("Item N°05 (veste) :");
		lblItemN_4.setForeground(Color.WHITE);
		lblItemN_4.setBounds(65, 303, 156, 16);
		contentPane.add(lblItemN_4);
		
		JLabel lblItemN_5 = new JLabel("Item N°06 (Pentalon) :");
		lblItemN_5.setForeground(Color.WHITE);
		lblItemN_5.setBounds(555, 166, 175, 16);
		contentPane.add(lblItemN_5);
		
		JLabel lblItemN_6 = new JLabel("Item N°07 (Shorts) :");
		lblItemN_6.setForeground(Color.WHITE);
		lblItemN_6.setBounds(555, 201, 156, 16);
		contentPane.add(lblItemN_6);
		
		JLabel lblItemN_7 = new JLabel("Item N°08 (Chaussette) :");
		lblItemN_7.setForeground(Color.WHITE);
		lblItemN_7.setBounds(555, 234, 175, 16);
		contentPane.add(lblItemN_7);
		
		JLabel lblItemN_8 = new JLabel("Item N°09 (Chausures) :");
		lblItemN_8.setForeground(Color.WHITE);
		lblItemN_8.setBounds(555, 269, 175, 16);
		contentPane.add(lblItemN_8);
		
		txtID = new JTextField();
		txtID.setEnabled(false);
		txtID.setBackground(Color.WHITE);
		txtID.setBounds(208, 88, 134, 27);
		contentPane.add(txtID);
		txtID.setColumns(10);
		
		txtTrans = new JTextField();
		txtTrans.setEnabled(false);
		txtTrans.setColumns(10);
		txtTrans.setBackground(Color.WHITE);
		txtTrans.setBounds(248, 129, 134, 27);
		contentPane.add(txtTrans);
		
		 txtItem1 = new JComboBox<String>();
		txtItem1.setEnabled(false);
		txtItem1.setEditable(true);
		txtItem1.setBounds(240, 163, 190, 27);
		contentPane.add(txtItem1);
		
		 txtItem2 = new JComboBox<String>();
		txtItem2.setEnabled(false);
		txtItem2.setEditable(true);
		txtItem2.setBounds(240, 195, 190, 27);
		contentPane.add(txtItem2);
		
		txtItem3 = new JComboBox<String>();
		txtItem3.setEnabled(false);
		txtItem3.setEditable(true);
		txtItem3.setBounds(240, 230, 190, 27);
		contentPane.add(txtItem3);
		
	    txtItem4 = new JComboBox<String>();
		txtItem4.setEnabled(false);
		txtItem4.setEditable(true);
		txtItem4.setBounds(240, 266, 190, 27);
		contentPane.add(txtItem4);
		
	    txtItem5 = new JComboBox<String>();
		txtItem5.setEnabled(false);
		txtItem5.setEditable(true);
		txtItem5.setBounds(240, 297, 190, 27);
		contentPane.add(txtItem5);
		
		 txtItem6 = new JComboBox<String>();
		txtItem6.setEnabled(false);
		txtItem6.setEditable(true);
		txtItem6.setBounds(762, 161, 170, 27);
		contentPane.add(txtItem6);
		
		 txtItem7 = new JComboBox<String>();
		txtItem7.setEditable(true);
		txtItem7.setEnabled(false);
		txtItem7.setBounds(762, 196, 170, 27);
		contentPane.add(txtItem7);
		
		 txtItem8 = new JComboBox<String>();
		txtItem8.setEnabled(false);
		txtItem8.setEditable(true);
		txtItem8.setBounds(762, 230, 170, 27);
		contentPane.add(txtItem8);
		
		 txtItem9 = new JComboBox<String>();
		txtItem9.setEnabled(false);
		txtItem9.setEditable(true);
		txtItem9.setBounds(762, 264, 170, 27);
		contentPane.add(txtItem9);
		
		final JButton btnGnrerFichier = new JButton("Générer Fichier ");
		btnGnrerFichier.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				AlgosRA algosRA = new AlgosRA();
				algosRA.setVisible(true);
			}
		});
		btnGnrerFichier.setEnabled(false);
		btnGnrerFichier.setBounds(561, 355, 134, 29);
		contentPane.add(btnGnrerFichier);
		
		final JCheckBox chkItem1 = new JCheckBox("Oui/ Non");
		chkItem1.setEnabled(false);
		chkItem1.setForeground(Color.WHITE);
		chkItem1.setBounds(431, 167, 128, 23);
		contentPane.add(chkItem1);
		
		final JCheckBox chkItem2 = new JCheckBox("Oui/ Non");
		chkItem2.setEnabled(false);
		chkItem2.setForeground(Color.WHITE);
		chkItem2.setBounds(431, 202, 128, 23);
		contentPane.add(chkItem2);
		
		final JCheckBox chkItem3 = new JCheckBox("Oui/ Non");
		chkItem3.setEnabled(false);
		chkItem3.setForeground(Color.WHITE);
		chkItem3.setBounds(431, 234, 128, 23);
		contentPane.add(chkItem3);
		
		final JCheckBox chkItem4 = new JCheckBox("Oui/ Non");
		chkItem4.setEnabled(false);
		chkItem4.setForeground(Color.WHITE);
		chkItem4.setBounds(431, 270, 128, 23);
		contentPane.add(chkItem4);
		
		final JCheckBox chkItem5 = new JCheckBox("Oui/ Non");
		chkItem5.setEnabled(false);
		chkItem5.setForeground(Color.WHITE);
		chkItem5.setBounds(431, 301, 128, 23);
		contentPane.add(chkItem5);
		
		final JCheckBox chkItem6 = new JCheckBox("Oui/ Non");
		chkItem6.setEnabled(false);
		chkItem6.setForeground(Color.WHITE);
		chkItem6.setBounds(953, 161, 128, 23);
		contentPane.add(chkItem6);
		
		final JCheckBox chkItem7 = new JCheckBox("Oui/ Non");
		chkItem7.setEnabled(false);
		chkItem7.setForeground(Color.WHITE);
		chkItem7.setBounds(953, 196, 128, 23);
		contentPane.add(chkItem7);
		
		final JCheckBox chkItem8 = new JCheckBox("Oui/ Non");
		chkItem8.setEnabled(false);
		chkItem8.setForeground(Color.WHITE);
		chkItem8.setBounds(953, 228, 128, 23);
		contentPane.add(chkItem8);
		
		final JCheckBox chkItem9 = new JCheckBox("Oui/ Non");
		chkItem9.setEnabled(false);
		chkItem9.setForeground(Color.WHITE);
		chkItem9.setBounds(953, 264, 128, 23);
		contentPane.add(chkItem9);
		JButton btnnouveau = new JButton("Nouveau");
		btnnouveau.addMouseListener(new MouseAdapter() {
			@SuppressWarnings("static-access")
			@Override
			public void mouseClicked(MouseEvent e) {
				//enabled text field
				txtID.setEnabled(true);
				txtTrans.setEnabled(true);
				//enabled combobox 
				txtItem1.setEnabled(true);
				txtItem2.setEnabled(true);
				txtItem3.setEnabled(true);
				txtItem4.setEnabled(true);
				txtItem5.setEnabled(true);
				txtItem6.setEnabled(true);
				txtItem7.setEnabled(true);
				txtItem8.setEnabled(true);
				txtItem9.setEnabled(true);
				//enabled checkbox 
				chkItem1.setEnabled(true);
				chkItem2.setEnabled(true);
				chkItem3.setEnabled(true);
				chkItem4.setEnabled(true);
				chkItem5.setEnabled(true);
				chkItem6.setEnabled(true);
				chkItem7.setEnabled(true);
				chkItem8.setEnabled(true);
				chkItem9.setEnabled(true);
				//enabled button
				btnEnregistrer.setEnabled(true);
				btnGnrerFichier.setEnabled(true);
				ConnectionToBD ConnBD = new ConnectionToBD();
				Connection dbConn=null;
				dbConn = ConnBD.connecter();
				
		        try 
		        {
		        	//c = connecter();
		        	// Creating Statement for query execution
		            Statement stmt = dbConn.createStatement();
		            // creating Query String                
		            String query = "select id from transaction";
		            // excecuting query
		            ResultSet rs = stmt.executeQuery(query);
		            while (rs.next()) {
		            int id = rs.getInt("id");
		            id++;
		            txtID.setText(Integer.toString(id));
		            txtTrans.setText("t"+id);

		            }
		        }
		        catch (Exception e1) {
		            System.err.println( e1.getClass().getName()+": "+ e1.getMessage() );
		         }
			}
		});
		
		btnnouveau.setBounds(327, 355, 117, 29);
		contentPane.add(btnnouveau);
		addWindowListener(new WindowAdapter() {
			@SuppressWarnings("static-access")
			@Override
			public void windowOpened(WindowEvent e) {
				String req= "SELECT article FROM  article"; 
				Statement stmt;
				ConnectionToBD ConnBD = new ConnectionToBD();
				Connection dbConn=null;
				dbConn = ConnBD.connecter();
			    try {
		        stmt = dbConn.createStatement();
		        ResultSet res = stmt.executeQuery(req);
		        while(res.next()){
		        txtItem1.addItem(res.getString(1));
		        txtItem2.addItem(res.getString(1));
		        txtItem3.addItem(res.getString(1));
		        txtItem4.addItem(res.getString(1));
		        txtItem5.addItem(res.getString(1));
		        txtItem6.addItem(res.getString(1));
		        txtItem7.addItem(res.getString(1));
		        txtItem8.addItem(res.getString(1));
		        txtItem9.addItem(res.getString(1));

			    }
			    res.close();
		        } catch (SQLException e1) {
		        e1.printStackTrace();
			    }
			    }
		        });
	    btnEnregistrer = new JButton("Enregistrer");
		btnEnregistrer.addMouseListener(new MouseAdapter() {
			@SuppressWarnings("static-access")
			@Override
			public void mouseClicked(MouseEvent e) {
			    PreparedStatement pstmt = null;
				ConnectionToBD ConnBD = new ConnectionToBD();
				Connection dbConn=null;
				dbConn = ConnBD.connecter();
				 try {
				      String query = "insert into transaction(id,item1,item2,item3,item4,item5,item6,item7,item8,item9,transaction) values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
				      pstmt = dbConn.prepareStatement(query); // create a stateme
				      //int x = Integer.parseInt(txtID.toString());
				      pstmt.setInt(1, 3); 
				      pstmt.setString(2, txtItem1.getEditor().getItem().toString()); 
				      pstmt.setString(3, txtItem2.getEditor().getItem().toString()); 
				      pstmt.setString(4, txtItem3.getEditor().getItem().toString()); 
				      pstmt.setString(5, txtItem4.getEditor().getItem().toString()); 
				      pstmt.setString(6, txtItem5.getEditor().getItem().toString()); 
				      pstmt.setString(7, txtItem6.getEditor().getItem().toString()); 
				      pstmt.setString(8, txtItem7.getEditor().getItem().toString()); // set input parameter 3
				      pstmt.setString(9, txtItem8.getEditor().getItem().toString()); // set input parameter 3
				      pstmt.setString(10, txtItem9.getEditor().getItem().toString()); // set input parameter 3
				      pstmt.setString(11, txtTrans.getText()); // set input parameter 3 
				      pstmt.executeUpdate(); // execute insert statement
					  JOptionPane.showMessageDialog(null,"la transaction est enregistré avec suucès...");
					  pstmt.close();
					  dbConn.close();
				} catch (Exception e1) {
				      e1.printStackTrace();
			    }     
			}
		});
		btnEnregistrer.setEnabled(false);
		btnEnregistrer.setBounds(439, 355, 117, 29);
		contentPane.add(btnEnregistrer);
		
		
	}
}
