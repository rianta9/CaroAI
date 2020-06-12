package com.rianta9.caro.bo;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.rianta9.caro.bean.Cell;
import com.rianta9.caro.bean.EvalCell;
import com.rianta9.caro.bean.State;
import com.rianta9.caro.values.Value;

/**
 * @author rianta9
 * Datecreate: 15 thg 5, 2020 19:38:08
 */
public class Heuristic {
    private int[][] evalState; // ma trận lượng giá
    
    public Heuristic() {
        this.evalState = new int[Value.SIZE][Value.SIZE];
    }
	/**
     * X = 1: User, O = 2: AI
     */
//    private String[] caseHuman = {
//    	"00110", "01010", "01100",
//    	"11100", "11010", "11001", "10110", "10101", "01101" , "10011", "01011", "00111",
//    	"01110", 
//    	"011100", "011010", "010110", "001110", "1010101", "1011001", "1001101", // đánh 2 đường nữa là thắng
//    	"01111", "10111", "11011", "11101", "11101",  "11110", // đánh 1 đường nữa là thắng
//        "11111"
//    };

//	Test: 01101010 
    
    private int[] point = {
    	4, 4, 4,
    	8, 8, 8,
    	8, 8, 8, 8, 8, 8, 
    	8,
    	500, 500, 500, 500, 500, 500, 500,
    	1000, 1000, 1000, 1000, 1000, 1000,
    	100000
    };
    private String[] caseUser = {
		"11001", "10101", "10011",
    	"00110", "01010", "01100",
        "11100", "11010", "10110", "01101", "01011", "00111",
    	"01110", 
    	"011100", "011010", "010110", "001110", "1010101", "1011001", "1001101", // đánh 2 đường nữa là thắng
    	"01111","10111", "11011", "11101", "11101",  "11110", 
        "11111"
    };
    private String[] caseAI = {
    	"22002", "20202", "20022",
    	"00220", "02020", "02200",
        "22200", "22020", "20220", "02202", "02022", "00222",
    	"02220", 
    	"022200", "022020", "020220", "002220", "2020202", "2022002", "2002202",
    	"02222", "20222", "22022", "22202", "22202",  "22220", 
        "22222"
    };
    
    
    int[] defenseScore = {0, 1, 9, 81, 729, 6534}; // bảng điểm phòng thủ
    int[] attackScore = {0, 3, 24, 192, 1536, 12288}; // bảng điểm tấn công
   
    
    /**
     * Lượng giá trạng thái bàn cờ
     */
    public int evaluateState(State state) {
        String rem = ";";
        int[][] cell = state.getState();
        //check hàng và cột (|,__)
        for (int i = 0; i < Value.SIZE; i++) {
            for (int j = 0; j < Value.SIZE; j++) {
                rem += cell[i][j];
            }
            rem += ";";
            for (int j = 0; j < Value.SIZE; j++) {
                rem += cell[j][i];
            }
            rem += ";";
        }
        // check nửa trên đường chéo phải ( \ )
        for (int i = 0; i < Value.SIZE - 4; i++) {
            for (int j = 0; j < Value.SIZE - i; j++) {
                rem += cell[j][i+j];
            }
            rem += ";";
        }
        // check nửa dưới đường chéo phải ( \ )
        for (int i = Value.SIZE - 5; i > 0; i--) {
            for (int j = 0; j < Value.SIZE - i; j++) {
                rem += cell[i+j][j];
            }
            rem += ";";
        }
        // check nửa trên đường chéo trái ( / )
        for (int i = 4; i < Value.SIZE; i++) {
            for (int j = 0; j <= i; j++) {
                rem += cell[i-j][j];
            }
            rem += ";";
        }
        // check nửa dưới đường chéo trái ( / )
        for (int i = Value.SIZE - 5; i > 0; i--) {
            for (int j = Value.SIZE - 1; j >= i; j--) {
                rem += cell[j][i + Value.SIZE - j - 1];
            }
            rem += ";\n";
        }
        String find1, find2;
        int diem = 0;
        // Tính điểm của trạng thái
        for (int i = 0; i < caseUser.length; i++) { // duyệt các đường chiến lược
            find1 = caseAI[i]; // duyệt những đường chiến lược của AI
            find2 = caseUser[i]; // duyệt những đường chiến lược của  User
            diem += point[i] * count(rem, find1); // cộng vào điểm lượng giá của AI
            diem -= point[i] * count(rem, find2); // trừ đi điểm lượng giá của User
        }
        return diem;
    }

   /**
    * Đếm số lượng find trong text
    * @param text
    * @param find
    * @return
    */
    public int count(String text, String find) {
        Pattern pattern = Pattern.compile(find);
        Matcher matcher = pattern.matcher(text);
        int i = 0;
        while (matcher.find()) i++;
        return i;
    }
    
    
    /**
     * Reset bảng điểm về 0
     */
    void resetValue() {
        for (int i = 0; i < Value.SIZE; i++) {
            for (int j = 0; j < Value.SIZE; j++) {
                evalState[i][j] = 0;
            }
        }
    }
    
