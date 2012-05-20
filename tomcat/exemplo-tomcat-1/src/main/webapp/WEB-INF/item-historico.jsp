<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.List,fourlinux.justjava.Lance"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Histórico do Item</title>
	</head>
	<body>
		<h1>Histórico do item</h1>
		<table>
		<tr>
			<th>Usuário</th>
			<th>Valor</th>
			<th>Data</th>
		</tr>
		<% 
			List<Lance> historico = (List<Lance>) request.getAttribute("historico"); 
			if (historico != null) {
				for (Lance lance : historico) { %>
		<tr>
			<td><%= lance.getEmailUsuario() %></td>
			<td><%= lance.getValorLance() %></td>
			<td><%= lance.getData() %></td>
		</tr>
		<% 		}
			}%>
		</table>
	</body>
	
</html>