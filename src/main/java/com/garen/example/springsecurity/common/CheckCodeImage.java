package com.garen.example.springsecurity.common;

import lombok.Data;
import org.apache.commons.lang3.RandomStringUtils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.time.LocalDateTime;
import java.util.Random;

@Data
public class CheckCodeImage {
    private String checkCode;
    private LocalDateTime expiresTime;

    public CheckCodeImage(String checkCode, long usefulTime) {
        this.checkCode = checkCode;
        this.expiresTime = LocalDateTime.now().plusSeconds(usefulTime);
    }

    public void writeTo(OutputStream ops) {
        if (ops == null) throw new IllegalArgumentException("The argument can not be null!");
        try {
            ImageIO.write(initImage(), "JPEG", ops);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private BufferedImage initImage() {
        int width = 100;
        int height = 50;
        Random random = new Random();

        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics graphics = image.getGraphics();
        // 背景填充
        graphics.setColor(Color.LIGHT_GRAY);
        graphics.fillRect(0, 0, width, height);

        // 画干扰线
        graphics.setColor(Color.BLACK);
        for (int i = 1; i <= 30; i++) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            graphics.drawLine(x, y, x + i, y + i);
        }

        // 画校验码
        graphics.setFont(new Font("consolas", Font.BOLD, height * 2 / 3));
        graphics.setColor(new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255)));
        graphics.drawString(checkCode, 10, height - 15);

        return image;
    }

    public static void main(String[] args) throws FileNotFoundException {
        CheckCodeImage checkCodeImage = new CheckCodeImage(RandomStringUtils.randomNumeric(4), 60L);
        checkCodeImage.writeTo(new FileOutputStream("d://image.jpg"));
    }
}
