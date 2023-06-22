<head>


    <link href="/css/signup.css" rel="stylesheet" type="text/css">
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <script  type="text/javascript" src ="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.bundle.min.js"></script>
    <script type="text/javascript" src ="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js" ></script>

</head>

<body>
<section class="login-block">
    <div class="container-fluid">
        <div class="row">
            <div class="col-sm-12">
                <form class="md-float-material form-material" action="/forgot" method="POST">
                    <div class="auth-box card">
                        <div class="card-block">
                            <div class="row">
                                <div class="col-md-12">
                                    <h2 class="text-center heading" ><b>Change your Password</b></h2>
                                </div>
                            </div>
                            <div class="form-group form-primary">

                                <input type="text" class="form-control" name="username" placeholder="Id"  id="name">
                            </div>

                            <div class="form-group form-primary">
                                <input type="text" class="form-control" name="newPassword"  placeholder="Enter your new Password"  id="username">

                            </div>

                            <div class="row">
                                <div class="col-md-12">

                                    <input type="submit" class="btn btn-primary btn-md btn-block waves-effect text-center m-b-20" name="submit" placeholder="Signup Now">
                                    <!--  <button type="button" class="btn btn-primary btn-md btn-block waves-effect text-center m-b-20"><i class="fa fa-lock"></i> Signup Now </button> -->
                                </div>
                            </div>

                            <div class="or-container"><div class="line-separator"></div> <div class="or-label">or</div><div class="line-separator"></div></div>


                            <div class="row">
                                <div class="col-md-12">
                                    <button class="btn btn-lg btn-google btn-block text-uppercase btn-outline" href ="/main"><h4><b>GO BACK</b></h4></button>

                                </div>
                            </div>
                            <br>

                            <p class="text-inverse text-center">Already have an account? <a href="/login" data-abc="true">Login</a></p>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</section>
</body>
