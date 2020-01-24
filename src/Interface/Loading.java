package Interface;

import java.awt.Dimension;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.GroupLayout;
import javax.swing.JWindow;
import javax.swing.LayoutStyle;

public class Loading  extends JWindow {
	    private javax.swing.JProgressBar bar;
	    private javax.swing.JLabel jLabel1;
	    private javax.swing.JPanel jPanel1;
	    private javax.swing.JLabel progress;
	
	
	 private void initComponents() {

	        jPanel1 = new javax.swing.JPanel();
	        jLabel1 = new javax.swing.JLabel();
	        bar = new javax.swing.JProgressBar();
	        progress = new javax.swing.JLabel();

	       
	        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

	        jPanel1.setBackground(new java.awt.Color(0, 0, 0));

	        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
	        jLabel1.setIcon(new javax.swing.ImageIcon("E:\\All Programmation\\dicom project\\Icons\\LOGO_1.png"));
	        jLabel1.setText("jLabel1");

	        progress.setForeground(new java.awt.Color(255, 255, 255));
	        progress.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
	        progress.setText("0%");

	        GroupLayout jPanel1Layout = new GroupLayout(jPanel1);
	        jPanel1.setLayout(jPanel1Layout);
	        jPanel1Layout.setHorizontalGroup(
	            jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
	            .addGroup(jPanel1Layout.createSequentialGroup()
	                .addContainerGap(192, Short.MAX_VALUE)
	                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
	                    .addGroup(GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
	                        .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
	                            .addComponent(jLabel1, GroupLayout.PREFERRED_SIZE, 309, GroupLayout.PREFERRED_SIZE)
	                            .addComponent(bar, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
	                        .addGap(182, 182, 182))
	                    .addGroup(GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
	                        .addComponent(progress)
	                        .addGap(327, 327, 327))))
	        );
	        jPanel1Layout.setVerticalGroup(
	            jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
	            .addGroup(jPanel1Layout.createSequentialGroup()
	                .addComponent(jLabel1, GroupLayout.PREFERRED_SIZE, 218,GroupLayout.PREFERRED_SIZE)
	                .addGap(52, 52, 52)
	                .addComponent(bar,GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
	                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
	                .addComponent(progress)
	                .addGap(0, 31, Short.MAX_VALUE))
	        );

	        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

	        setSize(new Dimension(683, 351));
	        setLocationRelativeTo(null);
	    }
public Loading(String titre)
{
	try {
		this.setIconImage(ImageIO.read(new File("E:\\All Programmation\\dicom project\\Icons\\LOGO_1.png")));
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	initComponents();
}
 public static void main(String[] args)
 {
	 Loading ld = new Loading("Loading");
	
	
     /* Create and display the form */
     java.awt.EventQueue.invokeLater(new Runnable() {
         public void run() {
             ld.setVisible(true);
         }
     });
 
     try
     {
         for(int i =0;i<100;i++)
         {
             Thread.sleep(40);
             ld.bar.setValue(i);
             ld.bar.setForeground(new java.awt.Color(255, 51, 0));
             ld.bar.setBackground(new java.awt.Color(0,0, 0));
             ld.progress.setText(Integer.toString(i)+"%");
         }
          
     }catch(Exception e)
     {
         
     }
     new Loading("").setVisible(false);
     new Home("");
     ld.dispose();
 }


}
