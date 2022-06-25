import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import java.awt.Color;
import java.awt.Font;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;

public class SHA256GUI {

	private JFrame frmTpCrypto;
	private JTextField txtin;
	private JTextField bintxt;
	private JRadioButton sha1;
	private JRadioButton sha2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SHA256GUI window = new SHA256GUI();
					window.frmTpCrypto.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public SHA256GUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmTpCrypto = new JFrame();
		frmTpCrypto.setTitle("TP_04 CRYPTO");
		frmTpCrypto.getContentPane().setBackground(Color.DARK_GRAY);
		frmTpCrypto.setBounds(100, 100, 715, 485);
		frmTpCrypto.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmTpCrypto.getContentPane().setLayout(null);
		
		JLabel lblEnterTextTo = new JLabel("Enter Text to be hashed");
		lblEnterTextTo.setForeground(Color.ORANGE);
		lblEnterTextTo.setBounds(50, 12, 226, 39);
		frmTpCrypto.getContentPane().add(lblEnterTextTo);
		
		sha1 = new JRadioButton("SHA1");
		sha1.setForeground(Color.ORANGE);
		sha1.setBackground(Color.DARK_GRAY);
		sha1.setBounds(427, 49, 98, 23);
		frmTpCrypto.getContentPane().add(sha1);
		
		sha2 = new JRadioButton("SHA-256");
		sha2.setForeground(Color.ORANGE);
		sha2.setBackground(Color.DARK_GRAY);
		sha2.setBounds(427, 76, 98, 23);
		frmTpCrypto.getContentPane().add(sha2);
		
		
		ButtonGroup gpp = new ButtonGroup();
		gpp.add(sha1);
		gpp.add(sha2);
		
		txtin = new JTextField();
		txtin.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 16));
		txtin.setForeground(Color.CYAN);
		txtin.setBackground(Color.BLACK);
		txtin.setBounds(50, 49, 351, 44);
		frmTpCrypto.getContentPane().add(txtin);
		txtin.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(50, 208, 612, 181);
		frmTpCrypto.getContentPane().add(scrollPane);
		
		JTextArea txtout = new JTextArea();
		txtout.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 12));
		txtout.setForeground(Color.GREEN);
		txtout.setBackground(Color.BLACK);
		scrollPane.setViewportView(txtout);
		
		bintxt = new JTextField();
		bintxt.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 16));
		bintxt.setForeground(Color.CYAN);
		bintxt.setBackground(Color.BLACK);
		bintxt.setColumns(10);
		bintxt.setBounds(50, 136, 612, 44);
		frmTpCrypto.getContentPane().add(bintxt);
		
		JLabel lblBinaryCode = new JLabel("Tabeal d'octet");
		lblBinaryCode.setForeground(Color.ORANGE);
		lblBinaryCode.setBounds(50, 97, 226, 39);
		frmTpCrypto.getContentPane().add(lblBinaryCode);
		
		JLabel lblTpByHadjazi = new JLabel("TP-4 by Hadjazi and Amour");
		lblTpByHadjazi.setForeground(Color.CYAN);
		lblTpByHadjazi.setBounds(439, 411, 223, 15);
		frmTpCrypto.getContentPane().add(lblTpByHadjazi);
		
		JButton btnText = new JButton("HASH Text");
		btnText.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
				
				if (sha1.isSelected()) {
					
					String msg = txtin.getText();
					
					bintxt.setText(SHA11(msg));
					txtout.append(SHA1(msg));
					
					
				} else if(sha2.isSelected()) {
					
                    String msg = txtin.getText();
					
					bintxt.setText(SHA22(msg));
					txtout.append(SHA2(msg));
					
				}else {
					txtout.append("Please select a Hashing method !!!!");
				}
				
				

				
			}
		});
		btnText.setForeground(Color.CYAN);
		btnText.setBackground(SystemColor.activeCaption);
		btnText.setBounds(533, 75, 130, 25);
		frmTpCrypto.getContentPane().add(btnText);
	}
	
	
	
	
	
	public static String SHA1(String input) {

        try {

            MessageDigest md = MessageDigest.getInstance("SHA-1");
            byte[] messageDigest = md.digest(input.getBytes());
            BigInteger no = new BigInteger(1, messageDigest);
            String hashtext = no.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }

            return hashtext;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
	
	
	public static String SHA11(String input) {

        try {

            MessageDigest md = MessageDigest.getInstance("SHA-1");
            byte[] messageDigest = md.digest(input.getBytes());
            BigInteger no = new BigInteger(1, messageDigest);
            String hashtext = no.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }

            return Arrays.toString(messageDigest);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
	
	
    public static String SHA2(String input) {

        try {

            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] messageDigest = md.digest(input.getBytes());
            BigInteger no = new BigInteger(1, messageDigest);
            String hashtext = no.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }

            return hashtext;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
    
    
    public static String SHA22(String input) {

        try {

            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] messageDigest = md.digest(input.getBytes());
            BigInteger no = new BigInteger(1, messageDigest);
            String hashtext = no.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }

            return Arrays.toString(messageDigest);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
    
    
    
}
