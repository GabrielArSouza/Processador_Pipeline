package componentes;

import controle.Port;
import controle.Signal;

/*
 * Classe referente ao "Banco de Registradores"
 * Contem todos os registradores da arquitetura
 */
public class RegistersGroup {

	// Portas de entrada e saida do componente
	Port input1; // dado que vem da InstructionMemory
	Port input2; // dado que vem da ULA
	Port input3; // dado que vem do DataMemory
	Port output1, output2; // dados que vao para ULA
	Port output3; // dado que vai para DataMemory
	Port output4; // manda sinal para PC
	
	// vetor de registradores
	Register[] registers;

	String regDestino; // registrador destino (auxilio para LW)
		
	// construtor
	public RegistersGroup( Port input1, Port input2, Port input3, Port output1, Port output2, Port output3, Port output4 ){
		
		this.input1 = input1;
		this.input2 = input2;
		this.input3 = input3;
		this.output1 = output1;
		this.output2 = output2;
		this.output3 = output3;
		this.output4 = output4;
		
		registers = new Register[32]; // decisao de projeto
		implantarRegistradores();
	}
	
	public void implantarRegistradores()
	{
		for ( int i=0; i< registers.length; i++)
			registers[i] = new Register();
	}
	
	// funcao com logica de execucao do componente
	public void execute(){
		
		// verifica se ha sinal na porta de entrada 1
		Signal signal = input1.getSignal();
		if( signal != null && signal.isEvent() == true )
		{
			// recupera a informacao armazenada no sinal
			String instrucao = input1.getSignal().read();
			System.out.println(">>> Registers Group com sinal de entrada.");
			
			// chama decoder para decodificar instrucao recebida
			Decoder dec = new Decoder( this );
			dec.execute(instrucao);

		}
		
		// verifica se ha sinal na porta de entrada 3
		signal = input3.getSignal();
		if( signal != null && signal.isEvent() == true ){
			String valorDataMem = input3.getSignal().read();
			System.out.println(">>> Chegou dado "+valorDataMem);
			
			System.out.println(">>> Colocando dado no registrador indicado");
			System.out.println(">>> Registrador indicado: "+regDestino);
			int indexRegDestino = Integer.parseInt(regDestino.substring(1));
			Register reg = registers[indexRegDestino];
			reg.write(valorDataMem);
			System.out.println(">>> Dado escrito no registrador destino com index "+indexRegDestino );
			
			// busca proxima instrucao - envia sinal pra PC
			input3.setEvent(false);
			signal.setEvent(true);
			output4.setSignal(signal);
			
		}
		
	}
	
	
}
