package componentes;

import controle.Canal;
import controle.Port;
import controle.Signal;

/*
 * Classe referente ao componente "Contador de Programa"
 * Contador de programa � o respons�vel por conter o endere�o da pr�xima instru��o
 * a ser buscada na mem�ria para execu��o
 */
public class Pc {

	// Portas de conexao
	Port input, output;
	
	// Valor de instrucao do contador
	int endProxInst;
	
	
	// construtor
	public Pc( Port input, Port output ){
		this.input = input;
		this.output = output;
		endProxInst = 0;
	}
	
	
	// funcao com logica de execucao do componente
	public void execute(  ){
		
		// confirma se ha sinal na porta de entrada
		Signal signal = input.getSignal();
		if( signal != null && signal.isEvent() ){
			// muda para o prox endereco de instrucao
			endProxInst += 1;
			System.out.println(">>> PC com sinal de entrada. Prox endereco de instrucao selecionado.");
			System.out.println(">>> Proximo endereco: "+endProxInst);
			// escreve dado no sinal
			signal.write(endProxInst+"");
			System.out.println(">>> Endereco de instrucao enviado pelo sinal");
			
			// diz que quer enviar algo por essa porta
			input.setSignal(null);
			signal.setEvent(true);
			output.setSignal(signal);
			
			//escrever no canal
			output.write();
			System.out.println(">>> Escreveu sinal alterado no canal\n");
		}
		
	}
	
	public int getProxInst()
	{
		return endProxInst;
	}
	
}
