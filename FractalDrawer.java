public class FractalDrawer {

    public FractalDrawer() {
        initializeColorMap();
    }

    //Color variables
    public int[] colors = new int[16];
    public static int black = 0;

    /**
     * convert RGB value to integer for use by the BufferedImage class
     * 
     * @param red
     * @param green
     * @param blue
     * @return
     */
    public static int getIntFromColor(int red, int green, int blue) {
        red   = (red << 16) & 0xFF0000;   //Shift red 16-bits
        green = (green << 8) & 0x00FF00;  //Shift Green 8-bits
        blue  = blue & 0x0000FF;          

        return 0x000000 | red | green | blue; //0x000000 Bitwise OR to combine the colors
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
        this.colors[0]  = getIntFromColor(66, 30, 15);
        this.colors[1]  = getIntFromColor(25, 7, 26);
        this.colors[2]  = getIntFromColor(9, 1, 47);
        this.colors[3]  = getIntFromColor(4, 4, 73);
        this.colors[4]  = getIntFromColor(0, 7, 100);
        this.colors[5]  = getIntFromColor(12, 44, 138);
        this.colors[6]  = getIntFromColor(24, 82, 177);
        this.colors[7]  = getIntFromColor(57, 125, 209);
        this.colors[8]  = getIntFromColor(134, 181, 229);
        this.colors[9]  = getIntFromColor(211, 236, 248);
        this.colors[10] = getIntFromColor(241, 233, 191);
        this.colors[11] = getIntFromColor(248, 201, 95);
        this.colors[12] = getIntFromColor(255, 170, 0);
        this.colors[13] = getIntFromColor(204, 128, 0);
        this.colors[14] = getIntFromColor(153, 87, 0);
        this.colors[15] = getIntFromColor(106, 52, 3);

        black = getIntFromColor(0, 0, 0);
    }

    
}
