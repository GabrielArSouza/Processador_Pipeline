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
	
	
	public void loadMemory(  ){
		
		instrucoes[1] = "add R4, 3, R4, 0";
		instrucoes[2] = "add R4, R3, R4, 0";
		
		// carregar com mais instrucoes
		
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
