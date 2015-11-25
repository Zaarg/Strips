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
<h1>${reeksen.size()} Reeksen</h1>
	<c:url value='/reeksen' var='url'/>
	<form:form action='${url}' commandName='zoekenForm' method='get'> 
		<form:label path='zoekTerm'>Zoeken: </form:label> <form:errors path='zoekTerm'/>
		<form:input path='zoekTerm' type='text'/> 
		<input type='submit' value='Zoeken'>
		<form:errors cssClass='fout'/> 
	</form:form>
	${nietsgevonden}<br>
	<c:if test='${not empty reeksen}'>
    <table>
      <thead>
        <tr>
          <th>Naam</th>
          <th>Genre</th>
          <th>Beschrijving</th>
          <th>Laatste</th>
          <th>Beëindigd?</th>
          <th>Volledig?</th>
          <th>Uitsluiten lijsten?</th>
        </tr>
      </thead>
      <tbody>
        <c:forEach items='${reeksen}' var='reeks'> 
          <spring:url var='reeksurl' value='/reeksen/{id}'>   
  				<spring:param name='id' value='${reeks.id}'/>
  				<spring:param name='zoekTerm' value='${zoekTerm}'/> 
  		  </spring:url>           
          <tr>
            <td><a href='${reeksurl}#${reeks.id}' id="${reeks.id}">${reeks.naam}</a></td>
            <td>${reeks.genre}</td>
            <td>${reeks.beschrijving}</td>
            <td><c:if test="${not empty reeks.strips}">
        		${reeks.strips.get(reeks.strips.size() - 1).albumnr}
    		</c:if></td>
            <td>${reeks.beeindigd ? 'ja' : 'nee'}</td>
            <td>${reeks.volledig ? 'ja' : 'nee'}</td>
            <td>${reeks.geenlijst ? 'ja' : 'nee'}</td>
          </tr>
          <c:if test='${gekozenreeks.id eq reeks.id}'>
          <tr>
          	<td colspan="7">
          		<spring:url value='/reeksen/{id}/wijzigen' var='wijzigURL'>
 					<spring:param name='id' value='${reeks.id}'/>
				</spring:url>
				<form action='${wijzigURL}'>
				<input type='submit' value='Wijzigen'>
				</form>
          	</td>
          </tr>
          <tr><td colspan ="7"><h2>${reeks.naam}</h2></td></tr>
          <c:choose>
          <c:when test="${not empty reeks.strips}">
          <tr>
          	<td colspan = "7">
          		<table>
          			<thead>
          				<tr>
          					<th>Album nr</th>
          					<th>Titel</th>
          					<th>Paginas</th>
          					<th>Hardcover</th>
          					<th>Jaar</th>
          					
          				</tr>
          			</thead>
          			<tbody>
          				<c:forEach items='${reeks.strips}' var='strip' varStatus="status">
          				<tr>
          					<td>${strip.albumnr}</td>
          					<spring:url var='url' value='/strips/{id}'>   
  								<spring:param name='id' value='${strip.id}'/> 
  		  					</spring:url> 
            				<td><a href='${url}'>${strip.titel}</a></td>
          					<td>${strip.paginas}</td>
          					<td>${strip.hardcover ? 'ja' : 'nee'}</td>
          					<td>${strip.jaar}</td>
          					<c:if test="${status.last}"><td rowspan="${reeks.strips.size()}">
          							<spring:url value='/strips/toevoegen/clonedfrom{id}' var='cloneURL'>
 										<spring:param name='id' value='${strip.id}'/>
									</spring:url>
									<form action='${cloneURL}' id="reeksaanvullenbutton">
										<input type='submit' value='Reeks aanvullen'>
									</form>
          					</td></c:if>
          				</tr>
          				</c:forEach>          				
          			</tbody>
          		</table>
          	</td>
          </tr>	
  		  </c:when>
  		  <c:otherwise>
          				<tr>
          					<td colspan="6">
          						<spring:url value='/reeksen/{id}/verwijderen' var='verwijderURL'>
  									<spring:param name='id' value='${reeks.id}'/>
								</spring:url>
								<form action='${verwijderURL}' method='post'>
								<%--<security:csrfInput /> --%>
								<input type='submit' value='Lege Reeks Verwijderen'>
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