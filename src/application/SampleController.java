package application;

import javafx.fxml.FXML;
import javafx.scene.shape.Rectangle;

public class SampleController {
	
	@FXML
	Rectangle head = new Rectangle();
	@FXML
	Rectangle body1 = new Rectangle();
	@FXML
	Rectangle body2 = new Rectangle();
	@FXML
	Rectangle body3 = new Rectangle();
	
	private double x = 0;
	private double y = 0;
	private int bodycnt = 0;
	private double[] storage = new double[100];//[0]=x,[1]=y
	
	public void eat() {
		bodycnt ++;
		
	if(bodycnt==1) {body1.setOpacity(1);}
	if(bodycnt==2) {body2.setOpacity(1);}
	if(bodycnt==3) {body3.setOpacity(1);}	
		
	}
	
	public void up(String e) {

		if (e.equalsIgnoreCase("w")) {
			storage[0]= head.getX();
			storage[1]= head.getY();
//			head.setRotate(0);
			head.setY(y -= 50.0);
			//
			body1.setY(head.getY());
			System.out.println("--W-- \nHead  x:" + head.getX()+" -- " + "Head  y:" + head.getY() +"\n" + "Body1 x:" + body1.getX() + " -- " + "Body1 y:" + body1.getY());
			System.out.println("last x:" + storage[0] + " -- " + "last y:" + storage[1]);
		}
	}
	public void down(String e) {

		if (e.equalsIgnoreCase("s")) {
			storage[0]= head.getX();
			storage[1]= head.getY();
//			head.setRotate(180);
			head.setY(y += 50.0);
			//
			if ( head.getY()<storage[1] && head.getX()==body1.getX()) { body1.setX(body1.getX()-50); }
			else body1.setY(body1.getY()+50);
			System.out.println("--S-- \nHead  x:" + head.getX()+" -- " + "Head  y:" + head.getY() +"\n" + "Body1 x:" + body1.getX() + " -- " + "Body1 y:" + body1.getY());
			System.out.println("last x:" + storage[0] + " -- " + "last y:" + storage[1]);
		}
		
	}
	public void right(String e) {

		if (e.equalsIgnoreCase("d")) {
			storage[0]= head.getX();
			storage[1]= head.getY();
//			head.setRotate(90);
			head.setX(x += 50.0);
			//
			
			System.out.println("--D-- \nHead  x:" + head.getX()+" -- " + "Head  y:" + head.getY() +"\n" + "Body1 x:" + body1.getX() + " -- " + "Body1 y:" + body1.getY());
			System.out.println("last x:" + storage[0] + " -- " + "last y:" + storage[1]);}
		
	}
	public void left(String e) {

		if (e.equalsIgnoreCase("a")) {
			storage[0]= head.getX();
			storage[1]= head.getY();
//			head.setRotate(270);
			head.setX(x -= 50.0);
			//
			if (head.getX()<storage[0] && head.getY()==body1.getY()) { body1.setY(body1.getY()-50); }
			else body1.setX(body1.getX()-50);
			System.out.println("--A-- \nHead  x:" + head.getX()+" -- " + "Head  y:" + head.getY() +"\n" + "Body1 x:" + body1.getX() + " -- " + "Body1 y:" + body1.getY());
			System.out.println("last x:" + storage[0] + " -- " + "last y:" + storage[1]);}

		
	}

	
	
	
	
	
	
	
	
	
	
	
	
}
