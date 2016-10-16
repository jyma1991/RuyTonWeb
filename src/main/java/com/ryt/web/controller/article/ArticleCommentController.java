package com.ryt.web.controller.article;

import java.util.List;

import org.durcframework.core.DefaultGridResult;
import org.durcframework.core.GridResult;
import org.durcframework.core.MessageResult;
import org.durcframework.core.controller.CrudController;
import org.durcframework.core.expression.ExpressionQuery;
import org.durcframework.core.expression.subexpression.ValueExpression;

import com.ryt.web.entity.article.ArticleComment;
import com.ryt.web.entity.article.ArticleCommentSch;
import com.ryt.web.entity.user.User;
import com.ryt.web.service.article.ArticleCommentService;
import com.ryt.web.service.user.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ArticleCommentController extends
		CrudController<ArticleComment, ArticleCommentService> {
	@Autowired
	private UserService userService;

		//鏂板璁板綍
	@RequestMapping("/addArticleComment.do")
	public @ResponseBody
	MessageResult addArticleComment(ArticleComment entity) {
		return this.save(entity);
	}

	//鍒犻櫎璁板綍
	@RequestMapping("/delArticleComment.do")
	public @ResponseBody
	MessageResult delArticleComment(ArticleComment entity) {
		return this.delete(entity);
	}
	
	//淇敼璁板綍
	@RequestMapping("/updateArticleComment.do")
	public @ResponseBody
	MessageResult updateArticleComment(ArticleComment entity) {
		return this.update(entity);
	}

	//鏉′欢鏌ヨ鍒嗛〉鎿嶄綔
	@RequestMapping("/listArticleComment.do")
	public @ResponseBody
	GridResult listArticleComment(ArticleCommentSch searchEntitySch) {
		DefaultGridResult result = (DefaultGridResult)this.query(searchEntitySch);
		List<ArticleComment> comments = (List<ArticleComment>)result.getRows();
		for(int i = 0; i<comments.size(); i++){
			User user = userService.get(comments.get(i).getUserId());
			if(user != null){
				comments.get(i).setSendName(user.getTrueName());
			}
		}
		return new DefaultGridResult(comments);
	}

	@RequestMapping("/listArticleCommentTree.do")
    public @ResponseBody Object listArticleCommentTree(ArticleCommentSch searchEntity) {

        /*
         * 默认EasyUI树形分页列表式
         */
        if (searchEntity.getParentIdSch() == 0) {
            DefaultGridResult resultGrid = (DefaultGridResult) this.query(searchEntity);

            for (int i = 0; i < resultGrid.getRows().size(); i++) {
                ArticleComment getRow = (ArticleComment) resultGrid.getRows().get(i);
                ExpressionQuery queryCount = new ExpressionQuery();
                queryCount.add(new ValueExpression("parentId", getRow.getId()));
                if (this.getService().findTotalCount(queryCount) > 0) {
                    getRow.setState("closed");
                } else {
                    getRow.setState("open");
                }
            }

            return resultGrid;
        } else {
            /*
             * 子类节点式
             */
            ArticleCommentSch entity = new ArticleCommentSch();
            entity.setArticleBaseIdSch(searchEntity.getArticleIdSch());
            entity.setParentIdSch(searchEntity.getParentIdSch());
            entity.setIdSch(searchEntity.getIdSch());
            DefaultGridResult resultGrid = (DefaultGridResult) this.query(entity);

            for (int i = 0; i < resultGrid.getRows().size(); i++) {
                ArticleComment getRow = (ArticleComment) resultGrid.getRows().get(i);
                ExpressionQuery queryCount = new ExpressionQuery();
                queryCount.add(new ValueExpression("parentId", getRow.getId()));
                if (this.getService().findTotalCount(queryCount) > 0) {
                    getRow.setState("closed");
                } else {
                    getRow.setState("open");
                }
            }

            return resultGrid.getRows();
        }

    }
	
	//鏉′欢鏌ヨ骞惰繑鍥炴墍鏈夎褰�
	@RequestMapping("/listAllArticleComment.do")
    public @ResponseBody Object listAllArticleComment(ArticleCommentSch searchEntitySch) {
        return this.queryAll(searchEntitySch);
    }

    //鑾峰彇璇︾粏淇℃伅
	@RequestMapping("/getArticleCommentById.do")
    public @ResponseBody ArticleComment getArticleCommentById(ArticleComment entity) {
        return this.getService().get(entity.getId());
    }

 	

}