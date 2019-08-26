package com.mobiquityinc.packer;

import static org.junit.jupiter.api.Assertions.*;

import java.util.stream.IntStream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import com.mobiquityinc.exception.APIException;

class ThingTest {

	static IntStream range() {
	    return IntStream.range(0, 5);
	}

	@ParameterizedTest
	@MethodSource("range")
	void testConvertList(int index) throws APIException {
		assertEquals(
				PackerTestValues.createThings(index),
				Thing.convertList(PackerTestValues.THINGS_VALUES[index],Pack.MAX_NUMBER_OF_ITEMS));
	}

}
