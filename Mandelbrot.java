import java.math.BigDecimal;

/**
 * Implementation of the mandelbrot set using the ComplexNum class.
 * @author Ethan Tenney
 */
public class Mandelbrot {
	
	/**
	 * Default setting for the max iterations the algorithm will test 
	 * before calling it inside the set or not.
	 * TODO: Have this scale with screen size for accurate rendering.
	 */
	final static int MAX_ITERATIONS = 80;
	
	/**
	 * Tests the divergence of an input complex number c in the 
	 * function f(z) = z^2 + c when iterated from z = (0 + 0i)
	 * for which the sequence f(z]0), f(f(0)), etc., does not 
	 * diverge to infinity.
	 * @param c input constant complex number
	 * @return either MAX_ITERATIONS if doesn't diverge, otherwise 
	 * the point at which it passes norm(z) > 2, after which it 
	 * cannot come back from.
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
	
	/**
	 * Prints to console are rough approximation of the Mandelbrot set 
	 * using "*" for being in the set, and " " for being outside it.
	 * @param args
	 */
	public static void main(String[] args) {
		
		for (double y = 1; y >= -1 ; y-=0.05) {
			for (double x = -2; x <= 0.5; x+=0.025) {
				ComplexNum c = new ComplexNum(x, y);
				if(testDivergence(c) == MAX_ITERATIONS) {
					System.out.print("*");
				} else {
					System.out.print(" ");
				}
			}
			System.out.println();
		}
		
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
