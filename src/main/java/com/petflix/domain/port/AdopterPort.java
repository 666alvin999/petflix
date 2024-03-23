package com.petflix.domain.port;

import com.petflix.domain.bean.ActionSuccess;
import com.petflix.domain.bean.Adopter;

import java.util.List;

public interface AdopterPort {

	List<Adopter> getAllAdopters();

	ActionSuccess createAdopter(Adopter adopter);

}
