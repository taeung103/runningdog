<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
    <c:import url="../include/admin_head.jsp"/>
</head>
<body oncontextmenu="return false" onselectstart="return false" ondragstart="return false">
    <div id="wrap">
        <c:import url="../include/admin_header.jsp"/>

        <div id="container">
            <c:import url="../include/admin_util.jsp"/>

            <!-- 상단 타이틀 -->
            <div class="pageTitle">
                <div class="adminPath">
                    <h3>후원하기 관리</h3>
                    <h2>| 리스트</h2>
                </div>
            </div>
            <!-- //상단 타이틀 -->

            <!-- 본문내용 -->
            <div class="list_wrap">
                <!-- 검색영역 -->
                <div class="sort-area">  
                    <h4>전체 게시물 100개</h4>
                    <form action="" method="get" id="">
                    <div class="searchBox">
                        <select name="search" class="ListSelect">
                                <option value="">제목</option>
                        </select>
                        <div>
                            <input type="text" name="keyword" placeholder="검색어를 입력해주세요.">
                            <button type="submit" class="top-search"><i class="xi-search"></i></button>
                        </div>
                    </div>
                    </form>
                </div>
                <!-- 검색영역 끝 -->
                <table class="list">
                    <colgroup>
                        <col width="5%">
                        <col width="5%">
                        <col width="8%">
                        <col width="8%">
                        <col width="*">
                        <col width="10%">
                        <col width="10%">
                    </colgroup>
                    <thead>
                        <tr>
                            <th><input type="checkbox" name="" id="" value=""></th>
                            <th>번호</th>
                            <th>분류</th>
                            <th>썸네일</th>
                            <th>제목</th>
                            <th>작성자</th>
                            <th>등록일</th>
                        </tr>
                        <tr>
                        </tr>
                        <tr class="hr">
                            <th colspan="8"></th>
                        </tr>
                    </thead>
                    <tbody>
                    
                    	<%-- <c:if test=""> --%>
                        <tr>
                            <td><input type="checkbox" name="" id="" value=""></td>
                            <td class="number" onclick="location='asdetial.ad'">6</td>
                            <td class="kinds" onclick="location='asdetial.ad'"><span class="protect">후원중</span></td>
                            <td class="thumbnail" onclick="location='asdetial.ad'"><img src="resources/images/test/test01.jfif"></td>
                            <td class="title" onclick="location='asdetial.ad'">이 작은 아이가 살아갈 수 있게 도와주세요.</td>
                            <td class="name" onclick="location='asdetial.ad'">관리자</td>
                            <td class="date" onclick="location='asdetial.ad'">2020.09.01</td>
                        </tr>
                        <tr>
                            <td><input type="checkbox" name="" id="" value=""></td>
                            <td class="number" onclick="location='asdetial.ad'">6</td>
                            <td class="kinds" onclick="location='asdetial.ad'"><span class="protect">후원중</span></td>
                            <td class="thumbnail" onclick="location='asdetial.ad'"><img src="resources/images/test/test01.jfif"></td>
                            <td class="title" onclick="location='asdetial.ad'">이 작은 아이가 살아갈 수 있게 도와주세요.</td>
                            <td class="name" onclick="location='asdetial.ad'">관리자</td>
                            <td class="date" onclick="location='asdetial.ad'">2020.09.01</td>
                        </tr>
                        <tr>
                            <td><input type="checkbox" name="" id="" value=""></td>
                            <td class="number" onclick="location='asdetial.ad'">6</td>
                            <td class="kinds" onclick="location='asdetial.ad'"><span class="protect">후원중</span></td>
                            <td class="thumbnail" onclick="location='asdetial.ad'"><img src="resources/images/test/test01.jfif"></td>
                            <td class="title" onclick="location='asdetial.ad'">이 작은 아이가 살아갈 수 있게 도와주세요.</td>
                            <td class="name" onclick="location='asdetial.ad'">관리자</td>
                            <td class="date" onclick="location='asdetial.ad'">2020.09.01</td>
                        </tr>
                        <tr>
                            <td><input type="checkbox" name="" id="" value=""></td>
                            <td class="number" onclick="location='asdetial.ad'">6</td>
                            <td class="kinds" onclick="location='asdetial.ad'"><span class="protect">후원중</span></td>
                            <td class="thumbnail" onclick="location='asdetial.ad'"><img src="resources/images/test/test01.jfif"></td>
                            <td class="title" onclick="location='asdetial.ad'">이 작은 아이가 살아갈 수 있게 도와주세요.</td>
                            <td class="name" onclick="location='asdetial.ad'">관리자</td>
                            <td class="date" onclick="location='asdetial.ad'">2020.09.01</td>
                        </tr>
                        <tr>
                            <td><input type="checkbox" name="" id="" value=""></td>
                            <td class="number" onclick="location='asdetial.ad'">6</td>
                            <td class="kinds" onclick="location='asdetial.ad'"><span class="complete">후원마감</span></td>
                            <td class="thumbnail" onclick="location='asdetial.ad'"><img src="resources/images/test/test01.jfif"></td>
                            <td class="title" onclick="location='asdetial.ad'">이 작은 아이가 살아갈 수 있게 도와주세요.</td>
                            <td class="name" onclick="location='asdetial.ad'">관리자</td>
                            <td class="date" onclick="location='asdetial.ad'">2020.09.01</td>
                        </tr>
                        <tr>
                            <td><input type="checkbox" name="" id="" value=""></td>
                            <td class="number" onclick="location='asdetial.ad'">6</td>
                            <td class="kinds" onclick="location='asdetial.ad'"><span class="complete">후원마감</span></td>
                            <td class="thumbnail" onclick="location='asdetial.ad'"><img src="resources/images/test/test01.jfif"></td>
                            <td class="title" onclick="location='asdetial.ad'">이 작은 아이가 살아갈 수 있게 도와주세요.</td>
                            <td class="name" onclick="location='asdetial.ad'">관리자</td>
                            <td class="date" onclick="location='asdetial.ad'">2020.09.01</td>
                        </tr>
                        <%-- </c:if>
                        
                        <c:if test=""> --%>
						<tr class="list-no">
							<td colspan="7">
								<p><img src="resources/images/btnIcn/icn_big_listNo.png" alt="" title="" /></p>
								<h1>목록이 없습니다.</h1>
							</td>
						</tr>
						<%-- </c:if> --%>
                    </tbody>
                </table>
                <p class="warning_text"> *삭제된 게시물은 되돌릴 수 없습니다. 신중하게 선택해주세요.</p>
                <!-- //게시판 -->

                <!-- 버튼 -->
                <div class="list-btn">
                    <button type="button" id="" class="btn-left chkBtn"><i class="xi-cut"></i> 선택삭제</button>
                    <button type="button" id="" class="btn-right writeBtn" onclick="location='aswrite.ad'"><i class="xi-pen-o"></i> 글작성</button>
                </div>
                <!-- //버튼 -->

                <!-- 페이징 -->
                <dl class="list-paging">
                    <dd>
                   		<a href="#none"><i class="xi-angle-left"></i></a>
                        <a href="#none" class="active">1</a>
                        <a href="#none">2</a>
                        <a href="#none">3</a>
                        <a href="#none">4</a>
                        <a href="#none">5</a>
                        <a href="#none"><i class="xi-angle-right"></i></a>
                    </dd>
                </dl>
                <!-- //페이징 -->

            </div>
        </div>
        <c:import url="../include/admin_footer.jsp"/>
    </div>
</body>
</html>