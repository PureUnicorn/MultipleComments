package personal.timeless.cms.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import personal.timeless.cms.bean.CommentSaying;
import personal.timeless.cms.mapper.CommentSayingMapper;
import personal.timeless.cms.service.CommentSayingService;

import java.util.List;

@Service
public class CommentSayingServiceImpl implements CommentSayingService {
	
	@Autowired
    private CommentSayingMapper commentSayingMapper;

	@Override
	public void insertComment(CommentSaying commentSaying){
		commentSayingMapper.insertComment(commentSaying);}

	@Override
	public List<CommentSaying> selectListBySectionId(int id){
		return commentSayingMapper.selectListBySectionId(id);
	}

	@Override
	public CommentSaying QueryOneSaying(int id) {
		return commentSayingMapper.selectOneById(id);
	}

	@Override
	public void deleteById(int sayingId) {
		commentSayingMapper.deleteById(sayingId);
	}

	@Override
	public void modifySayingLikes(int id, String likes) {
		commentSayingMapper.updateLikesById(id, likes);
	}
}
