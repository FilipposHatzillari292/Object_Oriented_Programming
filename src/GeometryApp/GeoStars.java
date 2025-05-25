package GeometryApp;

class GeoStars {
    private final int width;
    private final int height;
    private final char drawChar;


    public GeoStars(int width, int height, char drawChar) {
        if (width < 1 || height < 1) {
            throw new IllegalArgumentException("Width and height must be positive");
        }
        this.width = width;
        this.height = height;
        this.drawChar = drawChar;
    }


    public void draw() {
        drawEdge();
        drawMiddle();
        if (height > 1) {
            drawEdge();
        }
    }

    private void drawEdge() {
        for (int i = 0; i < width; i++) {
            System.out.print(drawChar + "  ");
        }
        System.out.println();
    }

    private void drawMiddle() {
        for (int i = 0; i < height - 2; i++) {
            drawMiddleLine();
        }
    }

    private void drawMiddleLine() {
        System.out.print(drawChar);
        for (int j = 0; j < width - 2; j++) {
            System.out.print("   ");
        }
        if (width > 1) {
            System.out.print("  " + drawChar);
        }
        System.out.println();
    }
}
