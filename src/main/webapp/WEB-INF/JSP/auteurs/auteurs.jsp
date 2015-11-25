<%@page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@taglib prefix='spring' uri='http://www.springframework.org/tags'%>
<%@taglib prefix='form' uri='http://www.springframework.org/tags/form'%>
<%@taglib prefix='stieflo' uri='http://stieflo.no_ip.org/tags' %>

<!doctype html>
<html lang='nl'>
<head><stieflo:head title='Auteurs'/></head>
<body>
<header><stieflo:menu/></header>
<div id="main">
<h1>${auteurs.size()} Auteurs</h1>
	<c:url value='/auteurs' var='url'/>
	<form:form action='${url}' commandName='zoekenForm' method='get'> 
		<form:label path='zoekTerm'>Zoeken: </form:label> <form:errors path='zoekTerm'/>
		<form:input path='zoekTerm' type='text'/> 
		<input type='submit' value='Zoeken'>
		<form:errors cssClass='fout'/> 
	</form:form>
	${nietsgevonden}<br>
	<c:if test='${not empty auteurs}'>
    <table>
      <thead>
        <tr>
          <th>Naam</th>
          
        </tr>
      </thead>
      <tbody>
        <c:forEach items='${auteurs}' var='auteur'>
          <c:url value="" var="auteurURL">
             <c:param name="auteurid" value="${auteur.id}"/>
          </c:url>
          <tr>
            <td><a href='${auteurURL}#${auteur.id}' id="${auteur.id}">${auteur.naam}</a></td>
          </tr>
          <c:if test='${param.auteurid eq auteur.id}'>
          <tr>
          	<td colspan="3">
          		<spring:url value='/auteurs/{id}/wijzigen' var='wijzigURL'>
 					<spring:param name='id' value='${auteur.id}'/>
				</spring:url>
				<form action='${wijzigURL}'>
					<input type='submit' value='Wijzigen'>
				</form>
          	</td>
          </tr>
          <c:choose>
          <c:when test="${(not empty auteur.scenariststrips) or (not empty auteur.tekenaarstrips) or (not empty auteur.inkleurderstrips)}">
          <tr>
          	<td>
          		<table>
          			<thead>
          				<tr>
          					<th>Scenarist van</th>
          					<th>Tekenaar van</th>
          					<th>Inkleurder van</th>
          				</tr>
          			</thead>
          			<tbody>          				
          				<tr>
          					<td><c:forEach items='${auteur.scenariststrips}' var='strip'>
          						${strip.albumnr}
          						${strip.reeks.naam}
          						<spring:url var='url' value='/strips/{id}'>   
  									<spring:param name='id' value='${strip.id}'/> 
  		  						</spring:url>
          						<a href='${url}'>${strip.titel}</a><br>
          					</c:forEach>	
          					</td>
          					<td><c:forEach items='${auteur.tekenaarstrips}' var='strip'>
          						${strip.albumnr}
          						${strip.reeks.naam}
          						<spring:url var='url' value='/strips/{id}'>   
  									<spring:param name='id' value='${strip.id}'/> 
  		  						</spring:url>
          						<a href='${url}'>${strip.titel}</a><br>
          					</c:forEach>	
          					</td>
          					<td><c:forEach items='${auteur.inkleurderstrips}' var='strip'>
          						${strip.albumnr}
          						${strip.reeks.naam}
          						<spring:url var='url' value='/strips/{id}'>   
  									<spring:param name='id' value='${strip.id}'/> 
  		  						</spring:url>
          						<a href='${url}'>${strip.titel}</a><br>
          					</c:forEach>	
          					</td>
          				</tr>
          			</tbody>
          		</table>
          	</td>
          </tr>	
  		  </c:when>
  		  <c:otherwise>
  		  				<tr>
          					<td colspan="3">
          						<spring:url value='/auteurs/{id}/verwijderen' var='verwijderURL'>
  									<spring:param name='id' value='${auteur.id}'/>
								</spring:url>
								<form action='${verwijderURL}' method='post'>
								<%--<security:csrfInput /> --%>
								<input type='submit' value='Auteur zonder strips Verwijderen'>
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