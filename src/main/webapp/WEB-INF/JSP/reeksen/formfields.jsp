<%@taglib prefix='form' uri='http://www.springframework.org/tags/form'%>

<form:label path='naam'>Naam:<form:errors path='naam'/></form:label> 
		<form:input path='naam' autofocus='autofocus' required='required' maxlength='60'/>
		
		<input type="radio" name="genrekeuzetype" id='selectielijst' value="SelectieLijst">Genre uit lijst<br/>
		<form:label path='genre.naam'>Selecteer een Genre:<form:errors path='genre.naam'/> 
		<form:select items='${genres}' itemLabel='naam' itemValue='id' path='genre' size='10' required='required' id='genrelijst'/>
		</form:label>
		
		<input type="radio" name="genrekeuzetype" id="nieuwgenre" value="Nieuw Genre">Nieuw Genre<br/>
		<form:label path='genre.naam'>Maak een nieuw Genre:<form:errors path='genre.naam'/> 
		<form:input path='genre.naam' autofocus='autofocus' required='required' maxlength='40' id='genreinvoer'/>
		</form:label>
		
		<form:label path='beschrijving'>Beschrijving: <form:errors path='beschrijving'/></form:label>
		<form:input path='beschrijving' maxlength='300'/>
		<div class='rij'><form:checkbox path='beeindigd' label='Beeindigd?'/></div>
		<div class='rij'><form:checkbox path='volledig' label='Volledig?'/></div>
		<div class='rij'><form:checkbox path='geenlijst' label='Niet Oplijsten?'/></div>