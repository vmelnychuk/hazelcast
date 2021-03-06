package com.hazelcast.map.impl.nearcache.invalidation;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.impl.MapService;
import com.hazelcast.map.impl.MapServiceContext;
import com.hazelcast.nio.serialization.Data;
import com.hazelcast.test.HazelcastParallelClassRunner;
import com.hazelcast.test.HazelcastTestSupport;
import com.hazelcast.test.RequireAssertEnabled;
import com.hazelcast.test.annotation.ParallelTest;
import com.hazelcast.test.annotation.QuickTest;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;

import static org.mockito.Mockito.mock;

@RunWith(HazelcastParallelClassRunner.class)
@Category({QuickTest.class, ParallelTest.class})
public class NonStopInvalidatorTest extends HazelcastTestSupport {

    private Data key;
    private NonStopInvalidator invalidator;

    @Before
    public void setUp() {
        key = mock(Data.class);

        HazelcastInstance hz = createHazelcastInstance();
        MapService service = getNodeEngineImpl(hz).getService(MapService.SERVICE_NAME);
        MapServiceContext mapServiceContext = service.getMapServiceContext();
        invalidator = new NonStopInvalidator(mapServiceContext);
    }

    @RequireAssertEnabled
    @Test(expected = AssertionError.class)
    public void testInvalidate_withInvalidMapName() {
        invalidator.invalidateKey(key, null, null);
    }

    @RequireAssertEnabled
    @Test(expected = AssertionError.class)
    public void testClear_withInvalidMapName() {
        invalidator.invalidateAllKeys(null, null);
    }
}
