<!DOCTYPE html>
<html>
<@layout flag="owners">
    <#import "/spring.ftl" as spring/>
<head>


    <script type='text/javascript' src='http://code.jquery.com/jquery-1.11.0.js'></script>
    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.4.1/js/bootstrap-datepicker.min.js"></script>
    <script type="text/javascript" src="https://formden.com/static/cdn/formden.js"></script>
    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/clockpicker/0.0.7/bootstrap-clockpicker.js"></script>


    <link rel="stylesheet" href="https://formden.com/static/cdn/bootstrap-iso.css" />
    <link rel="stylesheet" href="https://formden.com/static/cdn/font-awesome/4.4.0/css/font-awesome.min.css" />
    <link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/clockpicker/0.0.7/bootstrap-clockpicker.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.4.1/css/bootstrap-datepicker3.css"/>
    <link rel="stylesheet"  type="text/css" href="/style.css" />

<style>
    h5 {color: red}
</style>
</head>

<body>
<div class="container">

    <h3 style="color: green; font-weight: bold; font-size: 20px;">${message!""}</h3>

    <form class="well form-horizontal" action="/admin/owners/new" method="post" id="ownerForm" name="ownerForm">

        <div class="form-group">
            <@spring.bind "ownerForm.firstName"/>
            <label class="col-md-4 control-label">First Name</label>
            <div class="col-md-4 selectContainer">
                <div class="col-md-12 input-group">
                    <input  name="firstName" id="firstName"  placeholder="First Name" class="form-control"  type="text">
                </div>
                <div class="col-sm-12">
                                    <small  class="text-danger">
                                        <#list spring.status.errorMessages as error><div>${error}</div></#list>
                                    </small>
                                </div>
            </div>
        </div>


        <div class="form-group">
            <@spring.bind "ownerForm.lastName"/>
            <label class="col-md-4 control-label">Last Name</label>
            <div class="col-md-4 inputGroupContainer">
                <div class="col-md-12 input-group">
                    <input  name="lastName" id="lastName"  placeholder="Last Name" class="form-control"  type="text">
                </div>
                <div class="col-sm-12">
                                    <small  class="text-danger">
                                        <#list spring.status.errorMessages as error><div>${error}</div></#list>
                                    </small>
                                </div>
            </div>
        </div>

        <div class="form-group">
            <@spring.bind "ownerForm.afm"/>
            <label class="col-md-4 control-label">Afm</label>
            <div class="col-md-4 inputGroupContainer">
                <div class="col-md-12 input-group">
                    <input  name="afm" id="afm" placeholder="afm"  class="form-control"  type="text">
                </div>
               <div class="col-sm-12">
                                   <small  class="text-danger">
                                       <#list spring.status.errorMessages as error><div>${error}</div></#list>
                                   </small>
                               </div>
            </div>
        </div>

        <div class="form-group">
            <@spring.bind "ownerForm.access"/>
            <label class="col-md-4 control-label">Access</label>
            <div class="col-md-4 inputGroupContainer">
                <div class="col-md-12 input-group ">
                    <input name="access" id="access" placeholder="Access"  class="form-control"  type="text">
                </div>
                <div class="col-sm-12">
                                    <small  class="text-danger">
                                        <#list spring.status.errorMessages as error><div>${error}</div></#list>
                                    </small>
                                </div>
            </div>
        </div>

        <div class="form-group">
            <@spring.bind "ownerForm.email"/>
            <label class="col-md-4 control-label">Email</label>
            <div class="col-md-4 inputGroupContainer">
                <div class="col-md-12 input-group">
                    <input name="email" id="email" placeholder="Email" class="form-control"  type="email">
                </div>
                <div class="col-sm-12">
                                    <small  class="text-danger">
                                        <#list spring.status.errorMessages as error><div>${error}</div></#list>
                                    </small>
                                </div>
            </div>
        </div>

        <div class="form-group">
            <@spring.bind "ownerForm.password"/>
            <label class="col-md-4 control-label">Password</label>
            <div class="col-md-4 inputGroupContainer">
                <div class="col-md-12 input-group ">
                    <input name="password" id="password" placeholder="Access"  class="form-control"  type="password">
                </div>
                <div class="col-sm-12">
                                    <small  class="text-danger">
                                        <#list spring.status.errorMessages as error><div>${error}</div></#list>
                                    </small>
                                </div>
            </div>
        </div>

        <div class="form-group">
            <label class="col-md-4 control-label"></label>
            <div class="col-md-4"><br>
                <button type="submit" class="btn pull-right btn-warning" id="btn">Add Owner</button>
            </div>
        </div>



    </form>

</div><!-- /.container -->
</@layout>
</body>
</html>




