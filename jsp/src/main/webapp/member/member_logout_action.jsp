<%@page import="java.net.URLEncoder"%>
<%@page import="xyz.itwill.dao.MemberDAO"%>
<%@page import="xyz.itwill.dto.MemberDTO"%>
<%@page import="xyz.itwill.util.Utility"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- 로그인정보를 전달받아 MEMBER 테이블에 저장된 회원정보와 비교하여 로그인 처리하고 
[main/main_page.jsp] 문서를 요청하기 위한 URL 주소를 전달하여 응답하는 JSP 문서 --%>
<%-- => 전달받은 로그인정보로 인증이 실패한 경우 [member/member_login.jsp] 문서를 요청하기 위한 URL 주소를 전달  --%>    
<%
	session.removeAttribute("loginMember");
	response.sendRedirect(request.getContextPath()+"/index.jsp?group=member&worker=member_login");

%>






