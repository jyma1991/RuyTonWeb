package com.ryt.web.service.sc;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.durcframework.core.expression.ExpressionQuery;
import org.durcframework.core.service.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ryt.app.commom.RuyTonAppContants;
import com.ryt.app.util.DateUtil;
import com.ryt.web.common.Utils;
import com.ryt.web.dao.sc.ScSignInOutDao;
import com.ryt.web.entity.article.ArticleBase;
import com.ryt.web.entity.sc.ScClass;
import com.ryt.web.entity.sc.ScClassSch;
import com.ryt.web.entity.sc.ScClassStudentSingInfo4Teacher;
import com.ryt.web.entity.sc.ScSignDay;
import com.ryt.web.entity.sc.ScSignInOut;
import com.ryt.web.entity.sc.ScSignInOutSch;
import com.ryt.web.entity.sc.ScStudent;
import com.ryt.web.entity.sc.ScStudentSch;
import com.ryt.web.entity.sys.SysUpload;
import com.ryt.web.entity.user.User;
import com.ryt.web.service.sys.SysUploadService;
import com.ryt.web.service.user.UserService;

@Service
public class ScSignInOutService extends CrudService<ScSignInOut, ScSignInOutDao> {
	@Autowired
	SysUploadService sysUploadService;
	@Autowired
	ScClassService scClassService;
	@Autowired
	ScStudentService scStudentServices;
	@Autowired
	UserService userService;

