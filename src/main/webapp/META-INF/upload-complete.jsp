<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01//EN">
<html>
<head>
    <script type="text/javascript">
        window.parent.<%= request.getParameter("callback_function") %>("<%= request.getAttribute("file_url") %>");
    </script>
</head>
</html>