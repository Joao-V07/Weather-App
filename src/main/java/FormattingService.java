public class FormattingService {
    UI ui = new UI();
    public String formatInput(){
        String[] input = ui.searchBox.getText().trim().split(",");
        String city = input[0];
        String country = input[1];
        System.out.println(city + country);
        return city + country;
    }
}
