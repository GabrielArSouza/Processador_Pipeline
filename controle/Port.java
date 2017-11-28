package controle;

public class Port 
{
	private Signal signal;
	private Canal canal;
	
	/**
	 * Construtor para o objeto
	 * @param signal o dado presente na porta
	 */
	public Port (Signal signal)
	{
		this.signal = signal;
	}
	
	/**
	 * Construtor de porta recebendo o canal
	 * associado a ela
	 * @param canal  o canal
	 */
	public Port (Canal canal)
	{
		this.signal = null;
		this.canal = canal;
	}
	
	/**
	 * Pega o dado presente na porta
	 * @return o dado da porta
	 */
	public Signal getSignal() {
		return signal;
	}

	/**
	 * Atribui um novo dado para a porta
	 * @param signal o dado
	 */
	public void setSignal(Signal signal) {
		this.signal = signal;
	}
	
	/**
	 * Pega o status do evento associado a porta*
	 * @return o evento true ou false
	 */
	public boolean getEvent(){
		return signal.isEvent();
	}
	
	/**
	 * Seta o evento do sinal
	 * @param event
	 */
	public void setEvent(boolean event)
	{
		signal.setEvent(event);
	}
	
	/**
	 * @return O canal associado a porta
	 */
	public Canal getCanal()
	{
		return canal;
	}

	/**
	 * Atribui um canal a porta
	 * @param canal  o canal
	 */
	public void setCanal(Canal canal)
	{
		this.canal = canal;
	}

	/**
	 * Ler o dado presente no canal que conecta
	 * a porta.
	 */
	public Signal read( )
	{
		if ( canal != null )
			signal = canal.read();
		return signal;		
	}
	
	/**
	 * Escreve o seu dado em um canal que est�
	 * conectada.
	 */
	public void write( )
	{
		if (canal != null)
		{
			canal.write(signal);
			this.signal = null;
		}
	}
		
}