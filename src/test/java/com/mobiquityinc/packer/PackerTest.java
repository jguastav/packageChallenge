package com.mobiquityinc.packer;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import com.mobiquityinc.exception.APIException;

class PackerTest {

	static IntStream range() {
	    return IntStream.range(0, 5);
	}

	@ParameterizedTest
	@MethodSource("range")
	void testPackOnePackage(int index) throws APIException {
		String result = Packer.packOnePackage(PackerTestValues.INPUT_PACKAGE[index]);
		assertEquals(result,PackerTestValues.RESULT_PACKAGE[index]);
	}
	
	@Test
	void testPack() throws APIException {
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("packageFile.txt").getFile());
		String result = Packer.pack(file.getAbsolutePath());
		assertEquals(PackerTestValues.FILE_RESULT_PACKAGE,result);
	}
	
	
}
