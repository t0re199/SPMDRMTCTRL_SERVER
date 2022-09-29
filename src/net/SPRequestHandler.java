package net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import utils.SPUtils;

public class SPRequestHandler implements Runnable
{
	private Socket clientSocket;
	private SPCommandReceiver spCommandReceiver;
	
	
	public SPRequestHandler(SPCommandReceiver spCommandReceiver, Socket clientSocket)
	{
		if(spCommandReceiver == null || clientSocket == null)
		{
			throw new IllegalArgumentException();
		}
		this.spCommandReceiver = spCommandReceiver;
		this.clientSocket = clientSocket;
	}

	@Override
	public void run()
	{
		handleRequest();
	}
	
	private void handleRequest()
	{
		InputStreamReader socketInputStreamReader = null;
		BufferedReader socketBufferedReader = null;
		
		String command = "";
		try
		{
			socketInputStreamReader = new InputStreamReader(clientSocket.getInputStream());
			socketBufferedReader = new BufferedReader(socketInputStreamReader);
			command = extractCommand(socketBufferedReader);
		}
		catch (IOException e)
		{
			SPUtils.printError("src = onRequestReceived, IOException");
			e.printStackTrace();
		}
		finally
		{
			if(socketInputStreamReader != null)
			{
				try
				{
					socketInputStreamReader.close();
				}
				catch (IOException e)
				{
					SPUtils.printError("src = onRequestReceived, Finally");
					e.printStackTrace();
				}
			}
			if(socketBufferedReader != null)
			{
				try
				{
					socketBufferedReader.close();
				}
				catch (IOException e)
				{
					SPUtils.printError("src = onRequestReceived, Finally");
					e.printStackTrace();
				}
			}
		}
		spCommandReceiver.onCommandReceived(command);
		
	}
	
	private String extractCommand(BufferedReader socketBufferedReader)
	{
		String data = "";
		String read = null;
		while(true)
		{
			try
			{
				read = socketBufferedReader.readLine();
				if(read != null)
				{
					data += read;
				}
				else
				{
					break;
				}
			}
			catch (IOException e)
			{
				data = null;
				SPUtils.printError("Unable to exetract command");
				e.printStackTrace();
				break;
			}
		}
		return data;
	}

}
