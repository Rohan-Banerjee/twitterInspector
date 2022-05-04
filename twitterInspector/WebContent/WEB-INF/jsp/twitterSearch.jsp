<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<style type="text/css">
<%@
include file="style.css" %>
</style>
<meta charset="ISO-8859-1">
<title>Social Media Content Moderation</title>
<link rel="stylesheet" href="chart.css" />

<link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;500;700&display=swap" rel="stylesheet">
</head>
<body class="sb-nav-fixed" style="background-color: #eff3f6 !important;">
<%@include file="shared/header.jsp" %>
<div id="loading" style="display: none"> <img id="loading-image" src="fetch-mod.gif" alt="Loading..." /> </div>
<div id="layoutSidenav" style="margin-top: 70px;">
<div id="layoutSidenav_content">
<div id="loading" style="display: none"> <img id="loading-image" src="fetch-mod.gif" alt="Loading..." /> </div>
<div class="container-fluid">
    <div class="row">
        <div class="col-md-12">
            <div id="layoutSidenav_nav"> <a href="gotoSearchQuery" class="active">Twitter Search</a> </div>
        </div>
    </div>
    <div class="row pb-3">
        <div class="col-md-12"> 
            <form class="searchForm" action="search">
                <h3>Search Keywords:</h3>
                <div class="searchBar">
                    <input class="form-control" name="query" type="text"
										placeholder="Enter Query...">
                </div>
                <button type="submit" class="btn btn-primary " id="sub2"
									style="float: right">Search</button>
									 <h3 style="color:red; text-align:center;"> ${err}</h3>
            </form>
            <c:choose>
                <c:when test="${original!=null}">
                    <div class="searchResultCol scroll" >
                    <div class="card-body tabs-card">
                     <a href="saveResultsTwitter" class="generateBtn"> Save</a>
                        <div id="forScroll" style="height:460px;">
                            <table class="table_prop">
                                <tr>
                                    <th>Tweets</th>
                                    <th>Results</th>
                                    
                                  
                                </tr>
                                <c:forEach var="original" items="${original}" varStatus="out">
                                    <tr>
                                        <td> ${original} - <a href="https://twitter.com/jainhitendra/status/${tweetId[out.index]}/">View Tweet</a></td>
                                        <td><c:forEach var="words" items="${moderateText[out.index]}" varStatus="loopCounter">
                                              
                                            </c:forEach><br><b>Expressions: </b><c:out value="${intent[out.index]}" />, <c:out value="${sentiment[out.index]}" />, <c:out value="${emotion[out.index]}" /></td>
                                       
                                       
                                    </tr>
                                </c:forEach>
                            </table>
                        </div>
                    </div>
                </c:when>
            </c:choose>
        </div>
    </div>
</div>
</div>
</div>
<script
					src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
					integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
					crossorigin="anonymous"></script> 
<script>
					$("#sub_twitter").click(function() {
						$("#loading").show();
					});

					$("#sub_twitter_search").click(function() {
						$("#loading").show();
					});

					$("#sub_facebook_timeline").click(function() {
						$("#loading").show();
					});

					$("#sub_facebook_posts").click(function() {
						$("#loading").show();
					});
				</script> 
<script>
					$("#sub2").click(function() {
						$("#loading").show();
					});
				</script>
</body>
</html>