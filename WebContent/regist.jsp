<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>欢迎注册</title>
		<style type="text/css">
			.inputStyle{
				width: 200px;
				height: 25px;
				border: gray 1px solid;
				border-radius: 3px;
			}
			
		</style>
	</head>
	<body>
	<!-- 使用form表单作上传时，必须在form表单中添加 enctype="multipart/form-data"-->
		<form id="form" action="InsertUserServlet" method="post" enctype="multipart/form-data">
			<div style="margin-top:80px;">
			<table width="450" height="500" align="center" style="border:1px darkslategray solid"
			   cellspacing="0" >
			   <caption>${tips}</caption>
			<tr height="80" bgcolor="darkslategray">
				<td colspan="2" align="center">
					<label style="color:white; font-size: 30px; font-family: 黑体;">在线音乐视听</label>
					<label style="color:white; font-size: 20px; font-family: 黑体;">欢迎注册</label>
				</td>
			</tr>
			<tr>
				<td width="120" align="right">
					<label>账号&nbsp;&nbsp;</label>
				</td>
				<td>
					<!-- 必须以字母开头，由字母数字下划线组成，长度在4位到8位之间 -->
					<!-- 失去焦点的事件    onfocus:聚集焦点触发事件-->
					<input id="userName" type="text" name="userName" onblur="checkInput(this)"
					placeholder="必须以字母开头，由字母数字下划线组成" class="inputStyle"/>
					<span id="tipuserName" style="font-size:12px"></span>
				</td>
			</tr>
			<tr>
				<td align="right">
					<label>密码&nbsp;&nbsp;</label>					
				</td>
				<td>
					<input id="userPwd" type="password" onblur="checkInput(this)"
					placeholder="密码长度必须在4位到8位" name="userPwd" class="inputStyle"/>
					<span id="tipuserPwd" style="font-size:12px"></span>
				</td>
			</tr>
			<tr>
				<td align="right">
					<label>确认密码&nbsp;&nbsp;</label>					
				</td>
				<td>
					<input id="rePwd"  type="password" onblur="checkInput(this)"
					name="rePwd" class="inputStyle"/>
					<span id="tiprePwd" style="font-size:12px"></span>
				</td>
			</tr>
			<tr>
				<td align="right">
					<label>昵称&nbsp;&nbsp;</label>					
				</td>
				<td>
					<input id="userNick" type="text" name="userNick" onblur="checkInput(this)" 
					class="inputStyle"/>
					<span id="tipuserNick" style="font-size:12px"></span>
				</td>
			</tr>
			<tr>
				<td align="right">
					<label>上传头像&nbsp;&nbsp;</label>					
				</td>
				<td>
					<!-- type类型设置为file，就可以上传文件 -->
					<input id="userPath"  type="file" name="userPath"
					class="inputStyle"/>
					<span id="tipuserPath" style="font-size:12px"></span>
				</td>
			</tr>
			<tr>
				<td align="right">
					<label>性别&nbsp;&nbsp;</label>					
				</td>
				<td>
					<input type="radio" name="userSex" value="男" checked="checked"/>男
					<input type="radio" name="userSex" value="女" />女
				</td>
			</tr>
			<tr>
				<td align="right">
					<label>年龄&nbsp;&nbsp;</label>					
				</td>
				<td>
					<input id="userAge" type="text" name="userAge" onblur="checkInput(this)"
					class="inputStyle"/>
					<span id="tipuserAge" style="font-size:12px"></span>
				</td>
			</tr>
			<tr>
				<td align="right">
					<label>签名&nbsp;&nbsp;</label>					
				</td>
				<td>
					<textarea id="userDesc" rows="3" name="userDesc" onblur="checkInput(this)"
					 style="width: 200px;"></textarea>
					<span id="tipuserDesc" style="font-size:12px"></span>
				</td>
			</tr>
			<tr height="60">
				<TD></TD>
				<td>
					<!-- submit：提交时，不管js、jquery的判断，只要点击，就向action跳转 -->
					<!--在开发中做表单提交时，一般使用button-->
					<input type="button" value="提交注册" onclick="submitRegist()"
					style="border:none;background: darkslategray; color: white; width: 200px; height: 35px;" />
				</td>
			</tr>
		</table>
		</div>
		
		</form>
		<script type="text/javascript" src="js/jquery-3.1.1.js"></script>
		<script type="text/javascript">
			//正则表达式    :  以字母开头:a-z 和A-Z       ^:以什么什么开头
			// \w :匹配字母数字下划线:
			//长度是4位到8位  
			// {m,n}:长度是m位到n位之间    m<=x <=n
			// {m,}:长度至少是m位
			// $:以什么什么结尾
		/* 	var re = /^[a-zA-Z]\w{3,7}/;
			var str = "a___";
			var flag = re.test(str);
			console.log(flag) */
			
			
			//使用全局变量
			var flag = false;
			//定义一个input框输入失去焦点会执行的方法
			function checkInput(obj){
				//如果账户触发的事件
				if(obj.name=="userName"){
					//账户的正则表达式
					var re = /^[a-zA-Z]\w{3,7}$/;
					//获取用户输入的账户值
					var userNameInput = $("#userName").val();
					//验证输入的账户是否满足正则表达式
					if(re.test(userNameInput)){
						//满足要求
						//给账户旁边的span提示：OK
						$("#tip"+obj.name).html("您老人家可以用了");
						//将字体变为绿色
						$("#tip"+obj.name).css("color","green");
						flag = true;
					}else{
						//不满足要求
						//给账户旁边的span提示：账户输入不满足格式
						$("#tip"+obj.name).html("不可以这样输哦~~");
						//将字体变为红色
						$("#tip"+obj.name).css("color","red");
						flag = false;
					}
				}else if(obj.name=="userPwd"){
					//密码长度必须在4位到8位
					//获取输入密码的值
					var userPwdInput = $("#userPwd").val();
					if(userPwdInput.length>=4 && userPwdInput.length<=8){
						//给密码提示：OK
						$("#tip"+obj.name).html("OK");
						$("#tip"+obj.name).css("color","green");
						flag = true;
					}else{
						//密码提示：你这个不可以用(⊙o⊙)
						$("#tip"+obj.name).html("这个不可以用哦");
						$("#tip"+obj.name).css("color","red");
						flag = false;
					}
				}else if(obj.name=="rePwd"){
					//判断密码前后输入是否一致
					var userPwd = $("#userPwd").val();
					var rePwd = $("#rePwd").val();
					if(userPwd==rePwd && userPwd!=""){
						$("#tip"+obj.name).html("这个可以用哦")
						$("#tip"+obj.name).css("color","green");
						flag =true;
					}else{
						$("#tip"+obj.name).html("这个不可以用哦");
						$("#tip"+obj.name).css("color","red");
						flag = false;
					}
				}else{
					//判断昵称、图片、年龄、签名都不能为空
					if($("#"+obj.name).val()==""){
						$("#tip"+obj.name).html(obj.name+"不能为空");
						$("#tip"+obj.name).css("color","red");
						flag = false;
					}else{
						$("#tip"+obj.name).html("OK");
						$("#tip"+obj.name).css("color","green");
						flag = true;
					}
				}
			}
			//点击提交注册时，执行方法
			function submitRegist(){
				//判断图片上传是否为空，为空，给出提示
				if($("#userPath").val()!=""){
					$("#tipuserPath").html("Ok");
					$("#tipuserPath").css("color","green");
					//当flag为true时，说明输入没问题，可以提交
					if(flag){
						//表单提交的方法
						$("#form").submit();
					}else{
						alert("您的表单信息输入不完整，请重新输入")
					}
				}else{
					$("#tipuserPath").html("图片上传不能为空");
					$("#tipuserPath").css("color","red");
				}
				
			}
		</script>
	</body>
</html>