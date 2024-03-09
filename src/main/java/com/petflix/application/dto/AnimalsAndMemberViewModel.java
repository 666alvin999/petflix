package com.petflix.application.dto;

import java.util.List;

public record AnimalsAndMemberViewModel(List<AnimalViewModel> animals, MemberViewModel member) {
}
