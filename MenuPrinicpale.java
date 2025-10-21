package main;
import java.awt.EventQueue;
import java.awt.Image;
import java.io.IOException;
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JTextField;
import javax.swing.Box;
@SuppressWarnings("serial")
public class MenuPrinicpale extends JFrame {

	private JPanel contentPane;
	private JLabel DateHeure;
	public String Utilisateur ="";
	private JTextField txtUtilisateur;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuPrinicpale frame = new MenuPrinicpale();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws IOException 
	 */
	public MenuPrinicpale() throws IOException {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				
		  
			}
			@Override
			public void windowActivated(WindowEvent e) {
				txtUtilisateur.setText(Utilisateur);
				  //afficher la date 
				Date aujourdhui = new Date();
		        DateFormatSymbols monDFS = new DateFormatSymbols();
		        String[] joursCourts = new String[] {
		            "",       //Definition personnalisée des Jours (en Allemand)
		            "Dim",
		            "Lun",
		            "Mar",
		            "Mer",     
		            "Jeu",
		            "Ven",
		            "Sam" };
		/*  Attention : il faut consulter la documentation de l'API pour
		 * connaître précisément le contenu et l'ordre des éléments fournis
		 * sous la forme de tableaux aux setters de la classe.
		 * Dans l'exemple, ci-dessus, les jours de la semaine commencent par
		 * dimanche.   
		 */
		         
		  monDFS.setShortWeekdays(joursCourts);
		  SimpleDateFormat dateFormat = new SimpleDateFormat(
		  "EEE dd MMM yyyy HH:mm:ss",  // Definition personnalisée de l'heure
		  monDFS);
		  DateHeure.setText(dateFormat.format(aujourdhui).toString());
			}
		});
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1280, 800);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
	    //Image img = new ImageIcon(this.getClass().getResource("/Puma.png")).getImage();
	    contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("Commencer ");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				AlgosRA algosra = new AlgosRA();
				algosra.setVisible(true);
				algosra.user = Utilisateur;
				//Transaction JFTrans = new Transaction();
				//JFTrans.setVisible(true);
			}
		});
		btnNewButton.setFont(new Font("Verdana", Font.BOLD, 18));
		btnNewButton.setIcon(new ImageIcon("/Users/mac/Desktop/TestRA/images/Nextttt.png"));
		
		btnNewButton.setBounds(895, 601, 362, 101);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
	    Image img1 = new ImageIcon(this.getClass().getResource("/dm.jpg")).getImage();
	    lblNewLabel_1.setIcon(new ImageIcon(img1));
		lblNewLabel_1.setBounds(691, 88, 507, 385);
		contentPane.add(lblNewLabel_1);
		
		JLabel DateHeure = new JLabel("New label");
		DateHeure.setFont(new Font("Bauhaus 93", Font.PLAIN, 13));
		DateHeure.setForeground(Color.WHITE);
		DateHeure.setBounds(967, 6, 61, 16);
		contentPane.add(DateHeure);
		
		JLabel lblNewLabel = new JLabel("Utilisateur : ");
		lblNewLabel.setFont(new Font("Bauhaus 93", Font.PLAIN, 30));
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBounds(40, 683, 170, 57);
		contentPane.add(lblNewLabel);
		
		txtUtilisateur = new JTextField();
		txtUtilisateur.setBackground(Color.BLACK);
		txtUtilisateur.setForeground(Color.WHITE);
		txtUtilisateur.setFont(new Font("Bauhaus 93", Font.PLAIN, 30));
		txtUtilisateur.setBounds(217, 683, 1063, 61);
		contentPane.add(txtUtilisateur);
		txtUtilisateur.setColumns(10);
		
	}
}
