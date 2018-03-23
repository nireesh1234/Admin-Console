<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="en">
<head>
   <title>Application Logs</title>
   <meta charset="utf-8">
   <meta name="viewport" content="width=device-width, initial-scale=1">   
   <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" />
   <link rel="stylesheet" href="https://cdn.datatables.net/1.10.16/css/dataTables.bootstrap.min.css" />
   <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
   <script src="https://cdn.datatables.net/1.10.16/js/jquery.dataTables.min.js"></script>
   <script src="https://cdn.datatables.net/1.10.16/js/dataTables.bootstrap.min.js"></script>
   
<%-- <meta name="_csrf" content="${_csrf.token}"/>
<!-- default header name is X-CSRF-TOKEN -->
<meta name="_csrf_header" content="${_csrf.headerName}"/> --%>

<script>

$(document).ready(function() {
    $('#example').DataTable();
} );

$('#myModal').on('show', function() {
    var id = $(this).data('id'),
	removeBtn = $(this).find('.danger');
})

$('.confirm-delete').on('click', function(e) {
	e.preventDefault();

    var id = $(this).data('id');
	$('#myModal').data('id', id).modal('show');
});

$('#btnYes').click(function() {
// handle deletion here
	var id = $('#myModal').data('id');
	$('[data-id='+id+']').remove();
	$('#myModal').modal('hide');
});

</script>
</head>

<nav class="navbar navbar-inverse">
	<div class="container">
		<div class="navbar-header">
			<a class="navbar-brand" href="/ajaxspring/dashboard">CAS Admin Console</a>
		</div>
	</div>
</nav>

<body>
<!-- <br><br> -->
<div class="container">
  <br>  		
</div>
<br>

<form method="POST" action="/ajaxspring/applicationlogs/show" modelAttribute="LogObjectModel" >

	<b>Application:</b><input type="text" name="application">	
	<b>Start Date:</b><input type="date" name="StartDate">	
	<b>End Date:</b><input type="date" name="endDate">	 
    <input type="submit" value="Search"/>
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
			
 </form>
 <br>
 <p><b>Click to see complete Logs:</b>
        <a href="/ajaxspring/applicationlogs">
          <span class="glyphicon glyphicon-refresh"></span>
        </a>
      </p>
      
  	<br>
  	<h5><b><u>Important Points</u></b></h5>
  	<ul>
 	 <li>Logged Date Column values are displayed in CET Format</li>
  	 <li> Click on refresh button to see complete logs</li>
 	</ul>
	
	<br><br>
	
	<table id="example" class="table table-striped table-bordered" cellspacing="0" width="100%">
			<thead>
				<tr>
					 <th>CAS Server</th>
					<th>Application</th>					
					<th>Service URL</th>
					<th>Service Ticket</th>
					<th>Logged User</th>
					<th>Logged Date</th>
				</tr>
			</thead>

		<tbody>
			<c:forEach var="logData" items="${log}">
			    <tr>
					<td>${logData.casServerName}</td> 
					<td>${logData.application}</td>
					<td>${logData.serviceURL}</td>
					<td>${logData.ticketId}</td>
					<td>${logData.loggedUser}</td>
					<td>${logData.loggedDate}</td> 
			    </tr>
			</c:forEach>
			</tbody>
		</table>   
	</div> 
    </body>
</html>
