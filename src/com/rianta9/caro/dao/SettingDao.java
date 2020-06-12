/**
 * 
 */
package com.rianta9.caro.dao;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;

import com.rianta9.caro.bean.Setting;
import com.rianta9.caro.values.Value;


/**
 * Lấy/ Cập nhật thông tin setting của game
 * @author rianta9
 * Datecreate: 17 thg 5, 2020 19:30:54
 */
public class SettingDao {
	public static Setting LoadSettingInfo() {
		File file = new File("file\\setting.txt");
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (Exception e1) {
				e1.printStackTrace();
				System.out.println("Co loi khi mo file setting.txt!");
				return new Setting(Value.BACKGROUND_COLOR, Value.CELL_COLOR, Value.USER_TEXT_COLOR, Value.AI_TEXT_COLOR, Value.DEFAULT_MODE);
			}
		}
		try {
			// Load data unicode
			BufferedReader reader = 
				new BufferedReader(new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8));
			String[] rem = reader.readLine().split("[,]");

			Color backgroundColor = new Color(Integer.parseInt(rem[0]), Integer.parseInt(rem[1]), Integer.parseInt(rem[2]));
			rem = reader.readLine().split("[,]");
			Color cellColor = new Color(Integer.parseInt(rem[0]), Integer.parseInt(rem[1]), Integer.parseInt(rem[2]));
			rem = reader.readLine().split("[,]");
			Color xColor = new Color(Integer.parseInt(rem[0]), Integer.parseInt(rem[1]), Integer.parseInt(rem[2]));
			rem = reader.readLine().split("[,]");
			Color oColor = new Color(Integer.parseInt(rem[0]), Integer.parseInt(rem[1]), Integer.parseInt(rem[2]));
			int mode = Integer.parseInt(reader.readLine());
			Setting setting = new Setting(backgroundColor, cellColor, xColor, oColor, mode);
			reader.close();
			return setting;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Co loi khi mo file setting.txt!");
			return new Setting(Value.BACKGROUND_COLOR, Value.CELL_COLOR, Value.USER_TEXT_COLOR, Value.AI_TEXT_COLOR, Value.DEFAULT_MODE);
		}
	}
	
	public static void SaveSettingInfo(Setting setting) {
		File file = new File("file\\setting.txt");
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (Exception e1) {
				System.out.println("File khong ton tai!. Da tao moi file setting.txt!");
			}
		}
		try {
			// Save data unicode
			OutputStreamWriter writer =
	             new OutputStreamWriter(new FileOutputStream(file, false), StandardCharsets.UTF_8);
			writer.write(setting.getStringBackgroundColor() + "\n");
			writer.write(setting.getStringCellColor() + "\n");
			writer.write(setting.getStringXColor() + "\n");
			writer.write(setting.getStringOColor() + "\n");
			writer.write(String.valueOf(setting.getMode()) + "\n");
//			Color rem = setting.getTextStoryColor();
//			String color = String.valueOf(rem.getRed())+","+String.valueOf(rem.getGreen())+","+String.valueOf(rem.getBlue());
//			writer.write(color + "\n");
			writer.close();
		} catch (Exception e1) {
			System.out.println("Co loi khi luu file setting.txt!");
		}
	}
}
