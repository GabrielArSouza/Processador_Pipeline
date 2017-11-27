package controle;

public class Canal 
{
	private boolean status;
	private Signal dado;
	
	/**
	 * Construtor para o objeto
	 */
	public Canal ()
	{
		status = false;
		dado = new Signal();
	}
	
	/**
	 * Pega o status atual do canal
	 * @return true se canal ocupado, falso caso
	 * canal esteja livre.
	 */
	public boolean getStatus() {
		return status;
	}
	
	/**
	 * Escreve um dado no canal e ativa o status dele
	 * para ocupado ( status == true ), caso o canal
	 * já esteja ocupado, nada é feito.
	 * @param dado o sinal enviado para o canal
	 */
	public void write( Signal dado )
	{
		if ( this.status == false )
		{
			status = true;
			this.dado = dado;
		}
	}
	
	/**
	 * Ler o dado presente no canal e muda seu
	 * status para livre. 
	 * @return o dado armazenado no canal
	 */
	public Signal read()
	{
		this.status = false;
		return dado;
	}
	
}