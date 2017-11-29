package componentes;

import controle.Port;

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
	// Resultado da operação
	Port ouput1;
	// Signal que sinaliza que resultado é igual a 0
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
		
	}
	
	
}
