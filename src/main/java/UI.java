import models.WeatherData;
import javax.swing.*;
import java.awt.*;
import javax.swing.JOptionPane;

public class UI {
JFrame frame;
JTextField searchBox;
JButton searchBtn;
JLabel title;
JLabel shadow;
JLabel guideLbl;
JLabel temp;
JLabel feelsLike;
JLabel weather;
JLabel weatherDesc;
JLabel humidity;
JLabel windSpeed;
JLabel windDir;
JLabel cloudiness;
JLabel city;
JLabel country;
CardLayout cardLayout = new CardLayout();
JPanel mainPanel = new JPanel(cardLayout);
JPanel searchPanel = new JPanel(null);
JPanel resultPanel = new JPanel(null);
JButton returnBtn = new JButton();

    /**
     * Creates the UI's frame and the search and result panels.
     */
    void createFrame(){
        frame = new JFrame("Weather App");
        frame.setSize(800, 500);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        searchPanel.setBackground(new Color(150, 201, 254));
        resultPanel.setBackground(new Color(150, 201, 254));
        mainPanel.add(searchPanel, "search");
        mainPanel.add(resultPanel, "result");
        frame.add(mainPanel);
    }

    /**
     * creates the Components for the search panel and adds them to it.
     */
    void createSearchComponents(){
        searchBtn = new JButton("Search");
            centralizeAndAdd(searchBtn, 350, 100, 40, searchPanel);

        title = new JLabel("Weather App");
            centralizeAndAdd(title, 50, 300, 50, searchPanel);
            title.setFont(new Font("Arial", Font.BOLD, 40));
            title.setHorizontalAlignment(SwingConstants.CENTER);
            title.setForeground(new Color(247, 209, 75));
            shadow = new JLabel("Weather App");
            centralizeAndAdd(shadow, 53, 299, 49, searchPanel);
            shadow.setFont(new Font("Arial", Font.BOLD, 40));
            shadow.setHorizontalAlignment(SwingConstants.CENTER);
            shadow.setForeground(Color.BLACK);

        searchBox = new JTextField();
            searchBox.setForeground(Color.BLACK);
            centralizeAndAdd(searchBox, 250, 380, 40, searchPanel);

        guideLbl = new JLabel("Type the location this way: \"city\" or \"city, Country\"");
            centralizeAndAdd(guideLbl, 220, 350, 20, searchPanel);
            guideLbl.setFont(new Font("Open Sans", Font.BOLD, 13));
            guideLbl.setHorizontalAlignment(SwingConstants.CENTER);
    }

    /**
     * Creates the components for the result panel and adds them to it.
     */
    void createResultComponents(){
    temp = new JLabel("00°");
        temp.setFont(new Font("SansSerif", Font.PLAIN, 120));
        temp.setForeground(Color.white);
        resultPanel.add(temp);
    feelsLike = new JLabel("feels like: 00°");
        feelsLike.setFont(new Font("SansSerif", Font.PLAIN, 30));
        feelsLike.setForeground(Color.white);
        resultPanel.add(feelsLike);
    weather = new JLabel("TEXT,");
        weather.setFont(new Font("SansSerif", Font.PLAIN, 50));
        weather.setForeground(Color.white);
        resultPanel.add(weather);
    weatherDesc = new JLabel("it's very hot");
        weatherDesc.setFont(new Font("Arial", Font.PLAIN, 30));
        weatherDesc.setForeground(Color.white);
        resultPanel.add(weatherDesc);
    windSpeed = new JLabel("Wind Speed: 0m/s");
        windSpeed.setFont(new Font("SansSerif", Font.BOLD, 30));
        windSpeed.setForeground(Color.white);
        resultPanel.add(windSpeed);
    windDir = new JLabel("North");
        windDir.setFont(new Font("SansSerif", Font.BOLD, 30));
        windDir.setForeground(Color.white);
        resultPanel.add(windDir);
    humidity = new JLabel("Humidity: 0%");
        humidity.setFont(new Font("SansSerif", Font.BOLD, 30));
        humidity.setForeground(Color.white);
        resultPanel.add(humidity);
    cloudiness = new JLabel("Cloudiness: 0%");
        cloudiness.setFont(new Font("SansSerif", Font.BOLD, 30));
        cloudiness.setForeground(Color.white);
        resultPanel.add(cloudiness);
    city = new JLabel("Sydney");
        city.setFont(new Font("SansSerif", Font.BOLD, 30) );
        city.setForeground(Color.white);
        resultPanel.add(city);
    country = new JLabel("AU");
        country.setFont(new Font("SansSerif", Font.BOLD, 30));
        country.setForeground(Color.white);
        resultPanel.add(country);
    returnBtn = new JButton("Return to Search Screen");
        centralizeAndAdd(returnBtn, 400, 225, 50, resultPanel);
    }

