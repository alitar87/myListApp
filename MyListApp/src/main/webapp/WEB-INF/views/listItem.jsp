<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Add a new list item</title>
</head>
<body>

<h2>List Item</h2>
<form:form method="POST" action="/demo/addListItem">
   <table>
    <tr>
        <td><form:label path="title">List Item</form:label></td>
        <td><form:input path="title" /></td>
    </tr>
    <tr>
        <td><form:label path="details">List Item Details</form:label></td>
        <td><form:input path="details" /></td>
    </tr>
    <tr>
        <td colspan="2">
            <input type="submit" value="Submit"/>
        </td>
    </tr>
</table>  
</form:form>

</body>
</html>
