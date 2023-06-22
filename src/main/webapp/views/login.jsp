<head>
    <link href="/css/login.css" rel="stylesheet" type="text/css">
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <script  type="text/javascript" src ="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.bundle.min.js"></script>
    <script type="text/javascript" src ="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js" ></script>
</head>

<body>

<div class="register-photo">
    <div class="form-container">
        <div class="image-holder"></div>
        <form action="/login" method="post">
            <h2 class="text-center"><strong>Airplane Reservation</strong></h2>
            <div class="form-group">
                <input class="form-control" type="Username" name="username" placeholder="Username">
            </div>

            <div class="form-group">
                <input class="form-control" type="password" name="password" placeholder="Password">
            </div>

            <div class="form-group">
                <div class="d-flex justify-content-between">
                    <div class="form-check"> <input class="form-check-input" type="checkbox" value="" id="flexCheckDefault"> <label class="form-check-label" for="flexCheckDefault"> Remember me </label> </div>
                </div>
                <div class="d-flex justify-content-around">
                    <div> <a href="/forgot" class="text-info" style="text-align: center">Forgot Password</a> </div>
                    <div class="form-group"><a href="/signup" class="text-info" type="submit" style="text-align:right; display: block; width: 100%;">Sign Up</a>
                    </div>

                </div>

            </div>
            <div class="form-group"><button class="btn btn-success btn-block btn-info" type="submit">Login</button></div>

            <a class="already" href="#">Terms of Use and Privacy Policy</a>
        </form>
    </div>
</div>
</body>