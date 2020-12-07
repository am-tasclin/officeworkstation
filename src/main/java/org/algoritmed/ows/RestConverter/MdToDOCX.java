package org.algoritmed.ows.RestConverter;

import org.algoritmed.ows.amdb.test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MdToDOCX {

    protected static final Logger logger = LoggerFactory.getLogger(MdToDOCX.class);
    protected test test1 = new test();

    @RequestMapping("/r/test")
    public void test() {
        test1.testFnc();
    }
}
