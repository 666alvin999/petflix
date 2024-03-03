package com.petflix.domain.port;

import com.petflix.domain.bean.Member;
import com.petflix.domain.bean.memberfield.MemberCity;

import java.util.List;
import java.util.Set;

public interface MemberPort {

	List<MemberCity> getAllMembersCity();

	Member getMemberById(int id);

	List<Member> getMembersByIds(Set<Integer> ids);

}
