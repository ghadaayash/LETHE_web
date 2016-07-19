<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: ghadahalghamdi
  Date: 10/07/2016
  Time: 23:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
        padding: 6px 8px;

    }

    .button-upload{
        background-color: #949494; /* Green */
        padding: 6px 8px;

    }
    .button-upload:hover{
        background-color: #eeeeee; /* Green */
        color:#949494;

    }

    .button-compute:hover{
        background-color: #eeeeee;
        padding: 6px 8px;
        color:#949494;
    }
</style>


<!-- Second ROW -->
<div class="row">
    <!-- signatures box -->
    <div class="col-md-3" style=""> <br/><br><br>
        <!-- upload first ontology-->
        <form:form commandName="logicalBackingObjects" name="frm1" method="post"
                   enctype="multipart/form-data" onSubmit="return Validate();">

            <!-- <p style="font-weight: bold">Upload first ontology</p>-->
            <form:label for="firstFileData" path="firstFileData">Upload first ontology</form:label><br />
            <form:input path="firstFileData" id="owlfile" type="file" />

            <!-- upload second ontology-->
           <!-- <p style="font-weight: bold">Upload second ontology</p>-->
            <form:label for="secondFileData" path="secondFileData">Upload second ontology</form:label><br />
            <form:input path="secondFileData" id="owlfile" type="file" /><br />

            <input class="btn button button-upload" type="submit" value="Upload Ontologies" name="Upload"/>

        </form:form>

        <form:form commandName="logicalBackingObjects" id="frm3" name="frm0" method="post" action="/logicalDifferences.html">

        <p style="font-weight: bold">Compute logical differences based on </p>
        <form:radiobutton path="logicalDifferencesOption" value="commonSig"/> Common Signatures <br/>
        <form:radiobutton path="logicalDifferencesOption" value="specificSig"/> Specified Signatures <br/>

        <br><p style="font-weight: bold">Get the signatures from </p>
        <form:radiobutton path="signaturesOption" value="firstOntologysig"/> First Ontology <br/>
        <form:radiobutton path="signaturesOption" value="secondOntologysig"/> Second Ontology <br/>

            <input class="btn button button-upload" type="submit" value="Submit" name="showSignatures"/>

        </form:form>



        <form:form commandName="logicalBackingObjects" id="frm3" name="frm3" method="post" action="/logicalDifferences.html">
            <h4>Select Signature</h4>
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
            <h4>Approximation Level <form:input path="approximationLevel"/></h4>
            <br><h4>Forgetting Method </h4>
            <form:radiobutton path="forgettingMethod" value="alchTBox"/> ALCH TBoxes <br/>
            <form:radiobutton path="forgettingMethod" value="shqTbox"/> SHQ TBoxes <br/>
            <form:radiobutton path="forgettingMethod" value="alcAbox"/> ALC with ABoxes <br/> <br>

            <input type="hidden" name="selectedValue" value="0"/>
            <input class="btn button button-compute" type="submit" value="Compute Logical Differences" name="processForm"/>
        </form:form>
    </div>

    <div class="col-md-4" style="margin-left:50px;">
        <br><br><br><h4>Resulted Axioms</h4>
        <div style="border-radius: 5px; height:400px;width:500px;border:1px solid #ccc;font:12px Courier New, Courier, monospace;overflow:auto; background-color:#fff">
            <c:out value="${resultedAxioms}"/>
        </div>

        <br>

        <center>
            <a class="btn button button-compute" href="/download.do" >Save Resulted Axioms</a>
        </center>
    </div>

    <!-- Third ROW -->



</div>