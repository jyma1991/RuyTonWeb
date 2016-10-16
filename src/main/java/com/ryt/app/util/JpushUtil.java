package com.ryt.app.util;

import cn.jpush.api.JPushClient;
import cn.jpush.api.push.model.Message;
import cn.jpush.api.push.model.Options;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.AndroidNotification;
import cn.jpush.api.push.model.notification.IosNotification;
import cn.jpush.api.push.model.notification.Notification;

public class JpushUtil {

	static JPushClient jpushClient = new JPushClient("e1afb88d99ea214e25868a6b", "3737da9e9b95088d794e3a8c", 3);
	static JPushClient iosJpushClient = new JPushClient("7c26f5330ccd8dac136a8033", "fff0c11438d5ed541f38e537", 3);

	/**
	 * 通过tag发送
	 * 
	 * @return
	 */
	public static boolean sendByTags(String tag, String content, String subject) {
		PushPayload payload = build_tag_pushplay(tag, content, subject);
		// PushPayload payload =
		// buildPushObject_ios_audienceMore_messageWithExtras(tag,
		// "18221373124", content, subject);
		try {
			jpushClient.sendPush(payload);
			iosJpushClient.sendPush(payload);
			return true;

		} catch (Exception e) {
			e.printStackTrace();
			return false;

		}
	}

	/**
	 * 通过audience发送通知
	 * 
	 * @param audience
	 * @param content
	 * @param subject
	 * @return
	 */
	public static boolean sendByAudience(String audience, String content, String subject) {
		PushPayload payload = build_audience_pushplay(audience, content, subject);
		try {
			jpushClient.sendPush(payload);
			iosJpushClient.sendPush(payload);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;

		}
	}

	public static PushPayload buildPushObject_all_all_alert(String content) {
		return PushPayload.alertAll(content);
	}

	public static PushPayload build_tag_pushplay(String tag, String content, String subject) {
		return PushPayload.newBuilder().setPlatform(Platform.android_ios()).setAudience(Audience.tag(tag))
				.setNotification(Notification.android(content, subject, null)).build();
	}

	public static PushPayload build_audience_pushplay(String audience, String content, String subject) {
		return PushPayload.newBuilder().setPlatform(Platform.all()).setAudience(Audience.alias(audience))
				.setNotification(Notification.alert(subject)).build();

	}

	/**
	 * 通过alins批量发送
	 * 
	 * @param audienceList
	 * @param content
	 * @param subject
	 */
	public static void sendByAudienceList(String[] audienceList, String type, String subject, int iosBadge) {
		try {
			PushPayload pushPayload = null;
			if (iosBadge == 0) {
				pushPayload = PushPayload.newBuilder().setPlatform(Platform.android_ios())
						.setAudience(Audience.alias(audienceList))
						.setNotification(Notification.newBuilder()
								.addPlatformNotification(IosNotification.newBuilder().setAlert(subject)
										.setSound("happy.caf").addExtra("from", "睿眼通").build())
								.addPlatformNotification(AndroidNotification.alert(subject)).build())

				.setMessage(Message.content(subject)).setOptions(Options.newBuilder().setApnsProduction(false).build())
						.build();
			} else {
				pushPayload = PushPayload.newBuilder().setPlatform(Platform.android_ios())
						.setAudience(Audience.alias(audienceList))
						.setNotification(Notification.newBuilder()
								.addPlatformNotification(IosNotification.newBuilder().setAlert(subject)
										.setBadge(iosBadge).setSound("happy.caf").addExtra("from", "睿眼通").build())
								.addPlatformNotification(AndroidNotification.alert(subject)).build())

				.setMessage(Message.content(subject)).setOptions(Options.newBuilder().setApnsProduction(false).build())
						.build();
			}

			jpushClient.sendPush(pushPayload);
			iosJpushClient.sendPush(pushPayload);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 通过tag批量发送
	 * 
	 * @return
	 */

	public static void sendByTagList(String[] tags, String content, String subject, int iosBadge) {
		PushPayload pushPayload = null;
		if(iosBadge == 0){
			pushPayload = PushPayload.newBuilder().setPlatform(Platform.android_ios())
						.setAudience(Audience.tag_and(tags))
						.setNotification(Notification.newBuilder()
								.addPlatformNotification(IosNotification.newBuilder().setAlert(subject)
										.setSound("happy.caf").addExtra("from", "睿眼通").build())
								.addPlatformNotification(AndroidNotification.alert(subject)).build())

				.setMessage(Message.content(subject)).setOptions(Options.newBuilder().setApnsProduction(false).build())
						.build();
		}else{
				pushPayload = PushPayload.newBuilder().setPlatform(Platform.android_ios())
							.setAudience(Audience.tag_and(tags))
							.setNotification(Notification.newBuilder()
									.addPlatformNotification(IosNotification.newBuilder().setAlert(subject).setBadge(iosBadge)
											.setSound("happy.caf").addExtra("from", "睿眼通").build())
									.addPlatformNotification(AndroidNotification.alert(subject)).build())

					.setMessage(Message.content(subject)).setOptions(Options.newBuilder().setApnsProduction(false).build())
							.build();
		}
		try {
			
			jpushClient.sendPush(pushPayload);
			iosJpushClient.sendPush(pushPayload);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		String[] audienceList = new String[] { "13862389729" };
		// String[] tags = new String[] { "scClass10" };
		try {
			PushPayload pushPayload = PushPayload.newBuilder().setPlatform(Platform.android_ios())
					.setAudience(Audience.alias(audienceList))
					.setNotification(Notification.newBuilder()
							.addPlatformNotification(IosNotification.newBuilder().setAlert("测试22222").setBadge(1)
									.setSound("happy.caf").addExtra("from", "睿眼通").build())
							.addPlatformNotification(AndroidNotification.alert("测试2222")).build())

			.setMessage(Message.content("测试222")).setOptions(Options.newBuilder().setApnsProduction(false).build())
					.build();
			jpushClient.sendPush(pushPayload);
			iosJpushClient.sendPush(pushPayload);
		} catch (Exception e) {
			e.printStackTrace();
		}

		// try {
		// PushPayload pushPayload =
		// PushPayload.newBuilder().setPlatform(Platform.android_ios())
		// .setAudience(Audience.tag_and(tags))
		// .setNotification(Notification.newBuilder()
		// .addPlatformNotification(IosNotification.newBuilder().setAlert("测试").setBadge(1)
		// .setSound("happy.caf").addExtra("from", "睿眼通").build())
		// .addPlatformNotification(AndroidNotification.alert("测试")).build())
		//
		// .setMessage(Message.content("测试")).setOptions(Options.newBuilder().setApnsProduction(false).build())
		// .build();
		// jpushClient.sendPush(pushPayload);
		// iosJpushClient.sendPush(pushPayload);
		// } catch (Exception e) {
		// e.printStackTrace();
		// }
	}

}
