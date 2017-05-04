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
        <li title="Current User">
          <div id="userName" style="font-size: x-large; margin-top: 5%;color: white;"></div>
        </li>
        <li title="Logout">
          <!-- Button used to collapse the left sidebar. Only visible on tablet and desktops-->
          <a href="userLogout">
            <%--<a href="logout">--%>
            <em class="fa fa-sign-out fa-lg"></em>
          </a>
        </li>
        <!-- END User logout-->

      </ul>
      <!-- END Right Navbar-->
    </div>
    <!-- END Nav wrapper-->
  </nav>
</header>

