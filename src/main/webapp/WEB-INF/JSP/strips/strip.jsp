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
<c:choose>
		<c:when test="${not empty strip}">
			<h1>${strip.titel}</h1>
			<h2>${strip.reeks.naam}, Nr: ${strip.albumnr}</h2>
	        <h3>${strip.paginas} paginas, ${strip.druknr == null ? '?':strip.druknr}${strip.druknr == 1 ? ' ste':' de'} druk (${strip.jaar}), ${strip.hardcover ? 'hardcover' : 'softcover'}</h3>
	        Scenario: 
	        <c:forEach items='${strip.scenaristen}' var='scenarist' varStatus='status'>
	        	${scenarist.naam}${status.last ? '.' : ','}
	        </c:forEach><br>
	        Tekeningen: 
	        <c:forEach items='${strip.tekenaars}' var='tekenaar' varStatus='status'>
	        	${tekenaar.naam}${status.last ? '.' : ','}
	        </c:forEach><br>
	      	Inkleuring: 
	      	<c:forEach items='${strip.inkleurders}' var='inkleurder' varStatus='status'>
	          	${inkleurder.naam}${status.last ? '.' : ','}
	        </c:forEach><br><br>
	        Speciaal: ${strip.speciaal}<br><br>
	        Beschrijving: ${strip.beschrijving}<br><br>
	        ISBN(text): ${strip.isbntext}, ISBN: ${strip.isbn}<br>
	        Vertaler: ${strip.vertaler.naam}<br>
	        Uitgeverij: ${strip.uitgever.naam}, ${strip.uitgever.plaats.plaatsnaam}, ${strip.uitgever.plaats.landcode}<br><br> 
	        Inkoopprijs: € ${strip.inkoopprijs}<br><br>
	        <img alt='${strip.reeks.naam}-${strip.albumnr}' src="<c:url value='/images/covers/${strip.cover}.jpg'/>" >
	        <br>
	        <spring:url value='/strips/toevoegen/clonedfrom{id}' var='cloneURL'>
 					<spring:param name='id' value='${strip.id}'/>
			</spring:url>
			<form action='${cloneURL}'>
				<input type='submit' value='Kloon naar nieuwe strip'>
			</form>	        
	        <spring:url value='/strips/{id}/wijzigen' var='wijzigURL'>
 					<spring:param name='id' value='${strip.id}'/>
			</spring:url>
			<form action='${wijzigURL}'>
				<input type='submit' value='Wijzigen'>
			</form>	        
	        <spring:url value='/strips/{id}/verwijderen' var='verwijderURL'>
  				<spring:param name='id' value='${strip.id}'/>
			</spring:url>
			<form action='${verwijderURL}' method='post'>
				<%--<security:csrfInput /> --%>
				<input type='submit' value='Verwijderen'>
			</form>  		
		</c:when>
		<c:otherwise>
			<div class='fout'>Strip niet gevonden</div>
		</c:otherwise>
</c:choose>	 
</div>
</body>
</html> 