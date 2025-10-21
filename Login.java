package main;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JFormattedTextField;
import javax.swing.JPasswordField;

@SuppressWarnings("serial")
public class Login extends JFrame {

	private JPanel contentPane;
    private PreparedStatement statement = null;
    private JTextField comboUser;
    private boolean verif;
    private JPasswordField txtPassword;



	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
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
	public Login() {
		setBackground(Color.BLACK);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 402, 212);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		JLabel lblNewLabel = new JLabel("Nom Utilisateur :");
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 13));
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBounds(90, 91, 117, 16);
		contentPane.add(lblNewLabel);
		JLabel lblMotDePasse = new JLabel("Mot de passe : ");
		lblMotDePasse.setForeground(Color.WHITE);
		lblMotDePasse.setFont(new Font("Arial", Font.BOLD, 13));
		lblMotDePasse.setBounds(90, 127, 117, 16);
		contentPane.add(lblMotDePasse);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 402, 54);
		contentPane.add(panel);
		
		JLabel lblNewLabel_1 = new JLabel("LogIn ");
		lblNewLabel_1.setFont(new Font("Bauhaus 93", Font.ITALIC, 20));
		panel.add(lblNewLabel_1);
		
		JButton btnAnnuler = new JButton("Annuler");
		btnAnnuler.setHorizontalAlignment(SwingConstants.LEFT);
		btnAnnuler.setIcon(new ImageIcon("/Users/mac/Downloads/1462762462_cancel.png"));
		btnAnnuler.setBounds(307, 155, 89, 29);
		contentPane.add(btnAnnuler);
		
		JButton btnLogin = new JButton("OK");
		btnLogin.addMouseListener(new MouseAdapter() {
			@SuppressWarnings("static-access")
			@Override
			public void mouseClicked(MouseEvent e) {
				ConnectionToBD ConnBD = new ConnectionToBD();
				Connection dbConn=null;
				dbConn = ConnBD.connecter();
				//String login = comboUser.getSelectedItem().toString();
		        //String password = txtPassword.getText();
		        try 
		        {
		        	Statement s = dbConn.createStatement(); 
		        	ResultSet r = s.executeQuery("SELECT * FROM utilisateur"); 
		        	while (r.next()) { 
		        	String LoginRecup = r.getString("user"); 
		        	String login = comboUser.getText();
		        	String pssw = txtPassword.getText();
		        	//String pw = new String(pssw); 
		        	String MPRecup = r.getString("psw"); 
		        	if (LoginRecup.equals(login)& MPRecup.equals(pssw) ) { 
		        	 verif = true; 
		        	MenuPrinicpale menu = new MenuPrinicpale();
            	 	menu.setVisible(true);
            	 	menu.Utilisateur = comboUser.getText().toString();
		        	}
		        	}
            	 	if (!verif) { 
            	 		JOptionPane.showMessageDialog(null, "Verifier Login / Mot de passe", "Message d'erreur:", JOptionPane.ERROR_MESSAGE); 
            	 		comboUser.setText("");
            	 		txtPassword.setText("");
            	 		} 
            	 	dbConn.close(); 
            	 	}
		        catch (Exception e1) {
		            System.err.println( e1.getClass().getName()+": "+ e1.getMessage() );
		        }	         }
		});
		btnLogin.setHorizontalAlignment(SwingConstants.LEFT);
		btnLogin.setIcon(new ImageIcon("/Users/mac/Downloads/1462762353_checkround-24.png"));
		btnLogin.setBounds(219, 155, 82, 29);
		contentPane.add(btnLogin);
		
		JLabel lblMotDePasse_1 = new JLabel("Mot de passe oublié ? ");
		lblMotDePasse_1.setForeground(Color.WHITE);
		lblMotDePasse_1.setFont(new Font("Arial", Font.BOLD, 10));
		lblMotDePasse_1.setBounds(6, 168, 163, 16);
		contentPane.add(lblMotDePasse_1);
		
		comboUser = new JTextField();
		comboUser.setColumns(10);
		comboUser.setBounds(219, 84, 163, 28);
		contentPane.add(comboUser);
		
		txtPassword = new JPasswordField();
		txtPassword.setBounds(219, 120, 163, 28);
		contentPane.add(txtPassword);
		
	}
}
