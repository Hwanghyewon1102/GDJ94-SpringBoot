<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
                        <h1 class="h3 mb-0 text-gray-800">${category} ${sub}</h1>
                        <a href="#" class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm"><i
                                class="fas fa-download fa-sm text-white-50"></i> Generate Report</a>
                    </div>
                    
                    <!-- Content Row -->
                    <div class="row">                    
	                    <!-- 생성한 contents 작성 -->
	                    
	                    
<!-- form ------------------------------------------------------------------------------------------------------ -->
						<form:form modelAttribute="dto" method="post" enctype="multipart/form-data">
							<form:hidden path="boardNum"/>
						
						  <div class="form-group">
						    <label for="board_writer" class="form-label">작성자</label>
						    <%-- <form:input path="boardWriter" disabled cssClass="form-control" id="writer"/> --%>
						  </div>
						  <div class="form-group">
						    <label for="board_title" class="form-label">제목</label>
						    <form:input path="boardTitle" cssClass="form-control" id="title"/>
						    <form:errors path="boardTitle"></form:errors>
						  </div>
						  <div class="form-group">
							<label for="board_contents" class="form-label">내용</label>
						    <form:textarea path="boardContents" cssClass="form-control" id="contents"/>
						  </div>
						  <div class="form-group" id="files">
								<button type="button" id="fileAdd">File add</button>
								
						  </div>
							
							<div id="file">
							</div>
						  
						  <button type="submit" class="btn btn-primary">완료</button>
						</form:form>
	                    
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
	
		<script src="/js/board/board.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/summernote@0.9.0/dist/summernote-bs4.min.js"></script>
    <script type="text/javascript">
    	$("#boardContents").summernote()
    </script>
</body>
</html>