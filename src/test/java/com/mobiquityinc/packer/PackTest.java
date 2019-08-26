package com.mobiquityinc.packer;

import static org.junit.jupiter.api.Assertions.*;

import java.util.stream.IntStream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import com.mobiquityinc.exception.APIException;

class PackTest {

	static IntStream range() {
	    return IntStream.range(0, 5);
	}

	@ParameterizedTest
	@MethodSource("range")
	void testConvert(int index) throws APIException {
		assertEquals(PackerTestValues.createPack(index),Pack.convert(PackerTestValues.INPUT_PACKAGE[index]));
	}
	
	

}
