package main;
import javax.swing.*;
import javax.swing.border.Border;

import java.awt.*;
@SuppressWarnings("serial")
public class SplashScreen extends JWindow {
    private int duration=0;
	public SplashScreen(int duration) {
		// TODO Auto-generated constructor stub
		this.duration = duration;
		JPanel panel = (JPanel) getContentPane();
		ImageIcon image = new ImageIcon("images/splash.jpg");
		panel.add(new JLabel(image),BorderLayout.CENTER);
		JProgressBar progressbar = new JProgressBar();
		//progressbar.setValue(25);
		progressbar.setMinimum( 0 );
		progressbar.setMaximum( 100 );
		for (int i = 0; i <= 100; i++) {
		      //jl.setText("Count : " + i);
			progressbar.setValue(i);
		      if(progressbar.getValue() == 100){    
		      }
		}
		progressbar.setStringPainted(true);
	    Border border = BorderFactory.createTitledBorder("Chargement...");
	    progressbar.setBorder(border);
		panel.add(progressbar,BorderLayout.SOUTH);
		setSize(image.getIconWidth(),image.getIconHeight());
		setLocationRelativeTo(null);
		setVisible(true);
		try
		{
			Thread.sleep(duration);
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
		setVisible(false);
		Login log =new Login();
		log.setVisible(true);
	}
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub
         new SplashScreen(5000);
	}

}
