<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="css/font-awesome.min.css" type="text/css">
<link href="css/a.css" rel="stylesheet" type="text/css">
<link href="css/b.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="js/jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>
</head>
<body>
	<nav class="navbar navbar-inverse">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand" href="/misordenes/tablet${a}.html">Ordenes</a>
			</div>
			<ul class="nav navbar-nav">
				<li>
					<a href="/misordenes/ConfiguracionCaja?a=${a}">Gestor de Cajas</a>
				</li>
				<li>
					<a href="/misordenes/ConfiguracionAlerta?a=${a}">Gestor de	Tiempo</a>
				</li>
				<li >
					<a href="/misordenes/ConfiguracionLCD?a=${a}">Gestor LCD</a>
				</li>
					<li  class="active">
					<a href="/misordenes/ConfiguracionTablet?a=${a}">Gestor Tablets</a>
				</li>
			</ul>
		</div>
	</nav>
	
	<form action="ConfiguracionTablet?a=${a}" method="post" id="formconfigcaja">
		<div class="section">
			<div class="container">
				<div class="row">
					<div class="col-md-1">
						<img src="img/burger-king.png" class="img-responsive img-rounded" style="margin-left: -60px">
					</div>
					<div class="col-md-1 col-md-offset-1">
					<!-- 	<span onclick="scrollupD()" class="fa fa-arrow-up fa-3x" style="color: #5CB85C; margin-top: 19px"></span> -->
					</div>
					<div class="col-md-offset-1 col-md-1">
						<span onclick="submitDetailsForm()"	class="fa fa-check-circle fa-5x" style="color: #5CB85C"></span>
					</div>
					<div class="col-md-offset-2 col-md-1">
						<a href="tablet${a}.html">
							<span onClick="redirect()" class="fa fa-arrow-circle-o-left fa-5x" style="color: #F0AD4E"></span>
						</a>
					</div>
					<div class="col-md-1 col-md-offset-2">
			<!-- 			<span onclick="scrolldownD()" class="fa fa-arrow-down fa-3x" style="color: #EC1D23; margin-top: 19px"></span> -->
					</div>
				</div>
			</div>
		</div>
		<div class="section" style="background-color: black; color: white;">
			<div class="container">
				<div class="row">
					<div class="col-md-12 scroll" style="border-radius: 25px; border: 2px solid white; padding: 20px; height: 65%; overflow: auto;"	id="disponibles">
						<div class="row h4">
							<div class="col-md-4 h1">Tablets</div>
							<div class="col-md-8 h1 text-center">ZONAS</div>
						</div>
						<c:forEach items="${tablet}" var="l">
							<div class="row">
								<div class="col-md-4 h2">${l.disp_id}</div>
								<div class="col-md-8 h4">
									<div class="checkbox-toolbar" >
										<input type="checkbox" id="radio1${l.disp_id}"	name="tablet_${l.disp_id}" value="1"	<c:if test="${l.z1 eq 1}"> checked="checked"</c:if>>
											<label for="radio1${l.disp_id}">ZONA 1</label>
										<input type="checkbox" id="radio2${l.disp_id}" name="tablet_${l.disp_id}" value="2"	<c:if test="${l.z2 eq 2}"> checked="checked"</c:if>>
											<label for="radio2${l.disp_id}">ZONA 2</label>
										<input type="checkbox" id="radio3${l.disp_id}" name="tablet_${l.disp_id}" value="3"	<c:if test="${l.z3 eq 3}"> checked="checked"</c:if>>
											<label for="radio3${l.disp_id}">ZONA 3</label>
										<input type="checkbox" id="radio4${l.disp_id}" name="tablet_${l.disp_id}" value="4"	<c:if test="${l.z4 eq 4}"> checked="checked"</c:if>>
											<label for="radio4${l.disp_id}">ZONA 4</label>
										<input type="checkbox" id="radioNA${l.disp_id}" name="tablet_${l.disp_id}" value="100"	<c:if test="${l.sz eq 100}"> checked="checked"</c:if>>
											<label for="radioNA${l.disp_id}">SIN ZONA</label>
									</div>
								</div>
							</div>
						</c:forEach>
					</div>
				</div>
			</div>
		</div>
	</form>
		<span onclick="scrollupD()" class="fa fa-arrow-up fa-3x flotanteu" style="color: #5CB85C; margin-top: 19px"></span>
	<span onclick="scrolldownD()" class="fa fa-arrow-down fa-3x flotanted" style="color: #EC1D23; margin-top: 19px"></span>
</body>
<script>
	var d = 1;
	var tope = $(document).height() / 100;
	function scrolldownD() {

		// Getting the height of the document

		var tope = ($(document).height()) / 100 + 1;
		if (tope > d) {
			$("html, body").animate({
				scrollTop : d * 100
			}, 50);

			d++;
			
		}

		//$('#disponibles').animate({ scrollTop:  $('#disponibles').offset().top - 20 }, 'fast');
	}

	function scrollupD() {

		if (d > 0) {
			d--;

			$("html, body").animate({
				scrollTop : d * 100
			}, 50);
			
		}

	}

	function redirect() {
		window.location.href = "layout_gestor.html";

	}

	function submitDetailsForm() {
		$("#formconfigcaja").submit();
	}
</script>
</html>