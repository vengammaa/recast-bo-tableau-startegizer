package com.lti.recast.recastBoTableau.strategizer.dto;

public class QueryColumnModel {

	private String objectId;
	
	private String columnName;
	
	private String aliasName;
	
	private String expression;

	public String getObjectId() {
		return objectId;
	}

	public void setObjectId(String objectId) {
		this.objectId = objectId;
	}

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}


	public String getAliasName() {
		return aliasName;
	}

	public void setAliasName(String aliasName) {
		this.aliasName = aliasName;
	}

	public String getExpression() {
		return expression;
	}

	public void setExpression(String expression) {
		this.expression = expression;
	}

	@Override
	public String toString() {
		return "QueryColumnModel [objectId=" + objectId + ", columnName=" + columnName + ", aliasName=" + aliasName
				+ ", expression=" + expression + "]";
	}

	
	
	
	
	
}
