<%@page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@taglib prefix='spring' uri='http://www.springframework.org/tags'%>
<%@taglib prefix='form' uri='http://www.springframework.org/tags/form'%>
<%@taglib prefix='stieflo' uri='http://stieflo.no_ip.org/tags' %>

<!doctype html>
<html lang='nl'>
<head><stieflo:head title='Uitgevers'/></head>
<body>
<header><stieflo:menu/></header>
<div id="main">
<h1>${uitgevers.size()} Uitgevers</h1>
	<c:url value='/uitgevers' var='url'/>
	<form:form action='${url}' commandName='zoekenForm' method='get'> 
		<form:label path='zoekTerm'>Zoeken: </form:label> <form:errors path='zoekTerm'/>
		<form:input path='zoekTerm' type='text'/> 
		<input type='submit' value='Zoeken'>
		<form:errors cssClass='fout'/> 
	</form:form>
	${nietsgevonden}<br>
	<c:if test='${not empty uitgevers}'>
    <table>
      <thead>
        <tr>
          <th>Naam</th>
          <th>Plaats</th>       
        </tr>
      </thead>
      <tbody>
        <c:forEach items='${uitgevers}' var='uitgever'>
          <c:url value="" var="uitgeverURL">
             <c:param name="uitgeverid" value="${uitgever.id}"/>
          </c:url>
          <tr>
            <td><a href='${uitgeverURL}#${uitgever.id}' id="${uitgever.id}">${uitgever.naam}</a></td>
            <td>${uitgever.plaats.plaatsnaam}, ${uitgever.plaats.landcode}</td>
          </tr>
          <c:if test='${param.uitgeverid eq uitgever.id}'>
          <tr>
          	<td colspan="3">
          		<spring:url value='/uitgevers/{id}/wijzigen' var='wijzigURL'>
 					<spring:param name='id' value='${uitgever.id}'/>
				</spring:url>
				<form action='${wijzigURL}'>
				<input type='submit' value='Wijzigen'>
				</form>
          	</td>
          </tr>
          <c:choose>
    	  <c:when test="${not empty uitgever.strips}">
          <tr>
          	<td colspan = "2">
          		<table>
          			<thead>
          				<tr>
          					<th>Uitgever van</th>
          				</tr>
          			</thead>
          			<tbody>          				
          				<c:forEach items='${uitgever.strips}' var='strip'><tr>
          					<td>
          						${strip.albumnr}
          						${strip.reeks.naam}
          						<spring:url var='url' value='/strips/{id}'>   
  									<spring:param name='id' value='${strip.id}'/> 
  		  						</spring:url>
          						<a href='${url}'>${strip.titel}</a>
          					</td>
          				</tr></c:forEach>
          			</tbody>
          		</table>
          	</td>
          </tr>
          </c:when>
  		  <c:otherwise>
  		  				<tr>
          					<td colspan="3">
          						<spring:url value='/uitgevers/{id}/verwijderen' var='verwijderURL'>
  									<spring:param name='id' value='${uitgever.id}'/>
								</spring:url>
								<form action='${verwijderURL}' method='post'>
								<%--<security:csrfInput /> --%>
								<input type='submit' value='Uitgever zonder strips Verwijderen'>
								</form>
							</td>
          				</tr>
            </c:otherwise>
            </c:choose>	
  		  </c:if>
        </c:forEach>
      </tbody>
    </table>
    </c:if> 
</div>
</body>
</html> 