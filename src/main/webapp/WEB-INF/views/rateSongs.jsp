<%--
  Created by IntelliJ IDEA.
  User: Anirban
  Date: 07-Mar-17
  Time: 5:26 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ include file="./uiFrame.jsp" %>

<style>
    .content-wrapper > h3, /* .content-wrapper > .content-heading*/
    {
        margin-bottom: 4px;
        padding: 4px;
    }

    .wrapper > section {
        top: -20px;
    }

    .user-rating {
        direction: rtl;
        font-size: 20px;
        unicode-bidi: bidi-override;
        padding: 10px 30px;
        display: inline-block;
    }

    .user-rating input {
        opacity: 0;
        position: relative;
        left: -15px;
        z-index: 2;
        cursor: pointer;
    }

    .user-rating span.star:before {
        color: #777777;
        content: "ï€†";
        /*padding-right: 5px;*/
    }

    .user-rating span.star {
        display: inline-block;
        font-family: FontAwesome;
        font-style: normal;
        font-weight: normal;
        position: relative;
        z-index: 1;
    }

    .user-rating span {
        margin-left: -15px;
    }

    .user-rating span.star:before {
        color: #777777;
        content: "\f006";
        /*padding-right: 5px;*/
    }

    .user-rating input:hover + span.star:before, .user-rating input:hover + span.star ~ span.star:before, .user-rating input:checked + span.star:before, .user-rating input:checked + span.star ~ span.star:before {
        color: #ffd100;
        content: "\f005";
    }

    .selected-rating {
        color: #ffd100;
        font-weight: bold;
        font-size: 3em;
    }
</style>
<section>

    <div class="content-wrapper">
        <%--<h3>Device management--%>
            <%--<small>Add new SNMP devices</small>--%>
        <%--</h3>--%>
        <!-- START panel-->
        <div class="row">
            <div class="panel panel-default">
                <div class="panel-heading" style="background-color: #003153 !important; color: white !important;"><b>Rate Songs</b></div>
                <div class="panel-body">
                    <form role="form" class="form-inline" id="formid">
                        <div class="fixedscroll">
                            <table class="table table-bordered table-striped table-hover" id="songs_table">
                                <thead>
                                <tr>
                                    <th style="width: 5%; text-align: center; background-color: #007BA7;">Movie Id</th>
                                    <th style="text-align: center;background-color: #007BA7; color: white">Movie Name</th>
                                    <th style="text-align: center;background-color: #007BA7; color: white">Avg Rate</th>
                                    <th style="text-align: center;background-color: #007BA7; color: white">Rate</th>
                                </tr>
                                </thead>
                                <tbody style="cursor: pointer"></tbody>
                            </table>
                            <button type="button" class="btn btn-info btn-lg pull-right"
                                    id="saveRatings">Save
                            </button>
                        </div>
                    </form>
                </div>
                </div>
            </div>
        </div>
    </div>
</section>


<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<%@ include file="./uiFrameFooter.jsp" %>

<div class="modal fade" id="myModal" role="dialog" role="dialog" aria-labelledby="myModalLabel"
     aria-hidden="true"
     data-backdrop="static" data-keyboard="false">
    <div class="modal-dialog">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <%--<button type="button">&times;</button>--%>
                <h4 id="modalHeaderContent"></h4>
            </div>
            <div class="modal-body">
                <p>
                    <%--<div id = "modalText" >--%>
                    <span id="modalBodyContent"></span>
                    <%--</div>--%>
                <span id="modalBodyContent"></span>

                </p>

            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" id="error-close" data-dismiss="modal">Close</button>
                <button type="button" class="btn btn-primary" id="success-close">Close. Rate Again</button>
                <button type="button" class="btn btn-primary" id="redirect-btn">Ok. Go to Dashboard</button>
            </div>
        </div>

    </div>
</div>

<div class="modal fade" id="loadingModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-body">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                Loading. Please Wait...
                <div class="progress progress-striped active">
                    <div class="progress-bar" role="progressbar" aria-valuenow="100" aria-valuemin="0"
                         aria-valuemax="100" style="width: 100%">
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<script src="./resources/app/js/rateSongs.js"></script>
