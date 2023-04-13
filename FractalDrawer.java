import java.awt.image.BufferedImage;

public class FractalDrawer {

    // Color variables
    public int[] colors;
    public static final int black = 0;

    // Image output variables
    public BufferedImage image;
    public int[] pixelColors; // 1d array representing all the pixels in the image
    public int numberOfFrames;

    // constructor
    public FractalDrawer(int width, int height, int numberOfFrames) {
        this.colors = new int[16];
        this.image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        this.pixelColors = new int[width * height];
        this.numberOfFrames = numberOfFrames;
        initializeColorMap();
        for (int i = 0; i < pixelColors.length; i++) {
            pixelColors[i] = 0xFF000000; // set all pixels to black with 100% alpha to start
        }
        // set pixels on image
        this.image.setRGB(0, 0, width, height, pixelColors, 0, width);
    }

    /**
     * convert RGB value to integer for use by the BufferedImage class
     * code inspired by CodeSpaceIndica on Stackoverflow
     */
    public static int RGBtoInt(int r, int g, int b) {
        r = (r << 16) & 0x00FF0000;    // bitwise AND masks all values but red, << shift red 16 bits
        g = (g << 8) & 0x0000FF00;     // bitwise AND masks all values but green, << shift green 8 bits
        b = b & 0x000000FF;            // bitwise AND masks all values but blue
        return 0xFF000000 | r | g | b; // bitwise OR to combine everything and return integer value.
    }

    /**
     * @param n the number of iterations from the mandelbrot code
     * @param maxIterations the max allowed iterations from the mandelbrot code
     * @return the integer value of the color from the color map (colors[i])
     */
    public int getColor(int n, int maxIterations) {
        if (n < maxIterations && n > 0) {
            int i = n % this.colors.length;
            return this.colors[i];
        }
        return black;
    }

	/**
     * Map colors to indices in the color map, first converting them to integer values.
     * Color values are the default gradient colors of Ultra Fractal, a fractal viewing program.
     * I took the colors and getColor() function from StackOverflow:
     * https://stackoverflow.com/questions/16500656/which-color-gradient-is-used-to-color-mandelbrot-in-wikipedia
     * 
     */
    private void initializeColorMap() {
        this.colors[0]  = RGBtoInt(66, 30, 15);
        this.colors[1]  = RGBtoInt(25, 7, 26);
        this.colors[2]  = RGBtoInt(9, 1, 47);
        this.colors[3]  = RGBtoInt(4, 4, 73);
        this.colors[4]  = RGBtoInt(0, 7, 100);
        this.colors[5]  = RGBtoInt(12, 44, 138);
        this.colors[6]  = RGBtoInt(24, 82, 177);
        this.colors[7]  = RGBtoInt(57, 125, 209);
        this.colors[8]  = RGBtoInt(134, 181, 229);
        this.colors[9]  = RGBtoInt(211, 236, 248);
        this.colors[10] = RGBtoInt(241, 233, 191);
        this.colors[11] = RGBtoInt(248, 201, 95);
        this.colors[12] = RGBtoInt(255, 170, 0);
        this.colors[13] = RGBtoInt(204, 128, 0);
        this.colors[14] = RGBtoInt(153, 87, 0);
        this.colors[15] = RGBtoInt(106, 52, 3);
    }

    
}
