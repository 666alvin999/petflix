package com.petflix.application.mapper;

import com.petflix.application.dto.MemberViewModel;
import com.petflix.domain.bean.Member;
import com.petflix.domain.bean.generalfields.FirstName;
import com.petflix.domain.bean.generalfields.Id;
import com.petflix.domain.bean.generalfields.LastName;
import com.petflix.domain.bean.memberfield.MemberCity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MemberPresentationMapper {

	public MemberViewModel mapToViewModel(Member member) {
		return new MemberViewModel(
			member.id().value(),
			member.firstName().value(),
			member.lastName().value(),
			member.city().value(),
			member.mail(),
			member.phone()
		);
	}

	public List<MemberViewModel> mapAllToViewModels(List<Member> members) {
		return members.stream().map(this::mapToViewModel).toList();
	}

	public Member mapToDomain(MemberViewModel memberViewModel) {
		return new Member(
			new Id(memberViewModel.getId()),
			new FirstName(memberViewModel.getFirstName()),
			new LastName(memberViewModel.getLastName()),
			new MemberCity(memberViewModel.getCity()),
			memberViewModel.getMail(),
			memberViewModel.getPhone()
		);
	}

}
