<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
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
<title>Base de datos</title>
<spring:url value="/resources" var="publicResourcesUrl" />
<spring:url value="/api/database/import" var="importDataUrl" />
<spring:url value="/api/database/delete" var="deleteDataUrl" />
<link href="${publicResourcesUrl}/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">
<link href="${publicResourcesUrl}/bootstrap/css/theme.css"
	rel="stylesheet">

</head>

<body>

	<jsp:include page="../includes/menu.jsp"></jsp:include>

	<div class="container theme-showcase" role="main">

		<h3>Base de datos</h3>

	<c:set var="scheme" value="${pageContext.request.scheme}"/>	
	<c:set var="serverPort" value="${pageContext.request.serverPort}"/>
	<c:set var="port" value=":${serverPort}"/>

		<a href="#"	onclick='if (confirm("¿Esta seguro de importar datos?")) requestOperation("${scheme}://${pageContext.request.serverName}${port}${importDataUrl}");'
			class="btn btn-success btn-sm" role="button" title="Edit">
		Importar datos <span class="glyphicon glyphicon-pencil"></span></a>
		<a href="#" onclick='if (confirm("¿Esta seguro de eliminar la base de datos?")) requestOperation("${scheme}://${pageContext.request.serverName}${port}${deleteDataUrl}");'
			class="btn btn-danger btn-sm" role="button" title="Eliminar">
		Eliminar datos <span class="glyphicon glyphicon-trash"></span></a>

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
	<script>
    function requestOperation(URLhost) {
	    var req = new XMLHttpRequest();
	    //var URLhost = '${importDataUrl}';
	    
	    req.open('GET', URLhost, true);
	    req.addEventListener('load',function(){
	      if(req.status >= 200 && req.status < 400){
	    	  var response = JSON.parse(req.responseText);
	    	  //alert(response);
	    	  get(response) {
	    		  
	    	  }
	    	  console.log(response);
	      } else {
	    	  console.log('Error in network request: ' + req.statusText);
	      }});
	    req.send(null);
	    event.preventDefault();
    }  
    </script>
</body>
</html>