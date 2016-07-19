<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: ghadahalghamdi
  Date: 12/07/2016
  Time: 16:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<style>
    body {
        padding-top: 40px;
        padding-bottom: 40px;
        background-color: #eee;
    }

    .form-signin {
        max-width: 330px;
        padding: 15px;
        margin: 0 auto;
    }

    .badge {
        display: table-cell;
        text-align: center;
        vertical-align: middle;
        border-radius: 50%;

    }
    .badge-size1 {
         height: 50px;
         width: 50px;

        font: italic 16px Muli, sans-serif;
        background: darkgray;
     }
    .badge-size2 {
        height: 40px;
        width: 40px;
        font: italic 16px Muli, sans-serif;
        background: darkblue;
    }
    .badge-size3 {
        height: 30px;
        width: 30px;
        font: italic 16px Muli, sans-serif;
        background: orange;
    }

    .button {
        border: none;
        color: #ffffff;
        padding: 15px 32px;
        text-align: center;
        text-decoration: none;
        display: inline-block;
        cursor: pointer;
        border-radius: 10px;
        margin: 10px;
        font: italic 14px Muli, sans-serif;
    }

    .button-uniform{
        background-color: #303030;
        padding: 10px 15px;
    }

    .button-logical{
        background-color: #303030;
        padding: 10px 9px;
    }

    .button-abduction{
        background-color: #303030;
        padding: 10px 9px;
    }

    .button:hover{
        text-decoration: none;
        color:#949494;
    }
</style>
<div class="container">
    <h3 style="font-style: italic">Welcome to LETHE Web Analyser</h3>
    <form class="form-signin">

        <h4 class="form-signin-heading">Please select a service</h4>
        <div>
            <a class ="button button-uniform" href="<spring:url value="/uniformInterpolation.html"/>">Uniform Interpolation</a>
        </div>
        <div>

             <a class ="button button-logical" href="<spring:url value="/logicalDifferences.html"/>">Logical Differences</a>
        </div>
        <div>
            <a class ="button button-abduction" href="<spring:url value="/tboxAbduction.html"/>">TBox Abduction</a>
        </div>
    </form>

</div> <!-- /container -->
