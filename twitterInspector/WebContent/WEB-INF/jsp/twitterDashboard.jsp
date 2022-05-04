<!DOCTYPE html>
<html>
<head>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<meta charset="ISO-8859-1">
<title>Social Media Content Moderation</title>
<link rel="stylesheet" type="text/css" href="style.css">
<style type="text/css">
<%@
include file="style.css" %>
</style>
<link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;500;700&display=swap" rel="stylesheet">
</head>
<body class="sb-nav-fixed" style="background-color: #eff3f6 !important;">
<%@include file="shared/header.jsp" %>
<div id="loading" style="display:none;"><img id="loading-image" src="fetch-mod.gif" alt="Loading..."/></div>
<div id="layoutSidenav" style="margin-top: 84px;">
    <div id="layoutSidenav_content">
        <div id="loading" style=display:none> <img id="loading-image" src="fetch-mod.gif" alt="Loading..."/></div>
        <div class="container-fluid">
            <div class="row">
                <div class="col-md-12">
                    <div id="layoutSidenav_nav"> <a href="" class="active">Moderation Results</a> <a href="gotoSearchQuery" >Twitter Search</a></div>
                </div>
          
                <div class="col-md-12">
                <div class="sectionColumn moderationTable">
                    <div role="tabpanel" class="tab-pane active" id="tag">
                        <h2 style="text-align:center;"> ${err}</h2>
                          <div class="titleArea">
                            <h2>Twitter Moderation</h2>
                            <div class="summry">
                            <a href="saveResultsTwitter" class="generateBtn"> Save</a>
                              <a href="createTwitterGraph" class="generateBtn"> Generate Summary</a>
                              <a class="arwIcon"><i class='fas fa-chevron-down'></i></a>
                            </div>
                          </div>
                           <div role="tabpanel" class="tab-pane active" id="tag">

                                            <div class="row">

                                                <div class="col-md-12" id="aftershowtran">

                                                    <div id="forScroll">
                                                  

                                                  <table class="table_prop">
											<tr>
											<th>Tweets</th>
											<th>Results</th>
											</tr>
											<c:forEach var="original" items="${original}" varStatus="out">
											<tr>
											<td> ${original} </td>
											<td><b>Expressions: </b><c:out value="${intent[out.index]}" />, <c:out value="${sentiment[out.index]}" />, <c:out value="${emotion[out.index]}" /></td>
											</tr>
											</c:forEach>
											
										</table>

                                                    </div>

                            </div>

                            <!------Tabs content Closed------>
                            

                        </div>

						<form action="nextTwitter">
					<input type="text" name="curPage" value="${page}" hidden="true"> 
					<input type="submit" > Next Page </input>
					</form>
                    </div>
                        
              
                </div>
                
                </div>
            </div>
        </div>
        <div class="row">
        
          <div class="col-md-12">
            <div class="sectionColumn result">
            <c:choose><c:when test="${res=='1'}">
    </c:when>    
    <c:otherwise>
     
   
         <div class="titleArea">
                            <h2>Twitter Results</h2>
                            <div class="summry">
                             
                              <a class="arwIcon"><i class='fas fa-chevron-down'></i></a>
                            </div>
                          </div>
                                        
<div class="" id="aftershowtran">
                                <div id="forScroll">
                                <table id="table" class="table table-striped">
                                    <thead>
                                        <tr>
                                            <th id="callid">S.no</th>
                                            <th id="agntName">Name</th>
                                            <th id="pro">Tweet</th>
                                            <th id="do">Image</th>
                                        </tr>
                                    </thead>
                                    <c:forEach items="${message}" var="entry" varStatus="status">
                                        <tbody>
                                            <tr>
                                                <td><c:out value="${status.count}" /></td>
                                                <td> ${entry}</td>
                                                <td><c:out value="${message1[status.index]}"/></td>
                                                <td><c:choose><c:when test="${message2[status.index]=='0'}">
       <img src="https://upload.wikimedia.org/wikipedia/commons/thumb/6/6c/No_image_3x4.svg/1024px-No_image_3x4.svg.png" style="width:150px; height:100px;" >
    </c:when>    
    <c:otherwise>
       <img src="${message2[status.index]}" style="width:150px; height:100px;" alt="No Image">
        <br />
    </c:otherwise></c:choose></td>
                                            </tr>
                                    </c:forEach>
                                </table>
                            </div>
                        </div>
                                        
                                        
                                        
                                        

                   

                 

 </c:otherwise></c:choose>
            </div>
          </div>
        </div>
    </div>
</div>
</div>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script> 
<script src='https://kit.fontawesome.com/a076d05399.js'></script>

<script>
    $("#sub_twitter").click(function(){
    $("#loading").show();
    });  
    $("#sub_twitter_search").click(function(){
      $("#loading").show();
      });  
    $("#sub_facebook_timeline").click(function(){
      $("#loading").show();
      });
    $("#sub_facebook_posts").click(function(){
      $("#loading").show();
      }); 
  </script> 
<script>
    $("#sub2").click(function(){
    $("#loading").show();
    });
     $("#layoutSidenav_nav li a").hover(function(){
    $(".subMenu").css('display', 'block');
    });
    
  </script>
</body>
</html>