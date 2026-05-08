import models.WeatherData;

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
CardLayout cardLayout = new CardLayout();
JPanel mainPanel = new JPanel(cardLayout);
JPanel searchPanel = new JPanel(null);
JPanel resultPanel = new JPanel(null);

    void createFrame(){
        frame = new JFrame("Weather App");
        frame.setSize(600, 500);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        searchPanel.setBackground(new Color(150, 201, 254));
        resultPanel.setBackground(new Color(150, 201, 254));
        mainPanel.add(searchPanel, "search");
        mainPanel.add(resultPanel, "result");
        frame.add(mainPanel);
    }

    void createComponents(){
        searchBtn = new JButton("Search");
            createAndCentralize(searchBtn, 350, 100, 40, searchPanel);

        title = new JLabel("Weather App");
            createAndCentralize(title, 50, 300, 50, searchPanel);
            title.setFont(new Font("Arial", Font.BOLD, 40));
            title.setHorizontalAlignment(SwingConstants.CENTER);
            title.setForeground(new Color(247, 209, 75));
            shadow = new JLabel("Weather App");
            createAndCentralize(shadow, 53, 299, 49, searchPanel);
            shadow.setFont(new Font("Arial", Font.BOLD, 40));
            shadow.setHorizontalAlignment(SwingConstants.CENTER);
            shadow.setForeground(Color.BLACK);

        searchBox = new JTextField("Enter \"City\" or \"City, Country\" (e.g. Sydney or Rio de Janeiro, BR)");
            searchBox.setForeground(Color.GRAY);
            createAndCentralize(searchBox, 250, 380, 40, searchPanel);

        guideLbl = new JLabel("Type the name of the city you're looking for:");
            createAndCentralize(guideLbl, 220, 300, 20, searchPanel);
            guideLbl.setFont(new Font("Open Sans", Font.BOLD, 13));
            guideLbl.setHorizontalAlignment(SwingConstants.CENTER);
    }

    void addActions(){
        searchBtnClicked();
        focusListener();
    }

    void createAndCentralize(JComponent component, int y, int w, int h, JPanel panel){
        component.setSize(w, h);
        int x = (frame.getWidth() - component.getWidth()) / 2;
        component.setBounds(x, y, w, h);
        panel.add(component);
    }

    public void createUI(){
        createFrame();
        createComponents();
        addActions();
        frame.setVisible(true);
        SwingUtilities.invokeLater(() -> searchBtn.requestFocusInWindow());
    }

    private void searchBtnClicked(){
        searchBtn.addActionListener(e -> {
            WeatherController controller = new WeatherController();
            WeatherData data = controller.search(searchBox.getText());
            if (data != null) {
                cardLayout.show(mainPanel, "result");
                System.out.println(data.weatherCondition);
                System.out.println(data.weatherDescription);
                System.out.println(data.temp);
                System.out.println(data.feelsLike);
                System.out.println(data.tempMax);
                System.out.println(data.tempMin);
                System.out.println(data.humidity);
                System.out.println(data.windSpeed);
                System.out.println(data.windDir);


            } else{
                System.out.println("deu bosta");
            }
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


