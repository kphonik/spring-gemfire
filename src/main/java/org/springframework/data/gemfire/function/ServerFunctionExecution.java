/*
 * Copyright 2002-2011 the original author or authors.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */
package org.springframework.data.gemfire.function;

import java.io.Serializable;

import com.gemstone.gemfire.cache.RegionService;
import com.gemstone.gemfire.cache.execute.Execution;
import com.gemstone.gemfire.cache.execute.Function;
import com.gemstone.gemfire.cache.execute.FunctionService;

/**
 * @author David Turanski
 *
 */
public class ServerFunctionExecution<T> extends FunctionExecution<T> {
	

	private final RegionService regionService;

    /**
     * 
     * @param regionService  e.g., Cache,Client, or GemFireCache
     * @param function
     * @param args
     */
	public ServerFunctionExecution(RegionService regionService, Function function, Serializable... args) {
		super(function, args);
		this.regionService = regionService; 
	}
	
	/**
	 * 
	  * @param regionService  e.g., Cache,Client, or GemFireCache
	 * @param functionId
	 * @param args
	 */
	public ServerFunctionExecution(RegionService regionService, String functionId, Serializable... args) {
		super(functionId, args);
		this.regionService = regionService; 
	}

	/* (non-Javadoc)
	 * @see org.springframework.data.gemfire.function.FunctionExecution#getExecution()
	 */
	@Override
	protected Execution getExecution() {
		return FunctionService.onServer(this.regionService);
	}
}