    /**
     * Lượng giá mỗi ô vuông của bàn cờ
     *
     * @param state trạng thái trò chơi
     * @param player người chơi hiện tại
     *
     */
    public void evaluateEachCell(State state, int player) {
        resetValue();
        int x, y, i, countAI, countUser;
        int[][] cell = state.getState();
        /*Kiểm tra theo hàng 
         * -----
         * -----
         * -----
         * */
        for (x = 0; x < Value.SIZE; x++) {
            for (y = 0; y < Value.SIZE - 4; y++) {
                countAI = 0; countUser = 0;
                /*đếm số ô người chơi và AI đã đánh ở đoạn từ y đến y+4*/
                for (i = 0; i < 5; i++) { // duyệt đoạn
                    if (cell[x][y+i] == Value.AI_VALUE) countAI++;
                    else if (cell[x][y+i] == Value.USER_VALUE) countUser++;
                }
                /*Nếu ở đoạn này một trong hai không đánh ô nào và người còn lại có đánh ít nhất 1 ô*/
                if (countAI * countUser == 0 && countAI != countUser) {              	
                    for (i = 0; i < 5; i++) { // duyệt đoạn
                        if (cell[x][y+i] == 0) { // nếu ô này ko ai đánh
                            if (countAI == 0) { // nếu ở đoạn này AI không đánh ô nào cả
                                if (player == Value.AI_VALUE) { // nếu lượt chơi hiện tại là của AI
                                    evalState[x][y+i] += defenseScore[countUser]; // thì cộng điểm phòng ngự ở ô này
                                } 
                                else evalState[x][y+i] += attackScore[countUser]; // ngược lại, thì tăng điểm tấn công ở ô này
                            }
                            else if (countUser == 0) { // nếu ở đoạn này User không đánh ô nào cả
                                if (player == Value.USER_VALUE) { // nếu lượt chơi hiện tại là của User
                                    evalState[x][y+i] += defenseScore[countAI]; // thì cộng điểm phòng ngự ở ô này
                                } 
                                else evalState[x][y+i] += attackScore[countAI]; //ngược lại,cộng điểm tấn công của ô này
                            }
                            if (countAI == 4 || countUser == 4) { // Nếu một trong hai người chơi có nước 4
                                evalState[x][y+i] *= 2; // thì lượng giá ô này lên gấp đôi
                            }
                        }
                    }
                }
            }
        }
        /*Kiểm tra theo cột 
         * |||||||
         * |||||||
         * |||||||
         * */
        for (x = 0; x < Value.SIZE - 4; x++) {
            for (y = 0; y < Value.SIZE; y++) {
                countAI = 0; countUser = 0;
                for (i = 0; i < 5; i++) {
                    if (cell[x+i][y] == Value.AI_VALUE) countAI++;
                    else if (cell[x+i][y] == Value.USER_VALUE) countUser++;
                }
                if (countAI * countUser == 0 && countAI != countUser) {
                    for (i = 0; i < 5; i++) {
                        if (cell[x+i][y] == 0) {
                            if (countAI == 0) {
                                if (player == Value.AI_VALUE) {
                                    evalState[x+i][y] += defenseScore[countUser];
                                } 
                                else evalState[x+i][y] += attackScore[countUser];
                            }
                            else if (countUser == 0) {
                                if (player == Value.USER_VALUE) {
                                    evalState[x+i][y] += defenseScore[countAI];
                                } 
                                else evalState[x+i][y] += attackScore[countAI];
                            }
                            if (countAI == 4 || countUser == 4) {
                                evalState[x+i][y] *= 2;
                            }
                        }
                    }
                }
            }
        }
        
        /*Kiểm tra theo đường chéo chính 
         * \\\\\\
         * \\\\\\
         * \\\\\\
         * */
        for (x = 0; x < Value.SIZE - 4; x++) {
            for (y = 0; y < Value.SIZE - 4; y++) {
                countAI = 0; countUser = 0;
                for (i = 0; i < 5; i++) {
                    if (cell[x+i][y+i] == Value.AI_VALUE) countAI++;
                    else if (cell[x+i][y+i] == Value.USER_VALUE) countUser++;
                }
                if (countAI * countUser == 0 && countAI != countUser) {
                    for (i = 0; i < 5; i++) {
                        if (cell[x+i][y+i] == 0) {
                            if (countAI == 0) {
                                if (player == Value.AI_VALUE) {
                                    evalState[x+i][y+i] += defenseScore[countUser];
                                } 
                                else evalState[x+i][y+i] += attackScore[countUser];
                            }
                            else if (countUser == 0) {
                                if (player == Value.USER_VALUE) {
                                    evalState[x+i][y+i] += defenseScore[countAI];
                                } 
                                else evalState[x+i][y+i] += attackScore[countAI];
                            }
                            if (countAI == 4 || countUser == 4) {
                                evalState[x+i][y+i] *= 2;
                            }
                        }
                    }
                }
            }
        }
        
        /*Kiểm tra theo đường chéo phụ*/
        
        for (x = 4; x < Value.SIZE; x++) {
            for (y = 0; y < Value.SIZE - 4; y++) {
                countAI = 0; countUser = 0;
                for (i = 0; i < 5; i++) {
                    if (cell[x-i][y+i] == Value.AI_VALUE)  countAI++;
                    else if (cell[x-i][y+i] == Value.USER_VALUE) countUser++;
                }
                if (countAI * countUser == 0 && countAI != countUser) {
                    for (i = 0; i < 5; i++) {
                        if (cell[x-i][y+i] == 0) {
                            if (countAI == 0) {
                                if (player == Value.AI_VALUE) {
                                    evalState[x-i][y+i] += defenseScore[countUser];
                                } 
                                else evalState[x-i][y+i] += attackScore[countUser];
                            }
                            else if (countUser == 0) {
                                if (player == Value.USER_VALUE) {
                                    evalState[x-i][y+i] += defenseScore[countAI];
                                } 
                                else evalState[x-i][y+i] += attackScore[countAI];
                            }
                            if (countAI == 4 || countUser == 4) {
                                evalState[x-i][y+i] *= 2;
                            }
                        }
                    }
                }
            }
        }
    }
    
//    
//    /**
//	 * @param x
//	 * @param i
//	 * @return
//	 */
//	private boolean isInBoard(int x, int y) {
//		if(x >= 0 && x < Value.SIZE && y >= 0 && y < Value.SIZE) return true;
//		return false;
//	}

