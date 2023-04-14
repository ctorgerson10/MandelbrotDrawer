
public class Complex {

	double real;
	double imaginary;
	final static int MAX_ITERATIONS = 100000;
	
	Complex (double real, double imaginary) {
		this.real = real;
		this.imaginary = imaginary;
	}
	
	public Complex add (Complex addend) {
		return new Complex(real + addend.real, imaginary + addend.imaginary);
	}
	
	public Complex pow2 () {
		// (a + bi)^2
		// = (a + bi) * (a + bi)
		// = a^2 + 2abi + b^2*i^2
		// = a^2-b^2 + 2abi
		// = (a^2 - b^2) + (2ab)i 
		double rl = Math.pow(real, 2) - Math.pow(imaginary, 2);
		double im = real * imaginary * 2;
		return new Complex(rl, im);
	}
	
	public double magnitude() {
		return Math.sqrt(Math.pow(real, 2) + Math.pow(imaginary, 2));
	}
	
	public static int testDivergence(Complex c, int iterations) {
		
		Complex z = new Complex(0,0); //first iteration always starts with z= (0 + 0i)
		
		int i = 0;
		while (z.magnitude() < 2 && i < iterations) {
			z = z.pow2();
			z = z.add(c);
			i++;
		}
		
		return i;
		
	}
	
	
	public static int optimizedTest(Complex c, int iterations) {
		Complex z = new Complex(0,0);
		double real2 = 0; //standin for real^2 
		double im2 = 0;
		int i = 0;
		// divergence test using 3 multiplications per iterations instead of 5
		// courtesy of wikipedia
		while (real2 + im2 <= 4 && i < MAX_ITERATIONS) {
			z.imaginary = (z.real+z.real)*z.imaginary + c.imaginary;
			z.real = real2 - im2 + c.real;
			real2 = z.real*z.real;
			im2 = z.imaginary*z.imaginary;
			i++;
		}
		return i;
	}
	
	public static void main(String[] args) {
		
		for (double y = 1; y >= -1 ; y-=0.05) {
			for (double x = -2; x <= 0.5; x+=0.025) {
				Complex c = new Complex(x, y);
				if(optimizedTest(c, MAX_ITERATIONS) == MAX_ITERATIONS) {
					System.out.print("*");
				} else {
					System.out.print(" ");
				}
			}
			System.out.println();
		}

	}

}
