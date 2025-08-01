package entity;


import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 * An image.
 */
public class Image implements Editable, Cloneable {

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

    public void resize(int newWidth, int newHeight) {
        this.width = newWidth;
        this.height = newHeight;
    }

    public void rotate(double degrees) {
        this.rotation += degrees;
    }

    public void crop(int cropX, int cropY, int cropWidth, int cropHeight) {
        image = image.getSubimage(cropX, cropY, cropWidth, cropHeight);
        this.width = cropWidth;
        this.height = cropHeight;
    }

    public void draw(Graphics2D g) {
        Graphics2D g2d = (Graphics2D) g.create();

        int centerX = x + width / 2;
        int centerY = y + height / 2;

        g2d.rotate(Math.toRadians(rotation), centerX, centerY);
        g2d.drawImage(image, x, y, width, height, null);

        g2d.dispose();
    }

    @Override
    public Image clone() {
        BufferedImage copy = new BufferedImage(
                image.getWidth(),
                image.getHeight(),
                image.getType()
        );

        Graphics2D g = copy.createGraphics();
        g.drawImage(image, 0, 0, null);
        g.dispose();

        Image cloned = new Image(copy);
        cloned.x = this.x;
        cloned.y = this.y;
        cloned.width = this.width;
        cloned.height = this.height;
        cloned.rotation = this.rotation;

        return cloned;
    }
}
