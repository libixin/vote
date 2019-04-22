package bean;

import java.time.LocalDateTime;

public class vote {

	private Integer id;
	private Integer userid;
	private String author;
	private String caption;//标题
	private String content;//内容
	private String votetype;
	private LocalDateTime publishtime;
	private LocalDateTime finaltime;
	private String enable;
	private String selecttype;
	
	public Integer getVoteid() {
		return id;
	}
	public void setVoteid(Integer voteId) {
		this.id = voteId;
	}

	public Integer getUserid() {
		return userid;
	}
	public void setUserid(Integer userId) {
		this.userid = userId;
	}
	
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	
	public String getCaption() {
		return caption;
	}
	public void setCaption(String caption) {
		this.caption = caption;
	}
	
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	public String getVotetype() {
		return votetype;
	}
	public void setVotetype(String voteType) {
		this.votetype = voteType;
	}
	
	public LocalDateTime getPublishtime() {
		return publishtime;
	}
	public void setPublishtime(LocalDateTime publishtime) {
		this.publishtime = publishtime;
	}
	
	public LocalDateTime getFinaltime() {
		return finaltime;
	}
	public void setFinaltime(LocalDateTime finaltime) {
		this.finaltime = finaltime;
	}
	
	public String getEnable() {
		return enable;
	}
	public void setEnable(String enable) {
		this.enable = enable;
	}
	
	public String getSelecttype() {
		return selecttype;
	}
	public void setSelecttype(String selecttype) {
		this.selecttype = selecttype;
	}
	
}
