package br.com.tarefas.controller.response;

public class ErroResponse {
	
	private String campo;
	private String mensagem;
	
	public ErroResponse(String campo, String mensagem) {
		super();
		this.campo = campo;
		this.mensagem = mensagem;
	}

	public ErroResponse(String mensagem) {
		this.mensagem = mensagem;
	}

	public String getCampo() {
		return campo;
	}

	public void setCampo(String campo) {
		this.campo = campo;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	
	

}
