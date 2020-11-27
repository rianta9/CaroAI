/**
 * 
 */
package com.rianta9.caro.values;

import java.awt.Color;

/**
 * @author rianta9
 * Datecreate: 24 thg 4, 2020 17:30:05
 */
public class Value {

	public static final String INFO_MESSAGE = 
		  "Trường: Đại Học Khoa Học Huế\n"
		+ "Môn: Nhập Môn Trí Tuệ Nhân Tạo\n"
		+ "Giảng viên: Đoàn Thị Hồng Phước\n"
		+ "Đề tài: Game cờ caro sử dụng thuật toán cắt tỉa alpha beta\n"
		+ "Thành viên thực hiện:\n"
		+ " Hoàng Quốc Khánh\n"
		+ " Đoàn Phú Thuận\n"
		+ " Hồ Xuân Lâm\n"
		+ " Nguyễn Minh Hiếu\n"
		+ "Nội dung:\n"
		+ " Game cờ caro sử dụng thuật toán cắt tỉa alpha beta, bài tập nhóm môn nhập môn trí tuệ nhân tạo!";
	public static final String INTRODUCE_MESSAGE = 
		  "Nguồn Gốc và Luật Chơi Cờ Caro"
		+ "\r\n"
		+ "\r\n" 
		+ "Cờ caro – một trò chơi rất phổ biến ở Việt Nam và được rất nhiều người yêu thích,"
		+ " đặc biệt là các bạn đã từng trải qua thời học sinh. Nói đến cờ caro,"
		+ " chắc hẳn không một bạn học sinh nào là không biết."
		+ " Chỉ cần một mảnh giấy kẻ ô, hai cây bút, bạn có thể tự tổ chức một cuộc thi cờ đúng nghĩa."
		+ "\r\n" 
		+ "\r\n" 
		+ "1. Lịch sử cờ caro" 
		+ "\r\n"
		+ "Cho đến nay vẫn chưa ai biết chính xác lai lịch của cờ caro, có lời đồn cho rằng,"
		+ " cờ caro xuất hiện cách đây khoảng hơn 2000 năm TCN tại Trung Quốc."
		+ " Tuy nhiên, một số nhà khoa học đã tìm ra bằng chứng rằng cờ caro đã xuất hiện"
		+ " ở Hy Lạp cổ đại và châu Mĩ trước thời Colombo."
		+ "\r\n" 
		+ "Sau khi ra đời, cờ caro được truyền bá rộng rãi sang các quốc gia khác nhau trên thế giới,"
		+ " vì vậy mà xuất hiện nhiều biến thể của trò chơi này với nhiều cách chơi khác nhau,"
		+ " đánh dấu nhiều mốc quan trọng trong lịch sử hình thành và phát triển cờ caro."
		+ "\r\n" 
		+ "Cờ caro bắt đầu với cái tên Wutzu bên Trung Quốc và biến danh thành Gomoku ở Nhật Bản."
		+ " Tuy nhiên, ở đất nước của xứ sở hoa anh đào,"
		+ " nó còn được gọi dưới nhiều cái tên khác nhau ở mỗi địa phương Kakugo,"
		+ " gomoku-narabe, Itsutsu-ishi,…"
		+ "\r\n" 
		+ "\r\n" 
		+ "2. Luật chơi"
		+ "\r\n" 
		+ "Khi mới xuất hiện, loại cờ này được chơi bằng các con cờ vây (viên cờ màu trắng và đen)"
		+ " trên một bàn cờ vây (19×19). Quân đen đi trước và người chơi lần lượt"
		+ " đặt một viên đá của họ trên đường chéo còn trống."
		+ " Người thắng là người đầu tiên có được một chuỗi liên tục gồm 5 quân hàng ngang,"
		+ " hoặc dọc, hoặc chéo."
		+ " Tuy nhiên, vì một khi đã đặt xuống, các quân cờ không thể di chuyển hoặc bỏ ra khỏi bàn,"
		+ " do đó loại cờ này có thể chơi bằng giấy bút."
		+ "\r\n" 
		+ "Ở Việt Nam, cờ này thường chơi trên giấy tập học sinh (đã có sẵn các ô ca-rô),"
		+ " dùng bút đánh dấu hình tròn (O) và chữ thập (X) để đại diện cho 2 quân cờ,"
		+ " bên nào có đường 5 quân liền nhau trên một hàng, một cột hoặc một đường chéo là thắng."
		+ "\r\n" 
		+ "Trong cờ carô, bên đi trước luôn có lợi thế rất lớn,"
		+ " thậm chí L.Victor Allis còn chứng minh được rằng trong cờ carô tự do,"
		+ " bên đi trước luôn luôn thắng. Để hạn chế nhược điểm này người ta đưa ra"
		+ " một số luật bổ sung để hạn chế lợi thế của người đi trước (quân đen)"
		+ " và tăng cường khả năng phòng thủ của người đi sau."
		+ "\r\n" 
		+ "\r\n" 
		+ "3. Các phiên bản cờ caro"
		+ "\r\n" 
		+ "Thoạt đầu mới nhìn chơi cờ caro, nhiều người vẫn nghĩ đây là trò chơi đơn giản"
		+ " nhưng thực chất trò caro mà chúng ta vẫn hay chơi chỉ là thể loại cũ của môn cờ này."
		+ " Trên thế giới hiện nay, môn cờ này đã được phát triển thành nhiều thế cờ khác nhau"
		+ " và luật chơi phức tạp hơn nhiều. Thực chất, cờ caro khá hấp dẫn với những màn đấu trí tuệ cao."
		+ " Để chơi cờ caro bạn cần có một tư duy logic cực nhanh và nhạy,"
		+ " không những thế bạn cần có óc quan sát và phán đoán."
		+ " Có như thế bạn mới có thể thắng được."
		+ " Nếu chơi với tính chất đơn giản thì đó chỉ là những quân cờ trắng đen."
		+ "\r\n" 
		+ "Cờ ca-rô (hay sọc ca-rô) là một trò chơi bàn cờ theo chiến thuật trừu tượng."
		+ " Cờ ca-rô trong tiếng Triều Tiên là omok (오목), tiếng Trung là 五子棋 (bính âm: wǔzǐqí)"
		+ " và trong tiếng Nhật là 五目並べ (gomoku narabe); tiếng Anh, sử dụng lại tiếng Nhật, gọi là gomoku."
		+ "\r\n" 
		+ "Các luật cơ bản: Ván cờ được chơi trên bàn cờ 15 x 15 dòng kẻ."
		+ " Hai bên sẽ thay phiên nhau đi những quân cờ vào giao điểm của các hàng ngang và cột dọc."
		+ " Người đi trước (quân đen) sẽ đi quân đầu tiên vào tâm bàn cờ (giao của hàng 8, cột 8)."
		+ " Luật thắng thì cũng như cờ ca-rô, tuy nhiên bên đen phải chịu những hạn chế cơ bản sau:"
		+ " không được tạo thành một hàng 6 quân liên tiếp (overlines),"
		+ " không được tạo thành các bẫy 3×3 (double-threes) và 4×4 (double-fours)"
		+ " – nếu đi vào những nước như vậy coi như thua,"
		+ " người nào đi được 5 quân nhưng đã bị chặn trước ở 2 đầu sẽ không thắng."
		+ "Cuối cùng, có luật về nước đi thứ 5 và thứ 7:"
		+ " Ở những nước thứ 5 và thứ 7, quân đen phải đưa ra 2 phương án nước đi"
		+ " để quân trắng có quyền lựa chọn (Alternative moves 5th and 7th)."
		+ " Hai lựa chọn này phải không đối xứng nhau (vì nếu đối xứng thì coi như không có quyền chọn)."
		+ " Tất cả những luật này không những hạn chế ưu thế của quân đen"
		+ " mà còn đem đến cho quân trắng những cơ hội phải công."
		+ " Ví dụ, quân trắng, bằng những nước đi của mình có thể buộc quân đen"
		+ " tạo thành 1 hàng 6 quân liên tiếp hay tạo thành bẫy 3×3."
		+ " Có những ván đấu kết thúc ngay ở nước thứ 7 vì quân đen buộc phải đi 2 nước đối xứng."
		+ "\r\n" 
		+ "\r\n" 
		+ "Cờ caro tưởng như rất đơn giản nhưng lại là môn cờ rất phức tạp và rất hấp dẫn."
		+ " Với một trò chơi sử dụng chủ yếu là kỹ năng logic thế này,"
		+ " cờ caro rất phù hợp với lứa tuổi học sinh."
		+ "\r\n"
		+ "Nguồn bài viết: cothu.vn";
	
