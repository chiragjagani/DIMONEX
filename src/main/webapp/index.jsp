<!-- <!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Dimonix</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.1.3/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,700">
    <link rel="stylesheet" href="assets/css/style.css">
    
    <style>
    .header-blue {
  background:linear-gradient(135deg, #172a74, #21a9af);
  background-color:#184e8e;
  padding-bottom:80px;
  font-family:'Source Sans Pro', sans-serif;
}

@media (min-width:768px) {
  .header-blue {
    padding-bottom:120px;
  }
}

.header-blue .navbar {
  background:transparent;
  padding-top:.75rem;
  padding-bottom:.75rem;
  color:#fff;
  border-radius:0;
  box-shadow:none;
  border:none;
}

@media (min-width:768px) {
  .header-blue .navbar {
    padding-top:1rem;
    padding-bottom:1rem;
  }
}

.header-blue .navbar .navbar-brand {
  font-weight:bold;
  color:inherit;
}

.header-blue .navbar .navbar-brand:hover {
  color:#f0f0f0;
}

.header-blue .navbar .navbar-collapse {
  border-top:1px solid rgba(255,255,255,0.3);
  margin-top:.5rem;
}

@media (min-width:768px) {
  .header-blue .navbar .navbar-collapse {
    border-color:transparent;
    margin:0;
  }
}

.header-blue .navbar .navbar-collapse span .login {
  color:#d9d9d9;
  margin-right:.5rem;
  text-decoration:none;
}

.header-blue .navbar .navbar-collapse span .login:hover {
  color:#fff;
}

.header-blue .navbar .navbar-toggler {
  border-color:rgba(255,255,255,0.3);
}

.header-blue .navbar .navbar-toggler:hover, .header-blue .navbar-toggler:focus {
  background:none;
}

.header-blue .navbar .navbar-nav a.active, .header-blue .navbar .navbar-nav > .show .dropdown-item {
  background:none;
  box-shadow:none;
}

@media (min-width: 768px) {
  .header-blue .navbar-nav .nav-link {
    padding-left:.7rem;
    padding-right:.7rem;
  }
}

@media (min-width: 992px) {
  .header-blue .navbar-nav .nav-link {
    padding-left:1.2rem;
    padding-right:1.2rem;
  }
}

.header-blue .navbar .navbar-nav > li > .dropdown-menu {
  margin-top:-5px;
  box-shadow:0 4px 8px rgba(0,0,0,.1);
  background-color:#fff;
  border-radius:2px;
}

.header-blue .navbar .dropdown-menu .dropdown-item:focus, .header-blue .navbar .dropdown-menu .dropdown-item {
  line-height:2;
  color:#37434d;
}

.header-blue .navbar .dropdown-menu .dropdown-item:focus, .header-blue .navbar .dropdown-menu .dropdown-item:hover {
  background:#ebeff1;
}

.header-blue .action-button, .header-blue .action-button:not(.disabled):active {
  border:1px solid rgba(255,255,255,0.7);
  border-radius:40px;
  color:#ebeff1;
  box-shadow:none;
  text-shadow:none;
  padding:.3rem .8rem;
  background:transparent;
  transition:background-color 0.25s;
  outline:none;
}

.header-blue .action-button:hover {
  color:#fff;
}

.header-blue .navbar .form-inline label {
  color:#d9d9d9;
}

.header-blue .navbar .form-inline .search-field {
  display:inline-block;
  width:80%;
  background:none;
  border:none;
  border-bottom:1px solid transparent;
  border-radius:0;
  color:#ccc;
  box-shadow:none;
  color:inherit;
  transition:border-bottom-color 0.3s;
}

.header-blue .navbar .form-inline .search-field:focus {
  border-bottom:1px solid #ccc;
}

.header-blue .hero {
  margin-top:20px;
  text-align:center;
}

@media (min-width:768px) {
  .header-blue .hero {
    margin-top:60px;
    text-align:left;
  }
}

.header-blue .hero h1 {
  color:#fff;
  font-size:40px;
  margin-top:0;
  margin-bottom:15px;
  font-weight:300;
  line-height:1.4;
}

@media (min-width:992px) {
  .header-blue .hero h1 {
    margin-top:190px;
    margin-bottom:24px;
    line-height:1.2;
  }
}

.header-blue .hero p {
  color:rgba(255,255,255,0.8);
  font-size:20px;
  margin-bottom:30px;
  font-weight:300;
}

.header-blue .phone-holder {
  text-align:right;
}

.header-blue div.iphone-mockup {
  position:relative;
  max-width:300px;
  margin:20px;
  display:inline-block;
}

.header-blue .iphone-mockup img.device {
  width:100%;
  height:300px;
  margin-top:60px;
}

/* .header-blue .iphone-mockup .screen {
  position:absolute;
  width:88%;
  height:77%;
  top:12%;
  border-radius:2px;
  left:6%;
  border:1px solid #444;
  background-color:#aaa;
  overflow:hidden;
  background:url();
  background-size:cover;
  background-position:center;
} */

/* .header-blue .iphone-mockup .screen:before {
  content:'';
  background-color:#fff;
  position:absolute;
  width:70%;
  height:140%;
  top:-12%;
  right:-60%;
  transform:rotate(-19deg);
  opacity:0.2;
} */

    
    
    </style>
</head>

<body>
    <div>
        <div class="header-blue">
            <nav class="navbar navbar-dark navbar-expand-md navigation-clean-search">
                <div class="container" ><a class="navbar-brand" href="#" style="font-size: 80px;margin-top:50px">Dimonix</a><button class="navbar-toggler" data-toggle="collapse" data-target="#navcol-1"></button>
                    <div class="collapse navbar-collapse"
                        id="navcol-1">
                       
                        <form class="form-inline mr-auto" target="_self">
                            <div class="form-group"><label for="search-field"></label><input class="form-control search-field" type="search" name="search" id="search-field"></div>
                        </form><span class="navbar-text"> <a href="login" class="login">Log In</a></span><a href="register" class="btn btn-light action-button" role="button" href="#">Sign Up</a></div>
                </div>
            </nav>
            <div class="container hero">
            
                <div class="row">
                
                    <div class="col-12 col-lg-6 col-xl-5 offset-xl-1">
                  
                        <h1>Dimond revolution is here.</h1>
                        <p>Welcome to dimonex for smooth execution of the diamond inventory and management. </p></div>
                    <div
                        class="col-md-5 col-lg-5 offset-lg-1 offset-xl-0 d-none d-lg-block phone-holder">
                        <div class="iphone-mockup"><img src="https://png.pngtree.com/png-vector/20231027/ourmid/pngtree-the-koh-i-noor-diamond-transparent-background-png-image_10370874.png" class="device">
                            <div class="screen"></div>
                        </div>
                </div>
            </div>
        </div>
    </div>
    </div>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.1.3/js/bootstrap.bundle.min.js"></script>
</body>

</html> -->




<!-- <!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Dimonix</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.1.3/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,700">

    <style>
        /* Global styles */
        body {
            font-family: 'Source Sans Pro', sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f9f9f9;
        }

        .header-blue {
            background: linear-gradient(135deg, #172a74, #21a9af);
            background-color: #184e8e;
            padding-bottom: 80px;
        }

        .navbar {
            background: transparent;
            padding-top: 1rem;
            padding-bottom: 1rem;
            color: #fff;
        }

        .navbar-brand {
            font-weight: bold;
            font-size: 80px;
        }

        .navbar-nav .nav-link {
            padding-left: 1rem;
            padding-right: 1rem;
            color: #fff;
        }

        .hero {
            text-align: center;
            color: white;
            padding: 60px 0;
        }

        .hero h1 {
            font-size: 50px;
            font-weight: 300;
            line-height: 1.4;
            animation: fadeInUp 1s ease-out;
        }

        .hero p {
            font-size: 20px;
            font-weight: 300;
            margin-bottom: 30px;
            animation: fadeInUp 1.5s ease-out;
        }

        .btn-light {
            border-radius: 40px;
            padding: 10px 30px;
            background: #fff;
            color: #21a9af;
            font-weight: bold;
            text-transform: uppercase;
            transition: background-color 0.3s ease;
            animation: fadeInUp 2s ease-out;
        }

        .btn-light:hover {
            background-color: #21a9af;
            color: #fff;
        }

        /* Image Styling */
        .phone-holder {
            text-align: center;
        }

        .iphone-mockup img.device {
            width: 100%;
            height: 400px;
            margin-top: 60px;
            animation: slideInUp 1s ease-out;
        }

        /* Testimonial Section */
        .testimonials {
            background-color: #f4f4f4;
            padding: 50px 0;
        }

        .testimonial-item {
            text-align: center;
            padding: 20px;
            margin: 15px;
            background-color: white;
            border-radius: 10px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            transition: transform 0.3s ease;
        }

        .testimonial-item:hover {
            transform: translateY(-10px);
        }

        .testimonial-item img {
            width: 100px;
            height: 100px;
            border-radius: 50%;
            margin-bottom: 20px;
            border: 3px solid #21a9af;
        }

        .testimonial-item h5 {
            font-size: 18px;
            color: #333;
            font-weight: bold;
        }

        .testimonial-item p {
            color: #777;
        }

        /* Footer Section */
        footer {
            background-color: #172a74;
            color: #fff;
            padding: 40px 0;
        }

        footer p {
            text-align: center;
            margin: 0;
        }

        footer .social-icons i {
            color: #fff;
            margin: 0 15px;
            font-size: 20px;
            transition: color 0.3s ease;
        }

        footer .social-icons i:hover {
            color: #21a9af;
        }

        /* Animations */
        @keyframes fadeInUp {
            from {
                opacity: 0;
                transform: translateY(20px);
            }

            to {
                opacity: 1;
                transform: translateY(0);
            }
        }

        @keyframes slideInUp {
            from {
                transform: translateY(50px);
                opacity: 0;
            }

            to {
                transform: translateY(0);
                opacity: 1;
            }
        }
    </style>
</head>

<body>

    <div class="header-blue">
        <nav class="navbar navbar-expand-md navbar-dark">
            <div class="container">
                <a class="navbar-brand" href="#">Dimonix</a>
                <button class="navbar-toggler" data-toggle="collapse" data-target="#navcol-1"></button>
                <div class="collapse navbar-collapse" id="navcol-1">
                    <form class="form-inline mr-auto">
                        <div class="form-group">
                            <input class="form-control search-field" type="search" name="search" id="search-field" placeholder="Search">
                        </div>
                    </form>
                    <span class="navbar-text"><a href="login" class="login">Log In</a></span>
                    <a href="register" class="btn btn-light action-button" role="button">Sign Up</a>
                </div>
            </div>
        </nav>

        <div class="container hero">
            <div class="row">
                <div class="col-lg-6 col-xl-5 offset-xl-1">
                    <h1>Dimond revolution is here.</h1>
                    <p>Welcome to Dimonex, the smoothest platform for diamond inventory and management.</p>
                    <a href="register" class="btn btn-light">Get Started</a>
                </div>
                <div class="col-lg-5 offset-lg-1 d-none d-lg-block phone-holder">
                    <div class="iphone-mockup">
                        <img src="https://upload.wikimedia.org/wikipedia/commons/7/72/The_Koh-i-Noor_Diamond.png" class="device">
                    </div>
                </div>
            </div>
        </div>
    </div>

    <section class="testimonials">
        <div class="container">
            <h2 class="text-center mb-5">What Our Users Say</h2>
            <div class="row">
                <div class="col-md-4">
                    <div class="testimonial-item">
                        <img src="https://randomuser.me/api/portraits/men/45.jpg" alt="User 1">
                        <h5>John Doe</h5>
                        <p>"Dimonix has completely transformed the way we manage our diamond inventory. It's seamless and efficient!"</p>
                    </div>
                </div>
                <div class="col-md-4">
                    <div class="testimonial-item">
                        <img src="https://randomuser.me/api/portraits/women/45.jpg" alt="User 2">
                        <h5>Jane Smith</h5>
                        <p>"An absolute game-changer! The best platform for managing diamonds in the market." </p>
                    </div>
                </div>
                <div class="col-md-4">
                    <div class="testimonial-item">
                        <img src="https://randomuser.me/api/portraits/men/33.jpg" alt="User 3">
                        <h5>David Lee</h5>
                        <p>"I can't imagine running my business without Dimonix. The features are incredible, and the user experience is fantastic!"</p>
                    </div>
                </div>
            </div>
        </div>
    </section>

    <footer>
        <div class="container">
            <p>&copy; 2025 Dimonix. All rights reserved.</p>
            <div class="social-icons">
                <i class="fa fa-facebook"></i>
                <i class="fa fa-twitter"></i>
                <i class="fa fa-instagram"></i>
            </div>
        </div>
    </footer>

    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.1.3/js/bootstrap.bundle.min.js"></script>
</body>

</html>
 -->
 
 
 
 
 
 
 <!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Dimonix</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.1.3/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,700">

    <style>
        /* Global styles */
        body {
            font-family: 'Source Sans Pro', sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f9f9f9;
        }

        .header-blue {
            background: linear-gradient(135deg, #172a74, #21a9af);
            background-color: #184e8e;
            padding-bottom: 80px;
        }

        .navbar {
            background: transparent;
            padding-top: 1rem;
            padding-bottom: 1rem;
            color: #fff;
        }

        .navbar-brand {
            font-weight: bold;
            font-size: 80px;
        }

        .navbar-nav .nav-link {
            padding-left: 1rem;
            padding-right: 1rem;
            color: #fff;
        }

        .hero {
            text-align: center;
            color: white;
            padding: 60px 0;
        }

        .hero h1 {
            font-size: 50px;
            font-weight: 300;
            line-height: 1.4;
            animation: fadeInUp 1s ease-out;
        }

        .hero p {
            font-size: 20px;
            font-weight: 300;
            margin-bottom: 30px;
            animation: fadeInUp 1.5s ease-out;
        }

        .btn-light {
            border-radius: 40px;
            padding: 10px 30px;
            background: #fff;
            color: #21a9af;
            font-weight: bold;
            text-transform: uppercase;
            transition: background-color 0.3s ease;
            animation: fadeInUp 2s ease-out;
        }

        .btn-light:hover {
            background-color: #21a9af;
            color: #fff;
        }

        /* Image Styling */
        .phone-holder {
            text-align: center;
        }

        .iphone-mockup img.device {
            width: 100%;
            height: 400px;
            margin-top: 60px;
            animation: slideInUp 1s ease-out;
        }

        /* Testimonial Section */
        .testimonials {
            background-color: #f4f4f4;
            padding: 50px 0;
        }

        .testimonial-item {
            text-align: center;
            padding: 20px;
            margin: 15px;
            background-color: white;
            border-radius: 10px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            transition: transform 0.3s ease;
        }

        .testimonial-item:hover {
            transform: translateY(-10px);
        }

        .testimonial-item img {
            width: 100px;
            height: 100px;
            border-radius: 50%;
            margin-bottom: 20px;
            border: 3px solid #21a9af;
        }

        .testimonial-item h5 {
            font-size: 18px;
            color: #333;
            font-weight: bold;
        }

        .testimonial-item p {
            color: #777;
        }

        /* Partners Section */
        .partners {
            background-color: #ffffff;
            padding: 50px 0;
        }

        .partners h2 {
            font-size: 30px;
            text-align: center;
            margin-bottom: 50px;
            font-weight: 700;
        }

        .partners .partner-logo {
            display: inline-block;
            margin: 20px;
            max-width: 150px;
            opacity: 0.7;
            transition: opacity 0.3s ease;
        }

        .partners .partner-logo img {
            width: 100%;
            max-width: 100%;
        }

        .partners .partner-logo:hover {
            opacity: 1;
        }

        /* Footer Section */
        footer {
            background-color: #172a74;
            color: #fff;
            padding: 40px 0;
        }

        footer p {
            text-align: center;
            margin: 0;
        }

        footer .social-icons i {
            color: #fff;
            margin: 0 15px;
            font-size: 20px;
            transition: color 0.3s ease;
        }

        footer .social-icons i:hover {
            color: #21a9af;
        }

        /* Animations */
        @keyframes fadeInUp {
            from {
                opacity: 0;
                transform: translateY(20px);
            }

            to {
                opacity: 1;
                transform: translateY(0);
            }
        }

        @keyframes slideInUp {
            from {
                transform: translateY(50px);
                opacity: 0;
            }

            to {
                transform: translateY(0);
                opacity: 1;
            }
        }
    </style>
</head>

<body>

    <div class="header-blue">
        <nav class="navbar navbar-expand-md navbar-dark">
            <div class="container">
                <a class="navbar-brand" href="#">Dimonix</a>
                <button class="navbar-toggler" data-toggle="collapse" data-target="#navcol-1"></button>
                <div class="collapse navbar-collapse" id="navcol-1">
                    <form class="form-inline mr-auto">
                     <!--    <div class="form-group">
                            <input class="form-control search-field" type="search" name="search" id="search-field" placeholder="Search">
                        </div> -->
                    </form>
                    <span class="navbar-text"><a href="login" class="login">Log In</a></span>
                    <a href="register" class="btn btn-light action-button" role="button">Sign Up</a>
                </div>
            </div>
        </nav>

        <div class="container hero">
            <div class="row">
                <div class="col-lg-6 col-xl-5 offset-xl-1">
                    <h1>Dimond revolution is here.</h1>
                    <p>Welcome to Dimonex, the smoothest platform for diamond inventory and management.</p>
                    <a href="register" class="btn btn-light">Get Started</a>
                </div>
                <div class="col-lg-5 offset-lg-1 d-none d-lg-block phone-holder">
                    <div class="iphone-mockup">
                        <img src="https://png.pngtree.com/png-vector/20231027/ourmid/pngtree-the-koh-i-noor-diamond-transparent-background-png-image_10370874.png" class="device">
                    </div>
                </div>
            </div>
        </div>
    </div>

    <section class="testimonials">
        <div class="container">
            <h2 class="text-center mb-5">What Our Users Say</h2>
            <div class="row">
                <div class="col-md-4">
                    <div class="testimonial-item">
                        <img src="https://randomuser.me/api/portraits/men/45.jpg" alt="User 1">
                        <h5>John Doe</h5>
                        <p>"Dimonix has completely transformed the way we manage our diamond inventory. It's seamless and efficient!"</p>
                    </div>
                </div>
                <div class="col-md-4">
                    <div class="testimonial-item">
                        <img src="https://randomuser.me/api/portraits/women/45.jpg" alt="User 2">
                        <h5>Jane Smith</h5>
                        <p>"An absolute game-changer! The best platform for managing diamonds in the market." </p>
                    </div>
                </div>
                <div class="col-md-4">
                    <div class="testimonial-item">
                        <img src="https://randomuser.me/api/portraits/men/33.jpg" alt="User 3">
                        <h5>David Lee</h5>
                        <p>"I can't imagine running my business without Dimonix. The features are incredible, and the user experience is fantastic!"</p>
                    </div>
                </div>
            </div>
        </div>
    </section>

    <!-- Our Partners Section -->
    <section class="partners">
        <div class="container">
            <h2>Our Partners</h2>
            <div class="row">
                <div class="col-md-2 partner-logo">
                <img src="https://i.imgur.com/7UFXRH7.png" alt="Partner 1"> <!-- De Beers -->
            </div>
            <div class="col-md-2 partner-logo">
                <img src="https://i.imgur.com/jhZg1wI.png" alt="Partner 2"> <!-- Alrosa -->
            </div>
            <div class="col-md-2 partner-logo">
                <img src="https://i.imgur.com/MxUMV3T.png" alt="Partner 3"> <!-- Swatch Group -->
            </div>
            <div class="col-md-2 partner-logo">
                <img src="https://i.imgur.com/ptd22lh.png" alt="Partner 4"> <!-- LVMH -->
            </div>
            <div class="col-md-2 partner-logo">
                <img src="https://i.imgur.com/rlP3l6X.png" alt="Partner 5"> <!-- Tiffany & Co -->
            </div>
            <div class="col-md-2 partner-logo">
                <img src="https://i.imgur.com/F4MQ9xz.png" alt="Partner 6"> <!-- Cartier -->
            </div>
            </div>
        </div>
    </section>

    <footer>
        <div class="container">
            <p>&copy; 2025 Dimonix. All rights reserved.</p>
            <div class="social-icons">
                <i class="fa fa-facebook"></i>
                <i class="fa fa-twitter"></i>
                <i class="fa fa-instagram"></i>
            </div>
        </div>
    </footer>

    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.1.3/js/bootstrap.bundle.min.js"></script>
</body>

</html>
 