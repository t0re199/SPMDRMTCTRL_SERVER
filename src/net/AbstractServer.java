package net;

public abstract class AbstractServer implements Server
{	
	protected int port;
	protected ServerStatus status = ServerStatus.NOT_RUNNING;
	
	public boolean isRunning()
	{
		return status == ServerStatus.RUNNING;
	}
	
	public int getPort()
	{
		return port;
	}
	
	public ServerStatus getStatus()
	{
		return status;
	}
	
	protected void setStatus(ServerStatus status)
	{
		this.status = status;
	}
}
