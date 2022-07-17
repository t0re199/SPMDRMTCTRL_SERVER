package os;

public interface Performer
{
	int parseCommand(String command);
	
	String commandToString(int command);
	
	void execute(String command);
	
	void execute(int command);
}
