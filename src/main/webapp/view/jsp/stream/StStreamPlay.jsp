<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>流播放</title>
</head>
<body>

<div class="easyui-layout" data-options="fit:true">
		<div id="" style="min-height: 528px; margin-top:-20px;">
			<!-- <div class="row stream_title"><%=request.getParameter("title") %></div> -->
			<a id="player" class="row"  href=""></a>
				  <script>
					str='<%= request.getParameter("url")%>';
					//alert(str);
					arr=str.split('/');
					//rtmp://server/app/playpath
					protocol=arr[0];
					server=arr[2];
					app=arr[3];
					playpath=arr[4];
			            flowplayer("player", ctx+"videoplayer/flowplayer-3.2.8.swf",{  
				            clip: {  
				              //scaling:'orig',
				              url: playpath, 
				              provider: 'rtmp',
				              live:'true'
				            },   
				            plugins: {   
				               rtmp: {   
				                 url: ctx+'videoplayer/flowplayer.rtmp-3.2.8.swf',   
				                 netConnectionUrl: protocol+'//'+server+'/'+ app
				               }  
				           }  
				        }); 
				        
			    </script>
			    
			
		</div>
		
	</div>
	<script type="text/javascript">
	        var closeDialog = function($dialog) {
				$dialog.dialog('destroy');
			}
    </script>
</body>
</html>