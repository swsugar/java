<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!-- 테이블 태그를 이용하여 상품(상품코드, 상품명, 분류명, 거래처명, 입고일, 구매가, 판매가, 마일리지) 목록 랜더링. -->
<table class="table table-bordered">
	<thead>
		<tr>
			<th>일련번호</th>
			<th>상품명</th>
			<th>상품분류</th>
			<th>거래처</th>
			<th>입고일</th>
			<th>구매가</th>
			<th>판매가</th>
			<th>마일리지</th>
		</tr>
	</thead>
	<tbody>
		<c:set var="prodList" value="${pagingVO.dataList }" />
		<c:choose>
			<c:when test="${not empty prodList }">
				<c:forEach items="${prodList }" var="prod">
					<tr>
						<td>${prod.rnum }</td>
						<td>
							<c:url value="/prod/prodView.do" var="prodViewURL">
								<c:param name="what" value="${prod.prodId }" />
							</c:url>
							<a href="${prodViewURL }">${prod.prodName }</a>
						</td>
						<td>${prod.lprodNm }</td>
						<td>${prod.buyer.buyerName }</td>
						<td>${prod.prodInsdate }</td>
						<td>${prod.prodCost }</td>
						<td>${prod.prodPrice }</td>
						<td>${prod.prodMileage }</td>
					</tr>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<tr>
					<td colspan="7">상품이 없음.</td>
				</tr>
			</c:otherwise>
		</c:choose>
	</tbody>
	<tfoot>
		<tr>
			<td colspan="7">
				<div class="d-flex justify-content-center pagingArea">
					${pagingVO.pagingHTMLBS }
				</div>
				<div id="searchUI" class="d-flex justify-content-center">
					<select name="prodLgu">
						<option value>상품분류</option>
						<c:forEach items="${lprodList }" var="lprod">
							<option value="${lprod['lprodGu'] }">${lprod.lprodNm }</option>
						</c:forEach>
					</select>
					<select name="prodBuyer">
						<option value>상품거래처</option>
						<c:forEach items="${buyerList }" var="buyer">
							<option value="${buyer.buyerId }" class="${buyer.buyerLgu }">${buyer.buyerName }</option>
						</c:forEach>
					</select>
					<input type="text" name="prodName" placeholder="상품명"/>
					<input id="searchBtn" type="button" value="검색" class="btn btn-success"/>
					<input type="button" value="신규등록" class="btn btn-secondary linkBtn"
						data-href="${cPath }/prod/prodInsert.do"
					/>
				</div>
			</td>
		</tr>
	</tfoot>
</table>
<form id="searchForm" action="${cPath }/prod/prodList.do">
	<input type="text" name="prodLgu" placeholder="상품분류"/>
	<input type="text" name="prodBuyer" placeholder="거래처"/>
	<input type="text" name="prodName" placeholder="상품명"/>
	<input type="text" name="page" placeholder="page">
</form>
<script>
	$(".linkBtn").on("click", function(){
		let href = $(this).data("href");
		location.href = href;
	});
	$("[name=prodBuyer]").val("${pagingVO.detailCondition.prodBuyer}");
	$("[name=prodLgu]").on("change", function(event){
		let selectedLgu = $(this).val();
		let options = $(this).siblings("[name=prodBuyer]").find("option");
		$.each(options, function(idx, opt){
			if(!selectedLgu||idx==0 || $(this).hasClass(selectedLgu)){
				$(this).show();
			}else{
				$(this).hide();
			}
		});
// 		prodBuyerTag.find("option:first");
// 		options[0]
		
	}).val("${pagingVO.detailCondition.prodLgu}").trigger("change");
	
	$("[name=prodName]").val("${pagingVO.detailCondition.prodName}");
	
	$(".pagingArea").on("click", "a", function(event){
		let page = $(this).data("page");
		searchForm.find("[name=page]").val(page);
		searchForm.submit();
	});
	
	let searchUI = $("#searchUI");
	let searchForm = $("#searchForm");
	$("#searchBtn").on("click", function(event){
		let inputs = searchUI.find(":input[name]");
		$(inputs).each(function(idx, input){
			let name = $(this).attr("name");
			let value = $(this).val();
			searchForm.find("[name="+name+"]").val(value);
		});
		searchForm.submit();
	});
</script>















