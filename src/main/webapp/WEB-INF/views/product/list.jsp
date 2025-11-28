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
		<c:import url="/WEB-INF/views/template/sidebar.jsp"></c:import>
		<div id="content-wrapper" class="d-flex flex-column">
        
 	        <div id="content">
            
            	<c:import url="/WEB-INF/views/template/topbar.jsp"></c:import>
            	<div class="container-fluid">
            	
            		<div class="d-sm-flex align-items-center justify-content-between mb-4">
                        <h1 class="h3 mb-0 text-gray-800">${category}</h1>
                        <a href="#" class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm"><i
                                class="fas fa-download fa-sm text-white-50"></i> Generate Report</a>
                    </div>
                    
                    <div class="row justify-content-center">      
                    	
                    	<div class="col-sm-8">
                    	
	                    	<form action="./list" method="get">
			                    <div class="input-group mb-3">
				                    <select class="form-control" name="kind">
									  <option value="k1">제목</option>
									  <option value="k2">내용</option>
									  <option value="k3">작성자</option>
									</select>
								  <input type="text" class="form-control" name="search" value="${param.search}" placeholder="검색어를 입력하세요" aria-label="Recipient’s username" aria-describedby="button-addon2">
								  <button class="btn btn-outline-secondary" type="submit" id="button-addon2">검색</button>
								</div>
	                    	</form>
	                    	
		                    <table class="table mt-5">
							  <thead>
							    <tr>
							      <th scope="col">Num</th>
							      <th scope="col">name</th>
							      <th scope="col">contents</th>
							      <th scope="col">category</th>
							      <th scope="col">rate</th>
							      <th scope="col">sale</th>
							    </tr>
							  </thead>
							  <tbody>
								  <c:forEach items="${list}" var="dto">
								    <tr>
								      <th scope="row">${dto.productsNum}</th>
								      <td>
								     
								      <a href="./detail?boardNum=${dto.productsNum}">${dto.productsName}</a>
								      </td>
								      <td>${dto.productsContents}</td>
								      <td>${dto.productsCategory}</td>
								      <td>${dto.productsRate}</td>
								      <td>${dto.products_sale}</td>
								    </tr>
								  </c:forEach>
							  </tbody>
							</table>
							
						</div> </div> <div class="row justify-content-center">
                    	<div class="col-sm-8 d-flex justify-content-between">
                    	
							<nav aria-label="Page navigation example">
							  <ul class="pagination">
							    <li class="page-item">
							      <a class="page-link" href="./list?page=${pager.begin-1}&kind=${param.kind}&search=${param.search}" aria-label="Previous">
							        <span aria-hidden="true">&laquo;</span>
							      </a>
							    </li>
							    
							   <c:forEach begin="${pager.begin}" end="${pager.end}" var="i">
							    <li class="page-item"><a class="page-link" href="./list?page=${i}&kind=${pager.kind}&search=${pager.search}">${i}</a></li>
							   </c:forEach> 
							    
							    <li class="page-item">
							      <a class="page-link" href="./list?page=${pager.end + 1}&kind=${param.kind}&search=${param.search}" aria-label="Next">
							        <span aria-hidden="true">&raquo;</span>
							      </a>
							    </li>
							  </ul>
							</nav>
							
							<div>
					        	<a href="./add" class="btn btn-outline-primary">글쓰기</a>
							</div>
							
						</div>
                    </div>
						
            	</div>
            	</div>
        	<footer class="sticky-footer bg-white">
                <div class="container my-auto">
                    <div class="copyright text-center my-auto">
                        <span>Copyright &copy; Your Website 2021</span>
                    </div>
                </div>
            </footer>
            </div>
        
	</div>
	
	<c:import url="/WEB-INF/views/template/foot.jsp"></c:import>
	
</body>
</html>