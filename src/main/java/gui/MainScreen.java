package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class MainScreen {
    public final static ImageIcon beerGlass = new ImageIcon("src/main/images/beerglass.png");




    public MainScreen() {
        JFrame mainFrame = new JFrame("MyBrews");
        mainFrame.setSize(450, 350);
        mainFrame.setIconImage(beerGlass.getImage());
        // Form title
        JLabel mainTitle = new JLabel("Welcome to MyBrews!");
        mainTitle.setFont(new Font("Tahoma", Font.BOLD, 18));
        // Title icons
        // Before title
        ImageIcon beerGlassTitleImageFlipped = new ImageIcon("src/main/images/beerglasstitleflipped.png");
        Image beerGlassTitleImageFlippedResized = getScaledImage(beerGlassTitleImageFlipped.getImage(), 40, 40);
        JLabel titleImageBefore = new JLabel(new ImageIcon(beerGlassTitleImageFlippedResized));
        // After title
        ImageIcon beerGlassTitleImage = new ImageIcon("src/main/images/beerglasstitle.jpg");
        Image beerGlassTitleImageResized = getScaledImage(beerGlassTitleImage.getImage(), 40, 40);
        JLabel titleImageAfter = new JLabel(new ImageIcon(beerGlassTitleImageResized));
        // App description
        JTextArea appDescription = new JTextArea(5,40);
        appDescription.setEditable(false);
        // Search user button
        JButton findUser = new JButton("Look for fellow beer lovers!");
        // Add user button
        JButton addUser = new JButton("          Add a new user!          ");

        /*
        Dimension findUserSize = new Dimension(findUser.getSize());
        addUser.setPreferredSize(findUserSize);

        Doesn't work, even if fora say that you should use preferred size with layout managers
        So a shady solution with spaces is used
         */





        SpringLayout layout = new SpringLayout();
        mainFrame.setLayout(layout);
        mainFrame.add(titleImageBefore);
        mainFrame.add(mainTitle);
        mainFrame.add(titleImageAfter);
        mainFrame.add(appDescription);
        mainFrame.add(findUser);
        mainFrame.add(addUser);


        // Layout title
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, mainTitle, 0, SpringLayout.HORIZONTAL_CENTER, mainFrame.getContentPane());
        layout.putConstraint(SpringLayout.NORTH, mainTitle, 20, SpringLayout.NORTH, mainFrame);
        // Layout icons
        layout.putConstraint(SpringLayout.EAST, titleImageBefore, -8, SpringLayout.WEST, mainTitle);
        layout.putConstraint(SpringLayout.VERTICAL_CENTER, titleImageBefore, 10, SpringLayout.VERTICAL_CENTER, mainFrame);
        layout.putConstraint(SpringLayout.WEST, titleImageAfter, 8, SpringLayout.EAST, mainTitle);
        layout.putConstraint(SpringLayout.VERTICAL_CENTER, titleImageAfter, 10, SpringLayout.VERTICAL_CENTER, mainFrame);
        // Layout description text area
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, appDescription, 0, SpringLayout.HORIZONTAL_CENTER, mainFrame.getContentPane());
        layout.putConstraint(SpringLayout.NORTH, appDescription, 20, SpringLayout.SOUTH, mainTitle);
        // Layout buttons
        layout.putConstraint(SpringLayout.EAST, findUser, -5, SpringLayout.HORIZONTAL_CENTER, mainFrame.getContentPane());
        layout.putConstraint(SpringLayout.NORTH, findUser, 20, SpringLayout.SOUTH, appDescription);
        layout.putConstraint(SpringLayout.WEST, addUser, 5, SpringLayout.HORIZONTAL_CENTER, mainFrame.getContentPane());
        layout.putConstraint(SpringLayout.NORTH, addUser, 20, SpringLayout.SOUTH, appDescription);





        mainFrame.setLocationRelativeTo(null); // Center of screen
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainFrame.setVisible(true);

        findUser.addActionListener(e -> {
            new FindUserScreen();
        });

        addUser.addActionListener(e -> {
            new AddUserScreen();
        });

    }

    // Function to resize the image for the icon
    // From stackoverflow: https://stackoverflow.com/questions/6714045/how-to-resize-jlabel-imageicon
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
