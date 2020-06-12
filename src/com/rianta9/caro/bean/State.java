package com.rianta9.caro.bean;
import java.util.ArrayList;
import com.rianta9.caro.values.Value;



/**
 * Trạng thái trò chơi
 * @author rianta9
 * Datecreate: 12 thg 5, 2020 18:20:17
 */
public class State {
	private int[][] cell; // ma trận trạng thái
	private ArrayList<Cell> moveSteps; // danh sách các bước đã đi
	private int steps; // số ô đã đánh
	
	public State(){
		this.steps = 0;
		this.moveSteps = new ArrayList<Cell>();
		this.cell = new int[Value.SIZE][Value.SIZE];
		for (int i=0; i<Value.SIZE; i++) {
			for(int j=0; j<Value.SIZE; j++) cell[i][j] = 0;
		}
	}
	
	public State(int[][] cell){
		this.steps = 0;
		this.moveSteps = new ArrayList<Cell>();
		this.cell = new int[Value.SIZE][Value.SIZE];
		for (int i=0; i<Value.SIZE; i++) {
			for(int j=0; j<Value.SIZE; j++) {
				this.cell[i][j] = cell[i][j];
				if(cell[i][j] != 0) {
					this.moveSteps.add(new Cell(i, j, cell[i][j]));
					this.steps++;
				}
			}
		}
		
	}
	
	/**
	 * Cập nhật nước đi mới
	 * @param x
	 * @param y
	 * @param player
	 */
	public void update(int x, int y, int player) {
		this.cell[x][y] = player;
		this.moveSteps.add(new Cell(x, y, player));
		this.steps++;
	}
	
	public int[][] getState(){
		return this.cell;
	}
	
	public void setState(int[][] cell) {
		for (int i=0; i<Value.SIZE; i++) {
			for(int j=0; j<Value.SIZE; j++) this.cell[i][j] = cell[i][j];
		}
	}
	
	public void printState() {
		for (int i=0; i<Value.SIZE; i++) {
			for(int j=0; j<Value.SIZE; j++) {
				if(cell[i][j] == Value.AI_VALUE) System.out.print("- ");
				else if(cell[i][j] == Value.USER_VALUE) System.out.print("+ ");
				else System.out.print(cell[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	
	/**
	 * Kiểm tra người thắng cuộc
	 * @return true: nếu player thắng, false nếu ngược lại
	 */
	public boolean checkWinner(int player) {
		int[] lineX = {1, 1, 0, 1};  // |các đường cần kiểm tra(ngang, dọc, chéo xuống trái, chéo xuống phải)
		int[] lineY = {0, 1, 1, -1}; // |để tìm người thắng
		for (int x = 0; x < Value.SIZE; x++) {
			for (int y = 0; y < Value.SIZE; y++) {
				if(cell[x][y] == player) { // Nếu ô này đã được player chọn => kiểm tra			
					for (int i = 0; i < 4; i++) { // kiểm tra 4 đường
						int count = 1; // count = 5 => player chiến thắng
						for(int j = 1; j <= 4; j++) { // kiểm tra 4 ô tiếp theo
							int vtx = x + lineX[i]*j; // vị trí x của ô tiếp theo cần check
							int vty = y + lineY[i]*j; // vị trí y của ô tiếp theo cần check
							// vtx hoặc vty < 0 hoặc > Value.SIZE, hoặc ô này != ô đầu => khỏi ktra
							if(vtx < 0 || vty < 0 || vtx >= Value.SIZE || vty >= Value.SIZE) break;
							if(cell[vtx][vty] == player) count++;
							else break;
						}
						if(count == 5) return true; // Player thắng
					}
				}
			}
		}
		return false; // Không ai thắng cả
	}

	/**
	 * Kiểm tra một ô đã có ai đánh chưa
	 * @param x
	 * @param y
	 * @return
	 */
	public boolean isClickable(int x, int y) {
		if(x >= 0 && x < Value.SIZE && y >= 0 && y < Value.SIZE)
			if(cell[x][y] == 0) return true;
		return false;
	}
	
	/**
	 * Hết ô để đánh
	 * @return true: hết, false: chưa hết
	 */
	public boolean isOver() {
		if(this.steps == Value.SIZE*Value.SIZE) return true;
		else return false;
	}
}
