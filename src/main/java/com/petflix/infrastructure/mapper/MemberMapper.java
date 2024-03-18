package com.petflix.infrastructure.mapper;

import com.petflix.domain.bean.Member;
import com.petflix.domain.bean.generalfields.FirstName;
import com.petflix.domain.bean.generalfields.Id;
import com.petflix.domain.bean.generalfields.LastName;
import com.petflix.domain.bean.memberfield.MemberCity;
import com.petflix.infrastructure.dto.MemberDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MemberMapper {

	public Member mapToDomain(MemberDTO memberDTO) {
		return new Member(
			new Id(memberDTO.getId()),
			new FirstName(memberDTO.getFirstName()),
			new LastName(memberDTO.getLastName()),
			new MemberCity(memberDTO.getCity()),
			memberDTO.getMail(),
			memberDTO.getPhone()
		);
	}

	public List<Member> mapAllToDomain(List<MemberDTO> memberDTOs) {
		return memberDTOs.stream().map(this::mapToDomain).toList();
	}

	public List<MemberCity> mapCities(List<String> cities) {
		return cities.stream().map(MemberCity::new).toList();
	}

}
