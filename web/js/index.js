 $(document).ready(function()
        {
            $("#log").click(function()
            {
                var username=$("#login-name").val();
                var password=$("#login-pass").val();
                if(username=="" || password=="")
                    alert("Please finish all information!");
                else
                {
                    $.post(
                            
                    "./login",
                    {"username":username,"password":password},
                     function(data){  
                         //alert(data);
                         if(data=="0")
                        {
                            
                            window.location.href = "browse.jsp?username="+$("#login-name").val()+"&";
                         }
                         else
                            alert("Username or Password wrong! Please check your enter");
                            $("#login-pass").val("");
                     },
                    "text" );
                }
            });
                    
            
        });
        
$(document).ready(function()
  {
   $("#signup").click(function()
    {
                
      window.location.href = "signup.jsp";
    }
                         
   );
                    
            
  });
