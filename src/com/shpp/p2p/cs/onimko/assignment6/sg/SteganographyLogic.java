package com.shpp.p2p.cs.onimko.assignment6.sg;

import acm.graphics.GImage;

public class SteganographyLogic {
    /**
     * Given a GImage containing a hidden message, finds the hidden message
     * contained within it and returns a boolean array containing that message.
     * <p/>
     * A message has been hidden in the input image as follows.  For each pixel
     * in the image, if that pixel has a red component that is an even number,
     * the message value at that pixel is false.  If the red component is an odd
     * number, the message value at that pixel is true.
     *
     * @param source The image containing the hidden message.
     * @return The hidden message, expressed as a boolean array.
     */
    public static boolean[][] findMessage(GImage source) {
       int[][] im = source.getPixelArray();
       boolean[][] bwIm = new boolean[im.length][im[0].length];
       for(int x = 0; x < im.length; x++) {
           for(int y = 0; y < im[x].length; y++)
               if(GImage.getRed(im[x][y]) % 2 == 1) bwIm[x][y] = true;
               else bwIm[x][y] = false;
       }
        return bwIm;
    }

    /**
     * Hides the given message inside the specified image.
     * <p/>
     * The image will be given to you as a GImage of some size, and the message will
     * be specified as a boolean array of pixels, where each white pixel is denoted
     * false and each black pixel is denoted true.
     * <p/>
     * The message should be hidden in the image by adjusting the red channel of all
     * the pixels in the original image.  For each pixel in the original image, you
     * should make the red channel an even number if the message color is white at
     * that position, and odd otherwise.
     * <p/>
     * You can assume that the dimensions of the message and the image are the same.
     * <p/>
     *
     * @param message The message to hide.
     * @param source  The source image.
     * @return A GImage whose pixels have the message hidden within it.
     */
    public static GImage hideMessage(boolean[][] message, GImage source) {
      int[][] im = source.getPixelArray();
      for(int x = 0; x < im.length; x++) {
        for(int y = 0; y < im[x].length; y++)
          if (message[x][y] == true) {
            if (GImage.getRed(im[x][y]) % 2 == 0)
              im[x][y] = setRed(im[x][y], GImage.getRed(im[x][y]) + 1);
          }else if (GImage.getRed(im[x][y]) % 2 == 1)
            im[x][y] = setRed(im[x][y], GImage.getRed(im[x][y]) - 1);
      }
        return new GImage(im);
    }

  /**
   * Method changed the red color in a RGB-pixel.
   * @param var the input RGB-pixel
   * @param red the input value of red color.
   * @return the RGB-pixel with new value of red color.
   */
    private static int setRed(int var, int red) {
      return GImage.createRGBPixel(red,GImage.getGreen(var),GImage.getBlue(var),GImage.getAlpha(var));
    }
}
