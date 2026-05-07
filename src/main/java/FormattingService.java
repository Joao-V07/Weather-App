public class FormattingService {

    public String[] formatInput(String text){
        try {
            String[] input = text.trim().replace(" ", "").split(",");
            String city = input[0];
            String country = input.length > 1 ? input[1] : null;
            return new String[]{city, country};
        } catch(ArrayIndexOutOfBoundsException | IllegalArgumentException e){
            System.out.println("ta errado");
            return null;
        }
    }
}
