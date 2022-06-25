import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class xteaGUI {

	private JFrame frmTpCrypto;
	private JTextField txtin;
	private JRadioButton mode1;
	private JRadioButton mode2;
	private JRadioButton mode3;
	private JRadioButton mode4;
	private JRadioButton mode5;
	final static int BLOCKSIZE = 4;
    final static int DELTA = 0x9e3779b9;
    final static int ROUNDS = 32;
    final static int IV = 0x45ca1292;
    static int KEY;
    static String plainText;
    static JTextArea encryptionTextArea;
    final static String newLine = "\n";
    private JLabel lblKey;
    private JTextField keyin;
    private JButton btnDencryptXtea;
    int[][] c;
	

    
    /**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					xteaGUI window = new xteaGUI();
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
	public xteaGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmTpCrypto = new JFrame();
		frmTpCrypto.setTitle("TP_03 CRYPTO");
		frmTpCrypto.getContentPane().setBackground(Color.DARK_GRAY);
		frmTpCrypto.setBounds(100, 100, 715, 485);
		frmTpCrypto.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmTpCrypto.getContentPane().setLayout(null);
		
		JLabel lblEnterTextTo = new JLabel("Enter Text to be encrypted");
		lblEnterTextTo.setForeground(Color.ORANGE);
		lblEnterTextTo.setBounds(50, 12, 226, 39);
		frmTpCrypto.getContentPane().add(lblEnterTextTo);
		
		mode1 = new JRadioButton("ECB");
		mode1.setForeground(Color.ORANGE);
		mode1.setBackground(Color.DARK_GRAY);
		mode1.setBounds(60, 147, 64, 23);
		frmTpCrypto.getContentPane().add(mode1);
		
		mode2 = new JRadioButton("CBC");
		mode2.setForeground(Color.ORANGE);
		mode2.setBackground(Color.DARK_GRAY);
		mode2.setBounds(162, 147, 64, 23);
		frmTpCrypto.getContentPane().add(mode2);
		
		mode4 = new JRadioButton("CTR");
		mode4.setForeground(Color.ORANGE);
		mode4.setBackground(Color.DARK_GRAY);
		mode4.setBounds(366, 147, 64, 23);
		frmTpCrypto.getContentPane().add(mode4);
		
		mode3 = new JRadioButton("OFB");
		mode3.setForeground(Color.ORANGE);
		mode3.setBackground(Color.DARK_GRAY);
		mode3.setBounds(264, 147, 69, 23);
		frmTpCrypto.getContentPane().add(mode3);
		
		mode5 = new JRadioButton("XTS");
		mode5.setForeground(Color.ORANGE);
		mode5.setBackground(Color.DARK_GRAY);
		mode5.setBounds(455, 147, 69, 23);
		frmTpCrypto.getContentPane().add(mode5);
		
		
		ButtonGroup gpp = new ButtonGroup();
		gpp.add(mode1);
		gpp.add(mode2);
		gpp.add(mode3);
		gpp.add(mode4);
		gpp.add(mode5);
		
		txtin = new JTextField();
		txtin.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 15));
		txtin.setForeground(Color.CYAN);
		txtin.setBackground(Color.BLACK);
		txtin.setBounds(50, 49, 365, 44);
		frmTpCrypto.getContentPane().add(txtin);
		txtin.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(50, 208, 612, 181);
		frmTpCrypto.getContentPane().add(scrollPane);
		
		encryptionTextArea = new JTextArea();
		encryptionTextArea.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 12));
		encryptionTextArea.setForeground(Color.GREEN);
		encryptionTextArea.setBackground(Color.BLACK);
		scrollPane.setViewportView(encryptionTextArea);
		
		JLabel lblBinaryCode = new JLabel("Operation Mode");
		lblBinaryCode.setForeground(Color.ORANGE);
		lblBinaryCode.setBounds(50, 97, 226, 39);
		frmTpCrypto.getContentPane().add(lblBinaryCode);
		
		JLabel lblTpByHadjazi = new JLabel("TP-3 by Hadjazi and Amour");
		lblTpByHadjazi.setForeground(Color.CYAN);
		lblTpByHadjazi.setBounds(439, 411, 223, 15);
		frmTpCrypto.getContentPane().add(lblTpByHadjazi);
		
		JButton btnText = new JButton("Encrypt XTEA");
		btnText.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
				
				if (mode1.isSelected()) {
					
					String plainText = txtin.getText();
					 KEY = Integer.parseInt(keyin.getText());
					
					encryptionTextArea.append("===============<ECB encryption>===============" + newLine);
					encryptionTextArea.append("<<Plain text blocks>>" + newLine);
					c = ECBencrypt(plainText, KEY);	//encrypt

					
		
					
					
				} else if(mode2.isSelected()) {
					
                    String plainText = txtin.getText();
                     KEY = Integer.parseInt(keyin.getText());
                    
                     encryptionTextArea.append("===============<CBC encryption>===============" + newLine);
                 	encryptionTextArea.append("<<Plain text blocks>>" + newLine);
                 	 c = CBCencrypt(plainText, KEY, IV); 	//encrypt
                    
                    
                    
					
				} else if(mode3.isSelected()) {
					
                    String plainText = txtin.getText();	
                     KEY = Integer.parseInt(keyin.getText());
                    
                     encryptionTextArea.append("===============< OFB UNDER CONSTRUCTION >===============" + newLine);
                    
                    
                    
                    
                    
                    
				} else if(mode4.isSelected()) {
	
					String plainText = txtin.getText();
					 KEY = Integer.parseInt(keyin.getText());
					
					
						encryptionTextArea.append("===============<CTR encryption>===============" + newLine);
						encryptionTextArea.append("<<Plain text blocks>>" + newLine);
						c = CTRencrypt(plainText, KEY, IV); 	//encrypt
					
					
					
					
					
					
    
    
				} else if(mode5.isSelected()) {
	
					String plainText = txtin.getText();
					 KEY = Integer.parseInt(keyin.getText());
					
					
					 encryptionTextArea.append("===============< XTS UNDER CONSTRUCTION >===============" + newLine);
					
					
					
					
					
					
						
				}else {
					encryptionTextArea.append("Please select a Operation mode !!!!");
				}
				
				

				
			}
		});
		btnText.setForeground(Color.CYAN);
		btnText.setBackground(SystemColor.activeCaption);
		btnText.setBounds(532, 125, 130, 25);
		frmTpCrypto.getContentPane().add(btnText);
		
		lblKey = new JLabel("KEY");
		lblKey.setForeground(Color.ORANGE);
		lblKey.setBounds(502, 12, 98, 39);
		frmTpCrypto.getContentPane().add(lblKey);
		
		keyin = new JTextField();
		keyin.setForeground(Color.CYAN);
		keyin.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 15));
		keyin.setColumns(10);
		keyin.setBackground(Color.BLACK);
		keyin.setBounds(499, 49, 163, 44);
		frmTpCrypto.getContentPane().add(keyin);
		
		btnDencryptXtea = new JButton("Decrypt XTEA");
		btnDencryptXtea.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
				
				
				
				
				
				
				
				if (mode1.isSelected()) {
					
					String plainText = txtin.getText();
					int KEY = Integer.parseInt(keyin.getText());
					encryptionTextArea.append("<<Plain text>>" + newLine);
					encryptionTextArea.append(ECBdecrypt(c, KEY) + newLine); //decrypt and display
					
		
					
					
				} else if(mode2.isSelected()) {
					
                    String plainText = txtin.getText();
                     KEY = Integer.parseInt(keyin.getText());
                     encryptionTextArea.append("<<Plain text>>" + newLine);
                     encryptionTextArea.append(CBCdecrypt(c, KEY, IV) + newLine); //decrypt and display
                    
                    
                    
                    
					
				} else if(mode3.isSelected()) {
					
                    String plainText = txtin.getText();	
                     KEY = Integer.parseInt(keyin.getText());
                    
                    
                    
                    
                    
                    
                    
                    
				} else if(mode4.isSelected()) {
	
					String plainText = txtin.getText();
					 KEY = Integer.parseInt(keyin.getText());
					 encryptionTextArea.append("<<Plain text>>" + newLine);
					 encryptionTextArea.append(CTRdecrypt(c, KEY, IV) + newLine); //decrypt and display
					
					
					
					
					
					
					
    
    
				} else if(mode5.isSelected()) {
	
					String plainText = txtin.getText();
					 KEY = Integer.parseInt(keyin.getText());
					
					
					 encryptionTextArea.append("===============< XTS UNDER CONSTRUCTION >===============" + newLine);
					
					
					
					
					
					
						
				}else {
					encryptionTextArea.append("Please select a Operation mode !!!!");
				}
				
				
				
				
				
				
				
				
				
				
			}
		});
		btnDencryptXtea.setForeground(Color.CYAN);
		btnDencryptXtea.setBackground(SystemColor.activeCaption);
		btnDencryptXtea.setBounds(532, 162, 130, 25);
		frmTpCrypto.getContentPane().add(btnDencryptXtea);
		

	}
	
	
	
	

	    public static int[][] ECBencrypt(String plainText, int key)
	    {
		int numOfBlocks = ((plainText.length() - (plainText.length() % 8)) / 8);
		if((plainText.length() % 8) == 0)
		{
		    int i = 0, j = 0;
		    String block = null;
		    int[][] cipherData = new int[numOfBlocks][];
		    for(i = 0, j = 0; i < numOfBlocks; i++, j = j + 8)
		    {
			block = plainText.substring(j, j + 8);
			encryptionTextArea.append("plain-block " + i + " = " + "[" + block + "]" + newLine);
			cipherData[i] = encrypt(block, key);
		    }
		    return cipherData;
		}
		else //accounts for the case with a partial-block at the end
		{
		    numOfBlocks++;
		    int i = 0, j = 0;
		    String block = null;
		    int[][] cipherData = new int[numOfBlocks][];
		    for(i = 0, j = 0; i < numOfBlocks - 1; i++, j = j + 8)
		    {
			block = plainText.substring(j, j + 8);
			encryptionTextArea.append("plain-block " + i + " = " + "[" + block + "]" + newLine);
			cipherData[i] = encrypt(block, key);
		    }
		    block = plainText.substring(j, plainText.length());
		    if(block.length() < 8 && block.length() > 0)
		    {
			while(block.length() < 8)
			{
			    block = block + " ";
			}
			cipherData[i] = encrypt(block, key);
			encryptionTextArea.append("plain-block " + i + " = " + "[" + block + "]" + newLine);
		    }
		    return cipherData;
		}
	    }


	    public static String ECBdecrypt(int[][] cipherData, int key)
	    {
		String plainText = "";

		for(int i = 0; i < cipherData.length; i++)
		{
		    plainText = plainText + decrypt(cipherData[i], key);
	    
		}
		return plainText;
	    }



	    public static int[][] CBCencrypt(String plainText, int key, int iv)
	    {
		int numOfBlocks = ((plainText.length() - (plainText.length() % 8)) / 8);
		if((plainText.length() % 8) == 0)
		{
		    int i = 0, j = 0;
		    String block = null;
		    int[][] cipherData = new int[numOfBlocks][];
		    for(i = 0, j = 0; i < numOfBlocks; i++, j = j + 8)
		    {
			block = plainText.substring(j, j + 8);
			encryptionTextArea.append("plain-block " + i + " = " + "[" + block + "]" + newLine);
			String leftHalf = block.substring(0, BLOCKSIZE);
			String rightHalf = block.substring(BLOCKSIZE, block.length());
			if(i == 0)
			{
			    int[] p = {textToNum3(leftHalf) ^ iv, textToNum3(rightHalf) ^ iv};
			    cipherData[i] = encrypt_cbc(p, key);
			}
			else
			{
			    int[] p = {textToNum3(leftHalf) ^ cipherData[i - 1][0],
				textToNum3(rightHalf) ^ cipherData[i - 1][1]};
				cipherData[i] = encrypt_cbc(p, key);
			}
		    }
		    return cipherData;
		}
		else //accounts for the case with a partial-block at the end
		{
		    numOfBlocks++;
		    int i = 0, j = 0;
		    String block = null;
		    int[][] cipherData = new int[numOfBlocks][];
		    for(i = 0, j = 0; i < numOfBlocks - 1; i++, j = j + 8)
		    {
			block = plainText.substring(j, j + 8);
			encryptionTextArea.append("plain-block " + i + " = " + "[" + block + "]" + newLine);
			String leftHalf = block.substring(0, BLOCKSIZE);
			String rightHalf = block.substring(BLOCKSIZE, block.length());
			if(i == 0)
			{
			    int[] p = {textToNum3(leftHalf) ^ iv, textToNum3(rightHalf) ^ iv};
			    cipherData[i] = encrypt_cbc(p, key);
			}
			else
			{
			    int[] p = {textToNum3(leftHalf) ^ cipherData[i - 1][0],
				textToNum3(rightHalf) ^ cipherData[i - 1][1]};
				cipherData[i] = encrypt_cbc(p, key);
			}
		    }
		    block = plainText.substring(j, plainText.length());
		    if(block.length() < 8 && block.length() > 0)
		    {
			while(block.length() < 8)
			{
			    block = block + " ";
			}
			String leftHalf = block.substring(0, BLOCKSIZE);
			String rightHalf = block.substring(BLOCKSIZE, block.length());
			if(i == 0)
			{
			    int[] p = {textToNum3(leftHalf) ^ iv, textToNum3(rightHalf) ^ iv};
			    cipherData[i] = encrypt_cbc(p, key);
			}
			else
			{
			    int[] p = {textToNum3(leftHalf) ^ cipherData[i - 1][0],
				textToNum3(rightHalf) ^ cipherData[i - 1][1]};
				cipherData[i] = encrypt_cbc(p, key);
			}
			encryptionTextArea.append("plain-block " + i + " = " + "[" + block + "]" + newLine);
		    }
		    return cipherData;
		}
	    }


	    public static String CBCdecrypt(int[][] cipherData, int key, int iv)
	    {
		String plainText = "";
		for(int i = 0; i < cipherData.length; i++)
		{
		    int[] temp = decrypt_cbc(cipherData[i], key);
		    if(i == 0)
		    {
			temp[0] = iv ^ temp[0];
			temp[1] = iv ^ temp[1];
		    }
		    else
		    {
			temp[0] = cipherData[i - 1][0] ^ temp[0];
			temp[1] = cipherData[i - 1][1] ^ temp[1];
		    }
		    plainText = plainText + numToText3(temp[0]) + numToText3(temp[1]);
		}
		return plainText;
	    }



	    public static int[][] CTRencrypt(String plainText, int key, int iv)
	    {
		int numOfBlocks = ((plainText.length() - (plainText.length() % 8)) / 8);
		if((plainText.length() % 8) == 0)
		{
		    int i = 0, j = 0;
		    String block = null;
		    int[][] cipherData = new int[numOfBlocks][];
		    for(i = 0, j = 0; i < numOfBlocks; i++, j = j + 8)
		    {
			block = plainText.substring(j, j + 8);
			encryptionTextArea.append("plain-block " + i + " = " + "[" + block + "]" + newLine);
			String leftHalf = block.substring(0, BLOCKSIZE);
			String rightHalf = block.substring(BLOCKSIZE, block.length());


			int[] p = {textToNum3(leftHalf), textToNum3(rightHalf)};
			int[] ivPlain = {iv + i, iv + i};
			int[] ivCipher = encrypt_cbc(ivPlain, key);
			p[0] = p[0] ^ ivCipher[0];
			p[1] = p[1] ^ ivCipher[1];
			cipherData[i] = p;
		    }
		    return cipherData;
		}
		else //accounts for the case with a partial-block at the end
		{
		    numOfBlocks++;
		    int i = 0, j = 0;
		    String block = null;
		    int[][] cipherData = new int[numOfBlocks][];
		    for(i = 0, j = 0; i < numOfBlocks - 1; i++, j = j + 8)
		    {
			block = plainText.substring(j, j + 8);
			encryptionTextArea.append("plain-block " + i + " = " + "[" + block + "]" + newLine);
			String leftHalf = block.substring(0, BLOCKSIZE);
			String rightHalf = block.substring(BLOCKSIZE, block.length());
			int[] p = {textToNum3(leftHalf), textToNum3(rightHalf)};
			int[] ivPlain = {iv + i, iv + i};
			int[] ivCipher = encrypt_cbc(ivPlain, key);
			p[0] = p[0] ^ ivCipher[0];
			p[1] = p[1] ^ ivCipher[1];
			cipherData[i] = p;
		    }
		    block = plainText.substring(j, plainText.length());
		    if(block.length() < 8 && block.length() > 0)
		    {
			while(block.length() < 8)
			{
			    block = block + " ";
			}
			String leftHalf = block.substring(0, BLOCKSIZE);
			String rightHalf = block.substring(BLOCKSIZE, block.length());
			int[] p = {textToNum3(leftHalf), textToNum3(rightHalf)};
			int[] ivPlain = {iv + i, iv + i};
			int[] ivCipher = encrypt_cbc(ivPlain, key);
			p[0] = p[0] ^ ivCipher[0];
			p[1] = p[1] ^ ivCipher[1];
			cipherData[i] = p;
			encryptionTextArea.append("plain-block " + i + " = " + "[" + block + "]" + newLine);
		    }
		    return cipherData;
		}
	    }


		public static String CTRdecrypt(int[][] cipherData, int key, int iv)
		{
			String plainText = "";
			for(int i = 0; i < cipherData.length; i++)
			{
				int[] ivPlain = {iv + i, iv + i};
				int[] ivCipher = encrypt_cbc(ivPlain, key);
				int[] p = cipherData[i];
				p[0] = p[0] ^ ivCipher[0];
				p[1] = p[1] ^ ivCipher[1];
				plainText = plainText + numToText3(p[0]) + numToText3(p[1]);
			}
			return plainText;
		}


		public static int textToNum3(String text)
		{
	        StringBuffer lBinary = new StringBuffer();

	        byte[] lBytes = text.getBytes();

	        for(int z = 0; z < lBytes.length; z++)
	        {
				if(lBytes[z] < 32)
				{
					lBinary.append("000").append(Integer.toBinaryString(lBytes[z]));
				}
				else if(lBytes[z] >= 32 && lBytes[z] < 64)
				{
					lBinary.append("00").append(Integer.toBinaryString(lBytes[z]));
				}
				else if(64 <= lBytes[z] && lBytes[z] < 128)
				{
					lBinary.append("0").append(Integer.toBinaryString(lBytes[z]));
				}
				else
				{
					lBinary.append(Integer.toBinaryString(lBytes[z]));
				}
			}
			return Integer.parseInt(lBinary.toString(), 2);
		}


		public static String numToText3(int num)
		{
			String outBinary = Integer.toBinaryString(num);
	/*
			if(outBinary.length() % 8 == 7)
			{
				outBinary = "0" + outBinary;
			}
			else if(outBinary.length() % 8 == 6)
			{
				outBinary = "00" + outBinary;
			}
			else if(outBinary.length() % 8 == 5)
			{
				outBinary = "000" + outBinary;
			}
	*/
			while(outBinary.length() < 32)
			{
				outBinary = "0" + outBinary;
			}

			byte[] outBytes = new byte[BLOCKSIZE];

			for(int i = 0, j = 0; i < outBinary.length(); i = i + 8, j++)
			{
				String bytePiece = outBinary.substring(i, i + 8);
				outBytes[j] = (byte)Integer.parseInt(bytePiece, 2);
			}

			String outString = "";
			for(int i = 0; i < outBytes.length; i++)
			{
				outString = outString + (char)outBytes[i];
			}
			return outString;
		}


		public static int[] encrypt(String plain, int key)
		{
			String leftHalf = plain.substring(0, BLOCKSIZE);
			String rightHalf = plain.substring(BLOCKSIZE, plain.length());

			int mult = 255 / ROUNDS;
			int l = textToNum3(leftHalf);
			int r = textToNum3(rightHalf);
			int sum = 0;
			for(int i = 0; i < ROUNDS; i++)
			{
				sum += DELTA;
				l += ((r << 4) + key) ^ (r + sum) ^ ((r >> 5) + key);
				r += ((l << 4) + key) ^ (l + sum) ^ ((l >> 5) + key);
				encryptionTextArea.setCaretColor(new Color(i * mult, i * mult, i * mult));
				encryptionTextArea.append(l + "\t" + r + newLine);
				//encryptionTextArea.setCaretColor(null);
			}
			int[] cipher = {l, r};
			return cipher;
		}


		public static String decrypt(int[] cipher, int key)
		{
			int mult = 255 / ROUNDS;
			int l = cipher[0];
			int r = cipher[1];
			int sum = DELTA << 5;
			for(int i = 0; i < ROUNDS; i++)
			{
				r -= ((l << 4) + key) ^ (l + sum) ^ ((l >> 5) + key);
				l -= ((r << 4) + key) ^ (r + sum) ^ ((r >> 5) + key);
				encryptionTextArea.setCaretColor(new Color((ROUNDS - i) * mult, (ROUNDS - i) * mult, (ROUNDS - i) * mult));
				encryptionTextArea.append(l + "\t" + r + newLine);
				sum -= DELTA;
			}
			String plain = numToText3(l) + numToText3(r);
			return plain;
		}


		public static int[] encrypt_cbc(int[] plain, int key)
		{
			int mult = 255 / ROUNDS;
			int l = plain[0];
			int r = plain[1];
			int sum = 0;
			for(int i = 0; i < ROUNDS; i++)
			{
				sum += DELTA;
				l += ((r << 4) + key) ^ (r + sum) ^ ((r >> 5) + key);
				r += ((l << 4) + key) ^ (l + sum) ^ ((l >> 5) + key);
				encryptionTextArea.setCaretColor(new Color(i * mult, i * mult, i * mult));
				encryptionTextArea.append(l + "\t" + r + newLine);
			}
			int[] cipher = {l, r};
			return cipher;
		}


		public static int[] decrypt_cbc(int[] cipher, int key)
		{
			int mult = 255 / ROUNDS;
			int l = cipher[0];
			int r = cipher[1];
			int sum = DELTA << 5;
			for(int i = 0; i < ROUNDS; i++)
			{
				r -= ((l << 4) + key) ^ (l + sum) ^ ((l >> 5) + key);
				l -= ((r << 4) + key) ^ (r + sum) ^ ((r >> 5) + key);
				encryptionTextArea.setCaretColor(new Color((ROUNDS - i) * mult, (ROUNDS - i) * mult, (ROUNDS - i) * mult));
				encryptionTextArea.append(l + "\t" + r + newLine);
				sum -= DELTA;
			}
			int[] p = {l, r};
			return p;
		}


		public static void printCipherData(int[][] cipherData)
		{
			for(int q = 0; q < cipherData.length; q++)
			{
				if(cipherData[q] != null)
				{
					encryptionTextArea.append("cipher-block " + q + " = " + "[");
					printCipherBlock(cipherData[q]);
					encryptionTextArea.append("]" + newLine);
				}
			}
		}


		public static void printCipherBlock(int[] cipher)
		{
			if(cipher != null)
			{
				encryptionTextArea.append(numToText3(cipher[0]) + numToText3(cipher[1]));
			}
			else
			{
				encryptionTextArea.append("NullArry");
			}
		}
	
	

    
    
    
}
