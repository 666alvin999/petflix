package com.petflix.domain.usecase;

import com.petflix.domain.bean.Member;
import com.petflix.domain.port.MemberPort;

public class GetMemberInfo {

	private final MemberPort memberPort;

	public GetMemberInfo(MemberPort memberPort) {
		this.memberPort = memberPort;
	}

	public Member execute(int id) {
		return this.memberPort.getMemberById(id);
	}

}
