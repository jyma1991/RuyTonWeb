package com.ryt.web.service.article;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.durcframework.core.expression.ExpressionQuery;
import org.durcframework.core.expression.subexpression.ValueExpression;
import org.durcframework.core.service.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ryt.web.dao.article.ArticleBaseDao;
import com.ryt.web.entity.article.ArticleBase;
import com.ryt.web.entity.article.ArticleComment;
import com.ryt.web.entity.sys.SysClass;
import com.ryt.web.entity.sys.SysUpload;
import com.ryt.web.entity.user.User;
import com.ryt.web.service.area.AreaCityService;
import com.ryt.web.service.area.AreaProvinceService;
import com.ryt.web.service.sc.ScTeacherCourseService;
import com.ryt.web.service.sys.SysClassService;
import com.ryt.web.service.sys.SysUploadService;
import com.ryt.web.service.user.UserService;

import freemarker.core.ReturnInstruction.Return;

@Service
public class ArticleBaseService extends CrudService<ArticleBase, ArticleBaseDao> {
	
	@Autowired
	ArticleCommentService articleCommentService;
	@Autowired
	UserService userService;
	@Autowired
	SysClassService sysClassService;
	@Autowired
	SysUploadService uploadService;
	@Autowired
	AreaProvinceService provinceService;
	@Autowired
	AreaCityService cityService;
	@Autowired
	SysUploadService sysUploadService;
	@Autowired
	ScTeacherCourseService scTeacherCourseService;

    public void updateByBatchIds(ArticleBase ArticleBase) {
        this.getDao().update(ArticleBase);
    }
    
    /**
     * 统计家长圈发的消息
     * @param schoolId
     * @param classId
     * @param sysClassId
     * @param roleId
     * @param firstResult
     * @return
     */
    public List<ArticleBase> getArticlesByParents(Integer schoolId,Integer classId,Integer sysClassId,Integer roleId,Integer firstResult){
    	return this.getDao().getArticlesByParents(schoolId,classId,sysClassId, roleId,firstResult);
    }
    
    //获取最新文章和最新被评论文章
    public Map<String, Object> getLastForumArticle(Integer sysClassTypeId){
    	Map<String, Object> articleList = new HashMap<String, Object>();
    	List<ArticleBase> commentArticleList = new ArrayList<ArticleBase>();
    	//添加最新文章
    	List<ArticleBase> articleBases = this.getDao().getLastForumArticle(sysClassTypeId);
    	for(ArticleBase article: articleBases){
    		User author = userService.get(article.getUserId());
    		/*String adress = "";
			//设置作者所在地
			if(!author.getProvinceId().equals(0)){
				adress += provinceService.get(author.getProvinceId()).getProvinceName();
				if(!author.getCityId().equals(0)){
					String cityName = cityService.get(author.getCityId()).getCityName();
					if(!cityName.equals(adress)){
						adress += " "+cityName;
					}
				}
			}
			author.setAddressDetail(adress);
			//获取作者头像
			if(author.getDefaultAvatar() == null || author.getDefaultAvatar().equals("")){
				author.setDefaultAvatar("");
				Integer avatarId = author.getAvatar();
				if(!avatarId.equals(0)){
					author.setDefaultAvatar(uploadService.get(avatarId).getFileFullPath());
				}
			}
    		
    		SysClass assisType = sysClassService.get(article.getSysClassId());
			SysClass mainType = sysClassService.get(assisType.getParentId());
			article.setMainType(mainType);
			article.setAssisType(assisType);
			article.setMainForumReplyNums(getMainForumCommentNums(article.getId()));*/
			article.setForumAuthor(author);
    	}
    	articleList.put("article",articleBases);
    	
    	//根据最新评论获取最新文章
    	List<ArticleBase> listComment =getLastCommentForumArticle();
    	for (ArticleBase articleComment : listComment) {
    		articleComment.setForumAuthor(userService.get(articleComment.getUserId()));
    		commentArticleList.add(articleComment);
		}
    	
    	articleList.put("commentArticle", commentArticleList);
    	return articleList;
    }
    
    //根据类别Id以及数量获取文章列表
    public List<ArticleBase> getArticlesBySysClassTypeId(Integer sysClassTypeId,Integer pageSize,Integer page){
    	ExpressionQuery query = new ExpressionQuery();
    	query.add(new ValueExpression("sysClassId",sysClassTypeId));
    	query.add(new ValueExpression("isDeleted",0));
    	query.addSort("id", "desc");
    	query.setLimit(pageSize);
    	query.setStart((page-1)*pageSize);
    	return this.find(query);
    	
    }
    
    //根据类别Id查询该类别下面所有的文章数量
    public Integer getArticlesNumBySysClassId(Integer sysClassId){
    	ExpressionQuery query = new ExpressionQuery();
    	query.add(new ValueExpression("sysClassId",sysClassId));
    	query.add(new ValueExpression("isDeleted",0));
    	
    	query.setQueryAll(true);
    	return this.findTotalCount(query);
    }
    
