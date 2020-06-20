package com.ensk.exp.mape.exception;

public class ServiceIntegrationExeption extends Exception {

	private static final long serialVersionUID = 1L;

	public ServiceIntegrationExeption() {
		super();
	}

	public ServiceIntegrationExeption(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public ServiceIntegrationExeption(String message, Throwable cause) {
		super(message, cause);
	}

	public ServiceIntegrationExeption(String message) {
		super(message);
	}

	public ServiceIntegrationExeption(Throwable cause) {
		super(cause);
	}

}
