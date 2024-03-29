package org.processmining.stream.model.datastructure.countmin;

import java.io.UnsupportedEncodingException;

/**
 * Adapted from:
 * https://github.com/addthis/stream-lib/blob/master/src/main/java/com/
 * clearspring/analytics/stream/membership/Filter.java
 * commit: 9f1bf8bd8d81b4fcaf632a18be90091dd68afd4f
 * 
 * Source License: * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements. See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership. The ASF
 * licenses this file to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 *
 */
public abstract class CountMinUtils {

	// Murmur is faster than an SHA-based approach and provides as-good collision 
	// resistance.  The combinatorial generation approach described in 
	// http://www.eecs.harvard.edu/~kirsch/pubs/bbbf/esa06.pdf 
	// does prove to work in actual tests, and is obviously faster 
	// than performing further iterations of murmur. 
	public static int[] getHashBuckets(String key, int hashCount, int max) {
		byte[] b;
		try {
			b = key.getBytes("UTF-16");
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
		return getHashBuckets(b, hashCount, max);
	}

	static int[] getHashBuckets(byte[] b, int hashCount, int max) {
		int[] result = new int[hashCount];
		int hash1 = MurmurHash.hash(b, b.length, 0);
		int hash2 = MurmurHash.hash(b, b.length, hash1);
		for (int i = 0; i < hashCount; i++) {
			result[i] = Math.abs((hash1 + i * hash2) % max);
		}
		return result;
	}
}