	/**
	 * 查询学生签到信息
	 * 
	 * @author Jyma
	 * @param scSignInOut.getStudentId()
	 *            学生ID
	 * @param scSignInOut.getSignDate().getYear()
	 *            年份
	 * @param scSignInOut.getSignDate().getMonth()
	 *            月份
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public List<ScSignDay> getSignInfoStudent4Student(ScSignInOut scSignInOut) {
		// 查询实体类 学生ID 年 月份
		ScSignInOutSch searchEntitySch = new ScSignInOutSch();
		searchEntitySch.setStudentIdSch(scSignInOut.getStudentId());
		searchEntitySch.setSignInTimeStartSch(Utils.getFirstDayOfMonth(scSignInOut.getSignDate().getYear() + 1900,
				scSignInOut.getSignDate().getMonth()));
		searchEntitySch.setSignInTimeEndSch(Utils.getNextMonthFirst(scSignInOut.getSignDate().getYear() + 1900,
				scSignInOut.getSignDate().getMonth()));
		List<ScSignInOut> listSignIn = this
				.find(ExpressionQuery.buildQueryAll().addAnnotionExpression(searchEntitySch));// 请求月份的签入信息列表

		searchEntitySch = new ScSignInOutSch();
		searchEntitySch.setStudentIdSch(scSignInOut.getStudentId());
		searchEntitySch.setSignOutTimeStartSch(Utils.getFirstDayOfMonth(scSignInOut.getSignDate().getYear() + 1900,
				scSignInOut.getSignDate().getMonth()));
		searchEntitySch.setSignOutTimeEndSch(Utils.getNextMonthFirst(scSignInOut.getSignDate().getYear() + 1900,
				scSignInOut.getSignDate().getMonth()));
		List<ScSignInOut> listSignOut = this
				.find(ExpressionQuery.buildQueryAll().addAnnotionExpression(searchEntitySch));// 请求月份的签出信息列表
		// 以月份中的每天为key 签入和签出作为子数据列表
		Map<String, ScSignDay> mapSignDay = new HashMap<String, ScSignDay>();
		List<ScSignDay> listSignDay = new ArrayList<ScSignDay>();
		for (ScSignInOut scSignIn : listSignIn) {
			if (mapSignDay.containsKey(DateUtil.dateToStr(scSignIn.getSignInTime()))) {
				continue;
			} else {
				ScSignDay scSignDayIn = new ScSignDay();
				scSignDayIn.setSignDate(DateUtil.dateToStr(scSignIn.getSignInTime()));
				scSignDayIn.getSignDetail().put("signInTime", scSignIn.getSignInTime());
				SysUpload sysUpload = sysUploadService.get(scSignIn.getPicId());
				if (sysUpload != null) {
					scSignDayIn.getSignDetail().put("picIn", sysUpload.getFileFullPath());
				}
				mapSignDay.put(DateUtil.dateToStr(scSignIn.getSignInTime()), scSignDayIn);
			}
		}

		for (ScSignInOut scSignOut : listSignOut) {
			if (mapSignDay.containsKey(DateUtil.dateToStr(scSignOut.getSignOutTime()))) {
				mapSignDay.get(DateUtil.dateToStr(scSignOut.getSignOutTime())).getSignDetail().put("signOutTime",
						scSignOut.getSignOutTime());
				SysUpload sysUpload = sysUploadService.get(scSignOut.getPicId());
				if (sysUpload != null) {
					mapSignDay.get(DateUtil.dateToStr(scSignOut.getSignOutTime())).getSignDetail().put("picOut",
							sysUpload.getFileFullPath());
				}

			} else {
				ScSignDay scSignDayOut = new ScSignDay();
				scSignDayOut.setSignDate(DateUtil.dateToStr(scSignOut.getSignOutTime()));
				scSignDayOut.getSignDetail().put("signOutTime", scSignOut.getSignOutTime());
				SysUpload sysUpload = sysUploadService.get(scSignOut.getPicId());
				if (sysUpload != null) {
					scSignDayOut.getSignDetail().put("picOut", sysUpload.getFileFullPath());
					mapSignDay.put(DateUtil.dateToStr(scSignOut.getSignOutTime()), scSignDayOut);
				}

			}
		}
		Iterator<Entry<String, ScSignDay>> iter = mapSignDay.entrySet().iterator();
		while (iter.hasNext()) {
			Map.Entry entry = (Map.Entry) iter.next();
			ScSignDay val = (ScSignDay) entry.getValue();
			listSignDay.add(val);
		}

		return listSignDay;
	}

	/**
	 * 老师获取签到信息
	 * 
	 * @param scClassSch。managerIdSch
	 *            班主任ID
	 * @param scClassSch.signDateSch
	 *            签到日期
	 * @return
	 */
	public List<ScClassStudentSingInfo4Teacher> getSignInfo4Teacher(ScClassSch scClassSch) {
		// 获取该班主任的班级列表
		scClassSch.setPlaceTypeIdSch(RuyTonAppContants.CLASS_TYPE_CLASSROOM);
		List<ScClass> listScClass = scClassService
				.find(ExpressionQuery.buildQueryAll().addAnnotionExpression(scClassSch));
		List<ScClassStudentSingInfo4Teacher> listResult = new ArrayList<ScClassStudentSingInfo4Teacher>();

		for (ScClass scClass : listScClass) {
			ScClassStudentSingInfo4Teacher scSign = new ScClassStudentSingInfo4Teacher();
			scSign.setClassName(scClass.getClassName());
			Integer signInCount = 0;
			Integer signOutCount = 0;
			// 获取每个班级的学生
			ScStudentSch scStudentSch = new ScStudentSch();
			scStudentSch.setClassIdSch(scClass.getId());
			List<ScStudent> listStudent = scStudentServices
					.find(ExpressionQuery.buildQueryAll().addAnnotionExpression(scStudentSch));
			for (ScStudent scStudent : listStudent) {
				User studentUser = userService.get(scStudent.getStudentId());
				if (studentUser != null) {
					scStudent.setUser(studentUser);
				}

				ScSignInOutSch searchEntitySch = new ScSignInOutSch();
				searchEntitySch.setStudentIdSch(scStudent.getStudentId());
				searchEntitySch.setSignInTimeStartSch(Utils.getFirstSecondOfDay(scClassSch.getSignDateSch()));
				searchEntitySch.setSignInTimeEndSch(Utils.getFirstSecondOfNextDay(scClassSch.getSignDateSch()));
				List<ScSignInOut> listSignIn = this
						.find(ExpressionQuery.buildQueryAll().addAnnotionExpression(searchEntitySch));
				if (listSignIn.size() >= 1) {
					scStudent.setSignStatu(1);
					scStudent.setSignInTime(listSignIn.get(0).getSignInTime());
					signInCount++;
				}

				searchEntitySch = new ScSignInOutSch();
				searchEntitySch.setStudentIdSch(scStudent.getStudentId());
				searchEntitySch.setSignOutTimeStartSch(Utils.getFirstSecondOfDay(scClassSch.getSignDateSch()));
				searchEntitySch.setSignOutTimeEndSch(Utils.getFirstSecondOfNextDay(scClassSch.getSignDateSch()));
				List<ScSignInOut> listSignOut = this
						.find(ExpressionQuery.buildQueryAll().addAnnotionExpression(searchEntitySch));
				if (listSignOut.size() >= 1) {
					scStudent.setSignOutStatus(1);
					;
					signOutCount++;
					scStudent.setSignOutTime(listSignOut.get(0).getSignOutTime());
				}
			}
			scSign.setSignInCount(signInCount);
			scSign.setSignOutCount(signOutCount);
			scSign.setListSignIn(listStudent);
			listResult.add(scSign);
		}

		return listResult;
	}

