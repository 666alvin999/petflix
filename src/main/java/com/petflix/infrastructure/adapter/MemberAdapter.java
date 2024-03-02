package com.petflix.infrastructure.adapter;

import com.petflix.domain.bean.Member;
import com.petflix.domain.bean.memberfield.MemberCity;
import com.petflix.domain.port.MemberPort;
import com.petflix.infrastructure.dao.MemberDao;
import com.petflix.infrastructure.dto.MemberDTO;
import com.petflix.infrastructure.mapper.MemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MemberAdapter implements MemberPort {

	private final MemberDao memberDao;
	private final MemberMapper memberMapper;

	@Autowired
	public MemberAdapter(MemberDao memberDao, MemberMapper memberMapper) {
		this.memberDao = memberDao;
		this.memberMapper = memberMapper;
	}

	@Override
	public List<MemberCity> getAllMembersCity() {
		List<String> citiesData = this.memberDao.getAllCities();

		return this.memberMapper.mapCities(citiesData);
	}

	@Override
	public Member getMemberById(int id) {
		List<MemberDTO> memberDTOs = this.memberDao.getMemberById(id);

		if (memberDTOs.size() != 1) {
			return null;
		}

		return this.memberMapper.mapToDomain(memberDTOs.get(0));
	}

}
