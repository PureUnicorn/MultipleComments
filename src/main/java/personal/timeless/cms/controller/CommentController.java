package personal.timeless.cms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import personal.timeless.cms.bean.CommentFirstLevel;
import personal.timeless.cms.bean.CommentSecondLevel;
import personal.timeless.cms.service.CommentService;

/**
 * @author Whitte
 * @Description 用于处理一、二级评论的CRUD
 * @date 2019/7/26 9:08
 */
@RequestMapping("/comment")
@RestController
public class CommentController {
	
	@Autowired
	private CommentService commentService;

	/**
	  * @Description 添加一级评论
	  * @param commentFirstLevel 一级评论的pojo，包含评论内容等信息
	  * @return 评论pojo
	  */
	@RequestMapping(value="/add/first", method=RequestMethod.POST)
    public CommentFirstLevel addFirstLevelCommment(CommentFirstLevel commentFirstLevel) {
		commentService.addFlcComment(commentFirstLevel);
		return commentFirstLevel;
    }

    /**
      * @Description 用于删除一级评论
      * @param sayingId 父评论的ID
	  * @param commentId 回复的ID
      * @return 无
      */
	@RequestMapping(value="/delete/first/{sayingId}/{commentId}")
    public void removeFirstLevelCommment(@PathVariable int sayingId, @PathVariable int commentId) {
		commentService.removeFlcComment(commentId, sayingId);
    }

    /**
      * @Description 增加二级评论
      * @param commentSecondLevel 二级评论pojo，包含评论内容
      * @return 评论pojo
      */
	@RequestMapping(value="/add/second", method=RequestMethod.POST)
    public CommentSecondLevel addSecondLevelCommment(CommentSecondLevel commentSecondLevel) {
		commentService.addSlcComment(commentSecondLevel);
		return commentSecondLevel;
    }

    /**
      * @Description 删除二级评论
      * @param sayingId 父级评论ID
	  * @param commentId 回复评论ID
      * @return 无
      */
	@RequestMapping(value="/delete/second/{sayingId}/{commentId}")
    public void removeSecondLevelCommment(@PathVariable int sayingId, @PathVariable int commentId) {
		commentService.removeSlcComment(commentId, sayingId);
    }
}