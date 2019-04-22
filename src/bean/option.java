package bean;

public class option {

	private Integer id;
	private Integer voteid;
	private String content;//选项内容

	public Integer getOptionid() {
		return id;
	}
	public void setOptionid(Integer optionid) {
		this.id = optionid;
	}
	
	public Integer getVoteid() {
		return voteid;
	}
	public void setVoteid(Integer voteId) {
		this.voteid = voteId;
	}
	
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}

}
