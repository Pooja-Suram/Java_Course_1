import edu.duke.*;

import java.io.File;

/* This class converts given image into grayscale by:
* 1. Selecting multiple files
* 2. for each image,
*       create new grayscale version image of original image
*       calculate average of RGB values of each pixel
*        assign those pixels rgb values to newly created grayscale image*/
public class GreyScaleImages {

    /*Selects multiple images and converts them into grayscale
    * and saves new image with original image name
    * prefixed with "gray-" */
    public void selectFilesAndConvertToGreyScale(){
        DirectoryResource dr = new DirectoryResource();
        for(File f : dr.selectedFiles()){
            ImageResource inImage = new ImageResource(f);
            ImageResource greyImage = convertToGray(inImage);
            String fileName = f.getName();
            String greyImageName = "images/gray-" + fileName;
            greyImage.setFileName(greyImageName);
            greyImage.draw();
            greyImage.save();
        }
    }

    /*Converts given image to gray */
    public ImageResource convertToGray(ImageResource inImage){
        ImageResource greyImage = new ImageResource(inImage.getWidth(), inImage.getHeight());
        for(Pixel pixel: greyImage.pixels()){
            Pixel inPixel = inImage.getPixel(pixel.getX(), pixel.getY());
            int average = (inPixel.getRed() + inPixel.getBlue()+ inPixel.getGreen())/3;
            pixel.setRed(average);
            pixel.setGreen(average);
            pixel.setBlue(average);
        }

        return greyImage;
    }

    public static void main(String[] args) {
        GreyScaleImages image = new GreyScaleImages();
        image.selectFilesAndConvertToGreyScale();
    }
}
