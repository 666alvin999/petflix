package com.petflix.domain.bean;

import com.petflix.domain.port.MemberPort;

public record Control(Adopter adopter, Animal animal, Member managing) {
}
