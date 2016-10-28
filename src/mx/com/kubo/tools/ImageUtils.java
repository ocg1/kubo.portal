package mx.com.kubo.tools;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Hashtable;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import org.apache.sanselan.ImageReadException;
import org.apache.sanselan.Sanselan;
import org.apache.sanselan.common.IImageMetadata;
import org.apache.sanselan.common.ImageMetadata.Item;

public class ImageUtils {

public static BufferedImage cropImage(BufferedImage image,int x1,int y1,int x2,int y2){
	int width = Math.abs(x1-x2);
	int height = Math.abs(y1-y2);
	BufferedImage res=null;
	 try {
			res = image.getSubimage(x1, y1, width, height);
		} catch (Exception e) {
			e.printStackTrace();
			res=null;
		}
      return res; 
	
}
public static BufferedImage rotateImage(String pathImage, double angle){
	ImageIcon icon = new ImageIcon(pathImage);
	int w = icon.getIconWidth();
	int h = icon.getIconHeight();

	BufferedImage image = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);

	Graphics2D g2 = image.createGraphics();
	double x = (w-h)/80.0;
	double y = (h-w)/80.0;

	AffineTransform at = AffineTransform.getTranslateInstance(x, y);
	at.rotate(Math.toRadians(180), w/2.0, h/2.0); //Indicamos los 180 Grados
	g2.drawImage(icon.getImage(),at,icon.getImageObserver());	
	return image;
}

public  static BufferedImage rotateImage(BufferedImage image, double angle){
	int w = image.getWidth();
	int h = image.getHeight();
		BufferedImage image2=null;
		image2 = new BufferedImage(h,w,BufferedImage.TYPE_INT_RGB);
		Graphics2D g2d = image2.createGraphics();
		double x = (h-w)/2.0;
		double y = (w-h)/2.0;

		AffineTransform at = AffineTransform.getTranslateInstance(x, y);
		at.rotate(Math.toRadians(angle), w/2.0, h/2.0);

		g2d.drawRenderedImage(image,at);
	return image2;
	
	}


public static Hashtable<String, Integer> scaleImage(File fOrig,int maxWidth,int maxHeight){
	try {
		BufferedImage img = ImageIO.read(fOrig);
		int imgWidth  = (int) img.getWidth();
		int imgHeight  = (int) img.getHeight();

		int x = maxWidth;
		int y = maxHeight;
		
		double resx = 0;
		double resy = 0;
		
		if (imgWidth > x) {
				resx = x / (double) imgWidth;
				double yy = resx * (double) imgHeight;
				y = (int) yy;
			} 
			//else 
			if (maxHeight < y) {
				resy = maxHeight / (double) imgHeight;
				double xx = resy * (double)imgWidth ;
				x = (int) xx;
				y=maxHeight;
		}
		
		if(x == maxWidth && y == maxHeight ){
			x=imgWidth;
			y = imgHeight;
		}

		Hashtable<String, Integer> ht = new Hashtable<String, Integer>();
		ht.put("Height", y);
		ht.put("Width", x);
		return ht;
		
	} catch (IOException io) {
		//io.printStackTrace();
		System.out.println("Sin imagen");
		return null;
	}
}

	public static Hashtable<String, Integer> getWidthAndHeightImg(File fOrig){
		Hashtable<String, Integer> ht=null;
		try {
			BufferedImage img = ImageIO.read(fOrig);
			int imgWidth  = (int) img.getWidth();
			int imgHeight  = (int) img.getHeight();

			ht = new Hashtable<String, Integer>();
			
			ht.put("Width",imgWidth);
			ht.put("Height",imgHeight);
			
		} catch (IOException io) {
			io.printStackTrace();
			return null;
		}
		
		return ht;
	}
	


public static BufferedImage getScaledImage(BufferedImage image, int width, int height) throws IOException {
    int imageWidth  = image.getWidth();
    int imageHeight = image.getHeight();

    double scaleX = (double)width/imageWidth;
    double scaleY = (double)height/imageHeight;
    AffineTransform scaleTransform = AffineTransform.getScaleInstance(scaleX, scaleY);
    AffineTransformOp bilinearScaleOp = new AffineTransformOp(scaleTransform, AffineTransformOp.TYPE_BILINEAR);

    return bilinearScaleOp.filter(
        image,
        new BufferedImage(width, height, image.getType()));
	}



public static String getMetadata(File file){
	String metadata=null;
	IImageMetadata sanselanmetadata;
	try {
		sanselanmetadata = Sanselan.getMetadata(file);
        if(sanselanmetadata!=null){        	
        	List<?> items=sanselanmetadata.getItems();
        	Object item;
        	metadata="";
	       	  for (int i = 0; i < items.size(); i++) {
	       		  item = items.get(i);
	       		  if (item instanceof Item) {
	       			  Item tf_item = (Item) item;
	       			  metadata+=tf_item.getKeyword()+ ":" + tf_item.getText()+"|";
		       	   } 
		       		else {
		       			metadata+= item.toString()+"|";
		       	   }        
	       	  }
        }	
	} catch (ImageReadException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	}
	return metadata;
}


public static boolean resizeAndSaveImg(String path){
	
	File file = new File(path);
	
	BufferedImage img = null; 
	try{ 
		img = ImageIO.read(file);
		
		int imgWidth  = (int) img.getWidth();
		int imgHeight  = (int) img.getHeight();

		int maxWidth = 445;
		int maxHeight = 305;
		
		int x = maxWidth;
		int y = maxHeight;
		
		double resx = 0;
		double resy = 0;
		
		if (imgWidth > x) {
				resx = x / (double) imgWidth;
				double yy = resx * (double) imgHeight;
				y = (int) yy;
			} 
			//else 
			if (maxHeight < y) {
				resy = maxHeight / (double) imgHeight;
				double xx = resy * (double)imgWidth ;
				x = (int) xx;
				y=maxHeight;
		}
		
		if(x == maxWidth && y == maxHeight ){
			x=imgWidth;
			y = imgHeight;
		}
		

		img = ImageUtils.getScaledImage(img, x, y);
		//Se coloca JPEG como valor null ya que el obj img ya contitene la extencion del archivo
		ImageIO.write(img,"JPEG", file);

		return true;
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return false;
	}
}



} 
