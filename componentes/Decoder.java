package componentes;

import controle.Port;
import controle.Signal;

/*
 * Classe referente a um componente decodificar
 * Decodifica as instrucoes vindas da memoria de instrucoes
 */
public class Decoder {

	
	String[] inst;
	
	RegistersGroup regsGroup;
	
	public Decoder( RegistersGroup regsGroup ){
		
		this.regsGroup = regsGroup;
		
	}
	
	
	// funcao com logica de execucao do componente (decodificacao)
	public void execute( String instrucao ){
	
	
		System.out.println(">>> Iniciando decodificacao");
		
		// separando instrucao pelo formato
		if ( inst == null ) return;
			 
		inst = instrucao.split(" ");
		
		
		// interpretacao das instrucoes
		switch (inst[0]) {
			
			case "add":
				instAdd();
				break;
			case "sub":
				instSub();
				break;
			case "and":
				instAnd();
				break;
			case "or":
				instOr();
				break;
			case "xor":
				instXor();
				break;
			case "not":
				instNot();
				break;
			case "lw":
				instLw();
				break;
			case "st":
				instSt();
				break;
			default:
				break;
				
		}
		
		
	}
	
	
	/*
	 * Implementacao da logica e procedimentos do opcode "ADD"
	 */
	public void instAdd(){
		
		System.out.println(">>> A instrucao atual é um ADD");
		
		// identifica registrador destino
		String regDestino = inst[1];
		regsGroup.regDestino = regDestino;
		System.out.println(">>> Registrador destino: " +inst[1] );
		
		// identifica registradores fonte
		int indiceOp1 = Integer.parseInt(inst[1].substring(1));
		int indiceOp2 = Integer.parseInt(inst[2].substring(1));
		
		String op1 = regsGroup.registers[indiceOp1].data;
		String op2 = regsGroup.registers[indiceOp2].data;
		System.out.println(">>> Primeiro registrador fonte: " +inst[1] );
		System.out.println(">>> Primeiro operando fonte: " + op1);
		System.out.println(">>> Segundo registrador fonte: " +inst[2] );
		System.out.println(">>> Segundo operando fonte: " + op2);
		
		Signal signalOp1 = new Signal(op1);
		Signal signalOp2 = new Signal(op2);
		Signal signal3 = new Signal(inst[0]);
		
		System.out.println(">>> Envia dados para ULA");
		// diz que quer enviar algo por esse sinal
		regsGroup.input1.setEvent(false);
		signalOp1.setEvent(true);
		signalOp2.setEvent(true);
		signal3.setEvent(true);
		regsGroup.output1.setSignal(signalOp1);
		regsGroup.output1.write();
		regsGroup.output2.setSignal(signalOp2);
		regsGroup.output2.write();
		// escreve tipo da operação
		regsGroup.output5.setSignal(signal3);
		regsGroup.output3.write();
		
	}
	
	/*
	 * Implementacao da logica e procedimentos do opcode "SUB"
	 */
	public void instSub(){
		
		System.out.println(">>> A instrucao atual é um SUB");

		// stub
		
	}
	
	/*
	 * Implementacao da logica e procedimentos do opcode "OR"
	 */
	public void instOr(){
		
		System.out.println(">>> A instrucao atual é um OR");

		// stub
		
	}
	
	/*
	 * Implementacao da logica e procedimentos do opcode "AND"
	 */
	public void instAnd(){
		
		System.out.println(">>> A instrucao atual é um AND");


		
	}
	
	/*
	 * Implementacao da logica e procedimentos do opcode "XOR"
	 */
	public void instXor(){
		
		System.out.println(">>> A instrucao atual é um XOR");

		// stub
		
	}
	
	/*
	 * Implementacao da logica e procedimentos do opcode "NOT"
	 */
	public void instNot(){
		
		System.out.println(">>> A instrucao atual é um NOT");

		// stub
		
	}
	
	/*
	 * Implementacao da logica e procedimentos do opcode "CMP"
	 */
	public void instCmp(){
		
		System.out.println(">>> A instrucao atual é um CMP");

		// stub
		
	}
	
	/*
	 * Implementacao da logica e procedimentos do opcode "LW" - "Leitura em memoria"
	 */
	public void instLw(){
		
		System.out.println(">>> A instrucao atual é um LW");
		
		// identifica registrador destino
		String regDestino = inst[1];
		regsGroup.regDestino = regDestino;
		System.out.println(">>> Registrador destino: " +inst[1] );
		
		// identifica endereco de memoria (fonte)
		int endMemFonte = Integer.parseInt(inst[2].substring(1));
		System.out.println(">>> Endereco de memoria (fonte): " +inst[2]);
		
		Signal signal = new Signal(endMemFonte+"");
		System.out.println(">>> Endereco de instrucao sendo buscado em dataMemory pelo sinal");
		// diz que quer enviar algo por esse sinal
		regsGroup.input1.setEvent(false);
		signal.setEvent(true);
		regsGroup.output3.setSignal(signal);
		regsGroup.output3.write();
	}
	
	/*
	 * Implementacao da logica e procedimentos do opcode "ST" - "Armazenamento em memoria"
	 */
	public void instSt(){
		
		System.out.println("A instrucao atual é um ST");
		
		// stub
		
	}

		
}
