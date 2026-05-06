import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class UI {
JFrame frame;
JTextField searchBox;
JButton searchBtn;
JLabel title;
JLabel shadow;
JLabel guideLbl;

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
searchBox = new JTextField("Enter \"City\" or \"City, Country\" (e.g. Sydney or Rio de Janeiro, BR)");
    searchBox.setForeground(Color.GRAY);
    createAndCentralize(searchBox, 250, 380, 40);
guideLbl = new JLabel("Type the name of the city you're looking for:");
    createAndCentralize(guideLbl, 220, 300, 20);
    guideLbl.setFont(new Font("Open Sans", Font.BOLD, 13));
    guideLbl.setHorizontalAlignment(SwingConstants.CENTER);
    }

    void addActions(){
        searchBtnClicked();
        focusListener();
    }

    void createAndCentralize(JComponent component, int y, int w, int h){
        component.setSize(w, h);
        int x = (frame.getWidth() - component.getWidth()) / 2;
        component.setBounds(x, y, w, h);
        frame.add(component);
    }

    public void createUI(){
        createFrame();
        createComponents();
        addActions();
        frame.setVisible(true);
        SwingUtilities.invokeLater(() -> searchBtn.requestFocusInWindow());
    }

    private void searchBtnClicked(){
        WeatherService weatherAPI = new WeatherService();
        FormattingService formatter = new FormattingService();
        searchBtn.addActionListener(e -> {
            String[] location = formatter.formatInput(searchBox.getText());
            String city = location[0];
            String country = location[1];
            weatherAPI.APIRequest(city, country);
                });
    }
    private void focusListener(){
        searchBox.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (searchBox.getText().equals("Enter \"City\" or \"City, Country\" (e.g. Sydney or Rio de Janeiro, BR)")) {
                    searchBox.setText("");
                    searchBox.setForeground(Color.BLACK);
                }
            }
            @Override
            public void focusLost(FocusEvent e) {
                    if (searchBox.getText().isEmpty()){
                        searchBox.setText("Enter \"City\" or \"City, Country\" (e.g. Sydney or Rio de Janeiro, BR)");
                        searchBox.setForeground(Color.GRAY);
            }
        }
    });
        }
    }


