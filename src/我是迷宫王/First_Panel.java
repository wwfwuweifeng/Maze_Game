package �����Թ���;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JPanel;

public class First_Panel extends JPanel{

	 public First_Panel() {
		// TODO �Զ����ɵĹ��캯�����
//	 this.setSize(575, 376);
		this.setLayout(null);
		this.repaint();
	}
	 @Override
	protected void paintComponent(Graphics g) {
		// TODO �Զ����ɵķ������
		super.paintComponent(g);
		Toolkit tool = this.getToolkit();
		Image image = tool.getImage("e:/workplace/��2/src/�����ļ�/start.gif");
		g.drawImage(image,0,0,575,376, this);
	}
}
