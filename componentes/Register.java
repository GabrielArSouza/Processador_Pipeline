package componentes;

import controle.Port;
import controle.Signal;

/*
 * Classe referente ao componente "Registrador"
 * Antes que a instru��o seja interpretada e as unidades da CPU sejam acionadas, 
 * o processador necessita buscar a instrucao de onde ela estiver armazenada (memoria) 
 * e armazena-la em seu pr�prio interior, em um dispositivo de mem�ria denominado 
 * registrador de instru��o
 */
public class Register {

	String data;
	
	public Register() {
		data = "";
	}
	
	public void write( String data ){
		this.data = data;
	}
	
	public String read(){
		return data;
	}
	
	
}
