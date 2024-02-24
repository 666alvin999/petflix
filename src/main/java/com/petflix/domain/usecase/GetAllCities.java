package com.petflix.domain.usecase;

import com.petflix.domain.port.MemberPort;

import java.util.List;

public class GetAllCities {

	private final MemberPort memberPort;

	public GetAllCities(MemberPort memberPort) {
		this.memberPort = memberPort;
	}

	public List<String> execute() {
		return memberPort.getAllMembersCity();
	}

}
