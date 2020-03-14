package cn.jzw.mysql.databody;

public class UsersLibary {
	protected String userID;
	protected String userName;
	protected String creatDate;
	protected String userPasswd;
	public UsersLibary() {
		// TODO Auto-generated constructor stub
		creatDate=null;
		userID=null;
		userName=null;
		userPasswd=null;
	}
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getCreatDate() {
		return creatDate;
	}
	public void setCreatDate(String creatDate) {
		this.creatDate = creatDate;
	}
	public String getUserPasswd() {
		return userPasswd;
	}
	public void setUserPasswd(String userPasswd) {
		this.userPasswd = userPasswd;
	}
	
	@Override
	public String toString() {
		return "UsersLibary [userID=" + userID + ", userName=" + userName + ", creatDate=" + creatDate + ", userPasswd="
				+ userPasswd + "]";
	}

}
