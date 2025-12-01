<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<c:import url="/WEB-INF/views/template/head.jsp"></c:import>
	
</head>
<body id="page-top">
	<div id="wrapper">
		<!-- side bar -->
		<c:import url="/WEB-INF/views/template/sidebar.jsp"></c:import>
		<!-- side bar -->
		
		<!-- Content Wrapper -->
        <div id="content-wrapper" class="d-flex flex-column">
        
 	        <!-- Main Content -->
            <div id="content">
            
            	<!-- topbar -->
            	<c:import url="/WEB-INF/views/template/topbar.jsp"></c:import>
            	<!-- topbar -->
            	
            	<!-- Begin Page Content -->
            	<div class="container-fluid">
            	
            		<!-- Page Heading -->
                    <div class="d-sm-flex align-items-center justify-content-between mb-4">
                        <h1 class="h3 mb-0 text-gray-800">	 Detail</h1>
                        <a href="#" class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm"><i
                                class="fas fa-download fa-sm text-white-50"></i>상세보기</a>
                    </div>
                    
                    <!-- Content Row -->
                    <div class="row justify-content-center">
				        <div class="col-lg-10">
				            <div class="card shadow mb-4">
				                <div class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
				                    <h6 class="m-0 font-weight-bold text-primary">
				                        ${detail.productsNum}. ${detail.productsName}
				                    </h6>
				                    <div class="dropdown no-arrow">
				                       <span class="small text-gray-500 mr-3">이자: ${detail.productsRate}</span>
				                       <span class="small text-gray-500">판매여부:
				                       	<c:choose>
								            <c:when test="${detail.productsSale eq 0}">
								                O
								            </c:when>
								            <c:otherwise>
								                X
								            </c:otherwise>
								        </c:choose>
				                       </span>
				                    </div>
				                </div>
				                <div class="card-body">
				                    <div class="mb-3">
				                        <label class="small font-weight-bold text-gray-800">상품이름</label>
				                        <span class="ml-2">${detail.productsName}</span>
				                    </div>
				                    
				                    <hr> <div class="mb-5 mt-4" style="min-height: 200px;">
				                        ${detail.productsContents}
				                    </div>
				
				                </div>
				                
				                
				                <div class="card-footer text-center">
				                    <a href="./update?productsNum=${detail.productsNum}" class="btn btn-warning btn-icon-split">
				                        <span class="text">수정</span>
				                    </a>
				                    
				                   <form action="./delete" method="post" class="d-inline ml-2">
								        <input type="hidden" name="productsNum" value="${detail.productsNum}">
								        
								        <button type="submit" class="btn btn-danger btn-icon-split">
								            <span class="text">DEELTE</span>
								        </button>
								    </form>
				                    
				                    
				                    <a href="./list" class="btn btn-secondary btn-icon-split ml-2">
				                        <span class="text">목록</span>
				                    </a>
				                </div>
				            </div>
				        </div>
				    </div>
            	<!-- container-fulid -->
            </div>
        	<!-- end mian -->
        	
        	<!-- Footer -->
            <footer class="sticky-footer bg-white">
                <div class="container my-auto">
                    <div class="copyright text-center my-auto">
                        <span>Copyright &copy; Your Website 2021</span>
                    </div>
                </div>
            </footer>
            <!-- End of Footer -->
        </div>
        
        
	</div>
	
	<c:import url="/WEB-INF/views/template/foot.jsp"></c:import>
	
</body>
</html>