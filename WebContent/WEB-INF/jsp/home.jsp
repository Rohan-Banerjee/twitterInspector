<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Home</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<style>

#loading {
width: 100%;
height: 100%;
top: 0;
left: 0;
position: fixed;
display: block;
opacity: 0.7;
justify-content:right;
background-color: #fff;
z-index: 99;
text-align: center;
}
#loading-image {
position: absolute;
top: 300px;
justify-content:center;
z-index: 100;
}

body {

    background: linear-gradient(to bottom, #0f75bc 50%, #052942 100%);

    background-repeat: no-repeat;

    background-size: cover;

    height: auto;

}

div#roww {

    height: 330px;

}

div#coltext {

    margin: auto;

}

li.nav-item.dropdown {

    margin-top: 10px;

}

img.logohome {

    margin-top: 5px;

}

 

.first-image, .second-image {

    background-color: #ffffff;

}

 

img.row-img {

    width: 100%;

    border-radius: 10px;

    height: 100%;

}

 

.text-col {

    padding: 4%;

}

 

.first-container {

    margin-top: 10%;

    padding-right: 0px;

    padding-left: 0px;

    border-radius: 10px;

}

 

.second-container {

    padding-right: 0px;

    padding-left: 0px;

    border-radius: 10px;

}

 

.bg-image-home h1 {

    font-size: 35px;

    font-family: 'Open Sans', sans-serif;

    color: #2e384d;

}

 

.bg-image-home p {

    font-family: 'Open Sans', sans-serif;

    color: #b0bac9;

    font-size: 16px;

}

 

button.btn-primary {
    background-color: #00AC34; padding: 10px 85px; color: #f7f7f2; margin-top: 2%; border-radius: 6px; border:none; font-weight: 500; font-size: 15px; 
    }

 

.second-image h1 {

    text-align: right;

}

 

.second-image p {

    text-align: right;

}

 

.second-image button.btn-primary {

    float: right;

}

 

img.user-img {

    width: 40px;

}

section.bg-image-home {

    padding: 4% 0%; height: 96vh; display: flex;
    justify-content: center;
    align-items: center;

}


.myClass { display: flex; flex-direction: column; font-family: 'Roboto', sans-serif; }

.socialColumn {
    background: #FFFFFF;
    border-radius: 10px;
    padding: 28px;
    display: flex;
    flex-direction: column;
    text-align: center;
    justify-content: space-between;
    align-items: center;
    flex: 1;
}

.myClass h4 {color:#2E384D; font-size: 28px;font-family: 'Roboto', sans-serif;}
.socialColumn p {color:#677585; font-size: 15px line-height:22px;}

.icon {
    margin-bottom: 16px;
    width: 60px;
    height: 60px;
}
.icon img{
width: 100%;
height: auto ; 
}
.no_color
{
color:transparent;
}
/*****MOBILE MEDIA QUERY*****/

@media only screen and (max-width: 768px) {

    .first-container {

        margin-top: 30%;

        text-align: center;

    }

 

    .bg-image-home h1 {

        font-size: 24px;

    }

 

    .second-container {

        text-align: center !important;

    }

 

    .second-image h1 {

        text-align: center;

    }

 

    .second-image p {

        text-align: center;

    }

 

    .second-image button.btn-primary {

        float: none;

    }

 

    .bg-image-home {

        padding: 4%;

    }

 

    .bg-image-home {

        height: 100%;

    }

 

    .text-col {

        padding: 8%;

    }

    section.bg-image-home {

        padding: 4% 4%;

    }

}

 

/*****IPAD MEDIA QUERY*****/

@media only screen 

and (min-device-width : 768px) 

and (max-device-width : 1024px) {

 

    .first-container {

        margin-top: 20%;

        text-align: center;

    }

    

    section.bg-image-home {

        padding: 8% 0% 50% 0%;

    }

 

    .bg-image-home {

        height: 100%;

    }

 

    .second-container {

        text-align: center !important;

    }

 

    .second-image h1 {

        text-align: center;

    }

 

    .second-image p {

        text-align: center;

    }

 

    .second-image button.btn-primary {

        float: none;

    }

}

 

#forAgent{

 margin-top: 18%;;

}


</style>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;500&display=swap" rel="stylesheet">
</head>
<body>

  <nav class="navbar navbar-expand-lg navbar-light bg-light shadow fixed-top">

    <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse"

      data-target="#navbarTogglerDemo02" aria-controls="navbarTogglerDemo02" aria-expanded="false"

      aria-label="Toggle navigation">

      <span class="navbar-toggler-icon"></span>

    </button>

    <a class="navbar-brand"><img  style="width:12%" src="assets/tilogo.png" alt="logo" ></a>

 

    <div class="collapse navbar-collapse custom-navbar" id="navbarTogglerDemo02">

      <ul class="navbar-nav ml-auto right-nav">

        <li class="nav-item">

          <a class="nav-link"><img class="logohome" src="assets/Home.jpg" alt="logo-home"></a>

        </li>

        <li class="nav-item dropdown">

          <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown"

            aria-haspopup="true" aria-expanded="false">

            ${userNameVisible}

          </a>

          <div class="dropdown-menu" aria-labelledby="navbarDropdown">

            <a class="dropdown-item" (click)="onLogout()">logout</a>

 

          </div>

        </li>

        <li class="nav-item">

          <a class="nav-link" href="#"><img src="assets/user.png" alt="arrow" class="user-img"></a>

        </li>

      </ul>

    </div>

  </nav>
<div id="loading" style=display:none>
<img id="loading-image" src="fetch-mod.gif" alt="Loading..."/>
</div>



  <section class="bg-image-home">

<form>


          
            <div class="socialColumn">
              <div class="icon"><img src="assets/twitter.svg"></div>
              <h4>Twitter Inspector</h4>
             
              <button type="submit" id="sub1" class="btn btn-primary" onclick="javascript: form.action='gotoSearchQuery';">Inspect</button>
            </div>
         

		
		
		

    

 	 
 		

    </form>

  </section>

 <script>
$("#sub1").click(function(){
$("#loading").show();
});


$("#sub2").click(function(){
$("#loading").show();
});

$("#sub3").click(function(){
	$("#loading").show();
	});
	
$("#sub4").click(function(){
	$("#loading").show();
	});

</script>


</body>
</html>