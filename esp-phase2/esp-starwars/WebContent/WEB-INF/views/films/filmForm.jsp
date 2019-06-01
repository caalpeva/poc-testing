<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<title>Listado de Peliculas</title>
<spring:url value="/resources" var="publicResourcesUrl" />
<spring:url value="/films/index" var="filmQueryUrl" />
<link href="${publicResourcesUrl}/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">
<link href="${publicResourcesUrl}/bootstrap/css/theme.css"
	rel="stylesheet">
<link rel="stylesheet"
	href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">

</head>

<body>

	<jsp:include page="../includes/menu.jsp"></jsp:include>

	<div class="container theme-showcase" role="main">

		<div class="page-header">
			<h3 class="blog-title">
				<span class="label label-success">Listado de Peliculas</span>
			</h3>
		</div>

		<form:form action="${filmQueryUrl}" method="post"
			class="form-horizontal" modelAttribute="filmFormInstance">
			<div class="table-responsive">
				<table class="table table-hover table-striped table-bordered">
					<tr>
						<th>&nbsp;</th>
						<th>Título</th>
					</tr>

					<c:forEach var="film" items="${availableFilms}">
						<tr>
							<td><form:checkbox value="${film.id}" path="films" /></td>
							<td>${film.title}</td>
						</tr>
					</c:forEach>

				</table>

				<div class="form-group">
					<div class="col-sm-offset-2 col-sm-10">
						<button type="submit" class="btn btn-success">Enviar</button>
					</div>
				</div>

			</div>
		</form:form>

		<hr class="featurette-divider">

		<jsp:include page="../includes/footer.jsp"></jsp:include>

	</div>
	<!-- /container -->

	<!-- Bootstrap core JavaScript
    ================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
	<script src="${publicResourcesUrl}/bootstrap/js/bootstrap.min.js"></script>
	<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
	<script>
		$(function() {
			$("#fechaEstreno").datepicker({
				dateFormat : 'dd-mm-yy'
			});
		});
	</script>
</body>
</html>
