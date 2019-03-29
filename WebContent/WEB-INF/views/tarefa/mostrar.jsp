<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix='fmt' %>
<html>
<body>
	<h3>Alterar tarefa ${tarefa.id}</h3>
	<form:errors path="tarefa.descricao"/>
	<form action="alterarTarefa" method="post">
		
		<input	type="hidden"	name="id"	value="${tarefa.id}"	/>
		
		Descrição:<br>
		<textarea name="descricao" cols="100" rows="5">
		<%----%>${tarefa.descricao}<%----%></textarea>
		<br	/>	
		Finalizado?
		<input type="checkbox"  name="finalizado" value="true" ${tarefa.finalizado ? 'checked' : ''}/>
		<br>
		Data de Finalização:<br>
		<input type="text" name="dataFinalizacao" value="<fmt:formatDate 
			value="${tarefa.dataFinalizacao.time}" 
			pattern="dd/MM/yyy"/>"/>
			<br>
			<input type="submit" value="alterar"/>
	</form>

</body>
</html>
