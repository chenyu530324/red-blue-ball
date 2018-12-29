package com.jichen.redblueball.etl.service;

import org.easymock.EasyMockRunner;
import org.easymock.EasyMockSupport;
import org.easymock.Mock;
import org.easymock.TestSubject;
import org.junit.Test;
import org.junit.runner.RunWith;


import static java.util.Collections.emptyList;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.expectLastCall;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@RunWith(EasyMockRunner.class)
public class KillerEtlTest extends EasyMockSupport {

    @TestSubject
    private KillerEtl killerEtl = new KillerEtl();

    @Mock
    private ClassScannerService classScannerService;

    @Test
    public void couldEtlKiller() {
        expect(classScannerService.scanKillers()).andReturn(emptyList());
        expectLastCall().times(1);
        assertThat(killerEtl.etl(emptyList()), is(true));
    }

}