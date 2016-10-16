package com.ryt.web.dao.ofmessage;

import java.util.List;

import org.durcframework.core.dao.BaseDao;

import com.ryt.web.entity.ofmessage.OfMessageArchive;

public interface OfMessageArchiveDao extends BaseDao<OfMessageArchive> {

	List<OfMessageArchive> getMessageByPage(OfMessageArchive ofMessageArchive);

	List<OfMessageArchive> getOldMessageByPage(OfMessageArchive ofMessageArchive);
	
	/**
	 * 获取群聊相关数据
	 * @param ofMessageArchive
	 * @return
	 */
	List<OfMessageArchive> getGroupMessageByPage(OfMessageArchive ofMessageArchive);
	
	/**
	 * 获取群聊记录
	 * @param ofMessageArchive
	 * @return
	 */
	List<OfMessageArchive> getOldGroupMessageByPage(OfMessageArchive ofMessageArchive);
}