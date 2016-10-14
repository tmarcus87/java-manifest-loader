package org.tmarcus87.util;

import org.junit.Test;

import java.util.jar.Manifest;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.*;

public class ManifestLoaderTest {

	@Test
	public void testGetByClass() throws Exception {
		Manifest manifest = ManifestLoader.getByClass(Test.class);
		assertThat(manifest, notNullValue());
		assertThat(manifest.getMainAttributes(), notNullValue());
		assertThat(manifest.getMainAttributes().getValue("Implementation-Title"), is("JUnit"));
		assertThat(manifest.getMainAttributes().getValue("Implementation-Version"), is("4.12"));
		assertThat(manifest.getMainAttributes().getValue("Implementation-Vendor"), is("JUnit"));
		assertThat(manifest.getMainAttributes().getValue("Implementation-Vendor-Id"), is("junit"));
	}

	@Test
	public void testLoadManifest() throws Exception {

	}
}