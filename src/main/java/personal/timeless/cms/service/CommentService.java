package personal.timeless.cms.service;

import personal.timeless.cms.bean.CommentFirstLevel;
import personal.timeless.cms.bean.CommentSecondLevel;

public interface CommentService {
	
	public void addFlcComment(CommentFirstLevel commentFirstLevel);
	
	public void removeFlcComment(int commentId, int sayingId);
	
	public void addSlcComment(CommentSecondLevel commentSecondLevel);
	
	public void removeSlcComment(int commentId, int sayingId);
}
