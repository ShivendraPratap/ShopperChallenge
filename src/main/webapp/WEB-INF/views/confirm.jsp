<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-16">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <title>Success Page</title>

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
<body>
<h2>Congrats!!!!</h2>
<table class="table table-striped">
    <b> Your Application has been submitted! Please find the details entered below! </b>
    <tr>
        <td>First Name :</td>
        <td>${firstname}</td>
    </tr>

    <tr>
        <td>Last Name :</td>
        <td>${lastname}</td>
    </tr>

    <tr>
        <td>Email :</td>
        <td>${email}</td>
    </tr>

    <tr>
        <td>Phone number :</td>
        <td>${phone}</td>
    </tr>
</table>
</body>

</body>
</html>