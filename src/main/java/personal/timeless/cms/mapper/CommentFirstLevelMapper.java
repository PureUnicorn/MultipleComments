package personal.timeless.cms.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import personal.timeless.cms.bean.CommentFirstLevel;

@Mapper
public interface CommentFirstLevelMapper {
	public void insertFlcComment(CommentFirstLevel commentFirstLevel);
	
	public void deleteFlcComment(@Param("commentId") int commentId, @Param("sayingId") int sayingId);
}
