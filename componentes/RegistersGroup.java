package componentes;

import java.util.ArrayDeque;
import java.util.Deque;

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
	Port output5; // dado que sinaliza operação da ULA
	Port output6; // manda o resultado que deve ser salvo no dataMemory
	Port output7; // manda qual endereço deve ser gravado
	// vetor de registradores
	Register[] registers;

	Deque<String> regDestino = new ArrayDeque<String>();

	
	// construtor
	public RegistersGroup( Port input1, Port input2, Port input3, 
			Port output1, Port output2, Port output3, Port output4, Port output5, Port output6, Port output7 ){
		
		this.input1 = input1;
		this.input2 = input2;
		this.input3 = input3;
		this.output1 = output1;
		this.output2 = output2;
		this.output3 = output3;
		this.output4 = output4;
		this.output5 = output5;
		this.output6 = output6;
		this.output7 = output7;
		
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
		
		// verifica se ha sinal na porta de entrada 3
		Signal signal3 = input3.getSignal();
		
		// verifica se veio dado da ULA
		Signal signal2 = input2.getSignal();
				
		if( signal != null && signal.isEvent() == true )
		{
			// recupera a informacao armazenada no sinal
			String instrucao = input1.getSignal().read();
			System.out.println(">>> Registers Group com sinal de entrada.");
			
			// chama decoder para decodificar instrucao recebida
			Decoder dec = new Decoder( this );
			dec.execute(instrucao);

		}
		
		else if( signal3 != null && signal3.isEvent() == true ) {
			String valorDataMem = input3.getSignal().read();
			System.out.println(">>> Chegou dado no input 3: "+valorDataMem);
			
			System.out.println(">>> Colocando dado no registrador indicado");
			
			String register = regDestino.removeFirst();
			System.out.println(">>> Registrador indicado: "+register);
			int indexRegDestino = Integer.parseInt(register.substring(1));
			Register reg = registers[indexRegDestino];
			reg.write(valorDataMem);
			System.out.println(">>> Dado escrito no registrador destino com index "+indexRegDestino );
			
			// 
			input3.setEvent(false);
			
		}
		else if( signal2 != null && signal2.isEvent()) {
			System.out.println(">>> Chegou dados no input2. Veio da ULA.");
			String resultadoOp = input2.getSignal().read();
			System.out.println(">>> Resultado da operação: " + resultadoOp);
			String register = regDestino.removeFirst();
			System.out.println(">>> Registrador destino: "+register);
			int indexRegDestino = Integer.parseInt(register.substring(1));
			Register reg = registers[indexRegDestino];
			reg.write( resultadoOp );
			System.out.println(">>> Salvou " + resultadoOp + " no registrador de indice " + indexRegDestino);
			
			// 
			input2.setEvent(false);
			
			
		}
		
	}
	
	
}
