import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import javax.swing.*;

public class ImgShow extends JFrame {
    static {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        System.out.println(Core.VERSION);
    }

    public static void main(String[] args) {
        JFrame window = new JFrame();
        JLabel screen = new JLabel();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);

        Mat img = Imgcodecs.imread("src/main/resources/cat.jpg");
        Mat imgEmpty = new Mat(img.size(), img.type());

        Mat kernel = new Mat(10, 10, CvType.CV_8UC1, new Scalar(1.0));

        Imgproc.dilate(img, imgEmpty, kernel);
        Imgproc.erode(img, imgEmpty, kernel);
        Imgproc.cvtColor(img, imgEmpty, Imgproc.COLOR_RGB2GRAY);
        Imgproc.GaussianBlur(img, imgEmpty, new Size(15, 15), 0);
        Imgproc.Canny(img, imgEmpty, 2, 2);

        MatOfByte buf = new MatOfByte();
        Imgcodecs.imencode(".jpg", imgEmpty, buf);
        ImageIcon icon = new ImageIcon(buf.toArray());

        screen.setIcon(icon);
        window.getContentPane().add(screen);
        window.pack();
    }
}
