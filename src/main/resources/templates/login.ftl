<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/html">
<html lang="en" xmlns:th="http://www.thymeleleaf.org">
<html lang="en"
      xmlns:th="http://www.thymeleaf.org/dtd/xhtml1-strict-thymeleaf-spring4-4.dtd" >
<head>
    <meta charset="UTF-8">
    <title>login</title>
    <link href="https://cdn.bootcss.com/bootstrap/3.1.1/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdn.bootcss.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
    <link rel="stylesheet" href="/css/login-page.min.css">
</head>
<body class="login-page">
<div class="login-avatar" style="background: url(/img/default.png) no-repeat center center;" onmouseover="this.style.cursor='pointer'" onclick="document.location='/'">
</div>
<div class="login-form">
    <div class="login-content">
        <form name="loginForm" onsubmit="return login()">
            <div class="form-group">
                <div class="btn btn-default btn-block btn-login" id="user-type">
                    <i class="fa fa-edit"></i>
                    学生用户
                </div>
            </div>
            <div class="form-group">
                <div class="input-group">
                    <div class="input-group-addon">
                        <i class="fa fa-user"></i>
                    </div>
                    <input type="text" class="form-control" name="name" id="name" placeholder="用户名" autocomplete="off" />
                </div>
            </div>
            <div class="form-group">
                <div class="input-group">
                    <div class="input-group-addon">
                        <i class="fa fa-key"></i>
                    </div>
                    <input type="password" class="form-control" name="passBefore" id="passBefore" placeholder="密码" autocomplete="off" />
                    <input type="hidden" name="password" id="password"/>
                    <input type="hidden" name="role" id="role"/>
                </div>
            </div>

            <div class="form-group">
                <button type="submit" class="btn btn-primary btn-block btn-login" id="login-button" >
                    <i class="fa fa-sign-in"></i>
                    登录
                </button>
            </div>

        </form>
        <script language="javascript">
            function login(){
                //得到name输入框对象
                var name = document.getElementById("name");
                //判断输入框是否有内容
                if(name.value.length==0){
                    confirm("用户名不能为空");
                    return false;
                }
                var pass = document.getElementById("passBefore");
                if(pass.value.length==0){
                    confirm("密码不能为空");
                    return false;
                }
                return true;
            }
        </script>

    </div>
</div>
<script src="https://cdn.bootcss.com/jquery/3.0.0/jquery.min.js"></script>
<script src="https://cdn.bootcss.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>
<script src="/js/md5.min.js"></script>
<script type="text/javascript">
    $("#user-type").click(function(){
        if ("学生用户" == $(this).text().trim()){
            $(this).text("教师用户");
        }else if("教师用户" == $(this).text().trim()) {
            $(this).text("学生用户");
        }
    });

    var getType=function () {
        if ("学生用户" == $("#user-type").text().trim()){
            return 1;
        }if("教师用户" == $("#user-type").text().trim()) {
            return 2;
        }
    };
    $("#login-button").bind("click",
            function() {
                var b, a = document.forms[0];
                a.action = "/login.do",
                b = document.loginForm.passBefore.value,
                document.loginForm.password.value = md5(b),
                document.loginForm.role.value=getType();
                a.method = "post",
                a.submit()
            })
</script>
</body>
</html>