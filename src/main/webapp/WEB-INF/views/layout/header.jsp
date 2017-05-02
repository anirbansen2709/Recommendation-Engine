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
          <%--<a href="admin">--%>
            <%--<em class="icon-user"></em>--%>
          <%--</a>--%>
        </li>
        <!-- END User avatar toggle-->
        <%--<!-- START User logout-->--%>
        <%--<li title="Logout">--%>
          <%--<!-- Button used to collapse the left sidebar. Only visible on tablet and desktops-->--%>
          <%--<a href="userLogout">--%>
          <%--&lt;%&ndash;<a href="logout">&ndash;%&gt;--%>
            <%--<em class="fa fa-sign-out fa-lg"></em>--%>
          <%--</a>--%>
        <%--</li>--%>
        <%--<!-- END User logout-->--%>
      </ul>
      <!-- END Left navbar-->
      <!-- START Right Navbar-->
      <ul class="nav navbar-nav navbar-right">
        <!-- START User logout-->
        <li title="Logout">
          <!-- Button used to collapse the left sidebar. Only visible on tablet and desktops-->
          <a href="userLogout">
            <%--<a href="logout">--%>
            <em class="fa fa-sign-out fa-lg"></em>
          </a>
        </li>
        <!-- END User logout-->


        <%--<!--Notifcation Settings-->--%>
        <%--<li title="Notification Settings">--%>
          <%--<a href="settings">--%>
            <%--<em class="fa fa-gear fa-lg"></em>--%>
          <%--</a>--%>
        <%--</li>--%>
        <%--<!-- Fullscreen (only desktops)-->--%>
        <%--<li class="visible-lg" title="full Screen">--%>
          <%--<a href="#" data-toggle-fullscreen="">--%>
            <%--<em class="fa fa-expand"></em>--%>
          <%--</a>--%>
        <%--</li>--%>
        <%--<!-- START Alert menu-->--%>
        <%--<li class="dropdown dropdown-list">--%>
          <%--<a href="#" data-toggle="dropdown">--%>
            <%--<em class="icon-clock"></em>--%>
          <%--</a>--%>

          <%--<!-- list item-->--%>
          <%--<div class="panel widget dropdown-menu animated flipInX">--%>
            <%--<div class="col-xs-4 text-center bg-green pv-lg">--%>
              <%--<!-- See formats: https://docs.angularjs.org/api/ng/filter/date-->--%>
              <%--<div data-now="" data-format="MMMM" class="text-sm"></div>--%>
              <%--<br>--%>

              <%--<div data-now="" data-format="D" class="h2 mt0"></div>--%>
            <%--</div>--%>
            <%--<!-- last list item -->--%>
            <%--<div class="col-xs-8 pv-lg">--%>
              <%--<div data-now="" data-format="dddd" class="text-uppercase"></div>--%>
              <%--<br>--%>

              <%--<div data-now="" data-format="h:mm" class="h2 mt0"></div>--%>
              <%--<div data-now="" data-format="a" class="text-muted text-sm"></div>--%>
            <%--</div>--%>
          <%--</div>--%>
          <%--<!-- END Dropdown menu-->--%>
        <%--</li>--%>
        <%--<!-- END Alert menu-->--%>
        <%--<!-- START Contacts button-->--%>
        <%--<!-- END Contacts menu-->--%>
      </ul>
      <!-- END Right Navbar-->
    </div>
    <!-- END Nav wrapper-->
  </nav>
</header>

