package com.hazelcast.internal.serialization.impl;

import com.hazelcast.internal.serialization.InternalSerializationService;
import com.hazelcast.nio.serialization.Serializer;
import com.hazelcast.test.HazelcastParallelClassRunner;
import com.hazelcast.test.annotation.ParallelTest;
import com.hazelcast.test.annotation.QuickTest;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;

import java.nio.ByteOrder;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;

@RunWith(HazelcastParallelClassRunner.class)
@Category({QuickTest.class, ParallelTest.class})
public class StreamSerializerAdapterTest {

    private StreamSerializerAdapter adapter;
    private ConstantSerializers.IntegerArraySerializer serializer;

    private InternalSerializationService mockSerializationService;

    @Before
    public void setUp() {
        mockSerializationService = mock(InternalSerializationService.class);
        serializer = new ConstantSerializers.IntegerArraySerializer();
        adapter = new StreamSerializerAdapter(mockSerializationService, serializer);
    }

    @After
    public void tearDown() throws Exception {
        adapter.destroy();
    }

    @Test
    public void testAdaptor() throws Exception {
        int[] testIn = new int[]{1, 2, 3};

        ByteArrayObjectDataOutput out = new ByteArrayObjectDataOutput(100, mockSerializationService, ByteOrder.BIG_ENDIAN);
        ByteArrayObjectDataInput in = new ByteArrayObjectDataInput(out.buffer, mockSerializationService, ByteOrder.BIG_ENDIAN);
        adapter.write(out, testIn);
        int[] read = (int[]) adapter.read(in);

        Serializer impl = adapter.getImpl();

        assertArrayEquals(testIn, read);
        assertEquals(serializer, impl);
    }

    @Test
    public void testAdaptorEqualAndHashCode() throws Exception {
        StreamSerializerAdapter theOther = new StreamSerializerAdapter(mockSerializationService, serializer);
        StreamSerializerAdapter theEmptyOne = new StreamSerializerAdapter(mockSerializationService, null);

        assertEquals(adapter, adapter);
        assertEquals(adapter, theOther);
        assertNotEquals(adapter, null);
        assertNotEquals(adapter, "Not An Adaptor");
        assertNotEquals(adapter, theEmptyOne);

        assertEquals(adapter.hashCode(), serializer.hashCode());

        assertEquals(0, theEmptyOne.hashCode());
    }

    @Test
    public void testString() throws Exception {
        assertNotNull(adapter.toString());
    }
}
