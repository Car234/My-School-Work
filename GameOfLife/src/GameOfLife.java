import java.util.*;
import java.util.concurrent.*;

class SquareGrid {
	public int[][] squareGrid = new int[20][20];
	
	SquareGrid() {
		for(int i = 0; i < 20; i++) {
			for(int j = 0; j < 20; j++) {
				if (i == 0 || i == 19) {
					squareGrid[i][j] = 0;
					continue;
				}
				else if (j == 0 || j == 19) {
					squareGrid[i][j] = 0; 
					continue;
				}
				else {
					squareGrid[i][j] = 1;
					continue;
				}
			}
		}
	}
	
	void displayGrid() {
		for(int i = 0; i < 20; i++) {
			for(int j = 0; j < 20; j++) {
				if(j == 19) {
					if (squareGrid[i][j] == 0)
						System.out.println(" ");
					else
						System.out.println("X");
				}
				else {
					if (squareGrid[i][j] == 0)
						System.out.print(" ");
					else
						System.out.print("X");
				}
			}
		}
	}
}

class SquareThread extends Thread {
	private int first;
	private int second; 
	private SquareGrid grid;
	private boolean flip = false;
	private int liveNeighbors = 0; 
	public static final Object pauseLock = new Object();
	
	SquareThread(int first, int second, SquareGrid grid) {
		this.first = first;
		this.second = second;
		this.grid = grid;
	}
	
	public void run() {
		while (true) {
			synchronized (pauseLock) {
				liveNeighbors = 0;
				if ((first == 0) && (second == 0)) {
					if (grid.squareGrid[first][second + 1] == 1)
						liveNeighbors++;
					if (grid.squareGrid[first + 1][second + 1] == 1)
						liveNeighbors++;
					if (grid.squareGrid[first + 1][second] == 1)
						liveNeighbors++;
				}
				else if ((first == 0) && (second == 19)) {
					if (grid.squareGrid[first][second - 1] == 1)
						liveNeighbors++;
					if (grid.squareGrid[first + 1][second - 1] == 1)
						liveNeighbors++;
					if (grid.squareGrid[first + 1][second] == 1)
						liveNeighbors++;	
				}
				else if ((first == 19) && (second == 0)) {
					if (grid.squareGrid[first - 1][second] == 1)
						liveNeighbors++;
					if (grid.squareGrid[first - 1][second + 1] == 1)
						liveNeighbors++;
					if (grid.squareGrid[first][second + 1] == 1)
						liveNeighbors++;
				}
				else if ((first == 19) && (second == 19)) {
					if (grid.squareGrid[first - 1][second] == 1)
						liveNeighbors++;
					if (grid.squareGrid[first - 1][second - 1] == 1)
						liveNeighbors++;
					if (grid.squareGrid[first][second - 1] == 1)
						liveNeighbors++;
				}
				else if (first == 0) {
					if (grid.squareGrid[first][second - 1] == 1)
						liveNeighbors++;
					if (grid.squareGrid[first + 1][second - 1] == 1)
						liveNeighbors++;
					if (grid.squareGrid[first + 1][second] == 1)
						liveNeighbors++;
					if (grid.squareGrid[first + 1][second + 1] == 1)
						liveNeighbors++;
					if (grid.squareGrid[first][second + 1] == 1)
						liveNeighbors++;
				}
				else if (first == 19) {
					if (grid.squareGrid[first][second - 1] == 1)
						liveNeighbors++;
					if (grid.squareGrid[first - 1][second - 1] == 1)
						liveNeighbors++;
					if (grid.squareGrid[first - 1][second] == 1)
						liveNeighbors++;
					if (grid.squareGrid[first - 1][second + 1] == 1)
						liveNeighbors++;
					if (grid.squareGrid[first][second + 1] == 1)
						liveNeighbors++;
				}
				else if (second == 0) {
					if (grid.squareGrid[first - 1][second] == 1)
						liveNeighbors++;
					if (grid.squareGrid[first - 1][second + 1] == 1)
						liveNeighbors++;
					if (grid.squareGrid[first][second + 1] == 1)
						liveNeighbors++;
					if (grid.squareGrid[first + 1][second + 1] == 1)
						liveNeighbors++;
					if (grid.squareGrid[first + 1][second] == 1)
						liveNeighbors++;
				}
				else if (second == 19) {
					if (grid.squareGrid[first - 1][second] == 1)
						liveNeighbors++;
					if (grid.squareGrid[first - 1][second - 1] == 1)
						liveNeighbors++;
					if (grid.squareGrid[first][second - 1] == 1)
						liveNeighbors++;
					if (grid.squareGrid[first + 1][second - 1] == 1)
						liveNeighbors++;
					if (grid.squareGrid[first + 1][second] == 1)
						liveNeighbors++;
				}
				else {
					if (grid.squareGrid[first - 1][second] == 1)
						liveNeighbors++;
					if (grid.squareGrid[first - 1][second - 1] == 1)
						liveNeighbors++;
					if (grid.squareGrid[first][second - 1] == 1)
						liveNeighbors++;
					if (grid.squareGrid[first + 1][second - 1] == 1)
						liveNeighbors++;
					if (grid.squareGrid[first + 1][second] == 1)
						liveNeighbors++;
					if (grid.squareGrid[first + 1][second + 1] == 1)
						liveNeighbors++;
					if (grid.squareGrid[first][second + 1] == 1)
						liveNeighbors++;
					if (grid.squareGrid[first - 1][second + 1] == 1)
						liveNeighbors++;
				}
				
				if (grid.squareGrid[first][second] == 0) {
					if (liveNeighbors == 3)
						this.flip = true; 
				}
				else {
					if (liveNeighbors < 2 || liveNeighbors > 3)
						this.flip = true;
				}
				
				
				try {
					pauseLock.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	public void updateGrid() {
		if(flip == true) {
			grid.squareGrid[first][second] ^= 1;
			flip = false;
		}		
	}
	
	public static void startAgain() {
		synchronized (pauseLock) {
			pauseLock.notifyAll();
		}
	}
}

public class GameOfLife {

	@SuppressWarnings("deprecation")
	public static void main(String[] args) throws InterruptedException {
		SquareGrid grid = new SquareGrid();
		ArrayList<SquareThread> threads = new ArrayList<>();
		Scanner sc = new Scanner(System.in);
		int steps;
		
		for(int i = 0; i < 20; i++) {
			for (int j = 0; j < 20; j++) {
			threads.add(new SquareThread(i, j, grid));
			}	
		}
		System.out.println("How many steps would you like?");
		steps = sc.nextInt();
		
		System.out.println("INITIAL GRID!");
		grid.displayGrid();
		
		for (int i = 0; i < steps; i++) {
			if (i == 0) {
				for (SquareThread t : threads) t.start();	
				for (SquareThread t : threads) t.updateGrid();
			}
			else {
				SquareThread.startAgain();
				TimeUnit.MILLISECONDS.sleep(10);
				for (SquareThread t : threads) t.updateGrid();
			}
			
		}
		
		for (SquareThread t : threads) t.stop();
		System.out.println("\nGRID AFTER " + steps + " STEP(S)!");
		grid.displayGrid();
		sc.close();
	}

}
