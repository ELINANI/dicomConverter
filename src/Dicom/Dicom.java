package Dicom;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;


import org.dcm4che2.data.BasicDicomObject;
import org.dcm4che2.data.DicomObject;
import org.dcm4che2.data.Tag;
import org.dcm4che2.data.UID;
import org.dcm4che2.data.VR;
import org.dcm4che2.imageio.plugins.dcm.DicomImageReadParam;
import org.dcm4che2.io.DicomOutputStream;
import org.dcm4che2.util.UIDUtils;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

import Interface.tagsView;
import Interface.dcmVersImg;
import Interface.imgToDicom;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfWriter;
public class Dicom {
	public static File fileToConvert ;
	
	 static BufferedImage myJpegImage=null;
     File fileDest ; 
		public void convert(DicomObject dicom , File file) {
			fileDest =	new File("C:\\Users\\FiaNso\\eclipse-workspace\\Project\\All\\Dicom\\"+System.currentTimeMillis()+".dcm");
			try { 
				   BufferedImage jpegImage = ImageIO.read(file);
				   if (jpegImage == null)
				      throw new Exception("Invalid file.");  
				   int colorComponents = jpegImage.getColorModel().getNumColorComponents();
				   int bitsPerPixel = jpegImage.getColorModel().getPixelSize();
				   int bitsAllocated = (bitsPerPixel / colorComponents);
				   int samplesPerPixel = colorComponents;

				
				   
				   dicom.putString(Tag.SpecificCharacterSet, VR.CS, "ISO_IR 100");
				   dicom.putString(Tag.PhotometricInterpretation, VR.CS, samplesPerPixel == 3 ? "YBR_FULL_422" : "MONOCHROME2");
				   
				 
				   dicom.putInt(Tag.SamplesPerPixel, VR.US, samplesPerPixel);         
				   dicom.putInt(Tag.Rows, VR.US, jpegImage.getHeight());
				   dicom.putInt(Tag.Columns, VR.US, jpegImage.getWidth());
				   dicom.putInt(Tag.BitsAllocated, VR.US, bitsAllocated);
				   dicom.putInt(Tag.BitsStored, VR.US, bitsAllocated);
				   dicom.putInt(Tag.HighBit, VR.US, bitsAllocated-1);
				   dicom.putInt(Tag.PixelRepresentation, VR.US, 0);  
				   
				   
				   dicom.putDate(Tag.InstanceCreationDate, VR.DA, new Date());
				   dicom.putDate(Tag.InstanceCreationTime, VR.TM, new Date());
				   
				   dicom.putString(Tag.StudyInstanceUID, VR.UI, UIDUtils.createUID());
				   dicom.putString(Tag.SeriesInstanceUID, VR.UI, UIDUtils.createUID());
				   dicom.putString(Tag.SOPInstanceUID, VR.UI, UIDUtils.createUID());
				   
				   
				   
				   dicom.initFileMetaInformation(UID.JPEGBaseline1);
				   
				   FileOutputStream fos = new FileOutputStream(fileDest);// 
				   BufferedOutputStream bos = new BufferedOutputStream(fos);
				   DicomOutputStream dos = new DicomOutputStream(bos);
				   imgToDicom.dcmFile = new File(fileDest.getAbsolutePath());
				   dos.writeDicomFile(dicom);
				   
				   
				   dos.writeHeader(Tag.PixelData, VR.OB, -1);  
				   

				   dos.writeHeader(Tag.Item, null, 0);  
				   
				   
				
				   int jpgLen = (int) file.length(); // image Source
				   dos.writeHeader(Tag.Item, null, (jpgLen+1)&~1);
				   
				   
				   FileInputStream fits = new FileInputStream(file);
				   BufferedInputStream bis = new BufferedInputStream(fits);
				   DataInputStream dis = new DataInputStream(bis);

				   byte[] buffer = new byte[65536];       
				   int b;
				   while ((b = dis.read(buffer)) > 0) {
				      dos.write(buffer, 0, b);
				   }
				   		
				   if ((jpgLen&1) != 0) dos.write(0); 
				   dos.writeHeader(Tag.SequenceDelimitationItem, null, 0);
				   dos.close();
				  dis.close();
				  System.out.println("nadi");
				  
				} catch(Exception e) {
				   System.out.println("ERROR: "+ e.getMessage());
				}
		}
	 public void convertToPDF(String desc,File file ,ArrayList<File> file2)
	 {
		 try {
	           
	            Document doc = new Document();
	            
	            
	            PdfWriter.getInstance(doc, new FileOutputStream(desc));
	            
	            Rectangle r = PageSize.A4;
	            
	            BufferedImage orImg = ImageIO.read(file);
	            
	         
	            int width = orImg.getWidth();
	            int height = orImg.getHeight();
	           
	            if (width > r.getWidth())
	                width = (int) r.getWidth();
	            if (height > r.getHeight())
	                height = (int) r.getHeight();
	            
	            BufferedImage bi = new BufferedImage(width, height,
	                    BufferedImage.TYPE_INT_RGB);
	            
	            Graphics2D g2d = bi.createGraphics();
	           
	            g2d.drawImage(orImg, 0, 0, width, height, null);
	            
	            ByteArrayOutputStream bas = new ByteArrayOutputStream();
	            ImageIO.write(bi, "png", bas);
	            
	            Image img = Image.getInstance(bas.toByteArray());
	            
	            img.setAlignment(Element.ALIGN_CENTER);
	            doc.open();
	            doc.add(img);
	            //Start 
	            for (int i = 0 ; i <file2.size()  ;i ++)
	            {
	            	 System.out.println(file2.get(i).getAbsolutePath());
	            BufferedImage orImg2 = ImageIO.read(file2.get(i));
	            int width2 = orImg2.getWidth();
	            int height2 = orImg2.getHeight();
	           
	            if (width2 > r.getWidth())
	                width2 = (int) r.getWidth();
	            if (height2 > r.getHeight())
	                height2 = (int) r.getHeight();
	            
	            BufferedImage bii = new BufferedImage(width2, height2,
	                    BufferedImage.TYPE_INT_ARGB);
	            
	            Graphics2D g2d2 = bii.createGraphics();
	           
	            Color clr = new Color(10,20,12);
		         
		         g2d2.setColor(clr);
		         g2d2.setBackground(clr);
	             g2d2.drawImage(orImg2, 0, 0, width2, height2, null);
	            
	            ByteArrayOutputStream bass = new ByteArrayOutputStream();
	            ImageIO.write(bii, "png", bass);
	            
	            Image img2 = Image.getInstance(bass.toByteArray());
	            
	            img2.setAlignment(Element.ALIGN_LEFT);
	            
	            
	            //End
	            
	            
	            
	          
	            doc.add(img2);
	            System.out.println("Bien Convertie");
	            }
	            doc.close();
	           
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	 }
	 public void convertToPDF(String desc,File file)
	 { 
		 File F = new File("C:\\Users\\FiaNso\\eclipse-workspace\\Project\\Text.png");
		 try {
	            
	            Document doc = new Document();
	            
	            
	            PdfWriter.getInstance(doc, new FileOutputStream(desc));
	            
	            Rectangle r = PageSize.A4;
	            
	            BufferedImage orImg = ImageIO.read(file);
	            
	         
	            int width = orImg.getWidth();
	            int height = orImg.getHeight();
	           
	            if (width > r.getWidth())
	                width = (int) r.getWidth();
	            if (height > r.getHeight())
	                height = (int) r.getHeight();
	            
	            BufferedImage bi = new BufferedImage(width, height,
	                    BufferedImage.TYPE_INT_RGB);
	            
	            Graphics2D g2d = bi.createGraphics();
	           
	            g2d.drawImage(orImg, 0, 0, width, height, null);
	            
	            ByteArrayOutputStream bas = new ByteArrayOutputStream();
	            ImageIO.write(bi, "png", bas);
	            
	            Image img = Image.getInstance(bas.toByteArray());
	            
	            img.setAlignment(Element.ALIGN_CENTER);
	            
	    
	            
	            doc.open();
	            
	            doc.add(img);
	            
	            System.out.println("Bien Convertie");
 
	            doc.close();
	           
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	 }
	  public void convertToPNG(File file) 
	  {
		  try
		  {
			  BufferedImage image = ImageIO.read(file);
			  ImageIO.write(image, "png", new File("C:\\Users\\FiaNso\\eclipse-workspace\\Project\\All\\PNG\\"+file.getName()+".png"));  
			  System.out.println("Bien Convertie");
		  }catch(IOException ex)
		  {
			  System.out.println(ex.getMessage());
		  }
		  
	  }
	  public void convertToGIF(File file)
	  {
		  try
		  {
			  BufferedImage image = ImageIO.read(file);
			  ImageIO.write(image, "gif", new File("C:\\Users\\FiaNso\\eclipse-workspace\\Project\\All\\PDF\\"+file.getName()+".gif"));
			  System.out.println("Bien Convertie");
			  
		  }catch(IOException ex)
		  {
			  System.out.println(ex.getMessage());
		  }
	  }
	  public void convertDicomToJPG(File file)
	  {
		  Iterator<ImageReader> iterator =ImageIO.getImageReadersByFormatName("DICOM");
	        while (iterator.hasNext()) {
	            ImageReader imageReader = (ImageReader) iterator.next();
	            DicomImageReadParam dicomImageReadParam = (DicomImageReadParam) imageReader.getDefaultReadParam();
	            try {
	                ImageInputStream iis = ImageIO.createImageInputStream(file);
	                imageReader.setInput(iis,false);
	                myJpegImage = imageReader.read(0, dicomImageReadParam);
	                iis.close();
	                if(myJpegImage == null){
	                    System.out.println("Could not read image!!");
	                }
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	            File file2 = new File("C:\\Users\\FiaNso\\eclipse-workspace\\Project\\All\\JPG\\"+file.getName()+".jpg");
	            try {
	                OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(file2));
	                JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(outputStream);
	                encoder.encode(myJpegImage);
	                fileToConvert = file2;
	                outputStream.close();
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	            System.out.println("Completed");
	        }
	        
	  }
	 public static void main(String[] args) {
		
	    }

}
