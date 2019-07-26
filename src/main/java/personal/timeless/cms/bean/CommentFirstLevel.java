package personal.timeless.cms.bean;

import java.util.Date;
import java.util.List;

public class CommentFirstLevel {
	private Integer id;
	private Integer sayingId;
	private String commenter;
	private String avatar;
	private String commentContent;
	private Date commentTime;
	private CommentSaying commentSaying;
	private List<CommentSecondLevel> slcs;


	public CommentFirstLevel() {
		this.commentTime = new Date();
	}
	
	public List<CommentSecondLevel> getSlcs() {
		return slcs;
	}

	public void setSlcs(List<CommentSecondLevel> slcs) {
		this.slcs = slcs;
	}

	public CommentSaying getCommentSaying() {
		return commentSaying;
	}

	public void setCommentSaying(CommentSaying commentSaying) {
		this.commentSaying = commentSaying;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getSayingId() {
		return sayingId;
	}
	
	public void setSayingId(Integer sayingId) {
		this.sayingId = sayingId;
	}
	
	public String getCommenter() {
		return commenter;
	}
	
	public void setCommenter(String commenter) {
		this.commenter = commenter;
	}
	
	public String getAvatar() {
		return avatar;
	}
	
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	
	public String getCommentContent() {
		return commentContent;
	}
	
	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}
	
	public Date getCommentTime() {
		return commentTime;
	}
	
	public void setCommentTime(Date commentTime) {
		this.commentTime = commentTime;
	}

	@Override
	public String toString() {
		return "CommentFirstLevel [flc_id=" + id + ", sayingId=" + sayingId + ", commenter=" + commenter
				+ ", avatar=" + avatar + ", commentContent=" + commentContent + ", commentTime=" + commentTime + "]";
	}
}
