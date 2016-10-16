<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:include page="/view/taglib.jsp"></jsp:include>
<title>选择图标</title>

<body>

	<div class="easyui-layout" data-options="fit:true">
		<div data-options="region:'north',border:false">
			<div class="title" style="padding:10px;">
			选择图标
			</div>
		</div>
		<div data-options="region:'center',border:false">
			<input id="imgSelected" name = "imgSelected" hidden = "hidden"></input>
			<ul  id="imagelist" style="padding-left:0px"></ul>
			
		</div>

		<div data-options="region:'south',border:false">
			<div id="dgRole-buttons" style="text-align: right; padding: 10px;">
				<a href="#" class="easyui-linkbutton" iconCls="icon-ok" onclick="savedata(); return false;">清除</a> <a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="closeWin(); return false;">取消</a>
			</div>
		</div>
	</div>
	<div name="test">1234</div>
	<script type="text/javascript">
		var pigname = '${param.pigname}';
 		var imgList = new Array("ion-alert-circled.png","ion-alert.png","ion-android-add-contact.png","ion-android-add.png","ion-android-alarm-clock.png","ion-android-archive.png","ion-android-arrow-back.png","ion-android-arrow-down-left.png","ion-android-arrow-down-right.png","ion-android-arrow-up-left.png","ion-android-arrow-up-right.png","ion-android-battery.png","ion-android-book.png","ion-android-calendar.png","ion-android-call.png","ion-android-camera.png","ion-android-chat.png","ion-android-contact.png","ion-android-contacts.png","ion-android-data.png","ion-android-developer.png","ion-android-display.png","ion-android-download.png","ion-android-drawer.png","ion-android-dropdown.png","ion-android-folder.png","ion-android-friends.png","ion-android-globe.png","ion-android-hand.png","ion-android-home.png","ion-android-image.png","ion-android-inbox.png","ion-android-keypad.png","ion-android-lightbulb.png","ion-android-locate.png","ion-android-mail.png","ion-android-microphone.png","ion-android-more.png","ion-android-note.png","ion-android-options.png","ion-android-people.png","ion-android-person.png","ion-android-playstore.png","ion-android-printer.png","ion-android-promotion.png","ion-android-reminder.png","ion-android-remove.png","ion-android-search.png","ion-android-send.png","ion-android-settings.png","ion-android-share-alt.png","ion-android-sort.png","ion-android-stair-drawer.png","ion-android-star.png","ion-android-stopwatch.png","ion-android-storage.png","ion-android-system-windows.png","ion-android-time.png","ion-android-timer.png","ion-android-trash.png","ion-android-user-menu.png","ion-android-volume.png","ion-android-wifi.png","ion-aperture.png","ion-archive.png","ion-arrow-down-a.png","ion-arrow-down-b.png","ion-arrow-down-c.png","ion-arrow-expand.png","ion-arrow-graph-down-left.png","ion-arrow-graph-down-right.png","ion-arrow-graph-up-left.png","ion-arrow-graph-up-right.png","ion-arrow-left-a.png","ion-arrow-left-b.png","ion-arrow-left-c.png","ion-arrow-move.png","ion-arrow-resize.png","ion-arrow-return-left.png","ion-arrow-return-right.png","ion-arrow-right-a.png","ion-arrow-right-b.png","ion-arrow-right-c.png","ion-arrow-shrink.png","ion-arrow-swap.png","ion-arrow-up-a.png","ion-arrow-up-b.png","ion-arrow-up-c.png","ion-asterisk.png","ion-at.png","ion-bag.png","ion-battery-charging.png","ion-battery-empty.png","ion-battery-full.png","ion-battery-half.png","ion-battery-low.png","ion-beer.png","ion-bluetooth.png","ion-bonfire.png","ion-bookmark.png","ion-briefcase.png","ion-bug.png","ion-calculator.png","ion-calendar.png","ion-camera.png","ion-card.png","ion-cash.png","ion-chatbox-working.png","ion-chatbox.png","ion-chatboxes.png","ion-chatbubble-working.png","ion-chatbubble.png","ion-chatbubbles.png","ion-checkmark-circled.png","ion-checkmark-round.png","ion-checkmark.png","ion-checkmark1.png","ion-chevron-down.png","ion-chevron-left.png","ion-chevron-right.png","ion-chevron-up.png","ion-clipboard.png","ion-clock.png","ion-close-circled.png","ion-close-round.png","ion-close.png","ion-close1.png","ion-closed-captioning.png","ion-cloud.png","ion-code-download.png","ion-code-working.png","ion-code.png","ion-coffee.png","ion-compass.png","ion-compose.png","ion-connection-bars.png","ion-contrast.png","ion-cube.png","ion-disc.png","ion-document-text.png","ion-document.png","ion-drag.png","ion-earth.png","ion-edit.png","ion-egg.png","ion-eject.png","ion-email.png","ion-erlenmeyer-flask.png","ion-eye-disabled.png","ion-eye.png","ion-female.png","ion-filing.png","ion-film-marker.png","ion-fireball.png","ion-flag.png","ion-flame.png","ion-flash-off.png","ion-flash.png","ion-flask.png","ion-folder.png","ion-fork-repo.png","ion-fork.png","ion-forward.png","ion-funnel.png","ion-gear-a.png","ion-gear-b.png","ion-grid.png","ion-hammer.png","ion-happy-outline.png","ion-headphone.png","ion-heart-broken.png","ion-heart.png","ion-help-buoy.png","ion-help-circled.png","ion-help.png","ion-home.png","ion-icecream.png","ion-image.png","ion-images.png","ion-information-circled.png","ion-information.png","ion-ios-alarm-outline.png","ion-ios-alarm.png","ion-ios-albums-outline.png","ion-ios-albums.png","ion-ios-americanfootball-outline.png","ion-ios-americanfootball.png","ion-ios-analytics-outline.png","ion-ios-analytics.png","ion-ios-arrow-back.png","ion-ios-arrow-back1.png","ion-ios-arrow-down.png","ion-ios-arrow-forward.png","ion-ios-arrow-forward1.png","ion-ios-arrow-left.png","ion-ios-arrow-right.png","ion-ios-arrow-thin-down.png","ion-ios-arrow-thin-left.png","ion-ios-arrow-thin-right.png","ion-ios-arrow-thin-up.png","ion-ios-arrow-up.png","ion-ios-at-outline.png","ion-ios-at.png","ion-ios-barcode-outline.png","ion-ios-barcode.png","ion-ios-baseball-outline.png","ion-ios-baseball.png","ion-ios-basketball-outline.png","ion-ios-basketball.png","ion-ios-bell-outline.png","ion-ios-bell.png","ion-ios-bolt-outline.png","ion-ios-bolt.png","ion-ios-bookmarks-outline.png","ion-ios-bookmarks.png","ion-ios-box-outline.png","ion-ios-box.png","ion-ios-briefcase-outline.png","ion-ios-briefcase.png","ion-ios-browsers-outline.png","ion-ios-browsers.png","ion-ios-calculator-outline.png","ion-ios-calculator.png","ion-ios-calendar-outline.png","ion-ios-calendar.png","ion-ios-camera-outline.png","ion-ios-camera.png","ion-ios-cart-outline.png","ion-ios-cart.png","ion-ios-chatboxes-outline.png","ion-ios-chatboxes.png","ion-ios-chatbubble-outline.png","ion-ios-chatbubble.png","ion-ios-checkmark-empty.png","ion-ios-checkmark-outline.png","ion-ios-checkmark.png","ion-ios-circle-filled.png","ion-ios-circle-outline.png","ion-ios-clock-outline.png","ion-ios-clock.png","ion-ios-close-empty.png","ion-ios-close-outline.png","ion-ios-close.png","ion-ios-cloud-download-outline.png","ion-ios-cloud-download.png","ion-ios-cloud-outline.png","ion-ios-cloud-upload-outline.png","ion-ios-cloud-upload.png","ion-ios-cloud.png","ion-ios-cloudy-night-outline.png","ion-ios-cloudy-night.png","ion-ios-cloudy-outline.png","ion-ios-cloudy.png","ion-ios-cog-outline.png","ion-ios-cog.png","ion-ios-compose-outline.png","ion-ios-compose.png","ion-ios-copy-outline.png","ion-ios-copy.png","ion-ios-download-outline.png","ion-ios-download.png","ion-ios-drag.png","ion-ios-email-outline.png","ion-ios-email.png","ion-ios-eye-outline.png","ion-ios-eye.png","ion-ios-fastforward-outline.png","ion-ios-fastforward.png","ion-ios-filing-outline.png","ion-ios-filing.png","ion-ios-film-outline.png","ion-ios-film.png","ion-ios-flag-outline.png","ion-ios-flag.png","ion-ios-folder-outline.png","ion-ios-folder.png","ion-ios-football-outline.png","ion-ios-football.png","ion-ios-game-controller-a.png","ion-ios-game-controller-b.png","ion-ios-gear-outline.png","ion-ios-gear.png","ion-ios-glasses-outline.png","ion-ios-glasses.png","ion-ios-heart-outline.png","ion-ios-heart.png","ion-ios-help-empty.png","ion-ios-help-outline.png","ion-ios-help.png","ion-ios-home-outline.png","ion-ios-home.png","ion-ios-infinite-outline.png","ion-ios-infinite.png","ion-ios-information (2).png","ion-ios-information-empty.png","ion-ios-information-outline.png","ion-ios-information.png","ion-ios-ionic-outline.png","ion-ios-keypad-outline.png","ion-ios-keypad.png","ion-ios-lightbulb-outline.png","ion-ios-lightbulb.png","ion-ios-location (2).png","ion-ios-location-outline.png","ion-ios-location.png","ion-ios-locked-outline.png","ion-ios-locked.png","ion-ios-loop-strong.png","ion-ios-loop.png","ion-ios-medkit-outline.png","ion-ios-medkit.png","ion-ios-mic-off.png","ion-ios-mic-outline.png","ion-ios-mic.png","ion-ios-minus-empty.png","ion-ios-minus-outline.png","ion-ios-minus.png","ion-ios-monitor-outline.png","ion-ios-monitor.png","ion-ios-moon-outline.png","ion-ios-moon.png","ion-ios-more-outline.png","ion-ios-more.png","ion-ios-musical-note.png","ion-ios-musical-notes.png","ion-ios-navigate-outline.png","ion-ios-navigate.png","ion-ios-paper-outline.png","ion-ios-paper.png","ion-ios-paperplane-outline.png","ion-ios-paperplane.png","ion-ios-partlysunny-outline.png","ion-ios-partlysunny.png","ion-ios-pause-outline.png","ion-ios-pause.png","ion-ios-paw-outline.png","ion-ios-paw.png","ion-ios-people-outline.png","ion-ios-people.png","ion-ios-person-outline.png","ion-ios-person.png","ion-ios-personadd-outline.png","ion-ios-personadd.png","ion-ios-photos-outline.png","ion-ios-photos.png","ion-ios-pie-outline.png","ion-ios-pie.png","ion-ios-play-outline.png","ion-ios-play.png","ion-ios-plus-empty.png","ion-ios-plus-outline.png","ion-ios-plus.png","ion-ios-pricetag-outline.png","ion-ios-pricetag.png","ion-ios-pricetags-outline.png","ion-ios-pricetags.png","ion-ios-printer-outline.png","ion-ios-printer.png","ion-ios-pulse-strong.png","ion-ios-pulse.png","ion-ios-rainy-outline.png","ion-ios-rainy.png","ion-ios-recording-outline.png","ion-ios-recording.png","ion-ios-redo-outline.png","ion-ios-redo.png","ion-ios-refresh-empty.png","ion-ios-refresh-outline.png","ion-ios-refresh.png","ion-ios-reload.png","ion-ios-reverse-camera-outline.png","ion-ios-reverse-camera.png","ion-ios-rewind-outline.png","ion-ios-rewind.png","ion-ios-search-strong.png","ion-ios-search.png","ion-ios-settings-strong.png","ion-ios-settings.png","ion-ios-skipbackward-outline.png","ion-ios-skipbackward.png","ion-ios-skipforward-outline.png","ion-ios-skipforward.png","ion-ios-snowy.png","ion-ios-speedometer-outline.png","ion-ios-speedometer.png","ion-ios-star-half.png","ion-ios-star-outline.png","ion-ios-star.png","ion-ios-stopwatch-outline.png","ion-ios-stopwatch.png","ion-ios-sunny-outline.png","ion-ios-sunny.png","ion-ios-telephone-outline.png","ion-ios-telephone.png","ion-ios-tennisball-outline.png","ion-ios-tennisball.png","ion-ios-thunderstorm-outline.png","ion-ios-thunderstorm.png","ion-ios-time-outline.png","ion-ios-time.png","ion-ios-timer-outline.png","ion-ios-timer.png","ion-ios-toggle-outline.png","ion-ios-toggle.png","ion-ios-trash-outline.png","ion-ios-trash.png","ion-ios-undo-outline.png","ion-ios-undo.png","ion-ios-unlocked-outline.png","ion-ios-unlocked.png","ion-ios-upload-outline.png","ion-ios-upload.png","ion-ios-videocam-outline.png","ion-ios-videocam.png","ion-ios-volume-high.png","ion-ios-volume-low.png","ion-ios-wineglass-outline.png","ion-ios-wineglass.png","ion-ios-world-outline.png","ion-ios-world.png","ion-ipad.png","ion-iphone.png","ion-ipod.png","ion-jet.png","ion-key.png","ion-knife.png","ion-laptop.png","ion-leaf.png","ion-levels.png","ion-lightbulb.png","ion-link.png","ion-load-a.png","ion-load-b.png","ion-load-c.png","ion-load-d.png","ion-location.png","ion-locked.png","ion-log-in.png","ion-log-out.png","ion-loop.png","ion-magnet.png","ion-male.png","ion-man.png","ion-map.png","ion-medkit.png","ion-merge.png","ion-mic-a.png","ion-mic-b.png","ion-mic-c.png","ion-minus-circled.png","ion-minus-round.png","ion-minus.png","ion-model-s.png","ion-monitor.png","ion-more.png","ion-mouse.png","ion-music-note.png","ion-navicon-round.png","ion-navicon.png","ion-navigate.png","ion-network.png","ion-no-smoking.png","ion-nuclear.png","ion-outlet.png","ion-paper-airplane.png","ion-paperclip.png","ion-pause.png","ion-person-add.png","ion-person-stalker.png","ion-person.png","ion-pie-graph.png","ion-pin.png","ion-pinpoint.png","ion-pizza.png","ion-plane.png","ion-planet.png","ion-play.png","ion-playstation.png","ion-plus-circled.png","ion-plus-round.png","ion-plus.png","ion-podium.png","ion-pound.png","ion-power.png","ion-pricetag.png","ion-pricetags.png","ion-printer.png","ion-pull-request.png","ion-qr-scanner.png","ion-quote.png","ion-radio-waves.png","ion-record.png","ion-refresh.png","ion-reply-all.png","ion-reply.png","ion-ribbon-a.png","ion-ribbon-b.png","ion-sad.png","ion-scissors.png","ion-search.png","ion-settings.png","ion-share.png","ion-shuffle.png","ion-skip-backward.png","ion-skip-forward.png","ion-social-android-outline.png","ion-social-android.png","ion-social-apple-outline.png","ion-social-apple.png","ion-social-bitcoin-outline.png","ion-social-bitcoin.png","ion-social-buffer-outline.png","ion-social-buffer.png","ion-social-designernews-outline.png","ion-social-designernews.png","ion-social-dribbble-outline.png","ion-social-dribbble.png","ion-social-dropbox-outline.png","ion-social-dropbox.png","ion-social-facebook-outline.png","ion-social-facebook.png","ion-social-foursquare-outline.png","ion-social-foursquare.png","ion-social-freebsd-devil.png","ion-social-github-outline.png","ion-social-github.png","ion-social-google-outline.png","ion-social-google.png","ion-social-googleplus-outline.png","ion-social-googleplus.png","ion-social-hackernews-outline.png","ion-social-hackernews.png","ion-social-instagram-outline.png","ion-social-instagram.png","ion-social-linkedin-outline.png","ion-social-linkedin.png","ion-social-pinterest-outline.png","ion-social-pinterest.png","ion-social-reddit-outline.png","ion-social-reddit.png","ion-social-rss-outline.png","ion-social-rss.png","ion-social-skype-outline.png","ion-social-skype.png","ion-social-tumblr-outline.png","ion-social-tumblr.png","ion-social-tux.png","ion-social-twitter-outline.png","ion-social-twitter.png","ion-social-usd-outline.png","ion-social-usd.png","ion-social-vimeo-outline.png","ion-social-vimeo.png","ion-social-windows-outline.png","ion-social-windows.png","ion-social-wordpress-outline.png","ion-social-wordpress.png","ion-social-yahoo-outline.png","ion-social-yahoo.png","ion-social-youtube-outline.png","ion-social-youtube.png","ion-speakerphone.png","ion-speedometer.png","ion-spoon.png","ion-star.png","ion-stats-bars.png","ion-steam.png","ion-stop.png","ion-thermometer.png","ion-thumbsdown.png","ion-thumbsup.png","ion-toggle-filled.png","ion-toggle.png","ion-trash-a.png","ion-trash-b.png","ion-trophy.png","ion-umbrella.png","ion-university.png","ion-unlocked.png","ion-upload.png","ion-usb.png","ion-videocamera.png","ion-volume-high.png","ion-volume-low.png","ion-volume-medium.png","ion-volume-mute.png","ion-wand.png","ion-waterdrop.png","ion-wifi.png","ion-wineglass.png","ion-woman.png","ion-wrench.png","ion-xbox.png","ionic.png");
		classTypeName =	$.getJSON(ctx+'listAllSysUpload.do?funcIdSch=SysClass',function(dataJson){
		for(var i=0;i<imgList.length;i++)
		{
			addElementImg("imagelist",imgList[i]);
			
		}
		$("li").click(function(){
			var imgName = $(this).children('img').attr("name");
			$("#imgSelected").val(imgName.substring(0,imgName.length-4));
			parent.getImg();
			parent.closeDlg();
		});
	});

	function savedata(){
		
		parent.getImg();
		parent.closeDlg();
	}
	
	function closeWin(){
		
		parent.closeDlg();
	}
	
	function addElementImg(obj,name) {
	　　　　var ul = document.getElementById(obj);

	　　　　//添加 li
	　　　　var li = document.createElement("li");

	　　　　//添加 img
	　　　　var img = document.createElement("img");

	　　　　//设置 img 属性，如 id
	　　　　img.setAttribute("id", name);
		  img.setAttribute("name", name);	
	　	  img.setAttribute("width", "22px");
	　	  img.setAttribute("height", "22px");
	　	  if(name === pigname){
	　		li.style.background="#FF0000";
	　		li.style.height="22px";　		
	　		img.setAttribute("border","1px solid red");
	　		img.setAttribute("width", "20px");
		　	img.setAttribute("height", "20px");
	　	  }

	　　　　//设置 img 图片地址
	　　　　img.src = "../../../assets/icon/"+name;
		 
	　　　　li.appendChild(img);
	　　　　ul.appendChild(li);
	　　}

	</script>
</body>