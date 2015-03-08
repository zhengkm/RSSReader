<%-- 
    Document   : wrong
    Created on : 2014-12-7, 13:46:01
    Author     : zhengkaiming
--%>

<!--use to prevent some people turn to the browse.jsp by inputing the url directly-->
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    if(session.getAttribute("username")==null){
%>
<script type="text/javascript">
    alert("You should log in");
    window.location.href="index.jsp";
</script>
<%
    }
%>
