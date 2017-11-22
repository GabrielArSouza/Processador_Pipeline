public class Signal 
{
	private int data;
	private boolean event;

	public Signal ()
	{
		event = false;
	}
	
	/**
	 * Retorna o evento do sinal
	 * @return true ou false;
	 */
	public boolean isEvent() 
	{
		return event;
	}
	
	/**
	 * Muda o evento do sinal
	 * @param event
	 */
	public void setEvent( boolean event) 
	{
		this.event = event;
	}
	
	/**
	 * Atribui um dado para o sinal
	 */
	public void read( int signal )
	{
		data = signal;
	}
	
	/**
	 * retorna o dado do sinal
	 */
	public int write ()
	{
		return data;
	}
}