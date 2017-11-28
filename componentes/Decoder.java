package componentes;

import controle.Port;

/*
 * Classe referente a um componente decodificar
 * Decodifica as instrucoes vindas da memoria de instrucoes
 */
public class Decoder {

	
	public Decoder( ){
		
		
	}
	
	
	// funcao com logica de execucao do componente (decodificacao)
	public String[] execute( String instrucao ){
	
	
		System.out.println("Iniciando decodificacao");
		
		// separando instrucao pelo formato
		String[] inst = instrucao.split(" ");
		
		// Identifica opcode	
		System.out.println("Opcode: " + inst[0]);
		
		// identifica registradores fonte
		System.out.println("Registradores fonte: " +inst[1] + "; " +inst[2]);
		
		// identifica registrador destino
		System.out.println("Registrador destino: " +inst[3]);

		return inst;
		
	}
		
}
