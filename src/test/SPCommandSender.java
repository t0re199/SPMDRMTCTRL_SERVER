package test;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class SPCommandSender
{
	private static final String REGEX = "\\d{1,3}\\.\\d{1-3}\\.\\d{1-3}";
	
	private Socket socket;
	private OutputStreamWriter osw;
	private int port;
	private String ip;
	
	public SPCommandSender(String ip, int port)
	{
		if(port <= 0 || !ip.matches(REGEX))
		{
			throw new IllegalArgumentException();
		}
		this.port = port;
		this.ip = ip;
	}
	
	public SPCommandSender(int port)
	{
		if(port <= 0)
		{
			throw new IllegalArgumentException();
		}
		this.port = port;
		this.ip = "127.0.0.1";
	}
	
	public void sendCommand(String command) throws UnknownHostException, IOException
	{
		if(socket == null)
		{
			socket = new Socket(ip, port);
			socket.setKeepAlive(true);  
		}
		osw = new OutputStreamWriter(socket.getOutputStream());
		osw.write(command);
		osw.flush();
		osw.close();
	}
	
	public static void main(String[] args) throws UnknownHostException, IOException
	{
		SPCommandSender spcs = new SPCommandSender(2905);
		spcs.sendCommand("playpause");
		spcs.sendCommand("playpause");
		spcs.sendCommand("playpause"); 
	}
}
