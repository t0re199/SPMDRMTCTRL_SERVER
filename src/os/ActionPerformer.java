package os;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import utils.SPUtils;

public class ActionPerformer extends AbstractPerformer
{
	private static final int ROBOT_DELAY = 5;
	
	private Robot robot;
	private int masterKeyCode;
	private String platform = SPUtils.getPlatform();
	
	public ActionPerformer() throws AWTException
	{
		robot = new Robot();
		robot.setAutoDelay(ROBOT_DELAY);
		setupMasterKeyCode();
	}
	
	public ActionPerformer(Robot robot)
	{
		if(robot == null)
		{
			throw new IllegalArgumentException();
		}
		this.robot = robot;
		setupMasterKeyCode();
	}
	
	private final void setupMasterKeyCode()
	{
		masterKeyCode = platform.equals(SPUtils.WIN) ? KeyEvent.VK_CONTROL : KeyEvent.VK_META;
	}
	
	@Override
	public void execute(int command)
	{
		execute(commandToString(command));
	}
	
	public void execute(String command)
	{
		if(command == null)
		{
			throw new IllegalArgumentException();
		}
		switch(command.trim().toLowerCase())
		{
			case "playpause":
				playPause();
				break;
				
			case "nexttrack":
				performNextTrack();
				break;
				
			case "previoustrack":
				performePreviousTrack();
				break;
		
			case "volumeup":
				performeVolumeUp();
				break;
				
			case "volumedown":
				performeVolumeDown();
				break;
				
			case "shuffle":
				performeShuffle();
				break;
				
			case "repeat":
				performeRepeat();
				break;
				
			case "up":
				performeUpKey();
				break;
				
			case "down":
				performeDownKey();
				break;

			case "page_down":
				performePageDown();
				break;

			case "page_up":
				performePageUp();
				break;
				
			case "close_program":
				closeProgram();
				break;
				
			default:
				throw new IllegalArgumentException();
		}
	}
	
	private void closeProgram()
	{
		robot.keyPress(masterKeyCode);
		if(platform.equals(SPUtils.WIN))
		{
			robot.keyPress(KeyEvent.VK_W);
			robot.keyRelease(KeyEvent.VK_W);
		}
		else
		{
			robot.keyPress(KeyEvent.VK_Q);
			robot.keyRelease(KeyEvent.VK_Q);
		}
		robot.keyRelease(masterKeyCode);
	}
	
	
	private void performeVolumeUp()
	{
		robot.keyPress(masterKeyCode);
		robot.keyPress(KeyEvent.VK_UP);
		robot.keyRelease(KeyEvent.VK_UP);
		robot.keyPress(KeyEvent.VK_UP);
		robot.keyRelease(KeyEvent.VK_UP);
		robot.keyPress(KeyEvent.VK_UP);
		robot.keyRelease(KeyEvent.VK_UP);
		robot.keyRelease(masterKeyCode);
	}
	
	private void performeUpKey()
	{
		robot.keyPress(KeyEvent.VK_UP);
		robot.keyRelease(KeyEvent.VK_UP);
	}
	
	private void performeDownKey()
	{
		robot.keyPress(KeyEvent.VK_DOWN);
		robot.keyRelease(KeyEvent.VK_DOWN);
	}
	
	private void performePageUp()
	{
		robot.keyPress(KeyEvent.VK_PAGE_UP);
		robot.keyRelease(KeyEvent.VK_PAGE_UP);
	}
	
	private void performePageDown()
	{
		robot.keyPress(KeyEvent.VK_PAGE_DOWN);
		robot.keyRelease(KeyEvent.VK_PAGE_DOWN);
	}
	
	private void performeVolumeDown()
	{
		robot.keyPress(masterKeyCode);
		robot.keyPress(KeyEvent.VK_DOWN);
		robot.keyRelease(KeyEvent.VK_DOWN);
		robot.keyPress(KeyEvent.VK_DOWN);
		robot.keyRelease(KeyEvent.VK_DOWN);
		robot.keyPress(KeyEvent.VK_DOWN);
		robot.keyRelease(KeyEvent.VK_DOWN);
		robot.keyRelease(masterKeyCode);
	}
	
	private void performNextTrack()
	{
		robot.keyPress(masterKeyCode);
		robot.keyPress(KeyEvent.VK_RIGHT);
		robot.keyRelease(masterKeyCode);
		robot.keyRelease(KeyEvent.VK_RIGHT);
	}
	
	private void performePreviousTrack()
	{
		robot.keyPress(masterKeyCode);
		robot.keyPress(KeyEvent.VK_LEFT);
		robot.keyRelease(masterKeyCode);
		robot.keyRelease(KeyEvent.VK_LEFT);
	}
	
	private void performeShuffle()
	{
		robot.keyPress(masterKeyCode);
		robot.keyPress(KeyEvent.VK_S);
		robot.keyRelease(masterKeyCode);
		robot.keyRelease(KeyEvent.VK_S);
	}
	
	private void performeRepeat()
	{
		robot.keyPress(masterKeyCode);
		robot.keyPress(KeyEvent.VK_R);
		robot.keyRelease(masterKeyCode);
		robot.keyRelease(KeyEvent.VK_R);
	}
	
	private void playPause()
	{
		robot.keyPress(KeyEvent.VK_SPACE);
		robot.keyRelease(KeyEvent.VK_SPACE);
	}
	
	private void switchProgram()
	{
		robot.keyPress(KeyEvent.VK_ALT);
		robot.keyPress(KeyEvent.VK_TAB);
		robot.keyRelease(KeyEvent.VK_TAB);
		robot.keyRelease(KeyEvent.VK_ALT);
	}
	
	private void pressClearData()
	{
		robot.mouseMove(0x0, 0x0);
		robot.mouseMove(420, 470);
		robot.delay(10);
		robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
		robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
	}
	
	private void reloadPage()
	{
		robot.keyPress(masterKeyCode);
		robot.keyPress(KeyEvent.VK_R);
		robot.keyRelease(KeyEvent.VK_R);
		robot.keyRelease(masterKeyCode);
	}
	
	private void clickOnBlackFridayLogo()
	{
		robot.mouseMove(0x0, 0x0);
		robot.mouseMove(90, 450);
		robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
		robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
	}
	
	private void fillForm()
	{
		robot.mouseMove(0x0, 0x0);
		robot.mouseMove(742, 341);
		
		robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
		robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
		
		robot.keyPress(masterKeyCode);
		robot.keyPress(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_V);
		robot.keyRelease(masterKeyCode);
	}
	
	private void clickPlay()
	{
		robot.mouseMove(0x0, 0x0);
		robot.mouseMove(739, 406);
		
		robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
		robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
	}
	
	public void clearChromeData()
	{
		switchProgram();
		robot.delay(1000);
		pressClearData();
		robot.delay(1000);
		switchProgram();
		robot.delay(100);
		reloadPage();
		robot.keyPress(KeyEvent.VK_ENTER);
	}
	
}
