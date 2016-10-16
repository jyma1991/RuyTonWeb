package com.ryt.web.controller.notify;

import org.durcframework.core.GridResult;
import org.durcframework.core.MessageResult;
import org.durcframework.core.controller.CrudController;
import com.ryt.web.entity.notify.NotifyArticle;
import com.ryt.web.entity.notify.NotifyArticleSch;
import com.ryt.web.service.notify.NotifyArticleService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class NotifyArticleController extends
		CrudController<NotifyArticle, NotifyArticleService> {

		//新增记录
	@RequestMapping("/addNotifyArticle.do")
	public @ResponseBody
	MessageResult addNotifyArticle(NotifyArticle entity) {
		return this.save(entity);
	}

	//删除记录
	@RequestMapping("/delNotifyArticle.do")
	public @ResponseBody
	MessageResult delNotifyArticle(NotifyArticle entity) {
		return this.delete(entity);
	}
	
	//修改记录
	@RequestMapping("/updateNotifyArticle.do")
	public @ResponseBody
	MessageResult updateNotifyArticle(NotifyArticle entity) {
		return this.update(entity);
	}

	//条件查询分页操作
	@RequestMapping("/listNotifyArticle.do")
	public @ResponseBody
	GridResult listNotifyArticle(NotifyArticleSch searchEntitySch) {
		return this.query(searchEntitySch);
	}

	
	//条件查询并返回所有记录
	@RequestMapping("/listAllNotifyArticle.do")
    public @ResponseBody Object listAllNotifyArticle(NotifyArticleSch searchEntitySch) {
        return this.queryAll(searchEntitySch);
    }

    //获取详细信息
	@RequestMapping("/getNotifyArticleById.do")
    public @ResponseBody NotifyArticle getNotifyArticleById(NotifyArticle entity) {
        return this.getService().get(entity.getId());
    }

 	

}