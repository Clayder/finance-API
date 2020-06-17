package com.clayder.Finances.messages;

import java.util.ArrayList;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "ResponseError")
public class ResponseError {

	@ApiModelProperty(value="status code HTTP", example = "400", required = true)
	private Integer status;
	
	@ApiModelProperty(value="Mensagem de erro.", example = "Erro de validação", required = true)
	private String msg;
	
	@ApiModelProperty(value="", example = "1589910784032", required = true)
	private Integer timeStamp;
	
	@ApiModelProperty(value="", example = "[{ \"fieldName\": \"name\", \"message\": \"Preenchimento obrigatório.\" }]", required = true)
	private List<String> erros = new ArrayList<>();

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Integer getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(Integer timeStamp) {
		this.timeStamp = timeStamp;
	}

	public List<String> getErros() {
		return erros;
	}

	public void setErros(List<String> erros) {
		this.erros = erros;
	}
	
	
	
}
