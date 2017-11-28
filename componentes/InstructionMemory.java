package componentes;

import javax.swing.plaf.synth.SynthSeparatorUI;

import controle.Port;

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
	
	
	public void loadMemory(  ){
		
		instrucoes[1] = "lw R1 $3 0 0";
		instrucoes[2] = "lw R2 $4 0 0";
		instrucoes[3] = "lw R4 $1 0 0";
		instrucoes[4] = "add R3 R1 R2 0";
		instrucoes[5] = "sw $6 R3 0 0";
		
	}
	

	// funcao com logica de execucao do componente
	public void execute(  ){
	
		// confirma se ha sinal na porta de entrada
		if( input.getSignal().isEvent() == true ){
			
			// recupera a informacao armazenada no sinal
			int endInstrucao = Integer.parseInt(input.getSignal().read());
			System.out.println(">>> Instruction Memory com sinal de entrada.");
			// recupera a instrucao atual pelo endereco
			String currentInstruction = instrucoes[endInstrucao];
			System.out.println(">>> Proxima instrucao selecionada");
			System.out.println(">>> Prox instrucao: "+currentInstruction);
			
			// escreve dado no sinal
			output.getSignal().write(currentInstruction);
			// diz que quer enviar algo por esse sinal
			input.setEvent(false);
			output.setEvent(true);
			
			System.out.println(">>> Instrucao enviada pelo sinal");
			
		}
		
	}

	
}
