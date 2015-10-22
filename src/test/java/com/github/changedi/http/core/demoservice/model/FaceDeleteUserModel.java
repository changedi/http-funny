package com.github.changedi.http.core.demoservice.model;

import java.util.List;

public class FaceDeleteUserModel {

	private String jsonrpc;
	private int id;
	private String method;
	private List<FaceDeleteUserInternalModel> params;
	/**
	 * @return the jsonrpc
	 */
	public String getJsonrpc() {
		return jsonrpc;
	}
	/**
	 * @param jsonrpc the jsonrpc to set
	 */
	public void setJsonrpc(String jsonrpc) {
		this.jsonrpc = jsonrpc;
	}
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return the method
	 */
	public String getMethod() {
		return method;
	}
	/**
	 * @param method the method to set
	 */
	public void setMethod(String method) {
		this.method = method;
	}
	/**
	 * @return the params
	 */
	public List<FaceDeleteUserInternalModel> getParams() {
		return params;
	}
	/**
	 * @param params the params to set
	 */
	public void setParams(List<FaceDeleteUserInternalModel> params) {
		this.params = params;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "FaceDeleteUserModel [jsonrpc=" + jsonrpc + ", id=" + id
				+ ", method=" + method + ", params=" + params + "]";
	}
	
	
}
