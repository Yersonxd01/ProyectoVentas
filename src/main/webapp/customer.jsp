<%@page import="model.Customer"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link href="css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
	<div class="container">
		<h1>Registrar Cliente</h1>
		<div class="row">
			<div class="col-6 col-m-4">
				<form action="CustomerServlet?type=registrar">
					<div class="form-group">
						<label>Codigo</label> 
						<input class="form-control" type="text"
							name="txtCode" id="txtCode" value="" >
					</div>
					<div class="form-group">
					<label>Nombre</label>
					<input  class="form-control" type="text"
							name="txtCode" id="txtCode" value="">
					</div>
					<div class="form-group">
					<label>Tipo de documento</label>					
					<select class="form-select" name="selectDocument" id="selectDocument">
					<option>seleccionar</option>
					<option value="DNI">DNI</option>
					<option value="CE">CE</option>
					</select>
					</div>
					
					<div class="form-group">
					<label>Numero Docuemento</label>
					<input  class="form-control" type="text"
							name="txtCode" id="txtCode" value="">
					</div>
					<div class="form-group">	
					<label>Télefono</label>
					<input  class="form-control" type="text"
							name="txtCode" id="txtCode" value="">
				
					</div>
					<div class="form-group">	
					<label>Dirección</label>
					<input  class="form-control" type="text"
							name="txtCode" id="txtCode" value="">
				
					</div>
					<div class="form-group">	
					<label>Razón social</label>
					<input  class="form-control" type="text"
							name="txtCode" id="txtCode" value="">
				
					</div>
					
					<input type="submit" class="btn btn-primary" value="Enviar Datos">	
				</form>
			</div>
			<div class="col-6 col-m-4">
			<table class="table">
			<thead>
			<tr>
			<th>Id</th>
			<th>Nombre</th>
			<th>Tipo Documento</th>
			<th>Numero Documento</th>
			<th>Telefono</th>
			<th>Dirección</th>
			<th>Razón</th>
			<th>Acciones</th>	
			</tr>
			</thead>
			<tbody>
			<tr>
			<%List<Customer> listCustomer = (List<Customer>) request.getAttribute("customer");
			  if(listCustomer != null){
				  for(Customer c : listCustomer){
	
			%>
			<td><%=c.getId()%></td>
			<td><%=c.getName()%></td>
			<td><%=c.getDocumenType()%></td>
			<td><%=c.getDocumentNumber()%></td>
			<td><%=c.getPhone()%></td>
			<td><%=c.getBusinessName()%></td>
			<td></td>			
			</tr>
			
			<%}} %>
			</tbody>
			</table>
			</div>
			
		</div>


	</div>


</body>
</html>