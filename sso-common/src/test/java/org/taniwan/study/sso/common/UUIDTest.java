package org.taniwan.study.sso.common;

import org.junit.Test;

import com.fasterxml.uuid.EthernetAddress;
import com.fasterxml.uuid.Generators;

public class UUIDTest {
	
	@Test
	public void fasterxmlUUIDTest(){
		System.out.println(Generators.randomBasedGenerator().generate().toString());
		System.out.println(Generators.timeBasedGenerator().generate().toString());
		System.out.println(Generators.timeBasedGenerator(EthernetAddress.fromInterface()).generate().toString());
	}

}
