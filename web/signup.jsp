<%-- 
    Document   : signup
    Created on : 2014-12-3, 17:25:22
    Author     : zhengkaiming
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js" type="text/javascript"></script>
        <link href="./dist/css/vendor/bootstrap.min.css" rel="stylesheet">
        <link href="./dist/css/flat-ui.min.css" rel="stylesheet">
        <link href="./docs/assets/css/demo.css" rel="stylesheet">
        <link href="./css/browse.css" rel="stylesheet">
        <link rel="shortcut icon" href="img/favicon.ico">
        <script src="./js/signup.js"></script>
    </head>
    
    <body>
        <!--sign up-->
         <div class="container">
           <br/>
           <br/>
          <div class="bound">
          <div class="login-form">
            <div class="form-group">
              <input type="text" class="form-control login-field" value="" placeholder="Username" id="username" />
              <label class="login-field-icon fui-user" for="login-name"></label>
            </div>

            <div class="form-group">
              <input type="password" class="form-control login-field" value="" placeholder="Password" id="password" />
              <label class="login-field-icon fui-lock" for="login-pass"></label>
            </div>
            
            <div class="form-group">
              <input type="text" class="form-control login-field" value="" placeholder="City" id="city" />
              <label class="login-field-icon fui-location" for="login-pass"></label>
            </div>

            <div class="form-group">
                <select class="form-control" id="gender">
                     <optgroup label="Gender">
                     <option value="male">Male</option>
                     <option value="female">Female</option>
                     </optgroup>
                </select>
            </div>
            <a class="btn btn-primary btn-lg btn-block" href="#" id="newuser">Sign up</a>
            <a class="login-link" href="#" id="returnpage">Return the Index</a>
          </div>
         </div>
       </div>
    </body>
</html>
