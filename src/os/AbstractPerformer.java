package os;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public abstract class AbstractPerformer implements Performer
{
	private static final HashMap<String, Integer> map;
	private static final HashSet<String> commands = new HashSet<>(Arrays.asList("playpause","nexttrack","previoustrack","volumeup","volumedown",
																				"shuffle","repeat","up","down","page_down","page_up","close_program"));
	static
	{
		map = new HashMap<String, Integer>();
		int i = 0;
		for(String command : commands)
		{
			map.put(command, i);
			i++;
		}
	}
		
	@Override
	public int parseCommand(String command)
	{
		Integer i = map.get(command.trim().toLowerCase());
		if(i == null)
		{
			throw new IllegalArgumentException();
		}
		return i;
		
	}
	
	public String commandToString(int command)
	{
		for(String cmd : map.keySet())
		{
			if(map.get(cmd) == command)
			{
				return cmd;
			}
		}
		throw new IllegalArgumentException();
	}

}
