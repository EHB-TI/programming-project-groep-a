package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class MainScreen {
    private final JFrame mainFrame;
    public final static ImageIcon beerglass = new ImageIcon("src/main/images/beerglass.png");




    public MainScreen() {
        mainFrame = new JFrame("MyBrews");
        mainFrame.setSize(450, 550);
        ImageIcon beerglass = new ImageIcon("src/main/images/beerglass.png");
        mainFrame.setIconImage(beerglass.getImage());
        // Form title
        JLabel mainTitle = new JLabel("Welcome to MyBrews!");
        mainTitle.setFont(new Font("Tahoma", Font.BOLD, 18));
        ImageIcon beerglassTitle = new ImageIcon("src/main/images/beerglasstitle.jpg");
        Image beerglassResized = getScaledImage(beerglassTitle.getImage(), 40, 40);
        JLabel titleImage = new JLabel(new ImageIcon(beerglassResized));

        SpringLayout layout = new SpringLayout();
        mainFrame.setLayout(layout);
        mainFrame.add(mainTitle);
        mainFrame.add(titleImage);

        // Layout title
        layout.putConstraint(SpringLayout.WEST, mainTitle, 10, SpringLayout.WEST, mainFrame);
        layout.putConstraint(SpringLayout.NORTH, mainTitle, 20, SpringLayout.NORTH, mainFrame);
        layout.putConstraint(SpringLayout.WEST, titleImage, 8, SpringLayout.EAST, mainTitle);
        layout.putConstraint(SpringLayout.VERTICAL_CENTER, titleImage, 10, SpringLayout.VERTICAL_CENTER, mainFrame);


        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainFrame.setVisible(true);
    }

    // Function to resize the image for the icon from stackoverflow
    private Image getScaledImage(Image srcImg, int w, int h){
        BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = resizedImg.createGraphics();

        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.drawImage(srcImg, 0, 0, w, h, null);
        g2.dispose();

        return resizedImg;
    }

    public static void main(String[] args) {
        new MainScreen();
    }
}
