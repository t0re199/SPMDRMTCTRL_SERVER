package net;

import java.io.IOException;

public interface Server
{
	public static enum ServerStatus{RUNNING, NOT_RUNNING};
	
	ServerStatus getStatus();
	
	void stopServerListening();
	
	void startListening() throws IOException;
	
	void onCommandReceived(String command);
	
	boolean isRunning();
	
	int getPort();

}