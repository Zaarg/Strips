<%@page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@taglib prefix='spring' uri='http://www.springframework.org/tags'%>
<%@taglib prefix='form' uri='http://www.springframework.org/tags/form'%>
<%@taglib prefix='stieflo' uri='http://stieflo.no_ip.org/tags' %>

<!doctype html>
<html lang='nl'>
<head><stieflo:head title='Vertalers'/></head>
<body>
<header><stieflo:menu/></header>
<div id="main">
<h1>${vertalers.size()} Vertalers</h1>
	<c:url value='/vertalers' var='url'/>
	<form:form action='${url}' commandName='zoekenForm' method='get'> 
		<form:label path='zoekTerm'>Zoeken: </form:label> <form:errors path='zoekTerm'/>
		<form:input path='zoekTerm' type='text'/> 
		<input type='submit' value='Zoeken'>
		<form:errors cssClass='fout'/> 
	</form:form>
	${nietsgevonden}<br>
	<c:if test='${not empty vertalers}'>
    <table>
      <thead>
        <tr>
          <th>Naam</th>       
        </tr>
      </thead>
      <tbody>
        <c:forEach items='${vertalers}' var='vertaler'>
          <c:url value="" var="vertalerURL">
             <c:param name="vertalerid" value="${vertaler.id}"/>
          </c:url>
          <tr>
            <td><a href='${vertalerURL}#${vertaler.id}' id="${vertaler.id}">${vertaler.naam}</a></td>
          </tr>
          <c:if test='${param.vertalerid eq vertaler.id}'>
          <tr>
          	<td colspan="3">
          		<spring:url value='/vertalers/{id}/wijzigen' var='wijzigURL'>
 					<spring:param name='id' value='${vertaler.id}'/>
				</spring:url>
				<form action='${wijzigURL}'>
					<input type='submit' value='Wijzigen'>
				</form>
          	</td>
          </tr>
          <c:choose>
    	  <c:when test="${not empty vertaler.strips}">
          <tr>
          	<td>
          		<table>
          			<thead>
          				<tr>
          					<th>Vertaler van</th>
          				</tr>
          			</thead>
          			<tbody>          				
          				<c:forEach items='${vertaler.strips}' var='strip'><tr>
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
          						<spring:url value='/vertalers/{id}/verwijderen' var='verwijderURL'>
  									<spring:param name='id' value='${vertaler.id}'/>
								</spring:url>
								<form action='${verwijderURL}' method='post'>
								<%--<security:csrfInput /> --%>
								<input type='submit' value='Vertaler zonder strips Verwijderen'>
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