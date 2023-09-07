package xyz.itwill.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Data;
import xyz.itwill.dto.SecurityAuth;
import xyz.itwill.dto.SecurityUsers;

//������ ����ڿ� ���� ������ �����ϱ� ���� Ŭ����
// => UserDatails �������̽��� ��ӹ޾� �ۼ� 
// => UserDatails �������̽��� ��ӹ��� User Ŭ������ ��ӹ޾� �ۼ� ����
@Data
public class CustomUserDetails implements UserDetails {
	private static final long serialVersionUID = 1L;

	//������ ������� ������ ����� �ʵ� ����
	private String userid;
	private String passwd;
	private String name;
	private String email;
	private String enabled;
	//������ ������� ���� ������ ����� �ʵ� ����
	private List<GrantedAuthority> securityAuthList;
	
	//�Ű������� ���޹��� SecurityUsers ��ü�� �ʵ尪�� CustomUserDetails Ŭ������ �ʵ忡 ����
	public CustomUserDetails(SecurityUsers users) {
		this.userid=users.getUserid();
		this.passwd=users.getPasswd();
		this.name=users.getName();
		this.email=users.getEmail();
		this.enabled=users.getEnabled();

		//�˻��� ������� ����(String ��ü)�� GrantedAuthority ��ü�� �����Ͽ� List ��ü�� ��ҷ� ����
		this.securityAuthList=new ArrayList<GrantedAuthority>();
		for(SecurityAuth auth : users.getSecurityAuthList()) {
			securityAuthList.add(new SimpleGrantedAuthority(auth.getAuth()));
		}
	}
	
	//���� ������� ���� ������ ��ȯ�ϴ� �޼ҵ� 
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return securityAuthList;
	}

	//���� ������� ��й�ȣ�� ��ȯ�ϴ� �޼ҵ�
	@Override
	public String getPassword() {
		return passwd;
	}

	//���� ������� �ĺ���(���̵�)�� ��ȯ�ϴ� �޼ҵ�
	@Override
	public String getUsername() {
		return userid;
	}

	//���� ������� ��ȿ�Ⱓ ���� ������ ��ȯ�ϴ� �޼ҵ�
	// => false : ����� ��ȿ�Ⱓ �ʰ� ����, true : ����� ��ȿ�Ⱓ �̸� ����    
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	//���� ������� ����� ��� ���� ������ ��ȯ�ϴ� �޼ҵ�
	// => false : ����� ��� ����, true : ����� ���� ����    
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	//���� ������� ��й�ȣ ��ȿ�Ⱓ ���� ������ ��ȯ�ϴ� �޼ҵ�
	// => false : ��й�ȣ ��ȿ�Ⱓ �ʰ� ����, true : ��й�ȣ ��ȿ�Ⱓ �̸� ����   
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	//���� ������� Ȱ��ȭ ���� ������ ��ȯ�ϴ� �޼ҵ�
	// => false : ����� ��Ȱ��ȭ ����, true : ����� ��Ȱ��ȭ ����  
	@Override
	public boolean isEnabled() {
		if(enabled.equals("0")) {
			return false;
		} else {
			return true;
		}
	}

}