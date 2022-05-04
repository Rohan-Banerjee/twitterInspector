<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

<style type="text/css">
<%@
include file="style.css" %>
</style>
<link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;500;700&display=swap" rel="stylesheet">

<!-- Resources -->

<script src="https://cdn.amcharts.com/lib/4/core.js"></script>
<script src="https://cdn.amcharts.com/lib/4/charts.js"></script>
<script src="https://cdn.amcharts.com/lib/4/themes/dataviz.js"></script>
<script src="https://cdn.amcharts.com/lib/4/themes/animated.js"></script>
<!-- Chart code -->
<script>
am4core.ready(function() {

// Themes begin
am4core.useTheme(am4themes_dataviz);
am4core.useTheme(am4themes_animated);
// Themes end

var chart1 = am4core.create("chartdiv1", am4charts.XYChart);
var chart3 = am4core.create("chartdiv3", am4charts.XYChart);


var chart4 = am4core.create("chartdiv4", am4charts.XYChart);
var chart5 = am4core.create("chartdiv5", am4charts.XYChart);
var chart6 = am4core.create("chartdiv6", am4charts.XYChart);

var chart7 = am4core.create("chartdiv7", am4charts.XYChart);
var chart8 = am4core.create("chartdiv8", am4charts.XYChart);



var chart2 = am4core.create("chartdiv2", am4charts.XYChart);

function nullify(intent)
{
	var document = JSON.parse(intent);
	console.log(document);
	var output=[];
	
	for(var i=0; i<(document.length);i++)
		{
		
		console.log(i);
		console.log(document[i]);
		if(document[i].value!=0)
			{
			output.push(document[i]);
			}
		}
	console.log(output);
	return output;
}


var intent = JSON.stringify(${intent});
intent = nullify(intent);


var sentiment = JSON.stringify(${sentiment});
sentiment = nullify(sentiment);


var emotion = JSON.stringify(${emotion});
emotion = nullify(emotion);


var review = JSON.stringify(${review});
review = nullify(review);


chartData(chart1,intent)
chartData(chart2,sentiment)
chartData(chart3,emotion)
chartData(chart4,review)
//chartData(chart5,${tox_val})
chartData(chart6,${image})
chartData(chart7,${image_vs_texts})
chartData(chart8,${image_vs_texts})

function chartData(chart,chart_data) {
// Add data

chart.data = chart_data;
var yAxis = chart.yAxes.push(new am4charts.CategoryAxis());

yAxis.dataFields.category = "category";

yAxis.renderer.grid.template.location = 0;

yAxis.renderer.labels.template.fontSize = 10;

yAxis.renderer.minGridDistance = 10;



var xAxis = chart.xAxes.push(new am4charts.ValueAxis());

xAxis.min = 0;




xAxis.hidden = true

// Create series

var series = chart.series.push(new am4charts.ColumnSeries());

series.dataFields.valueX = "value";

series.dataFields.categoryY = "category";

series.columns.template.tooltipText = "{categoryY}: [bold]{valueX}[/]";

series.columns.template.strokeWidth = 1;

series.fill = am4core.color("#67b7dc");
series.columns.template.stroke = am4core.color("#1f7ba6");

series.tooltip.label.interactionsEnabled = true;

series.tooltip.keepTargetHover = true;

series.columns.template.events.on("hit", function (ev) {

  const object = {

    graphColName: ev.target.dataItem.dataContext,

    graphName: "Call_Closing"

  }

  this.changeroute(object);

}, this);



// let scrollbarX = new am4charts.XYChartScrollbar();

//               scrollbarX.series.push(series);

//               chart.scrollbarX = scrollbarX;



var categoryLabel = series.bullets.push(new am4charts.LabelBullet());

categoryLabel.label.text = "{value}";

categoryLabel.label.fontSize = 10;

categoryLabel.label.dx = -10;

categoryLabel.label.fill = am4core.color("#fff");



var label1 = chart.createChild(am4core.Label);

//label1.text = "Country";

label1.fontSize = 12;

// label.align = "left";

label1.isMeasured = false;

// label.x = 0;

// label.y = 0;

label1.y = am4core.percent(50);
label1.verticalCenter = "middle";
label1.x = am4core.percent(0);
label1.horizontalCenter = "middle";
label1.rotation = 270;
} 
}); // end am4core.ready()
</script>
</head>




<body>
<%@include file="shared/header.jsp" %>

<div id="layoutSidenav" style="margin-top: 84px;">
  <div id="layoutSidenav_content">
    <div id="loading" style="display: none"> <img id="loading-image" src="fetch-mod.gif" alt="Loading..." /> </div>
    <div class="container-fluid">
    <div class="row">
        <div class="col-md-12">
            <div id="layoutSidenav_nav"> <a href="createTwitterGraph" class="active">Twitter</a> </div>
        </div>
    </div>
    <div class="row pb-3">
        <div class="col-md-12"> 
            
            <form class="searchForm dashboardSearch" action="fetchDataUsingFiltersTwitter">
                <div class="rangeBar">
                    <div class="largeSearch">
                      <label>Start Date</label>
                      <input type="date" placeholder="choose date range" name="start" daterangepicker="" class="form-control">
                    </div>
                     <div class="largeSearch">
                      <label>End Date</label>
                      <input type="date" placeholder="choose date range" name="end" daterangepicker="" class="form-control">
                    </div>
                    <div class="smallSearch">
                      <label>Intent</label>
                      <select name="intent">
                      <option value="none">Select Criteria</option>	
  										<option value="complaint">Complaint</option>
										  <option value="suggestion">Feedback</option>
										  <option value="spam">Spam</option>
										  <option value="query">Query</option>
										   <option value="news">News</option>
										</select>
                    </div>
                    <div class="smallSearch">
                      <label>Sentiment</label>
                      <select name="sentiment">
                      <option value="none">Select Criteria</option>	
  										<option value="positive">Positive</option>
										  <option value="neutral">Neutral</option>
										  <option value="negative">Negative</option>
										 
										</select>
                    </div>
                     <div class="smallSearch">
                      <label>Tag</label>
                      <input type="text" name="queryTag" required/>
                    </div>
                </div>
                <button type="submit" class="btn btn-primary " id="sub2"
									style="float: right">Submit</button>
            </form>
            
            <div class="dashBoradCol">
              <div class="dataColumn">
                <div class="topCol">
                   <p>Intent</p>
                   <span class="settingCol">
<!--                      <select class="colSearch"><option>Today</option> <option>Yesterday</option></select> -->
                     <a href="#" class="setting"><img src="assets/setting.svg"></a>
                   </span>
                </div>
                <div id="chartdiv1"> </div>
              </div>
             <div class="dataColumn">
                <div class="topCol">
                   <p>Sentiment</p>
                   <span class="settingCol">
<!--                      <select class="colSearch"><option>Today</option> <option>Yesterday</option></select> -->
                     <a href="#" class="setting"><img src="assets/setting.svg"></a>
                   </span>
                </div>
                <div id="chartdiv2"> </div>
              </div>
            <div class="dataColumn">
                <div class="topCol">
                   <p>Emotion</p>
                   <span class="settingCol">
<!--                      <select class="colSearch"><option>Today</option> <option>Yesterday</option></select> -->
                     <a href="#" class="setting"><img src="assets/setting.svg"></a>
                   </span>
                </div>
                <div id="chartdiv3"> </div>
              </div>
            </div>
          
        </div>
    </div>
</div>
  </div>
</div>

  

</body>
</html>