import java.awt.MouseInfo;

import graphics.SPMediaRemoteControllerUI;

public class SPRunner
{
	public static void main(String[] args) throws Throwable
	{
		SPMediaRemoteControllerUI gui = new SPMediaRemoteControllerUI();
		gui.setSize(200, 200);
		gui.setVisible(true);
		System.out.println(MouseInfo.getPointerInfo().getLocation());
	}

}