package qwerty;

import nu.pattern.OpenCV;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.videoio.VideoCapture;

import javax.swing.*;
import java.awt.event.WindowEvent;

public class VideoShow {
    static {
//        nu.pattern.OpenCV.loadLocally();
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
//        OpenCV.loadLocally();
    }

    public static void main(String[] args) {
        JFrame window = new JFrame();
        JLabel screen = new JLabel();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);

//        VideoCapture capture = new VideoCapture("src/main/resources/chel.mp4");
        VideoCapture capture = new VideoCapture(0);

        Mat frame = new Mat();
        MatOfByte buf = new MatOfByte();
        ImageIcon icon;

        while (capture.grab()) {
            capture.read(frame);

            Imgcodecs.imencode(".png", frame, buf);
            icon = new ImageIcon(buf.toArray());
            screen.setIcon(icon);
            window.setContentPane(screen);
            window.pack();
        }

        capture.release();
        window.dispatchEvent(new WindowEvent(window, WindowEvent.WINDOW_CLOSING));
    }
}
