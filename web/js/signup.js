 $(document).ready(function()
        {
            $("#newuser").click(function()
            {
                var username=$("#username").val();
                var password=$("#password").val();
                var city=$("#city").val();
                var gender=$("#gender").val();
                
                
                if(username=="" || password=="" || city=="" || gender=="" )
                    alert("Please finish all information!");
                else
                { 
                        $.post(
                        "./newuser",
                        {"username":username,"password":password,"city":city,"gender":gender},
                         function(data){                    
                            alert(data);
                            $("#username").val("");
                            $("#password").val("");
                            $("#city").val("");
                            $("#gender").val("");
                            
                           window.location.href = "index.jsp";
                            },
                            "text" );                        
                    
                }
                
            
            });
                    
            
        });


         $(document).ready(function()
        {
            $("#returnpage").click(function()
            {
                
                            window.location.href = "index.jsp";
             }
                         
            );
                    
            
        });
