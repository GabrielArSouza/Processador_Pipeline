package componentes;

import controle.Port;
import controle.Signal;

/*
 * Classe referente ao componente "Unidade Aritm�tica e L�gica - ULA"
 * A ULA realiza as acoes operativas do processador
 * FUNCOES DA ULA
 * - opera��es aritm�ticas com inteiros;
 * - opera��es l�gicas bit a bit AND, NOT, OR, XOR;
 * - opera��es de deslocamento de bits
 */
public class Alu {

	// As entradas para a ULA s�o os dados a serem operados (chamados operandos) 
	// e o c�digo da unidade de controle indicando as opera��es para executar. 
	// As sa�das s�o os resultados da computa��o.

	// Operandos
	Port input1, input2;
	// Tipo de operação
	Port input3;
	// Signal que sinaliza que resultado é igual a 0
	Port output1;
	// Signal que sinaliza que resultado é igual a 0
	// Resultado da operação
	Port output2;



	/**
	 * 
	 * @param input1 Primeiro operando
	 * @param input2 Segundo operando
	 * @param input3 Tipo de operação
	 * @param output1 Resultado da operação
	 * @param output2 Signal de 1-bit que sinaliza que resultado é 0
	 */
	public Alu( Port input1, Port input2, Port input3,Port output1, Port output2 ){
		this.input1 = input1;
		this.input2 = input2;
		this.input3 = input3;
		this.output1 = output1;
		this.output2 = output2;
	}

	/**
	 * Lê codigo da operação e realiza
	 */
	public void execute() {
		Integer op1 = null;
		Integer op2 = null;


		// verifica se ha sinal na porta de entrada 1
		Signal signal = input1.getSignal();
		if( signal != null && signal.isEvent() == true )
		{
			// recupera a informacao armazenada no sinal
			op1 = Integer.parseInt( input1.getSignal().read() );
			System.out.println(">>> Lê primeiro operando: " + op1);
		}

		//verifica se ha sinal na porta de entrada 2
		signal = input2.getSignal();
		if( signal != null && signal.isEvent() == true )
		{
			// recupera a informacao armazenada no sinal
			op2 = Integer.parseInt( signal.read() );
			System.out.println(">>> Lê segundo operando: " + op2);
		}

		String opCode = null;

		//verifica se ha sinal na porta 3
		signal = input3.getSignal();
		if( signal != null && signal.isEvent() == true )
		{
			// recupera a informacao armazenada no sinal
			opCode = signal.read();
			System.out.println(">>> Lê operação: " + opCode);

			switch( opCode) {
			case "add":
				add(op1, op2);
				break;
			case "sub":
				sub(op1, op2);
				break;
			case "and":
				and(op1, op2);
				break;
			case "or":
				or( op1, op2);
				break;
			case "xor":
				xor(op1, op2);
				break;
			case "not":
				not(op1);
				break;
			default:
				break;

			}

		}
	}

	/**
	 * 
	 * @param op1
	 * @param op2
	 */
	public void add(int op1, int op2) {
		System.out.println(">>> Realiza ADD ");
		Integer resultado = op1+op2;
		
		System.out.println(">>> Grava resultado no canal");
		String signal1 = resultado.toString();
		String signal2 = (resultado == 0)? "v" : "f";
		
		Signal s1 = new Signal(signal1);
		Signal s2 = new Signal(signal2);
		
		// diz que quer enviar algo por esses sinais
		input1.setEvent(false);
		input2.setEvent(false);
		input3.setEvent(false);
		s1.setEvent(true);
		s2.setEvent(true);
		
		//output1.setSignal(s2);
		//output1.write();
		output2.setSignal(s1);
		output2.write();
	}

	/**
	 * 
	 * @param op1
	 * @param op2
	 */
	public void sub(int op1, int op2) {
		System.out.println(">>> Realiza SUB ");
		Integer resultado = op1-op2;
		
		System.out.println(">>> Grava resultado no canal");
		String signal1 = resultado.toString();
		String signal2 = (resultado == 0)? "v" : "f";
		
		Signal s1 = new Signal(signal1);
		Signal s2 = new Signal(signal2);
		
		// diz que quer enviar algo por esses sinais
		input1.setEvent(false);
		input2.setEvent(false);
		input3.setEvent(false);
		s1.setEvent(true);
		s2.setEvent(true);
		
		//output1.setSignal(s2);
		//output1.write();
		output2.setSignal(s1);
		output2.write();
	}

	/**
	 * 
	 * @param op1
	 * @param op2
	 */
	public void and(int op1, int op2) {
		System.out.println(">>> Realiza AND ");
		Integer resultado = op1&op2;
		
		System.out.println(">>> Grava resultado no canal");
		String signal1 = resultado.toString();
		String signal2 = (resultado == 0)? "v" : "f";
		
		Signal s1 = new Signal(signal1);
		Signal s2 = new Signal(signal2);
		
		// diz que quer enviar algo por esses sinais
		input1.setEvent(false);
		input2.setEvent(false);
		input3.setEvent(false);
		s1.setEvent(true);
		s2.setEvent(true);
		
		//output1.setSignal(s2);
		//output1.write();
		output2.setSignal(s1);
		output2.write();
	}

	/**
	 * 
	 * @param op1
	 * @param op2
	 */
	public void or(int op1, int op2) {
		System.out.println(">>> Realiza OR ");
		Integer resultado = op1|op2;
		
		System.out.println(">>> Grava resultado no canal");
		String signal1 = resultado.toString();
		String signal2 = (resultado == 0)? "v" : "f";
		
		Signal s1 = new Signal(signal1);
		Signal s2 = new Signal(signal2);
		
		// diz que quer enviar algo por esses sinais
		input1.setEvent(false);
		input2.setEvent(false);
		input3.setEvent(false);
		s1.setEvent(true);
		s2.setEvent(true);
		
		//output1.setSignal(s2);
		//output1.write();
		output2.setSignal(s1);
		output2.write();
	}

	/**
	 * 
	 * @param op1
	 * @param op2
	 */
	public void xor(int op1, int op2) {
		System.out.println(">>> Realiza XOR ");
		Integer resultado = op1^op2;
		
		System.out.println(">>> Grava resultado no canal");
		String signal1 = resultado.toString();
		String signal2 = (resultado == 0)? "v" : "f";
		
		Signal s1 = new Signal(signal1);
		Signal s2 = new Signal(signal2);
		
		// diz que quer enviar algo por esses sinais
		input1.setEvent(false);
		input2.setEvent(false);
		input3.setEvent(false);
		s1.setEvent(true);
		s2.setEvent(true);
		
		//output1.setSignal(s2);
		//output1.write();
		output2.setSignal(s1);
		output2.write();
	}

	/**
	 * 
	 * @param op1
	 * @param op2
	 */
	public void not(int op1) {
		System.out.println(">>> Realiza SUB ");
		Integer resultado = -op1;
		
		System.out.println(">>> Grava resultado no canal");
		String signal1 = resultado.toString();
		String signal2 = (resultado == 0)? "v" : "f";
		
		Signal s1 = new Signal(signal1);
		Signal s2 = new Signal(signal2);
		
		// diz que quer enviar algo por esses sinais
		input1.setEvent(false);
		input2.setEvent(false);
		input3.setEvent(false);
		s1.setEvent(true);
		s2.setEvent(true);
		
		//output1.setSignal(s2);
		//output1.write();
		output2.setSignal(s1);
		output2.write();
	}

}
