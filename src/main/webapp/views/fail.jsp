<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<head>
    <link href="/css/success.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/1.1.3/sweetalert.min.css">
</head>
<body>
<div class="wrapper">
    <form class="card-body" action="/success" method="POST"></form>
    <div class="text-center">
        <h1 class="card-title" type=""><b>Fail</b></h1>
        <p class="card-description"><h2>Return Back!</h2></p>
        <h3><button class="btn btn-outline-success" onclick="redirectToLogin()"><b>Retry!</b></button></h3>

        <script>
            function redirectToLogin() {
                window.location.href = "/login";
            }
        </script>

    </div>
</div>
</body>
