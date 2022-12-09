<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- Sidebar -->
<ul class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion" id="accordionSidebar">

    <!-- Sidebar - Brand -->
    <a class="sidebar-brand d-flex align-items-center justify-content-center" href="/premium/admin/main.do">
        <div>
            <img src="/imgs/common/logo_img.png" style="width: 50px;">
        </div>
<!--  <sup>2</sup> -->
        <div class="sidebar-brand-text mx-3" style="padding-top: 5px;">Smartooth</div>
    </a>

    <!-- Divider -->
    <hr class="sidebar-divider my-0"/>

    <!-- Nav Item - Dashboard -->
    <li class="nav-item active">
        <a class="nav-link" href='/premium/admin/main.do' ">
            <i class="fas fa-fw fa-tachometer-alt"></i>
            <span>Dashboard</span></a>
    </li>

    <!-- Divider -->
    <hr class="sidebar-divider">

    <!-- Heading -->
    <div class="sidebar-heading">
<!--         Interface -->
    </div>

    <!-- Nav Item - Pages Collapse Menu -->
<!--     <li class="nav-item"> -->
<!--         <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseTwo" -->
<!--             aria-expanded="true" aria-controls="collapseTwo"> -->
<!--             <i class="fas fa-fw fa-cog"></i> -->
<!--             <span>Student List</span> -->
<!--         </a> -->
<!--         <div id="collapseTwo" class="collapse" aria-labelledby="headingTwo" data-parent="#accordionSidebar"> -->
<!--             <div class="bg-white py-2 collapse-inner rounded"> -->
<!--                 <h6 class="collapse-header">Custom Components:</h6> -->
<!--                 <a class="collapse-item" href="buttons.html">Buttons</a> -->
<!--                 <a class="collapse-item" href="cards.html">Cards</a> -->
<!--             </div> -->
<!--         </div> -->
<!--     </li> -->
	<!-- Nav Item - Tables -->
<!--     <li class="nav-item"> -->
<!--         <a class="nav-link" href="/web/user/selectStUserInfoList.do"> -->
<!--             <i class="fas fa-fw fa-table"></i> -->
<!--             <span>Student List</span></a> -->
<!--     </li> -->
    
    <li class="nav-item">
        <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseProducts"
            aria-expanded="true" aria-controls="collapseProducts">
            <i class="fas fa-fw fa-wrench"></i>
            <span>기관 관리</span>
        </a>
        <div id="collapseProducts" class="collapse" aria-labelledby="headingUtilities"
            data-parent="#accordionSidebar">
            <div class="bg-white py-2 collapse-inner rounded">
            <!-- 조회 등록 삭제-->
                
				<a class="collapse-item" href="/premium/admin/organ/selectOrganList?userId=${userId}">조회</a>
<%-- 				<a class="collapse-item" href="/premium/admin/organ/selectOrganList?userId=${userId}">기관 조회</a> --%>
<%-- <c:if test="${userInfo.userType eq 'SU'}"> --%>
<!--             	<a class="collapse-item" href="/premium/admin/registOrganInfo">등록</a> -->
<%-- </c:if> --%>
<!--                 <a class="collapse-item" href="utilities-border.html">Borders</a> -->
<!--                 <a class="collapse-item" href="utilities-animation.html">Animations</a> -->
<!--                 <a class="collapse-item" href="utilities-other.html">Other</a> -->
            </div>
        </div>
    </li>


    <li class="nav-item">
        <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseMachine" aria-expanded="true" aria-controls="collapseMachine">
            <i class="fas fa-fw fa-user"></i>
            <span>사용자 관리</span>
        </a>
        <div id="collapseMachine" class="collapse" aria-labelledby="headingUtilities" data-parent="#accordionSidebar">
            <div class="bg-white py-2 collapse-inner rounded">
                <a class="collapse-item" href="/premium/admin/user/selectUserList">사용자 조회</a>
<!--                 <a class="collapse-item" href="/web/product/selectProductMain.do"">사용자 등록</a> -->
<%--                 <c:if test="${userInfo.userType eq 'SU'}"> --%>
<!--                 	<a class="collapse-item" href="/premium/admin/user/selectAdminUserList"">관리자 조회</a> -->
<%--                 </c:if> --%>
<!--                 <a class="collapse-item" href="utilities-border.html">Borders</a> -->
<!--                 <a class="collapse-item" href="utilities-animation.html">Animations</a> -->
<!--                 <a class="collapse-item" href="utilities-other.html">Other</a> -->
            </div>
        </div>
    </li>
	
    <!-- Divider -->
    <hr class="sidebar-divider">


    <!-- Heading -->
    <div class="sidebar-heading">
        Function
    </div>


    <!-- Nav Item - Pages Collapse Menu -->
<!--     <li class="nav-item"> -->
<!--         <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapsePages" -->
<!--             aria-expanded="true" aria-controls="collapsePages"> -->
<!--             <i class="fas fa-fw fa-folder"></i> -->
<!--             <span>Pages</span> -->
<!--         </a> -->
<!--         <div id="collapsePages" class="collapse" aria-labelledby="headingPages" data-parent="#accordionSidebar"> -->
<!--             <div class="bg-white py-2 collapse-inner rounded"> -->
<!--                 <h6 class="collapse-header">Login Screens:</h6> -->
<!--                 <a class="collapse-item" href="login.html">Login</a> -->
<!--                 <a class="collapse-item" href="register.html">Register</a> -->
<!--                 <a class="collapse-item" href="forgot-password.html">Forgot Password</a> -->
<!--                 <div class="collapse-divider"></div> -->
<!--                 <h6 class="collapse-header">Other Pages:</h6> -->
<!--                 <a class="collapse-item" href="404.html">404 Page</a> -->
<!--                 <a class="collapse-item" href="blank.html">Blank Page</a> -->
<!--             </div> -->
<!--         </div> -->
<!--     </li> -->


