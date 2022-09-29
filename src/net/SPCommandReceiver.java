package net;

import java.awt.AWTException;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;

import graphics.SPMediaRemoteControllerUI;
import os.ActionPerformer;
import os.Performer;
import utils.SPUtils;

public class SPCommandReceiver extends AbstractServer implements Runnable
{	
	private boolean dispatching = false;
	
	private Socket inputConnection;
	private ServerSocket serverSocket;
	
	private SPMediaRemoteControllerUI spMediaRemoteController;
	private Performer performer;
	
	private LinkedList<String> queue = new LinkedList<String>();

	public SPCommandReceiver(int port)
	{
		this(port, null);
	}
	
	public SPCommandReceiver(int port, SPMediaRemoteControllerUI spMediaRemoteControllerUI)
	{
		if(port <= 0)
		{
			throw new IllegalArgumentException();
		}
		super.port = port;
		
		this.spMediaRemoteController = spMediaRemoteControllerUI;
		
		try
		{
			performer = new ActionPerformer();
		}
		catch (AWTException e)
		{
			SPUtils.printError("Robot Construction Error");
			e.printStackTrace();
		}
	}
	
	
	public SPCommandReceiver(int port, Performer performer, SPMediaRemoteControllerUI spMediaRemoteControllerUI)
	{
		if(port <= 0)
		{
			throw new IllegalArgumentException();
		}
		super.port = port;
		
		this.spMediaRemoteController = spMediaRemoteControllerUI;
		this.performer = performer;
	}

	@Override
	public void run()
	{
		if(status != ServerStatus.RUNNING)
		{
			try
			{
				setStatus(ServerStatus.RUNNING);
				startListening();
			}
			catch(Throwable t)
			{
				t.printStackTrace();
				SPUtils.printError("Listening Exception");
			}
		}
	}
	
	public void startListening() throws IOException
	{
		serverSocket = new ServerSocket(port);
		System.out.println("Server Listening Started\n\n");
		while(isRunning())
		{
			try
			{
				inputConnection = serverSocket.accept();
				new Thread(new SPRequestHandler(this, inputConnection)).start();  
				Thread.sleep(100);
			}
			catch (IOException e) 
			{
				System.out.println("EXC socket closed, IOException catched!");
			}
			catch (InterruptedException e)
			{  
				e.printStackTrace();
			}
			
		}
		System.out.println("Server Listening Stopped");
	}
	
		
	private void dispatchCommands()
	{
		if(isRunning())
		{
			dispatching = true;
			String command = null;
			while(isRunning() && !queue.isEmpty())
			{
				command = queue.removeFirst();
				System.out.println("Handling: "+command);
				spMediaRemoteController.updateCommandRecivedLabel(command);
				switch(command)
				{
					case "close":
						this.stopServerListening();
						System.exit(0);
						break;
						
					case "stop":
						this.stopServerListening();
						if(this.spMediaRemoteController != null)
						{
							spMediaRemoteController.onStopReceived();
						}
						break;
						
					default:
						performer.execute(command);
						break;
				}
			}
			if(!queue.isEmpty())
			{
				queue.clear();
			}
			dispatching = false;
		}
	}
	

	@Override
	public void stopServerListening()
	{
		setStatus(ServerStatus.NOT_RUNNING);
		onStop();
		
	}
	
	private void onStop()
	{
		if(inputConnection != null)
		{
			try
			{
				inputConnection.close();
			}
			catch (IOException e)
			{
				
				System.out.println("ServerSocket Closed");
			}
		}
		if(serverSocket != null)
		{	
			try
			{
				serverSocket.close();
			}
			catch (IOException e)
			{
				SPUtils.printError("src = onStop, serverSocketCloseException");
				e.printStackTrace();
			}
		}
		if(!queue.isEmpty())
		{
			queue.clear();
		}
	}

	@Override
	public void onCommandReceived(String command)
	{
		queue.addLast(command.trim().toLowerCase());
		if(!dispatching)
		{
			dispatchCommands();
		}
		
	}

}
