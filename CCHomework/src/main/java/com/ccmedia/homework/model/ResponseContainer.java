package com.ccmedia.homework.model;

import java.util.ArrayList;
import java.util.List;

import com.ccmedia.homework.util.MessageConstants;
import com.google.gson.Gson;

public class ResponseContainer<T> {
	private T payload;
	private PagingDTO pagingDTO;
	private Gson gson;
	private String errorMessage;
	private String successMessage;

	public ResponseContainer() {
		gson = new Gson();
		errorMessage = "not defined errmessage";
		successMessage = "success";
	}

	public PagingDTO getPagingDTO() {
		return pagingDTO;
	}

	public void setPagingDTO(PagingDTO pagingDTO) {
		this.pagingDTO = pagingDTO;
	}

	public T getPayload() {
		return payload;
	}

	public void setPayload(T payload) {
		this.payload = payload;
	}

	public Gson getGson() {
		return gson;
	}

	public void setGson(Gson gson) {
		this.gson = gson;
	}

	public String getErrorMessage() {
		System.out.println(this.errorMessage);
		return this.errorMessage;
	}

	public void setErrorMessage(String code) {
		this.errorMessage = MessageConstants.getMessage(code);
	}

	public String getResponseToJson() {
		List<Object> response = new ArrayList<Object>();
		if(pagingDTO != null) {
			response.add(pagingDTO);
		}
		response.add(payload);
		response.add(successMessage);
		return gson.toJson(response);
	}

}
