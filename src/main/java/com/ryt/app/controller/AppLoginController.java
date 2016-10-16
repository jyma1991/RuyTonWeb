package com.ryt.app.controller;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.durcframework.core.SpringContext;
import org.durcframework.core.UserContext;
import org.durcframework.core.WebContext;
import org.durcframework.core.controller.CrudController;
import org.igniterealtime.restclient.RestApiClient;
import org.igniterealtime.restclient.entity.AuthenticationToken;
import org.igniterealtime.restclient.entity.MUCRoomEntity;
import org.igniterealtime.restclient.entity.UserEntity;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;
import org.jivesoftware.openfire.container.PluginManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.ryt.app.commom.DateUtil;
import com.ryt.app.commom.OpenfireConstants;
import com.ryt.app.commom.PayConstants;
import com.ryt.app.commom.RuyTonAppContants;
import com.ryt.app.entity.Pay;
import com.ryt.app.entity.WeiXinPay;
import com.ryt.app.service.AppArtcileBaseService;
import com.ryt.app.service.AppUserService;
import com.ryt.app.util.SendSmsUtil;
import com.ryt.app.util.StreamUtil;
import com.ryt.web.common.Config;
import com.ryt.web.common.Md5Encrypt;
import com.ryt.web.common.Utils;
import com.ryt.web.entity.article.ArticleBase;
import com.ryt.web.entity.finance.FinanceChargeOrder;
import com.ryt.web.entity.finance.FinanceChargeOrderBak;
import com.ryt.web.entity.sc.ScClass;
import com.ryt.web.entity.sc.ScClassTeacher;
import com.ryt.web.entity.sc.ScSchool;
import com.ryt.web.entity.sc.ScStudent;
import com.ryt.web.entity.sc.ScStudentParents;
import com.ryt.web.entity.sc.ScTeacher;
import com.ryt.web.entity.sc.ScTeacherCourse;
import com.ryt.web.entity.sys.SysClass;
import com.ryt.web.entity.sys.SysUpload;
import com.ryt.web.entity.user.User;
import com.ryt.web.service.article.ArticleBaseService;
import com.ryt.web.service.finance.FinanceChargeOrderService;
import com.ryt.web.service.sc.ScClassService;
import com.ryt.web.service.sc.ScClassTeacherService;
import com.ryt.web.service.sc.ScSchoolService;
import com.ryt.web.service.sc.ScSignInOutService;
import com.ryt.web.service.sc.ScStudentParentsService;
import com.ryt.web.service.sc.ScStudentService;
import com.ryt.web.service.sc.ScTeacherCourseService;
import com.ryt.web.service.sc.ScTeacherService;
import com.ryt.web.service.sys.SysClassService;
import com.ryt.web.service.sys.SysUploadService;
import com.ryt.web.util.CommonUtil;
import com.ryt.web.util.PasswordUtil;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Controller
@RequestMapping(value = "/app")
public class AppLoginController extends CrudController<User, AppUserService> {
	@Autowired
	ScStudentParentsService scStudentParentService;
	@Autowired
	FinanceChargeOrderService financeChargeOrderService;
	@Autowired
	ScSignInOutService scSignOutService;
	@Autowired
	ScStudentParentsService scStudentParentsService;
	@Autowired
	ScTeacherCourseService scTeacherCourseService;
	@Autowired
	AppArtcileBaseService appArtcileBaseService;
	@Autowired
	ArticleBaseService articleBaseService;

	private static OkHttpClient okHttpClient = new OkHttpClient();

	AuthenticationToken authenticationToken = new AuthenticationToken("admin", "12345");
	RestApiClient restApiClient = new RestApiClient("http://" + OpenfireConstants.SERVER_IP, 9090, authenticationToken);

