package com.nullprogram.noise;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import lombok.SneakyThrows;
import lombok.val;

public final class Launcher {

    @SneakyThrows
    public static void main(String[] args) {
        ClPerlinNoise cl = new ClPerlinNoise(0, 2, 2, 0.2f);
        cl.sample(2f);
        cl.sample(-10f);
        System.exit(0);

        /* Create an image. */
        Noise noise = new PerlinNoise(0, 2);
        double max = 32.0;
        double step = 0.04;
        int size = (int) (max / step);
        val im = new BufferedImage(size, size, BufferedImage.TYPE_INT_RGB);
        for (int i = 0; i < size; i++) {
            double x = i * step;
            for (int j = 0; j < size; j++) {
                double y = j * step;
                Vector p = new Vector(x, y);
                double m = noise.sample(p);
                float v = (float) ((m + 2f / 3f) * 2f / 3f);
                int c = new Color(v, v, v).getRGB();
                im.setRGB(i, j, c);
            }
        }

        /* Save the image. */
        try {
            ImageIO.write(im, "PNG", new File("out.png"));
        } catch (IOException e) {
            System.out.println("Failed to write image.");
            System.exit(-1);
        }
    }
}
