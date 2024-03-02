package com.petflix.domain.usecase;

import com.petflix.domain.bean.memberfield.MemberCity;
import com.petflix.domain.port.MemberPort;

import java.util.List;

public class GetAllCities {

	private final MemberPort memberPort;

	public GetAllCities(MemberPort memberPort) {
		this.memberPort = memberPort;
	}

	public List<MemberCity> execute() {
		return memberPort.getAllMembersCity();
	}

}
