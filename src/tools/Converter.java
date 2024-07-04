package tools;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class Converter {
    private File contents;
    private BufferedImage image;
    public static final char[] ASCII_CHARS = {' ', '.', '*', ':', 'o', '&', '8', '#', '@'}; 


    public Converter(String file) {
        contents = new File(file);
    }
    
    public void readInFile() {
        try {
            image = ImageIO.read(contents);
            
            int newWidth = 80;
            int newHeight = (int) Math.round((double) newWidth/image.getWidth() * image.getHeight());

            BufferedImage resizedImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_RGB); 
            resizedImage.getGraphics().drawImage(image.getScaledInstance(newWidth, newHeight, BufferedImage.SCALE_SMOOTH), 0, 0, null);


            convertImageToASCII(resizedImage); 

        }
        catch (IOException e) {
            e.printStackTrace(); 
        }
    }

    private void convertImageToASCII(BufferedImage image) {
       int width = image.getWidth();
       int height = image.getHeight(); 

       for (int i = 0; i < height; i+= 2) {
            for (int j = 0; j < width; j++) {
                int pixel = image.getRGB(j, i);
                int grayscale = getGrayScale(pixel);
                
                int index = (int) Math.round((double)(grayscale/255.0) * (ASCII_CHARS.length - 1));
                System.out.print(ASCII_CHARS[index]);
            }
            System.out.println();
       }
    }

    private int getGrayScale(int pixel) {
        int blue = pixel & 0xff;
        int green = (pixel & 0xff00) >> 8;
        int red = (pixel & 0xff0000) >> 16;
               
        return (int) (0.21 * red + 0.72 * green + 0.07 * blue); 
    }
}