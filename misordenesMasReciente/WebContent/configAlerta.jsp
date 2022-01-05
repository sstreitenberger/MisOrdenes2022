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

				<li><a href="/misordenes/ConfiguracionCaja?a=${a}">Gestor de Cajas</a></li>
				<li class="active"><a href="/misordenes/ConfiguracionAlerta?a=${a}">Gestor
						de Tiempo</a></li>
				<li >
					<a href="/misordenes/ConfiguracionLCD?a=${a}">Gestor LCD</a>
				</li>
					<li>
					<a href="/misordenes/ConfiguracionTablet?a=${a}">Gestor Tablets</a>
				</li>
			</ul>
		</div>
	</nav>

	<form action="ConfiguracionAlerta?a=${a}" method="post" id="formconfigtiempo">




		<div class="section">
			<div class="container">
				<div class="row">
					<div class="col-md-1">
						<img src="img/burger-king.png" class="img-responsive img-rounded">
					</div>
					<h1 class="col-md-4 text-center">Tiempos</h1>

				</div>
			</div>
		</div>
		<div class="section" style="background-color: black; color: white;">
			<div class="container">

				<div class="row">
					<div class="col-md-offset-1 col-md-1">

						<span onclick="submitDetailsForm2()"
							class="fa fa-check-circle fa-5x" style="color: #5CB85C"></span>

					</div>
					<div class="col-md-offset-2 col-md-1">

						<a href="tablet${a}.html"> <span onClick="redirect()"
							class="fa fa-arrow-circle-o-left fa-5x" style="color: #F0AD4E"></span>
						</a>
					</div>
				</div>

				<div class="row">

					<div class="col-md-6"
						style="border-radius: 25px; border: 2px solid white; padding: 20px; height: 65%; overflow: auto;"
						id="disponibles">


						<div class="row">
							<div class="col-md-1 col-md-offset-4">



								<span class="fa fa-minus fa-3x" onclick="amenos('sumar1')"
									style="color: white; margin-top: 30px; margin-left: -8px"></span>

							</div>
							<div class="round-button col-md-1 ">
								<div class="round-button-circle-alertA">
									<a class="round-button sumar1"
										style="font-size: 40px; margin-top: -14px;">${config.alertA}
									</a>

								</div>

							</div>
							<div class="col-md-1">
								<span class="fa fa-plus fa-3x" onclick="amas('sumar1')"
									style="color: white; margin-top: 30px; margin-left: -8px"></span>

							</div>

						</div>
						<div class="row">
							<div class="col-md-1 col-md-offset-4">




								<span class="fa fa-minus fa-3x" onclick="amenos('sumar2')"
									style="color: white; margin-top: 30px; margin-left: -8px"></span>



							</div>
							<div class="round-button col-md-1">
								<div class="round-button-circle-alertB">
									<a id="sumar2" class="round-button sumar2"
										style="font-size: 40px; margin-top: -14px;">${config.alertB}
									</a>
								</div>
							</div>
							<div class="col-md-1">
								<span class="fa fa-plus fa-3x" onclick="amas('sumar2')"
									style="color: white; margin-top: 30px; margin-left: -8px"></span>

							</div>
						</div>


						<div class="row">
							<div class="col-md-1 col-md-offset-4">




								<span class="fa fa-minus fa-3x" onclick="amenos('sumar3')"
									style="color: white; margin-top: 30px; margin-left: -8px"></span>

							</div>
							<div class="round-button col-md-1">
								<div class="round-button-circle-alertC">
									<a class="round-button sumar3"
										style="font-size: 40px; margin-top: -14px;">${config.alertC}
									</a>
								</div>
							</div>
							<div class="col-md-1">
								<span class="fa fa-plus fa-3x" onclick="amas('sumar3')"
									style="color: white; margin-top: 30px; margin-left: -8px"></span>

							</div>
						</div>

	<input class="hidden " id="AlertaA" name="AlertaA" >
	<input class="hidden "  id="AlertaB" name="AlertaB" >
	<input class="hidden " id="AlertaC" name="AlertaC" >
	


						<!-- div class="row">	
									<div class="col-sm-2">
							
                  <label  class="control-label" style="color:yellow;font-weight:bold;">Alerta A </label>
                </div>
             
                <div class="col-sm-9">
                  <input  class="form-control" id="AlertaA" name="AlertaA" placeholder="Tiempo en Segundos" value="${config.alertA}">
                </div>
				<div class="col-sm-1">
				<label  class="control-label" >Seg.</label>
				</div>
				 </div>
				 
				 
				 <div class="row">
				 
				<div class="col-sm-2">
							
                  <label  class="control-label" style="color:orange;font-weight:bold;">Alerta B</label>
                </div>
                <div class="col-sm-9">
                  <input  class="form-control" id="AlertaB" name="AlertaB" placeholder="Tiempo en Segundos" value="${config.alertB}">
                </div>
                <div class="col-sm-1">
				<label  class="control-label" >Seg.</label>
				</div>
				
				  </div>
				  
				  
				  
				   <div class="row">
				  
                				<div class="col-sm-2">
							
                  <label  class="control-label" style="color:red;font-weight:bold;">Alerta C</label>
                </div>
                <div class="col-sm-9">
                  <input  class="form-control" id="AlertaC" name="AlertaC" placeholder="Tiempo en Segundos" value="${config.alertC}">
                </div>
<div class="col-sm-1">
				<label  class="control-label text-left" >Seg.</label>
				</div>
						
						
						
					
						
						
						
						
						  </div-->



					</div>



				</div>


			</div>
		</div>
	</form>
</body>
<script>
	function redirect() {
		window.location.href = "layout_gestor.html";

	}
	function submitDetailsForm2() {
		
		$("#AlertaA").val($('.sumar1').text( ));
		$("#AlertaB").val($('.sumar2').text( ));
		$("#AlertaC").val($('.sumar3').text( ));
	
		$("#formconfigtiempo").submit();
		
	}
	
	function amas(ob){
		$('.'+ob).text( Number($('.'+ob).text())+1 )
	
	}
	
	function amenos(ob){
		if (Number($('.'+ob).text())>=1)
			$('.'+ob).text( Number($('.'+ob).text())-1 )
	}
</script>
</html>