package utils;

public final class SPUtils
{
	private static final String ERROR_PREFIX = "ERROR ---> ";
	public static final String WIN = "win";
	public static final String UNIX = "unix";
	
	private SPUtils()
	{
		
	}
	
	public static void printError(String error)
	{
		System.out.println(ERROR_PREFIX + error);
	}
	
	public static boolean osIsWindows()
	{
		return getOperatingSystem().contains("Windows");
	}
	
	
	public static boolean osIsLinuxOrMacOs()
	{
		return !osIsWindows();
	}
	
	
	private static String getOperatingSystem()
	{
		return System.getProperty("os.name");
	}
	
	public static String getPlatform()
	{
		return osIsWindows() ? WIN : UNIX;
	}

}
