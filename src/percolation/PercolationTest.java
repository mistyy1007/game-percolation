package percolation;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class PercolationTest {
	Percolation percolation;
	@BeforeClass
	public static void SetUpBeforeClass() throws Exception{
	}
	
	@Before
	public void setUp(){
		percolation = new Percolation();
	}
	@Test
	public void groundTest(){
		int index = 0;
		for (int i = 0; i < 50; i++){
			for(int j = 0; j < 50; j++){
				if (percolation.ground(50, 0.5)[i][j] == 1){
					index ++;
				}
			}
		}
		assertTrue(index >= 1220  && index <= 1275);
		
	}
	public void seepTest(){
		int[][] Array1 = {{2, 2, 1}, {1, 0, 0}, {0, 1, 0}};
		percolation.seep(Array1, 1);
		int[][] Array2 = {{2, 2, 1}, {1, 2, 2}, {0, 1, 0}};
		assertArrayEquals(Array2, Array1);
		percolation.seep(Array1, 2);
		int[][] Array3 = {{2, 2, 1}, {1, 2, 2}, {0, 1, 2}};
		assertArrayEquals(Array3, Array1);
	}
	
	public void percolateTest(){
		int[][] Array1 = {{0, 0, 1}, {1, 0, 0}, {0, 1, 0}};
		assertTrue(percolation.percolate(Array1));
		int[][] Array2 = {{0, 1, 0}, {1, 0, 1}, {0, 0, 0}};
		assertFalse(percolation.percolate(Array2));
		
	}
}
