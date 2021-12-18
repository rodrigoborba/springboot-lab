package br.com.tarefas.exception;

public class TarefaStatusException extends RuntimeException {
	
	private static final long serialVersionUID = 5481721327806548261L;

	public TarefaStatusException() {
		super();
	}

	public TarefaStatusException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public TarefaStatusException(String message, Throwable cause) {
		super(message, cause);
	}

	public TarefaStatusException(String message) {
		super(message);
	}

	public TarefaStatusException(Throwable cause) {
		super(cause);
	}
	
	

}
