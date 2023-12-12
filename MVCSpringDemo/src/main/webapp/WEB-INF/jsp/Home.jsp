
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page import="java.util.*"%>
<%@ page import="com.practice.vo.Demo"%>
<!DOCTYPE html>
<html>
<head>
<style>
table {
	font-family: arial, sans-serif;
	border-collapse: collapse;
	widtd: 100%;
}

.container {
	text-align: left;
	padding: 8px;
}

.containerrr {
	text-align: left;
	padding: 28px;
}

td, td {
	border: 2px solid #dddddd;
	text-align: left;
	padding: 10px;
}

tr:ntd-child(even) {
	background-color: #dddddd;
}

.input-label {
	color: #3d4751;
	font-size: 18px;
	font-family: open_sansRegular;
	margin-top: 15px;
	margin-bottom: 2px;
	font-weight: 400;
}

.form-control {
	border-color: #cecece;
	background: #fff;
	box-shadow: none none none transparent;
	-moz-box-shadow: none none none transparent;
	-webkit-box-shadow: none none none transparent;
	color: #3d4751;
	font-size: .875em;
	padding: 4px 50px;
	vertical-align: middle;
	border-radius: 3px;
	-moz-border-radius: 4px;
	-webkit-border-radius: 4px;
	height: 25px;
}

.resizedTextbox {
	widtd: 500px;
	height: 30px
}

.resizedtext {
	widtd: 500px;
	height: 200px
}

selection__arrow {
	height: 26px;
	position: absolute;
	top: 1px;
	right: 1px;
	widtd: 20px;
}

.primary {
	background-color: #0095da;
	border: 1px solid #0095da;
	color: #fff;
	margin-right: 0;
	border-radius: 3px;
	height: 30px;
}

.panal-body {
	display: table;
	content: " ";
}

.required input:after {
	content: "*";
	color: red;
}
</style>
<%
ArrayList<Demo> lst = (ArrayList) request.getAttribute("list");  
System.out.println("my list size is :"+lst.size());
%>
<title>Insert title here</title>
</head>
<body>
	<lable>
	<h4>Running Notes</h4>
	</lable>
	<div>

		<table border="1" width="60%" cellpadding="2">

			<tr>
				<th></th>
				<th>Sub Category</th>
				<th>Notes</th>
				<th>Notes Type</th>
				<th>Date/Time</th>
			</tr>
			<!--<c:forEach var="demo" items="${list}">-->


			<% for(Demo demo : lst){ %>
			<tr>
				<td><input type="radio" name="rad" id="rad" path="id"
					value="<%=demo.getId() %>"></input></td>
				<td><%=demo.getSubcategory() %></td>
				<td><%=demo.getNotes() %></td>
				<td><%=demo.getNotetype()%></td>
				<td><%=demo.getDateandtime() %></td>
			</tr>
			<%} %>
			<!--  <td>${demo.subcategory}</td>
					<td>${demo.notetype}</td>
					<td>${demo.notes}</td>
					<td>${demo.dateandtime}</td>-->

			<!--</c:forEach>-->
		</table>
		<button type="reset" class="primary" onclick="deleteval()">Delete</button>

	</div>
	<form:form action="save" method="post" modelAttribute="connection">
		<div class="panal-body">
			<br></br>
			<div class="container input-label">
				<lable for="subcategory">Sub Category</lable>
				<select name="subcategory" id="subcategory" path="subcategory"
					class="form-control resizedTextbox selection__arrow">
					<option value=""></option>
					<option value="MIB">MIB</option>
					<option value="Financial">Financial</option>
					<option value="Risk Management">Risk Management</option>
					<option value="Decision Tool">Decision Tool</option>
					<option value="Reinsurance">Reinsurance</option>
					<option value="Amendments">Amendments</option>
				</select>
			</div>
			<div class="containerrr input-label">
				<lable for="notetype">Note Type</lable>
				<select name="notetype" id="notetype" path="notetype"
					class="form-control resizedTextbox selection__arrow">
					<option value=""></option>
					<option value="Public">Public</option>
				</select>
			</div>
			<button type="button" class="primary">Select from List</button>
			<br></br>
			<div class="required">
				<label for="notes">Notes</label> <input type="text" id="notes"
					name="notes" path="notes" class="resizedtext"></input>
			</div>
			</br>
			<buttons>
			<button type="submit" class="primary" onclick="save()">Save</button>
			<button type="button" class="primary">Save as Draft</button>
			<button type="button" class="primary">Clear</button>
			</buttons>
		</div>
	</form:form>

	<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
	 <script type="text/javascript">
     
 
		function save() {
			var category = document.getElementById("subcategory").value;
			var notetype = document.getElementById("notetype").value;
			var notes = document.getElementById("notes").value;
			alert(category + " " + notetype + " " + notes);

			if (category === "") {
				alert("Sub Category field is empty");
			}

		}
		function deleteval(){
			var id ;
			debugger;
			var radioButtons = document.getElementsByName("rad");
			
			for(var i=0;i<=radioButtons.length;i++){
				
				if(radioButtons[i].checked){
					id = radioButtons[i].value;
					alert("you have deleted the value "+ id);
					break;
				}
			}
			
		
	    if (id) {
	        // Make an AJAX call to delete the record
	        $.ajax({
	            type: "DELETE",
	            url: "/MVCSpringDemo/home.com/deleteRecord/"+id,
	            //data: { id: id },
	            success: function (response) {
	                if (response === "success") {
	                    alert("Record deleted successfully");
	                    // Optionally, refresh the page or update the UI
	                    location.reload();
	                } else {
	                    alert("error deleting the record");
	                    location.reload();
	                }
	            },
	            error: function () {
	                alert("Error deleting the record");
	                location.reload();
	            }
	        });
	    } else {
	        alert("Please select a record to delete");
	    }
		}
		</script>
	
</body>

</html>