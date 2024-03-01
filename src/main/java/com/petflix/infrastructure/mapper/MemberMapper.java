package com.petflix.infrastructure.mapper;

import com.petflix.domain.bean.Member;
import com.petflix.domain.bean.PresentationVideo;
import com.petflix.domain.bean.generalfields.FirstName;
import com.petflix.domain.bean.generalfields.Id;
import com.petflix.domain.bean.generalfields.LastName;
import com.petflix.domain.bean.generalfields.Url;
import com.petflix.infrastructure.dto.MemberDTO;
import com.petflix.infrastructure.dto.PresentationVideoDTO;

import java.time.LocalDate;

public class MemberMapper {

	public Member mapToDomain(MemberDTO memberDTO) {
		return new Member(
			new Id(memberDTO.getId()),
			new FirstName(memberDTO.getFirstName()),
			new LastName(memberDTO.getLastName()),
			memberDTO.getCity(),
			memberDTO.getEmail(),
			memberDTO.getPhone()
		);
	}

	public MemberDTO mapToDTO(Member member) {
		return new MemberDTO(
			member.id().value(),
			member.firstName().value(),
			member.lastName().value(),
			member.city(),
			member.mail(),
			member.phone()
		);
	}

}
