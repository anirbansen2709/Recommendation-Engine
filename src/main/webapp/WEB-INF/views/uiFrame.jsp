<%--
  Created by IntelliJ IDEA.
  User: Rushil Mahaan
  Date: 2/22/2016
  Time: 9:20 AM
  this jsp page is for head of hside bar, top bar and navigation bar
--%>
<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta name="description" content="Bootstrap Admin App + jQuery">
    <meta name="keywords" content="app, responsive, jquery, bootstrap, dashboard, admin">
    <title>Listen Me</title>
    <!-- =============== VENDOR STYLES ===============-->
    <!-- FONT AWESOME-->
    <link rel="stylesheet" href="./resources/vendor/fontawesome/css/font-awesome.min.css">
    <!-- SIMPLE LINE ICONS-->
    <link rel="stylesheet" href="./resources/vendor/simple-line-icons/css/simple-line-icons.css">
    <!-- ANIMATE.CSS-->
    <link rel="stylesheet" href="./resources/vendor/animate.css/animate.min.css">
    <!-- WHIRL (spinners)-->
    <link rel="stylesheet" href="./resources/vendor/whirl/dist/whirl.css">
    <!-- =============== PAGE VENDOR STYLES ===============-->
    <!-- WEATHER ICONS-->
    <link rel="stylesheet" href="./resources/vendor/weather-icons/css/weather-icons.min.css">
    <!-- =============== BOOTSTRAP STYLES ===============-->
    <link rel="stylesheet" href="./resources/app/css/bootstrap.css" id="bscss">
    <!-- =============== APP STYLES ===============-->
    <link rel="stylesheet" href="./resources/app/css/app.css" id="maincss">
    <!-- DATATABLES-->
    <link rel="stylesheet" href="./resources/vendor/datatables-colvis/css/dataTables.colVis.css">
    <link rel="stylesheet" href="./resources/vendor/datatable-bootstrap/css/dataTables.bootstrap.css">

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

    <link rel = "stylesheet" href = "https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.3.0/css/font-awesome.min.css">

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <link rel="stylesheet" type="text/css" href="resources/slick/slick.css"/>
    <link rel="stylesheet" type="text/css" href="resources/slick/slick-theme.css"/>
</head>

