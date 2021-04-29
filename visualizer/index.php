<!doctype html>
<html lang="en" class="no-js">
<head>
    <meta charset="utf-8">
    <!--[if IE]><![endif]-->

    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <title></title>
    <meta name="description" content="">
    <meta name="author" content="">
    <meta name="viewport" content="width=device-width; initial-scale=1.0; maximum-scale=1.0;">
    <link rel="stylesheet" href="css/style.css?v=1">
</head>

<!--[if lt IE 7 ]>
<body class="ie6"> <![endif]-->
<!--[if IE 7 ]>
<body class="ie7"> <![endif]-->
<!--[if IE 8 ]>
<body class="ie8"> <![endif]-->
<!--[if IE 9 ]>
<body class="ie9"> <![endif]-->
<!--[if (gt IE 9)|!(IE)]><!-->
<body> <!--<![endif]-->


<div id="container">
    <header>
        <table id="players">
            <tr>
                <td style="width:40%;text-align:right;" class="player1Name"></td>
                <td style="width:20%;text-align:center;" class="playerVs">Loading</td>
                <td style="width:40%;text-align:left;" class="player2Name"></td>
            </tr>
        </table>
    </header>

    <div id="main">
        <canvas id="display" width="640" height="640"></canvas>
        <p id="turnCounter">Loading</p>
        <p id="controls">
            <a href="#" id="start-button" title="Jump to start"><span class="small">|</span>&laquo;</a> |
            <a href="#" id="prev-frame-button" title="Previous frame (left arrow)">&laquo;</a> |
            <a href="#" id="play-button" title="Pause (space bar)">&#9654;</a> |
            <a href="#" id="next-frame-button" title="Next frame (right arrow)">&raquo;</a> |
            <a href="#" id="end-button" title="Jump to end">&raquo;<span class="small">|</span></a><br/>
            <a href="#" id="speeddown" title="Slow down (down arrow)">-</a>
            <a href="#" id="speedup" title="Speed up (up arrow)">+</a>
        </p>

        <p>
            <br>
            <br>
            <canvas id="chart" width="640" height="100"></canvas>
        </p>
    </div>

    <footer>

    </footer>
</div> <!-- end of #container -->

<script src="http://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script>!window.jQuery && document.write('<script src="js/jquery-3.4.1.js"><\/script>')</script>

<script><?php echo 'const data = "'.str_replace("\n", "\\n"). '"'; ?></script>

<script src="js/visualizer.js?v=1"></script>
</body>
</html>