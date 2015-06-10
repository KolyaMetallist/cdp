<html>
	<body>
	    <h1>Ticket booking system</h1>
	     <ul>
		 	<li><a href="users">Users</a></li>
		 	<li><a href="events">Events</a></li>
		 	<li><a href="tickets">Tickets</a></li>
		</ul> 
		<br/>
		<a href="tickets/ticket/book">Book ticket</a>
		<br/>
		<h2>Please upload a file</h2>
        <form method="post" action="upload" enctype="multipart/form-data">
        	Select a file
            <input type="file" name="file"/>
            <input type="submit" value="Upload"/>
        </form>
	</body>
</html>