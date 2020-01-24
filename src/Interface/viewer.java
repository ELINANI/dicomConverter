package Interface;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class viewer extends JFrame {
	
	private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel labelImageDisplay;
    private File file;
    public void SauvegardeActionPerformed(ActionEvent event)
    {
    	 Object[] options = {"Ok",
                 "Fermer",
                 };
 	
 		int choix =JOptionPane.showOptionDialog(null,
 				"Image sauvegarder", 
 				"Resultat", JOptionPane.CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE, null,  options,options[1]);
 		switch (choix) {
 		case 0: 
 			
 			break ;
 			
 	case 1: 
 		this.dispose();
 			break ;
 		default:
 			throw new IllegalArgumentException("Unexpected value: " + choix);
 		}
    	
    }
    public void listTagActionPerformed(ActionEvent event)
    {
    	new tagsView("");
    	this.dispose();
    }
	

	public void viewimage(File selectedFile)
	{
		try {
			  BufferedImage img = ImageIO.read(selectedFile);
           ImageIcon icon  = new ImageIcon(img);
           labelImageDisplay.setIcon(icon); 
          } catch(IOException ex)
          {
              System.out.println(ex.getMessage());
              
          }
          
		
	}
	 private void initComponents() {

	        jPanel2 = new javax.swing.JPanel();
	        jLabel1 = new javax.swing.JLabel();
	        jLabel2 = new javax.swing.JLabel();
	        jPanel1 = new javax.swing.JPanel();
	        labelImageDisplay = new javax.swing.JLabel();
	        jButton1 = new javax.swing.JButton();
	        jButton2 = new javax.swing.JButton();
	        jButton1.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent evt) {
	            	SauvegardeActionPerformed(evt);
	            }
	        });
	        jButton2.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent evt) {
	                 listTagActionPerformed(evt);
	            }
	        });

	        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

	        jPanel2.setBackground(new java.awt.Color(255, 51, 0));

	        jLabel1.setIcon(new javax.swing.ImageIcon("E:\\All Programmation\\dicom project\\Icons\\LOGO_nadi.png")); // NOI18N

	        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
	        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
	        jLabel2.setText("Dicom Converter");

	        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
	        jPanel2.setLayout(jPanel2Layout);
	        jPanel2Layout.setHorizontalGroup(
	            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(jPanel2Layout.createSequentialGroup()
	                .addContainerGap()
	                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
	                .addGap(67, 67, 67)
	                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE)
	                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
	        );
	        jPanel2Layout.setVerticalGroup(
	            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addComponent(jLabel1)
	            .addGroup(jPanel2Layout.createSequentialGroup()
	                .addGap(24, 24, 24)
	                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
	        );

	        jPanel1.setBackground(new java.awt.Color(0, 0, 0));

	        jButton1.setBackground(new java.awt.Color(255, 51, 0));
	        jButton1.setIcon(new javax.swing.ImageIcon("E:\\All Programmation\\dicom project\\Icons\\téléchargement.jpg")); // NOI18N

	        jButton2.setBackground(new java.awt.Color(255, 51, 0));
	        jButton2.setIcon(new javax.swing.ImageIcon("E:\\All Programmation\\dicom project\\Icons\\writing-note-646711.png")); // NOI18N
	        jButton2.setText("Tags");

	        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
	        jPanel1.setLayout(jPanel1Layout);
	        jPanel1Layout.setHorizontalGroup(
	            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(jPanel1Layout.createSequentialGroup()
	                .addGap(24, 24, 24)
	                .addComponent(labelImageDisplay, javax.swing.GroupLayout.PREFERRED_SIZE, 634, javax.swing.GroupLayout.PREFERRED_SIZE)
	                .addContainerGap(19, Short.MAX_VALUE))
	            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
	                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
	                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
	                .addGap(44, 44, 44)
	                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
	                .addGap(28, 28, 28))
	        );
	        jPanel1Layout.setVerticalGroup(
	            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(jPanel1Layout.createSequentialGroup()
	                .addContainerGap()
	                .addComponent(labelImageDisplay, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE)
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
	                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
	                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
	                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
	                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
	        );

	        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
	        getContentPane().setLayout(layout);
	        layout.setHorizontalGroup(
	            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
	            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
	        );
	        layout.setVerticalGroup(
	            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(layout.createSequentialGroup()
	                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
	        );

	        pack();
	    }
	
	public viewer(String title ,File f)
	{
		try {
			this.setIconImage(ImageIO.read(new File("E:\\All Programmation\\dicom project\\Icons\\LOGO_1.png")));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		initComponents();
		setTitle(title);
		setVisible(true);
		this.file = f;
		viewimage(f);
	}
	public static void main(String[] args)
	{
		
		
	}

}
