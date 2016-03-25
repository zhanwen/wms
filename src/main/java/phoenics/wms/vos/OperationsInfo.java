package phoenics.wms.vos;

import java.io.Serializable;

public class OperationsInfo  implements Serializable {
	/**
	 * @Description: 
	 */
	private static final long serialVersionUID = 1L;
	 private String msgString="";
	 private String userName="";
	 private boolean success=false;
	 public OperationsInfo( String msgString,boolean success){
		 this("",msgString,success);
	 }
	 public OperationsInfo(String userName, String msgString,boolean success){
		 this.setUserName(userName);
		 this.setMsgString(msgString);
		 this.setSuccess(success);
	 }
	public String getMsgString() {
		return msgString;
	}
	public void setMsgString(String msgString) {
		this.msgString = msgString;
	}
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	

}
