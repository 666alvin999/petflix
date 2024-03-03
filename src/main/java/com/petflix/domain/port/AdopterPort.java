package com.petflix.domain.port;

import com.petflix.domain.bean.Adopter;

import java.util.List;

public interface AdopterPort {

	Adopter getAdopterById(int id);

	List<Adopter> getAdoptersByIds(List<Integer> ids);

}
