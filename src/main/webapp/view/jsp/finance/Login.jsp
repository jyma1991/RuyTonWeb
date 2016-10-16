<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html;">
        <title>登陆</title>
  
    </head>
    <body>
     <!--    <script src="jquery-1.2.6.pack.js"></script>
        <script src="jquery.messager.js"></script> -->
            <script type="text/javascript">
        submitOperate();
        function submitOperate(){       	
         $.post(ctx+"messagePrompt.do", function (number) {
                var str0 = '<br /><a href="javascript:Open()">您有' + number[0]+ '个代理商申请，请点击查看</a></br>'
                var str1 = '<br /><a href="javascript:OpenUpgrade()">您有' + number[1]+ '个用户达到升级标准，请点击查看</a></br>'
                var str2 = '<br /><a href="javascript:OpenRemainingAmt()">您有' + number[2]+ '个代理商申请提现，请点击查看</a></br>'
               if((number[0]==0)&&(number[1]==0)&&(number[2]==0)){
            	   
               }else{
                
                if((number[0]>0)&&(number[1]>0)&&(number[2]>0)){
                	str = str0+str1+str2;
                }  
                if((number[0]>0)&&(number[1]==0)&&(number[2]>0)){
                	str = str0+str2;
                }
                 if((number[0]>0)&&(number[1]>0)&&(number[2]==0)){
                	 str = str0+str1;
                }
                 if((number[0]==0)&&(number[1]>0)&&(number[2]>0)){
                	 str =str1+str2;
               }
                 if((number[0]==0)&&(number[1]==0)&&(number[2]>0)){
                 	str = str2;
                 }
                  if((number[0]>0)&&(number[1]==0)&&(number[2]==0)){
                 	 str = str0;
                 }
                  if((number[0]==0)&&(number[1]>0)&&(number[2]==0)){
                 	 str =str1;
                } 
                $.messager.show(
                    		{
                        title: '<font color=blue>系统短消息提醒</font>',
                        msg: '<font color=blue>'+str+'</font>',
                        showSpeed: 1000,
                        timeout: 0,     
                        showType: 'show',
                        width: 260,
                        height: 160
                    });  
               }
            },'json'
         );
        }        
        function Open(){
     	   window.location.href ="FinanceUntreatedAgent.jsp"; 
        }
        function OpenUpgrade(){
     	   window.location.href ="FinanceUntreatedUpgrade.jsp"; 
        }
        function OpenRemainingAmt(){
     	   window.location.href ="FinanceUntreatedRemainingAmt.jsp"; 
        }
        </script>
    </body>
</html>