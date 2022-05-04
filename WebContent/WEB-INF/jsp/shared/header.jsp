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





</style>
<script
src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.
min.js"></script>
<script
					src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
					integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
					crossorigin="anonymous"></script> 
<body>

<nav class="navbar navbar-expand-lg navbar-light bg-light shadow fixed-top" style="background-color: white !important;">
    <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" data-target="#navbarTogglerDemo02" aria-controls="navbarTogglerDemo02" aria-expanded="false" aria-label="Toggle navigation"> <span class="navbar-toggler-icon"></span> </button>
     <a class="navbar-brand"><img style="width:12%" src="assets/tilogo.png" alt="tilogo"></a> 
    <div class="collapse navbar-collapse custom-navbar" id="navbarTogglerDemo02">
        <ul class="navbar-nav mr-auto left-nav">
            <li class="nav-item"><a class="nav-link" id="buttn1" href="gotoSearchQuery">Twitter Inspector</a></li>
        </ul>
        <div class="rightSide ml-auto">
             <div class="dropdown"> <a class="btn btn-secondary dropdown-toggle reportA" href="#" role="button" id="dropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Options</a>
                <div class="dropdown-menu" aria-labelledby="dropdownMenuLink"><div class="dropdown-divider"></div><a class="dropdown-item" href="populateAllTweets">Review All moderated tweets</a></div>
            </div>
            
            <div class="dropdown"> <a class="btn btn-secondary dropdown-toggle reportA" href="#" role="button" id="dropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Report </a>
                <div class="dropdown-menu" aria-labelledby="dropdownMenuLink"> <a class="dropdown-item" href="createTwitterGraph">Twitter</a></div>
            </div>
            <a href="#" class="avatar"><img src="assets/user.png" alt=""></a> </div>
    </div>
</nav>
<div id="loading" style=display:none>
<img id="loading-image" src="fetch-mod.gif" alt="Loading..."/>
</div>
</body>	


<script>
$("#buttn1").click(function(){
	$("#loading").show();
	});
	$("#buttn2").click(function(){
		$("#loading").show();
		});
	$("#buttn3").click(function(){
		$("#loading").show();
		});
	$("#buttn4").click(function(){
		$("#loading").show();
		});
	$("#buttn5").click(function(){
		$("#loading").show();
		});
</script>


