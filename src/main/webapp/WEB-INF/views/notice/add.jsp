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
                        <h1 class="h3 mb-0 text-gray-800">공지사항</h1>
                        <a href="#" class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm"><i
                                class="fas fa-download fa-sm text-white-50"></i> Generate Report</a>
                    </div>
                    
                    <!-- Content Row -->
                    <div class="row">                    
	                    <!-- 생성한 contents 작성 -->
	                    <form method="post">
						  <div class="form-group">
						    <label for="board_title" class="form-label">제목</label>
						    <input type="text" class="form-control" id="board_title" name="boardTitle">
						  </div>
						  <div class="form-group">
						    <label for="board_writer" class="form-label">작성자</label>
						    <input type="text" class="form-control" id="board_writer" name="boardWriter">
						  </div>
						  <div class="form-group">
							<label for="board_contents" class="form-label">내용</label>
							<textarea class="form-control" id="boardContents" rows="3" name="boardContents"></textarea>
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
    	$("#boardContents").summernote()
    </script>
</body>
</html>