package com.petflix.domain.port;

import com.petflix.domain.bean.Member;

import java.util.List;

public interface MemberPort {

	List<String> getAllMembersCity();
	Member getMemberById(int id);
}
