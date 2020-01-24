package Interface;

import Dicom.Dicom;


	import java.awt.BorderLayout;
	import java.awt.Color;
	import java.awt.Container;
	import java.awt.Font;
    import java.awt.FontMetrics;
    import java.awt.Graphics2D;
    import java.awt.GridLayout;
	import java.awt.Image;
    import java.awt.RenderingHints;
    import java.awt.event.ActionEvent;
	import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
	import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.imageio.ImageIO;
	import javax.swing.BorderFactory;
	import javax.swing.ImageIcon;
	import javax.swing.JButton;
	import javax.swing.JFrame;
	import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
	import javax.swing.JTextArea;
	import javax.swing.border.EmptyBorder;
	import org.dcm4che2.data.DicomElement;
	import org.dcm4che2.data.DicomObject;
    import org.dcm4che2.io.DicomInputStream;
    import org.dcm4che2.tool.txt2dcmsr.Txt2DcmSR;
	import org.dcm4che2.util.TagUtils;
	import com.pixelmed.dicom.DicomException;
	import com.pixelmed.display.SourceImage;
	
	
	public  class viewObj  extends JFrame implements ActionListener{
		public static File file ;
		public static  ArrayList<String> Tags = new ArrayList<String>(); 
        public static  ArrayList<File> files = new ArrayList<File>(); 
		private static final long serialVersionUID = 1L;
		JLabel labelImageDisplay, LBL_TITLE1 ,LBL_Tags;
		JPanel WPanel, CPanel, NPanel, SPanel;
		JButton BT_Cancel ,Bt_Convert_To_PDF;
		JTextArea TEXTAREA;
		 
		
		
		public  void convertTxtToimage(ArrayList<String> text)
	    {
		   String chaine = "";
		   System.out.print(text.size()+"\t");
		  
	    	for(int i = 0  ; i < 12 ; i++)
	    	{
	    		chaine += "  "+text.get(i).toString();
	    	
	    	if(chaine.length() == 0)
	    	{
	    		chaine = "no data to display";
	    		
	    	}
	    	 BufferedImage img = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
	         Graphics2D g2d = img.createGraphics();
	         Font font = new Font("Arial", Font.CENTER_BASELINE, 10);
	         Color clr = new Color(10,20,12);
	         g2d.setFont(font);
	         g2d.setColor(clr);
	         g2d.setBackground(clr);
	         FontMetrics fm = g2d.getFontMetrics();
	         int width = fm.stringWidth(chaine);
	         int height = fm.getHeight();
	         g2d.dispose();
	         if(width == 0)
	         {
	        	 width = 150;
	         }

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
	         g2d.drawString(chaine, 0, fm.getAscent());
	         g2d.dispose();
	         try {
	        	 File f = new File("C:\\Users\\FiaNso\\eclipse-workspace\\Project\\Text"+i+".png");
	             ImageIO.write(img, "png", f);
	             files.add(f);
	             
	         } catch (IOException ex) {
	             ex.printStackTrace();
	         }
	         System.out.println(chaine);
	    	}
	    	
	    }

		
		public void convertToPdf(ActionEvent even)
		{
		  Dicom c = new Dicom();
		   convertTxtToimage(Tags);
		 c.convertToPDF("C:\\Users\\FiaNso\\eclipse-workspace\\Project\\All\\PDF\\"+dcmVersImg.selFile.getName()+".pdf", dcmVersImg.selFile,files);
		  Object[] options = {"OK",
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
		 	System.out.println("pdf  nadi");
		}
		
		void initButtons() {

			BT_Cancel = new JButton("CLOSE");
			BT_Cancel.setForeground(new Color(40,60,70));
			BT_Cancel.setBackground(new Color(255, 51, 0));
			BT_Cancel.setFont(new Font("Candara", Font.BOLD, 18));
			BT_Cancel.addActionListener(this);
			
			Bt_Convert_To_PDF = new JButton("convert image to pdf");
			Bt_Convert_To_PDF.setBackground(new Color(255, 51, 0));
			Bt_Convert_To_PDF.setForeground(new Color(40,60,70));
			Bt_Convert_To_PDF.setFont(new Font("Candara", Font.BOLD, 18));
			Bt_Convert_To_PDF.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent evt) {
	            	convertToPdf(evt);
	            }
	        });
			
			
			
		}
		
		void initTextAreas() {
			TEXTAREA= new JTextArea();
			TEXTAREA.setBackground(Color.BLACK);
			TEXTAREA.setForeground(Color.white);
			TEXTAREA.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
			TEXTAREA.setEnabled(false);
		}
		
		
		
		void initLabels() {
			LBL_TITLE1=new JLabel("DICOM VIEWER");
			LBL_TITLE1.setForeground(Color.WHITE);
			LBL_TITLE1.setFont(new Font("Candara", Font.BOLD, 30));
			LBL_TITLE1.setHorizontalAlignment(JLabel.CENTER);
			
			
			LBL_Tags=new JLabel("TAGS");
			LBL_Tags.setForeground(Color.WHITE);
			LBL_Tags.setFont(new Font("arial", Font.BOLD, 15));
			LBL_Tags.setHorizontalAlignment(JLabel.CENTER);
			
			
			labelImageDisplay= new JLabel();
		}
		void initPanels() {
			
			initLabels();
			NPanel = new JPanel();
			NPanel.setLayout(new GridLayout(1, 1));
			NPanel.setBackground(new Color(255, 51, 0));
			NPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
			NPanel.add(LBL_TITLE1);

			initButtons();

			SPanel = new JPanel();
			SPanel.setLayout(new GridLayout(2, 2, 8, 8));
			SPanel.setBackground(new Color(200,200,200));
			SPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
			SPanel.add(BT_Cancel);
			SPanel.add(Bt_Convert_To_PDF);
			


			initTextAreas();
			CPanel = new JPanel();
			CPanel.setLayout(new BorderLayout());
			CPanel.setBackground(Color.gray);
			CPanel.setBorder(new EmptyBorder(0, 0, 0, 0));
			CPanel.add(labelImageDisplay, BorderLayout.CENTER);
			
			
			
			WPanel = new JPanel();
			WPanel.setSize(130, 400);
			WPanel.setLayout(new BorderLayout());
			WPanel.setBackground(Color.darkGray);
			WPanel.setBorder(new EmptyBorder(0,0, 0, 0));
			WPanel.add(LBL_Tags,BorderLayout.NORTH);
			WPanel.add(TEXTAREA,BorderLayout.CENTER);
			
			
		}

		

	public void listHeader(DicomObject object) {
	   Iterator iter = object.datasetIterator();
	   while(iter.hasNext()) {
	      DicomElement element = (DicomElement) iter.next();
	      int tag = element.tag();
	      try {
	         String tagName = object.nameOf(tag);
	         String tagAddr = TagUtils.toString(tag);
	         String tagVR = object.vrOf(tag).toString();
	         
	         if (tagVR.equals("SQ")) {
	            if (element.hasItems()) {
	               TEXTAREA.append(tagAddr +" ["+  tagVR +"] "+ tagName+"\n");
	              
	               listHeader(element.getDicomObject());
	               continue;
	            }
	         }    
	         String tagValue = object.getString(tag);    
	         TEXTAREA.append(tagAddr +" ["+ tagVR +"] "+ tagName +" ["+ tagValue+"]"+"\n");
	         Tags.add(tagName);
             Tags.add(tagAddr);
             Tags.add(tagVR);
             //Tags.add("\n");
	      } catch (Exception e) {
	         e.printStackTrace();
	      }
	   }  
	}
		
		

		public viewObj(String T)  {
			try {
				this.setIconImage(ImageIO.read(new File("E:\\All Programmation\\dicom project\\Icons\\LOGO_1.png")));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			initPanels();
				//DicomObject H = dcmVersImg.selFile;
				 DicomInputStream dis;
				try {
					dis = new DicomInputStream(imgToDicom.dcmFile);
					 DicomObject object = dis.readDicomObject();
				      listHeader(object);
				      dis.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				  			
			Container cp = getContentPane();
			cp.setBackground(new Color(90, 80, 90));
			cp.setLayout(new BorderLayout());
			cp.add(NPanel, BorderLayout.NORTH);
			cp.add(CPanel, BorderLayout.CENTER);
			cp.add(SPanel, BorderLayout.SOUTH);
			cp.add(WPanel, BorderLayout.WEST);

			setBackground(new Color(90, 80, 90));
			setSize(1000, 700);
			setTitle(T);
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			setLocationRelativeTo(null);
			setResizable(false);
			setUndecorated(false);
			setVisible(true);
			
			 try
		     
				{
				 File SelectedFile =imgToDicom.dcmFile;
			      SourceImage image = new SourceImage(SelectedFile.getAbsolutePath());		      
			      Image img = image.getBufferedImage();
			      ImageIcon icon  = new ImageIcon(img);
			      labelImageDisplay.setIcon(icon); 
			     } catch(IOException | DicomException ex)
			     {
			         System.out.println(ex.getMessage());	         
			     }
			 
		}
		
		
		public static void main(String[] args) {
			
			new tagsView("DICOM VIEWER");
			

			}
		

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource()==BT_Cancel) {
				dispose();
			}
			
			
		}

	}


