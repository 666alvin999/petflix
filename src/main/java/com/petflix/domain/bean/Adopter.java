package com.petflix.domain.bean;

import com.petflix.domain.bean.generalfields.FirstName;
import com.petflix.domain.bean.generalfields.Id;
import com.petflix.domain.bean.generalfields.LastName;

public record Adopter(Id id, FirstName firstName, LastName lastName, String address, String mail) {
}
