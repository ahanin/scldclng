<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01//EN">
<html>
<head>
    <link href="style.css" rel="stylesheet"/>
</head>
<body>
    <h1>SuperUpload</h1>
    <p>Congratulations! You just posted!</p>
    <div id="form-area">
        <h2>Message:</h2>
        <p><%= request.getParameter("message") %></p>
        <% final String fileUrl = request.getParameter("file_url"); %>
        <% if (fileUrl != null && fileUrl.length() > 0 ) { %>
        <h2>Attachment:</h2>
        <a href="<%= request.getParameter("file_url") %>" target="_blank"><%= request.getParameter("file_url") %></a>
        <% } %>
    </div>
</body>
</html>