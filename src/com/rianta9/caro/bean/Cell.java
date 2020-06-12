/**
 * 
 */
package com.rianta9.caro.bean;

/**
 * @author rianta9
 * Datecreate: 12 thg 5, 2020 17:52:27
 */
public class Cell {
	private int x;
	private int y;
	private int selected; // người đã đánh ô này USER_VALUE: 1, AI_VALUE: -1
	
	public Cell() {
		this.x = -1;
		this.y = -1;
		this.selected = 0;
	}
	
	public Cell(int x, int y) {
		this.x = x;
		this.y = y;
		this.selected = 0; // chưa ai chọn
	}
	
	public Cell(int x, int y, int selected) {
		this.x = x;
		this.y = y;
		this.selected = selected;
	}
	
	public void setLocation(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}
	
	public int getSelected() {
		return this.selected;
	}
	
	public void setSelected(int selected) {
		this.selected = selected;
	}
	
	public boolean isClickable() {
		if(this.selected == 0) return true;
		return false;
	}
}
