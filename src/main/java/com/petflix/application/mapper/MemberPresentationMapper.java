package com.petflix.application.mapper;

import com.petflix.application.dto.MemberViewModel;
import com.petflix.domain.bean.Member;
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

}
