<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
	<head>
	    <title>Ticket booking</title>
	</head>
	<body>
	     <h3>Create a new user</h3>
	     <form method="post">
	        <table>
        		<tr>
            		<td>Name:</td>
           			<td><input name="name" value="Leonid Brezhnev"/></td>
        		</tr>
       			<tr>
            		<td>Email:</td>
            		<td><input name="email" value="leonidillich@kpss.su"/></td>
        		</tr>
        		<tr>
            		<td>Amount:</td>
            		<td><input name="amount" value="0.0"/></td>
        		</tr>
        		<tr>
            		<td colspan="2">
                		<input type="submit" value="Save Changes" />
            		</td>
        		</tr>
    		</table>
	     </form>
	</body>
</html>