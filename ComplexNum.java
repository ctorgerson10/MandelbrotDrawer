import java.math.BigDecimal;
import java.math.MathContext;

/**
 * Complex number class implementing the Java BigDecimal Class for precision. 
 * Consists of a Real value and an Imaginary value, eg/ (a + bi) on the complex plane.
 * @author Ethan Tenney
 */
public class ComplexNum {
	
	BigDecimal real;
	BigDecimal imaginary;
	
	/**
	 * Value used for default rounding of the ComplexNum when being constructed. 
	 * Uses Java MathContext to pass to BigDecimal.round
	 * TODO: Scale this value with the screen size in order to minimize latency
	 */
	final static MathContext DEFAULT_ROUNDING = new MathContext(10);
	
	/**
	 * Constructor that takes in BigDecimal values for the real and imaginary 
	 * parts. Uses DEFAULT_ROUNDING for rounding of values for latency.
	 * @param real
	 * @param imaginary
	 */
	ComplexNum (BigDecimal real, BigDecimal imaginary) {
		this.real = real.round(DEFAULT_ROUNDING);
		this.imaginary = imaginary.round(DEFAULT_ROUNDING);
	}
	
	/**
	 * Constructor that specifies the how much the decimals should be rounded 
	 * using Java MathContext.
	 * @param real value of the Complex Number
	 * @param imaginary value of the complex number
	 * @param roundFactor MathContext specifying precision for rounding 
	 */
	ComplexNum (BigDecimal real, BigDecimal imaginary, MathContext roundFactor) {
		this.real = real.round(roundFactor);
		this.imaginary = imaginary.round(roundFactor);
	}
	/**
	 * Constructor for ComplexNum taking in Strings
	 * @param r real part of the complex number
	 * @param i imaginary part of the complex number
	 */
	ComplexNum(String r, String i) {
		real = new BigDecimal(r);
		imaginary = new BigDecimal(i);
	}
	
	/**
	 * Constructor for ComplexNum taking in doubles 
	 * @param r real part of the complex number
	 * @param i imaginary part of the complex number
	 */
	ComplexNum(double r, double i) {
		real = new BigDecimal(r+"");
		imaginary = new BigDecimal(i+"");
	}
	
	/**
	 * Returns new complex number that is this + i
	 * @param addend value to be added to this ComplexNum
	 * @return sum value that is this + addend
	 */
	public ComplexNum add (ComplexNum addend) {
		return new ComplexNum(real.add(addend.real), imaginary.add(addend.imaginary));
	}
	/**
	 * Returns new complex number that is this^2 
	 * @return this^2
	 */
	public ComplexNum pow2 () {
		// (a + bi)^2
		// = (a + bi) * (a + bi)
		// = a^2 + 2abi + b^2*i^2
		// = a^2-b^2 + 2abi
		// = (a^2 - b^2) + (2ab)i 
		BigDecimal rl = real.pow(2).subtract(imaginary.pow(2)); 
		BigDecimal im = real.multiply(imaginary).multiply(new BigDecimal("2"));
		return new ComplexNum(rl, im);
	}
	
	/**
	 * Returns the norm/magnitude/hypotenuse of this. That is, 
	 *  sqrt( a^2 + b^2 ) by pythagorean theorem
	 * @return magnitude of this
	 */
	public BigDecimal magnitude() {
		BigDecimal temp = real.pow(2, DEFAULT_ROUNDING).add(imaginary.pow(2, DEFAULT_ROUNDING));
		return temp.sqrt(DEFAULT_ROUNDING);
		
	}
	
	/**
	 * Returns a string representation of the object. That is, 
	 * "[real,imaginary]"
	 * @return a string representation of the object
	 */
	public String toString() {
		return "["+real.toString()+","+imaginary.toString()+"]";
	}
}
