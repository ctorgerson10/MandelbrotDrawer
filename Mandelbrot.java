import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;

import javax.imageio.ImageIO;

/**
 * Implementation of the mandelbrot set using the ComplexNum class.
 * @author Ethan Tenney
 */
public class Mandelbrot {

	// width and height of the window
	static final int WIDTH = 1000;
	static final int HEIGHT = 1000;
	static final int NUM_FRAMES = 10;

	// output image variables
	static FractalDrawer fd = new FractalDrawer(WIDTH, HEIGHT, NUM_FRAMES);

	// starting MIN and MAX coordinate values -- [-2, 2] encapsulates the full mandelbrot set
	static BigDecimal STARTING_MIN_X = new BigDecimal(-2);
	static BigDecimal STARTING_MAX_X = new BigDecimal(2);
	static BigDecimal STARTING_MIN_Y = new BigDecimal(-2);
	static BigDecimal STARTING_MAX_Y = new BigDecimal(2);

	// ending MIN and MAX coordinate values -- I took these values from a stackoverflow thread
	// of interesting zoom locations on the mandelbrot set.
	static BigDecimal ENDING_MIN_X = new BigDecimal(-1.2576470439078538);
	static BigDecimal ENDING_MIN_Y = new BigDecimal(0.3780652779236957);
	static BigDecimal ENDING_MAX_X = new BigDecimal(-1.2576470439074896);
	static BigDecimal ENDING_MAX_Y = new BigDecimal(0.3780652779240597);

	// increment values -- i.e. how fast we are zooming in
	// this scales with how many frames we want to generate
	static BigDecimal MIN_X_INC = ENDING_MIN_X.subtract(STARTING_MIN_X).divide(new BigDecimal(NUM_FRAMES));
	static BigDecimal MIN_Y_INC = ENDING_MIN_Y.subtract(STARTING_MIN_Y).divide(new BigDecimal(NUM_FRAMES));
	static BigDecimal MAX_X_INC = ENDING_MAX_X.subtract(STARTING_MAX_X).divide(new BigDecimal(NUM_FRAMES));
	static BigDecimal MAX_Y_INC = ENDING_MAX_Y.subtract(STARTING_MAX_Y).divide(new BigDecimal(NUM_FRAMES));

	/**
	 * Default setting for the max iterations the algorithm will test 
	 * before calling it inside the set or not.
	 */
	final static int MAX_ITERATIONS = 1000;
	final static int THRESHOLD = 10;
	
	/**
	 * Tests the divergence of an input complex number c in the 
	 * function f(z) = z^2 + c when iterated from z = (0 + 0i)
	 * for which the sequence f(z]0), f(f(0)), etc., does not 
	 * diverge to infinity.
	 * @param c input constant complex number
	 * @return either MAX_ITERATIONS if doesn't diverge, otherwise the point at which it passes norm(z) > 2, after which it cannot come back from.
	 */
	public static int testDivergence(ComplexNum c) {
		
		return testDivergence (c, MAX_ITERATIONS);
		
	}
	
	/**
	 * Tests the divergence, specifying the number of iterations 
	 * it should try before giving up.
	 * @param c input constant complex number
	 * @param iterations amount of iterations it should try before 
	 * giving up.
	 * @return either MAX_ITERATIONS if doesn't diverge, otherwise 
	 * the point at which it passes norm(z) > 2, after which it 
	 * cannot come back from.
	 */
	public static int testDivergence(ComplexNum c, int iterations) {
		
		ComplexNum z = new ComplexNum(0,0); //first iteration always starts with z= (0 + 0i)
		
		int i = 0;
		while (z.magnitude().compareTo(new BigDecimal("2")) < 0 && i < iterations) {
			z = z.pow2();
			z = z.add(c);
			i++;
		}
		
		return i;
	}

	public static BigDecimal mapToCoordinatePlane(int input, BigDecimal minOutput, BigDecimal maxOutput) {

		BigDecimal i = new BigDecimal(input);
		BigDecimal width = new BigDecimal(WIDTH);

		return i.multiply(maxOutput.subtract(minOutput)).divide(width.add(minOutput), ComplexNum.DEFAULT_ROUNDING);
	}

