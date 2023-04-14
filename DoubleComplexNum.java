public class DoubleComplexNum {
	
	double real;
	double imaginary;
	
	/**
	 * Constructor that takes in double values for the real and imaginary 
	 * parts.
	 * @param real
	 * @param imaginary
	 */
	DoubleComplexNum (double real, double imaginary) {
		this.real = real;
		this.imaginary = imaginary;
	}
	
	/**
	 * Returns new complex number that is this + i
	 * @param addend value to be added to this ComplexNum
	 * @return sum value that is this + addend
	 */
	public DoubleComplexNum add (DoubleComplexNum addend) {
		return new DoubleComplexNum(this.real+addend.real, this.imaginary+addend.imaginary);
	}
	/**
	 * Returns new complex number that is this^2 
	 * @return this^2
	 */
	public DoubleComplexNum pow2 () {
		// (a + bi)^2
		// = (a + bi) * (a + bi)
		// = a^2 + 2abi + b^2*i^2
		// = a^2-b^2 + 2abi
		// = (a^2 - b^2) + (2ab)i
		double rl = (real * real) - (imaginary * imaginary); 
		double im = 2 * real * imaginary;
		return new DoubleComplexNum(rl, im);
	}
	
	/**
	 * Returns the norm/magnitude/hypotenuse of this. That is, 
	 *  sqrt( a^2 + b^2 ) by pythagorean theorem
	 * @return magnitude of this
	 */
	public double magnitude() {
		double tmp = (real * real) + (imaginary * imaginary);
		return Math.sqrt(tmp);
	}
	
	/**
	 * Returns a string representation of the object.
	 * "[real,imaginary]"
	 * @return a string representation of the object
	 */
	public String toString() {
		return "["+Double.toString(real)+","+Double.toString(imaginary)+"]";
	}
}
