package com.codigo.unit_testing;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class UnitTestingApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	void testSumaAssertEquals(){
		//ARRANGE
		int resultadoEsperado =10;
		int actual;

		//ACT
		actual = 5+5;

		//ASSERT: afirmamos el resultado esperado
		assertEquals(resultadoEsperado,actual);

	}

}