	/**
	 *  số hàng/số cột mặc định
	 */
	public static final int SIZE = 19;
	/**
	 *  chế độ chơi mặc định: User đi trước
	 */
	public static final int DEFAULT_MODE = 0; 
	/**
	 *  độ rộng của mỗi cell
	 */
	public static final int CELL_WIDTH = 30; 
	/**
	 *  khoảng cách giữa các panel
	 */
	public static final int MARGIN = 10; 
	/**
	 *  cỡ chữ trong mỗi cell
	 */
	public static final int TEXT_CELL_SIZE = 20; 
	/**
	 *  màu chữ mặc định của X
	 */
	public static final Color USER_TEXT_COLOR = Color.magenta; 
	/**
	 *  màu chữ mặc định của O
	 */
	public static final Color AI_TEXT_COLOR = Color.GREEN; 
	/**
	 *  màu mặc định của mỗi ô vuông
	 */
	public static final Color CELL_COLOR = Color.white; 
	
	/**
	 * màu mặc định khi user click vào một ô trong bàn cờ
	 */
	public static final Color CLICK_CELL_COLOR = new Color(0, 139, 139);
	/**
	 * màu nền mặc định
	 */
	public static final Color BACKGROUND_COLOR = new Color(31, 31, 51); 
	/**
	 * Giá trị mặc định của user
	 */
	public static final int USER_VALUE = 1;
	/**
	 * Giá trị mặc định của AI
	 */
	public static final int AI_VALUE = 2;
	/**
	 * Độ sâu tìm kiếm tối đa
	 */
	public static final int MAX_DEPTH = 3;
	/**
	 * số lượng lấy ra tối đa của danh sách các ô được lượng giá cao nhất
	 */
	public static final int MAX_NUM_OF_HIGHEST_CELL_LIST = 8;
}
