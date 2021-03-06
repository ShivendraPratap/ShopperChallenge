<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-16">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <title>Application Form</title>

    <!-- Bootstrap -->
    <link href="./bootstrap.min.css" rel="stylesheet">
    <link href="./main.css" rel="stylesheet">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>
<div class="container" style="margin-top:100px;">
    <h2>Please enter your details</h2>
    <form:form class="form-horizontal" role="form" methon="post" action="/addshopper" modelAttribute="shopper">
        <div class="form-group">
            <form:label path="firstName" class="col-sm-2 control-label">First Name</form:label>
            <div class="col-sm-10">
                <form:input path="firstName" class="form-control" id="focusedInput" type="text"  name="firstName" required="required"/>
            </div>
        </div>
        <div class="form-group">
            <form:label path="lastName" class="col-sm-2 control-label">Last Name</form:label>
            <div class="col-sm-10">
                <form:input path="lastName" class="form-control" id="focusedInput" type="text"  name="firstName" required="required"/>
            </div>
        </div>

        <div class="form-group">
            <form:label path="email" for="email" class="col-sm-2 control-label">Email</form:label>
            <div class="col-sm-10">
                <form:input path="email" class="form-control" id="email" type="email" required="required" />
            </div>
        </div>
        
        <div class="form-group">
            <form:label path="phone" for="phone" class="col-sm-2 control-label">Phone</form:label>
            <div class="col-sm-10">
                <form:input path="phone" class="form-control" id="phone" type="text" required="required" />
            </div>
        </div>
        
        <div class="form-group">
            <div class="col-sm-10 col-sm-offset-2">
                <input class="form-control button" id="submit" type="submit" value="Submit">
            </div>
        </div>

    </form:form>
</div>

<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
</body>
</html>