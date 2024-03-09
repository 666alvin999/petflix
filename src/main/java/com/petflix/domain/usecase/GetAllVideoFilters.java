package com.petflix.domain.usecase;

import com.petflix.domain.bean.VideoFilters;
import com.petflix.domain.bean.animalfields.AnimalType;
import com.petflix.domain.bean.memberfield.MemberCity;
import com.petflix.domain.port.AnimalPort;
import com.petflix.domain.port.MemberPort;

import java.util.List;

public class GetAllVideoFilters {

	private final AnimalPort animalPort;
	private final MemberPort memberPort;

	public GetAllVideoFilters(AnimalPort animalPort, MemberPort memberPort) {
		this.animalPort = animalPort;
		this.memberPort = memberPort;
	}

	public VideoFilters execute() {
		List<AnimalType> animalTypes = this.animalPort.getAllTypes();
		List<MemberCity> memberCities = this.memberPort.getAllMembersCity();

		return new VideoFilters(animalTypes, memberCities);
	}

}
