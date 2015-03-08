<%@ page import="com.sun.syndication.feed.synd.SyndFeed" %> 
<%@ page import="com.sun.syndication.feed.synd.SyndEntry" %> 
<%@ page import="java.util.Iterator" %> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.io.*,java.util.*" %>
<%@include file="wrong.jsp" %>
<%
   String username = (String)session.getAttribute("username");
%>


<html>
    <head>
        <title>RSS Reader</title>
        <link href="./css/browse.css" rel="stylesheet">
        <link href="./dist/css/vendor/bootstrap.min.css" rel="stylesheet">   
        <link rel="shortcut icon" href="img/favicon.ico">
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js" type="text/javascript"></script>   
        <script src="./js/browse.js"></script>
    </head>
      
    <body onload="FindandDisplay()">
      <div class="container home-container">
       <div class="home-page-posts animated fadeIn">
           <nav class="navbar navbar-inverse" role="navigation">
            <br/><br/>
            
           </nav>
            <div class="search">
             <form action="rss" name="p" method="GET"class="navbar-form ">
                <div class="form-group">
                <input  class="form-control" placeholder="Search Feed" type="text" name="rs" value="">
                </div>
                <button class="btn btn-embossed btn-primary" type="submit">Search Feeds</button>                  
                <br/>
             </form>               
            </div>
           
           <div class="fullcontent">
               
            <div id="leftContent">
                
                <!--show the username and set the function of log out-->
              <div class="user-desc">
                <div class="hd">                 
                  <h1 id="message"><% out.print(username); %></h1>      
                  <button class="btn btn-embossed btn-primary" id ="logout" type="submit">Log Out</button>
                </div>  
              </div>
                  
               <!--save the rss feeds you like in the selection bar-->
              <div class="user-collect">
                <form action="rssServlet2" name="p" method="GET">
                    <h2>Your Favorite Feeds</h2>
                  <select class="form-control select select-primary select-block mbl" name="rssreader" id="rssreader">
                    <optgroup label="RSS Reader" >  
                    </optgroup>
                  </select>
                   <br/>
                   <button class="btn btn-embossed btn-primary" type="submit" onclick="changecolor()">Review</button>
                   
                </form>
                  
                   <br/>
              </div>            
            </div>
                  
            <!--show the content of selected rss feed-->
            <div id="rightContent">
                <c:choose>
                <c:when test="${not empty entries}">
                    <div class="post">
                        <div class="content1">
                         <br/>
                         <div id="leftcontent1">
                         <img src="${image.url}"/>
                         <h2>${title}</h2>
                         </div>
                         <!--add the feed into "your favourite bar"-->
                         <div id="rightcontent1">                           
                          <a class="btn btn-embossed btn-primary" id="change" title="add as your favourite"onclick="outputFeed()">Favorites</a>
                         </div>
                        </div>
                    </div>
       
                    <c:forEach var="entry" items="${entries}" >
                        <div class="post">                          
                        <section>
                            <div class="content">                   
                                <h2><a href="${entry.link}">
                                <c:out value="${entry.title}" /></h2></a>
                                    <p><b>pubDate:</b> ${entry.publishedDate}</p>
                                    <p>${entry.description.value}</p>                                     
                            </div>
                        </section>
                        </div>
                        <br/>
                        <br/>
                    </c:forEach>

                </c:when>       
            </c:choose>
                        
            </div>
           
       </div>
     </div> 
      <script type="text/javascript">
               function FindandDisplay()
                {
                   var username= "<%=session.getAttribute("username") %>";
                   var rssfeed="<%=session.getAttribute("rssfeed") %>";

                    $.post(
                            "./collection",
                            {"username":username},
                             function(data){    
                                 $("#rssreader").html(data);
                                 

                             },
                            "text" );
                       
//                       green color means saved feed 
                      $.post(
                            "./changeColor",
                            {"username":username,"rssfeed":rssfeed},
                             function(data){    
                              //alert(data);
                              if(data==1){
                               $("#change").css("background-color","green");
                              }else{
                               $("#change").css("background-color","#428bca");  
                              }

                             },
                            "text" );
                            
                    
                     
                };
                
               function outputFeed()
                   {
                       
                        var username= "<%=session.getAttribute("username") %>";
                        var rssfeed="<%=session.getAttribute("rssfeed") %>";
//                        var savedfeed="<%=session.getAttribute("savedfeed") %>";
                        
                        if(document.getElementById("change").style.backgroundColor=="green"){
                            document.getElementById("change").style.backgroundColor="#428bca";
                        }else{
                        document.getElementById("change").style.backgroundColor="green";
                    }
                        $.post(
                                "./saveServlet",
                                {"rssfeed":rssfeed,"username":username},
                                 function(data){  
                                     //alert(data);
                                  $("#rssreader").html(data); 
                                   

                                 },
                                "text" );
                         
                   }
               
             
      
              
        </script>
    </body>
    
    
    
   
</html>
