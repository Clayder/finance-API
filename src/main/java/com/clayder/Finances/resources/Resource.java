package com.clayder.Finances.resources;

public abstract class Resource {
	
	protected final String status200Message = "OK";
	protected final String status201Message = "Cadastrado com sucesso.";
	protected final String status204Message = "OK";
	protected final String status400Message = "Requisição inválida.";
	protected final String status401Message = "Não autorizado.";
	protected final String status403Message = "Você não tem permissão para acessar este recurso.";
	protected final String status404Message = "Não encontrado.";
	protected final String status405Message = "Método não permitido";
	protected final String status415Message = "Tipo de mídia não suportado.";
	protected final String status422Message = "Entidade improcessável.";
	protected final String status500Message = "Erro interno.";
	
}