	public List<ArticleBase> getSignNotifyList(Integer sysClassId, Integer schoolId) {
		return getDao().getSignNotifyList(sysClassId, schoolId);
	}

	public List<ScSignInOut> findBySignDate(Date signDate, Integer studentId) {
		ScSignInOutSch scSignInOutSch = new ScSignInOutSch();
		scSignInOutSch.setStudentIdSch(studentId);
		scSignInOutSch.setSignDateSch(signDate);
		return this.find(ExpressionQuery.buildQueryAll().addAnnotionExpression(scSignInOutSch));
	}

	public List<ScClassStudentSingInfo4Teacher> getClassSignInfo4School(ScClassSch scClassSch) {

		// 只查询班级的地点 , 获取该学校的班级列表
		scClassSch.setPlaceTypeIdSch(RuyTonAppContants.CLASS_TYPE_CLASSROOM);
		List<ScClass> listScClass = scClassService
				.find(ExpressionQuery.buildQueryAll().addAnnotionExpression(scClassSch));
		List<ScClassStudentSingInfo4Teacher> listResult = new ArrayList<ScClassStudentSingInfo4Teacher>();

		for (ScClass scClass : listScClass) {
			ScClassStudentSingInfo4Teacher scSign = new ScClassStudentSingInfo4Teacher();
			scSign.setClassName(scClass.getClassName());
			Integer signInCount = 0;
			Integer signOutCount = 0;
			Integer studentCount = 0;
			// 获取每个班级的学生
			ScStudentSch scStudentSch = new ScStudentSch();
			scStudentSch.setClassIdSch(scClass.getId());
			List<ScStudent> listStudent = scStudentServices
					.find(ExpressionQuery.buildQueryAll().addAnnotionExpression(scStudentSch));
			studentCount = listStudent.size();

			for (ScStudent scStudent : listStudent) {

				User studentUser = userService.get(scStudent.getStudentId());
				if (studentUser != null) {
					scStudent.setUser(studentUser);
				}

				ScSignInOutSch searchEntitySch = new ScSignInOutSch();
				searchEntitySch.setStudentIdSch(scStudent.getStudentId());
				searchEntitySch.setSignInTimeStartSch(Utils.getFirstSecondOfDay(scClassSch.getSignDateSch()));
				searchEntitySch.setSignInTimeEndSch(Utils.getFirstSecondOfNextDay(scClassSch.getSignDateSch()));

				if (this.findTotalCount(ExpressionQuery.buildQueryAll().addAnnotionExpression(searchEntitySch)) >= 1) {
					signInCount++;
				}

				searchEntitySch = new ScSignInOutSch();
				searchEntitySch.setStudentIdSch(scStudent.getStudentId());
				searchEntitySch.setSignOutTimeStartSch(Utils.getFirstSecondOfDay(scClassSch.getSignDateSch()));
				searchEntitySch.setSignOutTimeEndSch(Utils.getFirstSecondOfNextDay(scClassSch.getSignDateSch()));
				if (this.findTotalCount(ExpressionQuery.buildQueryAll().addAnnotionExpression(searchEntitySch)) >= 1) {
					signOutCount++;
				}
			}
			scSign.setSignInCount(signInCount);
			scSign.setSignOutCount(signOutCount);
			scSign.setStudentCount(studentCount);
			listResult.add(scSign);
		}
		return listResult;
	}
}