    //获取下拉刷新的数据
    public List<ArticleBase> getNewArticlesBySysClassTypeId(ArticleBase articleBase){
    	ExpressionQuery query = new ExpressionQuery();
    	query.add(new ValueExpression("sysClassId",articleBase.getSysClassId()));
    	query.add(new ValueExpression("id", ">", articleBase.getId()));
    	query.add(new ValueExpression("isDeleted",0));
    	query.addSort("id", "desc");
    	query.setLimit(articleBase.getPageCount());
    	return this.find(query);
    }
    
    /**
     * 通过parentId查询论坛的某篇文章的直接回复
     * @param parentId
     * @param firstResult
     * @param pageSize
     * @return
     */
	public List<ArticleBase> findCommentsByPages(Integer parentId,Integer firstResult,Integer pageSize){
		return this.getDao().findCommentsByPages(parentId, firstResult, pageSize);
	}
	
	/**
	 * 通过论坛文章id查找文章所有的子评论的数量
	 * @param parentId
	 * @return
	 */
	public int getAllForumCommentNums(Integer parentId){
		return this.getDao().getAllForumCommentNums(parentId);
	}
	/**
	 * 获取论坛文章主评论的数量
	 * @param parentId
	 * @return
	 */
	public int getMainForumCommentNums(Integer parentId){
		ExpressionQuery query = new ExpressionQuery();
		query.add(new ValueExpression("parentId", parentId));
		query.add(new ValueExpression("isDeleted",0));
		query.setQueryAll(true);
		return this.findTotalCount(query);
	}
	
	/**
	 * 获取最新评论
	 * @return
	 */
	public List<ArticleBase> getLastCommentForumArticle(){
		return this.getDao().getLastCommentForumArticle();
	}

	/** 
	* @Title: getArticleById 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param classId
	* @param @return    设定文件 
	* @return Object    返回类型 
	* @version V1.0
	* @author xiaoren
	* @throws 
	*/
	public List<ArticleBase>  getArticleById(ArticleBase oldArticleBase) {
		ExpressionQuery query = new ExpressionQuery();
		query.add(new ValueExpression("classId", oldArticleBase.getClassId()));
		query.add(new ValueExpression("sysClassId", oldArticleBase.getSysClassId()));
		query.add(new ValueExpression("parentId", 0));
		query.add(new ValueExpression("isDeleted",0));
		query.setPageSize(oldArticleBase.getPageCount());
		List<ArticleBase> ArticleBaseList = this.find(query);
		for (ArticleBase articleBase : ArticleBaseList) {
			if (articleBase.getPicId() != null) {
				articleBase.setUrl(sysUploadService.get(articleBase.getPicId()).getFileFullPath());
			}
			if (articleBase.getTeacherId()!=null) {
				User teacher = userService.get(articleBase.getTeacherId());
				//获取教师的头像
				if(teacher.getAvatar()!= null && !teacher.getAvatar().equals(0)){
					teacher.setArtHead(sysUploadService.get(teacher.getAvatar()).getFileFullPath());
				}
				articleBase.setTeacher(teacher);
				articleBase.setTeacherCourses(scTeacherCourseService.getTeacherCoursesByTeacherId(teacher.getId()));
			}else{
				System.out.println("通过文章无法找到对应老师");
			}
			if(articleBase.getSource() != null && !articleBase.getSource().equals("0")){
				Integer audioId = Integer.parseInt(articleBase.getSource());
				SysUpload audio = sysUploadService.get(audioId);
				articleBase.setHomeworkAudio(audio);
			}
		}
		return ArticleBaseList;
	}
	
	
	
	public List<ArticleBase> findChildArticleByParentId(Integer parentId) {
		ExpressionQuery query = new ExpressionQuery();
		query.add(new ValueExpression("parentId", parentId));
		query.add(new ValueExpression("isDeleted",0));
		query.setQueryAll(true);
		List<ArticleBase> ArticleBaseList = this.find(query);

		return ArticleBaseList;
	}

	/** 
	* @Title: getTotalList 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param oldArticleBase
	* @param @return    设定文件 
	* @return int    返回类型 
	* @version V1.0
	* @author xiaoren
	* @throws 
	*/
	public int getTotalList(ArticleBase oldArticleBase) {
		ExpressionQuery query = new ExpressionQuery();
		query.add(new ValueExpression("classId", oldArticleBase.getClassId()));
		query.add(new ValueExpression("sysClassId", oldArticleBase.getSysClassId()));
		query.add(new ValueExpression("parentId", 0));
		query.add(new ValueExpression("isDeleted",0));
		query.setQueryAll(true);
		List<ArticleBase> ArticleBaseList = this.find(query);
		
		return ArticleBaseList.size();
	}
	
	
	
	
	
	
	
}