<%@page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@taglib prefix='spring' uri='http://www.springframework.org/tags'%>
<%@taglib prefix='form' uri='http://www.springframework.org/tags/form'%>
<%@taglib prefix='stieflo' uri='http://stieflo.no_ip.org/tags' %>

<!doctype html>
<html lang='nl'>
<head><stieflo:head title='Reeksen'/></head>
<body>
<header><stieflo:menu/></header>
<div id="main">
 <h1>Strips (${strips.size()} albums)</h1>
	<form:form action='${url}' commandName='zoekenForm' method='get'> 
		<form:label path='zoekTerm'>Zoeken: </form:label> <form:errors path='zoekTerm'/>
		<form:input path='zoekTerm' type='text' autofocus='autofocus'/> <input type='submit' value='Zoeken'>
		<form:errors cssClass='fout'/> 
	</form:form>
	${nietsgevonden}<br>
	<c:if test='${not empty strips}'>
    <table>
      <thead>
        <tr>
          <th>Reeks</th>
          <th>AlbumNr</th>
          <th>Titel</th>
          <th>Jaar</th>
          <th>Paginas</th>
          <th>Hardcover?</th>
        </tr>
      </thead>
      <tbody>
        <c:forEach items='${strips}' var='strip'>
          <tr>
          	<td>${strip.reeks.naam}</td>
          	<td>${strip.albumnr}</td>
          	<spring:url var='url' value='/strips/{id}'>   
  				<spring:param name='id' value='${strip.id}'/> 
  		  	</spring:url> 
            <td><a href='${url}'>${strip.titel}</a></td>
            <td>${strip.jaar}</td>
            <td>${strip.paginas}</td>
            <td>${strip.hardcover ? 'ja' : 'nee'}</td>
          </tr>
        </c:forEach>
      </tbody>
    </table>
    </c:if> 
</div>
</body>
</html> 