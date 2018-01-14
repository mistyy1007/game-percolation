/**
 * Construct an NxN grid, that is, a square array. Populate the grid randomly with "sand" grains, 
 * such that each location has a sand grain with probability p, or is empty with probability (1-p). 
 *
 * You should import java.util.Random to help do this.
 * "Water" arrives at the top of this array, 
 * so that every empty location in the first row becomes filled with water.
 * 
 * Water will only flow down or horizontally, never up. 
 * For each succeeding row, if an empty space has water directly above it, 
 * or horizontally adjacent to it, it also fills with water. At each move, water can flow any distance horizontally.
 * 
 * If the ground is packed tightly with sand, water will not seep very far down into it. 
 * If the ground is very loosely packed, water will almost certainly seep all the way down. 
 * 
 * For an NxN container, there is some packing probability p such that water has exactly a 
 * 50% probability of seeping all the way to the bottom row. Your job is to find that probability.
 * 
 * @author Rui
 *
 */

package percolation;

import java.util.Random;

public class Percolation {
	
	public static void main(String args[]) {    
		Percolation percolation = new Percolation();
		percolation.run();
    }
	
	void run(){
		double p1 = findProbability(50);
		double p2 = findProbability(100);
		double p3 = findProbability(200);
		System.out.println("50X50 grid: the possibilit is " + String.format("%.2f", p1));
		System.out.println("100X100 grid: the possibilit is " + String.format("%.2f", p2));
		System.out.println("200X200 grid: the possibilit is " + String.format("%.2f", p3));
	}
	
    /**
     * ground(n, p) returns an array of n arrays of integer,
     * where each array is of length n,
     * and each integer has probability p of being a sand grain, (1-p) of being empty (and dry).
     * Use the encoding 0 = empty space, 1 = sand grain, 2 = water.
     * @param n
     * @param p
     * @return
     */
	int[][] ground(int n, double p){
		int grid[][] = new int[n][n];
		for (int i = 0; i < n; i++){
			for(int j = 0; j < n; j++){
				if (new Random().nextDouble() <= p){
					grid[i][j] = 1;
				} else {
					grid[i][j] = 0;
				}
			}
		}
		return grid;
	}
	
	/**
     * seep(array, row) causes water to flow from row into row+1,
     * modifying the array. In other words, this function performs one step of the simulation.
     * @param ground
     * @param row
     */
	void seep(int[][] ground, int row){
		for (int i = 0; i < ground[row].length; i++){
			if (ground[row - 1][i] == 2 && ground[row][i] ==0){
					ground[row][i] = 2;	
			}
		}
		for (int k = ground[row].length - 1; k >0; k--){
			if (ground[row][k] == 2 && ground[row][k-1] == 0){
				ground[row][k-1] = 2;
			}
		}
		for (int j = 0; j < ground[row].length - 1; j++){
			if (ground[row][j] == 2 && ground[row][j+1] == 0){
				ground[row][j+1] = 2;
			}
		}
	}
	
    /**
     * Returns true if, after water has "seeped" as far as it can, 
     * water has reached the bottom row, and false otherwise. 
     * For the example above, the result would be true.
     * @param ground
     * @return
     */
	
	boolean percolate(int[][] ground){
		for (int i = 0; i < ground.length; i++){
			if (ground[0][i] != 1){
				ground[0][i] = 2;
			}
		}
		for (int j = 1; j < ground.length; j++){		
			seep(ground,j);
		}
		for (int k = 0; k < ground.length; k++){
			if (ground[ground.length - 1][k] == 2){
				return true;
			}
		}
		return false;
	}
	
    /**
     * For an n by n array, 
     * determines the packing probability p that causes the array to have a 
     * 50% probability of water seeping all the way to the bottom.
     * @param n
     * @return
     */
	
	double findProbability(int n){
		double p = 0.5;
		double delta = 0.05;
		int index = 0;
		while (index != 50){
			if ((index > 50) || p < 0){
				p = p + delta;
			}
			if ((index < 50) || p > 1){
				p = p - delta;
			}
			index = 0;
			for (int i = 0; i < 100; i++){
				if (percolate(ground(n,p))){
					index ++;
				}			
			}
			delta -= 0.005;
			if (delta < 0){
				delta = -delta;
			}
		}	
		return p;
	}
}
