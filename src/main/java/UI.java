import javax.swing.*;
import java.awt.*;

public class UI {
JFrame frame;
JTextField searchBox;
JButton searchBtn;
JLabel title;
JLabel shadow;
JLabel guideLbl;

    public UI(){
        createFrame();
        createComponents();
        addActions();
        frame.setVisible(true);
    }
    void createFrame(){
        frame = new JFrame("Weather App");
        frame.setSize(600, 500);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setBackground(new Color(150, 201, 254));
    }

    void createComponents(){
searchBtn = new JButton("Search");
    createAndCentralize(searchBtn, 350, 100, 40);
title = new JLabel("Weather App");
    createAndCentralize(title, 50, 300, 50);
    title.setFont(new Font("Arial", Font.BOLD, 40));
    title.setHorizontalAlignment(SwingConstants.CENTER);
    title.setForeground(new Color(247, 209, 75));
    shadow = new JLabel("Weather App");
    createAndCentralize(shadow, 53, 299, 49);
    shadow.setFont(new Font("Arial", Font.BOLD, 40));
    shadow.setHorizontalAlignment(SwingConstants.CENTER);
    shadow.setForeground(Color.BLACK);
searchBox = new JTextField("Search");
    createAndCentralize(searchBox, 250, 300, 40);
guideLbl = new JLabel("Type the name of the city you're looking for:");
    createAndCentralize(guideLbl, 220, 300, 20);
    guideLbl.setFont(new Font("Open Sans", Font.BOLD, 13));
    guideLbl.setHorizontalAlignment(SwingConstants.CENTER);
    }

    void addActions(){

    }

    void createAndCentralize(JComponent component, int y, int w, int h){
        component.setSize(w, h);
        int x = (frame.getWidth() - component.getWidth()) / 2;
        component.setBounds(x, y, w, h);
        frame.add(component);
    }
}

