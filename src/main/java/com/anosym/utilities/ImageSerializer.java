/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anosym.utilities;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Serializable;
import javax.imageio.ImageIO;

/**
 *
 * @author Marembo
 */
public class ImageSerializer implements Serializable {

  public static byte[] serializeImage(BufferedImage image) throws IOException {
    ByteArrayOutputStream baos = new ByteArrayOutputStream(1000);
    ImageIO.write(image, "jpg", baos);
    byte[] bytes = baos.toByteArray();
    baos.close();
    return bytes;
  }

  public static byte[] serializeImage(BufferedImage image, String type) throws IOException {
    ByteArrayOutputStream baos = new ByteArrayOutputStream(1000);
    ImageIO.write(image, type, baos);
    byte[] bytes = baos.toByteArray();
    baos.close();
    return bytes;
  }

  public static byte[] serializeImage0(Image image) throws IOException {
    if (image instanceof BufferedImage) {
      return serializeImage((BufferedImage) image);
    }
    BufferedImage image0 = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_RGB);
    image0.getGraphics().drawImage(image, 0, 0, null);
    return serializeImage(image0);
  }

  public static byte[] serializeImage0(Image image, int imageWidth, int imageHeight) throws IOException {
    if (image instanceof BufferedImage) {
      return serializeImage((BufferedImage) image);
    }
    BufferedImage image0 = new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_INT_RGB);
    image0.getGraphics().drawImage(image, 0, 0, null);
    return serializeImage(image0);
  }

  public static Image deserializeImage(byte[] bytes) throws IOException {
    BufferedImage imag = ImageIO.read(new ByteArrayInputStream(bytes));
    return imag;
  }

  public static BufferedImage[][] split(int rows, int cols, BufferedImage image) {
    BufferedImage[][] images = new BufferedImage[rows][cols];
    int width = image.getWidth() / cols, height = image.getHeight() / rows;
    for (int x = 0; x < rows; x++) {
      for (int y = 0; y < cols; y++) {
        images[x][y] = image.getSubimage(width * x, height * y, width, height);
      }
    }
    return images;
  }
}
