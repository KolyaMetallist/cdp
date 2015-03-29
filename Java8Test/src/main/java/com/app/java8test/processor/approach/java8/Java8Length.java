package com.app.java8test.processor.approach.java8;

import static java.util.Comparator.comparing;
import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toList;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map.Entry;
import java.util.stream.Collectors;

/**
 * This class implements Non-Java 8 approach for the task "Length"
 * 
 * Find first three words which have duplicates and print them inversely (e.g. map -> pam) in the upper case sorted by length in ascending order. (task name: duplicates)
 * PAM
 * WOLLA
 * STNEMUGRA
 *
 */
public class Java8Length implements Java8Approach {

	/**
	 * Executes the text analyzer task
	 * 
	 * @param file - the input file
	 * @param flag - the flag for single/multi threading
	 * @return list of results
	 * 
	 * @see com.app.java8test.processor.approach.Approach#taskExecution(java.io.File, boolean)
	 */
	@Override
	public List<?> taskExecution(File file, boolean parallel) {
		List<Entry<String, Integer>> list = new ArrayList<>();
		try {
			if (parallel) {
				list = Collections.synchronizedList(this.readWordsFromText(file, parallel))
					.parallelStream() // Stream<String>
					.distinct() // remove duplicates
					.collect(Collectors.toMap(identity(), String::length)) // convert to map of words and their length
					.entrySet() // extract entrySet from Map
					.parallelStream() // Stream<Entry<String, Integer>>
					.sorted(comparing((Entry<String, Integer> e) -> e.getValue()).reversed()) // sort by length descending
					.limit(3) // truncate 3 top entries
					.collect(toList());	
			} else {			
				list = this.readWordsFromText(file, parallel)
					.stream() // Stream<String>
					.distinct() // remove duplicates
					.collect(Collectors.toMap(identity(), String::length)) // convert to map of words and their length
					.entrySet() // extract entrySet from Map
					.stream() // Stream<Entry<String, Integer>>
					.sorted(comparing((Entry<String, Integer> e) -> e.getValue()).reversed()) // sort by length descending
					.limit(3) // truncate 3 top entries
					.collect(toList());				
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return list;
	}

}
