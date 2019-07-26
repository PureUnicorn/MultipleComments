package personal.timeless.cms.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import personal.timeless.cms.bean.CommentFirstLevel;
import personal.timeless.cms.bean.CommentSecondLevel;
import personal.timeless.cms.mapper.CommentFirstLevelMapper;
import personal.timeless.cms.mapper.CommentSecondLevelMapper;
import personal.timeless.cms.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService {
	
	@Autowired
	private CommentFirstLevelMapper commentFirstLevelMapper;
	
	@Autowired
	private CommentSecondLevelMapper commentSecondLevelMapper;

	@Override
	public void addFlcComment(CommentFirstLevel commentFirstLevel) {
		commentFirstLevelMapper.insertFlcComment(commentFirstLevel);
	}

	@Override
	public void removeFlcComment(int commentId, int sayingId) {
		commentFirstLevelMapper.deleteFlcComment(commentId, sayingId);
	}

	@Override
	public void addSlcComment(CommentSecondLevel commentSecondLevel) {
		commentSecondLevelMapper.insertSlcComment(commentSecondLevel);
	}

	@Override
	public void removeSlcComment(int commentId, int sayingId) {
		commentSecondLevelMapper.deleteSlcComment(sayingId, commentId);
	}

}
