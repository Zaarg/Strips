<%@taglib prefix='form' uri='http://www.springframework.org/tags/form'%>

<form:label path='naam'>Naam:<form:errors path='naam'/></form:label> 
<form:input path='naam' autofocus='autofocus' required='required' maxlength='40'/>
<form:label path='plaats.plaatsnaam'>Plaats:<form:errors path='plaats.plaatsnaam'/></form:label> 
<form:input path='plaats.plaatsnaam' required='required' maxlength='40'/>
<form:label path='plaats.landcode'>Landcode:<form:errors path='plaats.landcode'/></form:label> 
<form:input path='plaats.landcode' required='required' maxlength='2'/>