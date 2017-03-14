<%--
  Created by IntelliJ IDEA.
  User: Rushil Mahaan
  Date: 2/22/2016
  Time: 9:48 AM
  To change this template use File | Settings | File Templates.
--%>
<%--import of side bar , nav bar and top bar--%>
<%@ include file="./uiFrame.jsp" %>
<!-- Main section-->
<section>
  <!-- Page content-->
  <div class="content-wrapper">
    <div class="content-heading">
      N – View (Network Monitoring Software)
    </div>
    <!-- START widgets box-->
    <div class="row">

      <div class="col-lg-12 col-sm-12">
        <h4>INTRODUCTION</h4>
        <p>In a network which implements SNMP (Simple Network Management Protocol), there are managers
          (they handle the task of monitoring or managing a group of hosts or devices) and managed devices (network nodes that
          implements an SNMP interface) in a computer network. The system that has musicRecommendation installed in it acts like a manager and
          uses SNMP4j api     to collect data in the form of variables (object identifiers =”values”) on the managed systems, which
          describe the system configuration. These hierarchies, and other metadata (such as type and description of the variable),
          are described by Management Information Bases (MIBs). To identify individual devices and collect information from them,
          the software requires their IP addresses and community names. The IP and community names of the respective devices are
          configured during the installation of SNMP service.
          The data collected is then used to conduct vivid tests (pendrive detection, data upload/download, storage size etc.)
          and plot different charts for a user specified time frame that helps the software user to know important details about
          the individual devices in the network. The software also allows it’s user to have a glimpse of the running processes,
          software resources, storage devices, interfaces, drivers and unique system details of the network nodes  in real time.
        </p>
         </div>
      <div class="col-lg-12 col-sm-12">
        <h4>PROBLEM DEFINITION</h4>
          <p>
          In any institution or organization where there are a group of computers connected  in a network, questions are raised on security, privacy and violation of ethics of the organization. SNMP protocol firmly answers these issues by providing adequate information about all the network  nodes.
          Our software musicRecommendation uses this set of information to detect devices that crosses an organization’s threshold on parameters illustrated by the SNMP protocol. The software also plots charts and extracts real time data about these parameters to aid the perspective.    </p>
         </div>
      <div class="col-lg-12 col-sm-12">
        <h4>METHODOLOGY</h4>
        <p>
          The search engine basically requires the following technologies-  <br>1. HTML and CSS for UI and styling.  <br> 2. JQuery for basic validation.   <br>3. Java for functionality implementation    <br>4. Database is created using MySQL.   <br>5. Spring MVC (Model View Controller) is the framework used that provides full control over HTML, CSS, and JavaScript.</p>
         </div>
      <div class="col-lg-12 col-sm-12">
        <h4>CONCLUSION</h4>
        <p>
          We have successfully implemented the idea of fetching data from the network Pc’s in real time. This is done by adding the device’s entry in the device management page of the software where there is a list of all the devices that has been manually added by providing their respective IP address and community name. Real time data of running processes, software resources, storage devices, interfaces, drivers and unique system details of each device can be seen in tabular format by clicking on the “show live” button of it’s entry in the list of all the devices. The entry of the device is also updated in MySql database whenever a new device is added or removed.
        </p>
      </div>
      <div class="col-lg-3 col-sm-6">

      </div>
    </div>
  </div>
</section>
<%--this will import footer and all the commom js--%>
<%@ include file="./uiFrameFooter.jsp" %>

</body>
</html>