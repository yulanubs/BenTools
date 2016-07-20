package com.bentools.utils;

import java.io.InputStream;

public interface IFileCache {

	Object get(Object key);
	void put(Object key, Object file);
	void put(Object key, InputStream stream);
	
}
