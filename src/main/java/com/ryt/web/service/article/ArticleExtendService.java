package com.ryt.web.service.article;

import java.util.HashMap;
import java.util.List;

import org.durcframework.core.service.CrudService;

import com.ryt.web.dao.article.ArticleExtendDao;
import com.ryt.web.entity.article.ArticleExtend;
import org.springframework.stereotype.Service;

@Service
public class ArticleExtendService extends CrudService<ArticleExtend, ArticleExtendDao> {
	
	public HashMap<String, String> findABExtByabId(int abId) {
        List abExtQueryList = this.getDao().findABExtByabId(abId);

        HashMap<String, String> cellMap = new HashMap<String, String>();

        for (int i = 0; i < abExtQueryList.size(); i++) {
        	ArticleExtend ae = (ArticleExtend) abExtQueryList.get(i);
            cellMap.put(ae.getCellName(), ae.getCellValue());
        }

        return cellMap;
    }
	
	public ArticleExtend findABExtCheckboxByabIdAndCellname(int abId, String cellname) {
        return this.getDao().findABExtCheckboxByabIdAndCellname(abId, cellname);
    }
    public void delABExtByabId(int abId) {
        this.getDao().delABExtByabId(abId);
    }
}