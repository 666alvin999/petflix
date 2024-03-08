package com.petflix.domain.bean;

import com.petflix.domain.bean.generalfields.FirstName;
import com.petflix.domain.bean.generalfields.Id;
import com.petflix.domain.bean.generalfields.LastName;
import com.petflix.domain.bean.memberfield.MemberCity;

public record Member(Id id, FirstName firstName, LastName lastName, MemberCity city, String mail, String phone) {
}

