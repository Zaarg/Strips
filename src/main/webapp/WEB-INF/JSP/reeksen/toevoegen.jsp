<%@page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@taglib prefix='spring' uri='http://www.springframework.org/tags'%>
<%@taglib prefix='form' uri='http://www.springframework.org/tags/form'%>
<%@taglib prefix='stieflo' uri='http://stieflo.no_ip.org/tags' %>

<!doctype html>
<html lang='nl'>
<head><stieflo:head title='Strips'/></head>
<body>
<header><stieflo:menu/></header>
<div id="main">
<h1>Reeks toevoegen</h1>
	<c:url value='/reeksen' var='url'/>
	<form:form action='${url}' commandName='reeks' id='toevoegform'>	
		<jsp:include page='formfields.jsp' />
		<input type='submit' value='Toevoegen' id='toevoegknop'>
	</form:form>
	<script>document.getElementById('toevoegform').onsubmit = function() {
            document.getElementById('toevoegknop').disabled = true;};
          
          document.getElementById('selectielijst').onclick = enableDisableInputs;
      	  document.getElementById('nieuwgenre').onclick = enableDisableInputs;
      	  enableDisableInputs();
      	  function enableDisableInputs() {
      	  document.getElementById('genrelijst').disabled = ! document.getElementById('selectielijst').checked;
      	    document.getElementById('genreinvoer').disabled = ! document.getElementById('nieuwgenre').checked; 
      	  };     
    </script>  
</div>
</body>
</html> 