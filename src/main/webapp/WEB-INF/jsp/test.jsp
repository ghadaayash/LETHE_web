<%--
  Created by IntelliJ IDEA.
  User: ghadahalghamdi
  Date: 11/07/2016
  Time: 01:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<style>

    div {
        height:50px;
    }
    .short-div {
        height:25px;
    }
</style>



    <!-- first(2) row -->

    <div class="row-fluid">
        <div class="col-lg-5 col-md-5 col-sm-5 col-xs-5" style="background-color:red;">Span 5</div>
        <div class="col-lg-3 col-md-3 col-sm-3 col-xs-3" style="background-color:blue">Span 3</div>
        <div class="col-lg-2 col-md-2 col-sm-3 col-xs-2" style="padding:0px">
            <div class="short-div" style="background-color:green">Span 2</div>
            <div class="short-div" style="background-color:purple">Span 2</div>
        </div>
        <div class="col-lg-2 col-md-2 col-sm-3 col-xs-2" style="background-color:yellow">Span 2</div>
    </div>


    <div class="row-fluid">
        <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
            <div class="short-div" style="background-color:#999">Span 6</div>
            <div class="short-div">Span 6</div>
        </div>
        <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6" style="background-color:#ccc">Span 6</div>
    </div>