<!--     Nav Item - Charts -->
<!-- 	<li class="nav-item"> -->
<!--         <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseDiagnosis" aria-expanded="true" aria-controls="#collapseDiagnosis"> -->
<!--             <i class="fas fa-fw fa-desktop"></i> -->
<!--             <span>치아 모니터링</span> -->
<!--         </a> -->
<!--         <div id="#collapseDiagnosis" class="collapse" aria-labelledby="headingUtilities" data-parent="#accordionSidebar"> -->
<!--             <div class="bg-white py-2 collapse-inner rounded"> -->
<!--                 <a class="collapse-item" href="/premium/statistics/selectUserMeasureList">진단지</a> -->
<!--                 <a class="collapse-item" href="/web/product/selectProductMain.do"">사용자 등록</a> -->
<%--                 <c:if test="${userInfo.userType eq 'SU'}"> --%>
<!--                 	<a class="collapse-item" href="/premium/admin/user/selectAdminUserList"">관리자 조회</a> -->
<%--                 </c:if> --%>
<!--                 <a class="collapse-item" href="utilities-border.html">Borders</a> -->
<!--                 <a class="collapse-item" href="utilities-animation.html">Animations</a> -->
<!--                 <a class="collapse-item" href="utilities-other.html">Other</a> -->
<!--             </div> -->
<!--         </div> -->
<!--     </li> -->
	
	
	<!-- Nav Item - Utilities Collapse Menu -->
    <li class="nav-item">
        <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseDiagnosis"
            aria-expanded="true" aria-controls="collapseDiagnosis">
            <i class="fas fa-fw fa-desktop"></i>
            <span>치아 모니터링</span>
        </a>
        <div id="collapseDiagnosis" class="collapse" aria-labelledby="headingUtilities" data-parent="#accordionSidebar">
            <div class="bg-white py-2 collapse-inner rounded">
                <a class="collapse-item" href="/premium/admin/statistics/selectUserMeasureList">진단지 결과</a>
<%--                  <c:if test="${userInfo.userType eq 'SU'}"> --%>
<!--                 	<a class="collapse-item" href="/premium/admin/user/selectAdminUserList""></a> -->
<%--                 </c:if> --%>
<!--                 <a class="collapse-item" href="utilities-border.html">Borders</a> -->
<!--                 <a class="collapse-item" href="utilities-animation.html">Animations</a> -->
<!--                 <a class="collapse-item" href="utilities-other.html">Other</a> -->
            </div>
        </div>
    </li>
	
	<c:if test="${userInfo.userType eq 'SU'}">	
	<li class="nav-item">
        <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseDiagnosisEdit"
            aria-expanded="true" aria-controls="collapseDiagnosisEdit">
            <i class="fas fa-fw fa-tooth"></i>
            <span>측정 결과 관리</span>
        </a>
        <div id="collapseDiagnosisEdit" class="collapse" aria-labelledby="headingUtilities" data-parent="#accordionSidebar">
            <div class="bg-white py-2 collapse-inner rounded">
<!--                 <a class="collapse-item" href="/premium/admin/statistics/updateUserDiagnosis">진단 결과 수정</a> -->
                <a class="collapse-item" href="#">진단 결과 수정</a>
	<!--                 	<a class="collapse-item" href="/premium/admin/user/selectAdminUserList""></a> -->
	<!--                 <a class="collapse-item" href="utilities-border.html">Borders</a> -->
	<!--                 <a class="collapse-item" href="utilities-animation.html">Animations</a> -->
	<!--                 <a class="collapse-item" href="utilities-other.html">Other</a> -->
            </div>
        </div>
	    </li>
 	</c:if>

    <!-- Nav Item - Utilities Collapse Menu -->
    <li class="nav-item">
        <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseUtilities"
            aria-expanded="true" aria-controls="collapseUtilities">
            <i class="fas fa-fw fa-wrench"></i>
            <span>Setting</span>
        </a>
        <div id="collapseUtilities" class="collapse" aria-labelledby="headingUtilities" data-parent="#accordionSidebar">
            <div class="bg-white py-2 collapse-inner rounded">
                <h6 class="collapse-header">Custom Utilities:</h6>
                <a class="collapse-item" href="utilities-color.html">Colors</a>
                <a class="collapse-item" href="utilities-border.html">Borders</a>
                <a class="collapse-item" href="utilities-animation.html">Animations</a>
                <a class="collapse-item" href="utilities-other.html">Other</a>
            </div>
        </div>
    </li>

    <!-- Divider -->
    <hr class="sidebar-divider d-none d-md-block">

    <!-- Sidebar Toggler (Sidebar) -->
    <div class="text-center d-none d-md-inline">
        <button class="rounded-circle border-0" id="sidebarToggle"></button>
    </div>

<!-- Sidebar Message -->
<!--     <div class="sidebar-card d-none d-lg-flex"> -->
<!--         <img class="sidebar-card-illustration mb-2" src="imgs/undraw_rocket.svg" alt="..."> -->
<!--         <p class="text-center mb-2"><strong>SB Admin Pro</strong> is packed with premium features, components, and more!</p> -->
<!--         <a class="btn btn-success btn-sm" href="https://startbootstrap.com/theme/sb-admin-pro">Upgrade to Pro!</a> -->
<!--     </div> -->

</ul>
<!-- End of Sidebar -->