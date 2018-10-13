/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package box;

/**
 *
 * @author mitz
 */
public class MatchBox extends Box {
	double inpound;

	/**
	 *
	 * @param w is weight
	 * @param h is height
	 * @param d is depth
	 */
	public MatchBox(double w, double h, double d) {
		super(w, h, d);
		this.inpound = 0.03611;
	}
	
	/**
	 *
	 * @return multiply 3 sides, and exchange it cubic inch by inpound
	 */
	public double calculateWeight() {
		return width * height * depth * inpound;
	}
	
	/**
	 * Print 3 sides, weight and volume
	 */
	@Override
	public void getVolume() {
		System.out.println("width of MatchBox is: " + width);
		System.out.println("height of MatchBox is: " + height);
		System.out.println("depth of MatchBox is: " + depth);
		System.out.println("weight of MatchBox is: " + calculateWeight());
		super.getVolume();
	}
}
