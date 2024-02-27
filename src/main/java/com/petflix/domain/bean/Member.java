package com.petflix.domain.bean;

import com.petflix.domain.bean.generalfields.FirstName;
import com.petflix.domain.bean.generalfields.Id;
import com.petflix.domain.bean.generalfields.LastName;

public record Member(Id id, FirstName firstName, LastName lastName, String city, String mail, String phone) {
}

