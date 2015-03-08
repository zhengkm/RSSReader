<%-- 
    Document   : index
    Created on : 2014-12-3, 16:14:13
    Author     : zhengkaiming
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        
        <link href="./dist/css/vendor/bootstrap.min.css" rel="stylesheet">
        <link href="./dist/css/flat-ui.min.css" rel="stylesheet">
        <link href="./docs/assets/css/demo.css" rel="stylesheet">      
        <link rel="shortcut icon" href="img/favicon.ico">     
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js" type="text/javascript"></script>   
        <script src="./js/index.js"></script>
        <title>RSS Reader</title>
    </head>
    
    
    <body>

      <div class="container">
        <br/>
        <!--log in-->
        <div class="login">
        <div class="login-screen">
          <div class="login-icon">
            <img src="dist/img/login/icon.png" alt="Welcome to Mail App" />
            <h4>Welcome to <small>Rss Reader</small></h4>
          </div>

          <div class="login-form">
            <div class="form-group">
              <input type="text" class="form-control login-field" value="" placeholder="Username" id="login-name" />
              <label class="login-field-icon fui-user" for="login-name"></label>
            </div>

            <div class="form-group">
              <input type="password" class="form-control login-field" value="" placeholder="Password" id="login-pass" />
              <label class="login-field-icon fui-lock" for="login-pass"></label>
            </div>

            <a class="btn btn-primary btn-lg btn-block" href="#" id="log">Log in</a>
            <a class="btn btn-primary btn-lg btn-block" href="#" id="signup">Sign up</a>
           
          </div>
        </div>
      </div>
    </div>
    <!-- /.container -->

    </body>
</html>
