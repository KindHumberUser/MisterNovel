/*
 * Created by Sagar Ranga on 2025.5.28
 * Copyright © 2025 Sagar Ranga. All rights reserved.
 */

package com.ranga.misternovel.repositories;

import com.ranga.misternovel.entities.Address;
import org.springframework.data.repository.CrudRepository;

public interface AddressRepository extends CrudRepository<Address, Long> {
}
