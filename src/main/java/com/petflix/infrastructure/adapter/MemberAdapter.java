package com.petflix.infrastructure.adapter;

import com.petflix.domain.bean.Member;
import com.petflix.domain.port.MemberPort;

import java.util.List;

public class MemberAdapter implements MemberPort {

	@Override
	public List<String> getAllMembersCity() {
		return null;
	}

	@Override
	public Member getMemberById(int id) {
		return null;
	}

}
