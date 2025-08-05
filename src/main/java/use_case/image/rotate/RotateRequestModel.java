package use_case.image.rotate;

public class RotateRequestModel {
    private final double degrees;

    public RotateRequestModel(double degrees) {
        this.degrees = degrees;
    }

    public double getDegrees() { return degrees; }
}
