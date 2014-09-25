<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
     "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Send Sms Page</title>
</head>
<style>

	form {
		background-color: green;
		-webkit-border-radius: 8px;
	  	-moz-border-radius: 8px;
	  	border-radius: 8px;
	  	border: 2px solid;
	  	align: center;
	}

</style>
<body>
<form action="SendSmsServlet" method="post" style="width: 250px; padding: 10px">
 <p>
     Phone Number: <br> <input type="text" name="number" style="width: 100%"><BR>
     Message: <br><textarea type="text" name="text" style="width: 100%; height: 100px;" maxlength="150"></textarea><br><br>
     <button name="submit" value="submit" type="submit">Send</button>
  </p>
</form>
</body>
</html>