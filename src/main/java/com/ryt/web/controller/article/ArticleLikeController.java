package com.ryt.web.controller.article;

import org.durcframework.core.GridResult;
import org.durcframework.core.MessageResult;
import org.durcframework.core.controller.CrudController;
import com.ryt.web.entity.article.ArticleLike;
import com.ryt.web.entity.article.ArticleLikeSch;
import com.ryt.web.service.article.ArticleLikeService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ArticleLikeController extends
		CrudController<ArticleLike, ArticleLikeService> {

		//新增记录
	@RequestMapping("/addArticleLike.do")
	public @ResponseBody
	MessageResult addArticleLike(ArticleLike entity) {
		return this.save(entity);
	}

	//删除记录
	@RequestMapping("/delArticleLike.do")
	public @ResponseBody
	MessageResult delArticleLike(ArticleLike entity) {
		return this.delete(entity);
	}
	
	//修改记录
	@RequestMapping("/updateArticleLike.do")
	public @ResponseBody
	MessageResult updateArticleLike(ArticleLike entity) {
		return this.update(entity);
	}

	//条件查询分页操作
	@RequestMapping("/listArticleLike.do")
	public @ResponseBody
	GridResult listArticleLike(ArticleLikeSch searchEntitySch) {
		return this.query(searchEntitySch);
	}

	
	//条件查询并返回所有记录
	@RequestMapping("/listAllArticleLike.do")
    public @ResponseBody Object listAllArticleLike(ArticleLikeSch searchEntitySch) {
        return this.queryAll(searchEntitySch);
    }

    //获取详细信息
	@RequestMapping("/getArticleLikeById.do")
    public @ResponseBody ArticleLike getArticleLikeById(ArticleLike entity) {
        return this.getService().get(entity.getId());
    }

 	

}