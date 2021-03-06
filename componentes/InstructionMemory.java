package componentes;

import javax.swing.plaf.synth.SynthSeparatorUI;

import controle.Port;
import controle.Signal;

/*
 * Classe referente ao componente "Memoria de Instru�ao"
 * Contem as instrucoes armazenadas no sistema, que estao na "fila" para serem executadas
 */
public class InstructionMemory {

	// portas de entrada e saida
	Port input, output;
	
	// indica instrucao a ser lida
	private int contadorPrograma; 
	
	// vetor com instrucoes
	private String[] instrucoes;
	
	
	// Recebe a instrucao a ser lida
	public InstructionMemory( Port input, Port output ){
		this.input = input;
		this.output = output;
		instrucoes = new String[1000]; // definicao do projeto
	}
	
	
	public int loadMemory(  ){
		
		instrucoes[1] = "lw R1 $1 0 0";
		instrucoes[2] = "lw R2 $2 0 0";
		instrucoes[3] = "lw R4 $3 0 0";
		instrucoes[4] = "lw R5 $4 0 0";
		instrucoes[5] = "lw R6 $5 0 0";
		instrucoes[6] = "add R3 R1 R2 0";
		instrucoes[7] = "sub R7 R5 R4 0";
		instrucoes[8] = "st $6 R3 0 0";
		
		return 8;
		
	}
	

	// funcao com logica de execucao do componente
	public void execute(  ){
	
		// confirma se ha sinal na porta de entrada
		Signal signal = input.getSignal();
		if( signal != null && signal.isEvent() == true ) {
			// recupera a informacao armazenada no sinal
			int endInstrucao = Integer.parseInt(signal.read());
			System.out.println(">>> Instruction Memory com sinal de entrada.");
			// recupera a instrucao atual pelo endereco
			String currentInstruction = instrucoes[endInstrucao];
			System.out.println(">>> Proxima instrucao selecionada");
			System.out.println(">>> Prox instrucao: "+currentInstruction);
			
			// escreve dado no sinal
			signal.write(currentInstruction);
			// diz que quer enviar algo por esse sinal
			input.setSignal(null);
			signal.setEvent(true);
			output.setSignal(signal);
			// enviar sinal para o canal
			output.write();
			
			System.out.println(">>> Instrucao enviada pelo sinal");
			
		}else
			System.out.println("dado errado\n");
		
	}

	
}