	/**
	 * 登录
	 * 
	 * @param user
	 *            用户输入的信息
	 * @return
	 */
	@RequestMapping("/login.do")
	public @ResponseBody Map<String, Object> loginToApp(User user) {
		Map<String, Object> result = new HashMap<String, Object>();
		List<User> childrenList = null;
		try {
			// 登录先判断用户名是否存在
			User userEntity = this.getService().getUserByUserName(user.getUserName());
			ScStudentService scStudentService = SpringContext.getBean(ScStudentService.class);
			ScClassService scClassService = SpringContext.getBean(ScClassService.class);
			ScSchoolService schoolService = SpringContext.getBean(ScSchoolService.class);
			ScTeacherService scTeacherService = SpringContext.getBean(ScTeacherService.class);
			ScClassTeacherService classTeacherService = SpringContext.getBean(ScClassTeacherService.class);
			SysUploadService sysUploadService = SpringContext.getBean(SysUploadService.class);
			AppUserService appUserService = SpringContext.getBean(AppUserService.class);
			// JSONArray jsonArray = new JSONArray();
			if (userEntity == null) {
				result.put("success", false);
				result.put("info", "用户名或密码错误");
			}
			// 匹配用户密码
			else {

				boolean isPswdCorrect = PasswordUtil.validatePassword(user.getMd5Pwd(), userEntity.getPassword());
				if (isPswdCorrect) {
//					ReceiptPlugin receiptPlugin = new ReceiptPlugin();
//					receiptPlugin.initializePlugin(new PluginManager(new File("text.txt")), new );
					User entity = this.getService().appLogin(user.getUserName(),
							PasswordUtil.createHash(user.getMd5Pwd()));
					if (entity != null) {
						// 信息保存到openfire of_user表中
						UserEntity ofUser = restApiClient.getUser(entity.getUserName());
						//restApiClient.getRestClient().setConnectionTimeout(connectionTimeout);
						if (ofUser != null) {
							ofUser.setPassword(entity.getMd5Pwd());
							restApiClient.updateUser(ofUser);

						} else {
							ofUser = new UserEntity(entity.getUserName(), entity.getTrueName(), entity.getEmail(),
									user.getMd5Pwd());
						
							restApiClient.createUser(ofUser);
						}

						String token = Jwts.builder().setSubject(String.valueOf(entity.getId()))
								.claim("qq", entity.getQq()).claim("weixin", entity.getWeiXin()).setIssuedAt(new Date())
								.signWith(SignatureAlgorithm.HS256, "secretkey").compact();
						entity.setToken(token);
						// 更新用户信息
						User newUser = this.getService().update(entity);
						UserContext.getInstance().setUser(newUser);
						// 设置该用户的头像
						if (!StringUtils.isEmpty(newUser.getDefaultAvatar())) {
							newUser.setArtHead(newUser.getDefaultAvatar());
						} else {
							SysUpload sysUpload = sysUploadService.get(newUser.getAvatar());
							if (sysUpload != null) {
								newUser.setArtHead(sysUpload.getFileFullPath());
							}
						}

						// 家长登录
						if (newUser.getRoleProperty() == RuyTonAppContants.USER_TYPE_PARENT) {
							// 通过家长的id获取孩子相关信息
							childrenList = getService().getChildrens(newUser.getId());
							List<User> resultList = new ArrayList<User>();
							// 重新封装一些学生信息
							for (User child : childrenList) {
								ScStudent scStudent = scStudentService.getSctudentByUserId(child.getId());
								if (scStudent != null) {
									child.setSchoolId(scStudent.getSchoolId());
									child.setUserId(child.getId());
									child.setClassId(scStudent.getClassId());
									ScClass scClass = scClassService.get(scStudent.getClassId());
									// TODO
									// 根据家长小孩所在的班级创建班级聊天时,应该是在创建班级的时候同时创建聊天室
									// 创建班级房间
									MUCRoomEntity chatRoom = restApiClient.getChatRoom("scclass" + scClass.getId());
									if (chatRoom == null) {
										chatRoom = new MUCRoomEntity("scclass" + scClass.getId(),
												scClass.getClassName(), scClass.getClassName() + "的描述内容");
										chatRoom.setMaxUsers(50);
										chatRoom.setPublicRoom(true);
										chatRoom.setPersistent(true);
										chatRoom.setBroadcastPresenceRoles(new ArrayList<String>());
										restApiClient.createChatRoom(chatRoom);
									}

									child.setScClassName(scClass.getClassName());
									ScSchool scSchool = schoolService.getSchoolBySchoolId(scStudent.getSchoolId());
									child.setSchoolName(scSchool.getSchoolName());
									child.setAgentId(scSchool.getAgentId());
									child.setSchoolId(scSchool.getSchoolId());
									child.setRelativeName(
											appUserService.getUserRelativeName(newUser.getId(), child.getId()));
									if (!StringUtils.isEmpty(child.getDefaultAvatar())) {
										child.setBabyHead(child.getDefaultAvatar());
									}
									List<ScStudentParents> scStudentParents = scStudentParentService
											.getScStudentsParents(child.getId(), newUser.getId(), 0);

									if (scStudentParents != null && scStudentParents.size() > 0) {
										ScStudentParents scStudentParent = scStudentParents.get(0);
										child.setCardNo(scStudentParent.getCardNo());
										child.setRelatedTypeId(scStudentParent.getRelatedTypeId());
										String vipEndDate = scStudentParent.getVipEndDate();
										// 如果已经存在该学生的卡号，但是绑定日期未设置(TODO
										// 后台添加可能会产生的这个情况)
										if (!StringUtils.isEmpty(child.getCardNo())) {
											if (StringUtils.isEmpty(scStudentParent.getBindCardDate())) {
												// 修改绑定卡号日期
												scStudentParent.setBindCardDate(
														com.ryt.app.util.DateUtil.formateYMD(new Date().getTime()));
											}
											scStudentParent = scStudentParentService.update(scStudentParent);
											child.setBindCardDate(scStudentParent.getBindCardDate());
										}
										if (StringUtils.isEmpty(vipEndDate)) {
											child.setMemberFlag(RuyTonAppContants.MEMBER_FLAG_FALSE);
										} else {
											// 比较当前时间和会员到期时间
											Date vipEnd = DateUtil.getYMDDate(vipEndDate);
											if (vipEnd.getTime() < System.currentTimeMillis()) {
												child.setMemberFlag(RuyTonAppContants.MEMBER_FLAG_FALSE);
											} else {
												child.setMemberFlag(RuyTonAppContants.MEMBER_FLAG_TRUE);
											}
										}
									}
								}
								resultList.add(child);

							}
							newUser.setChildrens(resultList);
						}
						// 教师登录
						else if (newUser.getRoleProperty() == RuyTonAppContants.USER_TYPE_TEACHER) {
							// 设置老师的一些基本信息
							ScTeacher scTeacher = scTeacherService.findTechersByTeacherId(newUser.getId());
							newUser.setSchoolId(scTeacher.getSchoolId());
							// 查询老师所带的班级
							List<ScClassTeacher> classTeachers = classTeacherService
									.findScClassTechersByTeacherId(scTeacher.getTeacherId());
							List<Integer> classIds = new ArrayList<Integer>();
							List<ScClass> scClasses = new ArrayList<ScClass>();
							for (int i = 0; i < classTeachers.size(); i++) {
								classIds.add(classTeachers.get(i).getClassId());
								ScClass scClass = scClassService.get(classTeachers.get(i).getClassId());
								if (scClass != null) {
									MUCRoomEntity chatRoom = restApiClient.getChatRoom("scclass" + scClass.getId());
									if (chatRoom == null) {
										chatRoom = new MUCRoomEntity("scclass" + scClass.getId(),
												scClass.getClassName(), scClass.getClassName() + "的描述内容");
										chatRoom.setMaxUsers(50);
										chatRoom.setPublicRoom(true);
										chatRoom.setPersistent(true);
										chatRoom.setBroadcastPresenceRoles(new ArrayList<String>());
										restApiClient.createChatRoom(chatRoom);
									}
								}

								scClasses.add(scClass);
							}
							newUser.setClassIds(classIds);
							newUser.setScClasses(scClasses);

						}
						// 园长登录
						else if (newUser.getRoleProperty() == RuyTonAppContants.USER_TYPE_SCHOOL) {
							List<ScClass> allScClass = scClassService.getAllScClassBySchoolId(newUser);
							List<Integer> classIds = new ArrayList<Integer>();
							for (ScClass scClass : allScClass) {
								// 先查询房间是否存在
								MUCRoomEntity chatRoom = restApiClient.getChatRoom("scclass" + scClass.getId());
								if (chatRoom == null) {
									// 创建班级房间
									chatRoom = new MUCRoomEntity("scclass" + scClass.getId(), scClass.getClassName(),
											scClass.getClassName() + "的描述内容");
									chatRoom.setMaxUsers(50);
									chatRoom.setPublicRoom(true);
									chatRoom.setPersistent(true);
									chatRoom.setBroadcastPresenceRoles(new ArrayList<String>());
									restApiClient.createChatRoom(chatRoom);
								}
								classIds.add(scClass.getId());

							}

							newUser.setSchoolId(newUser.getId());
							newUser.setClassIds(classIds);
						}
						newUser.setMd5Pwd(user.getMd5Pwd());
						result.put("success", true);
						result.put("info", newUser);
						result.put("token", newUser.getToken());

						// result.put("childrens", jsonArray);

					}
				} else {
					result.put("success", false);
					result.put("info", "用户名或密码错误");
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	/**
	 * 发送短信验证码给用户手机
	 * 
	 * @param mobile
	 *            手机号码
	 * @return
	 */
	@RequestMapping(value = "/getCode.do")
	@ResponseBody
	public Map<String, Object> getCode(HttpServletRequest request, String mobile) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		User user = getService().getDao().getUserByUserName(mobile);
		if (user != null) {
			resultMap = SendSmsUtil.sendSms(mobile, request);
			boolean success = (Boolean) resultMap.get("success");
			String code = (String) resultMap.get("info");
			if (success) {
				// 验证码放入session,以便用户验证
				// request.getSession().setAttribute("code", code);
				HttpSession session = WebContext.getInstance().getSession();
				session.setAttribute(mobile, code);
			}

		} else {
			resultMap.put("success", false);
			resultMap.put("info", "用户不存在,请填写正确的手机号");
		}
		return resultMap;

	}

	/**
	 * 检查短信验证码是否正确
	 * 
	 * @param mobile
	 *            手机号码
	 * @return
	 */
	@RequestMapping(value = "/checkCode.do")
	@ResponseBody
	public Map<String, Object> checkCode(HttpServletRequest request, String mobile, String code) {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		// SendSmsUtil.getCode(request);
		if (!StringUtils.isEmpty(mobile) && !StringUtils.isEmpty(code)) {
			// 获取存放在该用户验证码信息
			HttpSession session = WebContext.getInstance().getSession();
			String correctCode = (String) session.getAttribute(mobile);
			if (!StringUtils.isEmpty(correctCode) && correctCode.equals(code)) {
				resultMap.put("success", true);
			} else {
				if (StringUtils.isEmpty(correctCode)) {
					resultMap.put("info", "短信验证码已失效，请重新获取验证码");
				} else {
					resultMap.put("info", "验证码错误");
				}
				resultMap.put("success", false);
			}
		}
		return resultMap;
	}

	/**
	 * 忘记密码
	 * 
	 * @param mobile
	 *            手机号码
	 * @param userPwd
	 *            用户密码
	 * @return
	 */
	@RequestMapping(value = "/forgetPwd.do")
	@ResponseBody
	public Map<String, Object> updatePwd(HttpServletRequest request, String mobile, String userPwd) {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		try {
			// 该用户的验证码置为空
			WebContext.getInstance().getSession().setAttribute(mobile, "");
			// 通过手机号码查找该用户
			User user = getService().getDao().getUserByUserName(mobile);
			if (user != null) {
				user.setUserPwd(PasswordUtil.createHash(userPwd));
				user.setToken("");
				// 更新
				getService().getDao().update(user);
				resultMap.put("success", true);
				resultMap.put("info", "修改成功,立即前往登录");
			} else {
				resultMap.put("success", false);
				resultMap.put("info", "用户信息错误");
			}

		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("success", false);
			resultMap.put("info", "修改密码失败,请重试");
		}

		return resultMap;
	}

	/**
	 * 支付宝回调接口
	 * 
	 * @param out_trade_no
	 *            订单编号
	 * @return
	 */
	@RequestMapping("/zhiFuBaoNotify.do")
	@ResponseBody
	public String paySuccess(Pay pay, FinanceChargeOrder changeOrder, HttpServletRequest request,
			HttpServletResponse response, FinanceChargeOrderBak financeChargeOrder) {
		try {

			// sign验证
			Map<String, String> sParaTemp = new HashMap<String, String>();
			sParaTemp.put("body", pay.getBody());
			sParaTemp.put("buyer_email", pay.getBuyer_email());
			sParaTemp.put("buyer_id", pay.getBuyer_id());
			sParaTemp.put("extra_common_param", pay.getExtra_common_param());
			sParaTemp.put("gmt_create", pay.getGmt_create());
			sParaTemp.put("gmt_payment", pay.getGmt_payment());
			sParaTemp.put("is_total_fee_adjust", pay.getIs_total_fee_adjust());
			sParaTemp.put("notify_id", pay.getNotify_id());
			sParaTemp.put("notify_time", pay.getNotify_time());
			sParaTemp.put("notify_type", pay.getNotify_type());
			sParaTemp.put("out_trade_no", pay.getOut_trade_no());
			sParaTemp.put("price", pay.getPrice());
			sParaTemp.put("quantity", pay.getQuantity());
			sParaTemp.put("seller_email", pay.getSeller_email());
			sParaTemp.put("seller_id", pay.getSeller_id());
			sParaTemp.put("subject", pay.getSubject());
			sParaTemp.put("total_fee", pay.getTotal_fee());
			sParaTemp.put("trade_no", pay.getTrade_no());
			sParaTemp.put("trade_status", pay.getTrade_status());
			sParaTemp.put("use_coupon", pay.getUse_coupon());

			String str = "";
			if (!StringUtils.isEmpty(pay.getBody())) {
				str = "body=" + pay.getBody();
			}
			if (!StringUtils.isEmpty(pay.getBuyer_email())) {
				str += "&buyer_email=" + pay.getBuyer_email();
			}
			if (!StringUtils.isEmpty(pay.getBuyer_id())) {
				str += "&buyer_id=" + pay.getBuyer_id();
			}
			if (!StringUtils.isEmpty(pay.getExtra_common_param())) {
				str += "&extra_common_param=" + pay.getExtra_common_param();
			}
			if (!StringUtils.isEmpty(pay.getGmt_create())) {
				str += "&gmt_create=" + pay.getGmt_create();
			}
			if (!StringUtils.isEmpty(pay.getGmt_payment())) {
				str += "&gmt_payment=" + pay.getGmt_payment();
			}
			if (!StringUtils.isEmpty(pay.getIs_total_fee_adjust())) {
				str += "&is_total_fee_adjust=" + pay.getIs_total_fee_adjust();
			}
			if (!StringUtils.isEmpty(pay.getNotify_id())) {
				str += "&notify_id=" + pay.getNotify_id();
			}
			if (!StringUtils.isEmpty(pay.getNotify_time())) {
				str += "&notify_time=" + pay.getNotify_time();
			}
			if (!StringUtils.isEmpty(pay.getNotify_type())) {
				str += "&notify_type=" + pay.getNotify_type();
			}
			if (!StringUtils.isEmpty(pay.getOut_trade_no())) {
				str += "&out_trade_no=" + pay.getOut_trade_no();
			}
			if (!StringUtils.isEmpty(pay.getPrice())) {
				str += "&price=" + pay.getPrice();
			}
			if (!StringUtils.isEmpty(pay.getQuantity())) {
				str += "&quantity=" + pay.getQuantity();
			}
			if (!StringUtils.isEmpty(pay.getSeller_email())) {
				str += "&seller_email=" + pay.getSeller_email();
			}
			if (!StringUtils.isEmpty(pay.getSeller_id())) {
				str += "&seller_id=" + pay.getSeller_id();
			}
			if (!StringUtils.isEmpty(pay.getSubject())) {
				str += "&subject=" + pay.getSubject();
			}
			if (!StringUtils.isEmpty(pay.getTotal_fee())) {
				str += "&total_fee=" + pay.getTotal_fee();
			}
			if (!StringUtils.isEmpty(pay.getTrade_no())) {
				str += "&trade_no=" + pay.getTrade_no();
			}
			if (!StringUtils.isEmpty(pay.getTrade_status())) {
				str += "&trade_status=" + pay.getTrade_status();
			}
			if (!StringUtils.isEmpty(pay.getUse_coupon())) {
				str += "&use_coupon=" + pay.getUse_coupon();
			}
			// 拼接支付宝账号的sign
			str += "&sign=" + RuyTonAppContants.PAY_SIGN;
			// md5进行加密
			String mySign = Md5Encrypt.encrypt(str);

			boolean signFlag = true;
			// TODO 签名为验证
			if (signFlag) {
				FinanceChargeOrder order = financeChargeOrderService.getByTradeNo(pay.getOut_trade_no());

				if (order != null) {
					if (RuyTonAppContants.SELLER_ID.equals(pay.getSeller_id())
							&& pay.getTotal_fee().equals(order.getTotalFee().toString())) {
						if (pay.getTrade_status().equals("TRADE_SUCCESS")) {
							if (order.getPayStatus().toString().equals("0")) {
								order.setPayStatus(RuyTonAppContants.PRODUCT_PAY_STATUS_PAY_SUCCESS);
								order.setBuyerEmail(pay.getBuyer_email());
								// order.setPayTime(pay.getNotify_time());
								order = financeChargeOrderService.update(order);
								// 修改user的vip状态
								String userNames = order.getChargeAccountId();
								if (!StringUtils.isEmpty(userNames)) {
									String[] userIds = userNames.split(",");
									for (String childId : userIds) {
										// 获取孩子的id
										User child = this.getService().get(Integer.parseInt(childId));
										// 获取购买者和孩子信息集合
										List<ScStudentParents> temp = scStudentParentService
												.getScStudentsParents(child.getId(), order.getUserId(), 0);
										if (temp != null && temp.size() > 0) {
											// TODO 可能会存在问题
											ScStudentParents scStudentParent = temp.get(0);
											// 之前不是vip则直接修改他的vipenddate
											Date currentDate = null;
											if (StringUtils.isEmpty(scStudentParent.getVipEndDate())) {
												currentDate = com.ryt.app.util.DateUtil
														.parseYMD(scStudentParent.getVipEndDate());
											} else {
												Date vipEnd = DateUtil.getYMDDate(scStudentParent.getVipEndDate());
												// 过期了，直接将当前日期负值更新
												if (vipEnd.getTime() < System.currentTimeMillis()) {
													currentDate = com.ryt.app.util.DateUtil
															.parseYMD(scStudentParent.getVipEndDate());
												} else {
													currentDate = vipEnd;
												}
											}
											// 一年
											if (order.getProductId() == RuyTonAppContants.PRODUCT_ONE_YEAR) {
												scStudentParent.setVipEndDate(com.ryt.app.util.DateUtil
														.addYear(RuyTonAppContants.PRODUCT_ONE_YEAR, currentDate));
											} else if (order.getProductId() == RuyTonAppContants.PRODUCT_TWO_YEAR) {
												scStudentParent.setVipEndDate(com.ryt.app.util.DateUtil
														.addYear(RuyTonAppContants.PRODUCT_TWO_YEAR, currentDate));
											} else if (order.getProductId() == RuyTonAppContants.PRODUCT_THREE_YEAR) {
												scStudentParent.setVipEndDate(com.ryt.app.util.DateUtil
														.addYear(RuyTonAppContants.PRODUCT_THREE_YEAR, currentDate));
											}
											scStudentParentService.update(scStudentParent);
										}

									}
								}

								return "success";
							} else {
								return "fail";
							}

						} else {
							return "fail";
						}
					}

				} else {
					return "fail";
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
			return "fail";
		}
		return "fail";
	}

	@RequestMapping(value = "weiXinNotify.do")
	@ResponseBody
	public String weiXinNotify(WeiXinPay pay, HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		String resultXml = "";
		ServletInputStream in = request.getInputStream();
		// 转换微信post过来的xml内容
		XStream xs = new XStream(new DomDriver());
		xs.alias("xml", WeiXinPay.class);
		String xmlMsg = StreamUtil.convertStreamToString(in);
		System.out.println(xmlMsg);
		JSONObject obj = new JSONObject();
		try {
			InputStream is = new ByteArrayInputStream(xmlMsg.getBytes("utf-8"));
			SAXBuilder sb = new SAXBuilder();
			Document doc = sb.build(is);
			Element root = doc.getRootElement();
			obj.put(root.getName(), iterateElement(root));
			JSONObject xmlJson = obj.getJSONObject("xml");
			String return_code = xmlJson.getString("return_code").split("\"")[1];// aftCnv.xml.prepay_id;
			if ("SUCCESS".equals(return_code)) {
				// TODO 为根据微信支付建议的进行签名验证，在这只做了一些简单的信息认证
				String appid = xmlJson.getString("appid").split("\"")[1];
				String out_trade_no = xmlJson.getString("out_trade_no").split("\"")[1];
				String total_fee = xmlJson.getString("total_fee").split("\"")[1];
				String mch_id = xmlJson.getString("mch_id").split("\"")[1];
				FinanceChargeOrder order = financeChargeOrderService.getByTradeNo(new String(out_trade_no));
				if (order == null) {
					resultXml = "<xml>" + "<return_code>FAIL</return_code>" + "</xml>";
				} else {
					if (PayConstants.WEIXIN_APPID.equals(appid) && PayConstants.WEIXIN_MCHID.equals(mch_id)) {
						BigDecimal bigDecimal = new BigDecimal(total_fee).divide(new BigDecimal(100));
						if (bigDecimal.toString().equals(order.getTotalFee().toString())) {
							if (order.getPayStatus().toString().equals("0")) {
								// 修改订单状态
								order.setPayStatus(1);
								order = financeChargeOrderService.update(order);
								// 修改user的vip状态
								String userNames = order.getChargeAccountId();
								if (!StringUtils.isEmpty(userNames)) {
									String[] userIds = userNames.split(",");
									for (String childId : userIds) {
										// 获取孩子的id
										User child = this.getService().get(Integer.parseInt(childId));
										// 获取购买者和孩子信息集合
										List<ScStudentParents> temp = scStudentParentService
												.getScStudentsParents(child.getId(), order.getUserId(), 0);
										if (temp != null && temp.size() > 0) {
											ScStudentParents scStudentParent = temp.get(0);
											// 之前不是vip则直接修改他的vipenddate
											Date currentDate = null;
											if (StringUtils.isEmpty(scStudentParent.getVipEndDate())) {
												currentDate = com.ryt.app.util.DateUtil
														.parseYMD(scStudentParent.getVipEndDate());
											} else {
												Date vipEnd = DateUtil.getYMDDate(scStudentParent.getVipEndDate());
												// 过期了，直接将当前日期负值更新
												if (vipEnd.getTime() < System.currentTimeMillis()) {
													currentDate = com.ryt.app.util.DateUtil
															.parseYMD(scStudentParent.getVipEndDate());
												} else {
													currentDate = vipEnd;
												}
											}
											// 一年
											if (order.getProductId() == RuyTonAppContants.PRODUCT_ONE_YEAR) {
												scStudentParent.setVipEndDate(com.ryt.app.util.DateUtil
														.addYear(RuyTonAppContants.PRODUCT_ONE_YEAR, currentDate));
											} else if (order.getProductId() == RuyTonAppContants.PRODUCT_TWO_YEAR) {
												scStudentParent.setVipEndDate(com.ryt.app.util.DateUtil
														.addYear(RuyTonAppContants.PRODUCT_TWO_YEAR, currentDate));
											} else if (order.getProductId() == RuyTonAppContants.PRODUCT_THREE_YEAR) {
												scStudentParent.setVipEndDate(com.ryt.app.util.DateUtil
														.addYear(RuyTonAppContants.PRODUCT_THREE_YEAR, currentDate));
											}
											scStudentParentService.update(scStudentParent);
										}

										resultXml = "<xml>" + "<return_code>SUCCESS</return_code>" + "</xml>";
									}
								}
							} else {
								resultXml = "<xml>" + "<return_code>SUCCESS</return_code>" + "</xml>";
							}

						}
					}
				}
			} else {
				resultXml = "<xml>" + "<return_code>FAIL</return_code>" + "</xml>";
			}

		} catch (Exception e) {
			e.printStackTrace();
			resultXml = "<xml>" + "<return_code>FAIL</return_code>" + "</xml>";
		}

		return resultXml;
	}

	@RequestMapping(value = "/getPayByWeiXin.do", method = RequestMethod.GET)
	public @ResponseBody JSONObject getPayByWeiXin() {
		String appid = "wxd04b8c76e4e8c63e";
		String mch_id = "1307420701";
		String nonce_str = new CommonUtil().getUUID();
		String spbill_create_ip = "192.168.0.157";
		String trade_type = "APP";
		int total_fee = 1;// ;parseInt(Math.round($scope.totalFee * 100));
		String body = "test pay";
		String callbackUrl = "www.baidu.com";
		String tradeNo = "201601181635";
		String stringA = "appid=" + appid + "&body=" + body + "&mch_id=" + mch_id + "&nonce_str=" + nonce_str
				+ "&notify_url=" + callbackUrl + "&out_trade_no=" + tradeNo + "&spbill_create_ip=" + spbill_create_ip
				+ "&total_fee=" + total_fee + "&trade_type=" + trade_type;
		String key = "25BB02c26529e99fe15d80de523e84f8";
		String stringSignTemp = stringA + "&key=" + key;
		String sign = Md5Encrypt.encrypt(stringSignTemp).toUpperCase();

		String xmlData = "<xml>" + "<appid>" + appid + "</appid>" + "<body>" + body + "</body>" + "<mch_id>" + mch_id
				+ "</mch_id>" + "<nonce_str>" + nonce_str + "</nonce_str>" + "<notify_url>" + callbackUrl
				+ "</notify_url>" + "<out_trade_no>" + tradeNo + "</out_trade_no>" + "<spbill_create_ip>"
				+ spbill_create_ip + "</spbill_create_ip>" + "<total_fee>" + total_fee + "</total_fee>" + "<trade_type>"
				+ trade_type + "</trade_type>" + "<sign>" + sign + "</sign>" + "</xml>";
		System.out.println(xmlData);
		Response response = null;
		String contentType = "application/xml";
		try {
			URL url = new URL("https://api.mch.weixin.qq.com/pay/unifiedorder");
			byte[] bodyB = xmlData.getBytes(Config.UTF8);
			MediaType type = MediaType.parse(contentType);
			RequestBody rBody = RequestBody.create(type, bodyB);
			Request request = new Request.Builder().url(url).post(rBody).header("User-Agent", Utils.getUserAgent())
					.header("Authorization", xmlData).build();
			response = okHttpClient.newCall(request).execute();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		String xmlContent = "";
		// JSONObject object = null;
		if (response.isSuccessful()) {
			// JSONParser parser;
			try {

				xmlContent = response.body().string();
				System.out.println(xmlContent);
				/**
				 * parser = new JSONParser(response.body().string());
				 * System.out.println(parser.toString()); object =
				 * (JSONObject)parser.parse();
				 **/
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			// object = object.getJSONObject("data");
			// System.out.println(object.toString());

		}
		/**
		 * $http({ method: 'POST', url:
		 * 'https://api.mch.weixin.qq.com/pay/unifiedorder', data: xmlData,
		 * headers: { "Content-Type": 'application/xml' } }).then(function(msg){
		 **/
		JSONObject obj = new JSONObject();
		try {
			InputStream is = new ByteArrayInputStream(xmlContent.getBytes("utf-8"));
			SAXBuilder sb = new SAXBuilder();
			Document doc = sb.build(is);
			Element root = doc.getRootElement();
			obj.put(root.getName(), iterateElement(root));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		// alert(JSON.stringify(msg));
		// var xmlContent = msg.data;
		// var x2js = new X2JS();
		// var aftCnv = x2js.xml_str2json(xmlContent);

		String prepay_id = obj.getJSONObject("xml").getString("prepay_id").split("\"")[1];// aftCnv.xml.prepay_id;
		// var temp = JSON.parse(prepay_id);
		// alert("_cdata="+temp._cdata);
		// alert("prepay_id="+prepay_id);
		System.out.println(prepay_id);

		long timestamp = new Date().getTime();
		String packageValue = "Sign=WXPay";
		// 参与签名的字段名为appId，partnerId，prepayId，nonceStr，timeStamp，package。注意：package的值格式为Sign=WXPay
		String stringB = "appid=" + appid + "&partnerid=" + mch_id + "&prepayId=" + prepay_id + "&package="
				+ packageValue + "&noncestr=" + nonce_str + "&timestamp=" + timestamp;
		String stringSignTempB = stringB + "&key=" + key;
		String signB = Md5Encrypt.encrypt(stringSignTempB).toUpperCase();
		System.out.println(mch_id);
		System.out.println(nonce_str);
		System.out.println(mch_id);
		System.out.println(sign + " " + signB);
		// var params = {
		// mch_id: mch_id, // merchant id
		// prepay_id:"1234567" , // prepay id
		// noncestr: nonce_str, // nonce
		// timestamp: new Date().getTime(), // timestamp
		// sign: sign, // signed string
		// };
		JSONObject object = new JSONObject();
		object.put("mch_id", mch_id);
		object.put("prepay_id", prepay_id);
		object.put("noncestr", nonce_str);
		object.put("timestamp", timestamp);
		object.put("sign", sign);
		System.out.println(object.toJSONString());

		// var stringSignTempB = stringB+"&key="+key;
		// alert(stringSignTempB)
		// var signB = (faultylabs.MD5(stringSignTempB)).toUpperCase();
		// alert(signB);
		return object;
	}

	/**
	 * 一个迭代方法
	 * 
	 * @param element
	 *            : org.jdom.Element
	 * @return java.util.Map 实例
	 */
	@SuppressWarnings("unchecked")
	private static Map iterateElement(Element element) {
		List jiedian = element.getChildren();
		Element et = null;
		Map obj = new HashMap();
		List list = null;
		for (int i = 0; i < jiedian.size(); i++) {
			list = new LinkedList();
			et = (Element) jiedian.get(i);
			if (et.getTextTrim().equals("")) {
				if (et.getChildren().size() == 0)
					continue;
				if (obj.containsKey(et.getName())) {
					list = (List) obj.get(et.getName());
				}
				list.add(iterateElement(et));
				obj.put(et.getName(), list);
			} else {
				if (obj.containsKey(et.getName())) {
					list = (List) obj.get(et.getName());
				}
				list.add(et.getTextTrim());
				obj.put(et.getName(), list);
			}
		}
		return obj;
	}

}
