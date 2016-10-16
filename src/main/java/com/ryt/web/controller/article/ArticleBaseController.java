package com.ryt.web.controller.article;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.durcframework.core.DefaultGridResult;
import org.durcframework.core.GridResult;
import org.durcframework.core.MessageResult;
import org.durcframework.core.controller.CrudController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ryt.web.common.MemcacheManager;
import com.ryt.web.entity.article.ArticleBase;
import com.ryt.web.entity.article.ArticleBaseSch;
import com.ryt.web.entity.article.ArticleExtend;
import com.ryt.web.service.article.ArticleBaseService;
import com.ryt.web.service.article.ArticleExtendService;
import com.ryt.web.service.sc.ScClassService;
import com.ryt.web.service.sc.ScSchoolService;
import com.ryt.web.service.user.UserService;

@Controller
public class ArticleBaseController extends
		CrudController<ArticleBase, ArticleBaseService> {

	@Autowired
    private ArticleExtendService articleExtendService;
	@Autowired
    private UserService userService;
	@Autowired
    private ScSchoolService scSchoolService;
	@Autowired
    private ScClassService scClassService;
	
	//鏂板璁板綍
	@RequestMapping("/addArticleBase.do")
	public @ResponseBody
	MessageResult addArticleBase(ArticleBase entity,HttpServletRequest request) {
		MessageResult ret = this.save(entity);
		if(entity.getExtFields()==null) return ret;
        // 判断扩展字段，并进行遍历
        String[] extFields = entity.getExtFields().split(",");
        for (int i = 0; i < extFields.length; i++) {
            ArticleExtend cae = new ArticleExtend();
            cae.setArticleBaseId(entity.getId());
            cae.setSysClassId(entity.getSysClassId());
            String extFieldName = extFields[i];
            // 如果该扩展字段存在且需要有值，那么则进行入库操作
            if (request.getParameter(extFieldName) != null && request.getParameter(extFieldName).length() > 0) {
                cae.setCellName(extFieldName);
                cae.setCellValue(request.getParameter(extFieldName));
                articleExtendService.save(cae);
            }
        }
       // MemcacheManager.delete("WIKI_"+entity.getSysClassId());
        return ret;
	}

	//鍒犻櫎璁板綍
	@RequestMapping("/delArticleBase.do")
	public @ResponseBody
	MessageResult delArticleBase(ArticleBase entity) {
		 //MemcacheManager.delete("WIKI_"+entity.getSysClassId());
		return this.delete(entity);
	}
	
	//淇敼璁板綍
	@RequestMapping("/updateArticleBase.do")
	public @ResponseBody
	MessageResult updateArticleBase(ArticleBase entity,HttpServletRequest request) {
		MessageResult ret = this.update(entity);
		if(entity.getExtFields()==null) return ret;
        // 扩展字段操作，目前的处理方式是，先将扩展字段中该基本信息所属扩展字段的内容都先全部删除，然后将新值进行重新入表的操作方式
        articleExtendService.delABExtByabId(entity.getId());
        // 判断扩展字段，并进行遍历
        String[] extFields = entity.getExtFields().split(",");
        for (int i = 0; i < extFields.length; i++) {
            ArticleExtend cae = new ArticleExtend();
            cae.setArticleBaseId(entity.getId());
            cae.setSysClassId(entity.getSysClassId());
            String extFieldName = extFields[i];
            // 如果该扩展字段存在且需要有值，那么则进行入库操作
            if (request.getParameter(extFieldName) != null && request.getParameter(extFieldName).length() > 0) {
            	cae.setCellName(extFieldName);
                cae.setCellValue(request.getParameter(extFieldName));
                articleExtendService.save(cae);
            }
        }
       // MemcacheManager.delete("WIKI_"+entity.getSysClassId());
        return ret;
	}

	//鏉′欢鏌ヨ鍒嗛〉鎿嶄綔
	@RequestMapping("/listArticleBase.do")
	public @ResponseBody
	GridResult listArticleBase(ArticleBaseSch searchEntitySch) {
		return this.query(searchEntitySch);
	}

	//分页查询家长圈的列表
	@RequestMapping("/listArticleParentCircle.do")
	public @ResponseBody
	GridResult listArticleParentCircle(ArticleBaseSch searchEntitySch) {
		//家长圈的sysClassId的值为155
		//此处的getArticlePropertySch获取的其实代表的是user表中的roleProperty属性
		List<ArticleBase> list = this.getService().getArticlesByParents(searchEntitySch.getSchoolIdSch(),searchEntitySch.getClassIdSch(),155, searchEntitySch.getArticlePropertySch(),searchEntitySch.getStart());
		for(int i = 0; i<list.size(); i++){
			ArticleBase articleBase = list.get(i);
			articleBase.setAuthor(userService.get(articleBase.getUserId()).getTrueName());
			articleBase.setAgent(userService.get(articleBase.getAgentId()));
			articleBase.setSchool(scSchoolService.getSchoolBySchoolId(articleBase.getSchoolId()));
			articleBase.setScClass(scClassService.get(articleBase.getClassId()));
		}
		return new DefaultGridResult(list);
	}
	
	
	//鏉′欢鏌ヨ骞惰繑鍥炴墍鏈夎褰�
	@RequestMapping("/listAllArticleBase.do")
    public @ResponseBody Object listAllArticleBase(ArticleBaseSch searchEntitySch) {
        return this.queryAll(searchEntitySch);
    }

    //鑾峰彇璇︾粏淇℃伅
	@RequestMapping("/getArticleBaseById.do")
    public @ResponseBody ArticleBase getArticleBaseById(ArticleBase entity) {
        return this.getService().get(entity.getId());
    }
	
	//批量更新文章属性
    @RequestMapping("/updateArticleBaseByBatchIds.do")
    public @ResponseBody MessageResult updateArticleBaseByBatchIds(Integer[] ids, Integer[] aps) {
    	
    	for(int i=0;i<ids.length;i++)
    	{
    		ArticleBase articleBase = new ArticleBase();
    		articleBase.setId(ids[i]);
    		articleBase.setArticleProperty(aps[i]);
    		this.getService().update(articleBase);
    	}
    	return success();
    }
    
    /**
     * 统计家长圈家长发的消息
     * @param sysClassId
     * @param roleId
     * @return
     */
	@RequestMapping("/listAllArticleBaseByParents.do")
    public @ResponseBody Object listAllArticleBaseByParents() {
		//sysClassId=155的时候表示家长圈，roleId为2的时候表示家长
        return this.getService().getArticlesByParents(null,null,155, 2,null);
    }

}
