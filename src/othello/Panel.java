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
            
            // 设置线条颜色为红色
            g.setColor(Color.black);
            
            // 绘制外层矩形框
            g.drawRect(sx, sy, rw, rw);
            
            /* 绘制水平10个，垂直10个方格。
             * 即水平方向9条线，垂直方向9条线，
             * 外围四周4条线已经画过了，不需要再画。
             * 同时内部64个方格填写数字。
             */
            for(int i = 1; i < 10; i ++) {
                // 绘制第i条竖直线
                g.drawLine(sx + (i * w), sy, sx + (i * w), sy + rw);
                
                // 绘制第i条水平线
                g.drawLine(sx, sy + (i * w), sx + rw, sy + (i * w));
                
                // 填写第i行从第1个方格到第8个方格里面的数字（方格序号从0开始）
                for(int j = 0; j < 10; j ++) {
                    //drawString(g, j, i);                    
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
