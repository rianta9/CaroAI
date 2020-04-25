/**
 * 
 */
package com.rianta9.caro.bean;

import java.util.Random;

import com.rianta9.caro.values.Value;

/**
 * @author rianta9
 * Datecreate: 30 thg 3, 2020 17:32:47
 */
public class CaroAI {
	private int[][] cell;
	private int size; // số hàng/cột
	private int mode; // chế độ chơi
	private Random rand;
	public static int USER_VALUE = 1;
	public static int AI_VALUE = -1;
	private int[] lineX = {1, 1, 0, 1};  // |các đường cần kiểm tra(ngang, dọc, chéo xuống trái, chéo xuống phải)
	private int[] lineY = {0, 1, 1, -1}; // |để tìm người thắng
	private int nextX; //| tọa độ mà AI lựa chọn
	private int nextY; //|
	
	
	public CaroAI() {
		this.size = Value.TABLE_SIZE; // 19
		this.mode = Value.DEFAULT_MODE; // 0
		rand = new Random();
		cell = new int[size][size];
		
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				cell[i][j] = 0;
			}
		}
	}
	
	public CaroAI(int size, int mode) {
		this.size = size;
		this.mode = mode;
		
		rand = new Random();
		cell = new int[size][size];
		
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				cell[i][j] = 0;
			}
		}
		if(mode == 1) { // AI chơi trước
			nextX = rand.nextInt(size); // lưu lại tọa độ X
			nextY = rand.nextInt(size); // lưu lại tọa độ Y
			update(nextX, nextY, AI_VALUE); // chọn 1 ô để đi
		}
	}
	
	/**
	 * Kiểm tra người chơi có thể chọn ô này hay không
	 * @param x vị trí hàng
	 * @param y vị trí cột
	 * @return true: nếu cell[x][y] = 0, false: ngược lại
	 */
	public boolean clickable(int x, int y) {
		if(cell[x][y] == 0) return true;
		return false;
	}
	
	/**
	 * Cập nhật ma trận
	 * @param x vị trí hàng
	 * @param y vị trí cột
	 * @param value USER_VALUE: 1, AI_VALUE: -1
	 */
	public void update(int x, int y, int value) {
		cell[x][y] = value;
	}
	
	/**
	 * Kiểm tra người thắng cuộc
	 * @return 0: nếu không ai thắng, 1: nếu user thắng, -1: nếu AI thắng
	 */
	public int checkWinner() {
		for (int x = 0; x < size; x++) {
			for (int y = 0; y < size; y++) {
				if(cell[x][y] != 0) { // Nếu ô này đã được chọn => kiểm tra			
					for (int i = 0; i < 4; i++) { // kiểm tra 4 đường để tìm người thắng
						int cells = cell[x][y]; // cells = 1 hoặc -1, cells bằng +/-5 => có người thắng
						// để thắng cần có 5 ô thẳng hàng
						for(int j = 1; j <= 4; j++) { // kiểm tra 4 ô tiếp theo
							int vtx = x + lineX[i]*j; // vị trí x của ô tiếp theo cần check
							int vty = y + lineY[i]*j; // vị trí y của ô tiếp theo cần check
							// vtx hoặc vty < 0 hoặc > size, hoặc ô này != ô đầu => khỏi ktra
							if(vtx < 0 || vty < 0 || vtx >= size || vty >= size) break;
							if(cell[vtx][vty] == cell[x][y]) cells++;
							else break;
						}
						if(cells == 5) return 1; // User thắng
						if(cells == -5) return -1; // AI thắng
					}
				}
			}
		}
		return 0; // Không ai thắng cả
	}
	
	/**
	 * Dùng AI để tìm bước đi tiếp theo. Sau đó update matrix
	 * @param mode
	 */
	public void nextStep() {
		int x, y;
		/*Dùng random để test thôi*/
		do {
			x = rand(size);
			y = rand(size);
		}while(!clickable(x, y));
		nextX = x;
		nextY = y;
		update(x, y, AI_VALUE); // cập nhật ma trận
		/*Code phần AI ở đây*/
		// Lưu ý: Sau khi tìm được bước đi của AI rồi, nhớ cập nhật nextX, nextY; update x, y bằng hàm update.
		// Lưu ý: Code xong phần AI chỉ cần comment phần random phía trên là code chạy được.
		
		// TODO: code
	}
	
	public int getNextX() {
		return nextX;
	}
	
	public int getNextY() {
		return nextY;
	}
	
	public int rand(int size) {
		return rand.nextInt(size);
	}
	
}
