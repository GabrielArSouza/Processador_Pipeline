package componentes;

import controle.Port;
import controle.Signal;

/*
 * Classe referente ao componente "Memoria de Dados"
 * Contem os dados armazenadas no sistema
 */
public class DataMemory {

	// portas de entrada e saida
	Port input1; // entrada vindo da regsGroup
	Port input2; // entrada vindo do ULA 
	Port input3; // entrada do dado do regsGroup
	Port input4; // entrada da fonte do regsGroup
	Port output1; // saida para regsGroup
	Port output2; // saida para "O ALEM" AQUI PARA A EXECUCAO, MUDAR ISSO AINDA
	
	
	// indica o endereco do dado a ser lido
	private int endDado; 
	
	// vetor com instrucoes
	private int[] dados;
	

	// Recebe a instrucao a ser lida
	public DataMemory( Port input1, Port input2, Port input3, Port input4, Port output1, Port output2 ){
		this.input1 = input1;
		this.input2 = input2;
		this.input3 = input3;
		this.input4 = input4;
		this.output1 = output1;
		this.output2 = output2;
		dados = new int[1000]; // definicao do projeto
	}
	

	public void loadMemory(  ){
		
		dados[1] = 3;
		dados[2] = 4;
		dados[3] = 7;
		dados[4] = 42;
		dados[5] = 67;
		
	}
	
	
	public void executeWrite(  ){
		
		Signal signal3 = input3.getSignal();
		Signal signal4 = input4.getSignal();
		
		if ( signal3 != null && signal4 != null )
		{
			
			// recupera endereço
			int endDado = Integer.parseInt(input4.getSignal().read().substring(1));
			
			//recupera dado
			int dado = Integer.parseInt(input3.getSignal().read());
			
			//escreve dado no endereço
			dados[endDado] = dado;
			System.out.println(">>> Gravando dado " + dado + " no endereço " + endDado);
			input3.setEvent(false);
			input4.setEvent(false);
		}
	}
	
	// funcao com logica de execucao do componente
	public void execute(  ){
	
		// confirma se ha sinal na porta de entrada vindo de regsGroup
		Signal signal = input1.getSignal();
		
		if( signal != null && signal.isEvent() ){
			
			// recupera a informacao armazenada no sinal
			int endDado = Integer.parseInt(input1.getSignal().read());
			System.out.println(">>> Data Memory com sinal de entrada.");
			
			// recupera o dado localizado no endereco
			int dado = dados[endDado];
			System.out.println(">>> Dado selecionado eh = " +dado);
			
			// escreve dado no sinal que vai para regsGroup
			signal.write(dado+"");
			// diz que quer enviar algo por esse sinal
			input1.setEvent(false);
			signal.setEvent(true);
			output1.setSignal(signal);
			output1.write();
			
			System.out.println(">>> Dado enviado para RegsGroup");
			
		}
		
		
	}
	
}














