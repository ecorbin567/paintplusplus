package entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 * Represents a drawable image entity within the application.
 * This class encapsulates a BufferedImage and adds properties.
 * It implements the Drawable interface to allow it to be rendered on a
 * canvas and the Cloneable interface to support the undo/redo functionality.
 */
public class Image implements Drawable, Cloneable {

    private BufferedImage image;
    private int x;
    private int y;
    private int width;
    private int height;
    private double rotation;

    /**
     * Constructs an Image entity from a BufferedImage.
     * @param image The BufferedImage to wrap in this entity.
     */
    public Image(BufferedImage image) {
        this.image = image;
        this.width = image.getWidth();
        this.height = image.getHeight();
        this.x = 0;
        this.y = 0;
    }

    /**
     * Returns the underlying BufferedImage.
     * @return The raw BufferedImage data.
     */
    public BufferedImage getBufferedImage() {
        return image;
    }

    /**
     * Sets new dimensions for rendering the image.
     * This method updates the width and height that will be used when the image
     * is drawn. It does not scale the underlying BufferedImage data.
     * @param newWidth The new width for the image.
     * @param newHeight The new height for the image.
     */
    public void resize(int newWidth, int newHeight) {
        this.width = newWidth;
        this.height = newHeight;
    }

    /**
     * Rotates the image by a specified number of degrees.
     * The rotation is cumulative.
     * @param degrees The number of degrees to add to the current rotation.
     */
    public void rotate(double degrees) {
        this.rotation += degrees;
    }

    /**
     * Crops the underlying image to the specified rectangle.
     * This is an operation that replaces the internal BufferedImage with a
     * new, smaller sub-image. The width and height are also updated.
     * @param cropX The x-coordinate of the top-left corner of the crop area.
     * @param cropY The y-coordinate of the top-left corner of the crop area.
     * @param cropWidth The width of the crop area.
     * @param cropHeight The height of the crop area.
     */
    public void crop(int cropX, int cropY, int cropWidth, int cropHeight) {
        image = image.getSubimage(cropX, cropY, cropWidth, cropHeight);
        this.width = cropWidth;
        this.height = cropHeight;
    }

    /**
     * Draws the image on the screen.
     * This method handles applying the current rotation, position, and dimension
     * to render the image correctly.
     * @param g The Graphics2D to put the image on.
     */
    public void draw(Graphics2D g) {
        final Graphics2D g2d = (Graphics2D) g.create();

        final int centerX = x + width / 2;
        final int centerY = y + height / 2;

        g2d.rotate(Math.toRadians(rotation), centerX, centerY);
        g2d.drawImage(image, x, y, width, height, null);

        g2d.dispose();
    }

    /**
     * Creates and returns a deep copy of this Image object.
     * This method creates a new BufferedImage with a copy of the pixel data.
     * This is important for the "Undo" feature, so that changes to a copy
     * don't affect the original that is saved in the history.
     * All other properties are also copied.
     * @return A new Image instance that is a deep copy of this one.
     */
    @Override
    public Image clone() {
        final BufferedImage copy = new BufferedImage(
                image.getWidth(),
                image.getHeight(),
                image.getType()
        );

        final Graphics2D g = copy.createGraphics();
        g.drawImage(image, 0, 0, null);
        g.dispose();

        final Image cloned = new Image(copy);
        cloned.x = this.x;
        cloned.y = this.y;
        cloned.width = this.width;
        cloned.height = this.height;
        cloned.rotation = this.rotation;

        return cloned;
    }
}