    /**
     * Adds the weather data to the components from the result panel.
     * @param data Weather data.
     */
    void updateResultComponents(WeatherData data){
        this.temp.setText(data.temp + "°");
        setSize(temp, 100, 1);
        this.feelsLike.setText("Feels like: " + data.feelsLike + "°");
        setSize(feelsLike, 285, 90);
        this.weather.setText(data.weatherCondition + "→");
        setSize(weather, 285, 25);
        this.weatherDesc.setText(data.weatherDescription);
        setSize(weatherDesc, weather.getX() + weather.getWidth(), 45);
        this.windSpeed.setText("Wind Speed: " + data.windSpeed + "m/s");
        setSize(windSpeed, 25, 250);
        this.windDir.setText("Wind Direction: " + convertWindDir(data.windDir));
        setSize(windDir, 25, 300);
        this.humidity.setText("Humidity: " + data.humidity + "%");
        setSize(humidity, 450, 250);
        this.cloudiness.setText("Cloudiness: " + data.cloudiness + "%");
        setSize(cloudiness, 450, 300);
        this.city.setText(data.city + ", ");
        setSize(city, 110, 130);
        this.country.setText(data.country);
        setSize(country, city.getWidth() + city.getX() - 5, 130);
    }
    /**
     * Converts the amount of degrees the Location's wind is blowing to Directions (e.g. 45° becomes Northeast)
     * @param degrees amount of degrees the wind is blowing as integer.
     * @return Direction the wind is blowing as String.
     */
    String convertWindDir(int degrees){
        if (degrees >= 338  || degrees < 23) return "North";
        if (degrees < 68) return "Northeast";
        if (degrees < 113) return "East";
        if (degrees < 158) return "Southeast";
        if (degrees < 203) return "South";
        if (degrees < 248) return "Southwest";
        if (degrees < 293) return "West";
        else return "Northwest";
    }

    /**
     * Calls for the methods responsible for updating the UI whenever the user clicks on a button.
     */
    void addActions(){
        searchBtnClicked();
        returnBtnClicked();
    }
    /**
     * Horizontally centralizes and adds a component to the panel based on it's size and the frame's size.
     * @param component The component you wish to centralize and add.
     * @param y The components Y position.
     * @param w The components width.
     * @param h The components height.
     * @param panel The panel the component is located at.
     */
    void centralizeAndAdd(JComponent component, int y, int w, int h, JPanel panel){
        component.setSize(w, h);
        int x = (frame.getWidth() - component.getWidth()) / 2;
        component.setBounds(x, y, w, h);
        panel.add(component);
    }

    /**
     * Calls for the methods that creates and adds the frame, panels, components and actions.
     */
    public void createUI(){
        createFrame();
        createSearchComponents();
        createResultComponents();
        addActions();
        frame.setVisible(true);
    }
    /**
     * Calls for the method responsible for doing the search and updating the UI with the weather data whenever user presses the search button or clicks "Enter"
     */
    private void searchBtnClicked(){
        searchBtn.addActionListener(e -> HandleSearch());
        searchBox.addActionListener(e -> HandleSearch());

    }

    /**
     * Returns to the search panel whenever the user clicks on the return button.
     */
    private void returnBtnClicked(){
        returnBtn.addActionListener(e -> {
            searchBox.setText("");
            cardLayout.show(mainPanel, "search");
        });
    }

    /**
     * Sets the location and preferred size of a component based on its Text.
     * @param comp The target component.
     * @param x The chosen X position of the component.
     * @param y The chosen Y position of the component.
     */
    private void setSize(JComponent comp, int x, int y){
        Dimension pref = comp.getPreferredSize();
        comp.setBounds(x, y, pref.width + 10, pref.height + 1);
    }
    /**
     * Handles the weather data search by sending the user's input to the search method, updating components texts, changing the panel and handling exceptions.
     */
    private void HandleSearch(){
        try {
            WeatherController controller = new WeatherController();
            if (!searchBox.getText().equals("Enter \"City\" or \"City, Country\" (e.g. Sydney or Rio de Janeiro, BR)")) {
                WeatherData data = controller.search(searchBox.getText());
                updateResultComponents(data);
                cardLayout.show(mainPanel, "result");
            } else {
                JOptionPane.showMessageDialog(mainPanel, "You must enter at least a city name");
            }
        } catch (ExceptionHandling ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Invalid Input", JOptionPane.ERROR_MESSAGE);
        }
    }
}