	/**
	 * Calculate mandelbrot set and set image pixels for output
	 */
	public static void drawFrames() {
		int frame_iter = 0;
		while (
			STARTING_MIN_X.compareTo(ENDING_MIN_X) == -1 ||
			STARTING_MIN_Y.compareTo(ENDING_MIN_Y) == -1 ||
			STARTING_MAX_X.compareTo(ENDING_MAX_X) == 1  ||
			STARTING_MAX_Y.compareTo(ENDING_MAX_Y) == 1  ){
				
				for (int x = 0; x < WIDTH; x++) {
					for (int y = 0; y < HEIGHT; y++) {
						// map pixel x/y values to values inside [-2, 2]
						BigDecimal real = mapToCoordinatePlane(x, STARTING_MIN_X, STARTING_MAX_X);
						BigDecimal imaginary = mapToCoordinatePlane(y, STARTING_MIN_Y, STARTING_MAX_Y);
						ComplexNum c = new ComplexNum(real, imaginary);
						int n = testDivergence(c);
						int color = fd.getColor(n, MAX_ITERATIONS);
						fd.pixelColors[y + x * WIDTH] = color;
					}
				}

				// map color values to image pixels
				fd.image.setRGB(0, 0, WIDTH, HEIGHT, fd.pixelColors, 0, WIDTH);

				// save image file
				String fileName = "images/frame" + frame_iter + ".png";
				File f = new File(fileName);
				try {
					ImageIO.write(fd.image, "png", f);
				} catch (IOException e) {
					e.printStackTrace();
				}
				System.out.println("Saved frame " + frame_iter + " as " + fileName);

				// increment bounds
				STARTING_MIN_X = STARTING_MIN_X.add(MIN_X_INC);
				STARTING_MIN_Y = STARTING_MIN_Y.add(MIN_Y_INC);
				STARTING_MAX_X = STARTING_MAX_X.add(MAX_X_INC);
				STARTING_MAX_Y = STARTING_MAX_Y.add(MAX_Y_INC);

				frame_iter++;
			}
	}

	/**
	 * Prints to console are rough approximation of the Mandelbrot set 
	 * using "*" for being in the set, and " " for being outside it.
	 * @param args
	 */
	public static void main(String[] args) {

		// I kept this code because I think it's cute
		for (double y = 1; y >= -1 ; y-=0.05) {
			for (double x = -2; x <= 0.5; x+=0.025) {
				ComplexNum c = new ComplexNum(x, y);
				int n = testDivergence(c);
				// int color = fd.getColor(n, MAX_ITERATIONS);
				// pixelColors[y + x * WIDTH] = color;
				if(n == MAX_ITERATIONS) {
					System.out.print("*");
				} else {
					System.out.print(" ");
				}
			}
			System.out.println();
		}

		// the real deal
		drawFrames();
	}

}

/*
Sample Output:
                                                                                *                    
                                                                                                     
                                                                                                     
                                                                           **                        
                                                                        ********                     
                                                                       ********                      
                                                                       ********                      
                                                                    * *  * * ***                     
                                                           **     ********************               
                                                          ******************************* ***        
                                                           **********************************        
                                                      *  ***********************************         
                                                        **************************************       
                                                    *********************************************    
                                                     ******************************************      
                                  **  ******       *********************************************     
                                  *************     ********************************************     
                                *****************  *********************************************     
                               ******************* ********************************************      
                          **** ******************* *******************************************       
        **** ******************************************************************************          
                          **** ******************* *******************************************       
                               ******************* ********************************************      
                                *****************  *********************************************     
                                  *************     ********************************************     
                                  **  ******       *********************************************     
                                                     ******************************************      
                                                    *********************************************    
                                                        **************************************       
                                                      *  ***********************************         
                                                           **********************************        
                                                          ******************************* ***        
                                                           **     ********************               
                                                                    * *  * * ***                     
                                                                       ********                      
                                                                       ********                      
                                                                        ********                     
                                                                           **                        
                                                                                                     
                                                                                                     


 */
