<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>demo</title>
</head>
<link href="/css/zoomify.css"
      rel='stylesheet' type='text/css'/>
<script src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript"
        src="/js/zoomify.js"></script>
<script type="text/javascript">
    $(function () {
        $('img').zoomify();
    });
</script>
</head>

<body style="text-align:center">
<div>
    <img style="height: 75px;"
         src="https://gratisography.com/thumbnails/gratisography-290-thumbnail.jpg"/>
    <img style="height: 75px;"
         src="https://gratisography.com/thumbnails/gratisography-290-thumbnail.jpg"/>
    <img style="height: 75px;"
         src="https://gratisography.com/thumbnails/gratisography-290-thumbnail.jpg"/>
    <img style="height: 75px;"
         src="https://gratisography.com/thumbnails/gratisography-290-thumbnail.jpg"/>
</div>

</body>
</html>