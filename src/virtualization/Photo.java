package virtualization;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.videoio.VideoCapture;


import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;

public class Photo {
	private Mat matrix = null;
	private VideoCapture c = null;
	
	public ImageView takePhoto(ImageView image) {
		Photo p = new Photo();
		WritableImage wii = p.capturePhoto();
		Image img = (Image) wii;
		image.setImage(img);
		
		return image;
	}
	
	private WritableImage capturePhoto() {
		WritableImage wiiU = null;
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
	    c = new VideoCapture(0);
		matrix = new Mat();
		c.read(matrix);
		
		if(c.isOpened()) {
			if(c.read(matrix)) {
				BufferedImage img = new BufferedImage(matrix.width(), matrix.height(), BufferedImage.TYPE_3BYTE_BGR);
				WritableRaster r = img.getRaster();
				DataBufferByte db = (DataBufferByte) r.getDataBuffer();
				byte[] bd = db.getData();
				matrix.get(0, 0, bd);
				
				wiiU = SwingFXUtils.toFXImage(img, null);
			}
		}
		c.release();
		return wiiU;
	}
}
