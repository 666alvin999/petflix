package com.petflix.domain.port;

import com.petflix.domain.bean.Member;
import com.petflix.domain.bean.memberfield.MemberCity;

import java.util.List;
import java.util.Set;

public interface MemberPort {

	List<Member> getAllMembers();

	List<Member> getMembersByIds(Set<Integer> ids);

	List<MemberCity> getAllMembersCity();

}
