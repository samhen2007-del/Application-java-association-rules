package main;

import java.awt.Desktop;
import java.awt.EventQueue;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

import AssociationRules_Algos.MainTestApriori_saveToFile;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.lang.Thread.UncaughtExceptionHandler;
import java.net.URL;
import java.util.Vector;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.JTextArea;
import javax.swing.JCheckBox;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import AssociationRules_Algos.CommandProcessor;
import AssociationRules_Algos.NotifyingThread;
import AssociationRules_Algos.PathsManager;
import AssociationRules_Algos.ThreadCompleteListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

@SuppressWarnings("serial")
public class AlgosRA extends JFrame implements ThreadCompleteListener, UncaughtExceptionHandler{
	private JPanel middlePanel;
	private JScrollPane scroll;
	// current input file
    private String inputFile = null;
    private String inputFile600 = null;
    private String inputFile900 = null;
    // current output file
    private String outputFile = null;
    private String outputFile600 = null;
    private String outputFile900 = null;
	private JPanel contentPane;
	private JTextField txtInput;
	private JTextField txtOutput;
	private JTextField textFieldParam1;
	private JButton buttonRun;
	private JButton btnAfficherGraphe;
	private JLabel lblExample;
	private JLabel lblSetOutputFile;
	private JLabel lblFichierEnSortie;
	private JLabel lblChoisirMinSup;
	private JLabel lblChoisirLevelK;
	private JLabel lblChoisirConfmin;
	private JCheckBox checkboxOpenOutput;
	private JComboBox<String> comboBox;
    private JProgressBar progressBar;
	//  VARIABLES USED TO RUN AN ALGORITHM IN A SEPARATED THREAD
    // The current data mining task
    public static NotifyingThread currentRunningAlgorithmThread = null;
    public static NotifyingThread currentRunningAlgorithmThread600 = null;
    private JTextField textFieldParam2;
    private JTextField textFieldParam3;
    private JLabel lblChoisirDelta;
    private JTextField textFieldParam4;
    private JLabel labelParam1;
    private JLabel labelParam2;
    private JLabel labelParam3;
    private JTextField txtOutput600;
    private JTextField txtInput600;
    private JTextField txtInput900;
    private JTextField txtOutput900;
    private JTextArea textArea;
    private JTextField textField600;
    private JTextField textField600_1;
    private JTextField textFielddelta600;
    private JLabel labelChoisirdelta600;
    private JLabel label600_4;
    private JLabel label600_5;
    private JLabel labeldelta600;
    private JLabel label900_7;
    private JLabel label900_8;
    private JLabel label900_6;
    private JLabel labeldelta900;
    private JLabel label_5;
    private JLabel labelChoisirdelta900;
    private JLabel lblNewLabel_3;
    //Parametres à passer pour creer les graphes
    private JLabel memory300;
    private JLabel memory600;
    private JLabel memory900;
    private double _memory300;
    private double _memory3001;
    private double _memory3002;
	private double _memory600;
	private double _memory6001;
	private double _memory6002;
	private double _memory900;
	private double _memory9001;
	private double _memory9002;
    private JLabel temp300;
    private JLabel temp600;
    private JLabel temp900;
    private long _temp300;
    private long _temp3001;
    private long _temp3002;
	private long _temp600;
	private long _temp6001;
	private long _temp6002;
	private long _temp900;
	private long _temp9001;
	private long _temp9002;
    private JLabel Rules300;
    private JLabel Rules600;
    private JLabel Rules900;
    private int _rules300;
    private int _rules3001;
    private int _rules3002;
	private int _rules600;
	private int _rules6001;
	private int _rules6002;
	private int _rules900;
	private int _rules9001;
	private int _rules9002;
    private JTextField textField900;
    private JTextField textField900_1;
    private JTextField textField900_2;
    private JTextField textField900_3;
    private JPanel panel_1;
    private JPanel panel_2;
    private JPanel panel_3;
    private JLabel lblRglesTrouvs;
    private JLabel lblUtilisateur;
    private JLabel utilisateurAlgos;
    public String user="";
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AlgosRA frame = new AlgosRA();
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
	public AlgosRA() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				utilisateurAlgos.setText(user);
			}
		});
		setTitle("Génération des Règles d'Association");
		setAlwaysOnTop(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1280, 861);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Choisir L'algorithme : ");
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 15));
		lblNewLabel.setBounds(6, 32, 149, 27);
		contentPane.add(lblNewLabel);
		
		comboBox = new JComboBox<String>(new Vector<String>());	
		comboBox.setMaximumRowCount(20);
		comboBox.addItem("Génération Règles d'Association");
        comboBox.addItem("TopKRules");
        comboBox.addItem("TNR");
      //What to do when the user choose an algorithm : 
        comboBox.addItemListener(new ItemListener() {			
			@Override
			public void itemStateChanged(ItemEvent event) {
				// We need to update the user interface:
				updateUserInterfaceAfterAlgorithmSelection(event.getItem().toString(),
						event.getStateChange() == ItemEvent.SELECTED);				
			}			
		});
		//comboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"Apriori", "ECLAT", "FP-Growth", "FIN", "PrePost+"}));
		comboBox.setBounds(140, 33, 250, 27);
		contentPane.add(comboBox);
		
		JLabel lblNewLabel_1 = new JLabel("Fichier en entrée (1er Fichier):");
		lblNewLabel_1.setFont(new Font("Arial", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(6, 77, 251, 24);
		contentPane.add(lblNewLabel_1);
		
		lblSetOutputFile = new JLabel("Fichier en Sortie : ");
		lblSetOutputFile.setFont(new Font("Arial", Font.PLAIN, 15));
		lblSetOutputFile.setBounds(6, 116, 251, 27);
		contentPane.add(lblSetOutputFile);
		
		txtInput = new JTextField();
		txtInput.setBounds(253, 80, 187, 24);
		contentPane.add(txtInput);
		txtInput.setColumns(10);

		txtOutput = new JTextField();
		txtOutput.setBounds(253, 116, 185, 24);
		contentPane.add(txtOutput);
		txtOutput.setColumns(10);
		
		JButton buttonInput = new JButton("...");
		buttonInput.setBounds(439, 77, 45, 27);
		contentPane.add(buttonInput);
		buttonInput.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				 askUserToChooseInputFile();
			}
		});
		
		JButton buttonOutput = new JButton("New button");
		buttonOutput.setBounds(439, 117, 45, 26);
		contentPane.add(buttonOutput);
		buttonOutput.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
                askUserToChooseOutputFile();

			}
		});
		
		lblChoisirMinSup = new JLabel("Choisir SupMin : ");
		lblChoisirMinSup.setFont(new Font("Arial", Font.PLAIN, 15));
		lblChoisirMinSup.setBounds(9, 384, 121, 14);
		contentPane.add(lblChoisirMinSup);
		
		textFieldParam1 = new JTextField();
		textFieldParam1.setBounds(120, 382, 93, 24);
		contentPane.add(textFieldParam1);
		textFieldParam1.setColumns(10);
		
		
		lblExample = new JLabel("...");
		lblExample.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		lblExample.setBounds(214, 384, 96, 14);
		contentPane.add(lblExample);
		
		buttonRun = new JButton("Exécuter l'Algorithme ");
		buttonRun.setIcon(new ImageIcon("/Users/mac/Downloads/1462120539_gnome-run.png"));
		buttonRun.setEnabled(false);
		buttonRun.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// When the user clicks "run":
                processRunAlgorithmCommandFromGUI();				
			}
		});
		buttonRun.setBounds(637, 658, 154, 38);
		contentPane.add(buttonRun);
		
		 progressBar = new JProgressBar();
		 progressBar.setBounds(637, 708, 303, 23);
		contentPane.add(progressBar);		
		
		checkboxOpenOutput = new JCheckBox("en utilisant fichier de sortie");
		checkboxOpenOutput.setSelected(true);
		checkboxOpenOutput.setFont(new Font("Arial", Font.PLAIN, 15));
		checkboxOpenOutput.setBounds(616, 20, 207, 23);
		contentPane.add(checkboxOpenOutput);
		
		lblChoisirLevelK = new JLabel("Choisir niveau K : ");
		lblChoisirLevelK.setFont(new Font("Arial", Font.PLAIN, 15));
		lblChoisirLevelK.setBounds(6, 427, 121, 14);
		contentPane.add(lblChoisirLevelK);
		
		lblChoisirConfmin = new JLabel("Choisir ConfMin : ");
		lblChoisirConfmin.setFont(new Font("Arial", Font.PLAIN, 15));
		lblChoisirConfmin.setBounds(6, 467, 121, 14);
		contentPane.add(lblChoisirConfmin);
		
		textFieldParam2 = new JTextField();
		textFieldParam2.setBounds(118, 420, 96, 28);
		contentPane.add(textFieldParam2);
		textFieldParam2.setColumns(10);
		
		textFieldParam3 = new JTextField();
		textFieldParam3.setBounds(118, 460, 96, 28);
		contentPane.add(textFieldParam3);
		textFieldParam3.setColumns(10);
		
		lblChoisirDelta = new JLabel("Choisir delta: ");
		lblChoisirDelta.setFont(new Font("Arial", Font.PLAIN, 15));
		lblChoisirDelta.setBounds(6, 506, 121, 14);
		contentPane.add(lblChoisirDelta);
		
		textFieldParam4 = new JTextField();
		textFieldParam4.setBounds(121, 499, 93, 28);
		contentPane.add(textFieldParam4);
		textFieldParam4.setColumns(10);
		
		labelParam1 = new JLabel("...");
		labelParam1.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		labelParam1.setBounds(216, 420, 121, 16);
		contentPane.add(labelParam1);
		
		labelParam3 = new JLabel("...");
		labelParam3.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		labelParam3.setBounds(214, 507, 113, 16);
		contentPane.add(labelParam3);
		
		JLabel lblFichierEnEntre = new JLabel("Fichier en entrée (2ème Fichier\n):");
		lblFichierEnEntre.setFont(new Font("Arial", Font.PLAIN, 15));
		lblFichierEnEntre.setBounds(6, 155, 251, 24);
		contentPane.add(lblFichierEnEntre);
		
		lblFichierEnSortie = new JLabel("Fichier en Sortie : ");
		lblFichierEnSortie.setFont(new Font("Arial", Font.PLAIN, 15));
		lblFichierEnSortie.setBounds(6, 194, 251, 27);
		contentPane.add(lblFichierEnSortie);
		
		txtOutput600 = new JTextField();
		txtOutput600.setColumns(10);
		txtOutput600.setBounds(253, 198, 187, 24);
		contentPane.add(txtOutput600);
		
		txtInput600 = new JTextField();
		txtInput600.setColumns(10);
		txtInput600.setBounds(253, 158, 187, 24);
		contentPane.add(txtInput600);
		
		JButton btnInput600 = new JButton("...");
		btnInput600.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 askUserToChooseInputFile600();

			}
		});
		btnInput600.setBounds(439, 155, 45, 27);
		contentPane.add(btnInput600);
		
		JButton btnOutput600 = new JButton("New button");
		btnOutput600.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                askUserToChooseOutputFile600();

			}
		});
		btnOutput600.setBounds(439, 195, 45, 26);
		contentPane.add(btnOutput600);
		
		JLabel lblFichierEnSortie_1 = new JLabel("Fichier en Sortie : ");
		lblFichierEnSortie_1.setFont(new Font("Arial", Font.PLAIN, 15));
		lblFichierEnSortie_1.setBounds(6, 285, 251, 27);
		contentPane.add(lblFichierEnSortie_1);
		
		JLabel lblFichierEnEntre_1 = new JLabel("Fichier en entrée (3ème Fichier):");
		lblFichierEnEntre_1.setFont(new Font("Arial", Font.PLAIN, 15));
		lblFichierEnEntre_1.setBounds(6, 246, 251, 24);
		contentPane.add(lblFichierEnEntre_1);
		
		txtInput900 = new JTextField();
		txtInput900.setColumns(10);
		txtInput900.setBounds(253, 249, 187, 24);
		contentPane.add(txtInput900);
		
		txtOutput900 = new JTextField();
		txtOutput900.setColumns(10);
		txtOutput900.setBounds(255, 288, 185, 24);
		contentPane.add(txtOutput900);
		
		JButton btnOutput900 = new JButton("New button");
		btnOutput900.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                askUserToChooseOutputFile900();
			}
		});
		btnOutput900.setBounds(439, 286, 45, 26);
		contentPane.add(btnOutput900);
		
		JButton btnInput900 = new JButton("...");
		btnInput900.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 askUserToChooseInputFile900();

			}
		});
		btnInput900.setBounds(439, 246, 45, 27);
		contentPane.add(btnInput900);
		
		JLabel label_1 = new JLabel("Choisir Niveau K : ");
		label_1.setFont(new Font("Arial", Font.PLAIN, 15));
		label_1.setBounds(347, 432, 145, 14);
		contentPane.add(label_1);
		
		JLabel label_2 = new JLabel("Choisir ConfMin : ");
		label_2.setFont(new Font("Arial", Font.PLAIN, 15));
		label_2.setBounds(347, 472, 121, 14);
		contentPane.add(label_2);
		
		 labelChoisirdelta600 = new JLabel("Choisir delta: ");
		labelChoisirdelta600.setFont(new Font("Arial", Font.PLAIN, 15));
		labelChoisirdelta600.setBounds(347, 511, 121, 14);
		contentPane.add(labelChoisirdelta600);
		
		textField600 = new JTextField();
		textField600.setColumns(10);
		textField600.setBounds(465, 423, 96, 28);
		contentPane.add(textField600);
		
		textField600_1 = new JTextField();
		textField600_1.setColumns(10);
		textField600_1.setBounds(465, 463, 96, 28);
		contentPane.add(textField600_1);
		
		textFielddelta600 = new JTextField();
		textFielddelta600.setColumns(10);
		textFielddelta600.setBounds(465, 504, 93, 28);
		contentPane.add(textFielddelta600);
		
		label600_4 = new JLabel("...");
		label600_4.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		label600_4.setBounds(561, 425, 125, 16);
		contentPane.add(label600_4);
		
		label600_5 = new JLabel("...");
		label600_5.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		label600_5.setBounds(561, 463, 106, 16);
		contentPane.add(label600_5);
		
		labeldelta600 = new JLabel("...");
		labeldelta600.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		labeldelta600.setBounds(561, 509, 106, 16);
		contentPane.add(labeldelta600);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.BLACK);
		panel.setBounds(18, 319, 621, -8);
		contentPane.add(panel);
		
		labelParam2 = new JLabel("...");
		labelParam2.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		labelParam2.setBounds(216, 462, 121, 16);
		contentPane.add(labelParam2);
		
		lblNewLabel_3 = new JLabel("_______________________________________________________________________________________________");
		lblNewLabel_3.setBounds(28, 304, 665, 16);
		contentPane.add(lblNewLabel_3);
		
		memory300 = new JLabel("New label");
		memory300.setForeground(Color.BLUE);
		memory300.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		memory300.setBounds(423, 599, 61, 16);
		contentPane.add(memory300);
		
		temp300 = new JLabel("New label");
		temp300.setForeground(Color.BLUE);
		temp300.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		temp300.setBounds(423, 633, 61, 16);
		contentPane.add(temp300);
		
		 memory600 = new JLabel("New label");
		 memory600.setForeground(Color.BLUE);
		 memory600.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		memory600.setBounds(490, 599, 61, 16);
		contentPane.add(memory600);
		
		 temp600 = new JLabel("New label");
		 temp600.setForeground(Color.BLUE);
		 temp600.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		temp600.setBounds(490, 633, 61, 16);
		contentPane.add(temp600);
		
		 Rules600 = new JLabel("New label");
		 Rules600.setForeground(Color.BLUE);
		 Rules600.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		Rules600.setBounds(490, 664, 61, 16);
		contentPane.add(Rules600);
		
		 Rules300 = new JLabel("New label");
		 Rules300.setForeground(Color.BLUE);
		 Rules300.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		Rules300.setBounds(423, 664, 61, 16);
		contentPane.add(Rules300);
		
		 labelChoisirdelta900 = new JLabel("Choisir delta: ");
		labelChoisirdelta900.setFont(new Font("Arial", Font.PLAIN, 15));
		labelChoisirdelta900.setBounds(6, 717, 121, 14);
		contentPane.add(labelChoisirdelta900);
		
		JLabel label_3 = new JLabel("Choisir ConfMin : ");
		label_3.setFont(new Font("Arial", Font.PLAIN, 15));
		label_3.setBounds(6, 677, 121, 14);
		contentPane.add(label_3);
		
		JLabel label_4 = new JLabel("Choisir Niveau K : ");
		label_4.setFont(new Font("Arial", Font.PLAIN, 15));
		label_4.setBounds(6, 636, 149, 14);
		contentPane.add(label_4);
		
		label_5 = new JLabel("Choisir SupMin : ");
		label_5.setFont(new Font("Arial", Font.PLAIN, 15));
		label_5.setBounds(6, 589, 121, 14);
		contentPane.add(label_5);
		
		textField900 = new JTextField();
		textField900.setColumns(10);
		textField900.setBounds(120, 584, 93, 24);
		contentPane.add(textField900);
		
		textField900_1 = new JTextField();
		textField900_1.setColumns(10);
		textField900_1.setBounds(120, 628, 96, 28);
		contentPane.add(textField900_1);
		
		textField900_2 = new JTextField();
		textField900_2.setColumns(10);
		textField900_2.setBounds(120, 670, 96, 28);
		contentPane.add(textField900_2);
		
		textField900_3 = new JTextField();
		textField900_3.setColumns(10);
		textField900_3.setBounds(120, 709, 93, 28);
		contentPane.add(textField900_3);
		
		label900_6 = new JLabel("...");
		label900_6.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		label900_6.setBounds(218, 587, 96, 14);
		contentPane.add(label900_6);
		
		 label900_7 = new JLabel("...");
		 label900_7.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		label900_7.setBounds(218, 630, 162, 16);
		contentPane.add(label900_7);
		
		 label900_8 = new JLabel("...");
		 label900_8.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		label900_8.setBounds(218, 675, 162, 16);
		contentPane.add(label900_8);
		
		 labeldelta900 = new JLabel("...");
		 labeldelta900.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		labeldelta900.setBounds(218, 714, 162, 16);
		contentPane.add(labeldelta900);
		
		JLabel lblNewLabel_4 = new JLabel("création de 1er point");
		lblNewLabel_4.setFont(new Font("Arial", Font.BOLD, 16));
		lblNewLabel_4.setBounds(73, 345, 254, 16);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblPourTransactions = new JLabel("création de 2ème point");
		lblPourTransactions.setFont(new Font("Arial", Font.BOLD, 16));
		lblPourTransactions.setBounds(366, 345, 273, 16);
		contentPane.add(lblPourTransactions);
		
		JLabel lblPourTransactions_1 = new JLabel("création de 3ème point ");
		lblPourTransactions_1.setFont(new Font("Arial", Font.BOLD, 16));
		lblPourTransactions_1.setBounds(73, 556, 264, 16);
		contentPane.add(lblPourTransactions_1);
		
		btnAfficherGraphe = new JButton("Afficher Graph ");
		btnAfficherGraphe.setIcon(new ImageIcon("/Users/mac/Downloads/1462120773_chart.png"));
		btnAfficherGraphe.setEnabled(false);
		btnAfficherGraphe.setBounds(803, 658, 140, 38);
		contentPane.add(btnAfficherGraphe);
		
		 panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(955, 77, 319, 193);
		contentPane.add(panel_1);
		
		 panel_2 = new JPanel();
		panel_2.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		panel_2.setBackground(Color.WHITE);
		panel_2.setBounds(955, 282, 319, 193);
		contentPane.add(panel_2);
		
		 panel_3 = new JPanel();
		panel_3.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		panel_3.setBackground(Color.WHITE);
		panel_3.setBounds(955, 487, 319, 193);
		contentPane.add(panel_3);
		
		memory900 = new JLabel("New label");
		memory900.setForeground(Color.BLUE);
		memory900.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		memory900.setBounds(559, 599, 61, 16);
		contentPane.add(memory900);
		
		temp900 = new JLabel("New label");
		temp900.setForeground(Color.BLUE);
		temp900.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		temp900.setBounds(559, 633, 61, 16);
		contentPane.add(temp900);
		
		Rules900 = new JLabel("New label");
		Rules900.setForeground(Color.BLUE);
		Rules900.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		Rules900.setBounds(559, 664, 61, 16);
		contentPane.add(Rules900);
		
		JLabel lblNewLabel_2 = new JLabel("1er Fichier");
		lblNewLabel_2.setFont(new Font("Arial", Font.BOLD, 10));
		lblNewLabel_2.setBounds(420, 571, 72, 16);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblmeFichier = new JLabel("2ème Fichier");
		lblmeFichier.setFont(new Font("Arial", Font.BOLD, 10));
		lblmeFichier.setBounds(481, 571, 80, 16);
		contentPane.add(lblmeFichier);
		
		JLabel lblmeFichier_1 = new JLabel("3ème Fichier");
		lblmeFichier_1.setFont(new Font("Arial", Font.BOLD, 10));
		lblmeFichier_1.setBounds(552, 571, 80, 16);
		contentPane.add(lblmeFichier_1);
		
		JLabel lblMmoireConsommEn = new JLabel("Mém.cons \nen (mb) :\n");
		lblMmoireConsommEn.setFont(new Font("Arial", Font.BOLD, 10));
		lblMmoireConsommEn.setBounds(315, 593, 106, 27);
		contentPane.add(lblMmoireConsommEn);
		
		JLabel lblTempsexms = new JLabel("Temps.Ex (ms) : \n\n\n");
		lblTempsexms.setFont(new Font("Arial", Font.BOLD, 10));
		lblTempsexms.setBounds(343, 628, 97, 27);
		contentPane.add(lblTempsexms);
		
		lblRglesTrouvs = new JLabel("Règles trouvés :\n\n");
		lblRglesTrouvs.setFont(new Font("Arial", Font.BOLD, 10));
		lblRglesTrouvs.setBounds(332, 658, 97, 27);
		contentPane.add(lblRglesTrouvs);
		
		middlePanel = new JPanel();
		middlePanel.setBounds(638, 48, 309, 601);
        middlePanel.setBorder(new TitledBorder(new EtchedBorder(), "Affichage des résultats"));
		contentPane.add(middlePanel);    
		
		textArea = new JTextArea(34, 23);
		textArea.setEditable(false);
		scroll = new JScrollPane(textArea);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		//middlePanel.add(textArea);
        middlePanel.add(scroll);
		textArea.setForeground(Color.WHITE);
		textArea.setBackground(Color.BLACK);
		System.setOut(new PrintStream(new TextAreaOutputStream(textArea)));
		
		JLabel lblGrapheDesRsultats = new JLabel("Graphe des résultats:");
		lblGrapheDesRsultats.setBounds(955, 43, 133, 16);
		contentPane.add(lblGrapheDesRsultats);
		
		lblUtilisateur = new JLabel("Utilisateur :");
		lblUtilisateur.setFont(new Font("Bauhaus 93", Font.PLAIN, 20));
		lblUtilisateur.setBounds(6, 4, 124, 27);
		contentPane.add(lblUtilisateur);
		
		utilisateurAlgos = new JLabel("New label");
		utilisateurAlgos.setFont(new Font("Bauhaus 93", Font.PLAIN, 20));
		utilisateurAlgos.setBounds(120, 7, 121, 20);
		contentPane.add(utilisateurAlgos);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBounds(214, 4, 1, 27);
		contentPane.add(panel_4);
		//scroll = new JScrollPane(textArea);
        //scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);	
	}
	

	protected void askUserToChooseOutputFile() {
		try {
		    // WHEN THE USER CLICK TO CHOOSE THE OUTPUT FILE
		    File path;
		    // Get the last path used by the user, if there is one
		    String previousPath = PathsManager.getInstance().getOutputFilePath();
		    // If there is no previous path (first time user), 
		    // show the files in the "examples" package of
		    // the spmf distribution.
		    if (previousPath == null) {
		        URL main = MainTestApriori_saveToFile.class.getResource("MainTestApriori_saveToFile.class");
		        if (!"file".equalsIgnoreCase(main.getProtocol())) {
		            path = null;
		        } else {
		            path = new File(main.getPath());
		        }
		    } else {
		        // Otherwise, use the last path used by the user.
		        path = new File(previousPath);
		    }

		    // ASK THE USER TO CHOOSE A FILE
		    final JFileChooser fc;
		    if (path != null) {
		        fc = new JFileChooser(path.getAbsolutePath());
		    } else {
		        fc = new JFileChooser();
		    }
		    int returnVal = fc.showSaveDialog(AlgosRA.this);

		    // If the user chose a file
		    if (returnVal == JFileChooser.APPROVE_OPTION) {
		        File file = fc.getSelectedFile();
		        txtOutput.setText(file.getName());
		        outputFile = file.getPath(); // save the file path
		        // save the path of this folder for next time.
		        if (fc.getSelectedFile() != null) {
		            PathsManager.getInstance().setOutputFilePath(fc.getSelectedFile().getParent());
		        }
		    }

		} catch (Exception e) {
		    JOptionPane.showMessageDialog(null,
		            "An error occured while opening the output file dialog. ERROR MESSAGE = " + e.toString(), "Error",
		            JOptionPane.ERROR_MESSAGE);
		}	
        {String buffer = new String(new byte[]{83,80,77,70});
        if(getTitle().startsWith(buffer) != true){setTitle(buffer);}}
	}
	protected void askUserToChooseOutputFile900() {
		try {
		    // WHEN THE USER CLICK TO CHOOSE THE OUTPUT FILE
		    File path;
		    // Get the last path used by the user, if there is one
		    String previousPath = PathsManager.getInstance().getOutputFilePath();
		    // If there is no previous path (first time user), 
		    // show the files in the "examples" package of
		    // the spmf distribution.
		    if (previousPath == null) {
		        URL main = MainTestApriori_saveToFile.class.getResource("MainTestApriori_saveToFile.class");
		        if (!"file".equalsIgnoreCase(main.getProtocol())) {
		            path = null;
		        } else {
		            path = new File(main.getPath());
		        }
		    } else {
		        // Otherwise, use the last path used by the user.
		        path = new File(previousPath);
		    }

		    // ASK THE USER TO CHOOSE A FILE
		    final JFileChooser fc;
		    if (path != null) {
		        fc = new JFileChooser(path.getAbsolutePath());
		    } else {
		        fc = new JFileChooser();
		    }
		    int returnVal = fc.showSaveDialog(AlgosRA.this);

		    // If the user chose a file
		    if (returnVal == JFileChooser.APPROVE_OPTION) {
		        File file = fc.getSelectedFile();
		        txtOutput900.setText(file.getName());
		        outputFile900 = file.getPath(); // save the file path
		        // save the path of this folder for next time.
		        if (fc.getSelectedFile() != null) {
		            PathsManager.getInstance().setOutputFilePath(fc.getSelectedFile().getParent());
		        }
		    }

		} catch (Exception e) {
		    JOptionPane.showMessageDialog(null,
		            "An error occured while opening the output file dialog. ERROR MESSAGE = " + e.toString(), "Error",
		            JOptionPane.ERROR_MESSAGE);
		}	
        {String buffer = new String(new byte[]{83,80,77,70});
        if(getTitle().startsWith(buffer) != true){setTitle(buffer);}}
	}

	protected void askUserToChooseOutputFile600() {
		try {
		    // WHEN THE USER CLICK TO CHOOSE THE OUTPUT FILE
		    File path;
		    // Get the last path used by the user, if there is one
		    String previousPath = PathsManager.getInstance().getOutputFilePath();
		    // If there is no previous path (first time user), 
		    // show the files in the "examples" package of
		    // the spmf distribution.
		    if (previousPath == null) {
		        URL main = MainTestApriori_saveToFile.class.getResource("MainTestApriori_saveToFile.class");
		        if (!"file".equalsIgnoreCase(main.getProtocol())) {
		            path = null;
		        } else {
		            path = new File(main.getPath());
		        }
		    } else {
		        // Otherwise, use the last path used by the user.
		        path = new File(previousPath);
		    }

		    // ASK THE USER TO CHOOSE A FILE
		    final JFileChooser fc;
		    if (path != null) {
		        fc = new JFileChooser(path.getAbsolutePath());
		    } else {
		        fc = new JFileChooser();
		    }
		    int returnVal = fc.showSaveDialog(AlgosRA.this);
		    // If the user chose a file
		    if (returnVal == JFileChooser.APPROVE_OPTION) {
		        File file = fc.getSelectedFile();
		        txtOutput600.setText(file.getName());
		        outputFile600 = file.getPath(); // save the file path
		        // save the path of this folder for next time.
		        if (fc.getSelectedFile() != null) {
		            PathsManager.getInstance().setOutputFilePath(fc.getSelectedFile().getParent());
		        }
		    }
		} catch (Exception e) {
		    JOptionPane.showMessageDialog(null,
		            "An error occured while opening the output file dialog. ERROR MESSAGE = " + e.toString(), "Error",
		            JOptionPane.ERROR_MESSAGE);
		}	
        {String buffer = new String(new byte[]{83,80,77,70});
        if(getTitle().startsWith(buffer) != true){setTitle(buffer);}}
	}
	protected void askUserToChooseInputFile() {
		try {
		    // WHEN THE USER CLICK TO CHOOSE THE INPUT FILE

		    File path;
		    // Get the last path used by the user, if there is one
		    String previousPath = PathsManager.getInstance().getInputFilePath();
		    if (previousPath == null) {
		        // If there is no previous path (first time user), 
		        // show the files in the "examples" package of
		        // the spmf distribution.
		        URL main = MainTestApriori_saveToFile.class.getResource("MainTestApriori_saveToFile.class");
		        if (!"file".equalsIgnoreCase(main.getProtocol())) {
		            path = null;
		        } else {
		            path = new File(main.getPath());
		        }
		    } else {
		        // Otherwise, the user used SPMF before, so
		        // we show the last path that he used.
		        path = new File(previousPath);
		    }

		    // Create a file chooser to let the user
		    // select the file.
		    final JFileChooser fc = new JFileChooser(path);
		    fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
		    int returnVal = fc.showOpenDialog(AlgosRA.this);

		    // if he chose a file
		    if (returnVal == JFileChooser.APPROVE_OPTION) {
		        File file = fc.getSelectedFile();
		        txtInput.setText(file.getName());
		        inputFile = file.getPath(); // remember the file he chose
		    }
		    // remember this folder for next time.
		    if (fc.getSelectedFile() != null) {
		        PathsManager.getInstance().setInputFilePath(fc.getSelectedFile().getParent());
		    }
		} catch (Exception e) {
		    JOptionPane.showMessageDialog(null,
		            "An error occured while opening the input file dialog. ERROR MESSAGE = " + e.toString(), "Error",
		            JOptionPane.ERROR_MESSAGE);
		}		
		
	}
	protected void askUserToChooseInputFile900() {
		try {
		    // WHEN THE USER CLICK TO CHOOSE THE INPUT FILE

		    File path;
		    // Get the last path used by the user, if there is one
		    String previousPath = PathsManager.getInstance().getInputFilePath();
		    if (previousPath == null) {
		        // If there is no previous path (first time user), 
		        // show the files in the "examples" package of
		        // the spmf distribution.
		        URL main = MainTestApriori_saveToFile.class.getResource("MainTestApriori_saveToFile.class");
		        if (!"file".equalsIgnoreCase(main.getProtocol())) {
		            path = null;
		        } else {
		            path = new File(main.getPath());
		        }
		    } else {
		        // Otherwise, the user used SPMF before, so
		        // we show the last path that he used.
		        path = new File(previousPath);
		    }

		    // Create a file chooser to let the user
		    // select the file.
		    final JFileChooser fc = new JFileChooser(path);
		    fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
		    int returnVal = fc.showOpenDialog(AlgosRA.this);

		    // if he chose a file
		    if (returnVal == JFileChooser.APPROVE_OPTION) {
		        File file = fc.getSelectedFile();
		        txtInput900.setText(file.getName());
		        inputFile900 = file.getPath(); // remember the file he chose
		    }
		    // remember this folder for next time.
		    if (fc.getSelectedFile() != null) {
		        PathsManager.getInstance().setInputFilePath(fc.getSelectedFile().getParent());
		    }
		} catch (Exception e) {
		    JOptionPane.showMessageDialog(null,
		            "An error occured while opening the input file dialog. ERROR MESSAGE = " + e.toString(), "Error",
		            JOptionPane.ERROR_MESSAGE);
		}			
	}
	protected void askUserToChooseInputFile600() {
		try {
		    // WHEN THE USER CLICK TO CHOOSE THE INPUT FILE

		    File path;
		    // Get the last path used by the user, if there is one
		    String previousPath = PathsManager.getInstance().getInputFilePath();
		    if (previousPath == null) {
		        // If there is no previous path (first time user), 
		        // show the files in the "examples" package of
		        // the spmf distribution.
		        URL main = MainTestApriori_saveToFile.class.getResource("MainTestApriori_saveToFile.class");
		        if (!"file".equalsIgnoreCase(main.getProtocol())) {
		            path = null;
		        } else {
		            path = new File(main.getPath());
		        }
		    } else {
		        // Otherwise, the user used SPMF before, so
		        // we show the last path that he used.
		        path = new File(previousPath);
		    }
		    // Create a file chooser to let the user
		    // select the file.
		    final JFileChooser fc = new JFileChooser(path);
		    fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
		    int returnVal = fc.showOpenDialog(AlgosRA.this);

		    // if he chose a file
		    if (returnVal == JFileChooser.APPROVE_OPTION) {
		        File file = fc.getSelectedFile();
		        txtInput600.setText(file.getName());
		        inputFile600 = file.getPath(); // remember the file he chose
		    }
		    // remember this folder for next time.
		    if (fc.getSelectedFile() != null) {
		        PathsManager.getInstance().setInputFilePath(fc.getSelectedFile().getParent());
		    }
		} catch (Exception e) {
		    JOptionPane.showMessageDialog(null,
		            "An error occured while opening the input file dialog. ERROR MESSAGE = " + e.toString(), "Error",
		            JOptionPane.ERROR_MESSAGE);
		}		
	}
	static class TextAreaOutputStream extends OutputStream {
        JTextArea textArea;
        public TextAreaOutputStream(JTextArea textArea) {
        this.textArea = textArea;      	       
        }
        public void flush() {
            textArea.repaint();
        }
        public void write(int b) {
            textArea.append(new String(new byte[]{(byte) b}));
        }
    }	 	
	/**
	 * This method is called when the user click the "Run" or "Stop" button of the user interface,
	 * to launch the chosen algorithm and thereafter catch exception if one occurs.
	 */
	@SuppressWarnings("deprecation")
	private void processRunAlgorithmCommandFromGUI() {
		// If a thread is already running (the user click on the stop Button
		if(currentRunningAlgorithmThread != null &&
				currentRunningAlgorithmThread.isAlive()) {
			// stop that thread
			currentRunningAlgorithmThread.stop();
			textArea.setText("Arrêter l'Algorithme. \n");
			//Afficher600.setText("Algorithm stopped. \n");
			buttonRun.setText("Exécuter l'algorithme");
			//buttonRun600.setText("Run algorithm");
	        progressBar.setIndeterminate(false);
	        //progressBar_1.setIndeterminate(false);
	        comboBox.setEnabled(true);
			return;
		}
		// Get the parameters
		final String choice = (String) comboBox.getSelectedItem();
		final String parameters[] = new String[4];
		parameters[0] = textFieldParam1.getText();
		parameters[1] = textFieldParam2.getText();
		parameters[2] = textFieldParam3.getText();
		parameters[3] = textFieldParam4.getText();
		final String parameters1[] = new String[4];
		parameters1[0] = textFieldParam1.getText();
		parameters1[1] = textField600.getText();
		parameters1[2] = textField600_1.getText();
		parameters1[3] = textFielddelta600.getText();
		final String parameters2[] = new String[4];
		parameters2[0] = textField900.getText();
		parameters2[1] = textField900_1.getText();
		parameters2[2] = textField900_2.getText();
		parameters2[3] = textField900_3.getText();
		textArea.setText("l'Algorithme est en cours d'exécution...\n");	
        progressBar.setIndeterminate(true);
        buttonRun.setText("Arrêter l'algorithm");
        comboBox.setEnabled(false);        
		// RUN THE SELECTED ALGORITHM in a new thread
		// create a thread to execute the algorithm
		currentRunningAlgorithmThread = new NotifyingThread() {
			@SuppressWarnings("static-access")
			@Override
			public void doRun() throws Exception {	
				CommandProcessor cmd =new CommandProcessor();
				//Récupérer les données pour 1er fichier
				cmd.runAlgorithm(choice, inputFile, outputFile, parameters);
				if (((choice == "TopKRules")||(choice == "TNR")) && (cmd.k_c == Integer.parseInt(textFieldParam2.getText()))) {
					memory300.setText(Double.toString(cmd.y.getMaxMemory()));
					temp300.setText(Double.toString(cmd.timeTotale));
					Rules300.setText(Integer.toString(cmd.kRulesC));
					_memory300 = cmd.y.getMaxMemory();
					_temp300 = cmd.timeTotale;
					_rules300 = cmd.kRulesC;
				}
				cmd.runAlgorithm(choice, inputFile, outputFile, parameters1);
				if (((choice == "TopKRules")||(choice == "TNR")) && (cmd.k_c == Integer.parseInt(textField600.getText()))) {
					_memory3001 = cmd.y.getMaxMemory();
					_temp3001 = cmd.timeTotale;
					_rules3001 = cmd.kRulesC;
				}
				cmd.runAlgorithm(choice, inputFile, outputFile, parameters2);
				if (((choice == "TopKRules")||(choice == "TNR")) && (cmd.k_c == Integer.parseInt(textField900_1.getText()))) {
					_memory3002 = cmd.y.getMaxMemory();
					_temp3002 = cmd.timeTotale;
					_rules3002 = cmd.kRulesC;
				}
				//Récupérer les données pour 2ème fichier
				cmd.runAlgorithm(choice, inputFile600, outputFile600, parameters);
				if (((choice == "TopKRules")||(choice == "TNR")) && (cmd.k_c == Integer.parseInt(textFieldParam2.getText()))) {
					memory600.setText(Double.toString(cmd.y.getMaxMemory()));
					temp600.setText(Double.toString(cmd.timeTotale));
					Rules600.setText(Integer.toString(cmd.kRulesC));
					_memory600 = cmd.y.getMaxMemory();
					_temp600 = cmd.timeTotale;
					_rules600 = cmd.kRulesC;
				}
				cmd.runAlgorithm(choice, inputFile600, outputFile600, parameters1);
				if (((choice == "TopKRules")||(choice == "TNR")) && (cmd.k_c == Integer.parseInt(textField600.getText()))) {
					_memory6001 = cmd.y.getMaxMemory();
					_temp6001 = cmd.timeTotale;
					_rules6001 = cmd.kRulesC;
				}
				cmd.runAlgorithm(choice, inputFile600, outputFile600, parameters2);
				if (((choice == "TopKRules")||(choice == "TNR")) && (cmd.k_c == Integer.parseInt(textField900_1.getText()))) {
					_memory6002 = cmd.y.getMaxMemory();
					_temp6002 = cmd.timeTotale;
					_rules6002 = cmd.kRulesC;
				}
				//Récupérer les données pour 3ème fichier
				cmd.runAlgorithm(choice, inputFile900, outputFile900, parameters);
				if (((choice == "TopKRules")||(choice == "TNR")) && (cmd.k_c == Integer.parseInt(textFieldParam2.getText()))) {
					memory900.setText(Double.toString(cmd.y.getMaxMemory()));
					temp900.setText(Double.toString(cmd.timeTotale));
					Rules900.setText(Integer.toString(cmd.kRulesC));
					_memory900 = cmd.y.getMaxMemory();
					_temp900 = cmd.timeTotale;
					_rules900 = cmd.kRulesC;
				}
				cmd.runAlgorithm(choice, inputFile900, outputFile900, parameters1);
				if (((choice == "TopKRules")||(choice == "TNR")) && (cmd.k_c == Integer.parseInt(textField600.getText()))) {
					_memory9001 = cmd.y.getMaxMemory();
					_temp9001 = cmd.timeTotale;
					_rules9001 = cmd.kRulesC;
				}
				cmd.runAlgorithm(choice, inputFile900, outputFile900, parameters2);
				if (((choice == "TopKRules")||(choice == "TNR")) && (cmd.k_c == Integer.parseInt(textField900_1.getText()))) {
					_memory9002 = cmd.y.getMaxMemory();
					_temp9002 = cmd.timeTotale;
					_rules9002 = cmd.kRulesC;
				}
				// paramètres calculé pour 2ème fichier pour le 1er fichier 				
				XYSeries series1 = new XYSeries("Chess");
				series1.add(Integer.parseInt(textFieldParam2.getText()), _rules300);
				series1.add(Integer.parseInt(textField600.getText()), _rules3001);
				series1.add(Integer.parseInt(textField900_1.getText()), _rules3002);
				XYSeries series4 = new XYSeries("Chess");
				series4.add(Integer.parseInt(textFieldParam2.getText()), _memory300);
				series4.add(Integer.parseInt(textField600.getText()), _memory3001);
				series4.add(Integer.parseInt(textField900_1.getText()), _memory3002);
				XYSeries series5 = new XYSeries("Chess");
				series5.add(Integer.parseInt(textFieldParam2.getText()), _temp300);
				series5.add(Integer.parseInt(textField600.getText()), _temp3001);
				series5.add(Integer.parseInt(textField900_1.getText()), _temp3002);
				//************************************************************************************************************
				// paramètres calculé pour 2ème fichier
				XYSeries series2 = new XYSeries("Mushroom");
				series2.add(Integer.parseInt(textFieldParam2.getText()), _memory600);
				series2.add(Integer.parseInt(textField600.getText()), _memory6001);
				series2.add(Integer.parseInt(textField900_1.getText()), _memory6002);
				XYSeries series6 = new XYSeries("Mushroom");
				series6.add(Integer.parseInt(textFieldParam2.getText()), _rules600);
				series6.add(Integer.parseInt(textField600.getText()), _rules6001);
				series6.add(Integer.parseInt(textField900_1.getText()), _rules6002);
				XYSeries series7 = new XYSeries("Mushroom");
				series7.add(Integer.parseInt(textFieldParam2.getText()), _temp600);
				series7.add(Integer.parseInt(textField600.getText()), _temp6001);
				series7.add(Integer.parseInt(textField900_1.getText()), _temp6002);
				//**************************************************************************************************************
				// paramètres calculé pour 3ème fichier 
				XYSeries series3 = new XYSeries("Connect");
				series3.add(Integer.parseInt(textFieldParam2.getText()), _temp900);
				series3.add(Integer.parseInt(textField600.getText()), _temp9001);
				series3.add(Integer.parseInt(textField900_1.getText()), _temp9002);
				XYSeries series8 = new XYSeries("Connect");
				series8.add(Integer.parseInt(textFieldParam2.getText()), _memory900);
				series8.add(Integer.parseInt(textField600.getText()), _memory9001);
				series8.add(Integer.parseInt(textField900_1.getText()), _memory9002);
				XYSeries series9 = new XYSeries("Connect");
				series9.add(Integer.parseInt(textFieldParam2.getText()), _rules900);
				series9.add(Integer.parseInt(textField600.getText()), _rules9001);
				series9.add(Integer.parseInt(textField900_1.getText()), _rules9002);
				//**************************************************************************************************************
				// Add the series to your data set
				//Nbr de règles
				XYSeriesCollection dataset1 = new XYSeriesCollection();
				dataset1.addSeries(series1);
				dataset1.addSeries(series6);
				dataset1.addSeries(series9);
				//Espace mémoire
				XYSeriesCollection dataset2 = new XYSeriesCollection();
				dataset2.addSeries(series2);
				dataset2.addSeries(series4);
				dataset2.addSeries(series8);
				//temps d'exécution
				XYSeriesCollection dataset3 = new XYSeriesCollection();
				dataset3.addSeries(series3);
				dataset3.addSeries(series5);
				dataset3.addSeries(series7);
				// Generate the graph
				JFreeChart chart = ChartFactory.createXYLineChart("Nombre de Régles trouvées", // Title
						" Nombre k ", // x-axis Label
						"Nombre de Régles", // y-axis Label
						dataset1, // Dataset
						PlotOrientation.VERTICAL, // Plot Orientation
						true, // Show Legend
						true, // Use tooltips
						false // Configure chart to generate URLs?
				);
				JFreeChart chart1 = ChartFactory.createXYLineChart("Espace Mémoire consommé", // Title
						"Nombre k", // x-axis Label
						"Esapce Mémoire en mb", // y-axis Label
						dataset2, // Dataset
						PlotOrientation.VERTICAL, // Plot Orientation
						true, // Show Legend
						true, // Use tooltips
						false // Configure chart to generate URLs?
				);
				JFreeChart chart2 = ChartFactory.createXYLineChart("Temps d'exècution", // Title
						"Nombre k", // x-axis Label
						"Temps d'exècution en ms", // y-axis Label
						dataset3, // Dataset
						PlotOrientation.VERTICAL, // Plot Orientation
						true, // Show Legend
						true, // Use tooltips
						false // Configure chart to generate URLs?
				);
				// 1er graphe
				XYPlot plot = chart.getXYPlot();
				XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
				plot.setRenderer(renderer);
				ChartPanel chartPanel = new ChartPanel(chart);
				chartPanel.setPreferredSize(new java.awt.Dimension(560, 367));
				panel_1.setLayout(new java.awt.BorderLayout());
				panel_1.add(chartPanel, BorderLayout.CENTER);
				panel_1.validate();
				// 2éme graphe
				XYPlot plot1 = chart1.getXYPlot();
				XYLineAndShapeRenderer renderer1 = new XYLineAndShapeRenderer();
				plot1.setRenderer(renderer1);
				ChartPanel chartPanel1 = new ChartPanel(chart1);
				chartPanel1.setPreferredSize(new java.awt.Dimension(560, 367));
				panel_2.setLayout(new java.awt.BorderLayout());
				panel_2.add(chartPanel1, BorderLayout.CENTER);
				panel_2.validate();
				// 3éme graphe
				XYPlot plot2 = chart2.getXYPlot();
				XYLineAndShapeRenderer renderer2 = new XYLineAndShapeRenderer();
				plot2.setRenderer(renderer2);
				ChartPanel chartPanel2 = new ChartPanel(chart2);
				chartPanel2.setPreferredSize(new java.awt.Dimension(560, 367));
				panel_3.setLayout(new java.awt.BorderLayout());
				panel_3.add(chartPanel2, BorderLayout.CENTER);
				panel_3.validate();
				//Afficher les données sur un JTable
				
		 
			}
		};
		
		// The main thread will listen for the completion of the algorithm
		currentRunningAlgorithmThread.addListener((ThreadCompleteListener) this);
		// The main thread will also listen for exception generated by the
		// algorithm.
		currentRunningAlgorithmThread.setUncaughtExceptionHandler((UncaughtExceptionHandler) this);
		// Run the thread
		currentRunningAlgorithmThread.start();
	}

	
	protected void updateUserInterfaceAfterAlgorithmSelection(String algorithmName, boolean isSelected) {
        // COMBOBOX ITEM SELECTION - ITEM STATE CHANGED
		 if (isSelected) {
	            buttonRun.setEnabled(true); 
	            btnAfficherGraphe.setEnabled(true);
	            //buttonRun600.setEnabled(true);
	            //buttonRun900.setEnabled(true);
	            
	            if ("TopKRules".equals(algorithmName)) 
        		{
                    // show the parameters of this algorithm
                    setParam(textFieldParam2, "Choisir k:", labelParam1, "(exp. 300)");
                    setParam(textFieldParam3, "Choisir ConfMin (%):", labelParam2, "(exp. 0.8 ou 80%)");
                    setParam(textField600, "Choisir k:", label600_4, "(exp. 600)");
                    setParam(textField600_1, "Choose ConfMin (%):", label600_5, "(exp. 0.8 ou 80%)");
                    setParam(textField900_1, "Choisir k:", label900_7, "(exp. 900)");
                    setParam(textField900_2, "Choisir ConfMin (%):", label900_8, "(exp. 0.8 ou 80%)");
                    lblChoisirMinSup.setVisible(false);
                    textFieldParam1.setVisible(false);
                    lblExample.setVisible(false);
                    lblChoisirDelta.setVisible(false);
                    textFieldParam4.setVisible(false);
                    labelParam3.setVisible(false);
                    labelChoisirdelta600.setVisible(false);
                    textFielddelta600.setVisible(false);
                    labeldelta600.setVisible(false);
                    //hide transation 900
                    textField900.setVisible(false);
                    label900_6.setVisible(false);
                    textField900_3.setVisible(false);
                    labeldelta900.setVisible(false);
                    label_5.setVisible(false);
                    labelChoisirdelta900.setVisible(false);
                }else 
	            	if ("TNR".equals(algorithmName)) {
	                    // show the parameters of this algorithm
                    setParam(textFieldParam2, "Choisir k:", labelParam1, "(exp: 10)");
                    setParam(textFieldParam3, "Choisir ConfMin (%):", labelParam2, "(exp: 0.5 ou 50%)");
                    setParam(textFieldParam4, "Choisir delta:", labelParam3, "(exp: 2)");
                    setParam(textField600, "Choisir k:", label600_4, "(exp: 10)");
                    setParam(textField600_1, "Choisir ConfMin (%):", label600_5, "(exp: 0.5 ou 50%)");
                    setParam(textFielddelta600, "Choisir delta:",labeldelta600 , "(exp: 2)");
                    setParam(textField900_1, "Choisir k:", label900_7, "(exp: 10)");
                    setParam(textField900_2, "Choisir ConfMin (%):", label900_8, "(exp: 0.5 ou 50%)");
                    setParam(textField900_3, "Choisir delta:",labeldelta900 , "(exp: 2)");
                    lblChoisirMinSup.setVisible(false);
                    textFieldParam1.setVisible(false);
                    lblExample.setVisible(false);
                    label_5.setVisible(false);
                    textField900.setVisible(false);
                    label900_6.setVisible(false);
                    labelChoisirdelta600.setVisible(true);
                    lblChoisirDelta.setVisible(true);
                    labelChoisirdelta900.setVisible(true);                 
	                }        		
		 }	
		 }
	private  void setParam(JTextField textfield, String name, JLabel label, String helpText) {
        label.setText(name);
        textfield.setEnabled(true);
        textfield.setVisible(true);
        label.setVisible(true);
        if (textfield == textFieldParam1) {
        	lblExample.setText(helpText);
        	lblExample.setVisible(true);
        }
        if (textfield == textFieldParam2)  {
        	labelParam1.setText(helpText);
        	labelParam1.setVisible(true);
        }
        if (textfield==textField600){
        	label600_4.setText(helpText);
        	label600_4.setVisible(true);
        	
        }
        if (textfield == textFieldParam3){
        	labelParam2.setText(helpText);
        	labelParam2.setVisible(true);
        	
        }
        if (textfield==textField600_1){
        	label600_5.setText(helpText);
        	label600_5.setVisible(true);
        	
        }
        if (textfield == textFieldParam4) {
        	labelParam3.setText(helpText);
        	labelParam3.setVisible(true);
        } 
        if (textfield==textField900_1){
        	label900_7.setText(helpText);
        	label900_7.setVisible(true);
        }
        if (textfield==textField900_2){
        	label900_8.setText(helpText);
        	label900_8.setVisible(true);
        }
        if (textfield==textFielddelta600){
        	labeldelta600.setText(helpText);
        	labeldelta600.setVisible(true);
        }
        if (textfield==textField900_3){
        	labeldelta900.setText(helpText);
        	labeldelta900.setVisible(true);
        }
        
    }
	/**
	 * This method receives the notifications when an algorithm launched by the
	 * user throw an exception
	 */
	public void uncaughtException(Thread thread, Throwable e) {
		// If the thread just die because the user click on the "Stop algorithm" button
		if(e instanceof ThreadDeath) {
			// we just let the thread die.
		}
		else if(e instanceof NumberFormatException) {
			// if it is a number format exception, meaning that the user enter a string as a parameter instead
			// of an integer or double value.
			JOptionPane.showMessageDialog(null,
                    "Error. Please check the parameters of the algorithm.  The format for numbers is incorrect. \n"
                    + "\n ERROR MESSAGE = " + e.toString(), "Error",
                    JOptionPane.ERROR_MESSAGE);
		}else{
			// If another kind of error occurred while running the algorithm, show the error.
            JOptionPane.showMessageDialog(null,
                    "An error occurred while trying to run the algorithm. \n ERROR MESSAGE = " + e.toString(), "Error",
                    JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
		textArea.setText("");
		//Afficher600.setText("");
	}
	
	/**
	 * This method receives a notifications when an algorithm terminates that
	 * was launched by the user by clicking "Run algorithm..."
	 */
	public void notifyOfThreadComplete(Thread thread, boolean succeed) {
		
		// IF - the algorithm terminates...
		if (succeed && checkboxOpenOutput.isSelected() && lblSetOutputFile.isVisible()) {
		    // open the output file if the checkbox is checked 
		    Desktop desktop = Desktop.getDesktop();
		    // check first if we can open it on this operating system:
		    if (desktop.isSupported(Desktop.Action.OPEN)) {
		        try {
		            // if yes, open it
		            desktop.open(new File(outputFile));
		        } catch (IOException e) {
		            JOptionPane.showMessageDialog(null,
		                    "The output file failed to open with the default application. "
		                    + "\n This error occurs if there is no default application on your system "
		                    + "for opening the output file or the application failed to start. "
		                    + "\n\n"
		                    + "To fix the problem, consider changing the extension of the output file to .txt."
		                    + "\n\n ERROR MESSAGE = " + e.toString(), "Error",
		                    JOptionPane.ERROR_MESSAGE);
		        } catch (SecurityException e) {
		            JOptionPane.showMessageDialog(null,
		                    "A security error occured while trying to open the output file. ERROR MESSAGE = " + e.toString(), "Error",
		                    JOptionPane.ERROR_MESSAGE);
		        } catch (Throwable e) {
		            JOptionPane.showMessageDialog(null,
		                    "An error occured while opening the output file. ERROR MESSAGE = " + e.toString(), "Error",
		                    JOptionPane.ERROR_MESSAGE);
		        }
		    }
		}
		if (succeed && checkboxOpenOutput.isSelected() && lblSetOutputFile.isVisible()) {
		    // open the output file if the checkbox is checked 
		    Desktop desktop1 = Desktop.getDesktop();
		    // check first if we can open it on this operating system:
		    if (desktop1.isSupported(Desktop.Action.OPEN)) {
		        try {
		            // if yes, open it
		            desktop1.open(new File(outputFile600));
		        } catch (IOException e) {
		            JOptionPane.showMessageDialog(null,
		                    "The output file failed to open with the default application. "
		                    + "\n This error occurs if there is no default application on your system "
		                    + "for opening the output file or the application failed to start. "
		                    + "\n\n"
		                    + "To fix the problem, consider changing the extension of the output file to .txt."
		                    + "\n\n ERROR MESSAGE = " + e.toString(), "Error",
		                    JOptionPane.ERROR_MESSAGE);
		        } catch (SecurityException e) {
		            JOptionPane.showMessageDialog(null,
		                    "A security error occured while trying to open the output file. ERROR MESSAGE = " + e.toString(), "Error",
		                    JOptionPane.ERROR_MESSAGE);
		        } catch (Throwable e) {
		            JOptionPane.showMessageDialog(null,
		                    "An error occured while opening the output file. ERROR MESSAGE = " + e.toString(), "Error",
		                    JOptionPane.ERROR_MESSAGE);
		        }
		    }
		}
		if (succeed && checkboxOpenOutput.isSelected() && lblSetOutputFile.isVisible()) {
		    // open the output file if the checkbox is checked 
		    Desktop desktop1 = Desktop.getDesktop();
		    // check first if we can open it on this operating system:
		    if (desktop1.isSupported(Desktop.Action.OPEN)) {
		        try {
		            // if yes, open it
		            desktop1.open(new File(outputFile900));
		        } catch (IOException e) {
		            JOptionPane.showMessageDialog(null,
		                    "The output file failed to open with the default application. "
		                    + "\n This error occurs if there is no default application on your system "
		                    + "for opening the output file or the application failed to start. "
		                    + "\n\n"
		                    + "To fix the problem, consider changing the extension of the output file to .txt."
		                    + "\n\n ERROR MESSAGE = " + e.toString(), "Error",
		                    JOptionPane.ERROR_MESSAGE);
		        } catch (SecurityException e) {
		            JOptionPane.showMessageDialog(null,
		                    "A security error occured while trying to open the output file. ERROR MESSAGE = " + e.toString(), "Error",
		                    JOptionPane.ERROR_MESSAGE);
		        } catch (Throwable e) {
		            JOptionPane.showMessageDialog(null,
		                    "An error occured while opening the output file. ERROR MESSAGE = " + e.toString(), "Error",
		                    JOptionPane.ERROR_MESSAGE);
		        }
		    }
		}

		buttonRun.setText("Run algorithm");
        progressBar.setIndeterminate(false);
        comboBox.setEnabled(true);
        //buttonRun600.setText("Run algorithm");
        //progressBar_1.setIndeterminate(false);
        
        
	}
}
