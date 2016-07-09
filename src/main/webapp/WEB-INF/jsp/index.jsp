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
<%
    String selectValue = request.getParameter("selectedStr");
    if (selectValue != null) {
        session.setAttribute("savedStr", request.getParameter("selectedStr"));
    }
%>
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
<div class="row">
    <div class="col-md-4">
        <h4>Upload Ontology</h4><br/>
<form:form commandName="uploadItem" name="frm1" method="post"
           enctype="multipart/form-data" onSubmit="return Validate();">
    <fieldset><legend>Upload File</legend>
        <table>
            <tr>
                <td><form:label for="fileData" path="fileData">File</form:label><br />
                </td>
                <td><form:input path="fileData" id="owlfile" type="file" /></td>
            </tr>
            <tr>
                <td><br />
                </td>
                <td><input type="submit" value="Upload" name="upload"/></td>
            </tr>
        </table>
    </fieldset>
</form:form>
        <br>
        <br>

        <h3>Uploaded File</h3>
       <c:out value="${uploadFile}"/>
        </div>
    <div class="col-md-4">
        <form:form commandName="uploadItem" name="frm2" method="post" action="/entities.html">
            <input type="submit" value="Show Entities" name="showEntities"/>
        </form:form>
        <br><br>

    </div>

    <div class="col-md-4">
        <h4>Select Signature</h4><br/>
        <form:form commandName="uploadItem" id="frm3" name="frm3" method="post" action="/selectedEntities.html">
            <form:errors path="*"/>
            <form:select path="selectedStr" id="selectedStr" name="selectedStr" class = "fileuploadContent" multiple="true"

                         style="height:200px;width:170px;border:1px solid #ccc;font:16px/26px Georgia, Garamond, Serif;overflow:auto; background-color:#fff">

                <c:forEach items="${owlEntitiestems}" var="e">
                    <form:option value="${ss.shortForm(b,e)}">
                        ${ss.shortForm(b,e)}
                    </form:option>

                </c:forEach>
            </form:select>
            <input type="hidden" name="selectedValue" value="0"/>
            <input type="submit" value="Pass Entities" name="selectEntities"/>
        </form:form>


        <br><br>

        <p>--------------------------------</p>
        <c:forEach items="${savedStr}" var="each">
            <p>${each}</p>
        </c:forEach>
        </div>

<div class="row">
    <div class="col-md-8">

    </div>
</div>
    </div>