package Dicom;

import java.io.File;
import java.util.Iterator;

import org.dcm4che2.data.DicomElement;
import org.dcm4che2.data.DicomObject;
import org.dcm4che2.io.DicomInputStream;
import org.dcm4che2.util.TagUtils;



public class ListDicomHeader {
	
	public void listHeader(DicomObject object) {
		int cpt = 0 ;
		   Iterator iter = object.datasetIterator();
		   while(iter.hasNext()) {
			   cpt++;
		      DicomElement element = (DicomElement) iter.next();
		      int tag = element.tag();
		      try {
		         String tagName = object.nameOf(tag);
		         String tagAddr = TagUtils.toString(tag);
		         String tagVR = object.vrOf(tag).toString();
		         if (tagVR.equals("SQ")) {
		            if (element.hasItems()) {
		               System.out.println(tagAddr +" ["+  tagVR +"] "+ tagName);
		               listHeader(element.getDicomObject());
		               continue;
		            }
		         }    
		         String tagValue = object.getString(tag);    
		         System.out.println(tagAddr +" ["+ tagVR +"] "+ tagName +" ["+ tagValue+"]"+cpt);
		      } catch (Exception e) {
		         e.printStackTrace();
		      }
		   }  
		}
	
	public static void main(String[] args) {
	}

}
