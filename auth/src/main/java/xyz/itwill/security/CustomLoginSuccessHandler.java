package xyz.itwill.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import lombok.extern.slf4j.Slf4j;

//�α��� ���� �Ŀ� ����� ����� �����ϱ� ���� Ŭ����
// => ������� ������ �α��� ��¥�� ���� ó�� �Ǵ� �α��� ���� Ƚ�� �ʱ�ȭ ���� ��� ����
// => AuthenticationSuccessHandler �������̽��� ��ӹ޾� �ۼ��ϰų� AuthenticationSuccessHandler 
//�������̽��� ��ӹ��� Ŭ������ ��ӹ޾� �ۼ�
@Slf4j
public class CustomLoginSuccessHandler implements AuthenticationSuccessHandler {
	@Override
	//�α��� ������ ������ Ȯ���Ͽ� Ư�� �������� �̵��ǵ��� ����
	//Authentication ��ü : ���� �� �ΰ�(����)�� ���õ� ������ ������ ��ü
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		//�α��� ������� ������ �����ϱ� ���� List ��ü ����
		List<String> roleNames=new ArrayList<String>();
		
		//Authentication.getAuthorities() : ������ ������ ��� ����(GrantedAuthority ��ü)�� 
		//List ��ü�� ��ȯ�ϴ� �޼ҵ�
		//GrantedAuthority ��ü : ����ڿ��� �ο��� ���ѿ� ���� ������ ������ ��ü
		for(GrantedAuthority authority : authentication.getAuthorities()) {
			//GrantedAuthority.getAuthority() : GrantedAuthority ��ü�� ����� ������ ��ȯ�ϴ� �޼ҵ� 
			roleNames.add(authority.getAuthority());
		}
		
		log.warn(roleNames.toString());
		
		//Collection<T>.contains(T obj) : Collection ��ü�� ����� ����� ���� ������ Ȯ���Ͽ�
		//Collection ��ü�� ��Ұ� ���� [false]�� ��ȯ�ϰ� ��Ұ� �ִ� ��� [true]�� ��ȯ�ϴ� �޼ҵ�
		if(roleNames.contains("ROLE_ADMIN")) {
			response.sendRedirect(request.getContextPath()+"/admin/page");
			return;
		}
		
		if(roleNames.contains("ROLE_MANAGER")) {
			response.sendRedirect(request.getContextPath()+"/manager/page");
			return;
		}
		
		if(roleNames.contains("ROLE_USER")) {
			response.sendRedirect(request.getContextPath()+"/user/page");
			return;
		}
		
		response.sendRedirect(request.getContextPath()+"/guest/page");
	}

}











