<header class="topnavbar-wrapper">
  <!-- START Top Navbar-->
  <nav role="navigation" class="navbar topnavbar" style="background-color: #003153 !important; background-image: none !important;">
    <!-- START navbar header-->
    <div class="navbar-header">
      <a href="#/" class="navbar-brand">
        <div class="brand-logo" style="margin-top: -5%;">
          <img src="/resources/logo-image/logo1.png" alt="dexter" style="height: 100%;">
        </div>
        <div class="brand-logo-collapsed" style="margin-top: -10%;margin-left:-1%;">
          <img src="/resources/logo-image/icon-logo.png" alt="SNMP" style="height: 100%;">
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