	/**
     * Lấy danh sách những ô tối ưu nhất có điểm lượng giá cao để duyệt
     * @return
     */
    public ArrayList<EvalCell> getOptimalList() {
        int size = Value.MAX_NUM_OF_HIGHEST_CELL_LIST; // số phần tử tối đa được phép lấy
        int[] maxValueList = new int[size];
        Cell[] maxCellList = new Cell[size];
        // khởi tạo giá trị
        for(int i = 0; i< size; i++) {
        	maxValueList[i] = Integer.MIN_VALUE;
        	maxCellList[i] = new Cell();
        }
        
        for (int x = 0; x < Value.SIZE; x++) {
            for (int y = 0; y < Value.SIZE; y++) {
            	int value = getEvalCellValue(x, y);
            	/*Tìm list những ô tối ưu để đánh*/
            	for(int i = size-1; i >= 0; i--) {
            		if(maxValueList[i] <= value && value != 0) { 
            			/* sắp xếp theo thứ tự giảm dần */
            			for(int j = 0; j < i; j++) { // cập nhật những phần tử trước i
            				maxValueList[j] = maxValueList[j+1]; // cập nhật điểm
            				maxCellList[j].setLocation(maxCellList[j+1].getX(), maxCellList[j+1].getY()); // cập nhật vị trí
            			}
            			// cập nhật phần tử i
            			maxValueList[i] = value;
            			maxCellList[i].setLocation(x, y);
            			break;
            		}
            	}
            }
        }
        // add vào list những phần tử có số điểm lớn nhất có độ lớn gần bằng nhau(ex: 981, 857, 80, 15 => chỉ chọn 981 và 857)
        int maxLength = lengthNum(maxValueList[size-1]); // length của số lớn nhất
        int[] difference = {0, 2, 8, 32, 128, 512}; // chênh lệch theo từng cấp độ dài so với phần tử lớn nhất

        ArrayList<EvalCell> list = new ArrayList<EvalCell>(); // danh sách điểm được sắp xếp giảm dần
        list.add(new EvalCell(maxCellList[size-1], maxValueList[size-1])); // add vào phần tử có điểm lớn nhất
        for(int i = size-2; i>=0; i--) { // add vào các phần tử còn lại phù hợp điều kiện
        	if(maxValueList[size-1] - maxValueList[i] <= difference[maxLength]) { // chỉ chấp nhận chênh lệch so với pt lớn nhất trong khoảng quy định
        		list.add(new EvalCell(maxCellList[i], maxValueList[i]));
        	}
        	else break;
        }
        return list;
    }
    
	
	public int getEvalCellValue(int x, int y) {
		return this.evalState[x][y];
	}
	
	public void setEvalCell(int x, int y, int value) {
		this.evalState[x][y] = value;
	}
	
	private int lengthNum(int a) {
		if(a == 0) return 1;
		if(a < 0) a *= -1;
		int dem = 0;
		while(a > 0) {
			a /= 10;
			dem++;
		}
		return dem;
	}
	
	/**
	 * In bảng điểm đánh giá trạng thái
	 */
	public void printEvalState() {
		for (int i=0; i<Value.SIZE; i++) {
			for(int j=0; j<Value.SIZE; j++) {
				int len = lengthNum(evalState[i][j]);
				String rem = String.valueOf(evalState[i][j]);
				if(len == 1) System.out.print(rem + "    ");
				else if(len == 2) System.out.print(rem + "   ");
				else if(len == 3) System.out.print(rem + "  ");
				else System.out.print(rem + " ");
			}
			System.out.println();
		}
	}

}
