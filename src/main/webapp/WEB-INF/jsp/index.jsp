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
<form:form modelAttribute="uploadItem" name="frm" method="post"
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
                <td><input type="submit" value="Upload" /></td>
            </tr>
        </table>
    </fieldset>
</form:form>
        <br>
        <br>
        <%
            if (session.getAttribute("uploadFile") != null
                    && !(session.getAttribute("uploadFile")).equals("")) {
        %>
        <h3>Uploaded File</h3>
        <br>
        <img
                src="<%=request.getSession().getServletContext().getRealPath("/") + "/upload/"
						+ session.getAttribute("uploadFile")%>"
                alt="Upload Image" />
        <%
                session.removeAttribute("uploadFile");
            }
        %>
        </div>
    </div>