import edu.duke.DirectoryResource;
import edu.duke.ImageResource;
import edu.duke.Pixel;

import java.io.File;


/*This class creates negative of given image */


public class BatchInversions {

    /*Creates inverted image of given image by:
    * 1. taking pixel's rgb values of original image
    * 2. assigning 255- r value to inverted image r value of pixel
    * 3. assigning 255- b value to inverted image b value of pixel
    * 4. assigning 255- g value to inverted image g value of pixel */
    public ImageResource makeInversion(ImageResource inImage){
        ImageResource invertedImage = new ImageResource(inImage.getWidth(), inImage.getHeight());
        for(Pixel pixel: invertedImage.pixels()){
            Pixel inPixel = inImage.getPixel(pixel.getX(), pixel.getY());
            int red = inPixel.getRed();
            int blue = inPixel.getBlue();
            int green = inPixel.getGreen();
            pixel.setBlue(255 - blue);
            pixel.setGreen(255 - green);
            pixel.setRed(255 - red);

        }
        return invertedImage;
    }

    /*Selects multiple files and makes inverted image of it
    * and saves new image with original image name prefixed with
    * "Inverted-" */
    public void selectAndConvertImages(){
        DirectoryResource dr = new DirectoryResource();
        for(File file: dr.selectedFiles()){
            ImageResource inImage = new ImageResource(file);
            ImageResource invertedImage = makeInversion(inImage);
            String name = file.getName();
            String newName = "images/Inverted-" + name;
            invertedImage.setFileName(newName);
            invertedImage.save();
            invertedImage.draw();
        }
    }

    public static void main(String[] args) {
        BatchInversions inversions = new BatchInversions();
        inversions.selectAndConvertImages();
    }
}
