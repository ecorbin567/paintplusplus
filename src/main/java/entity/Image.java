package entity;


import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class Image implements Drawable {

    private BufferedImage image;
    private int x, y;
    private int width, height;
    private double rotation = 0.0;

    public Image(BufferedImage image) {
        this.image = image;
        this.width = image.getWidth();
        this.height = image.getHeight();
        this.x = 0;
        this.y = 0;
    }

    public BufferedImage getBufferedImage() {
        return image;
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void resize(int newWidth, int newHeight) {
        this.width = newWidth;
        this.height = newHeight;
    }

    public void rotate(double degrees) {
        this.rotation += degrees;
    }

    public void crop(int cropX, int cropY, int cropWidth, int cropHeight) {}

    public void draw(Graphics2D g) {
        Graphics2D g2d = (Graphics2D) g.create();

        int centerX = x + width / 2;
        int centerY = y + height / 2;

        g2d.rotate(Math.toRadians(rotation), centerX, centerY);
        g2d.drawImage(image, x, y, width, height, null);

        g2d.dispose();
    }
}
