package com.ryt.web.controller.article;

import java.util.HashMap;

import org.durcframework.core.GridResult;
import org.durcframework.core.MessageResult;
import org.durcframework.core.controller.CrudController;

import com.ryt.web.entity.article.ArticleExtend;
import com.ryt.web.entity.article.ArticleExtendSch;
import com.ryt.web.service.article.ArticleExtendService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ArticleExtendController extends
		CrudController<ArticleExtend, ArticleExtendService> {

		//鏂板璁板綍
	@RequestMapping("/addArticleExtend.do")
	public @ResponseBody
	MessageResult addArticleExtend(ArticleExtend entity) {
		return this.save(entity);
	}

	//鍒犻櫎璁板綍
	@RequestMapping("/delArticleExtend.do")
	public @ResponseBody
	MessageResult delArticleExtend(ArticleExtend entity) {
		return this.delete(entity);
	}
	
	//淇敼璁板綍
	@RequestMapping("/updateArticleExtend.do")
	public @ResponseBody
	MessageResult updateArticleExtend(ArticleExtend entity) {
		return this.update(entity);
	}

	//鏉′欢鏌ヨ鍒嗛〉鎿嶄綔
	@RequestMapping("/listArticleExtend.do")
	public @ResponseBody
	GridResult listArticleExtend(ArticleExtendSch searchEntitySch) {
		return this.query(searchEntitySch);
	}

	
	//鏉′欢鏌ヨ骞惰繑鍥炴墍鏈夎褰�
	@RequestMapping("/listAllArticleExtend.do")
    public @ResponseBody Object listAllArticleExtend(ArticleExtendSch searchEntitySch) {
        return this.queryAll(searchEntitySch);
    }

    //鑾峰彇璇︾粏淇℃伅
	@RequestMapping("/getArticleExtendById.do")
    public @ResponseBody ArticleExtend getArticleExtendById(ArticleExtend entity) {
        return this.getService().get(entity.getId());
    }
	
	@RequestMapping("/findArticleExtendByAbId.do")
    public @ResponseBody HashMap<String, String> findArticleExtendByAbId(int abId) {
        return this.getService().findABExtByabId(abId);
    }
 	
    @RequestMapping("/findABExtCheckboxByabIdAndCellname.do")
    public @ResponseBody ArticleExtend findABExtCheckboxByabIdAndCellname(int abId, String cellname) {
        return this.getService().findABExtCheckboxByabIdAndCellname(abId, cellname);
    }

}