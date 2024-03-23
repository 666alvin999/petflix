package com.petflix.domain.port;

import com.petflix.domain.bean.ActionSuccess;
import com.petflix.domain.bean.Adoption;

public interface AdoptionPort {

	ActionSuccess createAdoption(Adoption adoption);

}
