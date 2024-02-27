package com.petflix.domain.usecase;

import com.petflix.domain.bean.Member;
import com.petflix.domain.port.MemberPort;

public class GetMemberInfoById {

	private final MemberPort memberPort;

	public GetMemberInfoById(MemberPort memberPort) {
		this.memberPort = memberPort;
	}

	public Member execute(int id) {
		return this.memberPort.getMemberById(id);
	}

}
