package com.pm.hibernate.controller;

import com.pm.hibernate.dto.SampleDTO;
import com.pm.hibernate.service.TestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by pmackiewicz on 2016-02-05.
 */
@Component
public class MyControllerImpl implements MyController{

    public static final Logger LOG = LoggerFactory.getLogger(MyControllerImpl.class);

    @Autowired
    private TestService testService;

    @Override
    public void prepare()  {
        SampleDTO sampleDTO = testService.get();
        LOG.info("Find " + sampleDTO);
    }
}
