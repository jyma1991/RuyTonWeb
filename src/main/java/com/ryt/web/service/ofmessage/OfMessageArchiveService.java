package com.ryt.web.service.ofmessage;

import java.util.List;

import org.durcframework.core.service.CrudService;
import com.ryt.web.dao.ofmessage.OfMessageArchiveDao;
import com.ryt.web.entity.ofmessage.OfMessageArchive;
import org.springframework.stereotype.Service;

@Service
public class OfMessageArchiveService extends CrudService<OfMessageArchive, OfMessageArchiveDao> {

	public List<OfMessageArchive> getMessageByPage(OfMessageArchive ofMessageArchive,boolean groupChat) {
		if(groupChat){
			return getDao().getGroupMessageByPage(ofMessageArchive);
		}
		return getDao().getMessageByPage(ofMessageArchive);
	}

	public List<OfMessageArchive> getOldMessageByPage(OfMessageArchive ofMessageArchive,boolean groupChat) {
		if(groupChat){
			return getDao().getOldGroupMessageByPage(ofMessageArchive);
		}
		 return getDao().getOldMessageByPage(ofMessageArchive);
	}

}