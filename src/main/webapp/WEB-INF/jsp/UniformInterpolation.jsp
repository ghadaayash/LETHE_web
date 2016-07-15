<%@ page import="org.semanticweb.owlapi.model.OWLEntity" %>
<%@ page import="org.openrdf.model.vocabulary.OWL" %>
<%@ page import="org.semanticweb.owlapi.model.OWLOntology" %>
<%@ page import="org.semanticweb.owlapi.util.ShortFormProvider" %>
<%@ page import="org.semanticweb.owlapi.util.SimpleShortFormProvider" %>
<%@ page import="org.semanticweb.owlapi.util.BidirectionalShortFormProviderAdapter" %>
<%@ page import="java.util.Set" %>
<%@ page import="java.util.HashSet" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags/form" %>
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
<script language="JavaScript">
    function Validate()
    {
        var owlfile =document.getElementById("owlfile").value;
        if(owlfile!=''){
            var checkowl = owlfile.toLowerCase();
            if (!checkowl.match(/(\.owl|\.rdf)$/)){
                alert("Please enter  OWL File Extensions .owl,.rdf");
                document.getElementById("owlfile").focus();
                return false;
            }
        }
        return true;
    }

    function SubmitValue(){
// This Function provides you to assign the selectedindex to a form variable which
//would come as request value at time of reloading
        document.frm3.selectedValue.value=document.frm3.selectedStr.selectedIndex;
        //alert(document.f1.slvalue.options[document.f1.selectedValue.value].value);
        //alert("rav");
        document.frm3.submit();
    }

    function retainValues(){

        var value1 =<%=request.getParameter("selectedValue")%>;
        if(value1!=null)
        {document.frm3.selectedStr.selectedIndex=value1;
            //alert(value1);
        }

    }
</script>
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
        padding: 10px 15px;

    }

    .button-upload{
        background-color: #949494; /* Green */
        padding: 10px 9px;

    }
    .button-upload:hover{
        background-color: #949494; /* Green */
        color:white;

    }

    .button-compute:hover{
        background-color: #949494; /* Green */
        padding: 10px 15px;
        color:white;
    }
</style>


<!-- Second ROW -->
<div class="row">
    <!-- signatures box -->
    <div class="col-md-3" style=""> <br/><br><br>
        <form:form commandName="uniformBackingObjects" name="frm1" method="post"
                   enctype="multipart/form-data" onSubmit="return Validate();">
            <h4>Upload Ontology</h4>
            <table>
                <tr>
                    <td><form:label for="fileData" path="fileData">File</form:label><br />
                    </td>
                    <td><form:input path="fileData" id="owlfile" type="file" /></td>
                </tr>
                <tr>
                    <td><br />
                    </td>
                    <td><center><input class="btn button button-upload" type="submit" value="Upload Ontology" name="upload"/></center></td>
                </tr>
            </table>

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

<center>
        <a class="btn button button-compute" href="/downloadInterpolant.do" >Save Resulted Ontology</a>
</center>
    </div>

<!-- Third ROW -->



    </div>