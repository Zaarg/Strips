<%@page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@taglib prefix='spring' uri='http://www.springframework.org/tags'%>
<%@taglib prefix='form' uri='http://www.springframework.org/tags/form'%>
<%@taglib prefix='stieflo' uri='http://stieflo.no_ip.org/tags' %>

<!doctype html>
<html lang='nl'>
<head><stieflo:head title='Strip wijzigen'/></head>
<body>
<header><stieflo:menu/></header>
<div id="main">
<h1>Strip ${strip.id} wijzigen</h1>
	<spring:url value='/strips/{id}/wijzigen' var='url'>
		<spring:param name='id' value='${strip.id}'/>
	</spring:url>
	<form:form action="${url}" commandName="strip" id='wijzigenform'>	
		<jsp:include page='formfields.jsp' />		
		<input type='submit' value='Wijzigen' id='wijzigenknop'>
	</form:form>
	<script>document.getElementById('wijzigenform').onsubmit = function() {
            document.getElementById('wijzigenknop').disabled = true;};     
    </script>   
</div>
</body>
</html> 