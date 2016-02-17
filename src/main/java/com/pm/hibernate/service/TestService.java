package com.pm.hibernate.service;

import com.pm.hibernate.dto.SampleDTO;

/**
 * Created by pmackiewicz on 2016-02-03.
 */
public interface TestService {
    SampleDTO get();

    SampleDTO find();

    SampleDTO delete();
}
