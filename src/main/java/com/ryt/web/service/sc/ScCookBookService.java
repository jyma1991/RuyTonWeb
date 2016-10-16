package com.ryt.web.service.sc;

import org.durcframework.core.service.CrudService;
import com.ryt.web.dao.sc.ScCookBookDao;
import com.ryt.web.entity.sc.ScCookBook;
import org.springframework.stereotype.Service;

@Service
public class ScCookBookService extends CrudService<ScCookBook, ScCookBookDao> {
}