package onboardingemployee.crudoperations.responsemodel;

import java.util.Map;

public class CustomResponse {
	
	Map<String, Object> metaData;
	Object data;
	public Map<String, Object> getMetaData() {
		return metaData;
	}
	public void setMetaData(Map<String, Object> metaData) {
		this.metaData = metaData;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	@Override
	public String toString() {
		return "CustomResponse [metaData=" + metaData + ", data=" + data + "]";
	}

}
