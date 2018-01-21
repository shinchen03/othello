package othello;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import javax.swing.JFrame;

public class Panel extends JFrame{
	
	private static final int sx = 100;
    private static final int sy = 100;
    private static final int w = 80;
    private static final int rw = 800;
    private static final int r = 72;
    private static final int rr = 10;
    private Graphics jg;
    private Color rectColor = new Color(0x008000);
    
	public void setPanel() {
		// visualize the game;
		Container p = getContentPane();
        setBounds(200, 200, 1000, 1000);
        setVisible(true);
        p.setBackground(rectColor);
        setLayout(null);   
        setResizable(false);
        this.setTitle("Othello");
        ImageIcon img = new ImageIcon("./img/icon.png");
        this.setIconImage(img.getImage());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);        
        
        try {
            Thread.sleep(500);
        } catch (Exception e) {
            e.printStackTrace();
        }        

        // 获取专门用于在窗口界面上绘图的对象
        jg =  this.getGraphics();
        
        // 绘制游戏区域
        paintComponents(jg);
	}
	
	public void paintComponents(Graphics g) {
        try {
            Graphics2D g2 = (Graphics2D) g;
            // 设置线条颜色为红色
            g2.setColor(Color.black);
            
            // 绘制外层矩形框
        	g2.setStroke(new BasicStroke(8));
            g2.drawRect(sx, sy, rw, rw);
            
            /* 绘制水平10个，垂直10个方格。
             * 即水平方向9条线，垂直方向9条线，
             * 外围四周4条线已经画过了，不需要再画。
             * 同时内部64个方格填写数字。
             */
            for(int i = 1; i < 10; i ++) {
            	g2.setStroke(new BasicStroke(5));
                // 绘制第i条竖直线
                g2.drawLine(sx + (i * w), sy, sx + (i * w), sy + rw);
                
                // 绘制第i条水平线
                g2.drawLine(sx, sy + (i * w), sx + rw, sy + (i * w));
                
                // 填写第i行从第1个方格到第8个方格里面的数字（方格序号从0开始）
                for(int j = 0; j < 10; j ++) {
                    //drawString(g, j, i);                    
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	
	public void refreshBoard(String[][] board) {
        Graphics2D g2 = (Graphics2D) jg;
		for (int i=1; i<9; i++) {
			for (int j=1; j<9; j++) {
				if (board[i][j] == "●") {
					g2.setColor(Color.BLACK);
					g2.fillOval(sx + (i * w) + 4, sy + (j * w) + 4, r, r);
				}
				if (board[i][j] == "○") {
					g2.setColor(Color.WHITE);
					g2.fillOval(sx + (i * w) + 4, sy + (j * w) + 4, r, r);
				}
				if (board[i][j] == "×") {
					g2.setColor(Color.WHITE);
					g2.fillOval(sx + (i * w) + w/2 -5, sy + (j * w) + w/2 -5, rr, rr);
				}
			}
		}
	}
}