<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: Ghada
  Date: 6/29/16
  Time: 7:33 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page language="java" session="true" %>

<!--Apply this function to validate owl, rdf files -->

<style>

    .rowFullWidth
    {
        margin-left: 0 !important;
        margin-right: 0 !important;
    }

    .button {
        background-color: #4CAF50; /* Green */
        border: none;
        color: #303030;
        padding: 15px 32px;
        text-align: center;
        text-decoration: none;
        display: inline-block;
        font-size: 13px;
        margin: 4px 2px;
        cursor: pointer;
        border-radius: 10px;
    }

    .button-compute{
        background-color: #949494; /* Green */
        padding: 6px 8px;

    }

    .button-upload{
        background-color: #949494; /* Green */
        padding: 6px 8px;

    }
    .button-upload:hover{
        background-color: #eeeeee; /* Green */
        padding: 6px 8px;
        color:#949494;

    }

    .button-compute:hover{
        background-color: #eeeeee; /* Green */
        padding: 6px 8px;
        color:#949494;
    }
</style>


<!-- Second ROW -->
<div class="row">
    <!-- signatures box -->
    <div class="col-md-3" style=""> <br/><br><br>
        <form:form commandName="uniformBackingObjects" name="frm1" method="post"
                   enctype="multipart/form-data">

                    <form:label for="fileData" path="fileData">Upload Ontology</form:label>
                    <form:input path="fileData" id="owlfile" type="file" /><br>
                    <input class="btn button button-upload" type="submit" value="Upload Ontology" name="upload"/>

        </form:form>
        <h4>Select Signature</h4>
        <form:form commandName="uniformBackingObjects" id="frm3" name="frm3" method="post" action="/uniformInterpolation.html">
            <form:errors path="*"/>
            <form:select path="selectedStr" id="selectedStr" name="selectedStr" class = "fileuploadContent" multiple="true"

                         style="border-radius: 5px; height:200px;width:300px;border:1px solid #ccc;font-size:16px; overflow:auto; background-color:#fff; margin-left: -13px;">

                <c:forEach items="${owlEntitiestems}" var="e">
                    <form:option value="${ss.shortForm(b,e)}">
                        ${ss.shortForm(b,e)}
                    </form:option>

                </c:forEach>
            </form:select>
            <br>
            <br><h4>Forgetting Method </h4>
            <form:radiobutton path="forgettingMethod" value="alchTBox"/> ALCH TBoxes <br/>
            <form:radiobutton path="forgettingMethod" value="shqTbox"/> SHQ TBoxes <br/>
            <form:radiobutton path="forgettingMethod" value="alcAbox"/> ALC with ABoxes <br/> <br>

            <input type="hidden" name="selectedValue" value="0"/>
            <input class="btn button button-compute" type="submit" value="Compute Uniform Interpolant" name="processForm"/>
        </form:form>
    </div>

    <div class="col-md-4" style="margin-left:50px;">
        <br><br><br><h4>Resulted Ontology</h4>
        <div style="border-radius: 5px; height:400px;width:500px;border:1px solid #ccc;font:12px Courier New, Courier, monospace;overflow:auto; background-color:#fff">
            <c:out value="${resultedOntology}"/>
        </div>


        <br>
        <br>

<center>
        <a class="btn button button-compute" href="/download.do" >Save Resulted Ontology</a>
</center>

    </div>

<!-- Third ROW -->



    </div>