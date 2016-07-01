<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Ghada
  Date: 6/29/16
  Time: 7:33 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

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
</script>
<div class="row">
    <div class="col-md-4">
        <h4>Upload Ontology</h4><br/>
<form:form commandName="uploadItem" name="frm" method="post"
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
        <form:form commandName="uploadItem" name="frm" method="post" action="/entities.html">
            <input type="submit" value="Show Entities" name="showEntities"/>
        </form:form>
        <br><br>
        <c:forEach items="${owlEntities}" var="each" varStatus="loop">
            <c:out value="${each}"/>
        </c:forEach>
    </div>
    </div>