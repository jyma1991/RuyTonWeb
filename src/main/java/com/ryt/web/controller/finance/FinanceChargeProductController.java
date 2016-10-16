package com.ryt.web.controller.finance;

import org.durcframework.core.GridResult;
import org.durcframework.core.MessageResult;
import org.durcframework.core.controller.CrudController;
import com.ryt.web.entity.finance.FinanceChargeProduct;
import com.ryt.web.entity.finance.FinanceChargeProductSch;
import com.ryt.web.service.finance.FinanceChargeProductService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class FinanceChargeProductController extends
		CrudController<FinanceChargeProduct, FinanceChargeProductService> {

		//新增记录
	@RequestMapping("/addFinanceChargeProduct.do")
	public @ResponseBody
	MessageResult addFinanceChargeProduct(FinanceChargeProduct entity) {
		return this.save(entity);
	}

	//删除记录
	@RequestMapping("/delFinanceChargeProduct.do")
	public @ResponseBody
	MessageResult delFinanceChargeProduct(FinanceChargeProduct entity) {
		return this.delete(entity);
	}
	
	//修改记录
	@RequestMapping("/updateFinanceChargeProduct.do")
	public @ResponseBody
	MessageResult updateFinanceChargeProduct(FinanceChargeProduct entity) {
		return this.update(entity);
	}

	//条件查询分页操作
	@RequestMapping("/listFinanceChargeProduct.do")
	public @ResponseBody
	GridResult listFinanceChargeProduct(FinanceChargeProductSch searchEntitySch) {
		return this.query(searchEntitySch);
	}

	
	//条件查询并返回所有记录
	@RequestMapping("/listAllFinanceChargeProduct.do")
    public @ResponseBody Object listAllFinanceChargeProduct(FinanceChargeProductSch searchEntitySch) {
        return this.queryAll(searchEntitySch);
    }

    //获取详细信息
	@RequestMapping("/getFinanceChargeProductById.do")
    public @ResponseBody FinanceChargeProduct getFinanceChargeProductById(FinanceChargeProduct entity) {
        return this.getService().get(entity.getId());
    }

 	

}