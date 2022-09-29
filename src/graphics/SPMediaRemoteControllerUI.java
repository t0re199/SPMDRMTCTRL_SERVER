package graphics;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingWorker;

import net.SPCommandReceiver;

public class SPMediaRemoteControllerUI extends JFrame
{
	private static final long serialVersionUID = 100007L;
	private JButton buttonStartStopServer;
	private JLabel commandRecivedLabel;
	private SPCommandReceiver spCommandReceiver;
	
	public SPMediaRemoteControllerUI() 
	{
		getContentPane().setLayout(new BorderLayout(0, 0));
		buttonStartStopServer = new JButton("START");
		buttonStartStopServer.setBackground(Color.GREEN);
		buttonStartStopServer.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				if(buttonStartStopServer.getText().equals("START"))
				{
					setCommandLabelVisibility(true);
					buttonStartStopServer.setText("STOP");
					buttonStartStopServer.setBackground(Color.RED);
					new SwingWorker<Void, Void>() 
					{ 
						@Override
						protected Void doInBackground()
						{
							if(spCommandReceiver == null)
							{
								spCommandReceiver = new SPCommandReceiver(2905, SPMediaRemoteControllerUI.this);
							}
							try
							{
								new Thread(spCommandReceiver).start();
							}
							catch (Throwable e)
							{
								System.out.println("Thread Forced to Stop.");
							}
					        return null;
						}
					}.execute();
				}
				else
				{
					onStopReceived();
				}
			}
		});
		getContentPane().add(buttonStartStopServer, BorderLayout.CENTER);
		
		commandRecivedLabel = new JLabel("Last Command: ");
		getContentPane().add(commandRecivedLabel, BorderLayout.SOUTH);
		setCommandLabelVisibility(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("SPMediaRemoteController Server");
		
	}
	
	public void updateCommandRecivedLabel(String cmd)
	{
		commandRecivedLabel.setText("Dispatched Command: "+cmd);
	}
	
	private void setCommandLabelVisibility(boolean flag)
	{
		commandRecivedLabel.setVisible(flag);
	}
	
	public void onStopReceived()
	{
		buttonStartStopServer.setText("START");
		buttonStartStopServer.setBackground(Color.GREEN);
		
		spCommandReceiver.stopServerListening();
		commandRecivedLabel.setText("");
		setCommandLabelVisibility(false);
	}
}
