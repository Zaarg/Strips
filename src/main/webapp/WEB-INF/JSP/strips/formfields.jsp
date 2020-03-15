<%@taglib prefix='form' uri='http://www.springframework.org/tags/form'%>

<form:label path='reeks'>Selecteer een Reeks:<form:errors path='reeks'/></form:label>
<form:select items='${reeksen}' itemLabel='naam' itemValue='id' path='reeks' size='10' id='reekslijst' required='required' autofocus='autofocus' />
<div class="inline">
    <form:label path='albumnr'>Album Nr <form:errors path='albumnr' />
    </form:label>
    <form:input path='albumnr' required='required' type='number' min='0' max='999' style="max-width:4em;"/>
</div><div class="inline">
    <form:label path='titel'>Titel<form:errors path='titel' />
    </form:label>
    <form:input path='titel' required='required' maxlength='60' style="min-width:35em;"/>
</div></p><div class="inline">
    <form:label path='paginas'>Paginas <form:errors path='paginas' />
    </form:label>
    <form:input path='paginas' required='required' type='number' min='0' max='999' style="max-width:5em;"/>
</div><div class="inline">    
    <form:label path='druknr'>Druk Nr. <form:errors path='druknr' />
    </form:label>
    <form:input path='druknr' required='required' type='number' min='0'	max='9999' style="max-width:4em;"/>
</div><div class="inline">
    <form:label path='jaar'>Jaar <form:errors path='jaar' />
    </form:label>
    <form:input path='jaar' required='required' type='number' min='1900' max='9999' style="max-width:4em;"/>
</div><div class="inline">
    <form:checkbox path='hardcover' label='Hardcover?' />
</div><div class="inline">
    <form:label path='isbntext'>ISBN<form:errors path='isbntext' /></form:label>
    <form:input path='isbntext' maxlength='20' />
</div><div class="inline">
    <form:label path='inkoopprijs'>Prijs (Eur)<form:errors path='inkoopprijs' /></form:label>
    <form:input path='inkoopprijs' type='number' min='0' step='0.01' style="max-width:6em;"/>
</div>
<p>(houdt CTRL ingedrukt voor meervoudige selectie)</p>
<div class="inline">
    <form:label path='scenaristen'>Scenaristen<form:errors path='scenaristen' />
    </form:label>
    <form:select items='${auteurs}' itemLabel='naam' itemValue='id' path='scenaristen' size='10' multiple='multiple' id='scenaristenlijst' required='required' style="min-width:300px;" />
</div><div class="inline">
    <form:label path='tekenaars'>Tekenaars<form:errors
                    path='tekenaars' />
    </form:label>
    <form:select items='${auteurs}' itemLabel='naam' itemValue='id' path='tekenaars' size='10' multiple='multiple' id='tekenaarslijst' required='required' style="min-width:300px;"/>
</div><div class="inline">
    <form:label path='inkleurders'>Inkleurders<form:errors path='inkleurders' />
    </form:label>
    <form:select items='${auteurs}' itemLabel='naam' itemValue='id' path='inkleurders' size='10' multiple='multiple' id='inkleurderslijst' style="min-width:300px;"/>
</div>
</p><div class="inline">
    <form:label path='speciaal'>Speciaal<form:errors path='speciaal' />
    </form:label>
    <form:input path='speciaal' maxlength='100' style="min-width:400px;"/>
</div></p><div class="inline">
    <form:label path='beschrijving'>Beschrijving<form:errors path='beschrijving' />
    </form:label>
    <form:input path='beschrijving' maxlength='300' style="min-width:35em;"/>
</div></p><div class="inline">
    <form:label path='vertaler'>Selecteer een Vertaler<form:errors path='vertaler' />
    </form:label>
    <form:select items='${vertalers}' itemLabel='naam' itemValue='id' path='vertaler' size='10' id='vertalerlijst' style="min-width:300px;"/>
</div><div class="inline">
    <form:label path='uitgever'>Selecteer een Uitgever<form:errors path='uitgever' />
    </form:label>
    <form:select items='${uitgevers}' itemLabel='naam' itemValue='id' path='uitgever' size='10' id='uitgeverlijst' style="min-width:300px;"/>
</div></p><div class="inline">
    <form:label path='cover'>Cover:<form:errors path='cover' /></form:label>
    <form:input path='cover' maxlength='60' />
</div></p>