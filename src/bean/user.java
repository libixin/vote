package bean;

import java.sql.Timestamp;

public class user {

	private Integer id;
	private String name;
	private String sex;
	private String intro;
	private String type;
	private String password;
	private String headIconUrl;
	private Timestamp registerDate;
	private String enable;
	
	public Integer getUserId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setUserId(Integer id) {
		this.id = id;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getIntro() {
		return intro;
	}
	public void setIntro(String intro) {
		this.intro = intro;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getHeadIconUrl() {
		return headIconUrl;
	}
	public void setHeadIconUrl(String headIconUrl) {
		this.headIconUrl = headIconUrl;
	}
	public Timestamp getRegisterDate() {
		return registerDate;
	}
	public void setRegisterDate(Timestamp registerDate) {
		this.registerDate = registerDate;
	}
	public String getEnable() {
		return enable;
	}
	public void setEnable(String enable) {
		this.enable = enable;
	}
	
}
