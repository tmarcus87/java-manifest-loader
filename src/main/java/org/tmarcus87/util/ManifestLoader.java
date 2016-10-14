package org.tmarcus87.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.jar.Manifest;

/**
 * @author ono_takahiko
 * @since 16/10/14
 */
public class ManifestLoader {

	private static Map<Class, Manifest> manifests = new HashMap<>();

	/**
	 * Load manifest by {@code Class}
	 *
	 * @param clazz    Class to get the Manifest.
	 * @return A manifest objector.
	 *
	 * @throws FileNotFoundException
	 * @throws MalformedURLException
	 * @throws IOException
	 */
	public static Manifest getByClass(Class clazz) throws FileNotFoundException, MalformedURLException, IOException {
		Manifest manifest = manifests.get(clazz);
		if (manifest == null) {
			manifest = loadManifest(clazz);
			manifests.put(clazz, manifest);
		}
		return manifest;
	}

	private static Manifest loadManifest(Class clazz) throws FileNotFoundException, MalformedURLException, IOException {
		URL classURL = clazz.getResource(clazz.getSimpleName() + ".class");

		if (classURL == null) {
			throw new FileNotFoundException();
		}

		String fullPath = classURL.toExternalForm();
		String packagePath = clazz.getPackage().getName().replaceAll("\\.", "/");
		String jarPath = fullPath.substring(0, fullPath.lastIndexOf(packagePath));

		return getByURL(new URL(jarPath + "META-INF/MANIFEST.MF"));
	}

	/**
	 * Load manifest by {@code URL}
	 *
	 * @param manifestUrl
	 * @return
	 *
	 * @throws IOException
	 */
	public static Manifest getByURL(URL manifestUrl) throws IOException{
		try (InputStream is = manifestUrl.openStream()) {
			return new Manifest(is);
		}
	}

}