<body>
<div class="wrapper">
    <!-- top navbar-->
    <header class="topnavbar-wrapper">
        <!-- START Top Navbar-->
        <nav role="navigation" class="navbar topnavbar" style="background-color: #003153 !important; background-image: none !important;">
            <!-- START navbar header-->
            <div class="navbar-header">
                <a href="#/" class="navbar-brand">
                    <div class="brand-logo">
                        <b><h3>Listen Me</h3></b>
                    </div>
                    <div class="brand-logo-collapsed">
                        <img src="./resources/images/snmp.jpg" alt="SNMP" class="img-responsive">
                    </div>
                </a>
            </div>
            <!-- END navbar header-->
            <!-- START Nav wrapper-->
            <div class="nav-wrapper">
                <!-- START Left navbar-->
                <ul class="nav navbar-nav">
                    <li>
                        <!-- Button used to collapse the left sidebar. Only visible on tablet and desktops-->
                        <a href="#" data-toggle-state="aside-collapsed" class="hidden-xs">
                            <em class="fa fa-navicon"></em>
                        </a>
                        <!-- Button to show/hide the sidebar on mobile. Visible on mobile only.-->
                        <a href="#" data-toggle-state="aside-toggled" data-no-persist="true"
                           class="visible-xs sidebar-toggle">
                            <em class="fa fa-navicon"></em>
                        </a>
                    </li>
                    <!-- START User avatar toggle-->
                    <li>
                        <!-- Button used to collapse the left sidebar. Only visible on tablet and desktops-->
                        <a href="admin">
                            <em class="icon-user"></em>
                        </a>
                    </li>
                    <!-- END User avatar toggle-->
                    <!-- START User logout-->
                    <li title="Logout">
                        <!-- Button used to collapse the left sidebar. Only visible on tablet and desktops-->
                        <a href="logout">
                            <em class="fa fa-sign-out fa-lg"></em>
                        </a>
                    </li>
                    <!-- END User logout-->
                </ul>
                <!-- END Left navbar-->
                <!-- START Right Navbar-->
                <ul class="nav navbar-nav navbar-right">
                    <!--Notifcation Settings-->
                    <li title="Notification Settings">
                        <a href="settings">
                            <em class="fa fa-gear fa-lg"></em>
                        </a>
                    </li>
                    <!-- Fullscreen (only desktops)-->
                    <li class="visible-lg" title="full Screen">
                        <a href="#" data-toggle-fullscreen="">
                            <em class="fa fa-expand"></em>
                        </a>
                    </li>
                    <!-- START Alert menu-->
                    <li class="dropdown dropdown-list">
                        <a href="#" data-toggle="dropdown">
                            <em class="icon-clock"></em>
                        </a>

                        <!-- list item-->
                        <div class="panel widget dropdown-menu animated flipInX">
                            <div class="col-xs-4 text-center bg-green pv-lg">
                                <!-- See formats: https://docs.angularjs.org/api/ng/filter/date-->
                                <div data-now="" data-format="MMMM" class="text-sm"></div>
                                <br>

                                <div data-now="" data-format="D" class="h2 mt0"></div>
                            </div>
                            <!-- last list item -->
                            <div class="col-xs-8 pv-lg">
                                <div data-now="" data-format="dddd" class="text-uppercase"></div>
                                <br>

                                <div data-now="" data-format="h:mm" class="h2 mt0"></div>
                                <div data-now="" data-format="a" class="text-muted text-sm"></div>
                            </div>
                        </div>
                        <!-- END Dropdown menu-->
                    </li>
                    <!-- END Alert menu-->
                    <!-- START Contacts button-->
                    <!-- END Contacts menu-->
                </ul>
                <!-- END Right Navbar-->
            </div>
            <!-- END Nav wrapper-->
        </nav>
    </header>
    <!-- sidebar-->
    <aside class="aside">
        <!-- START Sidebar (left)-->
        <div class="aside-inner">
            <nav data-sidebar-anyclick-close="" class="sidebar">
                <!-- START sidebar nav-->
                <ul class="nav">
                    <!-- START user info-->
                    <li class="has-user-block">
                        <div id="user-block" class="collapse">
                            <div class="item user-block">
                                <!-- User picture-->
                                <div class="user-block-picture">
                                    <div class="user-block-status">
                                        <img src="" alt="user" width="60" height="60"
                                             class="img-thumbnail img-circle">

                                        <div class="circle circle-success circle-lg"></div>
                                    </div>
                                </div>
                                <!-- Name and Job-->
                                <div class="user-block-info">
                                    <span class="user-block-name"></span>
                                    <span class="user-block-role"></span>
                                </div>
                            </div>
                        </div>
                    </li>
                    <!-- END user info-->
                    <!-- Iterates over all sidebar items-->
                    <li class=" ">
                        <a href="dashboard" title="Dashboard">
                            <em class="icon-speedometer"></em>
                            <span data-localize="">Dashboard</span>
                        </a>
                    </li>
                    <li class=" ">
                        <a href="rateSongs" title="Rate Songs">
                            <em class="icon-chemistry"></em>
                            <span data-localize="">Rate Songs</span>
                        </a>
                    </li>
                    <li class=" ">
                        <a href="test" title="test">
                            <em class="icon-note"></em>
                            <span>test</span>
                        </a>
                    </li>
                    <li class=" ">
                        <a href="topRatedMovies" title="topRatedMovies">
                            <em class="icon-note"></em>
                            <span>topRatedMovies</span>
                        </a>
                    </li>


                </ul>
                <!-- END sidebar nav-->
            </nav>
        </div>
        <!-- END Sidebar (left)-->
    </aside>
    <!-- offsidebar-->
    <aside class="offsidebar">
        <!-- START Off Sidebar (right)-->
        <nav>
            <div role="tabpanel">
                <!-- Nav tabs-->
                <ul role="tablist" class="nav nav-tabs nav-justified">
                    <li role="presentation" class="active">
                        <a href="#app-settings" aria-controls="app-settings" role="tab" data-toggle="tab">
                            <em class="icon-equalizer fa-lg"></em>
                        </a>
                    </li>
                </ul>
                <!-- Tab panes-->
                <div class="tab-content">
                    <div id="app-settings" role="tabpanel" class="tab-pane fade in active">
                        <h3 class="text-center text-thin">Settings</h3>

                        <div class="p">
                            <h4 class="text-muted text-thin">Themes</h4>

                            <div class="table-grid mb">
                                <div class="col mb">
                                    <div class="setting-color">
                                        <label data-load-css="./resources/app/css/theme-a.css">
                                            <input type="radio" name="setting-theme" checked="checked">
                                            <span class="icon-check"></span>
                                    <span class="split">
                                       <span class="color bg-info"></span>
                                       <span class="color bg-info-light"></span>
                                    </span>
                                            <span class="color bg-white"></span>
                                        </label>
                                    </div>
                                </div>
                                <div class="col mb">
                                    <div class="setting-color">
                                        <label data-load-css="./resources/app/css/theme-b.css">
                                            <input type="radio" name="setting-theme">
                                            <span class="icon-check"></span>
                                    <span class="split">
                                       <span class="color bg-green"></span>
                                       <span class="color bg-green-light"></span>
                                    </span>
                                            <span class="color bg-white"></span>
                                        </label>
                                    </div>
                                </div>
                                <div class="col mb">
                                    <div class="setting-color">
                                        <label data-load-css="./resources/app/css/theme-c.css">
                                            <input type="radio" name="setting-theme">
                                            <span class="icon-check"></span>
                                    <span class="split">
                                       <span class="color bg-purple"></span>
                                       <span class="color bg-purple-light"></span>
                                    </span>
                                            <span class="color bg-white"></span>
                                        </label>
                                    </div>
                                </div>
                                <div class="col mb">
                                    <div class="setting-color">
                                        <label data-load-css="./resources/app/css/theme-d.css">
                                            <input type="radio" name="setting-theme">
                                            <span class="icon-check"></span>
                                    <span class="split">
                                       <span class="color bg-danger"></span>
                                       <span class="color bg-danger-light"></span>
                                    </span>
                                            <span class="color bg-white"></span>
                                        </label>
                                    </div>
                                </div>
                            </div>
                            <div class="table-grid mb">
                                <div class="col mb">
                                    <div class="setting-color">
                                        <label data-load-css="./resources/app/css/theme-e.css">
                                            <input type="radio" name="setting-theme">
                                            <span class="icon-check"></span>
                                    <span class="split">
                                       <span class="color bg-info-dark"></span>
                                       <span class="color bg-info"></span>
                                    </span>
                                            <span class="color bg-gray-dark"></span>
                                        </label>
                                    </div>
                                </div>
                                <div class="col mb">
                                    <div class="setting-color">
                                        <label data-load-css="./resources/app/css/theme-f.css">
                                            <input type="radio" name="setting-theme">
                                            <span class="icon-check"></span>
                                    <span class="split">
                                       <span class="color bg-green-dark"></span>
                                       <span class="color bg-green"></span>
                                    </span>
                                            <span class="color bg-gray-dark"></span>
                                        </label>
                                    </div>
                                </div>
                                <div class="col mb">
                                    <div class="setting-color">
                                        <label data-load-css="./resources/app/css/theme-g.css">
                                            <input type="radio" name="setting-theme">
                                            <span class="icon-check"></span>
                                    <span class="split">
                                       <span class="color bg-purple-dark"></span>
                                       <span class="color bg-purple"></span>
                                    </span>
                                            <span class="color bg-gray-dark"></span>
                                        </label>
                                    </div>
                                </div>
                                <div class="col mb">
                                    <div class="setting-color">
                                        <label data-load-css="./resources/app/css/theme-h.css">
                                            <input type="radio" name="setting-theme">
                                            <span class="icon-check"></span>
                                    <span class="split">
                                       <span class="color bg-danger-dark"></span>
                                       <span class="color bg-danger"></span>
                                    </span>
                                            <span class="color bg-gray-dark"></span>
                                        </label>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="p">
                            <h4 class="text-muted text-thin">Layout</h4>

                            <div class="clearfix">
                                <p class="pull-left">Fixed</p>

                                <div class="pull-right">
                                    <label class="switch">
                                        <input id="chk-fixed" type="checkbox" data-toggle-state="layout-fixed">
                                        <span></span>
                                    </label>
                                </div>
                            </div>
                            <div class="clearfix">
                                <p class="pull-left">Boxed</p>

                                <div class="pull-right">
                                    <label class="switch">
                                        <input id="chk-boxed" type="checkbox" data-toggle-state="layout-boxed">
                                        <span></span>
                                    </label>
                                </div>
                            </div>
                            <div class="clearfix">
                                <p class="pull-left">RTL</p>

                                <div class="pull-right">
                                    <label class="switch">
                                        <input id="chk-rtl" type="checkbox">
                                        <span></span>
                                    </label>
                                </div>
                            </div>
                        </div>
                        <div class="p">
                            <h4 class="text-muted text-thin">Aside</h4>

                            <div class="clearfix">
                                <p class="pull-left">Collapsed</p>

                                <div class="pull-right">
                                    <label class="switch">
                                        <input id="chk-collapsed" type="checkbox" data-toggle-state="aside-collapsed">
                                        <span></span>
                                    </label>
                                </div>
                            </div>
                            <div class="clearfix">
                                <p class="pull-left">Float</p>

                                <div class="pull-right">
                                    <label class="switch">
                                        <input id="chk-float" type="checkbox" data-toggle-state="aside-float">
                                        <span></span>
                                    </label>
                                </div>
                            </div>
                            <div class="clearfix">
                                <p class="pull-left">Hover</p>

                                <div class="pull-right">
                                    <label class="switch">
                                        <input id="chk-hover" type="checkbox" data-toggle-state="aside-hover">
                                        <span></span>
                                    </label>
                                </div>
                            </div>
                        </div>
                    </div>

                </div>
            </div>
        </nav>
        <!-- END Off Sidebar (right)-->
    </aside>