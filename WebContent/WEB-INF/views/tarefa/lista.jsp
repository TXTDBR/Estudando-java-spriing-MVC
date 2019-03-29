<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
	<script type="txt/javascript" src="resources/js/jquery-1.8.3.js"></script>
</head>
	<body>
	<script	type="text/javascript">
		function finalizaTarefa(id) {
			$.post("finalizaTarefa", {'id': id}, function()	{
				$("#tarefa_"+id).html("Finalizado");
			});
		}
		</script>
		<a href="novaTarefa">Criar nova tarefa</a>	
		<br>
		<br>
		<table border="1">
			<tr>
				<th>ID</th>
				<th>Descrição</th>
				<th>Finalizado?</th>
				<th>Data de Finalização</th>
				<th>Ações</th>
			</tr>
			<c:forEach items="${tarefas}" var="tarefa">
			<tr>
				<td>${tarefa.id}</td>
				<td>${tarefa.descricao}</td>
				<c:if test="${tarefa.finalizado eq false}">
					<td id="tarefa_${tarefa.id}">
					<a href="#" 	onClick="finalizaTarefa(${tarefa.id})">Finalize agora!</a>
					</td>
				</c:if>
				<c:if test="${tarefa.finalizado eq true }">
					<td>Finalizado</td>
				</c:if>
				<td>
					<fmt:formatDate value="${tarefa.dataFinalizacao.time}" pattern="dd/MM/yyyy"/>
				</td>
				<td>
					<a href="removeTarefa?id=${tarefa.id}">remover</a>
					<a href="mostrarTarefa?id=${tarefa.id}">alterar</a>
				</td>
			</tr>
			</c:forEach>	 
		</table>
	</body>
</html>