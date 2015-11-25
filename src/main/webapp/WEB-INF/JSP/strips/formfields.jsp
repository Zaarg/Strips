<%@taglib prefix='form' uri='http://www.springframework.org/tags/form'%>

<form:label path='reeks'>Selecteer een Reeks:<form:errors path='reeks'/></form:label>
<form:select items='${reeksen}' itemLabel='naam' itemValue='id' path='reeks' size='10' id='reekslijst' required='required' autofocus='autofocus' />
<form:label path='albumnr'>Album Nr: <form:errors path='albumnr' />
</form:label>
<form:input path='albumnr' required='required' type='number' min='0' max='999' />
<form:label path='titel'>Titel:<form:errors path='titel' />
</form:label>
<form:input path='titel' required='required' maxlength='60' />
<form:label path='paginas'>Paginas: <form:errors path='paginas' />
</form:label>
<form:input path='paginas' required='required' type='number' min='0' max='999' />
<form:label path='druknr'>Druk Nr.: <form:errors path='druknr' />
</form:label>
<form:input path='druknr' required='required' type='number' min='0'	max='9999' />
<form:label path='jaar'>Jaar: <form:errors path='jaar' />
</form:label>
<form:input path='jaar' required='required' type='number' min='1900' max='9999' />
<form:label path='tekenaars'>Selecteer de tekenaars (houdt CTRL ingedrukt voor meervoudige selectie):<form:errors
		path='tekenaars' />
</form:label>
<form:select items='${auteurs}' itemLabel='naam' itemValue='id' path='tekenaars' size='5' multiple='multiple' id='tekenaarslijst' required='required' />
<form:label path='scenaristen'>Selecteer de scenaristen:<form:errors path='scenaristen' />
</form:label>
<form:select items='${auteurs}' itemLabel='naam' itemValue='id' path='scenaristen' size='5' multiple='multiple' id='scenaristenlijst' required='required' />
<form:label path='inkleurders'>Selecteer de inkleurders:<form:errors path='inkleurders' />
</form:label>
<form:select items='${auteurs}' itemLabel='naam' itemValue='id' path='inkleurders' size='5' multiple='multiple' id='inkleurderslijst' />
<div class='rij'>
	<form:checkbox path='hardcover' label='Hardcover?' />
</div>
<form:label path='speciaal'>Speciaal:<form:errors path='speciaal' />
</form:label>
<form:input path='speciaal' maxlength='100' />
<form:label path='beschrijving'>Beschrijving:<form:errors path='beschrijving' />
</form:label>
<form:input path='beschrijving' maxlength='300' />
<form:label path='isbntext'>ISBN:<form:errors path='isbntext' />
</form:label>
<form:input path='isbntext' maxlength='20' />
<form:label path='vertaler'>Selecteer een Vertaler:<form:errors path='vertaler' />
</form:label>
<form:select items='${vertalers}' itemLabel='naam' itemValue='id' path='vertaler' size='5' id='vertalerlijst' />
<form:label path='uitgever'>Selecteer een Uitgever:<form:errors path='uitgever' />
</form:label>
<form:select items='${uitgevers}' itemLabel='naam' itemValue='id' path='uitgever' size='5' id='uitgeverlijst' />
<form:label path='inkoopprijs'>Prijs: <form:errors path='inkoopprijs' />
</form:label>
<form:input path='inkoopprijs' type='number' min='0' />
<form:label path='cover'>Cover:<form:errors path='cover' />
</form:label>
<form:input path='cover' maxlength='60' />