import java.math.BigDecimal;
import java.math.MathContext;

/**
 * 
 */

/**
 * @author ethan
 *
 */
public class ComplexNum {
	
	BigDecimal real;
	BigDecimal imaginary;
	
	final static MathContext DEFAULT_ROUNDING = new MathContext(10);
	
	ComplexNum (BigDecimal real, BigDecimal imaginary) {
		this.real = real.round(DEFAULT_ROUNDING);
		this.imaginary = imaginary.round(DEFAULT_ROUNDING);
	}
	
	ComplexNum(String r, String i) {
		real = new BigDecimal(r);
		imaginary = new BigDecimal(i);
	}
	
	ComplexNum(double r, double i) {
		real = new BigDecimal(r+"");
		imaginary = new BigDecimal(i+"");
	}
	
	public ComplexNum add (ComplexNum num2) {
		return new ComplexNum(real.add(num2.real), imaginary.add(num2.imaginary));
	}
	public ComplexNum pow (Integer exp) {
		// (a + bi) * (a + bi)
		// 			real		im
		// real 	a^2			a*bi
		// im   	abi			b^2*i^2
		// 
		// = (a^2 - b^2) + (2ab)i 
		BigDecimal rl = real.pow(2).subtract(imaginary.pow(2)); 
		BigDecimal im = real.multiply(imaginary).multiply(new BigDecimal("2"));
		return new ComplexNum(rl, im);
	}
	
	public BigDecimal magnitude() {
		BigDecimal temp = real.pow(2, DEFAULT_ROUNDING).add(imaginary.pow(2, DEFAULT_ROUNDING));
		return temp.sqrt(DEFAULT_ROUNDING);
		
	}
	
	public String toString() {
		return "["+real.toString()+","+imaginary.toString()+"]";
	}
}
