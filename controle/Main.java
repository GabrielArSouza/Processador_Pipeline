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
		// canal entre PC e INSTRUCTION_MEMORY
		Canal canal_pc_instMemory = new Canal(); 
		// sinal entre INSTRUCTION_MEMORY e DECODER
		Canal canal_instMemory_regsGroup = new Canal(); 
		// sinal entre REGISTERS_GROUP e ULA (PORTA 1 DA ULA)
		Canal canal_regsGroup_ula_1 = new Canal(); 
		// sinal entre REGISTERS_GROUP e ULA (PORTA 2 DA ULA)
		Canal canal_regsGroup_ula_2 = new Canal(); 
		// sinal entre REGISTERS_GROUP e ULA (PORTA 3 DA ULA)
		Canal canal_regsGroup_ula_3 = new Canal(); 
		// sinal entre REGISTERS_GROUP e DATA_MEMORY
		Canal canal_regsGroup_dataMemory = new Canal(); 
		// sinal entre ULA e DATA_MEMORY
		Canal canal_ula_dataMemory = new Canal(); 
		// sinal entre ULA e REGISTERS_GROUP
		Canal canal_ula_regsGroup = new Canal(); 
										
		// sinal entre DATA_MEMORY e REGISTERS_GROUP
		Canal canal_dataMemory_regsGroup = new Canal();
		
		/*
		 * CRIANDO PORTAS
		 */
		// conecta a sa�da de PC na entrada de INSTRUCTION_MEMORY
		Port output_pc  = new Port(canal_pc_instMemory);
		Port input_instMemory = new Port(canal_pc_instMemory);
		
		// conecta a sa�da de INSTRUCTION_MEMORY na entrada de REGISTERS_GROUP
		Port output_instMemory  = new Port(canal_instMemory_regsGroup);
		Port input_1_regsGroup = new Port(canal_instMemory_regsGroup);
				
	
		// conecta a sa�da de REGISTERS_GROUP na entrada de ALU
		Port output_1_regsGroup = new Port(canal_regsGroup_ula_1); //operando
		Port output_2_regsGroup = new Port(canal_regsGroup_ula_2); //operando
		Port output_5_regsGroup  = new Port(canal_regsGroup_ula_3); //tipo operação
		Port input_1_ula = new Port(canal_regsGroup_ula_1);
		Port input_2_ula = new Port(canal_regsGroup_ula_2);
		Port input_3_ula = new Port(canal_regsGroup_ula_3); //operação
		
		// conecta a sa�da de REGISTERS_GROUP na entrada de DATA_MEMORY
		Port output_3_regsGroup  = new Port(canal_regsGroup_dataMemory);
		Port input_1_dataMemory = new Port(canal_regsGroup_dataMemory);
		
		// conecta a sa�da de ULA na entrada de DATA_MEMORY
		Port output_1_ula  = new Port(canal_ula_dataMemory);
		Port input_2_dataMemory = new Port(canal_ula_dataMemory);
					
		// conecta a sa�da de ULA na entrada de REGISTERS_GROUP
		Port output_2_ula  = new Port(canal_ula_regsGroup);
		Port input_2_regsGroup = new Port(canal_ula_regsGroup);
	
		// conecta a saida de DataMemory com entrada de RegsGroup
		Port output_1_dataMemory = new Port(canal_dataMemory_regsGroup);
		Port input_3_regsGroup = new Port(canal_dataMemory_regsGroup);
		
		
		// pra facilidar execucao
		Signal initial_teste = new Signal(true);
		Port input_pc = new Port(new Canal(initial_teste));
		Port output_dataMemory = new Port(new Canal(new Signal(false)));
		
		// pra facilidar execucao
		Port output_2_dataMemory = new Port(new Canal(initial_teste));
		Port output_4_regsGroup = new Port(new Canal(initial_teste));
		
		/*
		 * INSTANCIANDO TODOS OS COMPONENTES
		 */
		// instancia componente pc
		Pc pc = new Pc( input_pc, output_pc );
		// instancia componente InstructionMemory
		InstructionMemory instMemory = new InstructionMemory( input_instMemory, output_instMemory );
		// instancia componente registerGroup
		RegistersGroup regsGroup = new RegistersGroup( input_1_regsGroup, input_2_regsGroup,  input_3_regsGroup, output_1_regsGroup, output_2_regsGroup, output_3_regsGroup, output_4_regsGroup, output_5_regsGroup );
		// instancia componente ALU
		Alu ula = new Alu( input_1_ula, input_2_ula, input_3_ula, output_1_ula, output_2_ula );
		// instancia componente DataMemory
		DataMemory dataMemory = new DataMemory(input_1_dataMemory, input_2_dataMemory, output_1_dataMemory, output_2_dataMemory);
								
		
		/*
		 * EXECUCAO COM MECANISMO DE SIMULACAO DEDICADO
		 */
		
		// CARREGA MEMORIA DE INSTRUCOES
		instMemory.loadMemory();
		// CARREGA MEMORIA DE DADOS
		dataMemory.loadMemory();
		
		//carregar controlador de ciclo
		output_2_dataMemory.read();
		// SIMULA CICLO DE CLOCK COM O WHILE, ENQUANTO NAO TIVER SAIDA PELA MEMORIA DE DADOS
		do{
			
			// A CADA CICLO DE CLOCK FICAR VERIFICANDO SE CADA PORTA DESSAS TEM UM SINAL
			if( input_pc.read() != null )
			{
				System.out.println("input_pc.read() ok");
				pc.execute();
			}
			else if( input_instMemory.read() != null && input_instMemory.getEvent() )
			{
				System.out.println("Chamou o instMem\n");
				instMemory.execute();
				//output_2_dataMemory.setEvent(false);
			}
			else if ( input_1_regsGroup.read() != null && input_1_regsGroup.getEvent())
			{
				System.out.println("Chamou o regsGroup\n");
				regsGroup.execute();
				output_2_dataMemory.setEvent(true);
			}
			else if (input_1_dataMemory.read() != null && input_1_dataMemory.getEvent())
			{
				System.out.println("Chamou o dataMemory\n");
				dataMemory.execute();
			}
			else if (input_3_regsGroup.read() != null && input_3_regsGroup.getEvent())
			{
				System.out.println("chamou o regsGroups input 3\n");
				regsGroup.execute();
				output_2_dataMemory.setEvent(false); // parar simulacao
			}				
			
		} while( output_2_dataMemory.getEvent());
	
		
		System.out.println("Terminou");
		// QUANDO TIVER SINAL, CHAMA FUNCAO DE EXECUTAR
		// EXECUTAR RETORNA SEMPRE UM SINAL OU VALOR PROX PROXIMO CICLO

		
	}

}
