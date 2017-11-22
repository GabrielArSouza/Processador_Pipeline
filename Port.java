public class Port 
{
	private Signal signal;
	
	/**
	 * Construtor para o objeto
	 * @param signal o dado presente na porta
	 */
	public Port (Signal signal)
	{
		this.signal = signal;
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
	 * Seta o evento do sinal
	 * @param event
	 */
	public void setEvent(boolean event)
	{
		signal.setEvent(event);
	}
	
	/**
	 * Ler o dado presente no canal que conecta
	 * a porta.
	 */
	public void read( Canal canal )
	{
		signal = canal.read();
	}
	
	/**
	 * Escreve o seu dado em um canal que está
	 * conectada.
	 */
	public void write( Canal canal )
	{
		canal.write(signal);
	}
		
}