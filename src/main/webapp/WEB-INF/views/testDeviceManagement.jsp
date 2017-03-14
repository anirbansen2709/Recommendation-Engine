<%--
  Created by IntelliJ IDEA.
  User: Anirban
  Date: 2/22/2016
  Time: 9:52 AM
--%>
<%--import of side bar , nav bar and top bar--%>
<%@ include file="./uiFrame.jsp" %>
<%--<link rel="stylesheet" type="text/css" href="./resources/css/bootstrap.min.css"/>--%>
<%--<link rel="stylesheet" type="text/css" href="./resources/css/bootstrap-rtl.css"/>--%>
<%--<script type="text/javascript" src="./resources/js/jquery-1.12.0.min.js"></script>--%>
<!-- Main section-->
<style>
  .content-wrapper > h3,/* .content-wrapper > .content-heading*/ {
    margin-bottom: 4px;
    padding: 4px;
  }
  .wrapper > section{
    top: -20px;
  }
</style>

<section>

  <div class="content-wrapper">
    <h3>Device management
      <small>Add new SNMP devices</small>
    </h3>
    <!-- START panel-->
    <div class="row">
      <div class="panel panel-default">
        <div class="panel-heading">Device Management</div>
        <div class="panel-body">
          <form role="form" class="form-inline" id="formid">

            <div class="fixedscroll">
              <table class="table table-hover context-menu-wtn" id="users_table" class="display">
                <thead>
                <tr>
                  <th>Id</th>
                  <th>First_name</th>
                  <th>Last_name</th>
                  <th>Username</th>
                  <th>Password</th>
                  <th>Valid</th>
                  <th>Activated</th>
                  <th>Email</th>


                  <th></th>
                </tr>
                </thead>
              </table>
            </div>



          </form>
        </div>
      </div>
    </div>

    <!-- END panel-->
    <div class="row">
      <div class="panel panel-default">


    </div>
  </div>
</section>

<%--this will import footer and all the commom js--%>
<%@ include file="./uiFrameFooter.jsp" %>
<%--models--%>

<script type="text/javascript" src="/resources/app/js/testListAllUsers.js">


  //  device management button functions


</script>
<!-- FILESTYLE-->
<script src="./resources/vendor/bootstrap-filestyle/src/bootstrap-filestyle.js"></script>
<script type="text/javascript" src="./resources/js/bootstrap.min.js"></script>
<!-- CLASSY LOADER-->
<script src="./resources/vendor/jquery-classyloader/js/jquery.classyloader.min.js"></script>

<%--<!-- DataTables -->--%>
<%--<script src="/resources/datatables/datatables/jquery.dataTables.js"></script>--%>
<%--<script src="/resources/datatables/datatables/dataTables.colVis.min.js"></script>--%>
<%--<script src="/resources/datatables/datatables/dataTables.tableTools.min.js"></script>--%>
<%--<script src="/resources/datatables/datatables/dataTables.bootstrap.min.js"></script>--%>
<%--<script src="/resources/datatable-responsive/datatable-responsive/datatables.responsive.min.js"></script>--%>


<script src="http://code.jquery.com/jquery-1.10.2.min.js"></script>
<script src="http://cdn.datatables.net/1.10.0/js/jquery.dataTables.js"></script>
<script src="http://cdn.datatables.net/plug-ins/e9421181788/integration/bootstrap/3/dataTables.bootstrap.js"></script>
<script src="http://netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css"></script>
<script src="http://cdn.datatables.net/fixedheader/2.1.1/js/dataTables.fixedHeader.min.js"></script>
<script src="http://cdn.datatables.net/plug-ins/e9421181788/integration/bootstrap/3/dataTables.bootstrap.css"></script>
<script src="http://cdn.datatables.net/fixedheader/2.1.1/css/dataTables.fixedHeader.css"></script>



</html>
