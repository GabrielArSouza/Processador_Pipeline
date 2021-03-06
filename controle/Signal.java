package controle;

public class Signal 
{
	private String data;
	private boolean event;

	public Signal ()
	{
		event = false;
	}
	
	public Signal ( boolean event)
	{
		this.event = event;
	}
	
	public Signal ( String data)
	{
		this.event = true;
		this.data = data;
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
	 * Escreve dado no sinal
	 */
	public void write( String data )
	{
		this.data = data;
	}
	
	/**
	 * le dado do sinal
	 */
	public String read ()
	{
		return data;
	}
}