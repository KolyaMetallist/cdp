<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
	<head>
	    <title>Ticket booking</title>
	</head>
	<body>
	     <h3>Book a new ticket</h3>
	     <form method="post">
	        <table>
        		<tr>
            		<td>Select user:</td>
           			<td>
           				<select name="userId">
           					<c:forEach items="${userIds}" var="userId">
           						<option value="${userId.key}">${userId.value}</option>
           					</c:forEach>
           				</select>
           			</td>
        		</tr>
       			<tr>
            		<td>Select event:</td>
           			<td>
           				<select name="eventId">
           					<c:forEach items="${eventIds}" var="eventId">
           						<option value="${eventId.key}">${eventId.value}</option>
           					</c:forEach>
           				</select>
           			</td>
        		</tr>
        		<tr>
            		<td>Select ticket category:</td>
           			<td>
           				<select name="category">
           					<c:forEach items="${categories}" var="category">
           						<option value="${category}">${category}</option>
           					</c:forEach>
           				</select>
           			</td>
        		</tr>
        		<tr>
            		<td>Place:</td>
            		<td><input name="place" value="1"/></td>
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