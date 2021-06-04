<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
	<base href="<%=basePath%>">
<meta charset="UTF-8">
<link href="jquery/bootstrap_3.3.0/css/bootstrap.min.css" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="jquery/jquery-1.11.1-min.js"></script>
<script type="text/javascript" src="jquery/bootstrap_3.3.0/js/bootstrap.min.js"></script>
	<script>
		$(function (){
			//页面加载成功 用户名文板框清空
			$("#username").val("");

			//页面加载完毕用户名文本框获得焦点
			$("#username").focus();

			//为登录按钮绑定事件，执行登录操作
			$("#login").click(function (){
				Login();
			})

			//为当前窗口绑定键盘事件 回车登录
			$(window).keydown(function (event){
				if (event.keyCode==13){
					Login();
				}
			})
		})
		function Login(){
			//取得账号密码,且不能为空
			var username = $.trim($("#username").val());
			var pwd = $.trim($("#pwd").val());
			if (username=="" || pwd==""){
				$("#msg").html("账号密码不能为空");
				return false;
			}
			$.ajax({
				url : "settings/user/login.do",
				data : {
					"LoginUsername":username,
					"LoginPwd":pwd
				},
				type : "post",
				dataType : "json",
				success : function(data){
					if (data.success){
						//登陆成功
						window.location.href = "workbench/index.html";
					}else {
						//登录失败
						$("#msg").html(data.msg);
					}
				}
			})
		}


	</script>
</head>
<body>
	<div style="position: absolute; top: 0px; left: 0px; width: 60%;">
		<img src="image/2.JPG" style=" background-repeat:no-repeat ;background-size:50% 50%;background-attachment: fixed;">
	</div>
	<div id="top" style="height: 50px; background-color: #000000; width: 100%;">
		<div style="position: absolute; top: 5px; left: 0px; font-size: 30px; font-weight: 400; color: white; font-family: 'times new roman'">CRM &nbsp;<span style="font-size: 12px;"></span></div>
	</div>

	<div style="position: absolute; top: 120px; right: 100px;width:450px;height:400px;border:1px solid #D5D5D5">
		<div style="position: absolute; top: 0px; right: 60px;">
			<div class="page-header">
				<h1 style="color: white">登录</h1>
			</div>
			<form action="workbench/index.html" class="form-horizontal" role="form">
				<div class="form-group form-group-lg">
					<div style="width: 350px;">
						<input class="form-control" type="text" placeholder="用户名" id="username">
					</div>
					<div style="width: 350px; position: relative;top: 20px;">
						<input class="form-control" type="password" placeholder="密码" id="pwd">
					</div>
					<div class="checkbox"  style="position: relative;top: 30px; left: 10px;">
						
							<span id="msg" style="color: white"></span>
						
					</div>
					<button type="button" id="login" class="btn btn-primary btn-lg btn-block"  style="width: 350px; position: relative;top: 45px;">登录</button>
				</div>
			</form>
		</div>
	</div>
</body>
</html>