package Interface;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.pixelmed.dicom.DicomException;
import com.pixelmed.display.SourceImage;

import Dicom.Dicom;

public class dcmVersImg extends JFrame {
	
	private javax.swing.JButton browseButton;
    private javax.swing.JButton convert;
    private javax.swing.JCheckBox PDF;
    private javax.swing.JCheckBox JPG;
    private javax.swing.JCheckBox PNG;
    private javax.swing.JCheckBox GIF;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel labelImageDisplay;
	
     public static File selFile;
    private boolean PDFBool = false ;
    private boolean JPGBool = false ;
    private boolean PNGBool = false ;
    private boolean GIFBool = false ;
    
    
    private void convertTxtToimage(ArrayList<String> text)
    {
    	for(int i = 0  ;i < text.size() ; i++)
    	{
    	 BufferedImage img = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
         Graphics2D g2d = img.createGraphics();
         Font font = new Font("Arial", Font.PLAIN, 48);
         g2d.setFont(font);
         FontMetrics fm = g2d.getFontMetrics();
         int width = fm.stringWidth(text.get(i));
         int height = fm.getHeight();
         g2d.dispose();

         img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
         g2d = img.createGraphics();
         g2d.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
         g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
         g2d.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
         g2d.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);
         g2d.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
         g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
         g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
         g2d.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
         g2d.setFont(font);
         fm = g2d.getFontMetrics();
         g2d.setColor(Color.BLACK);
         g2d.drawString(text.get(i), 0, fm.getAscent());
         g2d.dispose();
         try {
             ImageIO.write(img, "png", new File("Text.png"));
             System.out.println("nadi");
         } catch (IOException ex) {
             ex.printStackTrace();
         }
    	}
    }
    
    private void ConvertActionPerformed(ActionEvent evt) {               
    	Dicom c = new Dicom();
         if(PNGBool)
         {
        	 c.convertToPNG(selFile);
        	 Object[] options = {"Ok",
                     "Consulter",
                     };
     	
     		int choix =JOptionPane.showOptionDialog(null,
     				"Image convertie avec succ�s", 
     				"Resultat", JOptionPane.CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE, null,  options,options[1]);
     		switch (choix) {
     		case 0: 
     			
     			break ;
     			
     	case 1: 
     		new viewer("Visualiser le fichier", new File("C:\\Users\\FiaNso\\eclipse-workspace\\Project\\All\\PNG\\"+selFile.getName()+".png"));
     		this.dispose();
     			break ;
     		default:
     			throw new IllegalArgumentException("Unexpected value: " + choix);
     		}
        	 
         }
         if(JPGBool)
         {
        	 c.convertDicomToJPG(selFile);
        	 Object[] options = {"Ok",
                     "Consulter",
                     };
     	
     		int choix =JOptionPane.showOptionDialog(null,
     				"Image convertie avec succ�s", 
     				"Resultat", JOptionPane.CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE, null,  options,options[1]);
     		switch (choix) {
     		case 0: 
     			
     			break ;
     			
     	case 1: 
     		new viewer("Visualiser le fichier", new File("C:\\Users\\FiaNso\\eclipse-workspace\\Project\\All\\JPG\\"+selFile.getName()+".jpg"));
     		this.dispose();
     			break ;
     		default:
     			throw new IllegalArgumentException("Unexpected value: " + choix);
     		}
         }
         if(GIFBool)
         {
        	 c.convertToGIF(selFile);
        	 Object[] options = {"Ok",
                     "Consulter",
                     };
     	
     		int choix =JOptionPane.showOptionDialog(null,
     				"Image convertie avec succ�s", 
     				"Resultat", JOptionPane.CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE, null,  options,options[1]);
     		switch (choix) {
     		case 0: 
     			
     			break ;
     			
     	case 1: 
     		new viewer("Visualiser le fichier", new File("C:\\Users\\FiaNso\\eclipse-workspace\\Project\\All\\GIF\\"+selFile.getName()+".gif"));
     		this.dispose();
     			break ;
     		default:
     			throw new IllegalArgumentException("Unexpected value: " + choix);
     		}
         }
         if(PDFBool)
         {
        	 c.convertToPDF("C:\\Users\\FiaNso\\eclipse-workspace\\Project\\All\\PDF\\"+selFile.getName()+".pdf",selFile);
        	 Object[] options = {"Ok",
                     "Fermer",
                     };
     	
     		int choix =JOptionPane.showOptionDialog(null,
     				"Image convertie avec succ�s", 
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
         
        }   
    private void GIFActionPerformed(ActionEvent evt) {               
    	GIFBool= true;
    		
    	    }   
      private void PNGActionPerformed(ActionEvent evt) {               
    		PNGBool= true;
    			
    		    }
      
      private void PDFActionPerformed(ActionEvent evt) {               
    		PDFBool= true;
    			
    		    } 
      private void JPGActionPerformed(ActionEvent evt) {               
    		JPGBool= true;
    			
    		    } 
      private void loadAndDisplay(File selectedFile)
      { 
          try
          {
           SourceImage image = new SourceImage(selectedFile.getAbsolutePath());
           Image img = image.getBufferedImage();  
           ImageIcon icon  = new ImageIcon(img);
           labelImageDisplay.setIcon(icon); 
          } catch(IOException | DicomException ex)
          {
              System.out.println(ex.getMessage());
              
          }
          
          
      }
      private void browseActionPerformed(ActionEvent evt) {                                             
          
          JFileChooser jcf  = new JFileChooser("C:\\Users\\FiaNso\\eclipse-workspace\\Project\\All\\Dicom"); 
          int returnValue = jcf.showOpenDialog(null);
          if(returnValue== JFileChooser.APPROVE_OPTION)
          {
               File selectedFile = jcf.getSelectedFile();
               System.out.println(selectedFile.getAbsoluteFile());
               loadAndDisplay(selectedFile);
               selFile= selectedFile;
          }
      }   
	
    private void initComponents() {
    	

        jPanel1 = new javax.swing.JPanel();
        browseButton = new javax.swing.JButton();
        labelImageDisplay = new javax.swing.JLabel();
        PDF = new javax.swing.JCheckBox();
        JPG = new javax.swing.JCheckBox();
        PNG = new javax.swing.JCheckBox();
        GIF = new javax.swing.JCheckBox();
        convert = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));
        labelImageDisplay.setSize(100, 100);
        browseButton.setBackground(new java.awt.Color(255, 51, 0));
        browseButton.setText("Browse");

        PDF.setText("PDF");

        JPG.setText("JPG");

        PNG.setText("PNG");

        GIF.setText("GIF");
        
         PDF.setBackground(new java.awt.Color(255, 51, 0));
         JPG.setBackground(new java.awt.Color(255, 51, 0));
         PNG.setBackground(new java.awt.Color(255, 51, 0));
         GIF.setBackground(new java.awt.Color(255, 51, 0));
         
        convert.setBackground(new java.awt.Color(255, 51, 0));
        browseButton.setIcon(new javax.swing.ImageIcon("E:\\All Programmation\\dicom project\\Icons\\folder_browse-512.png"));
        convert.setText("Convert");

        jPanel2.setBackground(new java.awt.Color(255, 51, 0));
        browseButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
            	browseActionPerformed(evt);
            }
        });
       
        convert.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
            	ConvertActionPerformed(evt);
            }
        });
        PNG.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
            	PNGActionPerformed(evt);
            }
        });
      GIF.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
            	GIFActionPerformed(evt);
            }
        });
      PDF.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
            	PDFActionPerformed(evt);
            }
        });
      JPG.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
            	JPGActionPerformed(evt);
            }
        });
        jLabel2.setIcon(new javax.swing.ImageIcon("E:\\All Programmation\\dicom project\\Icons\\LOGO_nadi.png")); // NOI18N

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Dicom Converter");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(67, 67, 67)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(browseButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 128, Short.MAX_VALUE)
                .addComponent(JPG)
                .addGap(62, 62, 62)
                .addComponent(PNG)
                .addGap(66, 66, 66)
                .addComponent(GIF)
                .addGap(63, 63, 63)
                .addComponent(PDF)
                .addGap(57, 57, 57)
                .addComponent(convert, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(labelImageDisplay)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(browseButton)
                        .addGap(8, 8, 8)
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 229, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(PDF)
                            .addComponent(JPG)
                            .addComponent(PNG)
                            .addComponent(GIF))
                        .addGap(31, 31, 31))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(convert)
                        .addContainerGap())))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(labelImageDisplay)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }
    public  dcmVersImg (String titre)
    {
    	try {
			this.setIconImage(ImageIO.read(new File("E:\\All Programmation\\dicom project\\Icons\\LOGO_1.png")));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	initComponents();
    	setTitle("titre");
    	setVisible(true);
    	setSize(950,700);
    	
    	
    }
    public static void main(String[] args)
    {
    	dcmVersImg v = new dcmVersImg("Welcome");
    }
}
