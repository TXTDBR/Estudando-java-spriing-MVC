<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
	<body>
		<a href="novaTarefa">Criar nova tarefa</a>
		<br>
		<br>
		<table border="1">
			<tr>
				<th>ID</th>
				<th>Descrição</th>
				<th>Finalizado?</th>
				<th>Data de Finalização</th>
			</tr>
			<c:forEach items="${tarefas}" var="tarefa">
			<tr>
				<td>${tarefa.id}</td>
				<td>${tarefa.descricao}</td>
				<c:if test="${tarefa.finalizado eq false}">
				<td>Não finalizado</td>
				</c:if>
				<c:if test="${tarefa.finalixado eq true}">
				<td>${tarefa.finalizado}</td>
				</c:if>
				<td>
					<fmt:formatDate value="${tarefa.dataFinalizacao.time}" pattern="dd/MM/yyyy"/>
				</td>
			</tr>
		</c:forEach>		
		</table>
	</body>
</html>
