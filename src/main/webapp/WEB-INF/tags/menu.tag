<%@ tag language="java" pageEncoding="UTF-8"%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>

<nav>
	<ul>
		<li><a href="<c:url value='/'/>">&#8962;</a></li>
		<li><a href="#">Reeksen</a>
			<ul>
				<li><a href="<c:url value='/reeksen'/>">Overzicht/Zoeken</a></li>
				<li><a href="<c:url value='/reeksen/toevoegen'/>">Toevoegen</a></li>
			</ul>
		</li>
		<li><a href="#">Strips</a>
			<ul>
				<li><a href="<c:url value='/strips'/>">Overzicht/Zoeken</a></li>
				<li><a href="<c:url value='/strips/toevoegen'/>">Toevoegen</a></li>
			</ul>
		</li>
		<li><a href="#">Auteurs</a>
			<ul>
				<li><a href="<c:url value='/auteurs'/>">Overzicht/Zoeken</a></li>
				<li><a href="<c:url value='/auteurs/toevoegen'/>">Toevoegen</a></li>
			</ul>
		</li>
		<li><a href="#">Uitgevers</a>
			<ul>
				<li><a href="<c:url value='/uitgevers'/>">Uitgeverijen Overzicht/Zoeken</a></li>
				<li><a href="<c:url value='/uitgevers/toevoegen'/>">Uitgeverij Toevoegen</a></li>
				<li><a href="<c:url value='/vertalers'/>">Vertalers Overzicht/Zoeken</a></li>
				<li><a href="<c:url value='/vertalers/toevoegen'/>">Vertaler Toevoegen</a></li>
			</ul>
		</li>
		<li><a href="#">Externe Links</a>
			<ul>
				<li><a href="http://www.depoort.com/nl/strips/nieuwe-strips-volgens-datum">De Poort - Nieuwe Strips</a></li>
				<li><a href="http://www.stripspeciaalzaak.be/Stripinvasie.php">Stripspeciaalzaak - Stripinvasie</a></li>
				<li><a href="http://www.catawiki.be/catalogus/1888-strips">Catawiki - Strips</a></li>
			</ul>
		</li>	
	</ul>
</nav>
 