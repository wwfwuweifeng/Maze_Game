package 我是迷宫王;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JPanel;

public class First_Panel extends JPanel{

	 public First_Panel() {
		// TODO 自动生成的构造函数存根
//	 this.setSize(575, 376);
		this.setLayout(null);
		this.repaint();
	}
	 @Override
	protected void paintComponent(Graphics g) {
		// TODO 自动生成的方法存根
		super.paintComponent(g);
		Toolkit tool = this.getToolkit();
		Image image = tool.getImage("e:/workplace/软工2/src/配置文件/start.gif");
		g.drawImage(image,0,0,575,376, this);
	}
}
