import java.awt.image.BufferedImage;

public class FractalDrawer {

    // Color variables
    public int[] colors;
    public int black;

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
     * I took the colors from StackOverflow:
     * https://stackoverflow.com/questions/16500656/which-color-gradient-is-used-to-color-mandelbrot-in-wikipedia
     * 
     */
    private void initializeColorMap() {
        this.colors[0]  = RGBtoInt(2, 3, 105);
        this.colors[1]  = RGBtoInt(35, 34, 113);
        this.colors[2]  = RGBtoInt(68, 66, 122);
        this.colors[3]  = RGBtoInt(101, 98, 131);
        this.colors[4]  = RGBtoInt(134, 129, 140);
        this.colors[5]  = RGBtoInt(167, 161, 149);
        this.colors[6]  = RGBtoInt(200, 193, 157);
        this.colors[7]  = RGBtoInt(233, 225, 166);
        this.colors[8]  = RGBtoInt(240, 231, 159);
        this.colors[9]  = RGBtoInt(221, 213, 137);
        this.colors[10] = RGBtoInt(201, 195, 114);
        this.colors[11] = RGBtoInt(182, 176, 92);
        this.colors[12] = RGBtoInt(163, 158, 69);
        this.colors[13] = RGBtoInt(143, 139, 47);
        this.colors[14] = RGBtoInt(124, 121, 24);
        this.colors[15] = RGBtoInt(105, 103, 2);
        this.black = RGBtoInt(0, 0, 0);
    }

    // color palettes

    // ffmpeg write to mp4
    // ffmpeg -framerate 24 -i frame%03d.png -pix_fmt yuv420p -c:v libx264 -crf 1 -c:a copy new.mp4

    /*
    RGB(54, 28, 227)
    RGB(128, 0, 213)
    RGB(163, 0, 181)
    RGB(187, 0, 159)
    RGB(207, 0, 143)
    RGB(227, 0, 127)
    RGB(247, 0, 111)
    RGB(255, 0, 89)
    RGB(255, 60, 75)
    RGB(255, 91, 56)
    RGB(255, 116, 30)
    RGB(255, 140, 0)
    RGB(248, 165, 0)
    RGB(235, 187, 0)
    RGB(219, 208, 0)
    RGB(201, 227, 28)
    */

    /*
    RGB(66, 30, 15);
    RGB(25, 7, 26);
    RGB(9, 1, 47);
    RGB(4, 4, 73);
    RGB(0, 7, 100);
    RGB(12, 44, 138);
    RGB(24, 82, 177);
    RGB(57, 125, 209);
    RGB(134, 181, 229);
    RGB(211, 236, 248);
    RGB(241, 233, 191);
    RGB(248, 201, 95);
    RGB(255, 170, 0);
    RGB(204, 128, 0);
    RGB(153, 87, 0);
    RGB(106, 52, 3);
    */

    // this one kinda ugly
    /*
    RGB(2, 3, 105)
    RGB(35, 34, 113)
    RGB(68, 66, 122)
    RGB(101, 98, 131)
    RGB(134, 129, 140)
    RGB(167, 161, 149)
    RGB(200, 193, 157)
    RGB(233, 225, 166)
    RGB(240, 231, 159)
    RGB(221, 213, 137)
    RGB(201, 195, 114)
    RGB(182, 176, 92)
    RGB(163, 158, 69)
    RGB(143, 139, 47)
    RGB(124, 121, 24)
    RGB(105, 103, 2)
    */
}
