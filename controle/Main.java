package controle;

import componentes.Alu;
import componentes.DataMemory;
import componentes.Decoder;
import componentes.InstructionMemory;
import componentes.Pc;
import componentes.RegistersGroup;

public class Main {

	public static void main(String[] args) {


		/*
		 * CRIANDO SINAIS
		 */
		// sinal entre PC e INSTRUCTION_MEMORY
		Signal signal_pc_instMemory = new Signal(); 
		// sinal entre INSTRUCTION_MEMORY e DECODER
		Signal signal_instMemory_regsGroup = new Signal(); 
		// sinal entre REGISTERS_GROUP e ULA (PORTA 1 DA ULA)
		Signal signal_regsGroup_ula_1 = new Signal(); 
		// sinal entre REGISTERS_GROUP e ULA (PORTA 2 DA ULA)
		Signal signal_regsGroup_ula_2 = new Signal(); 
		// sinal entre REGISTERS_GROUP e DATA_MEMORY
		Signal signal_regsGroup_dataMemory = new Signal(); 
		// sinal entre ULA e DATA_MEMORY
		Signal signal_ula_dataMemory = new Signal(); 
		// sinal entre ULA e REGISTERS_GROUP
		Signal signal_ula_regsGroup = new Signal(); 							
		// sinal entre DATA_MEMORY e REGISTERS_GROUP
		Signal signal_dataMemory_regsGroup = new Signal(); 
													
		
		/*
		 * CRIANDO PORTAS
		 */
		// conecta a sa�da de PC na entrada de INSTRUCTION_MEMORY
		Port output_pc  = new Port(signal_pc_instMemory);
		Port input_instMemory = new Port(signal_pc_instMemory);
		
		// conecta a sa�da de INSTRUCTION_MEMORY na entrada de REGISTERS_GROUP
		Port output_instMemory  = new Port(signal_instMemory_regsGroup);
		Port input_1_regsGroup = new Port(signal_instMemory_regsGroup);
				
	
		// conecta a sa�da de REGISTERS_GROUP na entrada de ALU
		Port output_1_regsGroup = new Port(signal_regsGroup_ula_1);
		Port output_2_regsGroup = new Port(signal_regsGroup_ula_2);
		Port input_1_ula = new Port(signal_regsGroup_ula_1);
		Port input_2_ula = new Port(signal_regsGroup_ula_2);
		
		// conecta a sa�da de REGISTERS_GROUP na entrada de DATA_MEMORY
		Port output_3_regsGroup  = new Port(signal_regsGroup_dataMemory);
		Port input_1_dataMemory = new Port(signal_regsGroup_dataMemory);
		
		// conecta a sa�da de ULA na entrada de DATA_MEMORY
		Port output_1_ula  = new Port(signal_ula_dataMemory);
		Port input_2_dataMemory = new Port(signal_ula_dataMemory);
					
		// conecta a sa�da de ULA na entrada de REGISTERS_GROUP
		Port output_2_ula  = new Port(signal_ula_regsGroup);
		Port input_2_regsGroup = new Port(signal_ula_regsGroup);
		
		// conecta a saida de DataMemory com entrada de RegsGroup
		Port output_1_dataMemory = new Port(signal_dataMemory_regsGroup);
		Port input_3_regsGroup = new Port(signal_dataMemory_regsGroup);
		
		// pra facilidar execucao
		Signal initial_teste = new Signal();
		Port input_pc = new Port(initial_teste);
		Port output_2_dataMemory = new Port(initial_teste);
		Port output_4_regsGroup = new Port(initial_teste);
		
		/*
		 * INSTANCIANDO TODOS OS COMPONENTES
		 */
		// instancia componente pc
		Pc pc = new Pc( input_pc, output_pc );
		// instancia componente InstructionMemory
		InstructionMemory instMemory = new InstructionMemory( input_instMemory, output_instMemory );
		// instancia componente registerGroup
		RegistersGroup regsGroup = new RegistersGroup( input_1_regsGroup, input_2_regsGroup,  input_3_regsGroup, output_1_regsGroup, output_2_regsGroup, output_3_regsGroup, output_4_regsGroup );
		// instancia componente ALU
		Alu ula = new Alu( input_1_ula, input_2_ula, output_1_ula, output_2_ula );
		// instancia componente DataMemory
		DataMemory dataMemory = new DataMemory(input_1_dataMemory, input_2_dataMemory, output_1_dataMemory, output_2_dataMemory);
								
		
		/*
		 * EXECUCAO COM MECANISMO DE SIMULACAO DEDICADO
		 */
		// INICIALMENTE, SET O SINAL DA ENTRADA DE PC 
		input_pc.setEvent(true);
		// CARREGA MEMORIA DE INSTRUCOES
		instMemory.loadMemory();
		// CARREGA MEMORIA DE DADOS
		dataMemory.loadMemory();
		
		// SIMULA CICLO DE CLOCK COM O WHILE, ENQUANTO NAO TIVER SAIDA PELA MEMORIA DE DADOS
		do{
			
			// A CADA CICLO DE CLOCK FICAR VERIFICANDO SE CADA PORTA DESSAS TEM UM SINAL
			if( input_pc.getEvent() == true ){
				pc.execute();
			} else if( input_instMemory.getEvent() == true ){
				instMemory.execute();
			} else if( input_1_regsGroup.getEvent() == true ){
				regsGroup.execute();
			} else if( input_1_dataMemory.getEvent() == true ){
				dataMemory.execute();
			} else if( input_3_regsGroup.getEvent() == true ){
				regsGroup.execute();
				output_2_dataMemory.setEvent(true); // parar simulacao
			}
			
			
		} while( !(output_2_dataMemory.getEvent()) );
		
		
		
		// QUANDO TIVER SINAL, CHAMA FUNCAO DE EXECUTAR
		// EXECUTAR RETORNA SEMPRE UM SINAL OU VALOR PROX PROXIMO CICLO

		
	}

}
