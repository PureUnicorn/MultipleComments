package personal.timeless.cms.bean;

import java.util.Date;
import java.util.List;

public class CommentSaying {
	private Integer id;
	private String sayingContent;
	private String author;
	private Integer sectionId;
	private String avatar;
	private String likes;
	private Date createTime;
	private List<CommentFirstLevel> flcs;
	
	public CommentSaying() {}

	@Override
	public String toString() {
		return "CommentSaying{" +
				"id=" + id +
				", sayingContent='" + sayingContent + '\'' +
				", author='" + author + '\'' +
				", sectionId=" + sectionId +
				", avatar='" + avatar + '\'' +
				", likes='" + likes + '\'' +
				", createTime=" + createTime +
				", flcs=" + flcs +
				'}';
	}


	public Integer getSectionId() {
		return sectionId;
	}

	public void setSectionId(Integer sectionId) {
		this.sectionId = sectionId;
	}

	public List<CommentFirstLevel> getFlcs() {
		return flcs;
	}

	public void setFlcs(List<CommentFirstLevel> flcs) {
		this.flcs = flcs;
	}

	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getSayingContent() {
		return sayingContent;
	}
	
	public void setSayingContent(String sayingContent) {
		this.sayingContent = sayingContent;
	}
	
	public String getAuthor() {
		return author;
	}
	
	public void setAuthor(String author) {
		this.author = author;
	}
	
	public String getAvatar() {
		return avatar;
	}
	
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	
	public String getLikes() {
		return likes;
	}
	
	public void setLikes(String likes) {
		this.likes = likes;
	}
	
	public Date getCreateTime() {
		return createTime;
	}
	
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}