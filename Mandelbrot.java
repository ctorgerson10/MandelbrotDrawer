import java.io.File;
import java.io.IOException;

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
	static double STARTING_MIN_X = -2;
	static double STARTING_MAX_X =  2;
	static double STARTING_MIN_Y = -2;
	static double STARTING_MAX_Y =  2;

	// ending MIN and MAX coordinate values -- I took these values from a stackoverflow thread
	// of interesting zoom locations on the mandelbrot set.
	static double ENDING_MIN_X = -1.2576470439078538;
	static double ENDING_MIN_Y = 0.3780652779236957;
	static double ENDING_MAX_X = -1.2576470439074896;
	static double ENDING_MAX_Y = 0.3780652779240597;

	// increment values -- i.e. how fast we are zooming in
	// this scales with how many frames we want to generate
	static double MIN_X_INC = (ENDING_MIN_X - STARTING_MIN_X) / NUM_FRAMES;
	static double MIN_Y_INC = (ENDING_MIN_Y - STARTING_MIN_Y) / NUM_FRAMES;
	static double MAX_X_INC = (ENDING_MAX_X - STARTING_MAX_X) / NUM_FRAMES;
	static double MAX_Y_INC = (ENDING_MAX_Y - STARTING_MAX_Y) / NUM_FRAMES;

	/**
	 * Default setting for the max iterations the algorithm will test 
	 * before calling it inside the set or not.
	 */
	final static int MAX_ITERATIONS = 1000;
	
	/**
	 * Tests the divergence of an input complex number c in the 
	 * function f(z) = z^2 + c when iterated from z = (0 + 0i)
	 * for which the sequence f(z]0), f(f(0)), etc., does not 
	 * diverge to infinity.
	 * @param c input constant complex number
	 * @return either MAX_ITERATIONS if doesn't diverge, otherwise the point at which it passes norm(z) > 2, after which it cannot come back from.
	 */
	public static int testDivergence(DoubleComplexNum c) {
		
		return testDivergence(c, MAX_ITERATIONS);
		
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
	public static int testDivergence(DoubleComplexNum c, int iterations) {
		
		DoubleComplexNum z = new DoubleComplexNum(0,0); //first iteration always starts with z= (0 + 0i)
		
		int i = 0;
		while (z.magnitude() < 2 && i < iterations) {
			z = z.pow2();
			z = z.add(c);
			i++;
		}
		
		return i;
	}

	public static double mapToCoordinatePlane(int input, double minOutput, double maxOutput) {
		return (input * (maxOutput - minOutput)) / WIDTH + minOutput;
	}

	/**
	 * Calculate mandelbrot set and set image pixels for output
	 */
	public static void drawFrames() {
		int frameCount = 0;
		while (STARTING_MIN_X < ENDING_MIN_X ||
			   STARTING_MIN_Y < ENDING_MIN_Y ||
			   STARTING_MAX_X > ENDING_MAX_X ||
			   STARTING_MAX_Y > ENDING_MAX_Y ){
				
				for (int x = 0; x < WIDTH; x++) { // rows
					for (int y = 0; y < HEIGHT; y++) { // columns
						// map pixel x/y values to values inside [-2, 2]
						double real = mapToCoordinatePlane(y, STARTING_MIN_X, STARTING_MAX_X);
						double imaginary = mapToCoordinatePlane(x, STARTING_MIN_Y, STARTING_MAX_Y);
						DoubleComplexNum c = new DoubleComplexNum(real, imaginary);
						int n = testDivergence(c);
						int color = fd.getColor(n, MAX_ITERATIONS);
						fd.pixelColors[y + x * WIDTH] = color;
					}
				}

				// map color values to image pixels
				fd.image.setRGB(0, 0, WIDTH, HEIGHT, fd.pixelColors, 0, WIDTH);

				// save image file
				String fileName = "images/frame" + String.format("%03d", (frameCount+1)) + ".png";
				File f = new File(fileName);
				try {
					ImageIO.write(fd.image, "png", f);
				} catch (IOException e) {
					e.printStackTrace();
				}
				System.out.println("Saved frame " + (frameCount+1) + " as " + fileName);

				// increment bounds
				STARTING_MIN_X = STARTING_MIN_X + MIN_X_INC;
				STARTING_MIN_Y = STARTING_MIN_Y + MIN_Y_INC;
				STARTING_MAX_X = STARTING_MAX_X + MAX_X_INC;
				STARTING_MAX_Y = STARTING_MAX_Y + MAX_Y_INC;

				// update the increment values
				MIN_X_INC *= 0.9;
				MIN_Y_INC *= 0.9;
				MAX_X_INC *= 0.9;
				MAX_Y_INC *= 0.9;

				frameCount++;
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
				DoubleComplexNum c = new DoubleComplexNum(x, y);
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

// some zoom coordinates
/*
 -1.2576470439078538 0.3780652779236957, -1.2576470439074896 0.3780652779240597
 -0.6002735730728121 -0.6646192892692977 -0.6002735278513613 -0.6646192440478469
 0.27448947852666156 -0.006315217712621591 0.2744894785266986 -0.006315217712584573
 0.36989570933793936 0.6714366753122559, 0.36989570933811894 0.6714366753124356
 -1.469375574129762 -0.011646337485088934, -1.4693755741062213 -0.01164633746154828
 -0.7730988756640723 -0.126912260064924, -0.7730988756639694 -0.12691226006482104
*/
