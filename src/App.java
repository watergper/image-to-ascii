import tools.Converter;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner input = new Scanner(System.in);
        System.out.println("input the file directory of the image you want to convert: ");
        String file = input.nextLine();
        Converter image = new Converter(file);
        image.readInFile();
        input.close();
    }
}
