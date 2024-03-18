package com.petflix.domain.usecase;

import com.petflix.domain.bean.Member;
import com.petflix.domain.port.MemberPort;

import java.util.List;

public class GetAllMembers {

	private final MemberPort memberPort;

	public GetAllMembers(MemberPort memberPort) {
		this.memberPort = memberPort;
	}

	public List<Member> execute() {
		return this.memberPort.getAllMembers();
	}

}
