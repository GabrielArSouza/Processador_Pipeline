package componentes;

import controle.Port;

/*
 * Classe referente ao componente "Memoria de Dados"
 * Contem os dados armazenadas no sistema
 */
public class DataMemory {

	// portas de entrada e saida
	Port input1; // entrada vindo da regsGroup
	Port input2; // entrada vindo do ULA 
	Port output1; // saida para regsGroup
	Port output2; // saida para "O ALEM" AQUI PARA A EXECUCAO, MUDAR ISSO AINDA

	
	// indica o endereco do dado a ser lido
	private int endDado; 
	
	// vetor com instrucoes
	private int[] dados;
	

	// Recebe a instrucao a ser lida
	public DataMemory( Port input1, Port input2, Port output1, Port output2 ){
		this.input1 = input1;
		this.input2 = input2;
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
	
	
	// funcao com logica de execucao do componente
	public void execute(  ){
	
		// confirma se ha sinal na porta de entrada vindo de regsGroup
		if( input1.getSignal().isEvent() == true ){
			
			// recupera a informacao armazenada no sinal
			int endDado = Integer.parseInt(input1.getSignal().read());
			System.out.println(">>> Data Memory com sinal de entrada.");
			// recupera o dado localizado no endereco
			int dado = dados[endDado];
			System.out.println(">>> Dado selecionado eh = " +dado);
			
			// escreve dado no sinal que vai para regsGroup
			output1.getSignal().write(dado+"");
			// diz que quer enviar algo por esse sinal
			input1.setEvent(false);
			output1.setEvent(true);
			
			System.out.println(">>> Dado enviado para RegsGroup");
			
		}
		
	}
	
}
