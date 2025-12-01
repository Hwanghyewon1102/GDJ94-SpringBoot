<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<c:import url="/WEB-INF/views/template/head.jsp"></c:import>
<link href="https://cdn.jsdelivr.net/npm/summernote@0.9.0/dist/summernote-bs4.min.css" rel="stylesheet">
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
                        <h1 class="h3 mb-0 text-gray-800">상품</h1>
                        <a href="#" class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm"><i
                                class="fas fa-download fa-sm text-white-50"></i> Generate Report</a>
                    </div>
                    
                    <!-- Content Row -->
                    <div class="row">                    
	                    <!-- 생성한 contents 작성 -->
	                    <form method="post">
	                    	<input type="hidden" name="productsNum"  value="${dto.productsNum}"> 
						  <div class="form-group">
						    <label for="productsName" class="form-label">상품이름</label>
						    <input type="text" class="form-control" value="${dto.productsName}" id="productsName" name="productsName">
						  </div>
						  <div class="form-group">
							<label for="productsContents" class="form-label">상품설명</label>
							<textarea class="form-control" id="productsContents"  rows="3" name="productsContents"> ${dto.productsContents} </textarea>
						  </div>
						  <div class="form-group">
						    <label for="productsCategory" class="form-label">상품분류</label>
						    <input type="text" class="form-control" value="${dto.productsCategory}" id="productsCategory" name="productsCategory">
						  </div>
						  <div class="form-group">
						    <label for="productsRate" class="form-label">이자율</label>
						    <input type="text" class="form-control" value="${dto.productsRate}" id="productsRate" name="productsRate">
						  </div>
						  <div class="form-group">
						    <label for="productsSale" class="form-label">판매여부</label>
						    <input type="text" class="form-control" value="${dto.productsSale}" id="productsSale" name="productsSale">
						  </div>
						  <button type="submit" class="btn btn-primary">완료</button>
						</form>
	                    
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
	
    <script src="https://cdn.jsdelivr.net/npm/summernote@0.9.0/dist/summernote-bs4.min.js"></script>
    <script type="text/javascript">
    	$("#productsContents").summernote()
    </script>
</body>
</html>