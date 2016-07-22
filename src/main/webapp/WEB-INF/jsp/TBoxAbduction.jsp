<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: ghadahalghamdi
  Date: 10/07/2016
  Time: 23:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script>
    var sel = document.getElementById('select_id');
    sel.addEventListener('click', function(el){
        var options = this.children;
        for(var i=0; i < this.childElementCount; i++){
            options[i].style.color = 'white';
        }
        var selected = this.children[this.selectedIndex];
        selected.style.color = 'red';
    }, false);
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

    option:checked, option:hover {
        color: white;
        background: #949494 repeat url("data:image/gif;base64,R0lGO...");
    }

    option[selected] { background: #f00; }
</style>
<div class="row">
    <div class="col-md-3" style=""> <br/><br><br>
        <form:form commandName="abductionBackingObjects" name="frm1" method="post"
                   enctype="multipart/form-data" onSubmit="return Validate();">

            <form:label for="fileData" path="fileData">Upload Ontology</form:label>
            <form:input path="fileData" id="owlfile" type="file" /><br>
            <input class="btn button button-upload" type="submit" value="Upload Ontology" name="upload"/>

        </form:form>
<h4>Select Signature</h4>
<form:form commandName="abductionBackingObjects" id="frm3" name="frm3" method="post" action="/tboxAbduction.html">
    <form:errors path="*"/>
    <form:select path="selectedSig" id="selectedSig" name="selectedSig" class = "fileuploadContent" multiple="true"

                 style="border-radius: 5px; height:200px;width:250px;border:1px solid #ccc;font-size:16px; overflow:auto; background-color:#fff; margin-left: -13px;">

        <c:forEach items="${owlEntitiestems}" var="e">
            <form:option value="${ss.shortForm(b,e)}">
                ${ss.shortForm(b,e)}
            </form:option>

        </c:forEach>
    </form:select>

    <br>
    </div>
    <div class="col-md-6"><br/><br><br><br/><br><br><br/><br>
        <h4>Select Axioms</h4>

            <form:errors path="*"/>
            <form:select path="selectedAx" id="selectedAx" name="selectedAx" class = "fileuploadContent" multiple="true"

                         style=" border-radius: 5px; height:200px;width:780px;border:1px solid #ccc;font-size:16px; background-color:#fff; margin-left: -13px;">

                <c:forEach items="${axioms}" var="e">
                    <form:option id = "select_id" cssStyle="white-space: pre-wrap;" value="${e}">
                        <c:out value="${e}"/>
                    </form:option>

                </c:forEach>
            </form:select>
        <input class="btn button button-compute" type="submit" value="Compute TBox Abduction" name="processForm"/>
        </form:form>

        <br>
    </div>

    </div>
<div class="row">

    <div class="col-md-6" style="margin-left: -10px;" >
        <h4>Resulted Ontology</h4>
        <div style="border-radius: 5px; height:400px;width:750px;border:1px solid #ccc;font:12px Courier New, Courier, monospace;overflow:auto; background-color:#fff">
            <c:out value="${explanations}"/>
        </div>
        <p><c:out value="${ontology}"/> </p>
      
        <br>
        <br>

        <center>
            <a class="btn button button-compute" href="/download.do" >Save Resulted Explination</a>
        </center>

    </div>

</div>
    <div class="row">
    </div>

