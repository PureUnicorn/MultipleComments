package personal.timeless.cms.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import personal.timeless.cms.bean.CommentSecondLevel;

@Mapper
public interface CommentSecondLevelMapper {
	
	public int insertSlcComment(CommentSecondLevel commentSecondLevel);
	public void deleteSlcComment(@Param("sayingId") int sayingId, @Param("commentId") int commentId);
}
