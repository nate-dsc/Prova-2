package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class PainelMenor extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static final Color VERMELHO = Color.RED;
	public static final Color VERDE = Color.GREEN;
	public static final Color JAVA = new Color(184, 81, 2);
	public static final Color C = new Color(2, 60, 153);
	public static final Color CMAIS = new Color(6, 74, 184);
	public static final Color CSHARP = new Color(63, 6, 120);
	public static final Color PHP = new Color(104, 89, 150);
	public static final Color RUBY = new Color(255, 41, 55);
	public static final Color JAVASCRIPT = new Color(250, 242, 5);
	public static final Color PYTHON = new Color(59, 113, 237);
	
	public static final Font BUGDEV = new Font("Dialog", Font.BOLD, 20);
	public static final Font FJAVA = new Font("Dialog", Font.BOLD, 18);
	public static final Font FPYTHON = new Font("Dialog", Font.BOLD, 13);
	public static final Font FRUBY = new Font("Dialog", Font.BOLD, 17);
	public static final Font FPHP = new Font("Dialog", Font.BOLD, 21);
	public static final Font JSCSHARP = new Font("Dialog", Font.BOLD, 31);
	public static final Font FCMAIS = new Font("Dialog", Font.BOLD, 23);
	public static final Font FC = new Font("Dialog", Font.BOLD, 37);
	
	JLabel label;
	
	PainelMenor()
	{
		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(50, 50));
		
		Border borda = BorderFactory.createLineBorder(Color.WHITE, 1);
		setBorder(borda);
		
		label = new JLabel();
		label.setSize(48, 48);
		label.setHorizontalAlignment(JLabel.CENTER);
		add(label, BorderLayout.CENTER);
	}
	
	public void atualizarPainelMenor(char tipo)
	{
		switch(tipo)
		{
		case 'V':
			setBackground(Color.PINK);
			label.setText("");
			break;
		case '+':
			setBackground(VERMELHO);
			label.setText("BUG");
			label.setFont(BUGDEV);
			label.setForeground(Color.WHITE);
			break;
		case '-':
			setBackground(VERDE);
			label.setText("DEV");
			label.setFont(BUGDEV);
			label.setForeground(Color.WHITE);
			break;
		case 'J':
			setBackground(JAVA);
			label.setText("Java");
			label.setFont(FJAVA);
			label.setForeground(Color.WHITE);
			break;
		case '1':
			setBackground(PYTHON);
			label.setText("Python");
			label.setFont(FPYTHON);
			label.setForeground(Color.YELLOW);
			break;
		case '2':
			setBackground(JAVASCRIPT);
			label.setText("JS");
			label.setFont(JSCSHARP);
			label.setForeground(Color.BLACK);
			break;
		case '3':
			setBackground(RUBY);
			label.setText("Ruby");
			label.setFont(FRUBY);
			label.setForeground(Color.WHITE);
			break;
		case '4':
			setBackground(PHP);
			label.setText("PHP");
			label.setFont(FPHP);
			label.setForeground(Color.WHITE);
			break;
		case '5':
			setBackground(CSHARP);
			label.setText("C#");
			label.setFont(JSCSHARP);
			label.setForeground(Color.WHITE);
			break;
		case '6':
			setBackground(CMAIS);
			label.setText("C++");
			label.setFont(FCMAIS);
			label.setForeground(Color.WHITE);
			break;
		case '7':
			setBackground(C);
			label.setText("C");
			label.setFont(FC);
			label.setForeground(Color.WHITE);
			break;
		}
	}
	
	public JLabel getLabel()
	{
		return this.label;
	}
}
