package com.clayder.Finances.messages;

public class ErrorMessageStatusCode {
	    
	 public static String get200() {
		 return "OK";
	 }
	 
	 public static String get400() {
		 return "Requisição inválida.";
	 }
	 
	 public static String get401() {
		 return "Não autorizado.";
	 }
	 
	 public static String get403() {
		 return "Você não tem permissão para acessar este recurso.";
	 }
	 
	 public static String get404() {
		 return "Não encontrado.";
	 }
	 
	 public static String get405() {
		 return "Método não permitido";
	 }
	 
	 public static String get415() {
		 return "Tipo de mídia não suportado.";
	 }
	 
	 public static String get500() {
		 return "Erro interno.";
	 }
}
