<!DOCTYPE html>
<html>
<head>
<title></title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<title>Social Media Content Moderation</title>
<style type="text/css">
<%@
include file="style.css" %>
</style>

<link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;500;700&display=swap" rel="stylesheet">
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-light bg-light shadow fixed-top" style="padding:10px 20px">
    <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" data-target="#navbarTogglerDemo02" aria-controls="navbarTogglerDemo02" aria-expanded="false" aria-label="Toggle navigation"> <span class="navbar-toggler-icon"></span> </button>
    <a class="navbar-brand"><img src="assets/Eclerx-logo.jpg" alt="logo"></a> </nav>
<section class="section" style="margin-top: 84px;">
    <div class="container indexPage">
        <div class="row firstPage">
            <div class="indexColum">
                <h1>Content Moderation Tool</h1>
                <p>Moderate your social media platforms content and build your business</p>
                <div class="homeGraphic">
                  <img src="assets/logingraphic.png" alt="" >
                </div>
            </div>
            <div class="indexColum loginForm">
                <h2>Welcome</h2>
                <p>Login to continue</p>
                <form action="checkLogin">
                    <div class="form-group">
                        <label>USERNAME</label>
                        <input name="email" type="text" class="form-control" required="">
                    </div>
                    <div class="form-group">
                        <label>PASSWORD</label>
                        <input name="pass" type="password" class="form-control" required="">
                    </div>
                    <button class="myBtn" type="submit">Login</button>
                </form>
                <p>Sorry ${user}. Please enter correct details.</p>
            </div>
        </div>
    </div>
</section>
</body>
</html>