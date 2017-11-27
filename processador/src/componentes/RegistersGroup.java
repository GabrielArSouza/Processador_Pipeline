package componentes;

import controle.Port;

/*
 * Classe referente ao "Banco de Registradores"
 * Contem todos os registradores da arquitetura
 */
public class RegistersGroup {

	// Portas de entrada e saida do componente
	Port input1, input2, output1, output2;

	// vetor de registradores
	private Register[] registers;
	
	
	// construtor
	public RegistersGroup( Port input1, Port input2, Port output1, Port output2 ){
		
		this.input1 = input1;
		this.input2 = input2;
		this.output1 = output1;
		this.output2 = output2;
		
		registers = new Register[32]; // decisao de projeto
		
	}
	
	
	// funcao com logica de execucao do componente
	public void execute(){
		
		// verifica se ha sinal na porta de entrada 1
		if( input1.getSignal().isEvent() == true ){
			
			// recupera a informacao armazenada no sinal
			String instrucao = input1.getSignal().read();
			System.out.println(">>> Registers Group com sinal de entrada.");
			
			// chama decoder para decodificar instrucao recebida
			Decoder dec = new Decoder();
			String[] operacao = dec.execute(instrucao);
			
			
			
			
			
		}
		
	}
	
	
}
